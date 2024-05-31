package com.cdp.misrutinas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MRSQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DBMisRutinas";
    private static final int DATABASE_VERSION = 1;
    String[][] tables = new String[12][2]; //ESTO VA CAMBIANDO A MEDIDA LE VAN AÑADIENDO TABLAS. DEBE TERMINAR EN [12][2].

    public MRSQLiteHelper(Context contexto) {
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        tables[0][0] = "CREATE TABLE Rol (id_rol INTEGER PRIMARY KEY AUTOINCREMENT, nombre_rol CHAR(10) NOT NULL)";
        tables[0][1] = "Rol";

        tables[1][0] = "CREATE TABLE Usuario (id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, apellido VARCHAR(45), nombre VARCHAR(45), dni INTEGER,  email VARCHAR(75) NOT NULL,tel INTEGER, pass VARCHAR(16),  id_rol INTEGER, FOREIGN KEY (id_rol) REFERENCES Rol(id_rol))";
        tables[1][1] = "Usuario";

        tables[2][0] = "CREATE TABLE Socio (id_socio INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario))";
        tables[2][1] = "Socio";

        tables[3][0] = "CREATE TABLE Entrenador (id_entrenador INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario))";
        tables[3][1] = "Entrenador";

        tables[4][0] = "CREATE TABLE Calendario (id_calen INTEGER PRIMARY KEY AUTOINCREMENT, hora TIME NOT NULL, fecha DATE NOT NULL)";
        tables[4][1] = "Calendario";

        tables[5][0] = "CREATE TABLE Clase (id_clase INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(45) NOT NULL, precio DECIMAL NOT NULL, descripcion VARCHAR(200) NOT NULL)";
        tables[5][1] = "Clase";

        tables[6][0] = "CREATE TABLE Horario (id_hor INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(30) NOT NULL, inicio TIME NOT NULL, fin TIME NOT NULL)";
        tables[6][1] = "Horario";

        tables[7][0] = "CREATE TABLE Dia (id_dia INTEGER PRIMARY KEY AUTOINCREMENT, nombre CHAR(9) NOT NULL)";
        tables[7][1] = "Dia";

        tables[8][0] = "CREATE TABLE Turno (id_turno INTEGER PRIMARY KEY AUTOINCREMENT, id_dia INTEGER, id_hor INTEGER, nombre VARCHAR(30) NOT NULL, FOREIGN KEY (id_dia) REFERENCES dia(id_dia), FOREIGN KEY (id_hor) REFERENCES horario(id_hor))";
        tables[8][1] = "Turno";

        tables[9][0] = "CREATE TABLE Clase_turno (id_clase_turno INTEGER PRIMARY KEY AUTOINCREMENT, id_clase INTEGER,id_turno INTEGER, id_entrenador INTEGER, FOREIGN KEY (id_clase) REFERENCES clase(id_clase), FOREIGN KEY (id_turno) REFERENCES turno(id_turno),FOREIGN KEY (id_entrenador) REFERENCES entrenador(id_entrenador))";
        tables[9][1] = "Clase_turno";

        tables[10][0] = "CREATE TABLE Socio_clase_turno (id_soc_cla_tur INTEGER PRIMARY KEY AUTOINCREMENT, id_socio INTEGER,id_clase_turno INTEGER, nombre VARCHAR(30) NOT NULL, FOREIGN KEY (id_socio) REFERENCES socio(id_socio), FOREIGN KEY (id_clase_turno) REFERENCES clase_turno(id_clase_turno))";
        tables[10][1] = "Socio_clase_turno";

        tables[11][0] = "CREATE TABLE Soc_cla_tur_cal (id_soc_cla_tur_cal INTEGER PRIMARY KEY AUTOINCREMENT, id_soc_cla_tur INTEGER,id_calen INTEGER, FOREIGN KEY (id_soc_cla_tur) REFERENCES socio_clase_turno(id_soc_cla_tur), FOREIGN KEY (id_calen) REFERENCES calendario(id_calen))";
        tables[11][1] = "Soc_cla_tur_cal";

        for(int i = 0; i < tables.length; i++) {
            String tableSQL = tables[i][0];
            db.execSQL(tableSQL);
        }

        ContentValues admin = new ContentValues();
        admin.put("nombre_rol", "admin");
        db.insert("Rol", null, admin);

        ContentValues socio = new ContentValues();
        socio.put("nombre_rol", "socio");
        db.insert("Rol", null, socio);

        ContentValues trainer = new ContentValues();
        trainer.put("nombre_rol", "entrenador");
        db.insert("Rol", null, trainer);

        ContentValues lunes = new ContentValues();
        lunes.put("nombre", "Lunes");
        db.insert("Dia", null, lunes);

        ContentValues martes = new ContentValues();
        martes.put("nombre", "Martes");
        db.insert("Dia", null, martes);

        ContentValues miercoles = new ContentValues();
        miercoles.put("nombre", "Miércoles");
        db.insert("Dia", null, miercoles);

        ContentValues jueves = new ContentValues();
        jueves.put("nombre", "Jueves");
        db.insert("Dia", null, jueves);

        ContentValues viernes = new ContentValues();
        viernes.put("nombre", "Viernes");
        db.insert("Dia", null, viernes);

        ContentValues sabado = new ContentValues();
        sabado.put("nombre", "Sábado");
        db.insert("Dia", null, sabado);

        ContentValues domingo = new ContentValues();
        domingo.put("nombre", "Domingo");
        db.insert("Dia", null, domingo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        for(int i = 0; i < tables.length; i++) {
            String tableName = tables[i][1];
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
        }
        onCreate(db);
    }
}
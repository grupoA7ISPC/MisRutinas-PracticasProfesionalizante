package com.cdp.misrutinas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cdp.misrutinas.entidades.Clases;

import java.util.ArrayList;

public class CrudClase extends MRSQLiteHelper{

    Context context;

    public CrudClase(Context contexto) {
        super(contexto);
        this.context = contexto;
    }
    public long insertarClase (String nombreClase,String precioClase, String descripcionClase){

        MRSQLiteHelper usdbh = new MRSQLiteHelper(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombreClase);
        values.put("precio", precioClase);
        values.put("descripcion", descripcionClase);

        long id = db.insert("Clase", null, values);

        return id;

    }

    public ArrayList<Clases> mostrarClases(){

        MRSQLiteHelper usdbh = new MRSQLiteHelper(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        ArrayList<Clases> listaClases = new ArrayList<>();
        Clases clase = null;
        Cursor cursorClases = null;

        cursorClases = db.rawQuery("SELECT * FROM Clase", null);

        if (cursorClases.moveToFirst()){
            do {
                clase = new Clases();
                clase.setId(cursorClases.getInt(0));
                clase.setNombre(cursorClases.getString(1));
                clase.setPrecio(cursorClases.getString(2));
                clase.setDescripcion(cursorClases.getString(3));

                listaClases.add(clase);
            }while (cursorClases.moveToNext());
        }

        cursorClases.close();

        return listaClases;

    }

    public Clases verClase(int id){

        MRSQLiteHelper usdbh = new MRSQLiteHelper(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        Clases clase = null;
        Cursor cursorClases = null;

        cursorClases = db.rawQuery("SELECT * FROM Clase WHERE id_clase = " + id + " LIMIT 1", null);

        if (cursorClases.moveToFirst()){
            clase = new Clases();
            clase.setId(cursorClases.getInt(0));
            clase.setNombre(cursorClases.getString(1));
            clase.setPrecio(cursorClases.getString(2));
            clase.setDescripcion(cursorClases.getString(3));
        }

        cursorClases.close();

        return clase;
    }

    public boolean editarClase (int id, String nombreClase,String precioClase, String descripcionClase){
        boolean correcto = false;

        MRSQLiteHelper usdbh = new MRSQLiteHelper(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        try {

            db.execSQL("UPDATE Clase SET nombre = '" + nombreClase + "', precio = '" + precioClase + "', descripcion = '" + descripcionClase + "' WHERE id_clase = '" + id + "'");
            correcto = true;
        }catch (Exception ex) {
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarClase (int id){
        boolean correcto = false;

        MRSQLiteHelper usdbh = new MRSQLiteHelper(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        try {

            db.execSQL("DELETE FROM Clase WHERE id_clase = '" + id + "'");
            correcto = true;
        }catch (Exception ex) {
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }
        return correcto;
    }

}

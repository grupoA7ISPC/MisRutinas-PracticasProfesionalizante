package com.cdp.misrutinas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.Toast;

import com.cdp.misrutinas.entidades.Clases;

import java.util.ArrayList;

public class CrudClase extends MRSQLiteHelper{

    Context context;

    public CrudClase(Context contexto) {
        super(contexto);
        this.context = contexto;
    }
    private boolean areFieldsValid(FieldLengthValidation... fields) {
        for (FieldLengthValidation fieldValidation : fields) {
            String field = fieldValidation.field;
            int minLength = fieldValidation.minLength;
            int maxLength = fieldValidation.maxLength;

            if (field != null && (field.length() < minLength || field.length() > maxLength)) {
                return false;
            }
        }
        return true;
    }
    public boolean esValida(String nombre, String descripcion, String precio) {
        SQLiteDatabase db = super.getWritableDatabase();
        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(descripcion) || TextUtils.isEmpty(precio)) {
            Toast.makeText(context, "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show();
            db.close();
            return false;
        }
        if (!areFieldsValid(
                new FieldLengthValidation(nombre, 1, 45),
                new FieldLengthValidation(descripcion, 1, 200),
                new FieldLengthValidation(precio, 1, 12)
        )){
            if (nombre.length() < 1 || nombre.length() > 45) {
                Toast.makeText(context, "El nombre de la clase debe tener entre 1 y 45 caracteres.", Toast.LENGTH_SHORT).show();
            }
            if (descripcion.length() < 1 || descripcion.length() > 200) {
                Toast.makeText(context, "La descripción de la clase debe tener entre 1 y 200 caracteres.", Toast.LENGTH_SHORT).show();
            }
            if (precio.length() < 1 || precio.length() > 12) {
                Toast.makeText(context, "Maximo 12 números.", Toast.LENGTH_SHORT).show();
            }
            db.close();
            return false;
        }


        return true;
    }
    public long insertarClase (String nombreClase,String precioClase, String descripcionClase){

        MRSQLiteHelper usdbh = new MRSQLiteHelper(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        if (esValida(nombreClase, descripcionClase, precioClase)) {
            try{
                ContentValues values = new ContentValues();
                values.put("nombre", nombreClase);
                values.put("precio", precioClase);
                values.put("descripcion", descripcionClase);

                long id = db.insert("Clase", null, values);
                if (id != -1){
                    db.close();
                    return id;

                }else {
                    db.close();
                    return -1;
                }

            } catch (Exception e){
                Toast.makeText(context, "Error al insertar el registro.", Toast.LENGTH_SHORT).show();
                db.close();
                return -1;
            }
        } else {
            db.close();
            return -1;
        }

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

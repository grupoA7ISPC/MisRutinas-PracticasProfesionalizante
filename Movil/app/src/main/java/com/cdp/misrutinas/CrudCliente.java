package com.cdp.misrutinas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.Toast;

import com.cdp.misrutinas.entidades.Clientes;
import com.cdp.misrutinas.entidades.Usuario;
import com.cdp.misrutinas.data.ValidationUserResult;

import java.util.ArrayList;

public class CrudCliente extends MRSQLiteHelper{
    Context context;

    public CrudCliente(Context contexto) {
        super(contexto);
        this.context = contexto;
    }

    //---------VALIDACIONES--------------

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

    private boolean isValidEmail(SQLiteDatabase db, String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (email.matches(emailRegex) && !existeRegistro(db, email, "email")) {
            return true;
        }
        return false;
    }

    private boolean existeRegistro(SQLiteDatabase db, String value, String columna) {
        String[] columns = {columna};
        String selection = columna + "= ?";
        String[] selectionArgs = {value};

        Cursor cursor = db.query("Usuario", columns, selection, selectionArgs, null, null, null);

        boolean existe = cursor.moveToFirst();
        cursor.close();

        return existe;
    }


    public ValidationUserResult isValidUser(String email, String password, String nombre, String apellido, String dni, String tel) {
        ValidationUserResult result = new ValidationUserResult();
        SQLiteDatabase db = super.getWritableDatabase();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(nombre) || TextUtils.isEmpty(apellido) || TextUtils.isEmpty(dni) || TextUtils.isEmpty(tel)) {
            if (TextUtils.isEmpty(email)) result.emailError = "Complete su email.";
            if (TextUtils.isEmpty(password)) result.passwordError = "Introduzca una contraseña.";
            if (TextUtils.isEmpty(nombre)) result.nombreError = "Introduzca su nombre.";
            if (TextUtils.isEmpty(apellido)) result.apellidoError = "Introduzca su apellido.";
            if (TextUtils.isEmpty(dni)) result.dniError = "Complete con su DNI.";
            if (TextUtils.isEmpty(tel)) result.telError = "Complete con su nro de teléfono (sin 0 ni 15).";
            result.isValid = false;
            return result;
        }

        if (existeRegistro(db, email, "email")) {
            result.emailError = "Este email ya está registrado.";
            result.isValid = false;
            return result;
        }

        if (!areFieldsValid(
                new FieldLengthValidation(email, 8, 75),
                new FieldLengthValidation(password, 8, 16),
                new FieldLengthValidation(nombre, 3, 15),
                new FieldLengthValidation(apellido, 3, 15),
                new FieldLengthValidation(dni, 8, 8),
                new FieldLengthValidation(tel, 10, 15)
        )) {
            if (email.length() < 8 || email.length() > 75) {
                result.emailError = "El email debe tener entre 8 y 75 caracteres.";
            }

            if (!isValidEmail(db, email)) {
                result.emailError = "Ingrese un email válido.";
            }

            if (password.length() < 8 || password.length() > 16) {
                result.passwordError = "La contraseña debe tener entre 8 y 16 caracteres.";
            }

            if (nombre.length() < 3 || nombre.length() > 15) {
                result.nombreError = "El nombre debe tener entre 3 y 15 caracteres.";
            }

            if (apellido.length() < 3 || apellido.length() > 15) {
                result.apellidoError = "El apellido debe tener entre 3 y 15 caracteres.";
            }

            if (tel.length() < 10 || tel.length() > 15) {
                result.telError = "Ingrese un teléfono válido, entre 10-15 caracteres con código de área y sin 15.";
            }

            if (!dni.matches("\\d{8}")) {
                result.dniError = "El DNI debe tener 8 dígitos numéricos.";
            }

            result.isValid = false;
            return result;
        }

        result.isValid = true;

        return result;
    }

    public long insertarUsuario(String email, String password, String nombre, String apellido, String dni, String tel) {
        SQLiteDatabase db = super.getWritableDatabase();

        ValidationUserResult validationResult = isValidUser(email, password, nombre, apellido, dni, tel);
        if (validationResult.isValid) {
            try {
                ContentValues values = new ContentValues();
                values.put("email", email);
                values.put("pass", password);
                values.put("nombre", nombre);
                values.put("apellido", apellido);
                values.put("dni", dni);
                values.put("tel", tel);
                values.put("id_rol", 1);

                long idUsuario = db.insert("Usuario", null, values);

                db.close();
                return idUsuario;

            } catch (Exception e) {
                Toast.makeText(context, "Error al crear usuario desde CrudCliente", Toast.LENGTH_SHORT).show();
                db.close();
                return -1;
            }
        } else {
            db.close();
            return -1;
        }
    }

    public Usuario getUsuariofromDB(String email) {
        Usuario usuario = null;
        SQLiteDatabase db = super.getWritableDatabase();

        String query = "SELECT * FROM Usuario WHERE email = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                usuario = new Usuario();

                // Retrieve data from the cursor
                usuario.setEmail(email);
                usuario.setPassword(cursor.getString(7));
                usuario.setUsername(cursor.getString(1));
                //"CREATE TABLE Usuario (id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, apellido VARCHAR(45), nombre VARCHAR(45), dni INTEGER,  email VARCHAR(75) NOT NULL,tel INTEGER, pass VARCHAR(16), active BOOLEAN, id_rol INTEGER, FOREIGN KEY (id_rol) REFERENCES Rol(id_rol))";
            }

            cursor.close();
        }

        return usuario;
    }



//------------------------------------
//"CREATE TABLE Socio (id_socio INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario))";
// "CREATE TABLE Entrenador (id_entrenador INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario))"

    public long insertarCliente(String nombre, String apellido, String dni, String email, String telefono, int idRol) {
        SQLiteDatabase db = super.getWritableDatabase();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(apellido) || TextUtils.isEmpty(dni) || TextUtils.isEmpty(email) || TextUtils.isEmpty(telefono)) {
            Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            db.close();
            return -1;
        }

        if (!areFieldsValid(
                new FieldLengthValidation(nombre, 1, 25),
                new FieldLengthValidation(apellido, 1, 25),
                new FieldLengthValidation(dni, 8, 8),
                new FieldLengthValidation(email, 8, 45),
                new FieldLengthValidation(telefono, 10, 10)
        )) {
            if (nombre.length() < 1 || nombre.length() > 45) {
                Toast.makeText(context, "Nombre debe tener entre 1 y 25 caracteres.", Toast.LENGTH_SHORT).show();
            }

            if (apellido.length() < 1 || apellido.length() > 45) {
                Toast.makeText(context, "Apellido debe tener entre 1 y 25 caracteres.", Toast.LENGTH_SHORT).show();
            }

            if (dni.length() != 8) {
                Toast.makeText(context, "DNI debe tener exactamente 8 caracteres.", Toast.LENGTH_SHORT).show();
            }

            if (email.length() < 8 || email.length() > 75) {
                Toast.makeText(context, "Email debe tener entre 8 y 45 caracteres.",  Toast.LENGTH_SHORT).show();
            }

            if (telefono.length() != 10) {
                Toast.makeText(context, "Teléfono debe tener exactamente 10 caracteres.",  Toast.LENGTH_SHORT).show();
            }

            db.close();
            return -1;
        }

        if (!isValidEmail(db, email)) {
            Toast.makeText(context, "El campo Email tiene un formato inválido o ya está en uso", Toast.LENGTH_SHORT).show();
            db.close();
            return -1;
        }

        ContentValues valuesUser = new ContentValues();
        valuesUser.put("nombre", nombre); //NULL
        valuesUser.put("apellido", apellido); //NULL
        valuesUser.put("dni", dni); //NULL
        valuesUser.put("email", email); //NOT NULL
        valuesUser.put("tel", telefono); //NOT NULL
        valuesUser.put("id_rol", idRol); //NOT NULL

        long idUsuario = db.insert("Usuario", null, valuesUser);

        String table = (idRol == 3) ? "Entrenador" : "Socio";

        if (idUsuario != -1) {
            ContentValues valuesCliente = new ContentValues();
            valuesCliente.put("id_usuario", idUsuario);
            long idCliente = db.insert(table, null, valuesCliente);

            db.close();

            return idCliente;
        } else {
            db.close();
            return -1;
        }
    }


    public ArrayList<Clientes> listarClientes(int idRol){
        SQLiteDatabase db = super.getWritableDatabase();

        ArrayList<Clientes> listaClientes = new ArrayList<>();
        Clientes cliente = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM Usuario WHERE id_rol = " + idRol, null);

        if (cursor.moveToFirst()){
            do {
                cliente = new Clientes();
                cliente.setId(cursor.getInt(0));
                cliente.setNombre(cursor.getString(3));
                cliente.setApellido(cursor.getString(2));

                listaClientes.add(cliente);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listaClientes;
    }

    public Clientes verCliente(int id){
        SQLiteDatabase db = super.getWritableDatabase();

        Clientes cliente = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM Usuario WHERE id_usuario = " + id + " Limit 1", null );

        //"CREATE TABLE Usuario (id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, username VARCHAR(20) UNIQUE , apellido VARCHAR(45), nombre VARCHAR(45), dni INTEGER,  email VARCHAR(75) NOT NULL,tel INTEGER, pass VARCHAR(16), active BOOLEAN, id_rol INTEGER, FOREIGN KEY (id_rol) REFERENCES Rol(id_rol))";
        if (cursor.moveToFirst()){
            cliente = new Clientes();
            cliente.setId(cursor.getInt(0));
            cliente.setNombre(cursor.getString(3));
            cliente.setApellido(cursor.getString(2));
            cliente.setDni(cursor.getString(4));
            cliente.setEmail(cursor.getString(5));
            cliente.setTel(cursor.getString(6));
        }
        cursor.close();
        return cliente;
    }

    public boolean editarCliente(int id, String nombre, String apellido, String dni, String email, String telefono, int idRol) {
        boolean correcto = false;
        SQLiteDatabase db = super.getWritableDatabase();

        try {
            ContentValues valuesUser = new ContentValues();
            valuesUser.put("nombre", nombre);
            valuesUser.put("apellido", apellido);
            valuesUser.put("dni", dni);
            valuesUser.put("email", email);
            valuesUser.put("tel", telefono);
            valuesUser.put("id_rol", idRol);

            db.update("Usuario", valuesUser, "id_usuario = " + id, null);

            correcto = true;
        } catch (Exception ex) {
            ex.toString();
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarCliente(int id) {
        boolean correcto = false;
        SQLiteDatabase db = super.getWritableDatabase();

        try {
            db.delete("Usuario", "id_usuario = " + id, null);
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
        } finally {
            db.close();
        }

        return correcto;
    }
}
package com.cdp.misrutinas.entidades;

public class Clientes {
    //"CREATE TABLE Usuario (id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, username VARCHAR(20) UNIQUE , apellido VARCHAR(45), nombre VARCHAR(45), dni INTEGER,  email VARCHAR(75) NOT NULL,tel INTEGER, pass VARCHAR(16), active BOOLEAN, id_rol INTEGER, FOREIGN KEY (id_rol) REFERENCES Rol(id_rol))";
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String tel;
    private int id_rol;

    public int getId() {
        return idUsuario;
    }

    public void setId(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreApellido() {
        return nombre + " " + apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }
}

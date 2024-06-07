CREATE DATABASE IF NOT exists mis_rutinas; 
use mis_rutinas; 

CREATE TABLE Rol (id_rol INTEGER PRIMARY KEY auto_increment, nombre_rol CHAR(10) NOT NULL);

CREATE TABLE Usuario (id_usuario INTEGER NOT NULL PRIMARY KEY auto_increment, username VARCHAR(20) UNIQUE , apellido VARCHAR(45), nombre VARCHAR(45), dni INTEGER,  email VARCHAR(75) NOT NULL,tel INTEGER, pass VARCHAR(16),  id_rol INTEGER, FOREIGN KEY (id_rol) REFERENCES Rol(id_rol));

CREATE TABLE Socio (id_socio INTEGER NOT NULL PRIMARY KEY auto_increment, id_usuario INTEGER, FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario));

CREATE TABLE Entrenador (id_entrenador INTEGER NOT NULL PRIMARY KEY auto_increment, id_usuario INTEGER, FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario));

CREATE TABLE Calendario (id_calen INTEGER PRIMARY KEY auto_increment, hora TIME NOT NULL, fecha DATE NOT NULL);

CREATE TABLE Clase (id_clase INTEGER PRIMARY KEY auto_increment, nombre VARCHAR(45) NOT NULL, precio DECIMAL NOT NULL, descripcion VARCHAR(200) NOT NULL);

CREATE TABLE Horario (id_hor INTEGER PRIMARY KEY auto_increment, nombre VARCHAR(30) NOT NULL, inicio TIME NOT NULL, fin TIME NOT NULL);

CREATE TABLE Dia (id_dia INTEGER PRIMARY KEY auto_increment, nombre CHAR(9) NOT NULL);

CREATE TABLE Turno (id_turno INTEGER PRIMARY KEY auto_increment, id_dia INTEGER, id_hor INTEGER, nombre VARCHAR(30) NOT NULL, FOREIGN KEY (id_dia) REFERENCES dia(id_dia), FOREIGN KEY (id_hor) REFERENCES horario(id_hor));

CREATE TABLE Clase_turno (id_clase_turno INTEGER PRIMARY KEY auto_increment, id_clase INTEGER,id_turno INTEGER, id_entrenador INTEGER, FOREIGN KEY (id_clase) REFERENCES clase(id_clase), FOREIGN KEY (id_turno) REFERENCES turno(id_turno),FOREIGN KEY (id_entrenador) REFERENCES entrenador(id_entrenador));

CREATE TABLE Socio_clase_turno (id_soc_cla_tur INTEGER PRIMARY KEY auto_increment, id_socio INTEGER,id_clase_turno INTEGER, nombre VARCHAR(30) NOT NULL, FOREIGN KEY (id_socio) REFERENCES socio(id_socio), FOREIGN KEY (id_clase_turno) REFERENCES clase_turno(id_clase_turno));

CREATE TABLE Soc_cla_tur_cal (id_soc_cla_tur_cal INTEGER PRIMARY KEY auto_increment, id_soc_cla_tur INTEGER,id_calen INTEGER, FOREIGN KEY (id_soc_cla_tur) REFERENCES socio_clase_turno(id_soc_cla_tur), FOREIGN KEY (id_calen) REFERENCES calendario(id_calen))
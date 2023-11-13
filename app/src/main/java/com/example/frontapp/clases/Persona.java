package com.example.frontapp.clases;

import java.util.concurrent.atomic.AtomicInteger;

public class Persona {
    private int idPersona;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String cedula;
    private boolean flag_es_doctor;
    private static final AtomicInteger count = new AtomicInteger(0);

    public Persona(String nombre, String apellido, String telefono, String email, String cedula, boolean flag_es_doctor) {
        this.idPersona = count.incrementAndGet();
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.cedula = cedula;
        this.flag_es_doctor = flag_es_doctor;
    }

    public Persona() {
        this.idPersona = 0;
        this.nombre = "";
        this.apellido = "";
        this.telefono = "";
        this.email = "";
        this.cedula = "";
        this.flag_es_doctor = false;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public boolean isFlag_es_doctor() {
        return flag_es_doctor;
    }

    public void setFlag_es_doctor(boolean flag_es_doctor) {
        this.flag_es_doctor = flag_es_doctor;
    }
}

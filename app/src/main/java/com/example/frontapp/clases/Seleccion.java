package com.example.frontapp.clases;

import java.util.ArrayList;

public class Seleccion {
    private int id_paciente = 0;
    private int id_doctor = 0;
    private int id_reserva = 0;
    private static Seleccion instance;

    public static Seleccion getInstance() {
        if (instance == null) {
            instance = new Seleccion();
        }
        return instance;
    }

    public void limpiar(){
        id_doctor = 0;
        id_reserva = 0;
        id_paciente = 0;
    }
    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }
}

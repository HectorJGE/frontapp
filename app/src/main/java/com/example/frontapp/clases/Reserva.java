package com.example.frontapp.clases;


import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class Reserva {
    private int idReserva;
    private Persona paciente;
    private Persona doctor;
    private Calendar fecha;
    private String hora;

    private static final AtomicInteger count = new AtomicInteger(0);

    public Reserva(Persona paciente, Persona doctor, Calendar fecha, String hora) {
        this.idReserva = count.incrementAndGet();
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Persona getPaciente() {
        return paciente;
    }

    public void setPaciente(Persona paciente) {
        this.paciente = paciente;
    }

    public Persona getDoctor() {
        return doctor;
    }

    public void setDoctor(Persona doctor) {
        this.doctor = doctor;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

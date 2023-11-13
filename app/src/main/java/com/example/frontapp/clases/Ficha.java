package com.example.frontapp.clases;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class Ficha {
    private int idFicha;
    private Persona paciente;
    private Persona doctor;
    private Calendar fecha;
    private Categoria categoria;
    private String motivo_consulta;
    private String diagnostico;

    private static final AtomicInteger count = new AtomicInteger(0);
    public Ficha(Persona paciente, Persona doctor, Calendar fecha, Categoria categoria, String motivo_consulta, String diagnostico) {
        this.idFicha = count.incrementAndGet();
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.categoria = categoria;
        this.motivo_consulta = motivo_consulta;
        this.diagnostico = diagnostico;
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getMotivo_consulta() {
        return motivo_consulta;
    }

    public void setMotivo_consulta(String motivo_consulta) {
        this.motivo_consulta = motivo_consulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}

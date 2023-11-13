package com.example.frontapp.clases;

import java.util.concurrent.atomic.AtomicInteger;

public class Categoria {

    private int idCategoria;
    private String descripcion;
    private static final AtomicInteger count = new AtomicInteger(0);
    public Categoria(String descripcion) {
        this.idCategoria = count.incrementAndGet();
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

package com.example.frontapp.clases;

import java.util.ArrayList;

public class ServiceCategoria {
    private ArrayList<Categoria> categorias = new ArrayList<>();
    private static ServiceCategoria instance;

    public static ServiceCategoria getInstance() {
        if (instance == null) {
            instance = new ServiceCategoria();
        }
        return instance;
    }


    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public ArrayList<Categoria> obtenerCategorias() {
        return categorias;
    }
}

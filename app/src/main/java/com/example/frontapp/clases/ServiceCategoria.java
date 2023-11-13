package com.example.frontapp.clases;

import java.util.ArrayList;

public class ServiceCategoria {
    private ArrayList<Categoria> categorias = new ArrayList<>();
    private static ServiceCategoria instance;

    public static ServiceCategoria getInstance() {
        if (instance == null) {
            instance = new ServiceCategoria();
            instance.agregarCategoria(new Categoria("Categoria 1"));
            instance.agregarCategoria(new Categoria("Categoria 2"));
        }
        return instance;
    }


    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public ArrayList<Categoria> obtenerCategorias() {
        return categorias;
    }

    public Categoria obtenerCategoriaPorID(int id){
        for(Categoria c : categorias){
            if(c.getIdCategoria() == id){
                return c;
            }
        }
        return new Categoria();
    }
}

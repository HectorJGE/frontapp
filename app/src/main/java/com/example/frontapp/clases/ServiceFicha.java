package com.example.frontapp.clases;

import java.util.ArrayList;

public class ServiceFicha {

    private ArrayList<Ficha> fichas = new ArrayList<>();
    private static ServiceFicha instance;

    public static ServiceFicha getInstance() {
        if (instance == null) {
            instance = new ServiceFicha();
        }
        return instance;
    }

    public void agregarFicha(Ficha ficha) {
        fichas.add(ficha);
    }

    public ArrayList<Ficha> obtenerFichas() {
        return fichas;
    }

}

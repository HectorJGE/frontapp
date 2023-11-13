package com.example.frontapp.clases;

import java.util.ArrayList;

public class ServicePersona {

    private ArrayList<Persona> personas = new ArrayList<>();
    private static ServicePersona instance;

    public static ServicePersona getInstance() {
        if (instance == null) {
            instance = new ServicePersona();
            //pruebas
            instance.agregarPersona(new Persona("Daniel","Gutierrez","0972285955","aosidfaofa@gmail.com", "4879045", true));
            instance.agregarPersona(new Persona("Jorge","Perez","0972385597","xcvxofa@gmail.com", "656775", false));
            instance.agregarPersona(new Persona("Sara","Vera","0972385597","xcvxofa@gmail.com", "656775", false));
            instance.agregarPersona(new Persona("Ramon","Miranda","0972385597","xcvxofa@gmail.com", "656775", true));
        }
        return instance;
    }

    public void agregarPersona(Persona persona) {
        personas.add(persona);
    }

    public ArrayList<Persona> obtenerPersonas() {
        return personas;
    }

    public Persona obtenerPersonaPorID(int id){
        for(Persona p : personas){
            if(p.getIdPersona() == id){
                return p;
            }
        }
        return new Persona();
    }

}

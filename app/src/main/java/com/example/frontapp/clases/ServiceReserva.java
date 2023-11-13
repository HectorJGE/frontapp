package com.example.frontapp.clases;

import java.util.ArrayList;

public class ServiceReserva {
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private static ServiceReserva instance;

    public static ServiceReserva getInstance() {
        if (instance == null) {
            instance = new ServiceReserva();
        }
        return instance;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public ArrayList<Reserva> obtenerReservas() {
        return reservas;
    }
    public Reserva obtenerReservaPorID(int id){
        for(Reserva r : reservas){
            if(r.getIdReserva() == id){
                return r;
            }
        }
        return new Reserva();
    }
}

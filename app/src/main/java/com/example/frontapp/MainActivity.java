package com.example.frontapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.frontapp.categorias.CategoriasActivity;
import com.example.frontapp.clases.Persona;
import com.example.frontapp.clases.ServiceCategoria;
import com.example.frontapp.clases.ServicePersona;
import com.example.frontapp.fichas.FichasActivity;
import com.example.frontapp.personas.PersonasActivity;
import com.example.frontapp.reservas.ReservasActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServiceCategoria serviceCategoria = ServiceCategoria.getInstance();
        ServicePersona servicePersona = ServicePersona.getInstance();
    }

    public void btnEventoIrCategorias(View v){
        Intent categoriasIntent = new Intent(this, CategoriasActivity.class);
        startActivity(categoriasIntent);
    }

    public void btnEventoIrPersonas(View v){
        Intent personasIntent = new Intent(this, PersonasActivity.class);
        startActivity(personasIntent);
    }

    public void btnEventoIrReservas(View v){
        //Debe haber al menos un doctor y un paciente para las reservas
        ServicePersona svPersona = ServicePersona.getInstance();
        int doctores = 0;
        int noDoctores = 0;
        for (Persona persona : svPersona.obtenerPersonas()) {
            if (persona.isFlag_es_doctor()) {
                doctores++;
            } else {
                noDoctores++;
            }
        }

        if(doctores == 0 && noDoctores == 0){
            Toast.makeText(MainActivity.this, R.string.errorAmbos, Toast.LENGTH_SHORT).show();
        }else if(doctores == 0){
            Toast.makeText(MainActivity.this, R.string.errorNoDoctores, Toast.LENGTH_SHORT).show();
        }else if(noDoctores == 0) {
            Toast.makeText(MainActivity.this, R.string.errorNoPacientes, Toast.LENGTH_SHORT).show();
        }else{
            Intent reservasIntent = new Intent(this, ReservasActivity.class);
            startActivity(reservasIntent);
        }
    }

    public void btnEventoIrFichas(View v){
        Intent fichasIntent = new Intent(this, FichasActivity.class);
        startActivity(fichasIntent);
    }
}
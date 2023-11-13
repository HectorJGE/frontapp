package com.example.frontapp.reservas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.frontapp.R;
import com.example.frontapp.clases.Persona;
import com.example.frontapp.clases.Seleccion;
import com.example.frontapp.clases.ServicePersona;

import java.util.ArrayList;

public class AgregarDoctorReservaActivity extends AppCompatActivity {

    private Spinner spinnerDoctor;
    private String doctorSeleccionado;
    int id_doctor;
    Seleccion seleccion = Seleccion.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_doctor_reserva);
        spinnerDoctor = findViewById(R.id.spinnerReservaDoctor);

        //obtener doctores
        ServicePersona personas = ServicePersona.getInstance();
        ArrayList<String> doctores = new ArrayList<String>();
        for(Persona persona : personas.obtenerPersonas()){
            if(persona.isFlag_es_doctor()){
                doctores.add(persona.getIdPersona() + " - " +
                        persona.getNombre() + " " + persona.getApellido() + ", " + persona.getCedula());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, doctores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoctor.setAdapter(adapter);

        // seleccion
        spinnerDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                doctorSeleccionado = doctores.get(position);
                String[] partes = doctorSeleccionado.split(" ");
                id_doctor = Integer.parseInt(partes[0].trim());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                doctorSeleccionado = doctores.get(0); //el primer doctor por defecto
                String[] partes = doctorSeleccionado.split(" ");
                id_doctor = Integer.parseInt(partes[0].trim());
            }
        });
    }

    public void btnEventoIrAgregarFechaReserva(View v){
        seleccion.setId_doctor(id_doctor);
        Intent agregarFechaIntent = new Intent(this, AgregarFechaReservaActivity.class);
        startActivity(agregarFechaIntent);
        finish();
    }
}
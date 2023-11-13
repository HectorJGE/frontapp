package com.example.frontapp.fichas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontapp.R;
import com.example.frontapp.clases.Persona;
import com.example.frontapp.clases.Seleccion;
import com.example.frontapp.clases.ServicePersona;

import java.util.ArrayList;

public class AgregarPacienteReservaActivity extends AppCompatActivity {

    private Spinner spinnerPaciente;
    private String pacienteSeleccionado;
    int id_paciente;
    Seleccion seleccion = Seleccion.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_paciente_reserva);
        spinnerPaciente = findViewById(R.id.spinnerReservaPaciente);

        //obtener pacientes
        ServicePersona personas = ServicePersona.getInstance();
        ArrayList<String> pacientes = new ArrayList<String>();
        for(Persona persona : personas.obtenerPersonas()){
            if(!persona.isFlag_es_doctor()){
                pacientes.add(persona.getIdPersona() + " - " +
                        persona.getNombre() + " " + persona.getApellido() + ", " + persona.getCedula());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pacientes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPaciente.setAdapter(adapter);

        // seleccion
        spinnerPaciente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                pacienteSeleccionado = pacientes.get(position);
                String[] partes = pacienteSeleccionado.split(" ");
                id_paciente = Integer.parseInt(partes[0].trim());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                pacienteSeleccionado = pacientes.get(0); //el primer paciente por defecto
                String[] partes = pacienteSeleccionado.split(" ");
                id_paciente = Integer.parseInt(partes[0].trim());
            }
        });
    }

    public void btnEventoIrAgregarDoctorReserva(View v){
        seleccion.setId_paciente(id_paciente);
        Intent agregarDoctorIntent = new Intent(this, AgregarDoctorReservaActivity.class);
        startActivity(agregarDoctorIntent);
        finish();
    }

}
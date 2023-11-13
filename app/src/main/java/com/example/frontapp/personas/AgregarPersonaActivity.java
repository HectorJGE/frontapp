package com.example.frontapp.personas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.frontapp.R;
import com.example.frontapp.categorias.CategoriasActivity;
import com.example.frontapp.clases.Categoria;
import com.example.frontapp.clases.Persona;
import com.example.frontapp.clases.ServiceCategoria;
import com.example.frontapp.clases.ServicePersona;

public class AgregarPersonaActivity extends AppCompatActivity {

    EditText nombre;
    EditText apellido;
    EditText telefono;
    EditText email;
    EditText cedula;
    CheckBox es_doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);
        nombre = findViewById(R.id.campoNombrePersona);
        apellido = findViewById(R.id.campoApellidoPersona);
        telefono = findViewById(R.id.campoTelefonoPersona);
        email = findViewById(R.id.campoEmailPersona);
        cedula = findViewById(R.id.campoCedulaPersona);
        es_doctor = findViewById(R.id.checkboxEsDoctor);
    }

    public void btnEventoGuardarPersona(View v){
        if(TextUtils.isEmpty( nombre.getText().toString() )) {
            nombre.setError("No puede dejar vacío");
        }else if(TextUtils.isEmpty( apellido.getText().toString() )){
            apellido.setError("No puede dejar vacío");
        }else if(TextUtils.isEmpty( telefono.getText().toString() )) {
            telefono.setError("No puede dejar vacío");
        }else if(TextUtils.isEmpty( email.getText().toString() )) {
            email.setError("No puede dejar vacío");
        }else if(TextUtils.isEmpty( cedula.getText().toString() )) {
            cedula.setError("No puede dejar vacío");
        }else{
            Persona nuevo = new Persona(nombre.getText().toString(),
                    apellido.getText().toString(), telefono.getText().toString(),
                    email.getText().toString(), cedula.getText().toString(), es_doctor.isChecked());
            ServicePersona servicePersona = ServicePersona.getInstance();
            servicePersona.agregarPersona(nuevo);

            Intent personasIntent = new Intent(this, PersonasActivity.class);
            startActivity(personasIntent);
            finish();
        }
    }


}
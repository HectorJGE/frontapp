package com.example.frontapp.categorias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.frontapp.MainActivity;
import com.example.frontapp.R;
import com.example.frontapp.clases.Categoria;
import com.example.frontapp.clases.ServiceCategoria;

public class AgregarCategoriaActivity extends AppCompatActivity {

    EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_categoria);
        nombre = findViewById(R.id.campoNombreCategoria);
    }

    public void btnEventoGuardarCategoria(View v){
        if(TextUtils.isEmpty( nombre.getText().toString() )) {
            nombre.setError("No puede dejar vacío");
        }else {
            Categoria nuevo = new Categoria(nombre.getText().toString());
            ServiceCategoria serviceCategoria = ServiceCategoria.getInstance();
            serviceCategoria.agregarCategoria(nuevo);
            Intent categoriasIntent = new Intent(this, CategoriasActivity.class);
            startActivity(categoriasIntent);
            finish();
        }
    }

    public void btnEventoRegresarCategorias(View v){
        Intent categoriasIntent = new Intent(this, CategoriasActivity.class);
        startActivity(categoriasIntent);
        finish();
    }
}
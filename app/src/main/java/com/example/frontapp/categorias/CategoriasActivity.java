package com.example.frontapp.categorias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frontapp.MainActivity;
import com.example.frontapp.R;
import com.example.frontapp.adapters.AdapterCategoria;
import com.example.frontapp.clases.Categoria;
import com.example.frontapp.clases.ServiceCategoria;

public class CategoriasActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    TextView txt;
    SearchView txtBuscar;
    private RecyclerView rvCategorias;
    private AdapterCategoria adapterCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        txtBuscar = findViewById(R.id.txtBuscar);
        rvCategorias = findViewById(R.id.rvListaCategorias);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvCategorias.setLayoutManager(layoutManager);

        ServiceCategoria serviceCategoria = ServiceCategoria.getInstance();
        adapterCategoria = new AdapterCategoria(serviceCategoria.obtenerCategorias());
        rvCategorias.setAdapter(adapterCategoria);

        if(serviceCategoria.obtenerCategorias().isEmpty()){
            txt = findViewById(R.id.sinCategorias);
            txt.setText("No hay categorías");
        }

        adapterCategoria.setOnBorrarClickListener(new AdapterCategoria.OnBorrarClickListener() {
            @Override
            public void onBorrarClick(int position) {
                borrarCategoria(position);
            }
        });
        txtBuscar.setOnQueryTextListener(this);
    }
    public void btnEventoIrAgregarCategorias(View v){
        Intent agregarCategoriasIntent = new Intent(this, AgregarCategoriaActivity.class);
        startActivity(agregarCategoriasIntent);
        finish();
    }

    private void borrarCategoria(int position) {
        ServiceCategoria serviceCategoria = ServiceCategoria.getInstance();
        serviceCategoria.obtenerCategorias().remove(position);

        adapterCategoria.notifyItemRemoved(position);
        Intent categoriasIntent = new Intent(this, CategoriasActivity.class);
        startActivity(categoriasIntent);
        finish();
    }

    public void btnEventoRegresarMenu(View v){
        Intent menuIntent = new Intent(this, MainActivity.class);
        startActivity(menuIntent);
        finish();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        adapterCategoria.filtrado(query);
        return false;
    }
}
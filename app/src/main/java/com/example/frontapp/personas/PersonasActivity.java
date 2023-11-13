package com.example.frontapp.personas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.frontapp.MainActivity;
import com.example.frontapp.R;
import com.example.frontapp.adapters.AdapterCategoria;
import com.example.frontapp.adapters.AdapterPersona;
import com.example.frontapp.categorias.AgregarCategoriaActivity;
import com.example.frontapp.categorias.CategoriasActivity;
import com.example.frontapp.clases.ServiceCategoria;
import com.example.frontapp.clases.ServicePersona;

public class PersonasActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, RadioGroup.OnCheckedChangeListener {

    TextView txt;
    SearchView txtBuscar;
    RadioGroup typePersona;
    private RecyclerView rvPersonas;
    private AdapterPersona adapterPersona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);
        typePersona = findViewById(R.id.typePersona);
        txtBuscar = findViewById(R.id.txtBuscar);
        rvPersonas = findViewById(R.id.rvListaPersonas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPersonas.setLayoutManager(layoutManager);

        ServicePersona servicePersona = ServicePersona.getInstance();
        adapterPersona = new AdapterPersona(servicePersona.obtenerPersonas());
        rvPersonas.setAdapter(adapterPersona);

        if(servicePersona.obtenerPersonas().isEmpty()){
            txt = findViewById(R.id.sinPersonas);
            txt.setText("No hay personas");
        }

        adapterPersona.setOnBorrarClickListener(new AdapterPersona.OnBorrarClickListener() {
            @Override
            public void onBorrarClick(int position) {
                borrarPersona(position);
            }
        });
        typePersona.setOnCheckedChangeListener(this);
        txtBuscar.setOnQueryTextListener(this);
    }

    public void btnEventoIrAgregarPersonas(View v){
        Intent agregarPersonasIntent = new Intent(this, AgregarPersonaActivity.class);
        startActivity(agregarPersonasIntent);
        finish();
    }

    private void borrarPersona(int position) {
        ServicePersona servicePersona = ServicePersona.getInstance();
        servicePersona.obtenerPersonas().remove(position);

        adapterPersona.notifyItemRemoved(position);
        Intent personasIntent = new Intent(this, PersonasActivity.class);
        startActivity(personasIntent);
        finish();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        adapterPersona.filtrado(query);
        return false;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int selected = typePersona.getCheckedRadioButtonId();
        adapterPersona.filtradoType(selected);
    }
}
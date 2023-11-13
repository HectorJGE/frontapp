package com.example.frontapp.fichas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.frontapp.R;
import com.example.frontapp.clases.Categoria;
import com.example.frontapp.clases.Ficha;
import com.example.frontapp.clases.Persona;
import com.example.frontapp.clases.Reserva;
import com.example.frontapp.clases.Seleccion;
import com.example.frontapp.clases.ServiceCategoria;
import com.example.frontapp.clases.ServiceFicha;
import com.example.frontapp.clases.ServicePersona;
import com.example.frontapp.clases.ServiceReserva;
import com.example.frontapp.reservas.ReservasActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class AgregarFichaActivity extends AppCompatActivity {

    private Spinner spinnerReserva;
    private String reservaSeleccionada;
    int id_reserva;
    private Spinner spinnerCategoria;
    private String categoriaSeleccionada;
    int id_categoria;
    EditText motivo_consulta;
    EditText diagnostico;
    Calendar fechahoy = Calendar.getInstance();
    String fecha = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_ficha);
        spinnerReserva = findViewById(R.id.spinnerReservaFicha);
        spinnerCategoria = findViewById(R.id.spinnerCategoriaFicha);

        // SPINNER RESERVAS
            //obtener reservas
            ServiceReserva reservas = ServiceReserva.getInstance();
            ArrayList<String> arrayreservas = new ArrayList<String>();
            for(Reserva reserva : reservas.obtenerReservas()){
                arrayreservas.add(reserva.getIdReserva() + " - " +
                        reserva.getPaciente().getNombre() + " " + reserva.getPaciente().getApellido() + ", "
                        + reserva.getDoctor().getNombre() + " " + reserva.getDoctor().getApellido());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayreservas);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerReserva.setAdapter(adapter);

            // seleccion
            spinnerReserva.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    reservaSeleccionada = arrayreservas.get(position);
                    String[] partes = reservaSeleccionada.split(" ");
                    id_reserva = Integer.parseInt(partes[0].trim());

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    reservaSeleccionada = arrayreservas.get(0);
                    String[] partes = reservaSeleccionada.split(" ");
                    id_reserva = Integer.parseInt(partes[0].trim());
                }
            });


        // SPINNER CATEGORIAS
            //obtener categorias
            ServiceCategoria categorias = ServiceCategoria.getInstance();
            ArrayList<String> arraycategorias = new ArrayList<String>();
            for(Categoria categoria : categorias.obtenerCategorias()){
                arraycategorias.add(categoria.getIdCategoria() + " - " +  categoria.getDescripcion());
            }

            ArrayAdapter<String> adapterCat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraycategorias);
            adapterCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCategoria.setAdapter(adapterCat);

            // seleccion
            spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    categoriaSeleccionada = arraycategorias.get(position);
                    String[] partes = categoriaSeleccionada.split(" ");
                    id_categoria = Integer.parseInt(partes[0].trim());

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    categoriaSeleccionada = arraycategorias.get(0);
                    String[] partes = categoriaSeleccionada.split(" ");
                    id_categoria = Integer.parseInt(partes[0].trim());
                }
            });

        int dia = fechahoy.get(Calendar.DAY_OF_MONTH);
        int mes = fechahoy.get(Calendar.MONTH) + 1;
        int anio = fechahoy.get(Calendar.YEAR);
        fecha = dia + "/" + mes + "/" + anio;
        motivo_consulta = findViewById(R.id.campoMotivoFicha);
        diagnostico = findViewById(R.id.campoDiagnosticoFicha);
    }

    public void btnEventoGuardarFicha(View v) {
        ServiceFicha svFicha = ServiceFicha.getInstance();

        ServiceReserva svReserva = ServiceReserva.getInstance();
        Reserva reserva = svReserva.obtenerReservaPorID(id_reserva);

        ServiceCategoria svCategoria = ServiceCategoria.getInstance();
        Categoria categoria = svCategoria.obtenerCategoriaPorID(id_categoria);

        Ficha ficha = new Ficha(reserva.getPaciente(),reserva.getDoctor(), fechahoy, categoria,
                motivo_consulta.getText().toString(), diagnostico.getText().toString());
        svFicha.agregarFicha(ficha);

        Intent fichasIntent = new Intent(this, FichasActivity.class);
        startActivity(fichasIntent);
        finish();
    }
}
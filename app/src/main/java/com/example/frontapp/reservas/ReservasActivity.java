package com.example.frontapp.reservas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.frontapp.R;
import com.example.frontapp.adapters.AdapterPersona;
import com.example.frontapp.adapters.AdapterReserva;
import com.example.frontapp.clases.ServicePersona;
import com.example.frontapp.clases.ServiceReserva;
import com.example.frontapp.personas.AgregarPersonaActivity;
import com.example.frontapp.personas.PersonasActivity;

import java.util.Calendar;

public class ReservasActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    Button btnFecha;
    EditText etFechaDesde;
    EditText etFechaHasta;
    private Calendar fechaDesde = Calendar.getInstance();
    private Calendar fechaHasta = Calendar.getInstance();
    TextView txt;
    private RecyclerView rvReservas;
    private AdapterReserva adapterReserva;
    SearchView txtBuscar;
    RadioButton doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);
        txtBuscar = findViewById(R.id.txtBuscar);
        doctor = findViewById(R.id.doctores);
        rvReservas = findViewById(R.id.rvListaReservas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvReservas.setLayoutManager(layoutManager);
        etFechaDesde = findViewById(R.id.fechaDesde);
        etFechaHasta = findViewById(R.id.fechaHasta);
        btnFecha = findViewById(R.id.btnFecha);

        ServiceReserva serviceReserva = ServiceReserva.getInstance();
        adapterReserva = new AdapterReserva(serviceReserva.obtenerReservas());
        rvReservas.setAdapter(adapterReserva);
        if(serviceReserva.obtenerReservas().isEmpty()){
            txt = findViewById(R.id.sinReservas);
            txt.setText("No hay reservas");
        }

        adapterReserva.setOnBorrarClickListener(new AdapterReserva.OnBorrarClickListener() {
            @Override
            public void onBorrarClick(int position) {
                borrarReserva(position);
            }
        });
        txtBuscar.setOnQueryTextListener(this);

        etFechaDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elegirFechaDesde();
            }
        });

        etFechaHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elegirFechaHasta();
            }
        });
        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterReserva.filtrarPorFecha(fechaDesde,fechaHasta);
            }
        });
    }

    public void btnEventoIrAgregarPacienteReserva(View v){
        Intent agregarPacienteIntent = new Intent(this, AgregarPacienteReservaActivity.class);
        startActivity(agregarPacienteIntent);
        finish();
    }

    private void borrarReserva(int position) {
        ServiceReserva serviceReserva = ServiceReserva.getInstance();
        serviceReserva.obtenerReservas().remove(position);

        adapterReserva.notifyItemRemoved(position);
        Intent reservasIntent = new Intent(this, ReservasActivity.class);
        startActivity(reservasIntent);
        finish();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        adapterReserva.filtrado(query, doctor.isChecked());
        return false;
    }
    private void elegirFechaDesde() {
        int año = fechaDesde.get(Calendar.YEAR);
        int mes = fechaDesde.get(Calendar.MONTH);
        int día = fechaDesde.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                fechaDesde = Calendar.getInstance();
                fechaDesde.set(year, month, day);
                etFechaDesde.setText(day + "/" + (month + 1) + "/" + year); // Muestra la fecha en el EditText
            }
        }, año, mes, día);

        datePickerDialog.show();
    }
    private void elegirFechaHasta() {
        int año = fechaHasta.get(Calendar.YEAR);
        int mes = fechaHasta.get(Calendar.MONTH);
        int día = fechaHasta.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                fechaHasta = Calendar.getInstance();
                fechaHasta.set(year, month, day);
                etFechaHasta.setText(day + "/" + (month + 1) + "/" + year); // Muestra la fecha en el EditText
            }
        }, año, mes, día);

        datePickerDialog.show();
    }

}
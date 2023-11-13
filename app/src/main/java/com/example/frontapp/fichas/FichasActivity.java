package com.example.frontapp.fichas;

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
import android.widget.SearchView;
import android.widget.TextView;

import com.example.frontapp.R;
import com.example.frontapp.adapters.AdapterFicha;
import com.example.frontapp.clases.ServiceFicha;

import java.util.Calendar;


public class FichasActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    Button btnFecha;
    EditText etFechaDesde;
    EditText etFechaHasta;
    private Calendar fechaDesde = Calendar.getInstance();
    private Calendar fechaHasta = Calendar.getInstance();
    TextView txt;
    private RecyclerView rvFichas;
    private AdapterFicha adapterFicha;
    SearchView txtBuscar;
    RadioButton doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichas);
        txtBuscar = findViewById(R.id.txtBuscar);
        doctor = findViewById(R.id.doctores);
        rvFichas = findViewById(R.id.rvListaFichas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvFichas.setLayoutManager(layoutManager);
        etFechaDesde = findViewById(R.id.fechaDesde);
        etFechaHasta = findViewById(R.id.fechaHasta);
        btnFecha = findViewById(R.id.btnFecha);

        ServiceFicha serviceFicha = ServiceFicha.getInstance();
        adapterFicha = new AdapterFicha(serviceFicha.obtenerFichas());
        rvFichas.setAdapter(adapterFicha);

        if(serviceFicha.obtenerFichas().isEmpty()){
            txt = findViewById(R.id.sinFichas);
            txt.setText("No hay fichas");
        }

        adapterFicha.setOnBorrarClickListener(new AdapterFicha.OnBorrarClickListener() {
            @Override
            public void onBorrarClick(int position) {
                borrarFicha(position);
            }
        });
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
                adapterFicha.filtrarPorFecha(fechaDesde,fechaHasta);
            }
        });

        txtBuscar.setOnQueryTextListener(this);
    }

    public void btnEventoIrAgregarFichas(View v){
        Intent agregarFichasIntent = new Intent(this, AgregarFichaActivity.class);
        startActivity(agregarFichasIntent);
        finish();
    }

    private void borrarFicha(int position) {
        ServiceFicha serviceFicha = ServiceFicha.getInstance();
        serviceFicha.obtenerFichas().remove(position);

        adapterFicha.notifyItemRemoved(position);
        Intent fichasIntent = new Intent(this, FichasActivity.class);
        startActivity(fichasIntent);
        finish();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        adapterFicha.filtrado(query, doctor.isChecked());
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
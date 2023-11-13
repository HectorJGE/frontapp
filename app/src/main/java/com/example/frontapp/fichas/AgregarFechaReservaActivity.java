package com.example.frontapp.fichas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontapp.R;
import com.example.frontapp.clases.Persona;
import com.example.frontapp.clases.Reserva;
import com.example.frontapp.clases.Seleccion;
import com.example.frontapp.clases.ServicePersona;
import com.example.frontapp.clases.ServiceReserva;

import java.util.ArrayList;
import java.util.Calendar;

public class AgregarFechaReservaActivity extends AppCompatActivity {

    EditText editTextFecha;
    private Calendar fechaSeleccionada = Calendar.getInstance();
    private Spinner spinnerHora;
    private ArrayList<String> horas = new ArrayList<String>();
    private String horaSeleccinada = " ";
    Seleccion seleccion = Seleccion.getInstance();
    ServiceReserva svReserva = ServiceReserva.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_fecha_reserva);

        Toast.makeText(AgregarFechaReservaActivity.this, fechaSeleccionada.getTime().toString(), Toast.LENGTH_SHORT).show();
        spinnerHora = findViewById(R.id.spinnerReservaHora);
        editTextFecha = findViewById(R.id.seleccionarFechaReserva);

        // Calendario
        editTextFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarCalendario();
            }
        });

        // Hora
        horas.add("09:00 - 10:00");
        horas.add("10:00 - 11:00");
        horas.add("11:00 - 12:00");
        horas.add("12:00 - 13:00");
        horas.add("13:00 - 14:00");
        horas.add("14:00 - 15:00");
        horas.add("15:00 - 16:00");
        horas.add("16:00 - 17:00");
        horas.add("17:00 - 18:00");
        horas.add("18:00 - 19:00");
        horas.add("19:00 - 20:00");
        horas.add("20:00 - 21:00");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, horas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHora.setAdapter(adapter);

        spinnerHora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                horaSeleccinada = horas.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                horaSeleccinada = horas.get(0); //a primera hora por defecto
            }
        });
        //Hora

    }

    public void btnEventoAgregarReserva(View v){
        ServicePersona svPersona = ServicePersona.getInstance();
        Persona paciente = svPersona.obtenerPersonaPorID( seleccion.getId_paciente() );
        Persona doctor = svPersona.obtenerPersonaPorID( seleccion.getId_doctor() );

        Reserva reserva = new Reserva(paciente, doctor, fechaSeleccionada, horaSeleccinada);
        svReserva.agregarReserva(reserva);
        seleccion.limpiar();

        Intent reservasIntent = new Intent(this, FichasActivity.class);
        startActivity(reservasIntent);
        finish();
    }

    private void mostrarCalendario() {
        int año = fechaSeleccionada.get(Calendar.YEAR);
        int mes = fechaSeleccionada.get(Calendar.MONTH);
        int día = fechaSeleccionada.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                fechaSeleccionada = Calendar.getInstance();
                fechaSeleccionada.set(year, month, day);
                editTextFecha.setText(day + "/" + (month + 1) + "/" + year); // Muestra la fecha en el EditText
            }
        }, año, mes, día);

        datePickerDialog.show();
    }
}
package com.example.frontapp.adapters;

import android.app.Person;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontapp.R;
import com.example.frontapp.clases.Categoria;
import com.example.frontapp.clases.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterPersona extends RecyclerView.Adapter<AdapterPersona.ViewHolder>{
    private ArrayList<Persona> dataPersona = new ArrayList<>();
    private ArrayList<Persona> listaOriginal;

    private AdapterPersona.OnBorrarClickListener borrarClickListener;

    public interface OnBorrarClickListener {
        void onBorrarClick(int position);
    }

    public void setOnBorrarClickListener(AdapterPersona.OnBorrarClickListener listener) {
        this.borrarClickListener = listener;
    }

    @NonNull
    @Override
    public AdapterPersona.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listapersonas, parent, false);
        AdapterPersona.ViewHolder pvh = new AdapterPersona.ViewHolder(v);

        pvh.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = pvh.getAdapterPosition();
                if (borrarClickListener != null && position != RecyclerView.NO_POSITION) {
                    borrarClickListener.onBorrarClick(position);
                }
            }
        });

        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPersona.ViewHolder holder, int i) {
        Persona persona = dataPersona.get(i);
        holder.tvID.setText("ID: " + String.valueOf(persona.getIdPersona()));
        holder.tvNombre.setText("Nombre: " + persona.getNombre());
        holder.tvApellido.setText("Apellido: " + persona.getApellido());
        holder.tvTelefono.setText("Teléfono: " + persona.getTelefono());
        holder.tvEmail.setText("E-mail: " + persona.getEmail());
        holder.tvCedula.setText("Cédula: " + persona.getCedula());
        if(persona.isFlag_es_doctor()){
            holder.tvFlagEsDoctor.setText("Doctor");
        }else{
            holder.tvFlagEsDoctor.setText("Paciente");
        }
    }

    @Override
    public int getItemCount() {
        return dataPersona.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvID;
        public TextView tvNombre;
        public TextView tvApellido;
        public TextView tvTelefono;
        public TextView tvEmail;
        public TextView tvCedula;
        public TextView tvFlagEsDoctor;
        public Button btnBorrar;
        public ViewHolder (View v){
            super(v);
            tvID = v.findViewById(R.id.rvTextoIDPersona);
            tvNombre = v.findViewById(R.id.rvTextoNombrePersona);
            tvApellido = v.findViewById(R.id.rvTextoApellidoPersona);
            tvTelefono = v.findViewById(R.id.rvTextoTelefonoPersona);
            tvEmail = v.findViewById(R.id.rvTextoEmailPersona);
            tvCedula = v.findViewById(R.id.rvTextoCedulaPersona);
            tvFlagEsDoctor = v.findViewById(R.id.rvTextoEsDoctorPersona);
            btnBorrar = v.findViewById(R.id.btnBorrarPersona);
        }
    }

    public AdapterPersona(ArrayList<Persona> listaPersonas){
        this.dataPersona = listaPersonas;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(dataPersona);
    }

    public void filtradoType(int checkedID){
        dataPersona.clear();
        dataPersona.addAll(listaOriginal);
        if ( checkedID == R.id.pacientes){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Persona> colleccion = dataPersona.stream()
                        .filter(i -> !i.isFlag_es_doctor())
                        .collect(Collectors.toList());
                dataPersona.clear();
                dataPersona.addAll(colleccion);
            }else{
                for (Persona p:listaOriginal) {
                    if(p.isFlag_es_doctor()) {
                        dataPersona.add(p);
                    }
                }
            }
        }else if ( checkedID == R.id.doctores){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Persona> colleccion = dataPersona.stream()
                        .filter(Persona::isFlag_es_doctor)
                        .collect(Collectors.toList());
                dataPersona.clear();
                dataPersona.addAll(colleccion);
            }else{
                for (Persona p:listaOriginal) {
                    if(p.isFlag_es_doctor()) {
                        dataPersona.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if( longitud == 0){
            dataPersona.clear();
            dataPersona.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Persona> colleccion = dataPersona.stream()
                        .filter(i ->
                                i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()) ||
                                i.getApellido().toLowerCase().contains(txtBuscar.toLowerCase())
                        )
                        .collect(Collectors.toList());
                dataPersona.clear();
                dataPersona.addAll(colleccion);
            }else{
                for (Persona p:listaOriginal) {
                    if(p.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()) ||
                        p.getApellido().toLowerCase().contains(txtBuscar.toLowerCase())){
                        dataPersona.add(p);
                    }
                }
            }
        }

        notifyDataSetChanged();
    }
}

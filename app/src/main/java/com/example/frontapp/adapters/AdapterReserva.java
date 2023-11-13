package com.example.frontapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontapp.R;
import com.example.frontapp.clases.Persona;
import com.example.frontapp.clases.Reserva;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterReserva extends RecyclerView.Adapter<AdapterReserva.ViewHolder>{

    private ArrayList<Reserva> dataReserva = new ArrayList<>();
    private ArrayList<Reserva> listaOriginal;
    private AdapterReserva.OnBorrarClickListener borrarClickListener;

    public interface OnBorrarClickListener {
        void onBorrarClick(int position);
    }

    public void setOnBorrarClickListener(AdapterReserva.OnBorrarClickListener listener) {
        this.borrarClickListener = listener;
    }

    @NonNull
    @Override
    public AdapterReserva.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listareservas, parent, false);
        AdapterReserva.ViewHolder pvh = new AdapterReserva.ViewHolder(v);

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
    public void onBindViewHolder(@NonNull AdapterReserva.ViewHolder holder, int position) {
        Reserva reserva = dataReserva.get(position);
        holder.tvID.setText("ID: " + String.valueOf(reserva.getIdReserva()));
        holder.tvPaciente.setText("Paciente: " + reserva.getPaciente().getNombre()
                + " " + reserva.getPaciente().getApellido() + ", " + reserva.getPaciente().getCedula());
        holder.tvDoctor.setText("Doctor: " + reserva.getDoctor().getNombre()
                + " " + reserva.getDoctor().getApellido() + ", " + reserva.getDoctor().getCedula());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaEnFormatoDDMMYYYY = sdf.format(reserva.getFecha().getTime());

        holder.tvFecha.setText("Fecha: " + fechaEnFormatoDDMMYYYY);
        holder.tvHora.setText("Hora: " + reserva.getHora().toString());
    }

    @Override
    public int getItemCount() {
        return dataReserva.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvID;
        public TextView tvPaciente;
        public TextView tvDoctor;
        public TextView tvFecha;
        public TextView tvHora;
        public Button btnBorrar;
        public ViewHolder (View v){
            super(v);
            tvID = v.findViewById(R.id.rvTextoIDReserva);
            tvPaciente = v.findViewById(R.id.rvTextoPacienteReserva);
            tvDoctor = v.findViewById(R.id.rvTextoDoctorReserva);
            tvFecha = v.findViewById(R.id.rvTextoFechaReserva);
            tvHora = v.findViewById(R.id.rvTextoHoraReserva);
            btnBorrar = v.findViewById(R.id.btnBorrarReserva);
        }
    }

    public AdapterReserva(ArrayList<Reserva> listaReservas){
        this.dataReserva = listaReservas;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(dataReserva);
    }

    public void filtrarPorFecha(Calendar fDesde, Calendar fHasta){
        dataReserva.clear();
        dataReserva.addAll(listaOriginal);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            List<Reserva> colleccion = dataReserva.stream()
                    .filter(i -> i.getFecha().after(fDesde) && i.getFecha().before(fHasta) )
                    .collect(Collectors.toList());
            dataReserva.clear();
            dataReserva.addAll(colleccion);
        }
        notifyDataSetChanged();
    }

    public void filtrado( String txtBuscar, Boolean doctor){
        int longitud = txtBuscar.length();
        if( longitud == 0 ){
            dataReserva.clear();
            dataReserva.addAll(listaOriginal);
        }else{

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Reserva> colleccion;
                if ( doctor ){
                    colleccion = dataReserva.stream()
                            .filter(i -> i.getDoctor().getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                            .collect(Collectors.toList());
                }else{
                    colleccion = dataReserva.stream()
                            .filter(i -> i.getPaciente().getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                            .collect(Collectors.toList());
                }
                dataReserva.clear();
                dataReserva.addAll(colleccion);
            }else{
                for (Reserva r:listaOriginal) {
                    if( doctor ){
                        if(r.getDoctor().getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                            dataReserva.add(r);
                        }
                    }else{
                        if(r.getPaciente().getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                            dataReserva.add(r);
                        }
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}

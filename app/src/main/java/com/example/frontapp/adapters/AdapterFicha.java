package com.example.frontapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontapp.R;
import com.example.frontapp.clases.Ficha;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterFicha extends RecyclerView.Adapter<AdapterFicha.ViewHolder>{

    private ArrayList<Ficha> dataFicha = new ArrayList<>();
    private ArrayList<Ficha> listaOriginal;
    private AdapterFicha.OnBorrarClickListener borrarClickListener;

    public interface OnBorrarClickListener {
        void onBorrarClick(int position);
    }

    public void setOnBorrarClickListener(AdapterFicha.OnBorrarClickListener listener) {
        this.borrarClickListener = listener;
    }

    @NonNull
    @Override
    public AdapterFicha.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listareservas, parent, false);
        AdapterFicha.ViewHolder pvh = new AdapterFicha.ViewHolder(v);

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
    public void onBindViewHolder(@NonNull AdapterFicha.ViewHolder holder, int position) {
        Ficha ficha = dataFicha.get(position);
        holder.tvID.setText("ID: " + String.valueOf(ficha.getIdFicha()));
        holder.tvPaciente.setText("Paciente: " + ficha.getPaciente().getNombre()
                + " " + ficha.getPaciente().getApellido() + ", " + ficha.getPaciente().getCedula());
        holder.tvDoctor.setText("Doctor: " + ficha.getDoctor().getNombre()
                + " " + ficha.getDoctor().getApellido() + ", " + ficha.getDoctor().getCedula());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaEnFormatoDDMMYYYY = sdf.format(ficha.getFecha().getTime());

        holder.tvFecha.setText("Fecha: " + fechaEnFormatoDDMMYYYY);
        holder.tvCategoria.setText("Categoria: " + ficha.getCategoria().toString());
        holder.tvMotivo.setText("Motivo: " + ficha.getMotivo_consulta().toString());
        holder.tvDiagnostico.setText("Diagnostico: " + ficha.getDiagnostico().toString());
    }

    @Override
    public int getItemCount() {
        return dataFicha.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvID;
        public TextView tvPaciente;
        public TextView tvDoctor;
        public TextView tvFecha;
        public TextView tvCategoria;
        public TextView tvMotivo;
        public TextView tvDiagnostico;
        public Button btnBorrar;
        public ViewHolder (View v){
            super(v);
            tvID = v.findViewById(R.id.rvTextoIDReserva);
            tvPaciente = v.findViewById(R.id.rvTextoPacienteReserva);
            tvDoctor = v.findViewById(R.id.rvTextoDoctorReserva);
            tvFecha = v.findViewById(R.id.rvTextoFechaReserva);
            tvCategoria = v.findViewById(R.id.rvTextoHoraReserva);
            tvMotivo = v.findViewById(R.id.rvTextoHoraReserva);
            tvDiagnostico = v.findViewById(R.id.rvTextoHoraReserva);
            btnBorrar = v.findViewById(R.id.btnBorrarReserva);
        }
    }

    public AdapterFicha(ArrayList<Ficha> listaFichas){
        this.dataFicha = listaFichas;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(dataFicha);
    }

    public void filtrado( String txtBuscar, Boolean doctor){
        int longitud = txtBuscar.length();
        if( longitud == 0 ){
            dataFicha.clear();
            dataFicha.addAll(listaOriginal);
        }else{

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Ficha> colleccion;
                if ( doctor ){
                    colleccion = dataFicha.stream()
                            .filter(i -> i.getDoctor().getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                            .collect(Collectors.toList());
                }else{
                    colleccion = dataFicha.stream()
                            .filter(i -> i.getPaciente().getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                            .collect(Collectors.toList());
                }
                dataFicha.clear();
                dataFicha.addAll(colleccion);
            }else{
                for (Ficha f:listaOriginal) {
                    if( doctor ){
                        if(f.getDoctor().getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                            dataFicha.add(f);
                        }
                    }else{
                        if(f.getPaciente().getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                            dataFicha.add(f);
                        }
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}

package com.example.frontapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontapp.R;
import com.example.frontapp.categorias.CategoriasActivity;
import com.example.frontapp.clases.Categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterCategoria extends RecyclerView.Adapter<AdapterCategoria.ViewHolder>{
    private ArrayList<Categoria> dataCategoria = new ArrayList<>();
    private ArrayList<Categoria> listaOriginal;

    private OnBorrarClickListener borrarClickListener;

    public interface OnBorrarClickListener {
        void onBorrarClick(int position);
    }

    public void setOnBorrarClickListener(OnBorrarClickListener listener) {
        this.borrarClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listacategorias, parent, false);
        ViewHolder pvh = new ViewHolder(v);

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Categoria categoria = dataCategoria.get(i);
        holder.tvID.setText("ID: " + String.valueOf(categoria.getIdCategoria()));
        holder.tvDescripcion.setText("Nombre: " + categoria.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return dataCategoria.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvID;
        public TextView tvDescripcion;
        public Button btnBorrar;
        public ViewHolder (View v){
            super(v);
            tvID = v.findViewById(R.id.rvTextoID);
            tvDescripcion = v.findViewById(R.id.rvTextoDescripcion);
            btnBorrar = v.findViewById(R.id.btnBorrarCategoria);
        }
    }

    public AdapterCategoria(ArrayList<Categoria> listaCategorias){
        this.dataCategoria = listaCategorias;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(dataCategoria);
    }
    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if( longitud == 0){
            dataCategoria.clear();
            dataCategoria.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Categoria> colleccion = dataCategoria.stream()
                        .filter(i -> i.getDescripcion().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                dataCategoria.clear();
                dataCategoria.addAll(colleccion);
            }else{
                for (Categoria c:listaOriginal) {
                    if(c.getDescripcion().toLowerCase().contains(txtBuscar.toLowerCase())){
                        dataCategoria.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}

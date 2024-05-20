package com.cdp.misrutinas.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.cdp.misrutinas.R;
import com.cdp.misrutinas.VerClaseActivity;
import com.cdp.misrutinas.entidades.Clases;

import java.util.ArrayList;

public class ListaClasesAdapter extends RecyclerView.Adapter<ListaClasesAdapter.ClaseViewHolder> {

    ArrayList<Clases> listaClases;

    public ListaClasesAdapter(ArrayList<Clases> listaClases){
        this.listaClases = listaClases;

    }

    @NonNull
    @Override
    public ClaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_clase,null,false);
        return new ClaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClaseViewHolder holder, int position) {
        holder.viewNombre.setText(listaClases.get(position).getNombre());
        holder.viewPrecio.setText(listaClases.get(position).getPrecio());
        holder.viewDescripcion.setText(listaClases.get(position).getDescripcion());

    }

    @Override
    public int getItemCount() {
        return listaClases.size();
    }

    public class ClaseViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre,viewPrecio,viewDescripcion;
        public ClaseViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            viewDescripcion = itemView.findViewById(R.id.viewDescripcion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerClaseActivity.class);
                    intent.putExtra("ID", listaClases.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}

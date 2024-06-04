package com.cdp.misrutinas.adaptadores;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.misrutinas.MainActivity;
import com.cdp.misrutinas.R;
import com.cdp.misrutinas.RegistroActivity;
import com.cdp.misrutinas.VerEntrenadorActivity;
import com.cdp.misrutinas.entidades.Clientes;

import java.util.ArrayList;

public class ListaEntrenadoresAdapter extends RecyclerView.Adapter<ListaEntrenadoresAdapter.EntrenadorViewHolder> {
    ArrayList<Clientes> listaEntrenadores;
    public ListaEntrenadoresAdapter(ArrayList<Clientes> listaEntrenadores) {
        this.listaEntrenadores = listaEntrenadores;
    }

    @NonNull
    @Override
    public EntrenadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entrenador, null, false);
        return new EntrenadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntrenadorViewHolder holder, int position) {
        holder.cardEntrenadorName.setText(listaEntrenadores.get(position).getNombreApellido());
    }

    @Override
    public int getItemCount() {
        return listaEntrenadores.size();
    }

    public class EntrenadorViewHolder extends RecyclerView.ViewHolder {
        TextView cardEntrenadorName;
        public EntrenadorViewHolder(@NonNull View itemView) {
            super(itemView);

            cardEntrenadorName = itemView.findViewById(R.id.cardEntrenadorName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent=new Intent(context, VerEntrenadorActivity.class);
                    intent.putExtra("ID",listaEntrenadores.get(getAdapterPosition()).getId());
                    context.startActivity(intent);


                }
            });
        }
    }
}

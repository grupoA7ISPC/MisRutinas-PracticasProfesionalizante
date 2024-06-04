package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cdp.misrutinas.adaptadores.ListaEntrenadoresAdapter;
import com.cdp.misrutinas.entidades.Clientes;
import java.util.ArrayList;

public class ListaProfesorActivity extends AppCompatActivity {
    private RecyclerView listaEntrenadores;
    private CrudCliente crud;
    ArrayList<Clientes> listaArrayEntrenadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_profesor);

        listaEntrenadores = findViewById(R.id.listaEntrenadores);
        listaEntrenadores.setLayoutManager(new LinearLayoutManager(this));

        CrudCliente crud = new CrudCliente(ListaProfesorActivity.this);

        listaArrayEntrenadores = new ArrayList<>();

        ListaEntrenadoresAdapter adapter = new ListaEntrenadoresAdapter(crud.listarClientes(3));
        listaEntrenadores.setAdapter(adapter);
    }

    public void btnVolverDashboard(View view){
        Intent intent=new Intent(ListaProfesorActivity.this,DashboardActivity.class);
        startActivity(intent);
    }

    public void irCrearEntrenador(View view) {
        Intent intent=new Intent(ListaProfesorActivity.this,CrearEntrenadorActivity.class);
        startActivity(intent);
    }
}
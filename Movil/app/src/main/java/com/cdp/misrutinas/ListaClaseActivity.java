package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cdp.misrutinas.adaptadores.ListaClasesAdapter;
import com.cdp.misrutinas.entidades.Clases;

import java.util.ArrayList;

public class ListaClaseActivity extends AppCompatActivity {

    RecyclerView listaClases;
    ArrayList<Clases> listaArrayClases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clase);
        listaClases = findViewById(R.id.listaClases);
        listaClases.setLayoutManager(new LinearLayoutManager(this));

        CrudClase dbClases = new CrudClase(ListaClaseActivity.this);

        listaArrayClases = new ArrayList<>();

        ListaClasesAdapter adapter = new ListaClasesAdapter(dbClases.mostrarClases());

        listaClases.setAdapter(adapter);
    }
    public void btnCrearClase(View view){
        Intent intent=new Intent(ListaClaseActivity.this, CrearClaseActivity.class);
        startActivity(intent);
    }

    public void btnVolverDashboard(View view){
        Intent intent=new Intent(ListaClaseActivity.this, DashboardActivity.class);
        startActivity(intent);
    }
}
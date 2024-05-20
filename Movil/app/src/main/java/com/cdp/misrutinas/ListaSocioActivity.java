package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cdp.misrutinas.adaptadores.ListaSociosAdapter;
import com.cdp.misrutinas.entidades.Clientes;
import java.util.ArrayList;

public class ListaSocioActivity extends AppCompatActivity {
    private RecyclerView listaSocios;
    private CrudCliente crud;
    ArrayList<Clientes> listaArraySocios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_socio);

        listaSocios = findViewById(R.id.listaSocios);
        listaSocios.setLayoutManager(new LinearLayoutManager(this));

        CrudCliente crud = new CrudCliente(ListaSocioActivity.this);

        listaArraySocios = new ArrayList<>();

        ListaSociosAdapter adapter = new ListaSociosAdapter(crud.listarClientes(2));
        listaSocios.setAdapter(adapter);
    }

    public void btnVolverDashboard(View view){
        Intent intent=new Intent(ListaSocioActivity.this,DashboardActivity.class);
        startActivity(intent);
    }

    public void irCrearSocio(View view) {
        Intent intent=new Intent(ListaSocioActivity.this,CrearSocioActivity.class);
        startActivity(intent);
    }
}
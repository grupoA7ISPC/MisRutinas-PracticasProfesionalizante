package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListaProfesorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_profesor);
    }

    public void btnVolverDashboard(View view){
        Intent intent=new Intent(ListaProfesorActivity.this,DashboardActivity.class);
        startActivity(intent);
    }

    public void irCrearEntrenador(View view) {
        Intent intent=new Intent(ListaProfesorActivity.this, CrearEntrenadorActivity.class);
        startActivity(intent);
    }
}
package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditarEntrenadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_profesor);
    }

    public void btnVolverEditarEntrenador(View view){
        Intent intent=new Intent(EditarEntrenadorActivity.this,ListaProfesorActivity.class);
        startActivity(intent);
    }

    public void btnGuardarEntrenador(View view){
        Intent intent=new Intent(EditarEntrenadorActivity.this,ListaProfesorActivity.class);
        startActivity(intent);
    }

    public void btnEliminarEntrenador(View view){
        Intent intent=new Intent(EditarEntrenadorActivity.this,ListaProfesorActivity.class);
        startActivity(intent);
    }
}
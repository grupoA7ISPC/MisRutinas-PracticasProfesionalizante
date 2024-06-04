package com.cdp.misrutinas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cdp.misrutinas.entidades.Clientes;

public class VerEntrenadorActivity extends AppCompatActivity {
    CrudCliente crud;
    TextView textNombreApellido;
    EditText textNombre, textApellido, textDNI, textEmail, textPhone;
    Button btnEditar, btnEliminar;
    int id = 0;
    Clientes entrenador;
    int idRol = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_socio);

        textNombreApellido = findViewById(R.id.textNombreApellido);
        textNombre = findViewById(R.id.textNombre);
        textApellido = findViewById(R.id.textApellido);
        textDNI = findViewById(R.id.textDNI);
        textEmail = findViewById(R.id.textEmail);
        textPhone = findViewById(R.id.textPhone);

        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        crud = new CrudCliente(this);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int)savedInstanceState.getSerializable("ID");
        }

        entrenador = crud.verCliente(id);

        if(entrenador != null){
            textNombreApellido.setText(entrenador.getNombreApellido());
            textNombreApellido.setInputType(InputType.TYPE_NULL);
            textNombre.setText(entrenador.getNombre());
            textNombre.setInputType(InputType.TYPE_NULL);
            textApellido.setText(entrenador.getApellido());
            textApellido.setInputType(InputType.TYPE_NULL);
            textDNI.setText(entrenador.getDni());
            textDNI.setInputType(InputType.TYPE_NULL);
            textEmail.setText(entrenador.getEmail());
            textEmail.setInputType(InputType.TYPE_NULL);
            textPhone.setText(entrenador.getTel());
            textPhone.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerEntrenadorActivity.this, EditarEntrenadorActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerEntrenadorActivity.this);
                builder.setMessage("¿Estás seguro de que quieres eliminar este entrenador? No podrás recuperarlo.")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (crud.eliminarCliente(id)) {
                                    volverListaEntrenadores();
                                };
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}}).show();
            }
        });
    }

    public void volverListaEntrenadores() {
        Intent intent=new Intent(VerEntrenadorActivity.this, ListaProfesorActivity.class);
        startActivity(intent);
    }

    public void cancelar(View view){
        Intent intent=new Intent(VerEntrenadorActivity.this, ListaProfesorActivity.class);
        startActivity(intent);
    }
    public void btnVolverListaSocios(View view){
        Intent intent=new Intent(VerEntrenadorActivity.this, ListaProfesorActivity.class);
        startActivity(intent);
    }
}

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

public class VerSocioActivity extends AppCompatActivity {
    CrudCliente crud;
    TextView textNombreApellido;
    EditText textNombre, textApellido, textDNI, textEmail, textPhone;
    Button btnEditar, btnEliminar;
    int id = 0;
    Clientes socio;
     int idRol = 2;

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

        socio = crud.verCliente(id);

        if(socio != null){
            textNombreApellido.setText(socio.getNombreApellido());
            textNombreApellido.setInputType(InputType.TYPE_NULL);
            textNombre.setText(socio.getNombre());
            textNombre.setInputType(InputType.TYPE_NULL);
            textApellido.setText(socio.getApellido());
            textApellido.setInputType(InputType.TYPE_NULL);
            textDNI.setText(socio.getDni());
            textDNI.setInputType(InputType.TYPE_NULL);
            textEmail.setText(socio.getEmail());
            textEmail.setInputType(InputType.TYPE_NULL);
            textPhone.setText(socio.getTel());
            textPhone.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerSocioActivity.this, EditarSocioActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerSocioActivity.this);
                builder.setMessage("¿Estás seguro de que quieres eliminar este socio? No podrás recuperarlo.")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (crud.eliminarCliente(id)) {
                                    volverListaSocios();
                                };
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}}).show();
            }
        });
    }

    public void volverListaSocios() {
        Intent intent=new Intent(VerSocioActivity.this, ListaSocioActivity.class);
        startActivity(intent);
    }

    public void cancelar(View view){
        Intent intent=new Intent(VerSocioActivity.this, ListaSocioActivity.class);
        startActivity(intent);
    }
    public void btnVolverListaSocios(View view){
        Intent intent=new Intent(VerSocioActivity.this, ListaSocioActivity.class);
        startActivity(intent);
    }
}
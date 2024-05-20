package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CrearSocioActivity extends AppCompatActivity {
    private CrudCliente crud;
    private EditText textNombre;
    private EditText textApellido;
    private EditText textDNI;
    private EditText textEmail;
    private EditText textPhone;

    private Button btnGuardar;

    private TextView textoResultado;

    private int idRol = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_socio);

        textNombre = findViewById(R.id.textNombre);
        textApellido = findViewById(R.id.textApellido);
        textDNI = findViewById(R.id.textDNI);
        textEmail = findViewById(R.id.textEmail);
        textPhone = findViewById(R.id.textPhone);

        btnGuardar = findViewById(R.id.btnGuardar);

        textoResultado = findViewById(R.id.textoResultado);

        crud = new CrudCliente(this);



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = textNombre.getText().toString();
                String apellido = textApellido.getText().toString();
                String dni = textDNI.getText().toString();
                String email = textEmail.getText().toString();
                String telefono = textPhone.getText().toString();

                long id = crud.insertarCliente(nombre, apellido, dni, email, telefono, idRol);

                if (id != -1) {
                    textoResultado.setText("¡Socio creado con exito! (ID: " + id+")");
                    irListaSocios();
                } else {
                    textoResultado.setText("Error al crear nuevo socio");
                }
            }
        });
    }

    public void cancelar(View view){
        Intent intent=new Intent(CrearSocioActivity.this, ListaSocioActivity.class);
        startActivity(intent);
    }

    private void irListaSocios(){
        Intent intent = new Intent(CrearSocioActivity.this, ListaSocioActivity.class);
        startActivity(intent);
    }


}
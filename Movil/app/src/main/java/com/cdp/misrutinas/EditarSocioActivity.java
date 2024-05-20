package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cdp.misrutinas.entidades.Clientes;

public class EditarSocioActivity extends AppCompatActivity {
    CrudCliente crud;
    TextView textNombreApellido;
    EditText textNombre, textApellido, textDNI, textEmail, textPhone;
    Button btnGuardar;
    int id = 0;
    Clientes socio;

    int idRol = 2;

    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_socio);

        textNombreApellido = findViewById(R.id.textNombreApellido);
        textNombre = findViewById(R.id.textNombre);
        textApellido = findViewById(R.id.textApellido);
        textDNI = findViewById(R.id.textDNI);
        textEmail = findViewById(R.id.textEmail);
        textPhone = findViewById(R.id.textPhone);

        btnGuardar = findViewById(R.id.btnGuardar);

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
            textNombre.setText(socio.getNombre());
            textApellido.setText(socio.getApellido());
            textDNI.setText(socio.getDni());
            textEmail.setText(socio.getEmail());
            textPhone.setText(socio.getTel());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean nombreValido = !textNombre.getText().toString().equals("");
                boolean apellidoValido = !textApellido.getText().toString().equals("");
                boolean dniValido = !textDNI.getText().toString().equals("");
                boolean emailValido = !textEmail.getText().toString().equals("");
                boolean phoneValido = !textPhone.getText().toString().equals("");

                if (nombreValido && apellidoValido && dniValido && emailValido && phoneValido) {
                    String nombre = textNombre.getText().toString();
                    String apellido = textApellido.getText().toString();
                    String dni = textDNI.getText().toString();
                    String email = textEmail.getText().toString();
                    String telefono = textPhone.getText().toString();
                    correcto = crud.editarCliente(id, nombre, apellido, dni, email, telefono, idRol);

                    if (correcto) {
                        Toast.makeText(EditarSocioActivity.this, "Socio editado", Toast.LENGTH_LONG).show();
                        volverSocio();
                    }else {
                        Toast.makeText(EditarSocioActivity.this, "Error, debe completar los campos obligatorios.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void volverSocio(){
        Intent intent = new Intent(this, VerSocioActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    public void btnVolverListaSocio(View view){
        Intent intent=new Intent(EditarSocioActivity.this,ListaSocioActivity.class);
        startActivity(intent);
    }

    public void cancelar(View view){
        Intent intent=new Intent(EditarSocioActivity.this,ListaSocioActivity.class);
        startActivity(intent);
    }
}
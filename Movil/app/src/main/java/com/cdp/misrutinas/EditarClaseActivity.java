package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdp.misrutinas.entidades.Clases;

public class EditarClaseActivity extends AppCompatActivity {

    EditText txtNombre, txtPrecio, txtDescripcion;
    Button btnGuardar, btnCancelar;
    ImageView btnvolverListaClase;
    TextView textViewEditarClase;

    boolean correcto = false;

    Clases clase;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_clase);

        txtNombre = findViewById(R.id.txtNombre);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnvolverListaClase = findViewById(R.id.volver_editar_clase);
        textViewEditarClase = findViewById(R.id.textViewEditarClase);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        CrudClase db = new CrudClase(EditarClaseActivity.this);
        clase = db.verClase(id);

        if (clase != null) {
            txtNombre.setText(clase.getNombre());
            txtPrecio.setText(clase.getPrecio());
            txtDescripcion.setText(clase.getDescripcion());
            textViewEditarClase.setText("Editar Clase: " + clase.getNombre());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtPrecio.getText().toString().equals("")) {
                    correcto = db.editarClase(id, txtNombre.getText().toString(), txtPrecio.getText().toString(), txtDescripcion.getText().toString());

                    if (correcto) {
                        Toast.makeText(EditarClaseActivity.this, "REGISTRO EDITADO", Toast.LENGTH_LONG).show();
                        volverListaClaseAgregada();
                    } else {
                        Toast.makeText(EditarClaseActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(EditarClaseActivity.this, "DEBES COMPLETAR TODOS LOS CAMPOS OBLIGATORIO", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverListaClaseAgregada();
            }
        });
    }

    private void volverListaClaseAgregada() {
        Intent intent = new Intent(this, ListaClaseActivity.class);
        startActivity(intent);
    }

    public void volverListaClase(View view) {
        Intent intent = new Intent(EditarClaseActivity.this, VerClaseActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}

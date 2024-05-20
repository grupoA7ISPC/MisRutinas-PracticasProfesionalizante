package com.cdp.misrutinas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cdp.misrutinas.entidades.Clases;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerClaseActivity extends AppCompatActivity {

    EditText txtNombre, txtPrecio, txtDescripcion;
    Button btnGuarda,fabEditar,fabEliminar;
    ImageView btnvolverListaClase;

    Clases clase;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_clase);

        txtNombre = findViewById(R.id.txtNombre);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnvolverListaClase = findViewById(R.id.btnvolverListaClase);


        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        CrudClase db = new CrudClase(VerClaseActivity.this);
        clase = db.verClase(id);

        if (clase != null){
            txtNombre.setText(clase.getNombre());
            txtPrecio.setText(clase.getPrecio());
            txtDescripcion.setText(clase.getDescripcion());
            btnGuarda.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtPrecio.setInputType(InputType.TYPE_NULL);
            txtDescripcion.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerClaseActivity.this, EditarClaseActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerClaseActivity.this);
                builder.setMessage("¿Desea eliminar esta clase?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if ( db.eliminarClase(id)){
                                    irListaClase();
                                }

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

    }

    private void irListaClase(){
        Intent intent = new Intent(this, ListaClaseActivity.class);
        startActivity(intent);
    }
    public void volverListaClase(View view){
        Intent intent=new Intent(VerClaseActivity.this, ListaClaseActivity.class);
        startActivity(intent);
    }

}
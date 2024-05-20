package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrearClaseActivity extends AppCompatActivity {

    private EditText editNombre, editPrecio,editDescripcion;
    private Button btnAgregar, btnActualizar, btnEliminar, btnConsultar;
    private CrudClase CrudClase;
    private SQLiteDatabase db;


    public void irListaClase(View view){
        Intent intent=new Intent(CrearClaseActivity.this, ListaClaseActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_clase);

        editNombre = findViewById(R.id.editNombre);
        editPrecio = findViewById(R.id.editPrecio);
        editDescripcion = findViewById(R.id.editDescripcion);
        btnAgregar = findViewById(R.id.btnAgregar);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrudClase = new CrudClase(CrearClaseActivity.this);
                long id = CrudClase.insertarClase(editNombre.getText().toString(),editPrecio.getText().toString(),editDescripcion.getText().toString());
                if (id > 0){
                    Toast.makeText(getApplicationContext(),"REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                    irListaClase();
                }else {
                    Toast.makeText(getApplicationContext(),"ERROR AL GUARDAR REGISTRO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void limpiar(){
        editNombre.setText("");
        editPrecio.setText("");
        editDescripcion.setText("");
    }
    private void irListaClase(){
        Intent intent = new Intent(this, ListaClaseActivity.class);
        startActivity(intent);

    }
}
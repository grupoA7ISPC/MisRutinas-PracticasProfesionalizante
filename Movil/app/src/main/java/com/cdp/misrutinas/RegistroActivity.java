package com.cdp.misrutinas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroActivity extends AppCompatActivity {
    private CrudCliente crud;

    private EditText textUserName;
    private EditText textNombre;
    private EditText textApellido;
    private EditText textDNI;
    private EditText textEmail;

    private EditText textPassword;

    private Button btnInsertar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        textUserName = findViewById(R.id.textUserName);
        textNombre = findViewById(R.id.textNombre);
        textApellido = findViewById(R.id.textApellido);
        textDNI = findViewById(R.id.textDNI);
        textEmail = findViewById(R.id.textEmail);
        textPassword = findViewById(R.id.textPassword);
        btnInsertar = findViewById(R.id.btnInsertar);

        // Inicializa Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        crud = new CrudCliente(this);


        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = textUserName.getText().toString();
                final String email = textEmail.getText().toString();
                final String password = textPassword.getText().toString();
                final String nombre = textNombre.getText().toString();
                final String apellido = textApellido.getText().toString();
                final String dni = textDNI.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(RegistroActivity.this, "Campos obligatorios incompletos", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean validUser = crud.isValidUser(username, email, password, nombre, apellido, dni);

                if (validUser) {
                    // Registra al usuario en Firebase Authentication
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegistroActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // El usuario se ha registrado con éxito en Firebase
                                        final FirebaseUser user = mAuth.getCurrentUser();

                                        long id = crud.insertarUsuario(username, email, password, nombre, apellido, dni);

                                        if (id != -1) {
                                            // Registro exitoso en Firebase y en la base de datos local
                                            Toast.makeText(RegistroActivity.this, "¡Registro exitoso!", Toast.LENGTH_SHORT).show();
                                            irLogin();
                                        } else {
                                            // Registro exitoso en Firebase, pero error en la base de datos local
                                            Toast.makeText(RegistroActivity.this, "Registro exitoso en Firebase, pero error al insertar el registro en la base de datos local.", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        // Si el registro falla en Firebase, muestra un mensaje de error
                                        Toast.makeText(RegistroActivity.this, "Error al registrar el usuario en Firebase.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }

    private void irLogin(){
        Intent intent = new Intent(RegistroActivity.this, IniciarSesionActivity.class);
        startActivity(intent);
    }
}

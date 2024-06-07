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

import com.cdp.misrutinas.data.ValidationUserResult;
import com.cdp.misrutinas.data.UserSession;

import com.cdp.misrutinas.entidades.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroActivity extends AppCompatActivity {
    private CrudCliente crud;
    private EditText textNombre, textApellido, textDNI, textEmail, textPassword, textTel;
    private TextView emailError, passwordError, nombreError, apellidoError, dniError, telError;

    private Button btnInsertar;
    private FirebaseAuth mAuth;
    private CrudCliente db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        textNombre = findViewById(R.id.textNombre);
        textApellido = findViewById(R.id.textApellido);
        textDNI = findViewById(R.id.textDNI);
        textEmail = findViewById(R.id.textEmail);
        textPassword = findViewById(R.id.textPassword);
        textTel = findViewById(R.id.textTel);
        btnInsertar = findViewById(R.id.btnInsertar);

        emailError = findViewById(R.id.emailError);
        passwordError = findViewById(R.id.passwordError);
        nombreError = findViewById(R.id.nombreError);
        apellidoError = findViewById(R.id.apellidoError);
        dniError = findViewById(R.id.dniError);
        telError = findViewById(R.id.telError);

        // Inicializa Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        crud = new CrudCliente(this);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = textEmail.getText().toString();
                final String password = textPassword.getText().toString();
                final String nombre = textNombre.getText().toString();
                final String apellido = textApellido.getText().toString();
                final String dni = textDNI.getText().toString();
                final String tel = textTel.getText().toString();

                emailError.setVisibility(View.GONE);
                passwordError.setVisibility(View.GONE);
                nombreError.setVisibility(View.GONE);
                apellidoError.setVisibility(View.GONE);
                dniError.setVisibility(View.GONE);
                telError.setVisibility(View.GONE);

                ValidationUserResult validationResult = crud.isValidUser(email, password, nombre, apellido, dni, tel);

                if(!validationResult.isValid) {
                    if (validationResult.emailError != null) {
                        emailError.setText(validationResult.emailError);
                        emailError.setVisibility(View.VISIBLE);
                    }
                    if (validationResult.passwordError != null) {
                        passwordError.setText(validationResult.passwordError);
                        passwordError.setVisibility(View.VISIBLE);
                    }
                    if (validationResult.nombreError != null) {
                        nombreError.setText(validationResult.nombreError);
                        nombreError.setVisibility(View.VISIBLE);
                    }
                    if (validationResult.apellidoError != null) {
                        apellidoError.setText(validationResult.apellidoError);
                        apellidoError.setVisibility(View.VISIBLE);
                    }
                    if (validationResult.dniError != null) {
                        dniError.setText(validationResult.dniError);
                        dniError.setVisibility(View.VISIBLE);
                    }
                    if (validationResult.telError != null) {
                        telError.setText(validationResult.telError);
                        telError.setVisibility(View.VISIBLE);
                    }
                } else {
                    // Registra al usuario en Firebase Authentication
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegistroActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // El usuario se ha registrado con éxito en Firebase

                                        final FirebaseUser user = mAuth.getCurrentUser();
                                        long id = crud.insertarUsuario(email, password, nombre, apellido, dni, tel);

                                        if (id != -1) {
                                            // Registro exitoso en Firebase y en la base de datos local
                                            Usuario usuario = crud.getUsuariofromDB(email);
                                            UserSession.getInstance().setCurrentUser(usuario);

                                            Toast.makeText(RegistroActivity.this, "¡Registro exitoso! ¡Bienvenido!", Toast.LENGTH_SHORT).show();
                                            irLogin();
                                        } else {
                                            // Registro exitoso en Firebase, pero error en la base de datos local
                                            Toast.makeText(RegistroActivity.this, "Exito en Firebase, error local", Toast.LENGTH_SHORT).show();
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

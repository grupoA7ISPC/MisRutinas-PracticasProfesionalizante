package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cdp.misrutinas.data.UserSession;
import com.cdp.misrutinas.entidades.Usuario;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;




public class IniciarSesionActivity extends AppCompatActivity {
    EditText editTextPassword, editTextCorreo;
    TextView userError;
    Button btnIngresar;
    private CrudCliente db;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        editTextPassword = findViewById(R.id.editTextPassword);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        userError = findViewById(R.id.userError);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                UserSession usuario = UserSession.getInstance();
                if (user != null && usuario != null) {
                    Intent intent = new Intent(IniciarSesionActivity.this, DashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
//
//                } else {
//                    Intent intent = new Intent(IniciarSesionActivity.this, IniciarSesionActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                    finish();
                }
            }
        };
        btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void btnVolverLogin(View view){
        Intent intent=new Intent(IniciarSesionActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void iniciarSesion(){
        String email = editTextCorreo.getText().toString();
        String password = editTextPassword.getText().toString();
        userError.setVisibility(View.GONE);

        db = new CrudCliente(this);
        Usuario usuario = db.getUsuariofromDB(email);

        if(!email.isEmpty() && !password.isEmpty()){
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserSession.getInstance().setCurrentUser(usuario);
                                    showDashboard();
                                } else {
//                                    Toast.makeText(IniciarSesionActivity.this, "Se ha producido un error al autenticar el usuario. Usuario inexistente y/o contraseña incorrecta",
//                                            Toast.LENGTH_SHORT).show();
                                    userError.setText("Error: Correo no registrado y/o contraseña incorrecta");
                                    userError.setVisibility(View.VISIBLE);
                                }
                            }

                        });
        } else {
//            Toast.makeText(IniciarSesionActivity.this, "Hay campos vacíos en el formulario", Toast.LENGTH_SHORT).show();
            userError.setText("Complete todos los campos.");
            userError.setVisibility(View.VISIBLE);
        }
    }

    //Reemplazar:
    private void showDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}
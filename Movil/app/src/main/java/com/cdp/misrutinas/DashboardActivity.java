package com.cdp.misrutinas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import com.cdp.misrutinas.entidades.Usuario;
import com.cdp.misrutinas.data.UserSession;

import com.google.firebase.auth.FirebaseAuth;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {

    TextView textUsername;
    private FirebaseAuth mAuth;

    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth = FirebaseAuth.getInstance();

        MRSQLiteHelper usdbh = new MRSQLiteHelper(this);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        Usuario usuario = UserSession.getInstance().getCurrentUser();

        // Obtener la referencia al TextView
        TextView tvClases = findViewById (R.id.totalClases);
        TextView tvEntrenadores = findViewById (R.id.totalEntrenador);
        TextView tvSocios = findViewById (R.id.totalSocios);
        textUsername = findViewById(R.id.textUsername);


        if (usuario != null) {
            textUsername.setText("@" + usuario.getNombre());
        } else {
            textUsername.setText("de vuelta");
        }


        // Total Clases
        Cursor cc = db.rawQuery ("select * from Clase", null); // Ejecutar la consulta
        int totalClases = cc.getCount (); // Obtener el número de filas
        tvClases.setText (String.valueOf (totalClases)); // Mostrar el número de filas en el TextView


        // Total Entrenadores
        Cursor ce = db.rawQuery ("select * from Usuario WHERE id_rol=3", null);
        int totalEntrenadores = ce.getCount ();
        tvEntrenadores.setText (String.valueOf (totalEntrenadores));

        // Total Socios
        Cursor cs = db.rawQuery ("select * from Usuario WHERE id_rol=2", null);
        int totalSocios = cs.getCount ();
        tvSocios.setText (String.valueOf (totalSocios));

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.home) {
            return true;
        } else if (itemId == R.id.contacto) {
            startActivity(new Intent(getApplicationContext(), ContactoActivity.class));
            return true;
        } else if (itemId == R.id.mas) {
            // Llama al método para mostrar el diálogo modal
            showCustomDialog();
            return true;
        }
        return false;
    }

    private void showCustomDialog() {
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "CustomDialogFragment");
    }

    public void btnSocioList(View view){
        Intent intent=new Intent(DashboardActivity.this, ListaSocioActivity.class);
        startActivity(intent);
    }

    public void btnProfeList(View view){
        Intent intent=new Intent(DashboardActivity.this, ListaProfesorActivity.class);
        startActivity(intent);
    }

    public void btnClaseList(View view){
        Intent intent=new Intent(DashboardActivity.this, ListaClaseActivity.class);
        startActivity(intent);
    }
    public void btnLogout(View view){
        mAuth.signOut();
        UserSession.getInstance().logout();
        Intent intent=new Intent(DashboardActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
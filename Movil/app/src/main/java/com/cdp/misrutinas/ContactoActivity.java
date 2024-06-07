package com.cdp.misrutinas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setSelectedItemId(R.id.contacto);
        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
        Button btnHomeWeb = findViewById(R.id.btnHomeWeb);
        btnHomeWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://github.com/grupoA7ISPC/MisRutinas-PracticasProfesionalizante.git";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }

        });
    }
    private boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.contacto) {
            return true;
        } else if (itemId == R.id.home) {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
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




    }

package com.cdp.misrutinas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.contacto) {
            return true;
        } else if (itemId == R.id.home) {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            return true;
        } else if (itemId == R.id.finanza) {
            startActivity(new Intent(getApplicationContext(), FinanzasActivity.class));
            return true;
        } else if (itemId == R.id.calendario) {
            startActivity(new Intent(getApplicationContext(), CalendarioActivity.class));
            return true;
        }
        return false;
    }
}
package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnMainLogin(View view){
        Intent intent=new Intent(MainActivity.this,IniciarSesionActivity.class);
        startActivity(intent);
    }

    public void btnMainRegister(View view){
        Intent intent=new Intent(MainActivity.this,RegistroActivity.class);
        startActivity(intent);
    }

}
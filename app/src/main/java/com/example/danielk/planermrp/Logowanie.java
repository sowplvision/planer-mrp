package com.example.danielk.planermrp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Logowanie extends AppCompatActivity {
    private EditText user, password;
    private SharedPreferences daneLogowania;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);

        //przypisanie pol do zmiennych
        user = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.haslo);

        //odczytywanie preferencji
        daneLogowania = getSharedPreferences("daneLogowania",Context.MODE_PRIVATE);
        boolean zapisz = daneLogowania.getBoolean("zapisz",true);

        if(zapisz) { //auto wypełnianie pól
            String uLogin = daneLogowania.getString("login", "");
            String uPassword = daneLogowania.getString("haslo", "");

            user.setText(uLogin);
            password.setText(uPassword);
        }
    }

    public void zaloguj(View view){

        //pobranie danych z pol po probie zalogowania
        String uLogin = user.getText().toString();
        String uPassword = password.getText().toString();

        //zapisywanie preferencji
        daneLogowania = getSharedPreferences("daneLogowania", Context.MODE_PRIVATE);
        boolean zapisz = daneLogowania.getBoolean("zapisz",true);

        if(zapisz) { // jeżeli użytkownik chce zapamiętywać swoje dane logowania
            SharedPreferences.Editor editor = daneLogowania.edit();

            editor.putString("login", uLogin);
            editor.putString("haslo", uPassword);

            editor.apply();
        }

        //przeslanie danych do protokolu i proba nawiazania polaczenia
        ObslugaBazyDanych obslugaBazyDanych = new ObslugaBazyDanych(this);
        obslugaBazyDanych.execute("login",uLogin,uPassword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.login_settings:
                Intent intent = new Intent(this, PreferencjeLogowania.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}

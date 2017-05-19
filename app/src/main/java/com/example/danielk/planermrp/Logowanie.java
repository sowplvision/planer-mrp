package com.example.danielk.planermrp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Logowanie extends AppCompatActivity {

    private EditText user;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);

        user = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.haslo);
    }

    protected void zaloguj(View view){
        /*TODO Obsługa przycisku zaloguj:

        * czy użytkownik istnieje
        * czy zapamietac dane w preferencjach jezeli nie zapamietane
        * logowanie do bazy
        Ewentualnie pobieranie z bazy jakis rzeczy ktore sa tylko dla tego uzytkownika
         */

        String uLogin = user.getText().toString();
        String uPassword = password.getText().toString();

        ObslugaBazyDanych obslugaBazyDanych = new ObslugaBazyDanych(this);
        obslugaBazyDanych.execute("login",uLogin,uPassword);
    }
}

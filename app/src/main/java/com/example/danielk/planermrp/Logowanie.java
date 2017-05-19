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

        //przypisanie pol do zmiennych
        user = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.haslo);
    }

    protected void zaloguj(View view){

        //pobranie danych z pol po probie zalogowania
        String uLogin = user.getText().toString();
        String uPassword = password.getText().toString();

        //przeslanie danych do protokolu i proba nawiazania polaczenia
        ObslugaBazyDanych obslugaBazyDanych = new ObslugaBazyDanych(this);
        obslugaBazyDanych.execute("login",uLogin,uPassword);
    }
}

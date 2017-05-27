package com.example.danielk.planermrp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class TworzeniePlanu extends AppCompatActivity {
    private EditText wartoscPopyt1, wartoscPopyt2, wartoscPopyt3, wartoscPopyt4, wartoscPopyt5, wartoscPopyt6, wartoscPopyt7, wartoscPopyt8, wartoscPopyt9, wartoscPopyt10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tworzenie_planu);


        wartoscPopyt1 = (EditText)findViewById(R.id.edit_n_popyt1);
        wartoscPopyt2 = (EditText)findViewById(R.id.edit_n_popyt2);
        wartoscPopyt3 = (EditText)findViewById(R.id.edit_n_popyt3);
        wartoscPopyt4 = (EditText)findViewById(R.id.edit_n_popyt4);
        wartoscPopyt5 = (EditText)findViewById(R.id.edit_n_popyt5);
        wartoscPopyt6 = (EditText)findViewById(R.id.edit_n_popyt6);
        wartoscPopyt7 = (EditText)findViewById(R.id.edit_n_popyt7);
        wartoscPopyt8 = (EditText)findViewById(R.id.edit_n_popyt8);
        wartoscPopyt9 = (EditText)findViewById(R.id.edit_n_popyt9);
        wartoscPopyt10 = (EditText)findViewById(R.id.edit_n_popyt10);

        wartoscPopyt1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wartosc1 = wartoscPopyt1.getText().toString(); //pobiera wartość

            }
        });
        wartoscPopyt2.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wartosc2 = wartoscPopyt2.getText().toString(); //pobiera wartość

            }
        });
        wartoscPopyt3.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wartosc3 = wartoscPopyt3.getText().toString(); //pobiera wartość

            }
        });
        wartoscPopyt4.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wartosc4 = wartoscPopyt4.getText().toString(); //pobiera wartość

            }
        });
        wartoscPopyt5.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wartosc5 = wartoscPopyt5.getText().toString(); //pobiera wartość

            }
        });
        wartoscPopyt6.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wartosc6 = wartoscPopyt6.getText().toString(); //pobiera wartość

            }
        });
        wartoscPopyt7.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wartosc7 = wartoscPopyt7.getText().toString(); //pobiera wartość

            }
        });
        wartoscPopyt8.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wartosc8 = wartoscPopyt8.getText().toString(); //pobiera wartość

            }
        });
        wartoscPopyt9.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wartosc9 = wartoscPopyt9.getText().toString(); //pobiera wartość

            }
        });
        wartoscPopyt10.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wartosc10 = wartoscPopyt10.getText().toString(); //pobiera wartość

            }
        });





    }



    //Menu ustawien
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.login_settings:
                Intent intent = new Intent(this, Preferencje.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}

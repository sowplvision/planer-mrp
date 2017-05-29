package com.example.danielk.planermrp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 *
 * Klasa bedąca menu głownym aplikacji gdzie użytkownik decyduje co chciałby zrobić w trakcie jej działania.
 * Użytkownik po zalogowaniu trafia wlasnie tutaj gdzie podejmuje akcje, zmienia sie takze wyglad Ustawień.
 * Wykorzystywany jest drugi schemat Preferencji.
 *
 **/

public class PanelGlowny extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_glowny);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //stworzenie menu
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //wybranie opcji z menu
        switch (item.getItemId()){
            case R.id.login_settings:
                Intent intent = new Intent(this, Preferencje.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    public void stworzPlan(View view){
        //obsluga przycisku i nowa aktywnosc
        Intent intent = new Intent(this,TworzeniePlanu.class);
        startActivity(intent);
    }

    public void wpisDoBazy(View view){
        //obsluga przycisku i nowa aktywnosc
        Intent intent = new Intent(this,WpisDoBazy.class);
        startActivity(intent);
    }

    public void informacje(View view){
        //obsluga przycisku i nowa aktywnosc
        Intent intent = new Intent(this, Informacje.class);
        startActivity(intent);
    }
    public void toastOpis(View view) {
        //podpowiedzi do przycisków
        Toast.makeText(this, "Stwórz Plan - zaprojektuj nowy plan produkcji wybranego produktu\nDodaj Przedmiot - dodaj nowy materiał, półprodukt, produkt do bazy danych", Toast.LENGTH_LONG).show();
    }
}

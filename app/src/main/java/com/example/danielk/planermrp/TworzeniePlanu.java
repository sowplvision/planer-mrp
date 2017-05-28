package com.example.danielk.planermrp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 * Klasa ktorej głownym przeznaczeniem jest tworzenie nowego planu produkcji na podstawie pobieranych
 *
 */

import java.util.concurrent.ExecutionException;

public class TworzeniePlanu extends AppCompatActivity{
    private Integer naStanie;
    private EditText[] wartosciPopytu, wartosciProdukcji;
    private TextView[] wartosciDostepne;
    private String wartosci[] = new String[10];
    private String wprodukcji[] = new String[10];
    private String wdostepne[] = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tworzenie_planu);

        //tablica pol
        wartosciPopytu = new EditText[10];
        wartosciProdukcji = new EditText[10];
        wartosciDostepne = new TextView[10];

        //powiazanie pol z elementami layoutu
        wartosciPopytu[0] = (EditText)findViewById(R.id.edit_n_popyt1);
        wartosciPopytu[1] = (EditText)findViewById(R.id.edit_n_popyt2);
        wartosciPopytu[2] = (EditText)findViewById(R.id.edit_n_popyt3);
        wartosciPopytu[3] = (EditText)findViewById(R.id.edit_n_popyt4);
        wartosciPopytu[4] = (EditText)findViewById(R.id.edit_n_popyt5);
        wartosciPopytu[5] = (EditText)findViewById(R.id.edit_n_popyt6);
        wartosciPopytu[6] = (EditText)findViewById(R.id.edit_n_popyt7);
        wartosciPopytu[7] = (EditText)findViewById(R.id.edit_n_popyt8);
        wartosciPopytu[8] = (EditText)findViewById(R.id.edit_n_popyt9);
        wartosciPopytu[9] = (EditText)findViewById(R.id.edit_n_popyt10);

        wartosciProdukcji[0] = (EditText)findViewById(R.id.edit_n_produkcje1);
        wartosciProdukcji[1] = (EditText)findViewById(R.id.edit_n_produkcje2);
        wartosciProdukcji[2] = (EditText)findViewById(R.id.edit_n_produkcje3);
        wartosciProdukcji[3] = (EditText)findViewById(R.id.edit_n_produkcje4);
        wartosciProdukcji[4] = (EditText)findViewById(R.id.edit_n_produkcje5);
        wartosciProdukcji[5] = (EditText)findViewById(R.id.edit_n_produkcje6);
        wartosciProdukcji[6] = (EditText)findViewById(R.id.edit_n_produkcje7);
        wartosciProdukcji[7] = (EditText)findViewById(R.id.edit_n_produkcje8);
        wartosciProdukcji[8] = (EditText)findViewById(R.id.edit_n_produkcje9);
        wartosciProdukcji[9] = (EditText)findViewById(R.id.edit_n_produkcje10);

        wartosciDostepne[0] = (TextView)findViewById(R.id.edit_n_dostepne1);
        wartosciDostepne[1] = (TextView)findViewById(R.id.edit_n_dostepne2);
        wartosciDostepne[2] = (TextView)findViewById(R.id.edit_n_dostepne3);
        wartosciDostepne[3] = (TextView)findViewById(R.id.edit_n_dostepne4);
        wartosciDostepne[4] = (TextView)findViewById(R.id.edit_n_dostepne5);
        wartosciDostepne[5] = (TextView)findViewById(R.id.edit_n_dostepne6);
        wartosciDostepne[6] = (TextView)findViewById(R.id.edit_n_dostepne7);
        wartosciDostepne[7] = (TextView)findViewById(R.id.edit_n_dostepne8);
        wartosciDostepne[8] = (TextView)findViewById(R.id.edit_n_dostepne9);
        wartosciDostepne[9] = (TextView)findViewById(R.id.edit_n_dostepne10);

        //pobranie wstepnych danych o ilosci znajdujacej sie na stanie
        ObslugaBazyDanych daniel = new ObslugaBazyDanych(this);

        try {
            naStanie = Integer.parseInt(daniel.execute("plan").get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(int i=0; i<10; i++) {
            //ustawienie wstepnych wartosci dostepnych produktow
            wartosciDostepne[i].setText(naStanie.toString());

            //podlaczenie nasluchiwaczy do kazdego z pol tekstowych
            wartosciProdukcji[i].addTextChangedListener(new TextChangeListener(i));
            wartosciPopytu[i].addTextChangedListener(new TextChangeListener(i));
        }
    }

    public void stworzPlan(View view) {
        //obsluga przycisku
        for(int i=0; i<10; i++){
            //pobranie wartosci z pol do odpowiedniej tablicy wartosci
            wartosci[i] = wartosciPopytu[i].getText().toString();
            wprodukcji[i] = wartosciProdukcji[i].getText().toString();
            wdostepne[i] = wartosciDostepne[i].getText().toString();
        }
    }

    //Menu ustawien
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    //zachowanie menu przy wybraniu jakiejs opcji
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

    //klasa nasluchujaca zmiany tekstu w polach
    private class TextChangeListener implements TextWatcher {
        private Integer produkcja, popyt, dostepne;
        private int position;

        //konstruktor domyslny
        public TextChangeListener(int position) {
            this.popyt = 0;
            this.produkcja = 0;
            this.position = position;
            this.dostepne = Integer.parseInt(wartosciDostepne[0].getText().toString());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            //jezeli pozycja jest wieksza od 0(np dzien 2) ilosc dostepna pobierana jest z dnia poprzedniego
            if(position>0){
                dostepne = Integer.parseInt(wartosciDostepne[position-1].getText().toString());
            }

            //
            for(int i = position; i<10; i++){
                //najpierw ustawiana jest wartosc dostepnego produktu w polu
                wartosciDostepne[i].setText(dostepne.toString());

                if (!wartosciProdukcji[i].getText().toString().equals("")){
                    try { //jezeli pole nie jest puste pobierz wartosc z pola
                        produkcja = Integer.parseInt(wartosciProdukcji[i].getText().toString());
                    }
                    catch (NumberFormatException e){
                        produkcja = 0;
                    }
                } else { //jezeli jest puste wartosc domyslna produkcji wynosi 0
                    produkcja = 0;
                }
                if (!wartosciPopytu[i].getText().toString().equals("")) {
                    try { //jezeli pole nie jest poste pobierz wartosc z pola
                        popyt = Integer.parseInt(wartosciPopytu[i].getText().toString());
                    }
                    catch (NumberFormatException e){
                        popyt = 0;
                    }
                } else { //jezeli pole jest puste domyslna wartosc popytu wynosi 0
                    popyt = 0;
                }

                //algorytm obliczania ilosci dostepnego produktu w wybranym dniu
                dostepne = dostepne + produkcja - popyt;
                //ustawianie wartosci wyliczonej do pola
                wartosciDostepne[i].setText(dostepne.toString());
            }
        }
    }
}

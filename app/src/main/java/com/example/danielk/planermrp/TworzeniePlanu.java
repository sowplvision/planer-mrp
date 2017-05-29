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
    private String cena, nazwaProduktu, czasMontazu, wielkoscPartii;

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

        //pobierz cene produktu
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObslugaBazyDanych obslugaBazyDanych = new ObslugaBazyDanych(getApplicationContext());

                try {
                    cena = obslugaBazyDanych.execute("cena").get();
                    cena = cena.replace(",",".");
                    cena = cena.substring(2); //postgresql przechowuje kwote razem z waluta (zł 0,00)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).run();

        //pobierz nazwe produktu
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObslugaBazyDanych obslugaBazyDanych = new ObslugaBazyDanych(getApplicationContext());
                try {
                    nazwaProduktu = obslugaBazyDanych.execute("nazwa_produktu").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).run();

        //pobierz czas montazu produktu
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObslugaBazyDanych obslugaBazyDanych = new ObslugaBazyDanych(getApplicationContext());
                try {
                    czasMontazu = obslugaBazyDanych.execute("czas_montazu_produktu").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).run();

        //pobierz wielkosc partii produktu
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObslugaBazyDanych obslugaBazyDanych = new ObslugaBazyDanych(getApplicationContext());
                try {
                    wielkoscPartii = obslugaBazyDanych.execute("wielkosc_partii_produktu").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).run();

        //symbole potrzebne do zapisu w csv (nowy wiersz,kolumna,tabelka, offsetWiersza, offsetKolumny)
        String nowyWiersz = "\n";
        String nowaKolumna = ",";
        String nowaTabela = "#";
        char kolumna;

        //tworzenie tekstowo tabeli GHP dla 10 dni dla produktu (obecnie jedynie dla szafy)
        String tresc = "GHP" + nowaKolumna + nazwaProduktu + nowaKolumna;
        tresc += nowaKolumna +"Cena za sztuke:" + nowaKolumna + cena + nowyWiersz;
        tresc += "Dzien:" + nowaKolumna;
        for(int i = 0; i<10; i++){
            tresc+= (i+1) + nowaKolumna;
        }
        tresc += nowyWiersz + "Przewidywany popyt:" + nowaKolumna;
        for(int i = 0; i<10; i++){
            tresc+= wartosci[i] + nowaKolumna;
        }
        tresc += nowyWiersz + "Produkcja:" + nowaKolumna;
        for(int i = 0; i<10; i++){
            tresc+= wprodukcji[i] + nowaKolumna;
        }
        tresc += nowyWiersz + "Dostepne:" + nowaKolumna;
        for(int i = 0; i<10; i++) {
            tresc += wdostepne[i] + nowaKolumna;
        }
        tresc += nowyWiersz + nowyWiersz + nowaKolumna +"Podsumowanie" + nowaKolumna + "Wartosc: [zl]";
        tresc += nowyWiersz + "Popyt:" + nowaKolumna + "=SUM(B3:K3)";
        tresc += nowaKolumna + "=E1*B8";
        tresc += nowyWiersz+ "Produkcja:" + nowaKolumna + "=SUM(B4:K4)";
        tresc += nowaKolumna + "=E1*B9";
        tresc += nowyWiersz + "Dostepne" + nowaKolumna + "=K5";
        tresc += nowaKolumna + "=E1*B10" + nowyWiersz;

        //utworz tabele MRP poziom 0 (Algorytm MRP dla jednej tabeli)
        tresc += nowaTabela + "MRP" + nowaKolumna + nazwaProduktu + "(Poziom 0)";
        tresc += nowyWiersz + "Dzien:" + nowaKolumna;
        for(int i = 0; i<10; i++){
            tresc += (i+1) + nowaKolumna;
        }
        tresc += nowyWiersz + "Calkowite zapotrzebowanie:" + nowaKolumna;
        kolumna = 'B';
        for(int i = 0; i<10; i++){
            //=OFFSET(B4;0;3;1;1)
            tresc += "=OFFSET(" + kolumna + "4;0;" + czasMontazu + ";1;1)" + nowaKolumna;
            kolumna = (char) (kolumna + 1);
        }
        tresc += nowyWiersz + "Planowane przyjecia:" + nowaKolumna;
        tresc += nowyWiersz + "Przewidywane na stanie:" + nowaKolumna;
        tresc += "=" + naStanie + "+B16+B19-B15" + nowaKolumna;
        kolumna = 'C';
        for (int i=1; i<10; i++){
            char kolumnaDodatkowa = (char)(kolumna-1);
            //=B17+C16+C19-C15
            tresc += "=" + kolumnaDodatkowa + "17+" + kolumna + "16+" + kolumna + "19-" + kolumna + "15" + nowaKolumna;
            kolumna = (char) (kolumna+1);
        }
        tresc += nowyWiersz + "Zapotrzebowanie netto:" + nowaKolumna;
        tresc += "=IF("+naStanie+"<B15;B15-" + naStanie + ";)" + nowaKolumna;
        kolumna = 'C';
        for (int i=1; i<10; i++){
            char kolumnaDodatkowa = (char)(kolumna-1);
            //=IF(B17<C15;C15-B17;)
            tresc += "=IF(" + kolumnaDodatkowa + "17<" + kolumna + "15;" + kolumna + "15-" + kolumnaDodatkowa + "17;)";
            tresc += nowaKolumna;
        }
        tresc += nowyWiersz + "Planowane zamowienia:" + nowaKolumna;
        kolumna = 'B';
        for(int i=0; i<10; i++){
            //=OFFSET(B20;0;3;1;1)
            tresc += "=OFFSET(" + kolumna + "20;0;" + czasMontazu + ";1;1)" + nowaKolumna;
        }
        tresc += nowyWiersz + "Planowane przyjecia zamowien:" + nowaKolumna;
        kolumna = 'B';
        for(int i=0; i<10; i++){
            //=IF(B18<>"";IF(3>=1;"";ROUNDUP(B18/10)*10);"")
            tresc += "=IF(" + kolumna + "18<>\"\";IF(" + naStanie + ">=" + (i+1) + ";\"\";ROUNDUP(";
            tresc += kolumna + "18/" + wielkoscPartii + ")*" + wielkoscPartii + ");\"\")";
            tresc += nowaKolumna;
        }

        //zapisywanie utworzonej tresci do pliku w pamieci wewnetrznej/PlanerMRP/plan.csv
        ZapisywaniePlanu zapisywaniePlanu = new ZapisywaniePlanu(tresc,this);
        zapisywaniePlanu.zapisz();
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

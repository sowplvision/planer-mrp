package com.example.danielk.planermrp;

import android.content.Context;
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

        wartosciPopytu = new EditText[10];
        wartosciProdukcji = new EditText[10];
        wartosciDostepne = new TextView[10];

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

        ObslugaBazyDanych daniel = new ObslugaBazyDanych(this);

        try {
            naStanie = Integer.parseInt(daniel.execute("plan").get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for(int i=0; i<10; i++) {
            wartosciDostepne[i].setText(naStanie.toString());
            wartosciProdukcji[i].addTextChangedListener(new TextChangeListener(i));
            wartosciPopytu[i].addTextChangedListener(new TextChangeListener(i));
        }
    }

    public void stworzPlan(View view) {
        wartosci[0] = wartosciPopytu[0].getText().toString(); //pobiera wartość popytu...
        wartosci[1] = wartosciPopytu[1].getText().toString();
        wartosci[2] = wartosciPopytu[2].getText().toString();
        wartosci[3] = wartosciPopytu[3].getText().toString();
        wartosci[4] = wartosciPopytu[4].getText().toString();
        wartosci[5] = wartosciPopytu[5].getText().toString();
        wartosci[6] = wartosciPopytu[6].getText().toString();
        wartosci[7] = wartosciPopytu[7].getText().toString();
        wartosci[8] = wartosciPopytu[8].getText().toString();
        wartosci[9] = wartosciPopytu[9].getText().toString(); // .

        wprodukcji[0] = wartosciProdukcji[0].getText().toString(); // pobiera wartość produkcji...
        wprodukcji[1] = wartosciProdukcji[1].getText().toString();
        wprodukcji[2] = wartosciProdukcji[2].getText().toString();
        wprodukcji[3] = wartosciProdukcji[3].getText().toString();
        wprodukcji[4] = wartosciProdukcji[4].getText().toString();
        wprodukcji[5] = wartosciProdukcji[5].getText().toString();
        wprodukcji[6] = wartosciProdukcji[6].getText().toString();
        wprodukcji[7] = wartosciProdukcji[7].getText().toString();
        wprodukcji[8] = wartosciProdukcji[8].getText().toString();
        wprodukcji[9] = wartosciProdukcji[9].getText().toString(); // .

        wdostepne[0] = wartosciDostepne[0].getText().toString(); // pobiera wartosc dostepnego towaru...
        wdostepne[1] = wartosciDostepne[1].getText().toString();
        wdostepne[2] = wartosciDostepne[2].getText().toString();
        wdostepne[3] = wartosciDostepne[3].getText().toString();
        wdostepne[4] = wartosciDostepne[4].getText().toString();
        wdostepne[5] = wartosciDostepne[5].getText().toString();
        wdostepne[6] = wartosciDostepne[6].getText().toString();
        wdostepne[7] = wartosciDostepne[7].getText().toString();
        wdostepne[8] = wartosciDostepne[8].getText().toString();
        wdostepne[9] = wartosciDostepne[9].getText().toString();
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

    private class TextChangeListener implements TextWatcher {
        private Integer produkcja, popyt, dostepne, temp;
        private int position;

        public TextChangeListener(int position) {
            this.popyt = 0;
            this.produkcja = 0;
            this.position = position;
            this.temp = Integer.parseInt(wartosciDostepne[position].getText().toString());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!wartosciProdukcji[position].getText().toString().equals("")){
                try {
                    produkcja = Integer.parseInt(wartosciProdukcji[position].getText().toString());
                }
                catch (NumberFormatException e){
                    produkcja = 0;
                }
            } else {
                produkcja = 0;
            }
            if (!wartosciPopytu[position].getText().toString().equals("")) {
                try {
                    popyt = Integer.parseInt(wartosciPopytu[position].getText().toString());
                }
                catch (NumberFormatException e){
                    popyt = 0;
                }
            } else {
                popyt = 0;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            dostepne = temp;
            if(wartosciPopytu[position].getText().toString().equals("") && wartosciProdukcji[position].getText().toString().equals("")){
                dostepne = temp;
            }
            else{
                dostepne = dostepne + produkcja - popyt;
            }
            for(int i = position; i<10; i++){
                wartosciDostepne[i].setText(dostepne.toString());
            }
        }
    }
}

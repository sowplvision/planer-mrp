package com.example.danielk.planermrp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TworzeniePlanu extends AppCompatActivity {
    private EditText wartoscPopyt1, wartoscPopyt2, wartoscPopyt3, wartoscPopyt4, wartoscPopyt5, wartoscPopyt6, wartoscPopyt7, wartoscPopyt8, wartoscPopyt9, wartoscPopyt10;
    private EditText wartoscProdukcji1, wartoscProdukcji2, wartoscProdukcji3, wartoscProdukcji4, wartoscProdukcji5, wartoscProdukcji6, wartoscProdukcji7, wartoscProdukcji8, wartoscProdukcji9, wartoscProdukcji10;
    private TextView wartoscDostepne1, wartoscDostepne2, wartoscDostepne3, wartoscDostepne4, wartoscDostepne5, wartoscDostepne6, wartoscDostepne7, wartoscDostepne8, wartoscDostepne9, wartoscDostepne10;
    private Integer naStanie;
    String wartosci[] = new String[10];
    String wprodukcji[] = new String[10];
    String wdostepne[] = new String[10];

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

        wartoscProdukcji1 = (EditText)findViewById(R.id.edit_n_produkcje1);
        wartoscProdukcji2 = (EditText)findViewById(R.id.edit_n_produkcje2);
        wartoscProdukcji3 = (EditText)findViewById(R.id.edit_n_produkcje3);
        wartoscProdukcji4 = (EditText)findViewById(R.id.edit_n_produkcje4);
        wartoscProdukcji5 = (EditText)findViewById(R.id.edit_n_produkcje5);
        wartoscProdukcji6 = (EditText)findViewById(R.id.edit_n_produkcje6);
        wartoscProdukcji7 = (EditText)findViewById(R.id.edit_n_produkcje7);
        wartoscProdukcji8 = (EditText)findViewById(R.id.edit_n_produkcje8);
        wartoscProdukcji9 = (EditText)findViewById(R.id.edit_n_produkcje9);
        wartoscProdukcji10 = (EditText)findViewById(R.id.edit_n_produkcje10);

        wartoscDostepne1 = (TextView)findViewById(R.id.edit_n_dostepne1);
        wartoscDostepne2 = (TextView)findViewById(R.id.edit_n_dostepne2);
        wartoscDostepne3 = (TextView)findViewById(R.id.edit_n_dostepne3);
        wartoscDostepne4 = (TextView)findViewById(R.id.edit_n_dostepne4);
        wartoscDostepne5 = (TextView)findViewById(R.id.edit_n_dostepne5);
        wartoscDostepne6 = (TextView)findViewById(R.id.edit_n_dostepne6);
        wartoscDostepne7 = (TextView)findViewById(R.id.edit_n_dostepne7);
        wartoscDostepne8 = (TextView)findViewById(R.id.edit_n_dostepne8);
        wartoscDostepne9 = (TextView)findViewById(R.id.edit_n_dostepne9);
        wartoscDostepne10 = (TextView)findViewById(R.id.edit_n_dostepne10);

        ObslugaBazyDanych daniel = new ObslugaBazyDanych(this);

        try {
            naStanie = Integer.parseInt(daniel.execute("plan").get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



    public void stworzPlan(View view) {
        wartosci[0] = wartoscPopyt1.getText().toString(); //pobiera wartość popytu...
        wartosci[1] = wartoscPopyt2.getText().toString();
        wartosci[2] = wartoscPopyt3.getText().toString();
        wartosci[3] = wartoscPopyt4.getText().toString();
        wartosci[4] = wartoscPopyt5.getText().toString();
        wartosci[5] = wartoscPopyt6.getText().toString();
        wartosci[6] = wartoscPopyt7.getText().toString();
        wartosci[7] = wartoscPopyt8.getText().toString();
        wartosci[8] = wartoscPopyt9.getText().toString();
        wartosci[9] = wartoscPopyt10.getText().toString(); // .

        wprodukcji[0] = wartoscProdukcji1.getText().toString(); // pobiera wartość produkcji...
        wprodukcji[1] = wartoscProdukcji2.getText().toString();
        wprodukcji[2] = wartoscProdukcji3.getText().toString();
        wprodukcji[3] = wartoscProdukcji4.getText().toString();
        wprodukcji[4] = wartoscProdukcji5.getText().toString();
        wprodukcji[5] = wartoscProdukcji6.getText().toString();
        wprodukcji[6] = wartoscProdukcji7.getText().toString();
        wprodukcji[7] = wartoscProdukcji8.getText().toString();
        wprodukcji[8] = wartoscProdukcji9.getText().toString();
        wprodukcji[9] = wartoscProdukcji10.getText().toString(); // .

        wdostepne[0] = wartoscDostepne1.getText().toString(); // pobiera wartosc dostepnego towaru...
        wdostepne[1] = wartoscDostepne2.getText().toString();
        wdostepne[2] = wartoscDostepne3.getText().toString();
        wdostepne[3] = wartoscDostepne4.getText().toString();
        wdostepne[4] = wartoscDostepne5.getText().toString();
        wdostepne[5] = wartoscDostepne6.getText().toString();
        wdostepne[6] = wartoscDostepne7.getText().toString();
        wdostepne[7] = wartoscDostepne8.getText().toString();
        wdostepne[8] = wartoscDostepne9.getText().toString();
        wdostepne[9] = wartoscDostepne10.getText().toString();
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

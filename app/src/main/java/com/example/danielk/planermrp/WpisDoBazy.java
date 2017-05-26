package com.example.danielk.planermrp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class WpisDoBazy extends AppCompatActivity implements OnItemSelectedListener {
    private LinearLayout material, polprodukt, produkt;
    private EditText nazwa, opis, czas, ilosc, partia, cena;
    private Button wyslij;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wpis_do_bazy);

        Spinner typProduktu = (Spinner) findViewById(R.id.typProduktu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.typ_produktu, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typProduktu.setAdapter(adapter);
        typProduktu.setOnItemSelectedListener(this);

        material = (LinearLayout) findViewById(R.id.material);
        polprodukt = (LinearLayout) findViewById(R.id.polprodukt);
        produkt = (LinearLayout) findViewById(R.id.produkt);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String typProduktu = parent.getItemAtPosition(position).toString();

        if(typProduktu.equals("Materiał")){
            material.setVisibility(View.VISIBLE);
            polprodukt.setVisibility(View.GONE);
            produkt.setVisibility(View.GONE);

            nazwa = (EditText) findViewById(R.id.nazwaMaterialu);
            opis = (EditText) findViewById(R.id.opisMaterialu);
            czas = (EditText) findViewById(R.id.czas);
            ilosc = (EditText) findViewById(R.id.ilosc);
            partia = (EditText) findViewById(R.id.partia);
            cena = (EditText) findViewById(R.id.cena);
            wyslij = (Button) findViewById(R.id.wyslijMaterial);

            nazwa.setHint("Nazwa materiału");
        }
        if(typProduktu.equals("Półprodukt")){
            material.setVisibility(View.GONE);
            polprodukt.setVisibility(View.VISIBLE);
            produkt.setVisibility(View.GONE);

            nazwa = (EditText) findViewById(R.id.nazwaPolproduktu);
            opis = (EditText) findViewById(R.id.opisPolproduktu);
            wyslij = (Button) findViewById(R.id.wyslijPolprodukt);

            nazwa.setHint("Nazwa półproduktu");
        }
        if(typProduktu.equals("Produkt")){
            material.setVisibility(View.GONE);
            polprodukt.setVisibility(View.GONE);
            produkt.setVisibility(View.VISIBLE);

            nazwa = (EditText) findViewById(R.id.nazwaProduktu);
            wyslij = (Button) findViewById(R.id.wyslijProdukt);

            nazwa.setHint("Nazwa produktu");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
                Intent intent = new Intent(this, Preferencje.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    public void wyslij(View view) {
    }
}

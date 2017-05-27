package com.example.danielk.planermrp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class WpisDoBazy extends AppCompatActivity implements OnItemSelectedListener {
    private LinearLayout material, polprodukt, produkt;
    private EditText nazwa, opis, czas, ilosc, partia, cena;
    private String name, details, time, quantity, partion, price;
    private String[] materialy, polprodukty;
    private ObslugaBazyDanych obslugaBazyDanych;

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

        obslugaBazyDanych = new ObslugaBazyDanych(this);

        try {
            String temp = obslugaBazyDanych.execute("materials").get();
            materialy = temp.split("; ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            String temp = obslugaBazyDanych.execute("halfproducts").get();
            polprodukty = temp.split("; ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ActionBar.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
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
            czas = (EditText) findViewById(R.id.czasMaterialu);
            ilosc = (EditText) findViewById(R.id.iloscMaterialu);
            partia = (EditText) findViewById(R.id.partiaMaterialu);
            cena = (EditText) findViewById(R.id.cenaMaterialu);

            nazwa.setHint("Nazwa materiału");
        }
        if(typProduktu.equals("Półprodukt")){
            material.setVisibility(View.GONE);
            polprodukt.setVisibility(View.VISIBLE);
            produkt.setVisibility(View.GONE);

            nazwa = (EditText) findViewById(R.id.nazwaPolproduktu);
            opis = (EditText) findViewById(R.id.opisPolproduktu);
            czas = (EditText) findViewById(R.id.czasPolproduktu);
            ilosc = (EditText) findViewById(R.id.iloscPolproduktu);
            partia = (EditText) findViewById(R.id.partiaPolproduktu);

            nazwa.setHint("Nazwa półproduktu");

            try {
                String temp = obslugaBazyDanych.execute("materials").get();
                materialy = temp.split("; ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            ListView listView = (ListView) findViewById(R.id.materialy);

            Adapter materialyAdapter = new Adapter(materialy);
            listView.setAdapter(materialyAdapter);
            setListViewHeightBasedOnChildren(listView);
        }
        if(typProduktu.equals("Produkt")){
            material.setVisibility(View.GONE);
            polprodukt.setVisibility(View.GONE);
            produkt.setVisibility(View.VISIBLE);

            nazwa = (EditText) findViewById(R.id.nazwaProduktu);
            opis = (EditText) findViewById(R.id.opisProduktu);
            czas = (EditText) findViewById(R.id.czasProduktu);
            ilosc = (EditText) findViewById(R.id.iloscProduktu);
            partia = (EditText) findViewById(R.id.partiaProduktu);

            nazwa.setHint("Nazwa produktu");

            try {
                String temp = obslugaBazyDanych.execute("halfproducts").get();
                polprodukty = temp.split("; ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
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

    public void wyslijMaterial(View view) {
        name = nazwa.getText().toString();
        details = opis.getText().toString();
        time = czas.getText().toString();
        quantity = ilosc.getText().toString();
        partion = partia.getText().toString();
        price = cena.getText().toString();

        price = price.replace('.',',');

        obslugaBazyDanych.execute("insert_material",name,details,time,quantity,partion,price);
    }

    public void wyslijPolprodukt(View view) {

    }

    public void wyslijProdukt(View view) {

    }

    public class Adapter extends BaseAdapter{
        private String[] items;
        public Adapter(String[] items){
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.polprodukty_item,null);

            CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.materialNazwa);

            checkBox.setText(items[position]);

            return convertView;
        }
    }
}

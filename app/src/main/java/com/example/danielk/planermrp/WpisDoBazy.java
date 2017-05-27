package com.example.danielk.planermrp;

import android.content.Intent;
import android.os.AsyncTask;
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

import java.util.concurrent.ExecutionException;

public class WpisDoBazy extends AppCompatActivity implements OnItemSelectedListener {
    private LinearLayout material, polprodukt, produkt;
    private EditText nazwa, opis, czas, ilosc, partia, cena;
    private String name, details, time, quantity, partion, price;
    private String[] materialy, polprodukty;
    private ListView materialyListView, polproduktyListView;
    private Adapter materialyAdapter, polproduktyAdapter;
    private ObslugaBazyDanych insertMaterial, insertPolprodukt, insertProdukt, materials, halfproducts;

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

        insertMaterial = new ObslugaBazyDanych(this);
        insertPolprodukt = new ObslugaBazyDanych(this);
        insertProdukt = new ObslugaBazyDanych(this);
        materials = new ObslugaBazyDanych(this);
        halfproducts = new ObslugaBazyDanych(this);

        try {
            String temp = materials.execute("materials").get();
            materialy = temp.split("; ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            String temp = halfproducts.execute("halfproducts").get();
            polprodukty = temp.split("; ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        materialyListView = (ListView) findViewById(R.id.materialyList);
        polproduktyListView = (ListView) findViewById(R.id.polproduktyList);
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

        if (typProduktu.equals("Materiał")) {
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
        if (typProduktu.equals("Półprodukt")) {
            material.setVisibility(View.GONE);
            polprodukt.setVisibility(View.VISIBLE);
            produkt.setVisibility(View.GONE);

            nazwa = (EditText) findViewById(R.id.nazwaPolproduktu);
            opis = (EditText) findViewById(R.id.opisPolproduktu);
            czas = (EditText) findViewById(R.id.czasPolproduktu);
            ilosc = (EditText) findViewById(R.id.iloscPolproduktu);
            partia = (EditText) findViewById(R.id.partiaPolproduktu);

            nazwa.setHint("Nazwa półproduktu");

            materialyAdapter = new Adapter(materialy);
            materialyListView.setAdapter(materialyAdapter);
            setListViewHeightBasedOnChildren(materialyListView);
        }
        if (typProduktu.equals("Produkt")) {
            material.setVisibility(View.GONE);
            polprodukt.setVisibility(View.GONE);
            produkt.setVisibility(View.VISIBLE);

            nazwa = (EditText) findViewById(R.id.nazwaProduktu);
            opis = (EditText) findViewById(R.id.opisProduktu);
            czas = (EditText) findViewById(R.id.czasProduktu);
            ilosc = (EditText) findViewById(R.id.iloscProduktu);
            partia = (EditText) findViewById(R.id.partiaProduktu);

            nazwa.setHint("Nazwa produktu");

            polproduktyAdapter = new Adapter(polprodukty);
            polproduktyListView.setAdapter(polproduktyAdapter);
            setListViewHeightBasedOnChildren(polproduktyListView);
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
        switch (item.getItemId()) {
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

        price = price.replace('.', ',');

        insertMaterial.execute("insert_material", name, details, time, quantity, partion, price);
    }

    public void wyslijPolprodukt(View view) {
        name = nazwa.getText().toString();
        details = opis.getText().toString();
        time = czas.getText().toString();
        quantity = ilosc.getText().toString();
        partion = partia.getText().toString();

        insertPolprodukt.execute("insert_polprodukt", name, details, time, quantity, partion, price);
    }

    public void wyslijProdukt(View view) {
        name = nazwa.getText().toString();
        details = opis.getText().toString();
        time = czas.getText().toString();
        quantity = ilosc.getText().toString();
        partion = partia.getText().toString();

        insertProdukt.execute("insert_produkt", name, details, time, quantity, partion, price);
    }

    public class Adapter extends BaseAdapter {
        private String[] items;

        public Adapter(String[] items) {
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
            convertView = getLayoutInflater().inflate(R.layout.list_checkbox_item, null);

            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.materialNazwa);

            checkBox.setText(items[position]);

            return convertView;
        }
    }
}

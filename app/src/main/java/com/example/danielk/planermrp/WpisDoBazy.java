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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.concurrent.ExecutionException;

/**
 *
 * Klasa ta odpowiada za dodawanie nowych materialow, produktow, polproduktow do bazy danych
 *
 **/

public class WpisDoBazy extends AppCompatActivity implements OnItemSelectedListener {
    private LinearLayout material, polprodukt, produkt;
    private EditText nazwa, opis, czas, ilosc, partia, cena;
    private String name, details, time, quantity, partion, price;
    private String[] materialy, polprodukty;
    private ListView materialyListView, polproduktyListView;
    private Adapter materialyAdapter, polproduktyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wpis_do_bazy);

        //stworzenie listy rozwijanej z typami produktow
        Spinner typProduktu = (Spinner) findViewById(R.id.typProduktu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.typ_produktu, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typProduktu.setAdapter(adapter);
        typProduktu.setOnItemSelectedListener(this);

        //podpiecie linearlayout'ow do zmiennych
        material = (LinearLayout) findViewById(R.id.material);
        polprodukt = (LinearLayout) findViewById(R.id.polprodukt);
        produkt = (LinearLayout) findViewById(R.id.produkt);

        //utworzenie widoku listy w ktorym zostana umieszczone checkboxy
        materialyListView = (ListView) findViewById(R.id.materialyList);
        polproduktyListView = (ListView) findViewById(R.id.polproduktyList);

        //pobieranie obecnej listy materialowi i zapisanie jej do tablicy - wykonywane przez osobny watek
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObslugaBazyDanych materials = new ObslugaBazyDanych(getApplicationContext());
                try {
                    String temp = materials.execute("materials").get();
                    materialy = temp.split("; ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).run();

        //pobieranie obecnej listy polproduktow i zapisanie jej do listy - wykonywane przez inny watek
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObslugaBazyDanych halfproducts = new ObslugaBazyDanych(getApplicationContext());
                try {
                    String temp = halfproducts.execute("halfproducts").get();
                    polprodukty = temp.split("; ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }

    //metoda rozwiazujaca problem z przewijaniem listy do samego dolu w przypadku umieszczenia jej wewnatrz
    //widoku ktory rownież jest przewijany - lista bez tego nie mogla byc przewijana do samego końca
    //ewewntualnie jej wysokosc byla wysokoscia pojedynczego elementu
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

    //zachowanie aplikacji na wybor elementu listy rozwijanej typProduktu
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String typProduktu = parent.getItemAtPosition(position).toString();

        if (typProduktu.equals("Materiał")) {
            //ukryj inne layoty i pokaz tworzenie materialu
            material.setVisibility(View.VISIBLE);
            polprodukt.setVisibility(View.GONE);
            produkt.setVisibility(View.GONE);

            //przypisz pola do wybranego layoutu
            nazwa = (EditText) findViewById(R.id.nazwaMaterialu);
            opis = (EditText) findViewById(R.id.opisMaterialu);
            czas = (EditText) findViewById(R.id.czasMaterialu);
            ilosc = (EditText) findViewById(R.id.iloscMaterialu);
            partia = (EditText) findViewById(R.id.partiaMaterialu);
            cena = (EditText) findViewById(R.id.cenaMaterialu);

            nazwa.setHint("Nazwa materiału");
        }
        if (typProduktu.equals("Półprodukt")) {
            //ukryj inne layoty i pokaz tworzenie polproduktu
            material.setVisibility(View.GONE);
            polprodukt.setVisibility(View.VISIBLE);
            produkt.setVisibility(View.GONE);

            //przypisz pola do wybranego layoutu
            nazwa = (EditText) findViewById(R.id.nazwaPolproduktu);
            opis = (EditText) findViewById(R.id.opisPolproduktu);
            czas = (EditText) findViewById(R.id.czasPolproduktu);
            ilosc = (EditText) findViewById(R.id.iloscPolproduktu);
            partia = (EditText) findViewById(R.id.partiaPolproduktu);

            nazwa.setHint("Nazwa półproduktu");

            //pobierz ponownie liste materialow
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ObslugaBazyDanych materials = new ObslugaBazyDanych(getApplicationContext());
                    try {
                        String temp = materials.execute("materials").get();
                        materialy = temp.split("; ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).run();

            //aktualizuj adapter listy i dodaj checboxy dla kazdego materialu z bazy danych
            materialyAdapter = new Adapter(materialy);
            materialyListView.setAdapter(materialyAdapter);
            setListViewHeightBasedOnChildren(materialyListView);
        }
        if (typProduktu.equals("Produkt")) {
            //ukryj inne layoty i pokaz tworzenie materialu
            material.setVisibility(View.GONE);
            polprodukt.setVisibility(View.GONE);
            produkt.setVisibility(View.VISIBLE);

            //przypisz pola do wybranego layoutu
            nazwa = (EditText) findViewById(R.id.nazwaProduktu);
            opis = (EditText) findViewById(R.id.opisProduktu);
            czas = (EditText) findViewById(R.id.czasProduktu);
            ilosc = (EditText) findViewById(R.id.iloscProduktu);
            partia = (EditText) findViewById(R.id.partiaProduktu);

            nazwa.setHint("Nazwa produktu");

            //pobierz ponownie liste polproduktow
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ObslugaBazyDanych halfproducts = new ObslugaBazyDanych(getApplicationContext());
                    try {
                        String temp = halfproducts.execute("halfproducts").get();
                        polprodukty = temp.split("; ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).run();

            //aktualizuj adapter listy i dodaj checboxy dla kazdego polproduktu z bazy danych
            polproduktyAdapter = new Adapter(polprodukty);
            polproduktyListView.setAdapter(polproduktyAdapter);
            setListViewHeightBasedOnChildren(polproduktyListView);
        }
    }

    //w przypadku nie wybrania zadnego elementu nie rob nic
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //utworzenie menu Preferencje
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    //zachowanie w przypadku wybrania obiektu z listy menu
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
        //obsluga przycisku - pobierz dane
        name = nazwa.getText().toString();
        details = opis.getText().toString();
        time = czas.getText().toString();
        quantity = ilosc.getText().toString();
        partion = partia.getText().toString();
        price = cena.getText().toString();

        //zastap "." "," poniewaz taki znak przecinka jest uzywany dla typu danych money (postgresql)
        price = price.replace('.', ',');

        //wyslij material do bazy danych - nowy watek umozliwia dodawanie kolejnych
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObslugaBazyDanych insertMaterial = new ObslugaBazyDanych(getApplicationContext());
                insertMaterial.execute("insert_material", name, details, time, quantity, partion, price);
            }
        }).run();
    }

    public void wyslijPolprodukt(View view) {
        //obsluga przycisku - pobierz dane
        name = nazwa.getText().toString();
        details = opis.getText().toString();
        time = czas.getText().toString();
        quantity = ilosc.getText().toString();
        partion = partia.getText().toString();

        //wyslij polprodukt do bazy danych - nowy watek umozliwia dodawanie kolejnych
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObslugaBazyDanych insertPolprodukt = new ObslugaBazyDanych(getApplicationContext());
                insertPolprodukt.execute("insert_polprodukt", name, details, time, quantity, partion);
            }
        }).run();
    }

    public void wyslijProdukt(View view) {
        //obsluga przycisku - pobierz dane
        name = nazwa.getText().toString();
        details = opis.getText().toString();
        time = czas.getText().toString();
        quantity = ilosc.getText().toString();
        partion = partia.getText().toString();

        //wyslij produkt do bazy danych - nowy watek umozliwia dodawanie kolejnych
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObslugaBazyDanych insertProdukt = new ObslugaBazyDanych(getApplicationContext());
                insertProdukt.execute("insert_produkt", name, details, time, quantity, partion, price);
            }
        }).run();
    }

    public class Adapter extends BaseAdapter {
        private String[] items;

        public Adapter(String[] items) {
            this.items = items;
        }

        @Override
        public int getCount() {
            //policz ile elementow nalezy utworzyc w liscie
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            //utworzenie listy checkboxow na podstawie schematu list_checkbox_item.xml
            convertView = getLayoutInflater().inflate(R.layout.list_checkbox_item, null);

            //przypisanie pojedynczego checkboxa do zmiennej
            final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.materialNazwa);

            //nadanie nazwy kazdemu elementowi z listy z osobna (pobrane z bazy danych nazwy)
            checkBox.setText(items[position]);

            //ustawienie nasluchiwacza dla kazdego elementu listy
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //zachowanie aplikacji na wybranie ktoregos z checkboxow

                }
            });

            //zwrocenie listy
            return convertView;
        }
    }
}

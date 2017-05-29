package com.example.danielk.planermrp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * Created by Daniel K on 2017-05-18.
 * Protokół obsługujący połączenie z baza danych PostGreSQL na serwerze v-ie.uek.krakow.pl
 * Mechanizm ten wykorzystuje metody klasy AsyncTask<> w wykonywania zadań w tle oraz reagowania
 * na stan w jakim się znajdują (przed/po wykonaniu)
 * Mechanizmy tutaj wymagaly tez stworzenia dosyc sporego zaplecza BackEndowego ktore zamiescilem
 * w repozytorium GitHuba jako ServerBackEnd
 *
 **/

public class ObslugaBazyDanych extends AsyncTask<String, Void, String> {
    private Context context;
    private String operationType;

    //konstruktor podstawowy, context wymagany jest do tworzenia Toast'ow z informacjami
    public ObslugaBazyDanych(Context context){
        this.context = context;
    }

    //metoda wywolywana w tle i wykonujaca operacje w zaleznosci od jej typu -> params[0]
    @Override
    protected String doInBackground(String... params) {
        //typy danych ktorych bedziemy sie spodziewac wyslac lub odebrac - dodalem w razie
        //dalszego rozwoju aplikacji (umozliwi to bardziej dynamicznie dzialanie)
        String inputCharSet = "UTF-8";
        String outputCharSer = "UTF-8";

        //rodzaj operacji do wykonania w tle
        operationType = params[0];

        if(operationType.equals("login")) { //operacja logowania
            try {
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/login.php"; //adres do skryptu

                //dane logowania przekazane z aktywnosci logowania
                String uLogin = params[1];
                String uPassword = params[2];

                //nawiązywanie połączenia oraz podstawowe ustawienia połączenia
                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                //wysyłanie zapytania o zalogowanie uzytkownika
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, outputCharSer));

                String sqlRequest = URLEncoder.encode("login", outputCharSer) + "=" + URLEncoder.encode(uLogin, outputCharSer) +
                        "&" + URLEncoder.encode("haslo", outputCharSer) + "=" + URLEncoder.encode(uPassword, outputCharSer);

                bufferedWriter.write(sqlRequest);
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();

                //odbieranie odpowiedzi od serwera
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharSet));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                //zwrocenie rezultatu odpowiedzi serwera do metody onPostExecute
                return result;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }

        if(operationType.equals("insert_material")){ //dodawanie nowego materialu do bazy
            try {
                //skrypt dodawania materialu
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/insert_material.php";

                //parametry do wyslania
                String nazwa = params[1];
                String opis = params[2];
                String czas = params[3];
                String naStanie = params[4];
                String partia = params[5];
                String cena = params[6];

                //ustawienia polaczenia i jego nawiązanie
                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                //wysylanie zapytania
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, outputCharSer));

                String sqlRequest = URLEncoder.encode("nazwa", outputCharSer) + "=" + URLEncoder.encode(nazwa, outputCharSer) +
                        "&" + URLEncoder.encode("opis", outputCharSer) + "=" + URLEncoder.encode(opis, outputCharSer) +
                        "&" + URLEncoder.encode("czas", outputCharSer) + "=" + URLEncoder.encode(czas, outputCharSer) +
                        "&" + URLEncoder.encode("naStanie", outputCharSer) + "=" + URLEncoder.encode(naStanie, outputCharSer) +
                        "&" + URLEncoder.encode("partia", outputCharSer) + "=" + URLEncoder.encode(partia, outputCharSer) +
                        "&" + URLEncoder.encode("cena", outputCharSer) + "=" + URLEncoder.encode(cena, outputCharSer);

                bufferedWriter.write(sqlRequest);
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();

                //odbieranie odpowiedzi
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharSet));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return result;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }

        if(operationType.equals("insert_polprodukt")){ //dodawanie polproduktu
            try {
                //adres do skryptu
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/insert_polprodukt.php";

                //parametry
                String nazwa = params[1];
                String opis = params[2];
                String czas = params[3];
                String naStanie = params[4];
                String partia = params[5];

                //ustanawianie polaczenia
                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                //wysylanie zapytania
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, outputCharSer));

                String sqlRequest = URLEncoder.encode("nazwa", outputCharSer) + "=" + URLEncoder.encode(nazwa, outputCharSer) +
                        "&" + URLEncoder.encode("opis", outputCharSer) + "=" + URLEncoder.encode(opis, outputCharSer) +
                        "&" + URLEncoder.encode("czas", outputCharSer) + "=" + URLEncoder.encode(czas, outputCharSer) +
                        "&" + URLEncoder.encode("naStanie", outputCharSer) + "=" + URLEncoder.encode(naStanie, outputCharSer) +
                        "&" + URLEncoder.encode("partia", outputCharSer) + "=" + URLEncoder.encode(partia, outputCharSer);

                bufferedWriter.write(sqlRequest);
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();

                //odbieranie odpowiedzi
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharSet));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return result;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }

        if(operationType.equals("insert_produkt")){ //dodawanie produktu
            try {
                //adres do skryptu
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/insert_produkt.php";

                //parametry
                String nazwa = params[1];
                String opis = params[2];
                String czas = params[3];
                String naStanie = params[4];
                String partia = params[5];

                //ustanowienie polaczenia
                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                //wysylanie zapytania
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, outputCharSer));

                String sqlRequest = URLEncoder.encode("nazwa", outputCharSer) + "=" + URLEncoder.encode(nazwa, outputCharSer) +
                        "&" + URLEncoder.encode("opis", outputCharSer) + "=" + URLEncoder.encode(opis, outputCharSer) +
                        "&" + URLEncoder.encode("czas", outputCharSer) + "=" + URLEncoder.encode(czas, outputCharSer) +
                        "&" + URLEncoder.encode("naStanie", outputCharSer) + "=" + URLEncoder.encode(naStanie, outputCharSer) +
                        "&" + URLEncoder.encode("partia", outputCharSer) + "=" + URLEncoder.encode(partia, outputCharSer);

                bufferedWriter.write(sqlRequest);
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();

                //odbieranie odpowiedzi
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharSet));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return result;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }
        if(operationType.equals("materials")){ //zapytanie o nazwy materialow (wszystkich)
            try {
                //adres do skryptu
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/materials.php";

                //ustanowienie polaczenia
                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);

                //odpowiedz serwera
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharSet));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return result;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }

        if(operationType.equals("halfproducts")){ //zapytanie o nazwy polproduktow (wszystkich)
            try {
                //adres do skryptu
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/halfproducts.php";

                //ustanowienie polaczenia
                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);

                //odbieranie odpowiedzi
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharSet));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return result;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }
        if(operationType.equals("plan")){ //zapytanie o ilosc dostepnych szaf
            // zapytanie dotyczylo tylko szaf z powodu malej ilosci czasu - w przyszlosci bedzie dynamicznie
            try {
                //adres skryptu
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/plan.php";

                //ustanowienie polaczenia
                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);

                //odbieranie odpowiedzi
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharSet));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return result;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }
        if(operationType.equals("cena")){ //zapytanie o ilosc dostepnych szaf
            // zapytanie dotyczylo tylko szaf z powodu malej ilosci czasu - w przyszlosci bedzie dynamicznie
            try {
                //adres skryptu
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/cena.php";

                //ustanowienie polaczenia
                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);

                //odbieranie odpowiedzi
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,inputCharSet));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return result;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }
        if(operationType.equals("nazwa_produktu")){ //zapytanie o ilosc dostepnych szaf
            // zapytanie dotyczylo tylko szaf z powodu malej ilosci czasu - w przyszlosci bedzie dynamicznie
            try {
                //adres skryptu
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/nazwa_produktu.php";

                //ustanowienie polaczenia
                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);

                //odbieranie odpowiedzi
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,inputCharSet));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return result;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }
        if(operationType.equals("czas_montazu_produktu")){ //zapytanie o ilosc dostepnych szaf
            // zapytanie dotyczylo tylko szaf z powodu malej ilosci czasu - w przyszlosci bedzie dynamicznie
            try {
                //adres skryptu
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/czas_montazu_produktu.php";

                //ustanowienie polaczenia
                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);

                //odbieranie odpowiedzi
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,inputCharSet));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return result;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }
        if(operationType.equals("wielkosc_partii_produktu")){ //zapytanie o ilosc dostepnych szaf
            // zapytanie dotyczylo tylko szaf z powodu malej ilosci czasu - w przyszlosci bedzie dynamicznie
            try {
                //adres skryptu
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/wielkosc_partii_produktu.php";

                //ustanowienie polaczenia
                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);

                //odbieranie odpowiedzi
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,inputCharSet));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return result;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }
        //odpowiedz domyslna
        return "Brak połączenia lub błąd skryptu.";
    }

    //metoda wywolywana przed wykonaniem metody doInBackground - nie znalazla jeszcze zastosowania
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //metoda wywolywana po wykonaniu metody doInBackground
    @Override
    protected void onPostExecute(String result) {
        //metoda reaguje na otrzymywane wyniki ze skryptow lub wywolywanie dzialania w metodzie doInBackground
        if(operationType.equals("login")) {
            //wyswietlenie odpowiedzi serwera
            if(result.equals("Logowanie powiodło sie.")){
                Toast.makeText(context, result, Toast.LENGTH_LONG).show();

                //przejscie do nowej aktywnosci
                Intent intent = new Intent(context, PanelGlowny.class);
                context.startActivity(intent);
            }
            else {
                Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            }
        }
        if(operationType.equals("insert_material")){
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
        if(operationType.equals("insert_polprodukt")){
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
        if(operationType.equals("insert_produkt")){
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

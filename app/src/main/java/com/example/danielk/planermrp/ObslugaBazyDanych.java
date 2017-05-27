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
 * Created by Daniel K on 2017-05-18.
 * Protokół obsługujący połączenie z baza danych PostGreSQL na serwerze v-ie.uek.krakow.pl
 */

public class ObslugaBazyDanych extends AsyncTask<String, Void, String> {
    private Context context;
    private String operationType;

    public ObslugaBazyDanych(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
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

        if(operationType.equals("insert_material")){
            try {
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/insert_material.php";

                String nazwa = params[1];
                String opis = params[2];
                String czas = params[3];
                String naStanie = params[4];
                String partia = params[5];
                String cena = params[6];

                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

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

        if(operationType.equals("insert_polprodukt")){
            try {
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/insert_polprodukt.php";

                String nazwa = params[1];
                String opis = params[2];
                String czas = params[3];
                String naStanie = params[4];
                String partia = params[5];

                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

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

        if(operationType.equals("insert_produkt")){
            try {
                String loginURL = "http://v-ie.uek.krakow.pl/~s187086/insert_produkt.php";

                String nazwa = params[1];
                String opis = params[2];
                String czas = params[3];
                String naStanie = params[4];
                String partia = params[5];

                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

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

        return "Brak połączenia lub błąd skryptu.";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        if(operationType.equals("login")) {
            //wyswietlenie odpowiedzi serwera
            if(result.equals("Logowanie powiodło sie.")){
                Toast.makeText(context, result, Toast.LENGTH_LONG).show();

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

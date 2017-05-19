package com.example.danielk.planermrp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

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
    private AlertDialog dialog;
    private String operationType;

    public ObslugaBazyDanych(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
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
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String sqlRequest = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(uLogin, "UTF-8") +
                        "&" + URLEncoder.encode("haslo", "UTF-8") + "=" + URLEncoder.encode(uPassword, "UTF-8");

                bufferedWriter.write(sqlRequest);
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();

                //odbieranie odpowiedzi od serwera
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

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
        return "Brak połączenia lub błąd skryptu.";
    }

    @Override
    protected void onPreExecute() {
        //utworzenie pustego dialogu
        dialog = new AlertDialog.Builder(context).create();
    }

    @Override
    protected void onPostExecute(String result) {
        if(operationType.equals("login")) {
            //wyswietlenie odpowiedzi serwera w formie dialogu
            dialog.setTitle("Status logowania");
            dialog.setMessage(result);
            dialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

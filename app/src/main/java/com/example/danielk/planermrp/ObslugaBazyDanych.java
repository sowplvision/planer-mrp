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

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Daniel K on 2017-05-18.
 */

public class ObslugaBazyDanych extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog dialog;

    public ObslugaBazyDanych(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String operationType = params[0];
        String uLogin = params[1];
        String uPassword = params[2];

        String loginURL = "http://v-ie.uek.krakow.pl/~s187086/login.php";

        if(operationType.equals("login")) {
            try {
                URL url = new URL(loginURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);


                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String sqlRequest = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(uLogin, "UTF-8") +
                        "&" + URLEncoder.encode("haslo", "UTF-8") + "=" + URLEncoder.encode(uPassword, "UTF-8");

                bufferedWriter.write(sqlRequest);
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();

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

                return result;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }
        return "Brak połączenia.";
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Logowanie");
    }

    @Override
    protected void onPostExecute(String result) {
        dialog.setMessage(result);
        dialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

package com.example.danielk.planermrp;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
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

public class ObslugaBazyDanych extends AsyncTask<String, Void, Void> {
    Context context;

    public ObslugaBazyDanych(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        String uLogin = params[0];
        String uPassword = params[1];

        String loginURL = "http://adres-pliku-php.php";

        try {
            URL url = new URL(loginURL);
            HttpURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String sqlRequest = URLEncoder.encode("login","UTF-8") + "=" + URLEncoder.encode(uLogin, "UTF-8");
            sqlRequest += "&" + URLEncoder.encode("haslo","UTF-8") + "=" + URLEncoder.encode(uPassword, "UTF-8");

            bufferedWriter.write(sqlRequest);
            bufferedWriter.flush();
            bufferedWriter.close();

            outputStream.close();
        }
        catch (MalformedURLException e){}
        catch (IOException e) {}
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

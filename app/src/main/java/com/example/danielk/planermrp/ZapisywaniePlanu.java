package com.example.danielk.planermrp;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  Klasa zapisuje dane podane przez użytkownika oraz tworzy plik z rozszerzeniem .csv
 */

public class ZapisywaniePlanu{
    private static Context context;
    private static String tresc;
    private static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PlanerMRP";

    public ZapisywaniePlanu(String tresc, Context context){
        this.tresc = tresc;
        this.context = context;

        File dir = new File(path);
        dir.mkdirs();
    }

    public static void zapisz() {
        File plik = new File(path + "/plan.csv");
        String[] data = tresc.split(";");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(plik);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            try {
                for (int i = 0; i < data.length; i++) {
                    fos.write(data[i].getBytes());
                    if (i < data.length - 1) {
                        fos.write("\n\n".getBytes());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(context,"Pomyślnie utworzono plik", Toast.LENGTH_LONG).show();
        }
    }
}

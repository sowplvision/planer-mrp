package com.example.danielk.planermrp;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  Klasa zapisuje dane podane przez użytkownika oraz tworzy plik z rozszerzeniem .csv
 */

public class ZapisywaniePlanu extends AppCompatActivity {
    public EditText editText;
    public TextView textView;
    public Button save;

    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aaZapisywaniePlanu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tworzenie_planu);

       // editText = (EditText) findViewById(R.id.editText);
       // textView = (TextView) findViewById(R.id.textView);
       // save = (Button) findViewById(R.id.save);

        File dir = new File(path);
        dir.mkdirs();
    }
    public void buttonSave (View view){

        File file = new File (path + "/savedFile.csv");
        String[] saveText = String.valueOf(editText.getText()).split(System.getProperty("line.separator"));

        editText.setText("");

        Toast.makeText(getApplicationContext(), "Zapisano", Toast.LENGTH_LONG).show();

        Save (file, saveText);
    }

    public static void Save(File file, String[] data) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            try {
                for (int i = 0; i < data.length; i++) {
                    fos.write(data[i].getBytes());
                    if (i < data.length - 1) {
                        fos.write("\n".getBytes());
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
        }
    }
}

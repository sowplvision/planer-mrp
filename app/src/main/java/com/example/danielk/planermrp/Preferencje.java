package com.example.danielk.planermrp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Preferencje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencje);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Ustawienia ustawienia = new Ustawienia();
        fragmentTransaction.add(android.R.id.content, ustawienia, "settings");
        fragmentTransaction.commit();
    }

    public static class Ustawienia extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.settings);
        }
    }
}

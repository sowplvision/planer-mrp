package com.example.danielk.planermrp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

/**
 *
 * Klasa zawierająca preferencje widoczne na ekranie Logowania.
 *
 */

public class PreferencjeLogowania extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preferencje_logowania);

        //tworzenie preferencji
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DaneLogowania daneLogowania = new DaneLogowania();
        fragmentTransaction.add(android.R.id.content, daneLogowania, "settings");
        fragmentTransaction.commit();
    }

    public static class DaneLogowania extends PreferenceFragment {
        private SharedPreferences daneLogowania;
        private CheckBoxPreference zapisz;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.login_settings);

            //odczytywanie zapisanych ustawień
            zapisz = (CheckBoxPreference) findPreference("pref_zapisz");

            daneLogowania = getActivity().getSharedPreferences("daneLogowania", Context.MODE_PRIVATE);

            zapisz.setChecked(daneLogowania.getBoolean("zapisz",true));
        }

        @Override
        public void onStop() {
            super.onStop();

            //zapisywanie zmian w ustawieniach w momencie opuszczania preferencji
            SharedPreferences.Editor editor = daneLogowania.edit();

            editor.putBoolean("zapisz",zapisz.isChecked());
            editor.apply();
        }
    }
}

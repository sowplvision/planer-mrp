package com.example.danielk.planermrp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

/**
 *
 * Klasa zawierająca rozszerzone preferencje, warta rozbudowania poprzez activity_preferencje.xml
 *
 **/

public class Preferencje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencje);

        //utworzenie preferencji
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Ustawienia ustawienia = new Ustawienia();
        fragmentTransaction.add(android.R.id.content, ustawienia, "settings");
        fragmentTransaction.commit();
    }

    public static class Ustawienia extends PreferenceFragment {
        private EditTextPreference login, haslo;
        private CheckBoxPreference zapisz;
        private String oldLogin, oldHaslo;
        private SharedPreferences daneLogowania;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.settings);

            //pobieranie preferencji jezeli istnieja juz jakies i przysywanie ich do pol
            daneLogowania = getActivity().getSharedPreferences("daneLogowania",Context.MODE_PRIVATE);

            oldLogin = daneLogowania.getString("login","");
            oldHaslo = daneLogowania.getString("haslo","");

            login = (EditTextPreference) findPreference("pref_login");
            haslo = (EditTextPreference) findPreference("pref_haslo");
            zapisz = (CheckBoxPreference) findPreference("pref_zapisz");

            login.setText(oldLogin);
            haslo.setText(oldHaslo);
            zapisz.setChecked(daneLogowania.getBoolean("zapisz",true));
        }

        @Override
        public void onStop() {
            super.onStop();

            //w momencie opuszczania preferencji zapisywanie zmian (jeśli zaszly)
            SharedPreferences.Editor editor = daneLogowania.edit();

            if(!oldLogin.equals(login.getText())){
                editor.putString("login",login.getText());
            }
            if(!oldHaslo.equals(haslo.getText())){
                editor.putString("haslo",haslo.getText());
            }
            editor.putBoolean("zapisz",zapisz.isChecked());
            editor.apply();
        }
    }
}

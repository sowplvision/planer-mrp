package com.example.danielk.planermrp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

public class PreferencjeLogowania extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preferencje_logowania);

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

            zapisz = (CheckBoxPreference) findPreference("pref_zapisz");

            daneLogowania = getActivity().getSharedPreferences("daneLogowania", Context.MODE_PRIVATE);

            zapisz.setChecked(daneLogowania.getBoolean("zapisz",true));
        }

        @Override
        public void onStop() {
            super.onStop();
            SharedPreferences.Editor editor = daneLogowania.edit();

            editor.putBoolean("zapisz",zapisz.isChecked());
            editor.apply();
        }
    }
}

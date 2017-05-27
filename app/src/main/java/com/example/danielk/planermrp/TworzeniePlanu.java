package com.example.danielk.planermrp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class TworzeniePlanu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tworzenie_planu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.login_settings:
                Intent intent = new Intent(this, Preferencje.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}

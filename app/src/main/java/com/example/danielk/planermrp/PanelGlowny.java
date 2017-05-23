package com.example.danielk.planermrp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class PanelGlowny extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_glowny);
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

    public void stworzPlan(View view){
        Intent intent = new Intent(this,TworzeniePlanu.class);
        startActivity(intent);
    }

    public void wpisDoBazy(View view){
        Intent intent = new Intent(this,WpisDoBazy.class);
        startActivity(intent);
    }

    public void informacje(View view){
        Intent intent = new Intent(this, Informacje.class);
        startActivity(intent);
    }
}

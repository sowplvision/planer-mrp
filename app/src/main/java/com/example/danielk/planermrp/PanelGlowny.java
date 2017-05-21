package com.example.danielk.planermrp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PanelGlowny extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_glowny);
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

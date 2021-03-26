package com.decard.mobilesdkexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Choose extends AppCompatActivity {

    private Button Deka;
    private Button Baolaite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);

        Deka = findViewById(R.id.deka);
        Baolaite = findViewById(R.id.baolaite);

        Deka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Choose.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Baolaite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Choose.this, DeviceScanActivity.class);
                startActivity(intent);
            }
        });
    }



}

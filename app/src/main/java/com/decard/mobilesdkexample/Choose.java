package com.decard.mobilesdkexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Choose extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);

         Button deka = findViewById(R.id.deka);
         Button baolaite = findViewById(R.id.baolaite);
         Button register = findViewById(R.id.register);
         Button temp = findViewById(R.id.temp);

        deka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Choose.this, MainActivity.class);
                startActivity(intent);
            }
        });

        baolaite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Choose.this, DeviceScanActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Choose.this, com.decard.mobilesdkexample.LoginRegister.loginActivity.class);
                startActivity(intent);
            }
        });

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Choose.this, Temp.class);
                startActivity(intent);
            }
        });
    }

}

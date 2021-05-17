package com.decard.mobilesdkexample.HelpClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.decard.mobilesdkexample.CustomerTel;
import com.decard.mobilesdkexample.R;

public class CustomerHelp extends AppCompatActivity implements View.OnClickListener {

    private Button checkCustomer;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        initView();
    }

    private void initView() {
        checkCustomer = findViewById(R.id.checkCustomer_btn);
        logout = findViewById(R.id.logout_btn);
        checkCustomer.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkCustomer_btn:
                Intent intent = new Intent();
                intent.setClass(CustomerHelp.this, CustomerTel.class);
                startActivity(intent);
                break;
            case R.id.logout_btn:
                Intent intent1 = new Intent();
                intent1.setClass(CustomerHelp.this,
                        com.decard.mobilesdkexample.loginActivity.class);
                startActivity(intent1);
                break;


        }
    }

}

package com.decard.mobilesdkexample;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class CurrCustomerInfo extends AppCompatActivity {

    private TextView name;
    private TextView gender;
    private TextView birth;
    private TextView id;
    private TextView address;
    MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerinfo);
        initView();
    }

    private void initView() {
        myApp = (MyApp)getApplication();
        name = findViewById(R.id.CName);
        name.setText(myApp.currCustomer.getCustomerName());

        gender = findViewById(R.id.CGender);
        gender.setText(myApp.currCustomer.getCustomerGender());

        birth = findViewById(R.id.CBirth);
        birth.setText(myApp.currCustomer.getCustomerBirthday());

        id = findViewById(R.id.CNumber);
        id.setText(myApp.currCustomer.getCardIdNo());

        address = findViewById(R.id.CAddress);
        address.setText(myApp.currCustomer.getCustomerAddress());
    }
}

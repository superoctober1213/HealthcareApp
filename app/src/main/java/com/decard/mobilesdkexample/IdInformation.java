package com.decard.mobilesdkexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.decard.mobilesdkexample.OperaUtils.IDCardUtil;

public class IdInformation extends AppCompatActivity {

    TextView InfoName;
    TextView InfoGender;
    TextView InfoBirth;
    TextView InfoNum;
    TextView InfoAdd;
    Button InStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_information);
        InStart= findViewById(R.id.Start);
        InfoName = findViewById(R.id.IName);
        InfoGender = findViewById(R.id.IGender);
        InfoBirth = findViewById(R.id.IBirth);
        InfoNum = findViewById(R.id.INumber);
        InfoAdd = findViewById(R.id.IAddress);

        InStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoName.setText(IDCardUtil.dc_get_i_d_raw_info().getName());
                InfoGender.setText(IDCardUtil.dc_get_i_d_raw_info().getSex());
                InfoBirth.setText(IDCardUtil.dc_get_i_d_raw_info().getBirthday());
                InfoNum.setText(IDCardUtil.dc_get_i_d_raw_info().getId() );
                InfoAdd.setText(IDCardUtil.dc_get_i_d_raw_info().getAddress());

            }
        });


    }

}

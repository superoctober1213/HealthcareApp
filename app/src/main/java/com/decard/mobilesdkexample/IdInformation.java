package com.decard.mobilesdkexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.decard.mobilesdkexample.OperaUtils.IDCardUtil;
import com.decard.mobilesdkexample.ReadHistory.DBReadHelper;
import com.decard.mobilesdkexample.ReadHistory.IdInfo;

import java.util.ArrayList;

public class IdInformation extends AppCompatActivity {

    private TextView InfoName;
    private TextView InfoGender;
    private TextView InfoBirth;
    private TextView InfoNum;
    private TextView InfoAdd;
    private Button InStart;
    private DBReadHelper dbReadHelper;


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
        dbReadHelper = new DBReadHelper(this);
        boolean match = true;
        ArrayList<IdInfo> data = dbReadHelper.getAllData();

        for (int i = 0; i < data.size(); i++) {
            if (IDCardUtil.dc_get_i_d_raw_info().getId().equals(data.get(i).getIdNum())){
                match = false;
            }
        }
        if (match) {
            dbReadHelper.add(IDCardUtil.dc_get_i_d_raw_info().getName(), IDCardUtil.dc_get_i_d_raw_info().getSex(), IDCardUtil.dc_get_i_d_raw_info().getBirthday(),
                    IDCardUtil.dc_get_i_d_raw_info().getId(), IDCardUtil.dc_get_i_d_raw_info().getAddress());
        }else Toast.makeText(this, "此身份已存在，请勿重复输入", Toast.LENGTH_SHORT).show();


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

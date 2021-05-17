package com.decard.mobilesdkexample;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.BaseClass.GetAccountInfoResponse;
import com.decard.mobilesdkexample.HelpClass.CustomerHelp;
import com.decard.mobilesdkexample.OperaUtils.IDCardUtil;
import com.decard.mobilesdkexample.BaseClass.AddSpaceTextWatcher;
import com.decard.mobilesdkexample.BaseClass.GetCustomerInfoRequest;
import com.decard.mobilesdkexample.BaseClass.GetCustomerInfoResponse;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.zip.GZIPInputStream;


public class IdInformation extends AppCompatActivity implements View.OnClickListener {

    private TextView InfoName;
    private TextView InfoGender;
    private TextView InfoBirth;
    private TextView InfoNum;
    private TextView InfoAdd;
    private Button InStart;
    private String phoneNum;
    private MessageHandler mHandler = new MessageHandler();
    private static final int RESULT1 = 1;
    private static final int RESULT2 = 2;
    private static final int RESULT3 = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_information);
        BasicOper.dc_open("BT", this, "DC:0D:30:B9:7D:F7", 0);

        initView();
    }

    private void initView() {
        InStart = findViewById(R.id.Start);
        InfoName = findViewById(R.id.IName);
        InfoGender = findViewById(R.id.IGender);
        InfoBirth = findViewById(R.id.IBirth);
        InfoNum = findViewById(R.id.INumber);
        InfoAdd = findViewById(R.id.IAddress);

        Intent intent =getIntent();
        phoneNum = intent.getStringExtra("phone");

        InStart.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Start:
                BasicOper.dc_beep(10);
                InfoName.setText(IDCardUtil.dc_get_i_d_raw_info().getName());
                InfoGender.setText(IDCardUtil.dc_get_i_d_raw_info().getSex());
                InfoBirth.setText(IDCardUtil.dc_get_i_d_raw_info().getBirthday());
                InfoNum.setText(IDCardUtil.dc_get_i_d_raw_info().getId());
                InfoAdd.setText(IDCardUtil.dc_get_i_d_raw_info().getAddress());

                GetCustomerInfoRequest getCustomerInfoRequest = new GetCustomerInfoRequest();
                sendIdInfo(getCustomerInfoRequest);
        }
    }


    private void sendIdInfo(GetCustomerInfoRequest getCustomerInfoRequest) {
        MyApp myApp = (MyApp) getApplication();
        GetAccountInfoResponse currAccount = myApp.currAccount;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        StringBuilder startDate =
                new StringBuilder(IDCardUtil.dc_get_i_d_raw_info().getStartTime());
        startDate.insert(4, "-");
        startDate.insert(7, "-");
        StringBuilder endDate = new StringBuilder(IDCardUtil.dc_get_i_d_raw_info().getEndTime());
        endDate.insert(4, "-");
        endDate.insert(7, "-");
        StringBuilder birth = new StringBuilder(IDCardUtil.dc_get_i_d_raw_info().getBirthday());
        birth.insert(4, "-");
        birth.insert(7, "-");

        StringBuilder nation = new StringBuilder(IDCardUtil.dc_get_i_d_raw_info().getNation());
        nation.delete(nation.length() - 1, nation.length());

        getCustomerInfoRequest.setCustomerGuid(UUID.randomUUID().toString());
        getCustomerInfoRequest.setCustomerName(IDCardUtil.dc_get_i_d_raw_info().getName());
        getCustomerInfoRequest.setCustomerGender(IDCardUtil.dc_get_i_d_raw_info().getSex());
        getCustomerInfoRequest.setCustomerNation(nation.toString());
        getCustomerInfoRequest.setCustomerBirthday(birth.toString());
        getCustomerInfoRequest.setCustomerAddress(IDCardUtil.dc_get_i_d_raw_info().getAddress());
        getCustomerInfoRequest.setCardIdNo(IDCardUtil.dc_get_i_d_raw_info().getId());
        getCustomerInfoRequest.setDepartment(IDCardUtil.dc_get_i_d_raw_info().getOffice());
        getCustomerInfoRequest.setExpireStartdate(startDate.toString());
        getCustomerInfoRequest.setExpireEnddate(endDate.toString());
        getCustomerInfoRequest.setPhoteImage(IDCardUtil.dc_get_i_d_raw_info().getPhotoDataHexStr());
        getCustomerInfoRequest.setCreatedOn(simpleDateFormat.format(date));
        getCustomerInfoRequest.setUpdatedOn(simpleDateFormat.format(date));
        getCustomerInfoRequest.setCreatedBy(currAccount.getAccountGuid());
        getCustomerInfoRequest.setUpdatedBy(currAccount.getAccountGuid());
        getCustomerInfoRequest.setContactTel(phoneNum);

        new Thread() {
            public void run() {
                String resultString = null;
                HttpURLConnection conn = null;
                InputStream inputStream = null;
                ByteArrayOutputStream bos = null;
                try {
                    String srcUrl = "https://www.zojoscreen.com/api/Values/InsertCustomerInfo/";
                    URL url = new URL(srcUrl);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);
                    conn.connect();
                    Gson gson = new Gson();
                    String paramsString = gson.toJson(getCustomerInfoRequest);

                    //传入参数
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(paramsString.getBytes());

                    int responseCode = conn.getResponseCode();
                    if (responseCode == conn.HTTP_OK || true) {
                        if (null != conn.getHeaderField("Content-Encoding")) {
                            inputStream = new GZIPInputStream(conn.getInputStream());
                        } else {
                            inputStream = conn.getInputStream();
                        }

                        bos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[10240];
                        int len = -1;
                        while ((len = inputStream.read(buffer)) != -1) {
                            bos.write(buffer, 0, len);
                        }
                        bos.flush();
                        byte[] resultByte = bos.toByteArray();
                        resultString = new String(resultByte);
                    }

                    GetCustomerInfoResponse getCustomerInfoResponse = gson.fromJson(resultString,
                            GetCustomerInfoResponse.class);

                    if (getCustomerInfoResponse.getResultCode().equals("0")) {
                        mHandler.sendEmptyMessage(RESULT1);
                    } else if (getCustomerInfoResponse.getResultCode().equals("1")) {
                        mHandler.sendEmptyMessage(RESULT2);
                    } else {
                        mHandler.sendEmptyMessage(RESULT3);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bos != null) {
                        try {
                            bos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();

    }

    class MessageHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case RESULT1:
                    Toast.makeText(IdInformation.this, "上传成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(IdInformation.this, CustomerHelp.class);
                    startActivity(intent);
                    finish();
                    break;
                case RESULT2:
                    Toast.makeText(IdInformation.this, "此身份已存在", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT3:
                    Toast.makeText(IdInformation.this, "上传失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}

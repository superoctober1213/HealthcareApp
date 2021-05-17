package com.decard.mobilesdkexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.decard.mobilesdkexample.BaseClass.CustomerQueryParamRequest;
import com.decard.mobilesdkexample.BaseClass.GetCustomerInfoResponse;
import com.decard.mobilesdkexample.HelpClass.AppUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class CustomerTel extends AppCompatActivity implements View.OnClickListener {

    private Button check;
    private EditText phoneInput;
    MyApp myApp;
    private MessageHandler mHandler = new MessageHandler();
    private static final int SUCCESS = 1;
    private static final int FAIL = 2;
    AlertDialog alertDialog1;
    AlertDialog alertDialog2;
    private CustomerQueryParamRequest customerQueryParamRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customertel);
        myApp = (MyApp) getApplication();
        initView();
    }

    private void initView() {
        check = findViewById(R.id.check_btn);
        phoneInput = findViewById(R.id.phone_input);
        check.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_btn:
                final String phone = phoneInput.getText().toString().trim().replace(" ", "");
                //去除分隔手机号中间的空格
                if (phone.equals("")) {//为空
                    Toast.makeText(this, "输入为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!AppUtil.isChinaPhoneLegal(phone)) {//不符合手机号码格式
                    phoneInput.setText("");
                    Toast.makeText(this, "手机号格式错误，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    checkCurrCustomer(phoneInput.getText().toString());
                }
        }
    }

    private void checkCurrCustomer(String phone) {
        customerQueryParamRequest = new CustomerQueryParamRequest(null,
                null, phone, null, null);

        ConnThread t1 = new ConnThread();
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    class ConnThread extends Thread {
        @Override
        public void run() {
            String resultString = null;
            HttpURLConnection conn = null;
            InputStream inputStream = null;
            ByteArrayOutputStream bos = null;
            try {
                String srcUrl = "https://www.zojoscreen.com/api/Values/GetCustomerInfoByParam";
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
                String paramsString = gson.toJson(customerQueryParamRequest);

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
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
                        "GB2312"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                GetCustomerInfoResponse getCustomerInfo = gson.fromJson(response.toString(),
                        GetCustomerInfoResponse.class);
                if (getCustomerInfo.getResultCode().equals("1")) {
                    mHandler.sendEmptyMessage(FAIL);
                }
                if (getCustomerInfo.getResultCode().equals("0")) {
                    myApp.currCustomer = getCustomerInfo;
                    mHandler.sendEmptyMessage(SUCCESS);
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
    }


    private void alert1(String phone) {
        alertDialog1 = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("手机号码不存在，是否建立新的用户")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.setClass(CustomerTel.this, IdInformation.class);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                        finish();
                    }
                })

                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .create();
    }

    private void alert2(GetCustomerInfoResponse response) {
        alertDialog2 = new AlertDialog.Builder(this)
                .setTitle("用户查找成功，请确认用户信息")
                .setMessage("姓名: " + response.getCustomerName() + "\n身份证号: " + response.getCardIdNo())
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(CustomerTel.this, Choose.class);
                        startActivity(intent);
                        finish();
                    }
                })

                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .create();

    }

    private class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FAIL:
                    alert1(phoneInput.getText().toString());
                    alertDialog1.show();
                    break;
                case SUCCESS:
                    alert2(myApp.currCustomer);
                    alertDialog2.show();
                    break;
            }
        }
    }
}

package com.decard.mobilesdkexample;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.contec.htd.code.base.ContecDevice;
import com.contec.htd.code.callback.BluetoothSearchCallback;
import com.contec.htd.code.callback.CommunicateCallback;
import com.contec.htd.code.connect.ContecSdk;
import com.contec.htd.code.device.ResultData;
import com.decard.mobilesdkexample.BaseClass.GetCheckItemInfoRequest;
import com.decard.mobilesdkexample.BaseClass.GetCheckItemInfoResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.GZIPInputStream;


public class Temp extends AppCompatActivity {

    ContecSdk sdk = new ContecSdk();
    private TextView textView;
    private static final String DEVICE_BLUETOOTH_NAME = "HC-08";
    private static final String DEVICE_BLUETOOTH_NAME_04 = "TEMP04";
    private static final String DEVICE_BLUETOOTH_NAME_05 = "TEMP05";//HTD8808E/HTD8808C/TP700
    private static final String DEVICE_BLUETOOTH_NAME_06 = "TEMP06";
    private static final String DEVICE_BLUETOOTH_NAME_Bioland = "Bioland-IT";

    private String TAG = MainActivity.class.getSimpleName();
    private boolean hasFound = false;
    private Button searchBtn;
    private BluetoothAdapter mBluetooth;
    MyApp myApp;
    private MessageHandler mHandler = new MessageHandler();
    private static final int SUCCESS = 1;
    private static final int FAIL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        myApp = (MyApp) getApplication();
        initView();

    }

    private void initView() {

        textView = findViewById(R.id.temp_tex);
        searchBtn = findViewById(R.id.temp_btn);
        sdk.init(true);
        ActivityCompat.requestPermissions(
                Temp.this,
                new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                },
                0
        );
        mBluetooth = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetooth.isEnabled()) {
            mBluetooth.enable();
        }
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasFound = false;
                textView.append("读取数据中 " + "\n");
                sdk.startBluetoothSearch(bluetoothSearchCallback, 20000);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    BluetoothSearchCallback bluetoothSearchCallback = new BluetoothSearchCallback() {

        @Override
        public void onSearchError(final int errorCode) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.append("搜索错误 ：" + errorCode + "\n");
                }
            });
        }

        @Override
        public void onContecDeviceFound(ContecDevice contecDevice) {

            if (hasFound) {
                return;
            }
            if (contecDevice.getName() == null) {
                return;
            }
            final String name = contecDevice.getName();
            //打印设备名称
            Log.e(TAG, contecDevice.getName());

            if (name.contains(DEVICE_BLUETOOTH_NAME) || name.contains(DEVICE_BLUETOOTH_NAME_04) || name.contains(DEVICE_BLUETOOTH_NAME_06) || name.contains(DEVICE_BLUETOOTH_NAME_Bioland)) {
                hasFound = true;
                //停止搜索
                sdk.stopBluetoothSearch();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.append("Found device:" + name + "\nStart communicate" + "\n");
//                    }
//                });
                //启动连接获取数据
                sdk.startCommunicate(Temp.this, contecDevice, communicateCallback);
            } else if (name.contains(DEVICE_BLUETOOTH_NAME_05)) {//HTD8808E/HTD8808C/TP700
                hasFound = true;
                //停止搜索
                sdk.stopBluetoothSearch();
                //仅TP700设备可通过广播包判断有无新数据
                //Only the TP700 device can determine whether there is new data from the
                // broadcast packet
                byte[] record = contecDevice.getRecord();
                String recordStr = new String(record);
                boolean hasNewData = recordStr != null && recordStr.contains("DATA");
                if (hasNewData) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.append("Found device:" + name + " has new data.\nStart
//                            communicate" + "\n");
//                        }
//                    });
                    //If the TP700 device has new data, connect to get new data.
                    //sdk.startCommunicate(MainActivity.this, contecDevice, communicateCallback);
                } else {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.append("Found device:" + name + " has no new data.\nStart
//                            communicate" + "\n");
//                        }
//                    });
                }

                //启动连接获取数据
                sdk.startCommunicate(Temp.this, contecDevice, communicateCallback);
            }
        }

        @Override
        public void onSearchComplete() {

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    textView.append("Search End" + "\n");
//                }
//            });

        }
    };


    CommunicateCallback communicateCallback = new CommunicateCallback() {


        @Override
        public void onCommunicateSuccess(final ResultData data) {

            final StringBuffer sstb = new StringBuffer();

            if (data.temp != null) {
                sstb.append("体温：").append(data.temp).append("\n");
                sendTempInfo(data.temp);
            } else {
                sstb.append("状态：").append(data.retValState).append(",错误：").append(data.error).append("\n");
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.append(sstb.toString() + "\n");
                }
            });

        }

        private void sendTempInfo(String temp) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(new Date());
            GetCheckItemInfoRequest itemInfoRequest = new GetCheckItemInfoRequest(0,
                    myApp.currCustomer.getCustomerGuid(), "Temperature", temp, date);

            new Thread() {
                public void run() {
                    String resultString = null;
                    HttpURLConnection conn = null;
                    InputStream inputStream = null;
                    ByteArrayOutputStream bos = null;
                    try {
                        String srcUrl = "https://www.zojoscreen" +
                                ".com/api/Values/EditCheckItemInfo/";
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
                        String paramsString = gson.toJson(itemInfoRequest);

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
                        BufferedReader reader =
                                new BufferedReader(new InputStreamReader(inputStream,
                                "GB2312"));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }

                        GetCheckItemInfoResponse getResponse = gson.fromJson(response.toString(),
                                GetCheckItemInfoResponse.class);
                        if (getResponse.getResultCode().equals("1")) {
                            mHandler.sendEmptyMessage(FAIL);
                        }
                        if (getResponse.getResultCode().equals("0")) {
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
            }.start();
        }

        @Override
        public void onCommunicateFailed(final int errorCode) {


//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    textView.append("Failed :" + errorCode + "-" + getResources()
//                    .getStringArray(R.array.blecommunicate_failed_messages)[errorCode] + "\n");
//                }
//            });
        }

        @Override
        public void onCommunicateProgress(final int status) {

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//
//                    textView.append("Progress = " + status + "-" + getResources()
//                    .getStringArray(R.array.communicate_progresses)[status] + "\n");
//                }
//            });
        }
    };

    private class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FAIL:
                    Toast.makeText(Temp.this, "上传失败，请再次上传", Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    Toast.makeText(Temp.this, "上传成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


}

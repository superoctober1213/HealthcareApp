 package com.decard.mobilesdkexample;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.contec.htd.code.base.ContecDevice;
import com.contec.htd.code.callback.BluetoothSearchCallback;
import com.contec.htd.code.callback.CommunicateCallback;
import com.contec.htd.code.connect.ContecSdk;
import com.contec.htd.code.device.ResultData;

 public class Temp extends AppCompatActivity {

    ContecSdk sdk = new ContecSdk();
    private TextView textView;
    private static final String DEVICE_BLUETOOTH_NAME = "HC-08";
    private static final String DEVICE_BLUETOOTH_NAME_04 = "TEMP04";
    private static final String DEVICE_BLUETOOTH_NAME_05 = "TEMP05";//HTD8808E/HTD8808C/TP700
    private static final String DEVICE_BLUETOOTH_NAME_06 = "TEMP06";
    private static final String DEVICE_BLUETOOTH_NAME_Bioland = "Bioland-IT";

    private String TAG = Temp.class.getSimpleName();
    private boolean hasFound = false;
    private Button searchBtn;
    private BluetoothAdapter mBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        textView = findViewById(R.id.temp_tex);
        searchBtn = findViewById(R.id.temp_btn);

        sdk.init(true);
//        ActivityCompat.requestPermissions(
//                Temp.this,
//                new String[]{
//                        Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION
//                },
//                0
//        );
        mBluetooth = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetooth.isEnabled()) {
            mBluetooth.enable();
        }
        searchBtn.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasFound = false;
                textView.setText("Searching Device " + "\n");
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
                     textView.append("Search Error ：" + errorCode + "\n");
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

             if (name.contains(DEVICE_BLUETOOTH_NAME) || name.contains(DEVICE_BLUETOOTH_NAME_04)  || name.contains(DEVICE_BLUETOOTH_NAME_06)||name.contains(DEVICE_BLUETOOTH_NAME_Bioland)) {
                 hasFound = true;
                 //停止搜索
                 sdk.stopBluetoothSearch();
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         textView.append("Found device:" + name + "\nStart communicate" + "\n");
                     }
                 });
                 //启动连接获取数据
                 sdk.startCommunicate(Temp.this, contecDevice, communicateCallback);
             }else if(name.contains(DEVICE_BLUETOOTH_NAME_05)){//HTD8808E/HTD8808C/TP700
                 hasFound = true;
                 //停止搜索
                 sdk.stopBluetoothSearch();
                 //仅TP700设备可通过广播包判断有无新数据
                 //Only the TP700 device can determine whether there is new data from the broadcast packet
                 byte[] record = contecDevice.getRecord();
                 String recordStr = new String(record);
                 boolean hasNewData = recordStr != null && recordStr.contains("DATA");
                 if(hasNewData){
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             textView.append("Found device:" + name + " has new data.\nStart communicate" + "\n");
                         }
                     });
                     //If the TP700 device has new data, connect to get new data.
                     //sdk.startCommunicate(MainActivity.this, contecDevice, communicateCallback);
                 }else{
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             textView.append("Found device:" + name + " has no new data.\nStart communicate" + "\n");
                         }
                     });
                 }

                 //启动连接获取数据
                 sdk.startCommunicate(Temp.this, contecDevice, communicateCallback);
             }
         }

         @Override
         public void onSearchComplete() {

             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     textView.append("Search End" + "\n");
                 }
             });

         }
     };


     CommunicateCallback communicateCallback = new CommunicateCallback() {


         @Override
         public void onCommunicateSuccess(final ResultData data) {

             final StringBuffer sstb = new StringBuffer();

             sstb.append("Number：").append(data.number).append(",State：").append(data.retValState);
             if (data.temp != null) {
                 sstb.append(",Temp：").append(data.temp).append("\n");
             } else {
                 sstb.append(",State：").append(data.retValState).append(",Error：").append(data.error).append("\n");
             }

             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     textView.append(sstb.toString() + "\n");
                 }
             });

         }

         @Override
         public void onCommunicateFailed(final int errorCode) {


             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     textView.append("Failed :" + errorCode + "-" + getResources().getStringArray(R.array.blecommunicate_failed_messages)[errorCode] + "\n");
                 }
             });
         }

         @Override
         public void onCommunicateProgress(final int status) {

             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {

                     textView.append("Progress = " + status + "-" + getResources().getStringArray(R.array.communicate_progresses)[status] + "\n");
                 }
             });
         }
     };
}

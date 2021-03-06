package com.decard.mobilesdkexample.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.decard.NDKMethod.BasicOper;
import com.decard.NDKMethod.SSCardDriver;
import com.decard.entitys.IDCard;
import com.decard.mobilesdkexample.ListenerUtils.MsgInfoListener;
import com.decard.mobilesdkexample.ListenerUtils.Scan2DListener;
import com.decard.mobilesdkexample.OperaUtils.Card102Util;
import com.decard.mobilesdkexample.OperaUtils.Card4428Util;
import com.decard.mobilesdkexample.OperaUtils.Card4442Util;
import com.decard.mobilesdkexample.OperaUtils.ContactCpuUtil;
import com.decard.mobilesdkexample.OperaUtils.ContactlessCpuUtil;
import com.decard.mobilesdkexample.OperaUtils.Fm11rf005CardUtil;
import com.decard.mobilesdkexample.OperaUtils.IDCardUtil;
import com.decard.mobilesdkexample.OperaUtils.M1CardUtil;
import com.decard.mobilesdkexample.OperaUtils.MagCardUtil;
import com.decard.mobilesdkexample.OperaUtils.PrintUtil;
import com.decard.mobilesdkexample.OperaUtils.SSCardUtil;
import com.decard.mobilesdkexample.OperaUtils.Scan2DUtil;
import com.decard.mobilesdkexample.OperaUtils.UltralightUtil;
import com.decard.mobilesdkexample.R;

import java.util.IdentityHashMap;

public class CardOperaActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "CARD_OPERA_ACTIVITY_LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_opera);

        findViewById(R.id.contact_cpu_btn).setOnClickListener(this);
        findViewById(R.id.contactless_cpu_btn).setOnClickListener(this);
        findViewById(R.id.m1_card_btn).setOnClickListener(this);
        findViewById(R.id.ultralight_card_btn).setOnClickListener(this);
        findViewById(R.id.card_4428_btn).setOnClickListener(this);
        findViewById(R.id.card_4442_btn).setOnClickListener(this);
        findViewById(R.id.card_102_btn).setOnClickListener(this);
        findViewById(R.id.mag_card_btn).setOnClickListener(this);
        findViewById(R.id.ID_card_btn).setOnClickListener(this);
        findViewById(R.id.SS_card_btn).setOnClickListener(this);
        findViewById(R.id.Scan_2D_btn).setOnClickListener(this);
        findViewById(R.id.Print_picture_btn).setOnClickListener(this);
        findViewById(R.id.Fm11rf005_card_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_cpu_btn:  //?????????CPU???
                String cpuInfo = ContactCpuUtil.read_contact_card_info();
                Log.e(TAG, "R.id.contact_cpu_btn: " + cpuInfo);
                Toast.makeText(this, "?????????CPU?????????????????? " + cpuInfo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.contactless_cpu_btn: //??????CPU???
                String RfInfo = ContactlessCpuUtil.read_discontacy_card_info();
                Log.e(TAG, "R.id.contactless_cpu_btn: " + RfInfo);
                Toast.makeText(this, "??????CPU?????????????????? " + RfInfo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.m1_card_btn:      //  M1???
                String M1Info = M1CardUtil.load_M1_key(1, 0, 6, "FFFFFFFFFFFF", 27);
                Toast.makeText(this, "M1?????????????????? " + M1Info, Toast.LENGTH_SHORT).show();
//                M1CardUtil.write_M1_card(1, 0, 15, 61);
                break;
            case R.id.ultralight_card_btn:      //ultralight???
                String ultralightInfo = UltralightUtil.operaUltralight(0, "passward", 7);
                Toast.makeText(this, "ultralight?????????????????? " + ultralightInfo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_4428_btn:     //4428???
                String info4428 = Card4428Util.card4428();
                Toast.makeText(this, "4428?????????????????? " + info4428, Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_4442_btn:     //4442???
                String info4442 = Card4442Util.card4442();
                Toast.makeText(this, "4428?????????????????? " + info4442, Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_102_btn:     //102???
                String card102 = Card102Util.card102((short) 0,"FF",5,10);
                Toast.makeText(this, "102?????????????????? "+card102, Toast.LENGTH_SHORT).show();
                break;
            case R.id.mag_card_btn:     //?????????
                final String MsgInfo = "";
                int ret = MagCardUtil.read_mag_card(0, new MsgInfoListener() {
                    @Override
                    public void getMsgInfo(String info) {
                        Log.e(TAG, "???????????????: " + info);
                    }
                });
                if (ret == 0) {
                    Toast.makeText(this, "??????????????????????????? ", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "?????????????????????:??????", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ID_card_btn:

//                short retRaw = IDCardUtil.getSamAReadCardInfoRaw(1);
//                Log.d(TAG, "raw: "+retRaw);
//                String retInfo = IDCardUtil.dc_IdCardReadCardInfoString();
//                Log.d(TAG, "info: "+retInfo);
//                Toast.makeText(this, "??????????????????????????? "+retInfo, Toast.LENGTH_SHORT).show();
//                IDCardUtil.getIdInfo();
                IDCard idCard = IDCardUtil.getIdCard(1);
                if (idCard!= null) {
                    Toast.makeText(this, "??????????????????????????? ", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "??????????????????????????? ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SS_card_btn:      //?????????
                String ssCardInfo = SSCardUtil.getSSCardInfo();
                Toast.makeText(this, "????????????????????? " + ssCardInfo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.Scan_2D_btn:      //???????????????
                int scanResult = Scan2DUtil.scan_2D(new Scan2DListener() {
                    @Override
                    public void getScan2DInfo(String info) {
                        Log.e(TAG, "?????????????????????: " + info);
                    }
                });
                if (scanResult == 0) {
                    Toast.makeText(this, "??????????????????????????? ", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "??????????????????????????? ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Print_picture_btn:    //????????????
                int printResult = PrintUtil.printFactor(2, getResources());
                if (printResult == 0) {
                    Toast.makeText(this, "?????????????????? ", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "?????????????????? ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Fm11rf005_card_btn:
                String fm11 = Fm11rf005CardUtil.getFm11rf005Info(0, 10);
                Log.e(TAG, "R.id.Fm11rf005_card_btn: " + fm11);
                Toast.makeText(this, "Fm11rf005????????????: " + fm11, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
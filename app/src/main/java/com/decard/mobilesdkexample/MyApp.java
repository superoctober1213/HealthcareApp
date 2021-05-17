package com.decard.mobilesdkexample;

import com.decard.DcLogAnUtil.DeCardApp;
import com.decard.mobilesdkexample.BaseClass.GetAccountInfoResponse;
import com.decard.mobilesdkexample.BaseClass.GetCustomerInfoResponse;

public class MyApp extends DeCardApp {

    GetAccountInfoResponse currAccount;
    GetCustomerInfoResponse currCustomer;
    @Override
    public void onCreate() {
        super.onCreate();
    }
}

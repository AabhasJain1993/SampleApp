package com.express.olaplayapp;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.express.olaplayapp.utils.AppLifeCycleHandler;
import com.express.olaplayapp.utils.NetworkUtil;
import com.express.olaplayapp.utils.StethoUtils;

/**
 * Created by root on 1/12/17.
 */

public class OlaPlayApp extends Application {
    private AppLifeCycleHandler appLifeCycleHandler = new AppLifeCycleHandler();
    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            appLifeCycleHandler.checkInternet(OlaPlayApp.this);
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    private void initialize() {
        StethoUtils.install(this);
        NetworkUtil.updateConnectivityStatus(this);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(networkChangeReceiver, new IntentFilter(NetworkUtil.LOCAL_INTENT_FILTER));
        appLifeCycleHandler.checkInternet(this);
    }
}

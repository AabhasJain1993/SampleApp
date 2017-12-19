package com.express.olaplayapp;

import android.app.Application;

import com.express.olaplayapp.utils.StethoUtils;

/**
 * Created by root on 1/12/17.
 */

public class OlaPlayApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    private void initialize() {
        StethoUtils.install(this);
    }
}

package com.express.olaplayapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.express.olaplayapp.utils.NetworkUtil;

/**
 * Created by Aabhas_Jain on 12/19/2017.
 */

public class RouterActivity extends AppCompatActivity {

    private boolean mIsfromNoInternetActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    void initialize() {
        mIsfromNoInternetActivity = getIntent().getBooleanExtra("FromNoInternetActivity", false);
    //    if(mIsfromNoInternetActivity) {
            if (NetworkUtil.isInternetConnected(false) ) {
    //			LocalBroadcastManager.getInstance(this).unregisterReceiver(networkChangeReceiver);
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
//        } else {
//            Intent intent = new Intent(this, SplashActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            finish();
//
//        }
    }
}

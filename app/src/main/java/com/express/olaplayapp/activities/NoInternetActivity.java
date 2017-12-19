package com.express.olaplayapp.activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.express.olaplayapp.R;
import com.express.olaplayapp.utils.NetworkUtil;
import com.express.olaplayapp.widget.TimedProgressDialog;


import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoInternetActivity extends AppCompatActivity {
	private TimedProgressDialog progress;

	private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			checkInternet();
		}
	};

	private void checkInternet() {
		if (NetworkUtil.isInternetConnected(false)) {
//			LocalBroadcastManager.getInstance(this).unregisterReceiver(networkChangeReceiver);
//			Intent intent = new Intent(this, RouterActivity.class);
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//			startActivity(intent);
//			finish();
			Intent intent = new Intent(this, RouterActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			intent.putExtra("FromNoInternetActivity", true);
			startActivity(intent);
			finish();
		}
	}

    View reportBtn;

	@Bind(R.id.no_internet_msg)
    TextView noInternetMsg;

    @Bind(R.id.retry_btn)
    TextView retryButton;

	@OnClick(R.id.retry_btn)
	void retry() {
		progress.show(2, TimeUnit.SECONDS);
		NetworkUtil.updateConnectivityStatus(this);
		checkInternet();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_no_internet);
		ButterKnife.bind(this);
		progress = new TimedProgressDialog(this);
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setIndeterminate(true);
		progress.setMessage(getString(R.string.loading));
		progress.setCancelable(false);
	}

	@Override
	public void onPause() {
		super.onPause();
		if ((progress != null) && progress.isShowing())
			progress.dismiss();
	}

	@StringRes
	private int getErrorMsg() {
			return R.string.internet_not_connected;
	}

	@Override
	protected void onStart() {
		super.onStart();
		LocalBroadcastManager.getInstance(this)
			.registerReceiver(networkChangeReceiver, new IntentFilter(NetworkUtil.LOCAL_INTENT_FILTER));
	}

	@Override
	protected void onStop() {
		super.onStop();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(networkChangeReceiver);
	}
}

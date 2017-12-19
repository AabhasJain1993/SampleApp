package com.express.olaplayapp.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.express.olaplayapp.activities.NoInternetActivity;


public class AppLifeCycleHandler implements Application.ActivityLifecycleCallbacks {
	// I use two separate variables here. You can, of course, just use one and
	// increment/decrement it instead of using two and incrementing both.
	private int resumed;
	private int paused;

	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
	}

	public void onActivityDestroyed(Activity activity) {
	}

	public void onActivityResumed(Activity activity) {
		Log.d("onActivityResumed : ",activity.getLocalClassName());
		++resumed;
		if (!(activity instanceof NoInternetActivity)) {
			checkInternet(activity);
		}
	}

	public void onActivityPaused(Activity activity) {
		Log.d("onActivityPaused : " ,activity.getLocalClassName());
		++paused;
	}

	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
	}

	public void onActivityStarted(Activity activity) {
	}

	public void onActivityStopped(Activity activity) {
	}

	public boolean isApplicationInForeground() {
		return resumed > paused;
	}

	public void checkInternet(Context context) {
		//isApplicationInForeground()
		if ( !NetworkUtil
			.isInternetConnected(false)) {
			Log.d("Check Internet","NoInternetActivity");
			Intent noInternetIntent = new Intent(context, NoInternetActivity.class);
			noInternetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			context.startActivity(noInternetIntent);
		}
	}
}


package com.express.olaplayapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
	private NetworkUtil() {
	}

	private static ConnectivityStatus connectivityStatus;

	public static ConnectivityStatus getConnectivityStatus() {
		return connectivityStatus;
	}

	public static final String LOCAL_INTENT_FILTER = "com.express.olaplayapp.LOCAL_INTENT";
	public static final String COMMAND = "COMMAND";
	public static final int NETWORK_CHANGED = 3;
	public static final String NETWORK_STATE = "NETWORK_STATE";

	public enum ConnectivityStatus {
		TYPE_WIFI(0),
		TYPE_MOBILE(1),
		TYPE_NOT_CONNECTED(2);

		private int val;

		ConnectivityStatus(int val) {
			this.val = val;
		}

		public int getVal() {
			return val;
		}

		public static ConnectivityStatus getEnum(int val) {
			switch (val) {
			case 0:
				return TYPE_WIFI;
			case 1:
				return TYPE_MOBILE;
			case 2:
				return TYPE_NOT_CONNECTED;
			}
			return null;
		}
	}

	//return true if status is changed
	public static ConnectivityStatus updateConnectivityStatus(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
			.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (null != activeNetwork) {
			if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
				return connectivityStatus = ConnectivityStatus.TYPE_WIFI;
			}

			if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
				return connectivityStatus = ConnectivityStatus.TYPE_MOBILE;
			}
		}
		return connectivityStatus = ConnectivityStatus.TYPE_NOT_CONNECTED;
	}

	public static boolean isInternetConnected(boolean isWifiOnlyEnabled) {
		if (connectivityStatus != null && connectivityStatus != ConnectivityStatus.TYPE_NOT_CONNECTED) {
			if (isWifiOnlyEnabled) {
				if (connectivityStatus == ConnectivityStatus.TYPE_WIFI) {
					return true;
				}
			}
			else {
				return true;
			}
		}
		return false;
	}
}

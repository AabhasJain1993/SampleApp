package com.express.olaplayapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.express.olaplayapp.utils.NetworkUtil;



public class NetworkChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, final Intent intent) {
		NetworkUtil.ConnectivityStatus oldStatus = NetworkUtil.getConnectivityStatus();
		if (oldStatus != NetworkUtil.updateConnectivityStatus(context)) {
			Log.d("Network State Changed: ", NetworkUtil.getConnectivityStatus()+"");
			broadcastNetworkChanged(context, NetworkUtil.getConnectivityStatus().getVal());
		}
	}

	private void broadcastNetworkChanged(Context context, int networkState) {
		Intent intent = new Intent(NetworkUtil.LOCAL_INTENT_FILTER);
		intent.putExtra(NetworkUtil.COMMAND, NetworkUtil.NETWORK_CHANGED);
		intent.putExtra(NetworkUtil.NETWORK_STATE, networkState);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
	}
}
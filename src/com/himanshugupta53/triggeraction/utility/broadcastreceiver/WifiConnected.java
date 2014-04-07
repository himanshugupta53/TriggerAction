package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.himanshugupta53.triggeraction.utility.WifiCustomManager;


public class WifiConnected extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		if (WifiCustomManager.getInstance().isNetworkConnected()){
			String connectedSSID = WifiCustomManager.getInstance().getConnectedWifiSSID();
    	}
	}

}

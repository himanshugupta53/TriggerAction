package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.MyUserPreferences;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

public class WifiStateChangedReceiver extends BroadcastReceiver {

	static BroadcastReceiver wifiStateChangedReceiver = null;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		MyUserPreferences.setContext(context);
		int extraWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
    	switch(extraWifiState){
    		case WifiManager.WIFI_STATE_ENABLED:
    			TriggerActionParser.performTriggerAction(TriggerModelGroup.WIFI_SWITCHED_ON, null, context);
    			break;
    		case WifiManager.WIFI_STATE_DISABLED:
    			TriggerActionParser.performTriggerAction(TriggerModelGroup.WIFI_SWITCHED_OFF, null, context);
    			break;
    	}
	}
	
	public static void registerBroadcastReceiver(Context context){
		if (wifiStateChangedReceiver == null)
			wifiStateChangedReceiver = new WifiStateChangedReceiver();
		context.registerReceiver(wifiStateChangedReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
	}

}

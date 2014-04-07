package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import com.himanshugupta53.triggeraction.action.ActionModelGroup;
import com.himanshugupta53.triggeraction.utility.Config;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

public class WifiStateChangedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		int extraWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
    	switch(extraWifiState){
    		case WifiManager.WIFI_STATE_ENABLED:
    			if (Config.action == ActionModelGroup.BLUETOOTH_SWITCH_ON){
    				BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();    
    			    mBluetoothAdapter.enable(); 
    			}
    			break;
    		case WifiManager.WIFI_STATE_DISABLED:
    			if (Config.action == ActionModelGroup.BLUETOOTH_SWITCH_ON){
    				BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();    
    			    mBluetoothAdapter.enable(); 
    			}
    			break;
    	}
	}

}

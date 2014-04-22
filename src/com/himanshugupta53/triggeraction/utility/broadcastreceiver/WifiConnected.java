package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;
import com.himanshugupta53.triggeraction.utility.WifiCustomManager;


public class WifiConnected extends BroadcastReceiver {

	static BroadcastReceiver wifiConnectedReceiver = null;
	String connectedSSID = null;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
		if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
			if (networkInfo.isConnected()){
				TriggerActionParser.performTriggerAction(TriggerModelGroup.WIFI_CONNECTED_TO_ANY_NETWORK, null, context);
				connectedSSID = WifiCustomManager.getInstance(context).getConnectedWifiSSID();
				List<String> connectedSSIDList = new ArrayList<String>();
				connectedSSIDList.add(connectedSSID);
				TriggerActionParser.performTriggerAction(TriggerModelGroup.WIFI_CONNECTED_TO_SPECIFIC_NETWORK, connectedSSIDList, context);
			}
			else{
				TriggerActionParser.performTriggerAction(TriggerModelGroup.WIFI_DISCONNECTED_FROM_ANY_NETWORK, null, context);
				List<String> connectedSSIDList = null;
				if (connectedSSID != null){
					connectedSSIDList = new ArrayList<String>();
					connectedSSIDList.add(connectedSSID);
					connectedSSID = null;
				}
				TriggerActionParser.performTriggerAction(TriggerModelGroup.WIFI_DISCONNECTED_FROM_SPECIFIC_NETWORK, connectedSSIDList, context);
			}
		}
		else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
			if (networkInfo.isConnected()){
				TriggerActionParser.performTriggerAction(TriggerModelGroup.DATA_CONNECTION_CONNECTED, null, context);
			}
			else{
				TriggerActionParser.performTriggerAction(TriggerModelGroup.DATA_CONNECTION_DISCONNECTED, null, context);
			}
		}
	}
	
	public static void registerBroadcastReceiver(Context context){
		if (wifiConnectedReceiver == null)
			wifiConnectedReceiver = new WifiConnected();
		context.registerReceiver(wifiConnectedReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
	}

}

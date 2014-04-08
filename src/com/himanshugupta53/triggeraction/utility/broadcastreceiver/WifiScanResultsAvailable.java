package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import com.himanshugupta53.triggeraction.trigger.TriggerDialog;
import com.himanshugupta53.triggeraction.utility.WifiCustomManager;

public class WifiScanResultsAvailable extends BroadcastReceiver {

	public List<String> accessPts;
	TriggerDialog triggerDialog;
	
	public WifiScanResultsAvailable(TriggerDialog dialog){
		super();
		triggerDialog = dialog;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction() == WifiManager.SCAN_RESULTS_AVAILABLE_ACTION){
    		List listOfAccessPts = WifiCustomManager.getInstance(context).getWifiAccessPoints();
    		int size = listOfAccessPts.size();
    		String[] temp = new String[size];
    		int length=0;
    		for (int i=0; i<size; i++){
    			ScanResult res = (ScanResult) listOfAccessPts.get(i);
    			if (res.SSID != null && !res.SSID.equals("")){
    				temp[length++] = res.SSID;
    			}
   			}
    		accessPts = new ArrayList<String>();
    		for (int j=0; j<length; j++){
    			accessPts.add(temp[j]);
    		}
    		triggerDialog.finishActivity();
    	}
	}

}

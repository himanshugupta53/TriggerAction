package com.himanshugupta53.triggeraction.utility;

import java.util.List;

import android.content.Context;
import android.net.wifi.WifiManager;

public class WifiCustomManager{

	private Context context;
	private WifiManager wifiManager;
	private static WifiCustomManager instance;
	
	private WifiCustomManager(Context _context, WifiManager _wifiManager) {
		context = _context;
		wifiManager = _wifiManager;
	}
	
	public static WifiCustomManager getInstance(Context context){
		if (instance == null && context != null){
			instance = new WifiCustomManager(context, (WifiManager) context.getSystemService(Context.WIFI_SERVICE));
		}
		return instance;
	}
	
	public static WifiCustomManager getInstance(){
		return getInstance(null);
	}

	public void startScan(){
		wifiManager.startScan();
	}
	
	public List getWifiAccessPoints(){
		List results = wifiManager.getScanResults();
		return results;
	}

}

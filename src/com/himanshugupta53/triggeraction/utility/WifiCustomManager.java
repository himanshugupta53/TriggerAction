package com.himanshugupta53.triggeraction.utility;

import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiCustomManager{

	private Context context;
	private WifiManager wifiManager;
	private ConnectivityManager connManager = null;
	private static WifiCustomManager instance;
	
	private WifiCustomManager(Context _context, WifiManager _wifiManager, ConnectivityManager _connManager) {
		context = _context;
		wifiManager = _wifiManager;
		connManager = _connManager;
	}
	
	public static WifiCustomManager getInstance(Context context){
		if (instance == null && context != null){
			instance = new WifiCustomManager(context, (WifiManager) context.getSystemService(Context.WIFI_SERVICE), (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
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
	
	public boolean isNetworkConnected(){
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		boolean isConnected = mWifi.isConnected(); 
		return isConnected;
	}
	
	public void enableWifi(){
		wifiManager.setWifiEnabled(true);;
	}
	
	public void disableWifi(){
		wifiManager.setWifiEnabled(false);;
	}
	
	public String getConnectedWifiSSID(){
		String ssid = "none";
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		ssid = wifiInfo.getSSID();
		return ssid;
	}

}

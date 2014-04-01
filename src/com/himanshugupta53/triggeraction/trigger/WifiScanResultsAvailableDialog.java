package com.himanshugupta53.triggeraction.trigger;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.widget.ListView;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.utility.DialogListArrayAdapter;
import com.himanshugupta53.triggeraction.utility.WifiCustomManager;
import com.himanshugupta53.triggeraction.utility.broadcastreceiver.WifiScanResultsAvailable;

public class WifiScanResultsAvailableDialog extends TriggerDialog {

	private Activity context;
	List<String> wifiScanResult;
	WifiScanResultsAvailable wifiScanReceiver;
	
	public WifiScanResultsAvailableDialog(Context _context) {
		super(_context);
		context = (Activity)_context;
		setLayout(R.layout.wifi_scan_result_dialog);
		wifiScanResult = new ArrayList<String>();
	}
	
	public void setWifiScanResult(List<String> res){
		wifiScanResult = res;
	}
	
	public void startActivity(){
		wifiScanReceiver = new WifiScanResultsAvailable(this);
		context.registerReceiver(wifiScanReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		WifiCustomManager.getInstance(context).startScan();
	}
	
	public void finishActivity(){
		List<String> list = wifiScanReceiver.accessPts;
		((TriggerActivity)context).hideProgressDialog(list);
	}
	
	@Override
	public void setValuesToLayoutFields() {
		DialogListArrayAdapter adapter = null;
		List<String> strVals = new ArrayList<String>();
		strVals.add("abc");
		strVals.add("def");
		strVals.add("ghi");
		strVals.add("abc");
		strVals.add("def");
		strVals.add("ghi");
		strVals.add("abc");
		strVals.add("def");
		strVals.add("ghi");
		strVals.add("abc");
		strVals.add("def");
		strVals.add("ghi");
		
		adapter = new DialogListArrayAdapter(context, wifiScanResult);
		//adapter.setTMGMap(map);
		ListView lV = (ListView) findViewById(R.id.wifiScanResultList);
		lV.setAdapter(adapter);
	}

}

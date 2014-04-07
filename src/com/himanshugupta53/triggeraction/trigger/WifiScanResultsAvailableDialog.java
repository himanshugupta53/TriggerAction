package com.himanshugupta53.triggeraction.trigger;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.utility.Config;
import com.himanshugupta53.triggeraction.utility.DialogListArrayAdapter;
import com.himanshugupta53.triggeraction.utility.WifiCustomManager;
import com.himanshugupta53.triggeraction.utility.broadcastreceiver.WifiScanResultsAvailable;

public class WifiScanResultsAvailableDialog extends TriggerDialog implements OnClickListener{

	List<String> wifiScanResult;
	WifiScanResultsAvailable wifiScanReceiver;
	Button cancelButton, okButton;
	EditText wifiSSID;

	public WifiScanResultsAvailableDialog(Context _context) {
		super(_context);
		setLayout(R.layout.wifi_scan_result_dialog);
		wifiScanResult = new ArrayList<String>();
	}

	@Override
	public void show(){
		super.show();
		wifiSSID = (EditText) findViewById(R.id.selectedWifiSSID);
		cancelButton = (Button) findViewById(R.id.wifiScanResultCancelButton);
		okButton = (Button) findViewById(R.id.wifiScanResultOKButton);
		cancelButton.setOnClickListener(this);
		okButton.setOnClickListener(this);
	}
	
	public void setResult(Object res){
		wifiScanResult = (List<String>)res;
	}

	public void startActivity(){
		wifiScanReceiver = new WifiScanResultsAvailable(this);
		context.registerReceiver(wifiScanReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		WifiCustomManager.getInstance(context).startScan();
	}

	public void finishActivity(){
		if (wifiScanReceiver != null)
			context.unregisterReceiver(wifiScanReceiver);
		List<String> list = wifiScanReceiver.accessPts;
		((TriggerActivity)context).hideProgressDialog(list);
	}

	@Override
	public void setValuesToLayoutFields() {
		DialogListArrayAdapter adapter = null;
		adapter = new DialogListArrayAdapter(context, wifiScanResult);
		adapter.setClickListener(this);
		ListView lV = (ListView) findViewById(R.id.wifiScanResultList);
		lV.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		if (v == cancelButton){
			onBackPressed();
		}
		else if (v == okButton){
			Config.triggerVal1 = wifiSSID.getText();
			((TriggerActivity)context).goToActionActivity();
			onBackPressed();
		}
		else{
			RelativeLayout rL = (RelativeLayout)v;
			TextView tV = (TextView) rL.findViewById(R.id.textView);
			EditText editText = (EditText) findViewById(R.id.selectedWifiSSID);
			editText.setText(tV.getText());
		}
	}

}

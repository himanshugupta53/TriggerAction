package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

public class BluetoothStateChanged extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		boolean isBluetoothEnabled = intent.getBooleanExtra("bluetoothState", false);
		if (isBluetoothEnabled){
			TriggerActionParser.performTriggerAction(TriggerModelGroup.BLUETOOTH_SWITCHED_ON, null, context);
		}
		else{
			TriggerActionParser.performTriggerAction(TriggerModelGroup.BLUETOOTH_SWITCHED_OFF, null, context);
		}
	}

}

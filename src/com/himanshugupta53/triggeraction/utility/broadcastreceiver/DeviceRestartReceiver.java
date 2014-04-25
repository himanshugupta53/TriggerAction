package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.MyService;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

public class DeviceRestartReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		Intent intent= new Intent(context, MyService.class);
		context.startService(intent);
		
		TriggerActionParser.performTriggerAction(TriggerModelGroup.DEVICE_START, null, context);
	}

}

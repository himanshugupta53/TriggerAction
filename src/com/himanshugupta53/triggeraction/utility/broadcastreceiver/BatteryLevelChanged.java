package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BatteryLevelChanged extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction() == Intent.ACTION_BATTERY_LOW){
			TriggerActionParser.performTriggerAction(TriggerModelGroup.BATTERY_LEVEL_LOW, null, context);
		}
		else if(intent.getAction() == Intent.ACTION_BATTERY_OKAY){
			TriggerActionParser.performTriggerAction(TriggerModelGroup.BATTERY_LEVEL_OKAY, null, context);
		} 
	}

}

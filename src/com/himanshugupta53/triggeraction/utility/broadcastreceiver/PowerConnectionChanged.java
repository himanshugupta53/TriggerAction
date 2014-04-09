package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PowerConnectionChanged extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction() == Intent.ACTION_POWER_CONNECTED){
			TriggerActionParser.performTriggerAction(TriggerModelGroup.POWER_CONNECTED, null, context);
		}
		else if (intent.getAction() == Intent.ACTION_POWER_DISCONNECTED){
			TriggerActionParser.performTriggerAction(TriggerModelGroup.POWER_DISCONNECTED, null, context);
		}
	}

}

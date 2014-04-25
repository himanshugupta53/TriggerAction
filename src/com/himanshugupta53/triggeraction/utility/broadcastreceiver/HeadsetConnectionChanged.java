package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HeadsetConnectionChanged extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		boolean isHeadsetEnabled = intent.getBooleanExtra("headsetState", false);
		if (isHeadsetEnabled){
			TriggerActionParser.performTriggerAction(TriggerModelGroup.HEADSET_CONNECTED, null, context);
		}
		else{
			TriggerActionParser.performTriggerAction(TriggerModelGroup.HEADSET_DISCONNECTED, null, context);
		}		
		
	}

}

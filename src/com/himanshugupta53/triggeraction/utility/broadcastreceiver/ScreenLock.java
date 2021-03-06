package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenLock extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		boolean isPhoneLocked = intent.getBooleanExtra("phoneLockStatus", false);
		if (isPhoneLocked){
			TriggerActionParser.performTriggerAction(TriggerModelGroup.PHONE_LOCKED, null, context);
		}
		else{
			TriggerActionParser.performTriggerAction(TriggerModelGroup.PHONE_UNLOCKED, null, context);
		}
		
	}

}

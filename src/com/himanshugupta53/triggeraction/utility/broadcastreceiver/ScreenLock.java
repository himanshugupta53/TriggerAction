package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenLock extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			TriggerActionParser.performTriggerAction(TriggerModelGroup.PHONE_LOCKED, null);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
        	TriggerActionParser.performTriggerAction(TriggerModelGroup.PHONE_UNLOCKED, null);
        }
	}

}

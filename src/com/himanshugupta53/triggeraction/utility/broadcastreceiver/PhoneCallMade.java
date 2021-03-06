package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

public class PhoneCallMade extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		TriggerActionParser.performTriggerAction(TriggerModelGroup.PHONE_CALL_MADE, null, context);
	}

}

package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import java.util.List;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PhoneCallReceived extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		TriggerActionParser.performTriggerAction(TriggerModelGroup.PHONE_CALL_RECEIVED, null, context);
	}

}

package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

public class AlarmManagerReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String input = intent.getStringExtra("time");
		List<String> inputList = new ArrayList<String>();
		inputList.add(input);
		TriggerActionParser.performTriggerAction(TriggerModelGroup.TIME_AT, inputList, context);
	}

}

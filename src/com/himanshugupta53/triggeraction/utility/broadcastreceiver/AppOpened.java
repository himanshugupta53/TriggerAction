package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.MyService;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

public class AppOpened extends BroadcastReceiver {

	static BroadcastReceiver appOpenedReceiver = null;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String appPackageName = intent.getStringExtra("newlyOpenedApp");
		TriggerActionParser.performTriggerAction(TriggerModelGroup.APP_OPENED_ANY, null, context);
		List<String> appList = new ArrayList<String>();
		appList.add(appPackageName);
		TriggerActionParser.performTriggerAction(TriggerModelGroup.APP_OPENED_SPECIFIC, appList, context);
	}
	
	public static void registerBroadcastReceiver(Context context){
		Intent intent= new Intent(context, MyService.class);
		context.startService(intent); 
		if (appOpenedReceiver == null)
			appOpenedReceiver = new AppOpened();
		context.registerReceiver(appOpenedReceiver, new IntentFilter("AppOpened"));
	}

}

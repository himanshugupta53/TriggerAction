package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AppOpened extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String appPackageName = intent.getStringExtra("newlyOpenedApp");
		
	}

}

package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

public class GPSStateChanged extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		final LocationManager manager = (LocationManager) context.getSystemService( Context.LOCATION_SERVICE );
		if (manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
			TriggerActionParser.performTriggerAction(TriggerModelGroup.GPS_SWITCHED_ON, null);
		}
		else{
			TriggerActionParser.performTriggerAction(TriggerModelGroup.GPS_SWITCHED_OFF, null);
		}
		Log.i("TriggerAction", "abcdefghi - GPS");
	}

}

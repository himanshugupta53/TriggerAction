package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DataConnectionStateChanged extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Toast.makeText(arg0, "DataConnectionStateChanged receiver called", Toast.LENGTH_LONG).show();
	}

}

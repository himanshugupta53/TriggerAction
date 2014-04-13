package com.himanshugupta53.triggeraction.utility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.broadcastreceiver.AlarmManagerReceiver;

public class TaskScheduler extends Thread {

	boolean requiredForTimeManagement = false;
	MyService service;
	String input;
	
	public TaskScheduler(boolean b, MyService s, String _input){
		super();
		requiredForTimeManagement = b;
		service = s;
		input = _input;
	}

	@Override
	public void run() {
		if (!requiredForTimeManagement)
			return;
		
		Intent intent = new Intent(service, AlarmManagerReceiver.class);
		intent.putExtra("time", input);
	    PendingIntent pendingIntent = PendingIntent.getBroadcast(service, 0, intent, 0);
	    AlarmManager alarmManager = (AlarmManager) service.getSystemService(Context.ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC_WAKEUP, Long.parseLong(input), pendingIntent);
	    //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 10000, pendingIntent);
	}
}

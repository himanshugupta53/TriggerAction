package com.himanshugupta53.triggeraction.utility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.himanshugupta53.triggeraction.utility.broadcastreceiver.AlarmManagerReceiver;

public class TaskRepeatScheduler extends Thread {

	boolean requiredForTimeManagement = false;
	MyService service;
	String input;

	public TaskRepeatScheduler(boolean b, MyService s, String _input){
		super();
		requiredForTimeManagement = b;
		service = s;
		input = _input;
	}

	@Override
	public void run() {
		if (!requiredForTimeManagement)
			return;
		String[] inputs = input.split("\\|");
		long startingEpochTime = Long.parseLong(inputs[0]);
		String period = inputs[1];
		int timeOfPeriod = 0;
		if (period.equals("onetime")){
			timeOfPeriod = 60 * 1000;
		}
		else if (period.equals("hourly")){
			timeOfPeriod = 3600 * 1000;
		}
		else if (period.equals("daily")){
			timeOfPeriod = 24 * 3600 * 1000;
		}
		else if (period.equals("weekly")){
			timeOfPeriod = 7 * 24 * 3600 * 1000;
		}
		int noOfTimes = -1;
		if (timeOfPeriod == -1){
			noOfTimes = 1;
		}
		else if (inputs[2].equals("")){
			noOfTimes = 0;
		}
		else{
			noOfTimes = Integer.parseInt(inputs[2]);
		}
		
		AlarmManager alarmManager = (AlarmManager) service.getSystemService(Context.ALARM_SERVICE);
		if (noOfTimes == 0){
			Intent intent = new Intent(service, AlarmManagerReceiver.class);
			intent.putExtra("time", ""+startingEpochTime);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(service, 0, intent, 0);
			alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, startingEpochTime, timeOfPeriod, pendingIntent);
		}
		else{
			for (int i = 0; i < noOfTimes; i++){
				Intent intent = new Intent(service, AlarmManagerReceiver.class);
				intent.putExtra("time", ""+(startingEpochTime + i * timeOfPeriod));
				PendingIntent pendingIntent = PendingIntent.getBroadcast(service, i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				alarmManager.set(AlarmManager.RTC_WAKEUP, startingEpochTime + i * timeOfPeriod, pendingIntent);
			}
		}
	}
}

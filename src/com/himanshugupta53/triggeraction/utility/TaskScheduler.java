package com.himanshugupta53.triggeraction.utility;

import java.util.ArrayList;
import java.util.List;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.broadcastreceiver.AlarmManagerReceiver;

public class TaskScheduler extends Thread {

	boolean requiredForTimeManagement = false;
	MyService service;
	TriggerModelGroup trigger;
	String input;
	private static TaskScheduler taskScheduler = null;
	
	public static TaskScheduler getInstance(boolean b, MyService s, String _input, TriggerModelGroup _trigger){
		if (taskScheduler != null)
			taskScheduler.terminate();
		taskScheduler = new TaskScheduler(b, s, _input, _trigger);
		taskScheduler.initialize(b, s, _input, _trigger);
		return taskScheduler;
	}
	
	private TaskScheduler(boolean b, MyService s, String _input, TriggerModelGroup _trigger){
		super();
	}
	
	private void initialize(boolean b, MyService s, String _input, TriggerModelGroup _trigger){
		requiredForTimeManagement = b;
		service = s;
		trigger = _trigger;
		input = _input;
	}

	private void terminate(){
		requiredForTimeManagement = false;
	}
	
	@Override
	public void run() {
		if (!requiredForTimeManagement)
			return;
		
		if (trigger == TriggerModelGroup.TIME_FROM_TO){
			List<String> list = new ArrayList<String>();
			String[] strArray = input.split("\\|");
			for (String str : strArray){
				list.add(str);
			}
			List<TriggerActionParser> triggerActionList = TriggerActionParser.getSavedActionsForTrigger(trigger, list);
			for (TriggerActionParser triggerAction : triggerActionList){
				triggerAction.action.performAction(service, triggerAction.actionInputs);
			}
		}
		
		long currentTime = System.currentTimeMillis();
		Intent intent = new Intent(service, AlarmManagerReceiver.class);
		intent.putExtra("time", currentTime + "");
	    PendingIntent pendingIntent = PendingIntent.getBroadcast(service, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	    AlarmManager alarmManager = (AlarmManager) service.getSystemService(Context.ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC_WAKEUP, currentTime, pendingIntent);
	}
}

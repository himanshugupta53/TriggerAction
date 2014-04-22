package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import java.util.ArrayList;
import java.util.List;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

public class AlarmManagerReceiver extends BroadcastReceiver {

	long earliestTriggerTime = 0;
	TriggerActionParser earliestTriggerActionToPerform = null;
	
	public void setNextTrigger(TriggerActionParser triggerAction, long triggerTime){
		if (triggerTime < earliestTriggerTime){
			earliestTriggerActionToPerform = triggerAction;
			earliestTriggerTime = triggerTime;
		}
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Alarm baja", Toast.LENGTH_LONG).show();
		
		String input = intent.getStringExtra("time");
		List<String> inputList = new ArrayList<String>();
		inputList.add(input);
		earliestTriggerActionToPerform = null;
		earliestTriggerTime = Long.MAX_VALUE;
		List<TriggerActionParser> triggerActions = TriggerActionParser.getSavedActionsForTrigger(TriggerModelGroup.TIME_AT);
		long currentTime = Long.parseLong(input);
		for (TriggerActionParser triggerAction : triggerActions){
			long triggerTime = Long.parseLong(triggerAction.triggerInputs.get(0));
			if (triggerTime == currentTime){
				triggerAction.action.performAction(context, triggerAction.actionInputs);
				triggerAction.deleteFromUserPreferences();
			}
			else if (triggerTime < currentTime){
				triggerAction.deleteFromUserPreferences();
			}
			else{
				setNextTrigger(triggerAction, triggerTime);
			}
		}
		 
		triggerActions = TriggerActionParser.getSavedActionsForTrigger(TriggerModelGroup.TIME_AT_REPEAT);
		for (TriggerActionParser triggerAction : triggerActions){
			long nextTimeWhenToPerformAction = Long.parseLong(triggerAction.triggerInputs.get(0));
			if (nextTimeWhenToPerformAction > currentTime){
				setNextTrigger(triggerAction, nextTimeWhenToPerformAction);
				continue;
			}
			String period = triggerAction.triggerInputs.get(1);
			int noOfTimes = triggerAction.triggerInputs.size() == 2 ? 0 : Integer.parseInt(triggerAction.triggerInputs.get(2));
			boolean isInfiniteTimes = noOfTimes == 0;
			boolean deletedFromUserPreferences = false;
			while (nextTimeWhenToPerformAction <= currentTime){
				if (nextTimeWhenToPerformAction == currentTime && (isInfiniteTimes || noOfTimes > 0)){
					triggerAction.action.performAction(context, triggerAction.actionInputs);
				}
				if (!isInfiniteTimes){
					noOfTimes--;
				}
				if (period.equals("onetime") || (!isInfiniteTimes && noOfTimes <= 0)){
					triggerAction.deleteFromUserPreferences();
					deletedFromUserPreferences = true;
					break;
				}
				else if (period.equals("hourly")){
					nextTimeWhenToPerformAction += 3600 * 1000;
				}
				else if (period.equals("daily")){
					nextTimeWhenToPerformAction += 24 * 3600 * 1000;
				}
				else if (period.equals("weekly")){
					nextTimeWhenToPerformAction += 7 * 24 * 3600 * 1000;
				}
			}
			if (!deletedFromUserPreferences){
				setNextTrigger(triggerAction, nextTimeWhenToPerformAction);
				List<String> triggerInputs = new ArrayList<String>();
				triggerInputs.add(""+nextTimeWhenToPerformAction);
				triggerInputs.add(period);
				triggerInputs.add(""+noOfTimes);
				triggerAction.editInUserPreferences(triggerInputs, null, null);
			}
		}
		
		triggerActions = TriggerActionParser.getSavedActionsForTrigger(TriggerModelGroup.TIME_FROM_TO);
		for (TriggerActionParser triggerAction : triggerActions){
			long nextTimeWhenToPerformAction = Long.parseLong(triggerAction.triggerInputs.get(1));
			if (nextTimeWhenToPerformAction > currentTime){
				setNextTrigger(triggerAction, nextTimeWhenToPerformAction);
				continue;
			}
			else{
				triggerAction.action.performOppositeAction(context, triggerAction.actionInputs);
				triggerAction.deleteFromUserPreferences();
			}
		}
		
		if (earliestTriggerActionToPerform != null && earliestTriggerTime > currentTime){
			Intent alarmIntent = new Intent(context, AlarmManagerReceiver.class);
			alarmIntent.putExtra("time", earliestTriggerTime+"");
		    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		    alarmManager.set(AlarmManager.RTC_WAKEUP, earliestTriggerTime, pendingIntent);
		}
		
	}

}

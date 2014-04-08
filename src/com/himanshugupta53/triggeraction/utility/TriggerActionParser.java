package com.himanshugupta53.triggeraction.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;

import com.himanshugupta53.triggeraction.action.ActionModelGroup;
import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;

public class TriggerActionParser {

	public TriggerModelGroup trigger;
	public ActionModelGroup action;
	public List<String> triggerInputs;
	public List<String> actionInputs;

	public String serialize(){
		String strValue = "@TRIGGER:";
		strValue = strValue + trigger.toString();
		if (triggerInputs != null){
			for (Object obj : triggerInputs){
				strValue = strValue + "#" + obj.toString();
			}
		}
		strValue = strValue + "@ACTION:";
		strValue = strValue + action.toString();
		if (actionInputs != null){
			for (Object obj : actionInputs){
				strValue = strValue + "#" + obj.toString();
			}
		}
		return strValue;
	}

	public static TriggerActionParser deserialize(String strValue){
		if (strValue == null)
			return null;
		TriggerActionParser triggerAction = new TriggerActionParser();
		String[] strArray = strValue.split("@");
		String triggerStr = strArray[1].split(":")[1];
		String actionStr = strArray[2].split(":")[1];
		strArray = triggerStr.split("#");
		int iterator = 0;
		for (String str : strArray){
			if (iterator == 0){
				triggerAction.trigger = TriggerModelGroup.valueOf(strArray[iterator]);
				triggerAction.triggerInputs = new ArrayList<String>();
			}
			else{
				triggerAction.triggerInputs.add(str);
			}
			iterator++;
		}
		strArray = actionStr.split("#");
		iterator = 0;
		for (String str : strArray){
			if (iterator == 0){
				triggerAction.action = ActionModelGroup.valueOf(strArray[iterator]);
				triggerAction.actionInputs = new ArrayList<String>();
			}
			else{
				triggerAction.actionInputs.add(str);
			}
			iterator++;
		}
		return triggerAction;
	}

	public void saveInUserPreferences(){
		String key = trigger.toString();
		Set<String> triggerActions = MyUserPreferences.getStringSet(key);
		if (triggerActions == null)
			triggerActions = new HashSet<String>();
		triggerActions.add(serialize());
		MyUserPreferences.setStringSet(key, triggerActions);
	}

	public void performActionOnTrigger(Activity context){
		saveInUserPreferences();
		trigger.checkAndPerformTaskInBackgroundService(context);
//		trigger.registerBroadcastReceiver(context);
	}

	public static List<TriggerActionParser> getSavedActionsForTrigger(TriggerModelGroup trigger, List<String> triggerInputs){
		Set<String> stringSet = MyUserPreferences.getStringSet(trigger.toString());
		List<TriggerActionParser> list = new ArrayList<TriggerActionParser>();
		if (stringSet != null){
			for (String str : stringSet){
				TriggerActionParser tAP = deserialize(str);
				if (Utility.areListsEqual(tAP.triggerInputs, triggerInputs))
					list.add(tAP);
			}
		}
		return list;
	}
	
	public static void performTriggerAction(TriggerModelGroup trigger, List<String> triggerInputs){
		List<TriggerActionParser> triggerActions = getSavedActionsForTrigger(trigger, triggerInputs);
		for (TriggerActionParser triggerAction : triggerActions){
			triggerAction.action.performAction();
		}
	}

}

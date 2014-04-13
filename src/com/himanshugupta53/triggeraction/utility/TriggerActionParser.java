package com.himanshugupta53.triggeraction.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Context;

import com.himanshugupta53.triggeraction.action.ActionModelGroup;
import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;

public class TriggerActionParser {

	private String id;
	private String name;
	public TriggerModelGroup trigger;
	public ActionModelGroup action;
	public List<String> triggerInputs;
	public List<String> actionInputs;

	public String getId(){
		return trigger.toString() + "#" + System.currentTimeMillis() + "#" + action.toString();
	}
	
	public String getName(){
		return name;
	}
	
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
		strValue = strValue + "@ID:";
		strValue = strValue + getId();
		strValue = strValue + "@NAME:";
		if (name == null){
			name = "Untitled";
		}
		strValue = strValue + name;
		return strValue;
	}

	public static TriggerActionParser deserialize(String strValue){
		if (strValue == null)
			return null;
		TriggerActionParser triggerAction = new TriggerActionParser();
		String[] strArray = strValue.split("@");
		String triggerStr = strArray[1].split(":")[1];
		String actionStr = strArray[2].split(":")[1];
		String _id = strArray[3].split(":")[1];
		String _name = strArray[4].split(":")[1];
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
		triggerAction.id = _id;
		triggerAction.name = _name;
		return triggerAction;
	}

	public void deleteFromUserPreferences(){
		String key = trigger.toString();
		Set<String> triggerActions = MyUserPreferences.getStringSet(key);
		Set<String> triggers = MyUserPreferences.getStringSet(MyUserPreferences.triggerKey);
		if (triggerActions != null){
			for (Iterator<String> iterator = triggerActions.iterator(); iterator.hasNext();) {
			    String str = iterator.next();
			    TriggerActionParser t = deserialize(str);
			    if (t.id.equals(this.id)){
			    	iterator.remove();
			    }
			}
			MyUserPreferences.setStringSet(key, triggerActions);
		}
		if (triggerActions == null || triggerActions.size() == 0){
			triggers.remove(key);
			MyUserPreferences.setStringSet(MyUserPreferences.triggerKey, triggers);
		}
	}
	
	public void saveInUserPreferences(){
		String key = trigger.toString();
		Set<String> triggerActions = MyUserPreferences.getStringSet(key);
		Set<String> triggers = MyUserPreferences.getStringSet(MyUserPreferences.triggerKey);
		if (triggerActions == null){
			triggerActions = new HashSet<String>();
		}
		if (triggers == null){
			triggers = new HashSet<String>();
		}
		triggerActions.add(serialize());
		triggers.add(key);
		MyUserPreferences.setStringSet(key, triggerActions);
		MyUserPreferences.setStringSet(MyUserPreferences.triggerKey, triggers);
	}

	public void performActionOnTrigger(Activity context){
		saveInUserPreferences();
		trigger.checkAndPerformTaskInBackgroundService(context, this);
//		trigger.registerBroadcastReceiver(context);
	}

	public static List<TriggerActionParser> getSavedActionsForTrigger(TriggerModelGroup trigger){
		Set<String> stringSet = MyUserPreferences.getStringSet(trigger.toString());
		List<TriggerActionParser> list = new ArrayList<TriggerActionParser>();
		if (stringSet != null){
			for (String str : stringSet){
				TriggerActionParser tAP = deserialize(str);
				list.add(tAP);
			}
		}
		return list;
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
	
	public static void performTriggerAction(TriggerModelGroup trigger, List<String> triggerInputs, Context context){
		List<TriggerActionParser> triggerActions = getSavedActionsForTrigger(trigger, triggerInputs);
		for (TriggerActionParser triggerAction : triggerActions){
			triggerAction.action.performAction(context);
		}
	}
	
	public static TriggerActionParser[] getAllDefinedTriggerActions(){
		Set<String> triggers = MyUserPreferences.getStringSet(MyUserPreferences.triggerKey);
		if (triggers == null || triggers.size() == 0)
			return new TriggerActionParser[]{};
		Set<TriggerActionParser> finalSet = new HashSet<TriggerActionParser>();
		for (String key : triggers){
			Set<String> triggerActions = MyUserPreferences.getStringSet(key);
			if (triggerActions == null || triggerActions.size() == 0){
				continue;
			}
			for (String str : triggerActions){
				finalSet.add(deserialize(str));
			}
		}
		TriggerActionParser[] tAPArray = new TriggerActionParser[finalSet.size()];
		int i=0;
		for (TriggerActionParser tAP : finalSet){
			tAPArray[i++] = tAP;
		}
		return tAPArray;
		
	}

}

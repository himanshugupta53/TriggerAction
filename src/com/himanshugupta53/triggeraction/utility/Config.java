package com.himanshugupta53.triggeraction.utility;

import java.util.ArrayList;
import java.util.List;

import com.himanshugupta53.triggeraction.action.ActionModelGroup;
import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;

public class Config {
	public static TriggerModelGroup trigger;
	public static ActionModelGroup action;
	private static List<String> triggerInputs, actionInputs;
	
	public static void addTriggerInput(String str){
		if (triggerInputs == null)
			triggerInputs = new ArrayList<String>();
		triggerInputs.add(str);
	}
	
	public static void addActionInput(String str){
		if (actionInputs == null)
			actionInputs = new ArrayList<String>();
		actionInputs.add(str);
	}
	
	public static List<String> getTriggerInputs(){
		return triggerInputs;
	}
	
	public static List<String> getActionInputs(){
		return actionInputs;
	}
}

package com.himanshugupta53.triggeraction.trigger.groups;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;

public class WIFI {

	public String getTitle(){
		return "Wifi";
	}
	
	public String getDescription(){
		return "Define a trigger point on switching on/off your wifi";
	}
	
	public TriggerModelGroup[] getListOfItems(){
		return TriggerModelGroup.getTriggersOfGroup("WIFI");
	}
}

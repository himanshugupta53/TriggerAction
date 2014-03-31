package com.himanshugupta53.triggeraction.trigger;

public enum TriggerModelGroup {
	WIFI_SWITCHED_ON("WIFI", 0),
	WIFI_SWITCHED_OFF("WIFI", 0),
	WIFI_CONNECTED_TO_ANY_NETWORK("WIFI", 0),
	WIFI_DISCONNECTED_FROM_ANY_NETWORK("WIFI", 0),
	WIFI_CONNECTED_TO_SPECIFIC_NETWORK("WIFI", 1),
	WIFI_DISCONNECTED_FROM_SPECIFIC_NETWORK("WIFI", 1),
	BLUETOOTH_SWITCHED_ON("BLUETOOTH", 0),
	BLUETOOTH_SWITCHED_OFF("BLUETOOTH", 0),
	BATTERY_LEVEL_DIPS_TO("BATTERY", 1),
	BATTERY_LEVEL_RISES_TO("BATTERY", 1),
	SMS_RECIEVED("SMS", 0),
	SMS_SENT("SMS", 0),
	PHONE_CALL_RECEIVED("PHONECALL", 0),
	PHONE_CALL_MADE("PHONECALL", 0),
	PHONE_LOCKED("LOCK", 0),
	PHONE_UNLOCKED("LOCK", 0),
	APP_OPENED_ANY("APP", 0),
	APP_OPENED_SPECIFIC("APP", 1),
	GPS_SWITCHED_ON("GPS", 0),
	GPS_SWITCHED_OFF("GPS", 0),
	TIME_AT("TIME", 1),
	TIME_FROM_TO("TIME", 2);
	
	private String groupName = null;
	private int noOfInputs = 0;
	
	private TriggerModelGroup(String grpName, int num){
		groupName = grpName;
		noOfInputs = num;
	}
	
	public String getGroupName(){
		return groupName;
	}
	
	public int getNoOfInputs(){
		return noOfInputs;
	}
	
	public TriggerModelGroup[] getTriggersOfGroup(String grp){
		if (grp == null)
			return null;
		TriggerModelGroup[] values = TriggerModelGroup.values();
		int count = 0;
		for (TriggerModelGroup t : values){
			if (t.groupName.equals(grp))
				count++;
		}
		TriggerModelGroup[] tmg = new TriggerModelGroup[count];
		int i=0;
		for (TriggerModelGroup t : values){
			if (t.groupName.equals(grp))
				tmg[i++] = t;
		}
		return tmg;
	}
	
}

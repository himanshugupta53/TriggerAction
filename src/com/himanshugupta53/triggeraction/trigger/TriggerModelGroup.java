package com.himanshugupta53.triggeraction.trigger;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.utility.CustomDialog;

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
	SMS_RECEIVED("SMS", 0),
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

	private String groupName = null, title = null, description = null, fulldescription = null;
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

	public String getTitle(Activity context){
		if (title == null){
			int var = -1;
			try {
				var = R.string.class.getField(this.getGroupName().toLowerCase() + "_trigger_title").getInt(null);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (var == -1)
				return "";
			title = context.getString(var);
		}
		return title;
	}

	public String getDescription(Activity context){
		if (description == null){
			int var = -1;
			try {
				var = R.string.class.getField(this.getGroupName().toLowerCase() + "_trigger_description").getInt(null);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (var == -1)
				return "";
			description = context.getString(var);
		}
		return description;
	}
	
	public String getFullDescription(Activity context){
		if (fulldescription == null){
			int var = -1;
			try {
				var = R.string.class.getField(this.toString().toLowerCase() + "_trigger_fulldescription").getInt(null);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (var == -1)
				return "";
			fulldescription = context.getString(var);
		}
		return fulldescription;
	}

	public static TriggerModelGroup[] getTriggersOfGroup(String grp){
		if (grp == null)
			return null;
		TriggerModelGroup[] values = TriggerModelGroup.values();
		int count = 0;
		for (TriggerModelGroup t : values){
			if (t.getGroupName().equals(grp))
				count++;
		}
		TriggerModelGroup[] tmg = new TriggerModelGroup[count];
		int i=0;
		for (TriggerModelGroup t : values){
			if (t.getGroupName().equals(grp))
				tmg[i++] = t;
		}
		return tmg;
	}

	public static String getTitleOfGroup(Activity context, String grp){
		TriggerModelGroup[] values = TriggerModelGroup.values();
		for (TriggerModelGroup t : values){
			if (t.getGroupName().equals(grp)){
				return t.getTitle(context);
			}
		}
		return "";
	}

	public static String getDescriptionOfGroup(Activity context, String grp){
		TriggerModelGroup[] values = TriggerModelGroup.values();
		for (TriggerModelGroup t : values){
			if (t.getGroupName().equals(grp)){
				return t.getDescription(context);
			}
		}
		return "";
	}

	public static Set<String> getGroupStrings(){
		TriggerModelGroup[] values = TriggerModelGroup.values();
		Set <String> groups = new HashSet<String>();
		for (TriggerModelGroup t : values){
			groups.add(t.getGroupName());
		}
		return groups;
	}
	
	public TriggerDialog getDialogPopup(Activity context){
		if (this.getNoOfInputs() == 0){
			return null;
		}
		switch(this){
		
		case WIFI_CONNECTED_TO_SPECIFIC_NETWORK:
			return new WifiScanResultsAvailableDialog(context);
		case WIFI_DISCONNECTED_FROM_SPECIFIC_NETWORK:
		case BATTERY_LEVEL_DIPS_TO:
		case BATTERY_LEVEL_RISES_TO:
		case APP_OPENED_SPECIFIC:
		case TIME_AT:
		case TIME_FROM_TO:
		
		case WIFI_SWITCHED_ON:
		case WIFI_SWITCHED_OFF:
		case WIFI_CONNECTED_TO_ANY_NETWORK:
		case WIFI_DISCONNECTED_FROM_ANY_NETWORK:
		case BLUETOOTH_SWITCHED_ON:
		case BLUETOOTH_SWITCHED_OFF:
		case SMS_RECEIVED:
		case SMS_SENT:
		case PHONE_CALL_RECEIVED:
		case PHONE_CALL_MADE:
		case PHONE_LOCKED:
		case PHONE_UNLOCKED:
		case APP_OPENED_ANY:
		case GPS_SWITCHED_ON:
		case GPS_SWITCHED_OFF:
			default:
				return null;
			
		}
	}

}

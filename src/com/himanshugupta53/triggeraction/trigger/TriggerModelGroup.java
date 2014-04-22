package com.himanshugupta53.triggeraction.trigger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.utility.MyService;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;
import com.himanshugupta53.triggeraction.utility.broadcastreceiver.AppOpened;
import com.himanshugupta53.triggeraction.utility.broadcastreceiver.WifiConnected;
import com.himanshugupta53.triggeraction.utility.broadcastreceiver.WifiStateChangedReceiver;

public enum TriggerModelGroup {
	WIFI_SWITCHED_ON("WIFI", 0),
	WIFI_SWITCHED_OFF("WIFI", 0),
	WIFI_CONNECTED_TO_ANY_NETWORK("WIFI", 0),
	WIFI_DISCONNECTED_FROM_ANY_NETWORK("WIFI", 0),
	WIFI_CONNECTED_TO_SPECIFIC_NETWORK("WIFI", 1),
	WIFI_DISCONNECTED_FROM_SPECIFIC_NETWORK("WIFI", 1),
	BLUETOOTH_SWITCHED_ON("BLUETOOTH", 0),
	BLUETOOTH_SWITCHED_OFF("BLUETOOTH", 0),
	BATTERY_LEVEL_LOW("BATTERY", 0),
	BATTERY_LEVEL_OKAY("BATTERY", 0),
	POWER_CONNECTED("BATTERY", 0),
	POWER_DISCONNECTED("BATTERY", 0),
	SMS_RECEIVED("SMS", 0),
	PHONE_CALL_RECEIVED("PHONECALL", 0),
	PHONE_CALL_MADE("PHONECALL", 0),
	PHONE_LOCKED("LOCK", 0),
	PHONE_UNLOCKED("LOCK", 0),
	APP_OPENED_ANY("APP", 0),
	APP_OPENED_SPECIFIC("APP", 1),
	GPS_SWITCHED_ON("GPS", 0),
	GPS_SWITCHED_OFF("GPS", 0),
	TIME_AT("TIME", 1),
	TIME_AT_REPEAT("TIME", 3),
	TIME_FROM_TO("TIME", 2),
	DATA_CONNECTION_CONNECTED("GPRS", 0),
	DATA_CONNECTION_DISCONNECTED("GPRS", 0),;

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

	public static List<TriggerModelGroup> getTriggersOfGroup(String grp){
		if (grp == null)
			return null;
		TriggerModelGroup[] values = TriggerModelGroup.values();
		List<TriggerModelGroup> list = new ArrayList<TriggerModelGroup>();
		for (TriggerModelGroup t : values){
			if (t.getGroupName().equals(grp))
				list.add(t);
		}
		return list;
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
		case WIFI_DISCONNECTED_FROM_SPECIFIC_NETWORK:
			return new WifiScanResultsAvailableDialog(context);
		case APP_OPENED_SPECIFIC:
			return new ListOfAppsDialog(context);
		case TIME_AT:
			return new TimeAtDialog(context);
		case TIME_AT_REPEAT:
			return new TimeAtRepeatDialog(context);
		case TIME_FROM_TO:
			return new TimeFromToDialog(context);
		case WIFI_SWITCHED_ON:
		case WIFI_SWITCHED_OFF:
		case WIFI_CONNECTED_TO_ANY_NETWORK:
		case WIFI_DISCONNECTED_FROM_ANY_NETWORK:
		case BLUETOOTH_SWITCHED_ON:
		case BLUETOOTH_SWITCHED_OFF:
		case SMS_RECEIVED:
		case PHONE_CALL_RECEIVED:
		case PHONE_CALL_MADE:
		case PHONE_LOCKED:
		case PHONE_UNLOCKED:
		case APP_OPENED_ANY:
		case GPS_SWITCHED_ON:
		case GPS_SWITCHED_OFF:
		case BATTERY_LEVEL_LOW:
		case BATTERY_LEVEL_OKAY:
		case POWER_CONNECTED:
		case POWER_DISCONNECTED:
		case DATA_CONNECTION_CONNECTED:
		case DATA_CONNECTION_DISCONNECTED:
		default:
			return null;

		}
	}

	public void checkAndPerformTaskInBackgroundService(Context context, TriggerActionParser tAP){
		switch(this){
		case APP_OPENED_ANY:
		case APP_OPENED_SPECIFIC:
		case BLUETOOTH_SWITCHED_ON:
		case BLUETOOTH_SWITCHED_OFF:
		case PHONE_LOCKED:
		case PHONE_UNLOCKED:
		case TIME_AT:
		case TIME_AT_REPEAT:
		case TIME_FROM_TO:
			Intent intent= new Intent(context, MyService.class);
			intent.putExtra(this.toString(), true);
			String input = "";
			if (tAP.triggerInputs != null){
				for (String str : tAP.triggerInputs){
					input = input + str +"|";
				}
			}
			if (input.length() > 0){
				input = input.substring(0, input.length() - 1);
			}
			intent.putExtra("input", input);
			context.startService(intent);
			break;			
		case WIFI_CONNECTED_TO_SPECIFIC_NETWORK:
		case WIFI_DISCONNECTED_FROM_SPECIFIC_NETWORK:
		case WIFI_CONNECTED_TO_ANY_NETWORK:
		case WIFI_DISCONNECTED_FROM_ANY_NETWORK:
		case WIFI_SWITCHED_ON:
		case WIFI_SWITCHED_OFF:
		case SMS_RECEIVED:
		case PHONE_CALL_RECEIVED:
		case PHONE_CALL_MADE:
		case GPS_SWITCHED_ON:
		case GPS_SWITCHED_OFF:
		case BATTERY_LEVEL_LOW:
		case BATTERY_LEVEL_OKAY:
		case POWER_CONNECTED:
		case POWER_DISCONNECTED:
		case DATA_CONNECTION_CONNECTED:
		case DATA_CONNECTION_DISCONNECTED:
		default:
		}
	}

	public void registerBroadcastReceiver(Activity context){
		switch(this){
		case WIFI_CONNECTED_TO_SPECIFIC_NETWORK:
		case WIFI_DISCONNECTED_FROM_SPECIFIC_NETWORK:
		case WIFI_CONNECTED_TO_ANY_NETWORK:
		case WIFI_DISCONNECTED_FROM_ANY_NETWORK:
			WifiConnected.registerBroadcastReceiver(context);
			break;
		case APP_OPENED_ANY:
		case APP_OPENED_SPECIFIC:
			AppOpened.registerBroadcastReceiver(context);
			break;
		case WIFI_SWITCHED_ON:
		case WIFI_SWITCHED_OFF:
			WifiStateChangedReceiver.registerBroadcastReceiver(context);
			break;
		case BLUETOOTH_SWITCHED_ON:
		case BLUETOOTH_SWITCHED_OFF:
		case SMS_RECEIVED:
		case PHONE_CALL_RECEIVED:
		case PHONE_CALL_MADE:
		case PHONE_LOCKED:
		case PHONE_UNLOCKED:
		case GPS_SWITCHED_ON:
		case GPS_SWITCHED_OFF:
		case BATTERY_LEVEL_LOW:
		case BATTERY_LEVEL_OKAY:
		case POWER_CONNECTED:
		case POWER_DISCONNECTED:
		case TIME_AT:
		case TIME_AT_REPEAT:
		case TIME_FROM_TO:
		case DATA_CONNECTION_CONNECTED:
		case DATA_CONNECTION_DISCONNECTED:
		default:
		}
	}

}

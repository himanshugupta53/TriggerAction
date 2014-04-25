package com.himanshugupta53.triggeraction.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.himanshugupta53.triggeraction.AlertDialogActivity;
import com.himanshugupta53.triggeraction.NotificationChooser;
import com.himanshugupta53.triggeraction.NotificationChooser.NotificationType;
import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.main.AlarmCancelActivity;
import com.himanshugupta53.triggeraction.main.MainActivity;
import com.himanshugupta53.triggeraction.trigger.ListOfAppsDialog;
import com.himanshugupta53.triggeraction.trigger.TriggerDialog;
import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.Config;
import com.himanshugupta53.triggeraction.utility.MyUserPreferences;
import com.himanshugupta53.triggeraction.utility.Utility;
import com.himanshugupta53.triggeraction.utility.WifiCustomManager;

public enum ActionModelGroup {
	WIFI_SWITCH_ON("WIFI", 0),
	WIFI_SWITCH_OFF("WIFI", 0),
	WIFI_CONNECT_TO_NETWORK("WIFI", 2),
	WIFI_DISCONNECT_FROM_NETWORK("WIFI", 1),
	BLUETOOTH_SWITCH_ON("BLUETOOTH", 0),
	BLUETOOTH_SWITCH_OFF("BLUETOOTH", 0),
	GPS_SWITCH_ON("GPS", 0),
	GPS_SWITCH_OFF("GPS", 0),
	APP_SPECIFIC_OPEN("APP", 1),
	CHANGE_VOLUME("VOLUME", 8),
	GPRS_SWITCH_ON("GPRS", 0),
	GPRS_SWITCH_OFF("GPRS", 0),
	VIBRATE("VIBRATE", 0),
	ALARM("ALARM", 0);

	private String groupName = null, title = null, description = null, fulldescription = null;
	private int noOfInputs = 0;

	private ActionModelGroup(String grpName, int num){
		groupName = grpName;
		noOfInputs = num;
	}

	public static ActionModelGroup[] getValuesForTrigger(TriggerModelGroup tMG){
		switch(tMG){
		case WIFI_CONNECTED_TO_SPECIFIC_NETWORK:
			return new ActionModelGroup[]{WIFI_SWITCH_OFF, WIFI_DISCONNECT_FROM_NETWORK, BLUETOOTH_SWITCH_ON, BLUETOOTH_SWITCH_OFF, GPS_SWITCH_ON, GPS_SWITCH_OFF, APP_SPECIFIC_OPEN, CHANGE_VOLUME, GPRS_SWITCH_ON, GPRS_SWITCH_OFF, VIBRATE, ALARM};
		case WIFI_DISCONNECTED_FROM_SPECIFIC_NETWORK:
			return new ActionModelGroup[]{WIFI_SWITCH_ON, WIFI_SWITCH_OFF, WIFI_CONNECT_TO_NETWORK, BLUETOOTH_SWITCH_ON, BLUETOOTH_SWITCH_OFF, GPS_SWITCH_ON, GPS_SWITCH_OFF, APP_SPECIFIC_OPEN, CHANGE_VOLUME, GPRS_SWITCH_ON, GPRS_SWITCH_OFF, VIBRATE, ALARM};
		case TIME_FROM_TO:
			return new ActionModelGroup[]{WIFI_SWITCH_ON, WIFI_SWITCH_OFF, WIFI_CONNECT_TO_NETWORK, WIFI_DISCONNECT_FROM_NETWORK, BLUETOOTH_SWITCH_ON, BLUETOOTH_SWITCH_OFF, GPS_SWITCH_ON, GPS_SWITCH_OFF, CHANGE_VOLUME, GPRS_SWITCH_ON, GPRS_SWITCH_OFF};
		case WIFI_SWITCHED_ON:
			return new ActionModelGroup[]{WIFI_CONNECT_TO_NETWORK, BLUETOOTH_SWITCH_ON, BLUETOOTH_SWITCH_OFF, GPS_SWITCH_ON, GPS_SWITCH_OFF, APP_SPECIFIC_OPEN, CHANGE_VOLUME, GPRS_SWITCH_ON, GPRS_SWITCH_OFF, VIBRATE, ALARM};
		case WIFI_SWITCHED_OFF:
			return new ActionModelGroup[]{BLUETOOTH_SWITCH_ON, BLUETOOTH_SWITCH_OFF, GPS_SWITCH_ON, GPS_SWITCH_OFF, APP_SPECIFIC_OPEN, CHANGE_VOLUME, GPRS_SWITCH_ON, GPRS_SWITCH_OFF, VIBRATE, ALARM};
		case WIFI_CONNECTED_TO_ANY_NETWORK:
			return new ActionModelGroup[]{WIFI_SWITCH_OFF, WIFI_CONNECT_TO_NETWORK, WIFI_DISCONNECT_FROM_NETWORK, BLUETOOTH_SWITCH_ON, BLUETOOTH_SWITCH_OFF, GPS_SWITCH_ON, GPS_SWITCH_OFF, APP_SPECIFIC_OPEN, CHANGE_VOLUME, GPRS_SWITCH_ON, GPRS_SWITCH_OFF, VIBRATE, ALARM};
		case WIFI_DISCONNECTED_FROM_ANY_NETWORK:
			return new ActionModelGroup[]{WIFI_SWITCH_OFF, WIFI_CONNECT_TO_NETWORK, BLUETOOTH_SWITCH_ON, BLUETOOTH_SWITCH_OFF, GPS_SWITCH_ON, GPS_SWITCH_OFF, APP_SPECIFIC_OPEN, CHANGE_VOLUME, GPRS_SWITCH_ON, GPRS_SWITCH_OFF, VIBRATE, ALARM};
		case BLUETOOTH_SWITCHED_ON:
		case BLUETOOTH_SWITCHED_OFF:
			return new ActionModelGroup[]{WIFI_SWITCH_ON, WIFI_SWITCH_OFF, WIFI_CONNECT_TO_NETWORK, WIFI_DISCONNECT_FROM_NETWORK, GPS_SWITCH_ON, GPS_SWITCH_OFF, APP_SPECIFIC_OPEN, CHANGE_VOLUME, GPRS_SWITCH_ON, GPRS_SWITCH_OFF, VIBRATE, ALARM};
		case GPS_SWITCHED_ON:
		case GPS_SWITCHED_OFF:
			return new ActionModelGroup[]{WIFI_SWITCH_ON, WIFI_SWITCH_OFF, WIFI_CONNECT_TO_NETWORK, WIFI_DISCONNECT_FROM_NETWORK, BLUETOOTH_SWITCH_ON, BLUETOOTH_SWITCH_OFF, APP_SPECIFIC_OPEN, CHANGE_VOLUME, GPRS_SWITCH_ON, GPRS_SWITCH_OFF, VIBRATE, ALARM};
		case PHONE_CALL_RECEIVED:
		case PHONE_CALL_MADE:
			return new ActionModelGroup[]{WIFI_SWITCH_ON, WIFI_SWITCH_OFF, WIFI_CONNECT_TO_NETWORK, WIFI_DISCONNECT_FROM_NETWORK, BLUETOOTH_SWITCH_ON, BLUETOOTH_SWITCH_OFF, GPS_SWITCH_ON, GPS_SWITCH_OFF, APP_SPECIFIC_OPEN, CHANGE_VOLUME, GPRS_SWITCH_ON, GPRS_SWITCH_OFF, VIBRATE};
		case DATA_CONNECTION_CONNECTED:
		case DATA_CONNECTION_DISCONNECTED:
			return new ActionModelGroup[]{WIFI_SWITCH_ON, WIFI_SWITCH_OFF, WIFI_CONNECT_TO_NETWORK, WIFI_DISCONNECT_FROM_NETWORK, BLUETOOTH_SWITCH_ON, BLUETOOTH_SWITCH_OFF, GPS_SWITCH_ON, GPS_SWITCH_OFF, APP_SPECIFIC_OPEN, CHANGE_VOLUME, VIBRATE};
		case BATTERY_LEVEL_LOW:
		case BATTERY_LEVEL_OKAY:
		case POWER_CONNECTED:
		case POWER_DISCONNECTED:
		case APP_OPENED_SPECIFIC:
		case TIME_AT:
		case TIME_AT_REPEAT:
		case SMS_RECEIVED:
		case PHONE_LOCKED:
		case PHONE_UNLOCKED:
		case APP_OPENED_ANY:
		case DEVICE_START:
		case HEADSET_CONNECTED:
		case HEADSET_DISCONNECTED:
		default:
			return ActionModelGroup.values();
		}
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
				var = R.string.class.getField(this.toString().toLowerCase() + "_action_title").getInt(null);
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
				var = R.string.class.getField(this.toString().toLowerCase() + "_action_description").getInt(null);
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
				var = R.string.class.getField(this.toString().toLowerCase() + "_action_fulldescription").getInt(null);
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

	public static ActionModelGroup[] getActionsOfGroup(String grp){
		if (grp == null)
			return null;
		ActionModelGroup[] values = ActionModelGroup.values();
		int count = 0;
		for (ActionModelGroup t : values){
			if (t.getGroupName().equals(grp))
				count++;
		}
		ActionModelGroup[] tmg = new ActionModelGroup[count];
		int i=0;
		for (ActionModelGroup t : values){
			if (t.getGroupName().equals(grp))
				tmg[i++] = t;
		}
		return tmg;
	}

	public static String getTitleOfGroup(Activity context, String grp){
		ActionModelGroup[] values = ActionModelGroup.values();
		for (ActionModelGroup t : values){
			if (t.getGroupName().equals(grp)){
				return t.getTitle(context);
			}
		}
		return "";
	}

	public static String getDescriptionOfGroup(Activity context, String grp){
		ActionModelGroup[] values = ActionModelGroup.values();
		for (ActionModelGroup t : values){
			if (t.getGroupName().equals(grp)){
				return t.getDescription(context);
			}
		}
		return "";
	}

	public static Set<String> getGroupStrings(){
		ActionModelGroup[] values = ActionModelGroup.values();
		Set <String> groups = new HashSet<String>();
		for (ActionModelGroup t : values){
			groups.add(t.getGroupName());
		}
		return groups;
	}

	public void performAction(Context context, List<String> inputList){
		if (context != null){
			NotificationChooser.NotificationType notificationType = NotificationType.getValueOf(MyUserPreferences.getString(Config.notificationTypeUserPreferenceKey));
			notificationType.performActionRelatedToNotificationType(context, "Action " + toString() + " has been performed");
		}
		
		switch(this){
		case WIFI_SWITCH_ON:
			WifiCustomManager.getInstance(context).enableWifi();
			break;
		case WIFI_SWITCH_OFF:
			WifiCustomManager.getInstance(context).disableWifi();
			break;
		case BLUETOOTH_SWITCH_ON:
			BluetoothAdapter.getDefaultAdapter().enable();
			break;
		case BLUETOOTH_SWITCH_OFF:
			BluetoothAdapter.getDefaultAdapter().disable();    
			break;
		case GPS_SWITCH_ON:
			Utility.turnGPSOn(context);
			break;
		case GPS_SWITCH_OFF:
			Utility.turnGPSOff(context);
			break;
		case APP_SPECIFIC_OPEN:
			context.startActivity(context.getPackageManager().getLaunchIntentForPackage(inputList.get(0)));
			break;
		case CHANGE_VOLUME:
			AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
			audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, Integer.parseInt(inputList.get(0)), 0);
			audioManager.setStreamVolume(AudioManager.STREAM_RING, Integer.parseInt(inputList.get(1)), 0);
			audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, Integer.parseInt(inputList.get(2)), 0);
			audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, Integer.parseInt(inputList.get(3)), 0);
			break;
		case GPRS_SWITCH_ON:
			Utility.turnGPRSOn(context);
			break;
		case GPRS_SWITCH_OFF:
			Utility.turnGPRSOff(context);
			break;
		case VIBRATE:
			Utility.vibratePhoneForTime(context, 1000);
			break;
		case ALARM:
			Utility.playAlarm(context);
			Utility.vibratePhoneForTime(context, 500);
			Intent intent = new Intent(context, AlarmCancelActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
			break;
		default:
		}
	}

	public void performOppositeAction(Context context, List<String> inputList){
		switch(this){
		case WIFI_SWITCH_ON:
			WIFI_SWITCH_OFF.performAction(context, null);
			break;
		case WIFI_SWITCH_OFF:
			WIFI_SWITCH_ON.performAction(context, null);
			break;
		case BLUETOOTH_SWITCH_ON:
			BLUETOOTH_SWITCH_OFF.performAction(context, null);
			break;
		case BLUETOOTH_SWITCH_OFF:
			BLUETOOTH_SWITCH_ON.performAction(context, null);    
			break;
		case GPS_SWITCH_ON:
			GPS_SWITCH_OFF.performAction(context, null);
			break;
		case GPS_SWITCH_OFF:
			GPS_SWITCH_ON.performAction(context, null);
			break;
		case CHANGE_VOLUME:
			AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
			audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, Integer.parseInt(inputList.get(4)), 0);
			audioManager.setStreamVolume(AudioManager.STREAM_RING, Integer.parseInt(inputList.get(5)), 0);
			audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, Integer.parseInt(inputList.get(6)), 0);
			audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, Integer.parseInt(inputList.get(7)), 0);
			break;
		case GPRS_SWITCH_ON:
			GPRS_SWITCH_OFF.performAction(context, null);
			break;
		case GPRS_SWITCH_OFF:
			GPRS_SWITCH_ON.performAction(context, null);
			break;
		case APP_SPECIFIC_OPEN:
		case VIBRATE:
		case ALARM:
		default:
		}
	}

	public TriggerDialog getDialogPopup(Activity context){
		if (this.getNoOfInputs() == 0){
			return null;
		}
		switch(this){
		case APP_SPECIFIC_OPEN:
			return new ListOfAppsDialog(context);
		case CHANGE_VOLUME:
			return new ChangeVolumeDialog(context);
		case WIFI_SWITCH_ON:
		case WIFI_SWITCH_OFF:
		case BLUETOOTH_SWITCH_ON:
		case BLUETOOTH_SWITCH_OFF:
		case GPS_SWITCH_ON:
		case GPS_SWITCH_OFF:
		case GPRS_SWITCH_ON:
		case GPRS_SWITCH_OFF:
		case VIBRATE:
		case ALARM:
		default:
			return null;

		}
	}




}

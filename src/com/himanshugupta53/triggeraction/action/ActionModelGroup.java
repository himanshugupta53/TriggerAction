package com.himanshugupta53.triggeraction.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.trigger.ListOfAppsDialog;
import com.himanshugupta53.triggeraction.trigger.TriggerDialog;
import com.himanshugupta53.triggeraction.utility.WifiCustomManager;

public enum ActionModelGroup {
	WIFI_SWITCH_ON("WIFI", 0),
	WIFI_SWITCH_OFF("WIFI", 0),
	BLUETOOTH_SWITCH_ON("BLUETOOTH", 0),
	BLUETOOTH_SWITCH_OFF("BLUETOOTH", 0),
	GPS_SWITCH_ON("GPS", 0),
	GPS_SWITCH_OFF("GPS", 0),
	APP_SPECIFIC_OPEN("APP", 1);


	private String groupName = null, title = null, description = null, fulldescription = null;
	private int noOfInputs = 0;

	private ActionModelGroup(String grpName, int num){
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
			turnGPSOn(context);
			break;
		case GPS_SWITCH_OFF:
			turnGPSOff(context);
			break;
		case APP_SPECIFIC_OPEN:
			context.startActivity(context.getPackageManager().getLaunchIntentForPackage(inputList.get(0)));
			break;
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
		case WIFI_SWITCH_ON:
		case WIFI_SWITCH_OFF:
		case BLUETOOTH_SWITCH_ON:
		case BLUETOOTH_SWITCH_OFF:
		case GPS_SWITCH_ON:
		case GPS_SWITCH_OFF:
		default:
			return null;

		}
	}
	
	public void turnGPSOn(Context ctx)
	{
	     Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
	     intent.putExtra("enabled", true);
	     ctx.sendBroadcast(intent);

	    String provider = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
	    if(!provider.contains("gps")){ //if gps is disabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        ctx.sendBroadcast(poke);


	    }
	}
	// automatic turn off the gps
	public void turnGPSOff(Context ctx)
	{
	    String provider = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
	    if(provider.contains("gps")){ //if gps is enabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        ctx.sendBroadcast(poke);
	    }
	}
	

}

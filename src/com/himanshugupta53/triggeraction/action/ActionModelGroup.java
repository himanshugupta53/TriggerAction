package com.himanshugupta53.triggeraction.action;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;

import com.himanshugupta53.triggeraction.R;

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
	

}

package com.himanshugupta53.triggeraction.utility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;


public class MyUserPreferences{

	private static SharedPreferences userPreferences;
	private static Context context;
	private static HashMap<String, Set<String>> triggerActionHashMap = null;
	public static String triggerKey = "triggers";

	private static SharedPreferences userPreferences(){
		if (userPreferences == null){
			userPreferences = context.getSharedPreferences("TriggerAction", 0);
		}
		return userPreferences;
	}
	
	private static HashMap<String, Set<String>> triggerActionHashMap(){
		if (triggerActionHashMap == null){
			triggerActionHashMap = new HashMap<String, Set<String>>();
		}
		return triggerActionHashMap;
	}
	
	public static void setContext(Context _context){
		context = _context;
	}

	public static void setBoolean(String key, boolean value){
		SharedPreferences.Editor editor = MyUserPreferences.userPreferences().edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static boolean getBoolean(String key, boolean _default){
		return MyUserPreferences.userPreferences().getBoolean(key, _default);
	}

	public static boolean getBoolean(String key){
		return getBoolean(key, false);
	}

	public static void setString(String key, String value){
		SharedPreferences.Editor editor = MyUserPreferences.userPreferences().edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getString(String key, String _default){
		return MyUserPreferences.userPreferences().getString(key, _default);
	}

	public static String getString(String key){
		return getString(key, null);
	}

	public static void setStringSet(String key, Set<String> value){
		String stringValue = "";
		if (value != null){
			MyUserPreferences.triggerActionHashMap().put(key, value);
			for (String str : value){
				stringValue = stringValue + str + "|";
			}
		}
		setString(key, stringValue);
	}

	public static Set<String> getStringSet(String key, Set<String> _default){
		Set<String> set = MyUserPreferences.triggerActionHashMap().get(key);
		if (set != null)
			return set;
		set = new HashSet<String>();
		String value = getString(key, null);
		if (value == null)
			return _default;
		String[] strArray = value.split("\\|");
		for (String str : strArray){
			set.add(str);
		}
		if (set != null){
			MyUserPreferences.triggerActionHashMap().put(key, set);
		}
		return set;
	}

	public static Set<String> getStringSet(String key){
		if (key == null)
			return null;
		return getStringSet(key, null);
	}
}

package com.himanshugupta53.triggeraction.utility;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class MyUserPreferences{

	private static SharedPreferences userPreferences;
	private static Context context;

	private static void instantiate(){
		if (MyUserPreferences.userPreferences == null){
			MyUserPreferences.userPreferences = context.getSharedPreferences("TriggerAction", 0);
		}
	}

	public static void setContext(Context _context){
		context = _context;
		instantiate();
	}

	public static void setBoolean(String key, boolean value){
		SharedPreferences.Editor editor = userPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static boolean getBoolean(String key, boolean _default){
		return userPreferences.getBoolean(key, _default);
	}

	public static boolean getBoolean(String key){
		return getBoolean(key, false);
	}

	public static void setString(String key, String value){
		SharedPreferences.Editor editor = userPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getString(String key, String _default){
		return userPreferences.getString(key, _default);
	}

	public static String getString(String key){
		return getString(key, null);
	}

	public static void setStringSet(String key, Set<String> value){
		String stringValue = "";
		if (value != null){
			for (String str : value){
				stringValue = stringValue + str + "|";
			}
		}
		setString(key, stringValue);
	}

	public static Set<String> getStringSet(String key, Set<String> _default){
		Set<String> set = new HashSet<String>();
		String value = getString(key, null);
		if (value == null)
			return _default;
		String[] strArray = value.split("\\|");
		for (String str : strArray){
			set.add(str);
		}
		return set;
	}

	public static Set<String> getStringSet(String key){
		return getStringSet(key, null);
	}
}
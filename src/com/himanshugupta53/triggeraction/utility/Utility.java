package com.himanshugupta53.triggeraction.utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.Settings;

public class Utility {

	private static Ringtone ringtone = null;
	private static BluetoothAdapter bluetoothAdapter = null;
	private static KeyguardManager keyguardManager = null;
	
	public static String getClassName(Object obj){
		Class<?> enclosingClass = obj.getClass().getEnclosingClass();
		if (enclosingClass != null) {
			return enclosingClass.getName();
		} else {
			return obj.getClass().getName();
		}
	}

	public static boolean areListsEqual(List<String> l1, List<String> l2){
		if ((l1 == null || l1.size() == 0) && (l2 == null || l2.size() == 0)){
			return true;
		}
		for (String str : l1){
			if (!l2.contains(str)){
				return false;
			}
		}
		for (String str : l2){
			if (!l1.contains(str)){
				return false;
			}
		}
		return true;
	}

	public static boolean isLeapYear(int year){
		if (year % 400 == 0)
			return true;
		if (year % 100 == 0)
			return false;
		if (year % 4 == 0)
			return true;
		return false;
	}

	public static int noOfDaysInMonth(int month, int year){
		int num = 31 - (month - 1) % 7 % 2;
		if (month == 2){
			num = isLeapYear(year) ? 29 : 28;
		}
		return num;
	}

	public static void vibratePhoneForTime(Context context, int milliseconds){
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(milliseconds);
	}

	public static void turnGPRSOn(Context context){
		turnGPRS(context, true);
	}

	public static void turnGPRSOff(Context context){
		turnGPRS(context, false);
	}

	private static void turnGPRS(Context context, boolean _switch){
		ConnectivityManager dataManager;
		dataManager  = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		Method dataMtd = null;
		try {
			dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataMtd.setAccessible(true);
		try {
			dataMtd.invoke(dataManager, _switch);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public static void turnGPSOn(Context ctx){
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
	public static void turnGPSOff(Context ctx){
		String provider = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		if(provider.contains("gps")){ //if gps is enabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3")); 
			ctx.sendBroadcast(poke);
		}
	}
	
	
	public static void playAlarm(Context context){
		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		ringtone = RingtoneManager.getRingtone(context, notification);
		ringtone.play();
	}
	
	public static boolean isAlarmPlaying(){
		if (ringtone != null)
			return ringtone.isPlaying();
		return false;
	}
	
	public static void stopAlarm(){
		if (ringtone != null)
			ringtone.stop();
	}
	
	public static boolean isBluetoothEnabled(){
		if (bluetoothAdapter == null)
			bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		return bluetoothAdapter.isEnabled();
	}

	public static boolean isPhoneLocked(Context context){
		if (keyguardManager == null)
			keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
		return keyguardManager.inKeyguardRestrictedInputMode();
	}

}

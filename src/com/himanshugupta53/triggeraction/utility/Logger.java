package com.himanshugupta53.triggeraction.utility;

import android.content.Context;
import android.provider.Settings.Secure;

import com.crittercism.app.Crittercism;

public class Logger {

	public static void initializeCrittercism(Context context){
		Crittercism.initialize(context, "535276298633a408bb000009");
		String deviceId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		Crittercism.setUsername(deviceId);
	}
	
	public static void leaveCrittercismBreadCrumb(String log){
		Crittercism.leaveBreadcrumb(log);
	}
	
}

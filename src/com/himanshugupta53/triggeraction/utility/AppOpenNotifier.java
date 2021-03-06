package com.himanshugupta53.triggeraction.utility;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AppOpenNotifier extends Thread {
	
	boolean requiredForAppOpen = false;
	String topActivityPackageName = null;
	MyService service = null;
	private static AppOpenNotifier appOpenNotifier = null;
	
	public static AppOpenNotifier getInstance(boolean b, MyService s){
		if (appOpenNotifier != null)
			appOpenNotifier.terminate();
		appOpenNotifier = new AppOpenNotifier(b, s);
		appOpenNotifier.initialize(b, s);
		return appOpenNotifier;
	}
	
	private AppOpenNotifier(boolean b, MyService s){
		super();
	}
	
	private void initialize(boolean b, MyService s){
		requiredForAppOpen = b;
		service = s;
	}
	
	private void terminate(){
		requiredForAppOpen = false;
	}
	
	@Override
	public void run() {
		while(requiredForAppOpen){
			try {
				Thread.sleep(2000);
				ActivityManager am = (ActivityManager) service.getSystemService(Context.ACTIVITY_SERVICE);
				List<ActivityManager.RunningTaskInfo> alltasks = am.getRunningTasks(1);
				RunningTaskInfo foregroundTask = null;
				for (ActivityManager.RunningTaskInfo aTask : alltasks){
					foregroundTask = aTask;
					break;
				}
				if (foregroundTask != null){
					String packageStr = foregroundTask.topActivity.getPackageName();
					if (packageStr != null){
						if (topActivityPackageName != null && !topActivityPackageName.equals(packageStr)){
							Log.i("TriggerActionAppService", "Previous activity was "+topActivityPackageName+" and current activity is "+packageStr);
							Intent intent = new Intent();
							intent.setAction("AppOpened");
							intent.putExtra("oldOpenedApp", topActivityPackageName);
							intent.putExtra("newlyOpenedApp", packageStr);
							service.sendBroadcast(intent);
						}
						topActivityPackageName = packageStr;
					}
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

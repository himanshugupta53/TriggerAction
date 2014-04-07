package com.himanshugupta53.triggeraction.utility;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.himanshugupta53.triggeraction.utility.broadcastreceiver.AppOpened;

public class MyService extends Service {

	String topActivityPackageName = null;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		final Thread t = new Thread() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(2000);
						ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
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
									intent.putExtra("newlyOpenedApp", packageStr);
									sendBroadcast(intent);
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
		};
		t.start();


		return Service.START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}

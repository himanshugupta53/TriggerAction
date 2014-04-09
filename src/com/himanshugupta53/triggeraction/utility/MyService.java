package com.himanshugupta53.triggeraction.utility;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;

public class MyService extends Service {
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		boolean appOpened = intent.getBooleanExtra(TriggerModelGroup.APP_OPENED_ANY.toString(), false) |
							intent.getBooleanExtra(TriggerModelGroup.APP_OPENED_SPECIFIC.toString(), false);
		boolean bluetoothStateChanged = intent.getBooleanExtra(TriggerModelGroup.BLUETOOTH_SWITCHED_ON.toString(), false) |
										intent.getBooleanExtra(TriggerModelGroup.BLUETOOTH_SWITCHED_OFF.toString(), false);
		boolean phoneLockStatusChanged = intent.getBooleanExtra(TriggerModelGroup.PHONE_LOCKED.toString(), false) |
										intent.getBooleanExtra(TriggerModelGroup.PHONE_UNLOCKED.toString(), false);
		boolean timeScheduler = true;
		
		final AppOpenNotifier appOpenNotifier = new AppOpenNotifier(appOpened, this);
		final BluetoothStateNotifier bluetoothStateNotifier = new BluetoothStateNotifier(bluetoothStateChanged, this);
		final PhoneLockedStateNotifier phoneLockedStateNotifier = new PhoneLockedStateNotifier(phoneLockStatusChanged, this);
		final TaskScheduler taskScheduler = new TaskScheduler(timeScheduler, this);
		
		appOpenNotifier.start();
		bluetoothStateNotifier.start();
		phoneLockedStateNotifier.start();
		taskScheduler.start();
		
		return Service.START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}

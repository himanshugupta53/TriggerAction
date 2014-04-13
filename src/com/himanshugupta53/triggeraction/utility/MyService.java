package com.himanshugupta53.triggeraction.utility;

import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;

public class MyService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		List<TriggerActionParser> triggerActions1 = TriggerActionParser.getSavedActionsForTrigger(TriggerModelGroup.APP_OPENED_ANY);
		List<TriggerActionParser> triggerActions2 = TriggerActionParser.getSavedActionsForTrigger(TriggerModelGroup.APP_OPENED_SPECIFIC);
		boolean appOpened = intent.getBooleanExtra(TriggerModelGroup.APP_OPENED_ANY.toString(), false) | intent.getBooleanExtra(TriggerModelGroup.APP_OPENED_SPECIFIC.toString(), false) | triggerActions1.size() > 0 | triggerActions2.size() > 0;

		triggerActions1 = TriggerActionParser.getSavedActionsForTrigger(TriggerModelGroup.BLUETOOTH_SWITCHED_ON);
		triggerActions2 = TriggerActionParser.getSavedActionsForTrigger(TriggerModelGroup.BLUETOOTH_SWITCHED_OFF);
		boolean bluetoothStateChanged = intent.getBooleanExtra(TriggerModelGroup.BLUETOOTH_SWITCHED_ON.toString(), false) | intent.getBooleanExtra(TriggerModelGroup.BLUETOOTH_SWITCHED_OFF.toString(), false) | triggerActions1.size() > 0 | triggerActions2.size() > 0;

		triggerActions1 = TriggerActionParser.getSavedActionsForTrigger(TriggerModelGroup.PHONE_LOCKED);
		triggerActions2 = TriggerActionParser.getSavedActionsForTrigger(TriggerModelGroup.PHONE_UNLOCKED);
		boolean phoneLockStatusChanged = intent.getBooleanExtra(TriggerModelGroup.PHONE_LOCKED.toString(), false) | intent.getBooleanExtra(TriggerModelGroup.PHONE_UNLOCKED.toString(), false) | triggerActions1.size() > 0 | triggerActions2.size() > 0;

		triggerActions1 = TriggerActionParser.getSavedActionsForTrigger(TriggerModelGroup.TIME_AT);
		boolean timeScheduler = intent.getBooleanExtra(TriggerModelGroup.TIME_AT.toString(), false) | triggerActions1.size() > 0;

		triggerActions1 = TriggerActionParser.getSavedActionsForTrigger(TriggerModelGroup.TIME_AT_REPEAT);
		boolean timeRepeatScheduler = intent.getBooleanExtra(TriggerModelGroup.TIME_AT_REPEAT.toString(), false) | triggerActions1.size() > 0;

		String input = intent.getStringExtra("input");

		AppOpenNotifier appOpenNotifier = new AppOpenNotifier(appOpened, this);
		BluetoothStateNotifier bluetoothStateNotifier = new BluetoothStateNotifier(bluetoothStateChanged, this);
		PhoneLockedStateNotifier phoneLockedStateNotifier = new PhoneLockedStateNotifier(phoneLockStatusChanged, this);
		TaskScheduler taskScheduler = new TaskScheduler(timeScheduler, this, input);
		TaskRepeatScheduler taskRepeatScheduler = new TaskRepeatScheduler(timeRepeatScheduler, this, input);
		
		appOpenNotifier.start();
		bluetoothStateNotifier.start();
		phoneLockedStateNotifier.start();
		taskScheduler.start();
		taskRepeatScheduler.start();

		return Service.START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}

package com.himanshugupta53.triggeraction.utility;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;

public class PhoneLockedStateNotifier extends Thread {

	boolean requiredForLockedStatusCheck = false;
	MyService service = null;
	boolean isPhoneLocked = false;
	private static PhoneLockedStateNotifier phoneLockedStateNotifier = null;
	
	
	private PhoneLockedStateNotifier(boolean b, MyService s){
		super();
	}

	public static PhoneLockedStateNotifier getInstance(boolean b, MyService s){
		if (phoneLockedStateNotifier != null)
			phoneLockedStateNotifier.terminate();
		phoneLockedStateNotifier = new PhoneLockedStateNotifier(b, s);
		phoneLockedStateNotifier.initialize(b, s);
		return phoneLockedStateNotifier;
	}
	
	private void initialize(boolean b, MyService s){
		requiredForLockedStatusCheck = b;
		service = s;
		isPhoneLocked = Utility.isPhoneLocked(service);	
	}
	
	private void terminate(){
		requiredForLockedStatusCheck = false;
	}
	
	@Override
	public void run() {
		while(requiredForLockedStatusCheck){
			try {
				Thread.sleep(2000);
				boolean locked = Utility.isPhoneLocked(service);
				if (isPhoneLocked != locked){
					Intent intent = new Intent();
					intent.setAction("PhoneLockStatusChanged");
					intent.putExtra("phoneLockStatus", locked);
					service.sendBroadcast(intent);
				}
				isPhoneLocked = locked;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

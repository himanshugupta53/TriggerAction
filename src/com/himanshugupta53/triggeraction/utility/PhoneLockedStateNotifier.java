package com.himanshugupta53.triggeraction.utility;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;

public class PhoneLockedStateNotifier extends Thread {

	boolean requiredForLockedStatusCheck = false;
	MyService service = null;
	boolean isPhoneLocked = false;

	public PhoneLockedStateNotifier(boolean b, MyService s){
		super();
		requiredForLockedStatusCheck = b;
		service = s;
	}

	@Override
	public void run() {
		while(requiredForLockedStatusCheck){
			try {
				Thread.sleep(2000);
				KeyguardManager km = (KeyguardManager) service.getSystemService(Context.KEYGUARD_SERVICE);
				boolean locked = km.inKeyguardRestrictedInputMode();
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

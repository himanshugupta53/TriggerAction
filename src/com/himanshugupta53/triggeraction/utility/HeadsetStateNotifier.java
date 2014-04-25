package com.himanshugupta53.triggeraction.utility;

import android.content.Intent;

public class HeadsetStateNotifier extends Thread {

	boolean requiredForHeadsetStateCheck = false;
	MyService service = null;
	boolean isHeadsetEnabled = false;
	private static HeadsetStateNotifier headsetStateNotifier = null;

	public static HeadsetStateNotifier getInstance(boolean b, MyService s){
		if (headsetStateNotifier != null)
			headsetStateNotifier.terminate();
		headsetStateNotifier = new HeadsetStateNotifier(b, s);
		headsetStateNotifier.initialize(b, s);
		return headsetStateNotifier;
	}

	private HeadsetStateNotifier(boolean b, MyService s){
		super();
	}

	private void initialize(boolean b, MyService s){
		requiredForHeadsetStateCheck = b;
		service = s;
		isHeadsetEnabled = Utility.isHeadsetPlugged(s);
	}

	private void terminate(){
		requiredForHeadsetStateCheck = false;
	}

	@Override
	public void run() {
		while(requiredForHeadsetStateCheck){
			try {
				Thread.sleep(2000);
				boolean isEnabled = Utility.isHeadsetPlugged(service);
				if (isHeadsetEnabled != isEnabled){
					Intent intent = new Intent();
					intent.setAction("HeadsetStateChanged");
					intent.putExtra("headsetState", isEnabled);
					service.sendBroadcast(intent);
				}
				isHeadsetEnabled = isEnabled;


			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

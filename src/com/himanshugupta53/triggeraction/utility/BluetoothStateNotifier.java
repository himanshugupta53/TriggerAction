package com.himanshugupta53.triggeraction.utility;

import android.content.Intent;

public class BluetoothStateNotifier extends Thread {

	boolean requiredForBluetoothStateCheck = false;
	MyService service = null;
	boolean isBluetoothEnabled = false;
	private static BluetoothStateNotifier bluetoothStateNotifier = null;

	public static BluetoothStateNotifier getInstance(boolean b, MyService s){
		if (bluetoothStateNotifier != null)
			bluetoothStateNotifier.terminate();
		bluetoothStateNotifier = new BluetoothStateNotifier(b, s);
		bluetoothStateNotifier.initialize(b, s);
		return bluetoothStateNotifier;
	}

	private BluetoothStateNotifier(boolean b, MyService s){
		super();
	}

	private void initialize(boolean b, MyService s){
		requiredForBluetoothStateCheck = b;
		service = s;
		isBluetoothEnabled = Utility.isBluetoothEnabled();
	}

	private void terminate(){
		requiredForBluetoothStateCheck = false;
	}

	@Override
	public void run() {
		while(requiredForBluetoothStateCheck){
			try {
				Thread.sleep(2000);
				boolean isEnabled = Utility.isBluetoothEnabled();
				if (isBluetoothEnabled != isEnabled){
					Intent intent = new Intent();
					intent.setAction("BluetoothStateChanged");
					intent.putExtra("bluetoothState", isEnabled);
					service.sendBroadcast(intent);
				}
				isBluetoothEnabled = isEnabled;


			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

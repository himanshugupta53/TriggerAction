package com.himanshugupta53.triggeraction.utility;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

public class BluetoothStateNotifier extends Thread {

	boolean requiredForBluetoothStateCheck = false;
	MyService service = null;
	boolean isBluetoothEnabled = false;

	public BluetoothStateNotifier(boolean b, MyService s){
		super();
		requiredForBluetoothStateCheck = b;
		service = s;
	}

	@Override
	public void run() {
		while(requiredForBluetoothStateCheck){
			try {
				Thread.sleep(2000);
				BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
				boolean isEnabled = mBluetoothAdapter.isEnabled();
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

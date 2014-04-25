package com.himanshugupta53.triggeraction.utility.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.widget.Toast;

public class HeadsetButtonPressed extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		KeyEvent event = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
	    if (event == null) {
	        return;
	    }
	    int action = event.getAction();

	    switch (event.getKeyCode()) {
	        case KeyEvent.KEYCODE_HEADSETHOOK:
	            if (action == KeyEvent.ACTION_UP)
	                if (SystemClock.uptimeMillis() - event.getDownTime() > 2000) 
	                     Toast.makeText(context, "Long click!", Toast.LENGTH_SHORT).show();
	        break;
	    }
	    
		
	}

}

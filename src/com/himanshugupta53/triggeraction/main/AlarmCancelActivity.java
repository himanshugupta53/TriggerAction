package com.himanshugupta53.triggeraction.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.utility.Utility;

public class AlarmCancelActivity extends Activity implements OnClickListener {

	ToggleButton toggleButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_cancel_layout);
		
		toggleButton = (ToggleButton) findViewById(R.id.alarmSwitch);
		toggleButton.setOnClickListener(this);
		
		if (!Utility.isAlarmPlaying())
			goToMainActivity();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm_cancel, menu);
		return true;
	}

	private void goToMainActivity(){
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	@Override
	public void onClick(View v) {
		goToMainActivity();
	}
	
	@Override
	public void onPause(){
		Utility.stopAlarm();
		super.onPause();
		onBackPressed();
	}
	
	
	@Override
	protected void onDestroy(){
		Utility.stopAlarm();
		super.onDestroy();
	}

}

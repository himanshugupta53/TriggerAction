package com.himanshugupta53.triggeraction.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.trigger.TriggerActivity;
import com.himanshugupta53.triggeraction.utility.MyService;
import com.himanshugupta53.triggeraction.utility.MyUserPreferences;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyUserPreferences.setContext(this);
		Intent intent= new Intent(this, MyService.class);
		startService(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// action with ID action_addtriggeraction was selected
		case R.id.action_addtriggeraction:
			Intent intent = new Intent(this, TriggerActivity.class);
			startActivity(intent);
			Toast.makeText(this, "Choose a trigger to define an action on!", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

		return true;
	} 
	
}

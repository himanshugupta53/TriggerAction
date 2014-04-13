package com.himanshugupta53.triggeraction.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.trigger.TriggerActivity;
import com.himanshugupta53.triggeraction.utility.Config;
import com.himanshugupta53.triggeraction.utility.MainArrayAdapter;
import com.himanshugupta53.triggeraction.utility.MainListActivity;
import com.himanshugupta53.triggeraction.utility.MyService;
import com.himanshugupta53.triggeraction.utility.MyUserPreferences;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

public class MainActivity extends MainListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		MyUserPreferences.setContext(this);
		super.setLayout(R.layout.main_activity_row_layout);
		super.setTAPValues(TriggerActionParser.getAllDefinedTriggerActions());
		super.onCreate(savedInstanceState);
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

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(this, "Item no "+position+" clicked on", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (Config.triggerActionSet){
			Config.triggerActionSet = false;
			super.setTAPValues(TriggerActionParser.getAllDefinedTriggerActions());
			((MainArrayAdapter)getListAdapter()).notifyDataSetChanged(); 
		}
	}

}

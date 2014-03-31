package com.himanshugupta53.triggeraction.action;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.utility.CustomListActivity;

public class ActionActivity extends CustomListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.setTitleValues(new ArrayList<String>());
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// action with ID action_addtriggeraction was selected
		case R.id.action_back:
			onBackPressed();
			break;
		default:
			break;
		}

		return true;
	}
}

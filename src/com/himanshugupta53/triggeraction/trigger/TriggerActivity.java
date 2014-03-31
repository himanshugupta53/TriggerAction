package com.himanshugupta53.triggeraction.trigger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.action.ActionActivity;
import com.himanshugupta53.triggeraction.utility.CustomListActivity;
import com.himanshugupta53.triggeraction.utility.DialogList;

@SuppressLint("NewApi")
public class TriggerActivity extends CustomListActivity implements OnClickListener {

	private DialogList dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.setValues(new String[] {"Camera", "Laptop", "Watch","Smartphone", "Television"});
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trigger, menu);
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

	@SuppressLint("NewApi")
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
//		CustomListPopupWindow listPopupWindow = new CustomListPopupWindow(this);
//		listPopupWindow.setClickListener(this);
//		listPopupWindow.setListOfItems(new String[]{"abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vxy"});
//		listPopupWindow.show();
		
		dialog = new DialogList(this);
		dialog.setValues(new String[] {"abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vxy"});
		dialog.show();
}


	@Override
	public void onBackPressed(){
		super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		dialog.hide();
		Intent intent = new Intent(this, ActionActivity.class);
		startActivity(intent);
	}
}

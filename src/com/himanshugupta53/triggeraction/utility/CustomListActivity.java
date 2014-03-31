package com.himanshugupta53.triggeraction.utility;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.himanshugupta53.triggeraction.R;

public class CustomListActivity extends ListActivity {

	String[] values;
	int layout = -1;

	protected void setValues(String[] vals){
		values = vals;
	}

	protected void setLayout(int _layout){
		layout = _layout;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_list_view);
		CustomArrayAdapter adapter = null;
		if (layout == -1){
			adapter = new CustomArrayAdapter(this, values);
		}
		else{
			adapter = new CustomArrayAdapter(this, layout, values);
		}
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custom_list, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// action with ID action_addtriggeraction was selected
		case R.id.action_addtriggeraction:
			Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT).show();
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

}





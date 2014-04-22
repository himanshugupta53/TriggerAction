package com.himanshugupta53.triggeraction.main;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.trigger.TriggerActivity;
import com.himanshugupta53.triggeraction.utility.Config;
import com.himanshugupta53.triggeraction.utility.DialogList;
import com.himanshugupta53.triggeraction.utility.MainArrayAdapter;
import com.himanshugupta53.triggeraction.utility.MainListActivity;
import com.himanshugupta53.triggeraction.utility.MyService;
import com.himanshugupta53.triggeraction.utility.MyUserPreferences;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

public class MainActivity extends MainListActivity implements OnClickListener{

	TriggerActionParser[] triggerActionParsers = null;
	DialogList dialog = null;
	TriggerActionParser triggerActionSelected = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		MyUserPreferences.setContext(this);
		super.setLayout(R.layout.main_activity_row_layout);
		triggerActionParsers = TriggerActionParser.getAllDefinedTriggerActions();
		super.setTAPValues(triggerActionParsers);
		super.onCreate(savedInstanceState);
		Intent intent= new Intent(this, MyService.class);
		startService(intent);
		
		ListView lv = (ListView) findViewById(android.R.id.list);
		Drawable bg = lv.getBackground();
	    if (bg != null) {
	        if (bg instanceof BitmapDrawable) {
	            BitmapDrawable bmp = (BitmapDrawable) bg;
	            bmp.mutate(); // make sure that we aren't sharing state anymore
	            bmp.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
	        }
	    }
	}
	
	@Override
	protected void onNewIntent(Intent intent){
		if (Config.triggerActionSet){
			Config.triggerActionSet = false;
			refreshTriggerActionList();
		}
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
			Config.resetData();
			Toast.makeText(this, "Choose a trigger to define an action on!", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

		return true;
	} 

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		triggerActionSelected = triggerActionParsers[position];
		dialog = new DialogList(this);
		List<String> options = new ArrayList<String>();
		List<String> tags = new ArrayList<String>();
		options.add("Delete Trigger Action");
		options.add("Change Name");
		tags.add("delete");
		tags.add("name");
		dialog.setValues(options);
		dialog.setData(tags);
		dialog.show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (Config.triggerActionSet){
			Config.triggerActionSet = false;
			refreshTriggerActionList();
		}
	}
	
	private void refreshTriggerActionList(){
		triggerActionParsers = TriggerActionParser.getAllDefinedTriggerActions(); 
		super.setTAPValues(triggerActionParsers);
		((MainArrayAdapter)getListAdapter()).notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		TextView textView = (TextView) v.findViewById(R.id.textView);
		if (textView.getTag().equals("delete")){
			triggerActionSelected.deleteFromUserPreferences();
			refreshTriggerActionList();
		}
		else if (textView.getTag().equals("name")){
			final EditText input = new EditText(this);
			new AlertDialog.Builder(this)
		    .setTitle("Edit Name")
		    .setMessage("Enter a name for the selected trigger action")
		    .setView(input)
		    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int whichButton) {
		            String newName = input.getText().toString();
		            if (newName == null || newName.equals(""))
		            	return;
		            if (newName.length() > 10)
		            	newName = newName.substring(0, 10);
		            triggerActionSelected.editInUserPreferences(null, null, newName);
		            refreshTriggerActionList();
		        }
		    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int whichButton) {
		            // Do nothing.
		        }
		    }).show();
		}
		dialog.dismiss();
		
	}

}

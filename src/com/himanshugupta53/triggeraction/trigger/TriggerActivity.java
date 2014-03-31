package com.himanshugupta53.triggeraction.trigger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

public class TriggerActivity extends CustomListActivity implements OnClickListener {

	private DialogList dialog;
	private List<String> groupList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Set<String> groupStrings = TriggerModelGroup.getGroupStrings();
		List<String> titleStrings = new ArrayList<String>();
		List<String> descriptionStrings = new ArrayList<String>();
		groupList = new ArrayList<String>();
		for (String grp : groupStrings){
			titleStrings.add(TriggerModelGroup.getTitleOfGroup(this, grp));
			descriptionStrings.add(TriggerModelGroup.getDescriptionOfGroup(this, grp));
			groupList.add(grp);
		}
		super.setTitleValues(titleStrings);
		super.setDescriptionValues(descriptionStrings);
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

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		dialog = new DialogList(this);
		TriggerModelGroup[] tMG = TriggerModelGroup.getTriggersOfGroup(groupList.get(position));
		dialog.setValues(tMG);
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

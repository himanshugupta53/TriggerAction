package com.himanshugupta53.triggeraction.utility;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

import com.himanshugupta53.triggeraction.R;

public class CustomExpandableListActivity extends ExpandableListActivity {

	private CustomExpandableListAdapter expandableListAdapter = null;
	private ExpandableListView expandableListView = null;
	protected SparseArray<ModelGroup> groups = new SparseArray<ModelGroup>();
	protected int subitemLayoutFile = R.layout.listrow_details;
	protected OnClickListener myListItemClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};

	protected OnGroupClickListener myListGroupClicked = new OnGroupClickListener() {
		@Override
		public boolean onGroupClick(ExpandableListView parent, View v,int groupPosition, long id) {
			return false;
		}

	};

	protected void setGroups(SparseArray<ModelGroup> vals){
		groups = vals;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_expandable_list);
		expandableListView = getExpandableListView();
		expandableListAdapter = new CustomExpandableListAdapter(this, groups, subitemLayoutFile);
		expandableListAdapter.setSubItemClickListener(myListItemClicked);
		setListAdapter(expandableListAdapter);
		expandableListView.setOnGroupClickListener(myListGroupClicked);
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
			Toast.makeText(this, "Back Action Selected", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

		return true;
	}

}

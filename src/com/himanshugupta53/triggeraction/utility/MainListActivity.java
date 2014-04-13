package com.himanshugupta53.triggeraction.utility;

import android.os.Bundle;

import com.himanshugupta53.triggeraction.R;

public class MainListActivity extends CustomListActivity {
	
	private TriggerActionParser[] tAPValues = null;
	MainArrayAdapter adapter = null;
	
	public void setTAPValues(TriggerActionParser[] values){
		tAPValues = values;
		if (adapter != null){
			adapter.setTAPValues(values);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_list_view);
		adapter = new MainArrayAdapter(this, layout, tAPValues);
		setListAdapter(adapter);
	}
	
}

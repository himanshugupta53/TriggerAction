package com.himanshugupta53.triggeraction.utility;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import com.himanshugupta53.triggeraction.R;

public class DialogList extends CustomDialog {

	private Activity context = null;
	private String[] values = null;

	public DialogList(Context _context) {
		super(_context);
		context = (Activity)_context;
		setLayout(R.layout.dialog_list);
		setTitleString("Choose a Trigger Point!");
		values = new String[]{};
	}

	public void setValues(String[] list){
		values = list;
	}
	
	@Override
	public void setValuesToLayoutFields() {
		CustomArrayAdapter adapter = null;
		adapter = new DialogListArrayAdapter(context, values);
		ListView lV = (ListView) findViewById(R.id.dialogList);
		lV.setAdapter(adapter);
	}

}

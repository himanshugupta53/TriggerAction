package com.himanshugupta53.triggeraction.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;

public class DialogList extends CustomDialog {

	private Activity context = null;
	private List<String> values = null;
	private List<?> data = null;

	public DialogList(Context _context) {
		super(_context);
		context = (Activity)_context;
		setLayout(R.layout.dialog_list);
		setTitleString("Choose a Trigger Point!");
		values = new ArrayList<String>();
	}

	public void setValues(List<String> list){
		values = list;
	}
	
	public void setData(List<?> list){
		data = list;
	}
	
	@Override
	public void setValuesToLayoutFields() {
		DialogListArrayAdapter adapter = new DialogListArrayAdapter(context, values);
		adapter.setData(data);
		ListView lV = (ListView) findViewById(R.id.dialogList);
		lV.setAdapter(adapter);
	}

}

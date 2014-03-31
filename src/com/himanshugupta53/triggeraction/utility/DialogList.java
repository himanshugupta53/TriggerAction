package com.himanshugupta53.triggeraction.utility;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;

public class DialogList extends CustomDialog {

	private Activity context = null;
	private TriggerModelGroup[] values = null;

	public DialogList(Context _context) {
		super(_context);
		context = (Activity)_context;
		setLayout(R.layout.dialog_list);
		setTitleString("Choose a Trigger Point!");
		values = new TriggerModelGroup[]{};
	}

	public void setValues(TriggerModelGroup[] list){
		values = list;
	}
	
	@Override
	public void setValuesToLayoutFields() {
		CustomArrayAdapter adapter = null;
		List<String> strVals = new ArrayList<String>();
		for (TriggerModelGroup t : values){
			strVals.add(t.getFullDescription(context));
		}
		adapter = new DialogListArrayAdapter(context, strVals);
		ListView lV = (ListView) findViewById(R.id.dialogList);
		lV.setAdapter(adapter);
	}

}

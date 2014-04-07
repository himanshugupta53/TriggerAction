package com.himanshugupta53.triggeraction.trigger;

import com.himanshugupta53.triggeraction.R;

import android.content.Context;
import android.widget.ListView;

public class TimeAtDialog extends TriggerDialog {

	public TimeAtDialog(Context context) {
		super(context);
		setLayout(R.layout.time_at_dialog);
	}

	@Override
	public void startActivity() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		((TriggerActivity)context).hideProgressDialog(null);
	}

	@Override
	public void setResult(Object o) {
	}

	@Override
	public void setValuesToLayoutFields() {
	}

}

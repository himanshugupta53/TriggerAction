package com.himanshugupta53.triggeraction.trigger;

import android.content.Context;

import com.himanshugupta53.triggeraction.utility.CustomDialog;

public abstract class TriggerDialog extends CustomDialog {

	public TriggerDialog(Context context) {
		super(context);
	}

	public abstract void startActivity();
	public abstract void finishActivity();
}

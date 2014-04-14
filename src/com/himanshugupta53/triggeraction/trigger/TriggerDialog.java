package com.himanshugupta53.triggeraction.trigger;

import android.content.Context;

import com.himanshugupta53.triggeraction.utility.CustomDialog;


//Actually this dialog can be used by ActionActivity as well
//This should be InputDialog instead of being called TriggerDialog
public abstract class TriggerDialog extends CustomDialog {

	public TriggerDialog(Context context) {
		super(context);
	}

	public abstract void startActivity();
	public abstract void finishActivity();
	public abstract void setResult(Object o);
}

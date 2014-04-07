package com.himanshugupta53.triggeraction.utility;

import android.app.Dialog;
import android.content.Context;

import com.himanshugupta53.triggeraction.R;

public abstract class CustomDialog extends Dialog {

	private int dialogLayout;
	private String title;
	protected Context context;
	
	public CustomDialog(Context _context) {
		super(_context);
		context = _context;
		dialogLayout = R.layout.custom_dialog;
		title = "Trigger Action";
	}

	public void setLayout(int layout){
		dialogLayout = layout;
	}
	
	public void setTitleString(String _title){
		title = _title;
	}
	
	public abstract void setValuesToLayoutFields();
	
	public void show(){
		setContentView(dialogLayout);
		setValuesToLayoutFields();
		setTitle(title);
		super.show();
	}
	
}

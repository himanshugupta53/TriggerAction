package com.himanshugupta53.triggeraction.trigger;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.utility.Config;

public class TimeFromToDialog extends TriggerDialog implements OnClickListener{

	NumberPicker hourDurationPicker, minuteDurationPicker;
	Button cancelButton, okButton;
	
	public TimeFromToDialog(Context context) {
		super(context);
		setLayout(R.layout.time_from_to_dialog);
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
		setTitleString("Set Duration");
		
		hourDurationPicker = (NumberPicker) findViewById(R.id.hourDirationPicker);
		hourDurationPicker.setMinValue(0);
		hourDurationPicker.setMaxValue(23);
		hourDurationPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		minuteDurationPicker = (NumberPicker) findViewById(R.id.minuteDurationPicker);
		minuteDurationPicker.setMinValue(0);
		minuteDurationPicker.setMaxValue(59);
		minuteDurationPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		cancelButton = (Button) findViewById(R.id.timeSetCancelButton);
		cancelButton.setOnClickListener(this);
		okButton = (Button) findViewById(R.id.timeSetOKButton);
		okButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == cancelButton){
			onBackPressed();
		}
		else{
			int hourVal = hourDurationPicker.getValue();
			int minuteVal = minuteDurationPicker.getValue();
			int duration = hourVal * 3600 + minuteVal * 60;
			long currentTime = System.currentTimeMillis();
			Config.addTriggerInput(currentTime + "");
			Config.addTriggerInput((currentTime+duration*1000) + "");
			((TriggerActivity)context).goToActionActivity();
			onBackPressed();
		}
		
	}
	
}

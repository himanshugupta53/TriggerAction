package com.himanshugupta53.triggeraction.trigger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TimePicker;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.utility.Config;
import com.himanshugupta53.triggeraction.utility.Utility;

public class TimeAtDialog extends TriggerDialog implements OnValueChangeListener, OnClickListener{

	NumberPicker monthPicker, datePicker, yearPicker;
	TimePicker timePicker;
	String[] months = null, years = null;
	int currentYear = 0, currentDate = 0, currentMonth = 0, currentHour = 0, currentMinute = 0, currentAMPM = 0;
	Button cancelButton, okButton;
	
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
		setTitleString("Set Trigger Time");
		
		months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
		Calendar c = Calendar.getInstance(); 
		currentYear = c.get(Calendar.YEAR);
		currentMonth = c.get(Calendar.MONTH);
		currentDate = c.get(Calendar.DATE);
		currentHour = c.get(Calendar.HOUR);
		currentMinute = c.get(Calendar.MINUTE);
		currentAMPM = c.get(Calendar.AM_PM);
		years = new String[]{""+currentYear, ""+(currentYear+1), ""+(currentYear+2), ""+(currentYear+3), ""+(currentYear+4)};
		
		yearPicker = (NumberPicker) findViewById(R.id.yearPicker);
		yearPicker.setMinValue(1);
		yearPicker.setMaxValue(5);
		yearPicker.setDisplayedValues(years);
		yearPicker.setOnValueChangedListener(this);
		yearPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		monthPicker = (NumberPicker) findViewById(R.id.monthPicker);
		monthPicker.setMinValue(1);
		monthPicker.setMaxValue(12);
		monthPicker.setDisplayedValues(months);
		monthPicker.setOnValueChangedListener(this);
		monthPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		datePicker = (NumberPicker) findViewById(R.id.datePicker);
		datePicker.setMinValue(1);
		datePicker.setMaxValue(31);
		datePicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		yearPicker.setValue(1);
		monthPicker.setValue(currentMonth+1);
		datePicker.setValue(currentDate);
		
		timePicker = (TimePicker) findViewById(R.id.timePicker);
		if (currentAMPM == 1)
			currentHour += 12;
		timePicker.setCurrentHour(currentHour);
		timePicker.setCurrentMinute(currentMinute);
		
		cancelButton = (Button) findViewById(R.id.timeSetCancelButton);
		cancelButton.setOnClickListener(this);
		okButton = (Button) findViewById(R.id.timeSetOKButton);
		okButton.setOnClickListener(this);
	}

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		datePicker.setMaxValue(Utility.noOfDaysInMonth(monthPicker.getValue(), yearPicker.getValue() + currentYear - 1));
	}

	@Override
	public void onClick(View v) {
		if (v == cancelButton){
			onBackPressed();
		}
		else{
			int monthVal = monthPicker.getValue();
			String month = monthVal < 10 ? "0"+monthVal : ""+monthVal;
			int dateVal = datePicker.getValue();
			String date = dateVal < 10 ? "0"+dateVal : "" + dateVal;
			String year = ""+(yearPicker.getValue() + currentYear - 1);
			int hourVal = timePicker.getCurrentHour();
			String hour = hourVal < 10 ? "0" + hourVal : "" + hourVal;
			int minuteVal = timePicker.getCurrentMinute();
			String minute = minuteVal < 10 ? "0" + minuteVal : "" + minuteVal;
			long epochTime = 0;
			try {
				epochTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(month+"/"+date+"/"+year+" "+hour+":"+minute+":00").getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Config.addTriggerInput(epochTime + "");
			((TriggerActivity)context).goToActionActivity();
			onBackPressed();
		}
		
	}
	
}

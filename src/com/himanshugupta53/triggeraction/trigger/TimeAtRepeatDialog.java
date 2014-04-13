package com.himanshugupta53.triggeraction.trigger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.utility.Config;

public class TimeAtRepeatDialog extends TriggerDialog implements OnTimeChangedListener, OnClickListener, OnItemSelectedListener {

	TimePicker timePicker;
	TextView startTimeTextView;
	int currentYear = 0, currentDate = 0, currentMonth = 0, currentHour = 0, currentMinute = 0, currentAMPM = 0;
	Button cancelButton, okButton;
	String spinnerSelectedValue;
	EditText noOfTimesTextView;

	public TimeAtRepeatDialog(Context context) {
		super(context);
		setLayout(R.layout.time_at_repeat_dialog);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void setValuesToLayoutFields() {
		setTitleString("Set Trigger Time");

		startTimeTextView = (TextView) findViewById(R.id.timeText);

		Calendar c = Calendar.getInstance(); 
		currentYear = c.get(Calendar.YEAR);
		currentMonth = c.get(Calendar.MONTH) + 1;
		currentDate = c.get(Calendar.DATE);
		currentHour = c.get(Calendar.HOUR);
		currentMinute = c.get(Calendar.MINUTE);
		currentAMPM = c.get(Calendar.AM_PM);

		timePicker = (TimePicker) findViewById(R.id.timePicker);
		if (currentAMPM == 1)
			currentHour += 12;
		timePicker.setCurrentHour(currentHour);
		timePicker.setCurrentMinute(currentMinute);
//		timePicker.setOnTimeChangedListener(this);
		
		cancelButton = (Button) findViewById(R.id.timeSetCancelButton);
		cancelButton.setOnClickListener(this);
		okButton = (Button) findViewById(R.id.timeSetOKButton);
		okButton.setOnClickListener(this);
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setOnItemSelectedListener(this);
		
		noOfTimesTextView = (EditText) findViewById(R.id.count);
	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		boolean am = true;
		if (hourOfDay > 12){
			hourOfDay -= 12;
			am = false;
		}
		startTimeTextView.setText("Starting time is "+(hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay)+":"+(minute < 10 ? "0"+minute : ""+minute)+" "+(am ? "am" : "pm"));
	}
	
	@Override
	public void onClick(View v) {
		if (v == cancelButton){
			onBackPressed();
		}
		else{
			String month = currentMonth < 10 ? "0"+currentMonth : ""+currentMonth;
			String date = currentDate < 10 ? "0"+currentDate : "" + currentDate;
			String year = ""+currentYear;
			int hourVal = timePicker.getCurrentHour();
			String hour = hourVal < 10 ? "0" + hourVal : "" + hourVal;
			int minuteVal = timePicker.getCurrentMinute();
			String minute = minuteVal < 10 ? "0" + minuteVal : "" + minuteVal;
			long currentTime = System.currentTimeMillis();
			long epochTime = 0;
			try {
				epochTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(month+"/"+date+"/"+year+" "+hour+":"+minute+":00").getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (epochTime < currentTime){
				epochTime += 86400000;//1 day
			}
			Config.addTriggerInput(epochTime + "");
			Config.addTriggerInput(spinnerSelectedValue);
			Config.addTriggerInput(noOfTimesTextView.getText().toString());
			((TriggerActivity)context).goToActionActivity();
			onBackPressed();
		}
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {
		spinnerSelectedValue = parent.getItemAtPosition(pos).toString().toLowerCase();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		spinnerSelectedValue = "onetime";
	}

}

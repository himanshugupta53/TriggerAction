package com.himanshugupta53.triggeraction;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AlertDialogActivity extends Activity implements OnClickListener {

	TextView description = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alert_dialog);
		
		String desc = getIntent().getStringExtra("description");
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		
		if (desc != null && !desc.equals("")){
			description = (TextView) findViewById(R.id.description);
			description.setText(desc);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alert_dialog, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		onBackPressed();
	}

}

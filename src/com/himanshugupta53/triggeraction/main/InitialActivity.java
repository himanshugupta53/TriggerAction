package com.himanshugupta53.triggeraction.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.himanshugupta53.triggeraction.NotificationChooser;
import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.trigger.TriggerActivity;
import com.himanshugupta53.triggeraction.utility.Config;

public class InitialActivity extends Activity implements OnClickListener {

	RelativeLayout relativeLayoutNewTriggerAction, relativeLayoutExistingTriggerAction, relativeLayoutNotificationType, relativeLayoutDefaultTriggerAction;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial);
		
		relativeLayoutNewTriggerAction = (RelativeLayout) findViewById(R.id.relativeLayoutNewTriggerAction);
		relativeLayoutExistingTriggerAction = (RelativeLayout) findViewById(R.id.relativeLayoutExistingTriggerAction);
		relativeLayoutNotificationType = (RelativeLayout) findViewById(R.id.relativeLayoutNotificationType);
		relativeLayoutDefaultTriggerAction = (RelativeLayout) findViewById(R.id.relativeLayoutDefaultTriggerAction);
		
		relativeLayoutNewTriggerAction.setOnClickListener(this);
		relativeLayoutExistingTriggerAction.setOnClickListener(this);
		relativeLayoutNotificationType.setOnClickListener(this);
		relativeLayoutDefaultTriggerAction.setOnClickListener(this);
		
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.initialActivityLinearLayout);
		Drawable bg = linearLayout.getBackground();
	    if (bg != null) {
	        if (bg instanceof BitmapDrawable) {
	            BitmapDrawable bmp = (BitmapDrawable) bg;
	            bmp.mutate(); // make sure that we aren't sharing state anymore
	            bmp.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
	        }
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.initial, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == relativeLayoutNewTriggerAction){
			Intent intent = new Intent(this, TriggerActivity.class);
			startActivity(intent);
			Config.resetData();
			Toast.makeText(this, "Choose a trigger to define an action on!", Toast.LENGTH_SHORT).show();
		}
		else if (v == relativeLayoutExistingTriggerAction){
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		else if (v == relativeLayoutNotificationType){
			Intent intent = new Intent(this, NotificationChooser.class);
			startActivity(intent);
		}
		else if (v == relativeLayoutDefaultTriggerAction){
			
		}
	}

}

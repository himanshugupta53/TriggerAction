package com.himanshugupta53.triggeraction.action;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.main.MainActivity;
import com.himanshugupta53.triggeraction.trigger.TriggerDialog;
import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.Config;
import com.himanshugupta53.triggeraction.utility.CustomListActivity;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;

public class ActionActivity extends CustomListActivity {

	private ActionModelGroup[] actionModelGroupValues = null;
	private Handler handler;
    private ProgressDialog progress;
    private TriggerDialog actionInputDialog;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle extras = getIntent().getExtras();
		TriggerModelGroup trigger = null;
		if (extras != null){
			trigger = (TriggerModelGroup) extras.get("trigger");
		}
		actionModelGroupValues = ActionModelGroup.getValuesForTrigger(trigger);
		List<String> titleStrings = new ArrayList<String>();
		List<String> descriptionStrings = new ArrayList<String>();
		for (ActionModelGroup aMG : actionModelGroupValues){
			titleStrings.add(aMG.getTitle(this));
			descriptionStrings.add(aMG.getDescription(this));
		}
		super.setTitleValues(titleStrings);
		super.setDescriptionValues(descriptionStrings);
		super.onCreate(savedInstanceState);
		
		ListView lv = (ListView) findViewById(android.R.id.list);
		Drawable bg = lv.getBackground();
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
		getMenuInflater().inflate(R.menu.action, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// action with ID action_addtriggeraction was selected
		case R.id.action_back:
			onBackPressed();
			break;
		default:
			break;
		}

		return true;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		ActionModelGroup aMG = actionModelGroupValues[position];
		Config.action = aMG;
		if (aMG.getNoOfInputs() == 0){
			setTriggerAction();
		}
		else{
			actionInputDialog = Config.action.getDialogPopup(this);
			showProgessDialog();
		}
		
	}

	public void setTriggerAction(){
		TriggerActionParser triggerAction = new TriggerActionParser();
		triggerAction.trigger = Config.trigger;
		triggerAction.action = Config.action;
		triggerAction.triggerInputs = Config.getTriggerInputs();
		triggerAction.actionInputs = Config.getActionInputs();
		triggerAction.performActionOnTrigger(this);
		Config.triggerActionSet = true;
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void showProgessDialog(){
		progress = new ProgressDialog(this);
        progress.setTitle("Please Wait!!");
        progress.setMessage("Wait!!");
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        
        handler = new Handler()
        {

            @Override
            public void handleMessage(Message msg)
            {
                actionInputDialog.setResult(msg.obj);
                actionInputDialog.show();
                super.handleMessage(msg);
                progress.dismiss();
            }

        };
        
        progress.show();
        actionInputDialog.startActivity();
	}
	
	public void hideProgressDialog(Object list){
		Message msg = Message.obtain();
		msg.obj = list;
		handler.dispatchMessage(msg);
	}
}

package com.himanshugupta53.triggeraction.action;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.trigger.TriggerDialog;
import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;
import com.himanshugupta53.triggeraction.utility.Config;
import com.himanshugupta53.triggeraction.utility.CustomListActivity;
import com.himanshugupta53.triggeraction.utility.MyService;
import com.himanshugupta53.triggeraction.utility.TriggerActionParser;
import com.himanshugupta53.triggeraction.utility.broadcastreceiver.WifiStateChangedReceiver;

public class ActionActivity extends CustomListActivity {

	private ActionModelGroup[] actionModelGroupValues = null;
	private Handler handler;
    private ProgressDialog progress;
    private TriggerDialog actionInputDialog;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		actionModelGroupValues = ActionModelGroup.values();
		List<String> titleStrings = new ArrayList<String>();
		List<String> descriptionStrings = new ArrayList<String>();
		for (ActionModelGroup aMG : actionModelGroupValues){
			titleStrings.add(aMG.getTitle(this));
			descriptionStrings.add(aMG.getDescription(this));
		}
		super.setTitleValues(titleStrings);
		super.setDescriptionValues(descriptionStrings);
		super.onCreate(savedInstanceState);
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
		onBackPressed();
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
                progress.dismiss();
                actionInputDialog.setResult(msg.obj);
                actionInputDialog.show();
                super.handleMessage(msg);
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

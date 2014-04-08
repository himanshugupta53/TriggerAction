package com.himanshugupta53.triggeraction.trigger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.action.ActionActivity;
import com.himanshugupta53.triggeraction.utility.Config;
import com.himanshugupta53.triggeraction.utility.CustomListActivity;
import com.himanshugupta53.triggeraction.utility.DialogList;

public class TriggerActivity extends CustomListActivity implements OnClickListener {

	private DialogList dialog;
	private List<String> groupList;
	private TriggerDialog triggerInputDialog;
	private Handler handler;
    private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Set<String> groupStrings = TriggerModelGroup.getGroupStrings();
		List<String> titleStrings = new ArrayList<String>();
		List<String> descriptionStrings = new ArrayList<String>();
		groupList = new ArrayList<String>();
		for (String grp : groupStrings){
			titleStrings.add(TriggerModelGroup.getTitleOfGroup(this, grp));
			descriptionStrings.add(TriggerModelGroup.getDescriptionOfGroup(this, grp));
			groupList.add(grp);
		}
		super.setTitleValues(titleStrings);
		super.setDescriptionValues(descriptionStrings);
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trigger, menu);
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
		dialog = new DialogList(this);
		List<TriggerModelGroup> tMG = TriggerModelGroup.getTriggersOfGroup(groupList.get(position));
		List<String> tMGstr = new ArrayList<String>();
		for (TriggerModelGroup t : tMG){
			tMGstr.add(t.getFullDescription(this));
		}
		dialog.setValues(tMGstr);
		dialog.setData(tMG);
		dialog.show();
	}


	@Override
	public void onBackPressed(){
		super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		TextView textView = (TextView) v.findViewById(R.id.textView);
		TriggerModelGroup tMG = (TriggerModelGroup)textView.getTag();
		Config.trigger = tMG;
		dialog.dismiss();
		if (tMG.getNoOfInputs() == 0){
			Intent intent = new Intent(this, ActionActivity.class);
			startActivity(intent);
		}
		else{
			triggerInputDialog = Config.trigger.getDialogPopup(this);
			this.showProgessDialog();
		}
	}
	
	public void goToActionActivity(){
		Intent intent = new Intent(this, ActionActivity.class);
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
                progress.dismiss();
                triggerInputDialog.setResult(msg.obj);
                triggerInputDialog.show();
                super.handleMessage(msg);
            }

        };
        
        progress.show();
        triggerInputDialog.startActivity();
	}
	
	public void hideProgressDialog(Object list){
		Message msg = Message.obtain();
		msg.obj = list;
		handler.dispatchMessage(msg);
	}
}

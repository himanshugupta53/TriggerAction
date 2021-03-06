package com.himanshugupta53.triggeraction.trigger;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.action.ActionActivity;
import com.himanshugupta53.triggeraction.utility.Config;

public class ListOfAppsDialog extends TriggerDialog implements OnClickListener{

	private PackageManager packageManager;
	private List<PackageInfo> packageList;
	boolean removeSystemApps = true;

	public ListOfAppsDialog(Context context) {
		super(context);
		setLayout(R.layout.dialog_list);
	}

	@Override
	public void startActivity() {
		packageManager = context.getPackageManager();
		List<PackageInfo> packageListTemp = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
		packageList = new ArrayList<PackageInfo>();

		/*To filter out System apps*/
		for(PackageInfo pi : packageListTemp) {
			boolean b = removeSystemApps && isSystemPackage(pi);
			if(!b) {
				packageList.add(pi);
			}
		}
		finishActivity();

	}

	@Override
	public void finishActivity() {
		if (context instanceof TriggerActivity){
			((TriggerActivity)context).hideProgressDialog(null);
		}
		else if(context instanceof ActionActivity){
			((ActionActivity)context).hideProgressDialog(null);
		} 
	}

	@Override
	public void setResult(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValuesToLayoutFields() {
		AppListArrayAdapter adapter = null;
		adapter = new AppListArrayAdapter(context, packageList, packageManager);
		adapter.setClickListener(this);
		ListView lV = (ListView) findViewById(R.id.dialogList);
		lV.setAdapter(adapter);
	}

	private boolean isSystemPackage(PackageInfo pkgInfo) {
		return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
	}

	@Override
	public void onClick(View v) {
		RelativeLayout rL = (RelativeLayout)v;
		TextView tV = (TextView) rL.findViewById(R.id.appName);
		if (context instanceof TriggerActivity){
			Config.addTriggerInput((String)tV.getTag());
			((TriggerActivity)context).goToActionActivity();
		}
		else if(context instanceof ActionActivity){
			Config.addActionInput((String)tV.getTag());
			((ActionActivity) context).setTriggerAction();
		} 
		onBackPressed();
	}

}

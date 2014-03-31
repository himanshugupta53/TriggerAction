package com.himanshugupta53.triggeraction.utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import com.himanshugupta53.triggeraction.R;

@SuppressLint("NewApi")
public class CustomListPopupWindow extends ListPopupWindow implements OnItemClickListener {

	Activity context = null;
	private String[] listOfItems = null;
	private View anchorView = null;
	private int verticalOffset, horizontalOffset;
	private OnItemClickListener clickListener = this;
	
	public CustomListPopupWindow(Context _context) {
		super(_context);
		context = (Activity)_context;
		listOfItems = new String[]{};
		anchorView = context.findViewById(R.id.action_back);
		verticalOffset = 10;
		horizontalOffset = -500;
		clickListener = this;
	}
	
	public void setListOfItems(String[] list){
		listOfItems = list;
	}
	
	public void setClickListener(OnItemClickListener _clickListener){
		clickListener = _clickListener;
	}
	
	@SuppressLint("NewApi")
	public void show(){
		CustomArrayAdapter adapter = new CustomArrayAdapter(context, listOfItems);
		setAdapter(adapter);
		setAnchorView(anchorView);
		setVerticalOffset(verticalOffset);
		setHorizontalOffset(horizontalOffset);
		setWidth(WindowManager.LayoutParams.MATCH_PARENT);
		setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		setModal(true);
		setOnItemClickListener(clickListener);
		super.show();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Toast.makeText(context, "Implement your own click listener", Toast.LENGTH_SHORT).show();	
	}

}

package com.himanshugupta53.triggeraction.utility;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.himanshugupta53.triggeraction.R;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter{

	private final SparseArray<ModelGroup> groups;
	public LayoutInflater inflater;
	public Activity activity;
	private int layoutFile;
	private OnClickListener subitemClickListener = null;

	public CustomExpandableListAdapter(Activity act, SparseArray<ModelGroup> groups, int layout) {
		activity = act;
		this.groups = groups;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutFile = layout;
	}

	public void setSubItemClickListener(OnClickListener myListItemClicked){
		subitemClickListener = myListItemClicked;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groups.get(groupPosition).children.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final String children = (String) getChild(groupPosition, childPosition);
		TextView text = null;
		if (convertView == null) {
			convertView = inflater.inflate(layoutFile, null);
		}
		text = (TextView) convertView.findViewById(R.id.textView1);
		text.setText(children);
		if (subitemClickListener != null){
			convertView.setOnClickListener(subitemClickListener);
		}
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groups.get(groupPosition).children.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.rowlayout, null);
		}
		ModelGroup group = (ModelGroup) getGroup(groupPosition);
		((TextView) convertView.findViewById(R.id.firstLine)).setText(group.title);
		((TextView) convertView.findViewById(R.id.secondLine)).setText(group.description);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}

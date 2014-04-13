package com.himanshugupta53.triggeraction.utility;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.himanshugupta53.triggeraction.R;


public class MainArrayAdapter extends CustomArrayAdapter {

	TriggerActionParser[] tAPValues = null;
	
	static class ViewHolder {
		public TextView title, description;
		public ImageView triggerImage, actionImage;
	}
	
	public MainArrayAdapter(Context context, int _layout, TriggerActionParser[] values){
		this(context, _layout, null, null);
		tAPValues = values;
	}
	
	public MainArrayAdapter(Context context, List<String> titleValues, List<String> descriptionValues) {
		super(context, titleValues, descriptionValues);
	}
	
	public MainArrayAdapter(Context context, int _layout, List<String> titleValues, List<String> descriptionValues) {
		super(context, _layout, titleValues, descriptionValues);
	}
	
	public void setTAPValues(TriggerActionParser[] values){
		tAPValues = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		// reuse views
		if (rowView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(layout, null);
			// configure view holder
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.title = (TextView) rowView.findViewById(R.id.triggerActionTitle);
			viewHolder.description = (TextView) rowView.findViewById(R.id.triggerActionDescription);
			viewHolder.triggerImage = (ImageView) rowView.findViewById(R.id.triggerImage);
			viewHolder.actionImage = (ImageView) rowView.findViewById(R.id.actionImage);
			rowView.setTag(viewHolder);
		}

		// fill data
		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.title.setText(tAPValues[position].getName());
		holder.description.setText("Trigger is " + tAPValues[position].trigger.toString() + " and action is " + tAPValues[position].action.toString());
		holder.triggerImage.setImageResource(R.drawable.ic_launcher);
		holder.actionImage.setImageResource(R.drawable.ic_launcher);
		
		return rowView;
	}
	
	@Override
	public int getCount() {
		if (tAPValues != null)
			return tAPValues.length;
		return 0;
	}
}

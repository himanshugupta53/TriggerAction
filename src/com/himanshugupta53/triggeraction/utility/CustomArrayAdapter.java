package com.himanshugupta53.triggeraction.utility;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.himanshugupta53.triggeraction.R;

public class CustomArrayAdapter extends ArrayAdapter<String> {
	
	protected Context context;
	protected String[] titleValues, descriptionValues;
	protected int layout = R.layout.rowlayout;

	static class ViewHolder {
		public TextView firstLine, secondLine;
		public ImageView image;
	}

	public CustomArrayAdapter(Context context, List<String> titleValues, List<String> descriptionValues) {
		super(context, R.layout.rowlayout, titleValues);
		this.context = context;
		if (titleValues != null)
			this.titleValues = titleValues.toArray(new String[titleValues.size()]);
		if (descriptionValues != null)
			this.descriptionValues = descriptionValues.toArray(new String[descriptionValues.size()]);
	}
	
	public CustomArrayAdapter(Context context, int _layout, List<String> titleValues, List<String> descriptionValues) {
		super(context, _layout, titleValues);
		this.layout = _layout;
		this.context = context;
		if (titleValues != null)
			this.titleValues = titleValues.toArray(new String[titleValues.size()]);
		if (descriptionValues != null)
			this.descriptionValues = descriptionValues.toArray(new String[descriptionValues.size()]);
	}

	@Override
	public int getCount() {
		if (titleValues != null)
			return titleValues.length;
		return 0;
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
			viewHolder.firstLine = (TextView) rowView.findViewById(R.id.firstLine);
			viewHolder.secondLine = (TextView) rowView.findViewById(R.id.secondLine);
			viewHolder.image = (ImageView) rowView.findViewById(R.id.icon);
			rowView.setTag(viewHolder);
		}

		// fill data
		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.firstLine.setText(titleValues[position]);
		holder.secondLine.setText(descriptionValues[position]);
		holder.image.setImageResource(R.drawable.ic_launcher);
		
		return rowView;
	}
}
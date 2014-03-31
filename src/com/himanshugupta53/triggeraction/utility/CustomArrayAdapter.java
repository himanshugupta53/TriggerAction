package com.himanshugupta53.triggeraction.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.himanshugupta53.triggeraction.R;

public class CustomArrayAdapter extends ArrayAdapter<String> {
	protected final Context context;
	protected final String[] values;
	protected int layout = R.layout.rowlayout;

	static class ViewHolder {
		public TextView firstLine, secondLine;
		public ImageView image;
	}

	public CustomArrayAdapter(Context context, String[] values) {
		super(context, R.layout.rowlayout, values);
		this.context = context;
		this.values = values;
	}
	
	public CustomArrayAdapter(Context context, int _layout, String[] values) {
		super(context, _layout, values);
		this.layout = _layout;
		this.context = context;
		this.values = values;
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
		String s = values[position];
		holder.firstLine.setText(s);
		holder.secondLine.setText("This is the description");
		if (s.startsWith("Windows7") || s.startsWith("iPhone")
				|| s.startsWith("Solaris")) {
			holder.image.setImageResource(R.drawable.ic_launcher);
		} else {
			holder.image.setImageResource(R.drawable.ic_launcher);
		}

		return rowView;
	}
}
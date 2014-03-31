package com.himanshugupta53.triggeraction.utility;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.himanshugupta53.triggeraction.R;

public class DialogListArrayAdapter extends CustomArrayAdapter {

	protected OnClickListener clickListener;
	
	static class ViewHolder {
		public TextView textView;
	}
	
	public DialogListArrayAdapter(Context context, List<String> values) {
		super(context, R.layout.dialog_list_row, values, null);
		clickListener = (OnClickListener) context;
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
			viewHolder.textView = (TextView) rowView.findViewById(R.id.textView);
			rowView.setTag(viewHolder);
			rowView.setOnClickListener(clickListener);
		}

		// fill data
		ViewHolder holder = (ViewHolder) rowView.getTag();
		String s = titleValues[position];
		holder.textView.setText(s);

		return rowView;
	}

}

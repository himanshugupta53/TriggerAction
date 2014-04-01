package com.himanshugupta53.triggeraction.utility;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.trigger.TriggerModelGroup;

public class DialogListArrayAdapter extends CustomArrayAdapter {

	protected OnClickListener clickListener;
	Map<String, TriggerModelGroup> tMGMap;
	
	static class ViewHolder {
		public TextView textView;
	}
	
	public DialogListArrayAdapter(Context context, List<String> strValues) {
		super(context, R.layout.dialog_list_row, strValues, null);
		clickListener = (OnClickListener) context;
	}

	public void setTMGMap(Map<String, TriggerModelGroup> map){
		tMGMap = map;
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
		if (tMGMap != null)
			holder.textView.setTag(tMGMap.get(s));

		return rowView;
	}

}

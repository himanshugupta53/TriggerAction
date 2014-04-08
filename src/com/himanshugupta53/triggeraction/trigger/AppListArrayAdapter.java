package com.himanshugupta53.triggeraction.trigger;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.himanshugupta53.triggeraction.R;
import com.himanshugupta53.triggeraction.utility.CustomArrayAdapter;

public class AppListArrayAdapter extends CustomArrayAdapter {

	private PackageManager packageManager;
	private List<PackageInfo> packageList;
	protected OnClickListener clickListener;
	
	static class ViewHolder {
		public TextView appName;
		public ImageView image;
	}
	
	public AppListArrayAdapter(Context context, List<PackageInfo> _packageList, PackageManager _packageManager){
		this(context, R.layout.app_details, getListOfPackageInfoStr(_packageList, _packageManager), null);
		packageManager = _packageManager;
		packageList = _packageList;
	}

	public AppListArrayAdapter(Context context, int _layout, List<String> titleValues, List<String> descriptionValues) {
		super(context, _layout, titleValues, descriptionValues);
	}
	
	private static List<String> getListOfPackageInfoStr(List<PackageInfo> _packageList, PackageManager _packageManager){
		List<String> list = new ArrayList<String>();
		for (PackageInfo packageInfo : _packageList){
			String str = _packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
			list.add(str);
		}
		return list;
	}
	
	public void setClickListener(OnClickListener listener){
		clickListener = listener;
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
			viewHolder.appName = (TextView) rowView.findViewById(R.id.appName);
			viewHolder.image = (ImageView) rowView.findViewById(R.id.appIcon);
			rowView.setTag(viewHolder);
			rowView.setOnClickListener(clickListener);
		}

		// fill data
		ViewHolder holder = (ViewHolder) rowView.getTag();
		PackageInfo packageInfo = (PackageInfo) packageList.get(position);
		Drawable appIcon = packageManager.getApplicationIcon(packageInfo.applicationInfo);
		Bitmap bitmap = ((BitmapDrawable) appIcon).getBitmap();
		Drawable dr = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 72, 72, true));
		holder.appName.setText(titleValues[position]);
		holder.image.setImageDrawable(dr);
		holder.appName.setTag(packageList.get(position).packageName);
		
		return rowView;
	}

}

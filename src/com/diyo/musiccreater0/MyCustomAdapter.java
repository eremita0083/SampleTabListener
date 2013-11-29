package com.diyo.musiccreater0;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

class MyCustomAdapter extends ArrayAdapter<String> {
	private int resourceId;
	private Typeface typeface = Typeface.createFromAsset(getContext()
			.getAssets(), "regular.ttf");
	private Map<Integer,Boolean> checkCb = new LinkedHashMap<Integer, Boolean>();

	public MyCustomAdapter(Context context, int resource, String[] objects) {
		super(context, resource, objects);
		this.resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = View.inflate(getContext(), resourceId, null);
		final int positionGv = position;

		TextView text = (TextView) convertView.findViewById(R.id.tv);
		final CheckBox cb = (CheckBox) convertView.findViewById(R.id.cb);

		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					checkCb.put(positionGv, true);
				} else if (!isChecked) {
					checkCb.put(positionGv, false);
				}

			}
		});
		if(checkCb.get(positionGv)==null){
			cb.setChecked(false);
		}else{
			cb.setChecked(checkCb.get(positionGv));
		}
		
		text.setText(getItem(position).toString());
		text.setTypeface(typeface);

		return convertView;
	}
	
	public  Map<Integer,Boolean> getCheckStatus() {
		return checkCb;
	}

}
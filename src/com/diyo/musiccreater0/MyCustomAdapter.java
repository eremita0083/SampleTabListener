package com.diyo.musiccreater0;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class MyCustomAdapter extends ArrayAdapter<String> {
	private int resourceId;
	private Typeface typeface = Typeface.createFromAsset(getContext()
			.getAssets(), "regular.ttf");

	public MyCustomAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		this.resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = View.inflate(getContext(), resourceId, null);

		TextView text = (TextView) convertView
				.findViewById(R.id.text_drawer_lv);

		if (position == 0) {
			text.setBackgroundResource(R.drawable.border);
		}

		text.setText(getItem(position).toString());
		text.setTypeface(typeface);

		return convertView;
	}

}
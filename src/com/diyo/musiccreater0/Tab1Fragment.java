package com.diyo.musiccreater0;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tab1Fragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_tab1, container, false);
		ListView lv = (ListView) v.findViewById(R.id.lv_tab1);
		/* List<String> group1 = getArguments().getStringArrayList("group1"); */
		Fragment fragment = Tab0Fragment.newInstance();
		List<String> group1 = fragment.getArguments().getStringArrayList(
				"group1");

		// groupが作られてたら、リストビューで表示する、なければ何も表示しない
		if (group1 != null) {
			ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, group1);
			lv.setAdapter(ad);
		}

		return v;
	}

	@Override
	public void onStart() {
		super.onStart();

	}
}
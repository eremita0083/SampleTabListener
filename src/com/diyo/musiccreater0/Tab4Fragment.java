package com.diyo.musiccreater0;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tab4Fragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_tab4, container, false);
		ListView lv = (ListView) v.findViewById(R.id.lv_tab4);
		Fragment fragment = Tab0Fragment.newInstance();
		List<String> group2 = fragment.getArguments().getStringArrayList(
				"group4");

		// groupが作られてたら、リストビューで表示する、なければ何も表示しない
		if (group2 != null) {
			ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, group2);
			lv.setAdapter(ad);
		}
		return v;
	}

	@Override
	public void onStart() {
		super.onStart();

	}
}
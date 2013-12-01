package com.diyo.musiccreater0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class Tab0Fragment extends Fragment {
	private String[] meibo = { "安達誠寛", "一瀬孝", "今井俊介", "岩崎大輔", "岩崎拓也", "岩塚美由紀",
			"大司まり", "大津良馬", "嘉村翼", "楠元信吾", "桑原玲", "小西未央子", "佐藤章", "大力新太郎",
			"高倉健治", "壇義弘", "能島章典", "野田幸代", "松島あゆみ", "松本真由美", "山口徹" };
	private String[] gender = { "男", "男", "男", "男", "男", "女", "女", "男", "男",
			"男", "男", "女", "男", "男", "男", "男", "男", "女", "女", "女", "男" };
	private final String[] maleFemale = {"男", "女"};
	private Map<String, String> nameAndGender = new LinkedHashMap<String, String>();
	private MyCustomAdapter ad;
	private static ArrayList<String> group1;
	private static ArrayList<String> group2;
	private static ArrayList<String> group3;
	private static ArrayList<String> group4;
	private static ArrayList<String> group5;
	Map<Integer, Boolean> checkStatus;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_main, container, false);
		final ListView lv = (ListView) v.findViewById(R.id.lv);
		Button btn = (Button) v.findViewById(R.id.btn);

		// 名前と性別を一致させて格納
		for (int i = 0; i < meibo.length; ++i) {
			nameAndGender.put(meibo[i], gender[i]);
		}

		// リストビュー
		ad = new MyCustomAdapter(getActivity(), R.layout.my_listview_item,
				meibo);
		lv.setAdapter(ad);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//dataを新しく作り直すようにしている
				group1 = new ArrayList<String>();
				group2 = new ArrayList<String>();
				group3 = new ArrayList<String>();
				group4 = new ArrayList<String>();
				group5 = new ArrayList<String>();
				//check状態をcustom adapterからとる
				checkStatus = new LinkedHashMap<Integer, Boolean>();
				checkStatus = ad.getCheckStatus();
				//checkも何もされなかったposition番目はnullが返るので、nullのposition番目にはfalseを入れるようにした。
				for(int i = 0 ; i < meibo.length ; ++ i){
					if(checkStatus.get(i)==null){
						checkStatus.put(i, false);
					}
				}
				Log.i("check", checkStatus.size() + "");
				List<String> menList = new ArrayList<String>();
				List<String> womenList = new ArrayList<String>();
				String str;
				// 出席、女、男
				for (int i = 0; i < checkStatus.size(); ++i) {
					Log.i("checkstatus.get" + i, checkStatus.get(i)+"");
					if (checkStatus.get(i) == true) {
						Log.i("check1", checkStatus.get(i) + "");
						if (gender[i].equals(maleFemale[0])) {
							str = lv.getItemAtPosition(i).toString();
							menList.add(str);
						} else if (gender[i].equals(maleFemale[1])) {
							str = (String) lv.getItemAtPosition(i);
							womenList.add(str);
						}
					}
				}
				// shuffle
				Collections.shuffle(menList);
				Collections.shuffle(womenList);

				// group分け男性
				for (int i = 0; i < menList.size(); ++i) {
					if (i % 5 == 0) {
						group1.add(menList.get(i));
						continue;
					} else if (i % 5 == 1) {
						group2.add(menList.get(i));
						continue;
					} else if (i % 5 == 2) {
						group3.add(menList.get(i));
						continue;
					} else if (i % 5 == 3) {
						group4.add(menList.get(i));
						continue;
					} else if (i % 5 == 4) {
						group5.add(menList.get(i));
					}
				}
				// group分け女性
				for (int i = 0; i < womenList.size(); ++i) {
					if (i % 5 == 4) {
						group1.add(womenList.get(i));
						continue;
					} else if (i % 5 == 3) {
						group2.add(womenList.get(i));
						continue;
					} else if (i % 5 == 2) {
						group3.add(womenList.get(i));
						continue;
					} else if (i % 5 == 1) {
						group4.add(womenList.get(i));
						continue;
					} else if (i % 5 == 0) {
						group5.add(womenList.get(i));
					}
				}
				// このデータを各リストビューに渡す
				for (int i = 0; i < group1.size(); i++) {
					Log.i("group1", group1.get(i));
				}
				for (int i = 0; i < group2.size(); i++) {
					Log.i("group2", group2.get(i));
				}
				for (int i = 0; i < group3.size(); i++) {
					Log.i("group3", group3.get(i));
				}
				for (int i = 0; i < group4.size(); i++) {
					Log.i("group4", group4.get(i));
				}
				for (int i = 0; i < group5.size(); i++) {
					Log.i("group5", group5.get(i));
				}

			}
		});

		return v;
	}

	public static Tab0Fragment newInstance() {
		Tab0Fragment fragment = new Tab0Fragment();
		// 引数を設定
		Bundle args = new Bundle();
		args.putStringArrayList("group1", group1);
		args.putStringArrayList("group2", group2);
		args.putStringArrayList("group3", group3);
		args.putStringArrayList("group4", group4);
		args.putStringArrayList("group5", group5);
		fragment.setArguments(args);
		return fragment;
	}
}
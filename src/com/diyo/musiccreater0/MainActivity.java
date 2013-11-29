package com.diyo.musiccreater0;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {
	private Fragment m0Fragment, m1Fragment, m2Fragment, m3Fragment,
			m4Fragment, m5Fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ActionBar ab = getActionBar();

		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); // NavigationmodeTabsでタブがつく。

		TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				if (tab.getText().equals("受講者一覧")) {
					//if (m0Fragment == null) {
						m0Fragment = Tab0Fragment.instantiate(
								getApplicationContext(),
								Tab0Fragment.class.getName());
						ft.add(android.R.id.content, m0Fragment, "受講者一覧");
				//	} else {
				//		ft.attach(m0Fragment);
				//	}
				} else if (tab.getText().equals("Group1")) {
					if (m1Fragment == null) {
						m1Fragment = Tab1Fragment.instantiate(
								getApplicationContext(),
								Tab1Fragment.class.getName());
						ft.add(android.R.id.content, m1Fragment, "Group1");
					} else {
						ft.attach(m1Fragment);
					}
				} else if (tab.getText().equals("Group2")) {
					if (m2Fragment == null) {
						m2Fragment = Tab2Fragment.instantiate(
								getApplicationContext(),
								Tab2Fragment.class.getName());
						ft.add(android.R.id.content, m2Fragment, "Group2");
					} else {
						ft.attach(m2Fragment);
					}
				} else if (tab.getText().equals("Group3")) {
					if (m3Fragment == null) {
						m3Fragment = Tab3Fragment.instantiate(
								getApplicationContext(),
								Tab3Fragment.class.getName());
						ft.add(android.R.id.content, m3Fragment, "Group3");
					} else {
						ft.attach(m3Fragment);
					}
				} else if (tab.getText().equals("Group4")) {
					if (m4Fragment == null) {
						m4Fragment = Tab4Fragment.instantiate(
								getApplicationContext(),
								Tab4Fragment.class.getName());
						ft.add(android.R.id.content, m4Fragment, "Group4");
					} else {
						ft.attach(m4Fragment);
					}
				} else if (tab.getText().equals("Group5")) {
					if (m5Fragment == null) {
						m5Fragment = Tab5Fragment.instantiate(
								getApplicationContext(),
								Tab5Fragment.class.getName());
						ft.add(android.R.id.content, m5Fragment, "Group5");
					} else {
						ft.attach(m5Fragment);
					}
				}

			}

			//タブが選択されなくなった場合に、タブの情報をdetachする。
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				if (tab.getText().equals("受講者一覧")) {
					if (m0Fragment != null) {
						ft.detach(m0Fragment);
					}
				} else if (tab.getText().equals("Group1")) {
					if (m1Fragment != null) {
						ft.detach(m1Fragment);
					}
				}else if (tab.getText().equals("Group2")) {
					if (m2Fragment != null) {
						ft.detach(m2Fragment);
					}
				}else if (tab.getText().equals("Group3")) {
					if (m3Fragment != null) {
						ft.detach(m3Fragment);
					}
				}else if (tab.getText().equals("Group4")) {
					if (m4Fragment != null) {
						ft.detach(m4Fragment);
					}
				}else if (tab.getText().equals("Group5")) {
					if (m5Fragment != null) {
						ft.detach(m5Fragment);
					}
				}
			}

		};

		// tabを追加
		ab.addTab(ab.newTab().setText("受講者一覧").setTabListener(tabListener));
		ab.addTab(ab.newTab().setText("Group1").setTabListener(tabListener));
		ab.addTab(ab.newTab().setText("Group2").setTabListener(tabListener));
		ab.addTab(ab.newTab().setText("Group3").setTabListener(tabListener));
		ab.addTab(ab.newTab().setText("Group4").setTabListener(tabListener));
		ab.addTab(ab.newTab().setText("Group5").setTabListener(tabListener));

	}

}
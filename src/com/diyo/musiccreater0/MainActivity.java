package com.diyo.musiccreater0;

import java.util.Locale;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity {
	private MyTabListener myTabListener = null;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawer;
	private String[] meibo = { "安達誠寛", "一瀬孝", "今井俊介", "岩崎大輔", "岩崎拓也", "岩塚美由紀",
			"大司まり", "大津良馬", "嘉村翼", "楠元信吾", "桑原玲", "小西未央子", "佐藤章", "大力新太郎",
			"高倉健治", "壇義弘", "能島章典", "野田幸代", "松島あゆみ", "松本真由美", "山口徹" };
	private String[] jender = {"男", "男", "男", "男", "男", "女",
			"女", "男", "男", "男", "男", "女", "男", "男",
			"男", "男", "男", "女", "女", "女", "男"};
	private MyCustomAdapter ad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// textview
		Typeface typeface = Typeface
				.createFromAsset(getAssets(), "regular.ttf");

		// drawer
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
		};
		mDrawer.setDrawerListener(mDrawerToggle);
		mDrawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
	
		myTabListener = new MyTabListener();
		// Tabをつける
		ActionBar ab = getSupportActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); //NavigationmodeTabsでタブがつく。Listでスピナがつく。
		ab.addTab(ab.newTab().setText("受講者一覧").setTabListener(myTabListener)); //これでタブを追加.setTabListenerでタブを押した時の処理
		ab.addTab(ab.newTab().setText("Group1").setTabListener(myTabListener)); //ActionBar.TabListenerを実装したクラスでそれを行う。
		ab.addTab(ab.newTab().setText("Group2").setTabListener(myTabListener)); //基本はフラグメントを使う
		ab.addTab(ab.newTab().setText("Group3").setTabListener(myTabListener)); //基本はフラグメントを使う
		ab.addTab(ab.newTab().setText("Group4").setTabListener(myTabListener)); //基本はフラグメントを使う
		ab.addTab(ab.newTab().setText("Group5").setTabListener(myTabListener)); //基本はフラグメントを使う
		ab.setDisplayHomeAsUpEnabled(true); //これでiconをクリックするとホームに戻る。
		ab.setHomeButtonEnabled(true); //こちらも一緒にsetする。

		ad = new MyCustomAdapter(this, R.layout.my_listview_item, meibo);

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// DrawerToggleの状態を同期する
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// DrawerToggleの状態を同期する
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}



package com.diyo.musiccreater0;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobeta.android.dslv.DragSortListView;
import com.mobeta.android.dslv.DragSortListView.DragListener;
import com.mobeta.android.dslv.DragSortListView.DropListener;

public class MainActivity extends ActionBarActivity {
	private MyTabListener myTabListener = null;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawer;
	private LinearLayout leftDrawer,rightDrawer;
	private List<String> array = new ArrayList<String>();
	private MyCustomAdapter ad;
	private TextToSpeech tts;
	private GestureDetector gd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Locale LOC = Locale.ENGLISH;
		// textview
		TextView textleft = (TextView) findViewById(R.id.text_left_drawer);
		Typeface typeface = Typeface
				.createFromAsset(getAssets(), "regular.ttf");
		textleft.setTypeface(typeface);

		// drawer
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		leftDrawer = (LinearLayout) findViewById(R.id.left_drawer);
		rightDrawer = (LinearLayout) findViewById(R.id.right_drawer);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
		};
		mDrawer.setDrawerListener(mDrawerToggle);
		mDrawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		//どこをフリックしてもドロワが出るようにした
		gd = new GestureDetector(this, new MyGestureDetector(mDrawer,leftDrawer,rightDrawer));
		
		myTabListener = new MyTabListener();
		// Tabをつける
		ActionBar ab = getSupportActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); //NavigationmodeTabsでタブがつく。Listでスピナがつく。
		ab.addTab(ab.newTab().setText("Tab1").setTabListener(myTabListener)); //これでタブを追加.setTabListenerでタブを押した時の処理
		ab.addTab(ab.newTab().setText("Tab2").setTabListener(myTabListener)); //ActionBar.TabListenerを実装したクラスでそれを行う。
		ab.addTab(ab.newTab().setText("Tab3").setTabListener(myTabListener)); //基本はフラグメントを使う
		ab.setDisplayHomeAsUpEnabled(true); //これでiconをクリックするとホームに戻る。
		ab.setHomeButtonEnabled(true); //こちらも一緒にsetする。

		// listview
		final DragSortListView lv = (DragSortListView) findViewById(R.id.lv_left_drawer);
		lv.setDivider(null);

		// selectbtn
		Button btnLeft = (Button) findViewById(R.id.select_btn_left_drawer);
		btnLeft.setTypeface(typeface); //btnに表示される
		btnLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this,
						lv.getItemAtPosition(0).toString(), Toast.LENGTH_SHORT)
						.show();
			}
		});

		lv.setDropListener(new DropListener() {

			@Override
			public void drop(int from, int to) {
				Log.i("drop", "from:" + from + "to:" + to);
				String target = ad.getItem(from);
				ad.remove(target);
				ad.insert(target, to);
				ad.notifyDataSetChanged();
			}
		});

		lv.setDragListener(new DragListener() {

			@Override
			public void drag(int from, int to) {
				String target = ad.getItem(from);
				Log.i("drag", target);
				tts = new TextToSpeech(getApplicationContext(),
						new OnInitListener() {
							@Override
							public void onInit(int status) {
							}
						});
				tts.setLanguage(LOC);
				if (0 < target.length()) {
					// 読み上げ中なら止める
					if (tts.isSpeaking()) {
						tts.stop();
					}
					// 読み上げ開始
					tts.speak(target, TextToSpeech.QUEUE_FLUSH, null);
				}
			}
		});

		for (int i = 0; i < 20; ++i) {
			array.add(i + "");
		}

		ad = new MyCustomAdapter(this, R.layout.my_listview_item, array);
		lv.setAdapter(ad);

		// XXX 必要性を考える　scrollの無限処理
		lv.setOnScrollListener(new OnScrollListener() {
			int loopcount;

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if ((totalItemCount != 0)
						&& (totalItemCount == firstVisibleItem
								+ visibleItemCount)) {
					// リストへの追加処理
					for (int i = 0; i < 20; ++i) {
						array.add(i + "");
					}
					// 追加後、リストを更新する
					ad.notifyDataSetChanged();
					loopcount += 1;
					if (loopcount > 3) {
						// XXX ループが三回以上あったらどうするか。考える。
						for (int i = 0; i < 20; ++i) {
							array.remove(i);
						}
						ad.notifyDataSetChanged();
						loopcount = 0;
					}
					return;
				}

			}
		});

	}

	//toucheventを子が拾わないようにして、フリックでdrawerを出せるようにする
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		 return gd.onTouchEvent(ev) || super.dispatchTouchEvent(ev);
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

	// TextToSpeechのリソースを解放する
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (null != tts) {
			tts.shutdown();
		}
	}

	// optionmenu、ライセンス明記のため
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}



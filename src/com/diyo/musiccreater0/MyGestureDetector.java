package com.diyo.musiccreater0;

import android.support.v4.widget.DrawerLayout;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyGestureDetector implements GestureDetector.OnGestureListener {
	private DrawerLayout myDl;
	private LinearLayout myLl;
	private LinearLayout myLl1;

	// コンストラクタ
	public MyGestureDetector(DrawerLayout dl, LinearLayout ll, LinearLayout ll1) {
		this.myDl = dl;
		this.myLl = ll;
		this.myLl1 = ll1;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		float dx = Math.abs(velocityX);
		float dy = Math.abs(velocityY);
		if (dx > dy && dx > 200) {
			if (e1.getX() - e2.getX() < 100) {
				if (myDl.isDrawerVisible(myLl1)) {
					myDl.closeDrawer(myLl1);
				}
				// show drawer
				myDl.openDrawer(myLl);
				return true;
			} else if (e1.getX() - e2.getX() > 100) {
				if (myDl.isDrawerVisible(myLl)) {
					myDl.closeDrawer(myLl);
				}
				// show drawer
				myDl.openDrawer(myLl1);
				return true;
			}
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

}

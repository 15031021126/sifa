package com.liuhezhineng.ximengsifa.widget;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @author AIqinfeng
 * @date 2018/7/4
 */

public class AndroidSevenPopupWindow extends PopupWindow {

	public AndroidSevenPopupWindow(View contentView, int width, int height) {
		super(contentView, width, height);
	}

	@Override
	public void showAsDropDown(View anchor) {
		if (Build.VERSION.SDK_INT >= 24) {
			Rect rect = new Rect();
			anchor.getGlobalVisibleRect(rect);
			int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
			setHeight(h);
		}
		super.showAsDropDown(anchor);
	}

	@Override
	public void showAsDropDown(View anchor, int xoff, int yoff) {
		if (Build.VERSION.SDK_INT >= 24) {
			Rect rect = new Rect();
			anchor.getGlobalVisibleRect(rect);
			int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
			setHeight(h);
		}
		super.showAsDropDown(anchor, xoff, yoff);
	}

	@Override
	public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
		if (Build.VERSION.SDK_INT >= 24) {
			Rect rect = new Rect();
			anchor.getGlobalVisibleRect(rect);
			int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
			setHeight(h);
		}
		super.showAsDropDown(anchor, xoff, yoff, gravity);
	}
}

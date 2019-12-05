package com.liuhezhineng.ximengsifa.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import com.liuhezhineng.ximengsifa.R;

/**
 * @author AIqinfeng
 * @date 2018/7/18
 */

public class SlidingSearchPopupWindowUtils{

	private static PopupWindow mPopupWindow;

	private static void initPopup(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.sliding_search_popop, null);
		mPopupWindow = new AndroidSevenPopupWindow(view,
			LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	}

	public static void show() {
	}
}

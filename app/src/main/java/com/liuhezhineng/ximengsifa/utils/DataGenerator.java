package com.liuhezhineng.ximengsifa.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.liuhezhineng.ximengsifa.R;

public class DataGenerator {

	public static final int[] mTabRes = new int[]{R.drawable.home, R.drawable.service, R.drawable.my};
	public static final int[] mMongolian = new int[]{
	        R.drawable.home_page_home,
            R.drawable.home_page_service,
            R.drawable.home_page_my};
	public static final int[] mTabResPressed = new int[]{
		    R.drawable.home_selected,
            R.drawable.service_selected,
            R.drawable.my_selected};
	public static final String[] mTabTitle = new String[]{"首页", "服务", "我的"};

	/**
	 * 获取Tab 显示的内容
	 */
	public static View getTabView(Context context, int position) {
		View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content, null);
        ImageView ivMongolian = view.findViewById(R.id.iv_mongolian);
		ImageView tabIcon = view.findViewById(R.id.tab_content_image);
		ivMongolian.setImageResource(DataGenerator.mMongolian[position]);
		tabIcon.setImageResource(DataGenerator.mTabRes[position]);
		TextView tabText = view.findViewById(R.id.tab_content_text);
		tabText.setText(mTabTitle[position]);
		return view;
	}
}
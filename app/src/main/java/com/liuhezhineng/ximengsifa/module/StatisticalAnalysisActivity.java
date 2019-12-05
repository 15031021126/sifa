package com.liuhezhineng.ximengsifa.module;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.DatePicker;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.flyco.tablayout.SlidingTabLayout;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.liuhezhineng.ximengsifa.widget.DoubleDatePickerDialog;
import com.qmuiteam.qmui.widget.QMUITopBar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author AIqinfeng
 * @description 统计分析
 */
public class StatisticalAnalysisActivity extends BaseActivity {

	public static final String AREA = "0";
	public static final String HANDLE_CHANNEL = "1";
	public static final String CASE_TYPE = "2";

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.sliding_tab_layout)
	SlidingTabLayout mSlidingTabLayout;
	@BindView(R.id.view_pager)
	ViewPager mViewPager;
	@BindView(R.id.tv_date)
	TextView mTvDate;
	private String[] mTitles = {"旗县", "办理渠道", "案件类型"};
	private String[] mTitlesKey = {AREA, HANDLE_CHANNEL, CASE_TYPE};
	private ArrayList<StatisticalAnalysisFragment> mFragments = new ArrayList<>();
	private String applyBeginDate = "2018-01-01";
	private String applyEndDate = "2018-12-31";

	public static void actionStart(Context context) {
		if (UserHelper.isIsLogin()) {
			Intent intent = new Intent(context, StatisticalAnalysisActivity.class);
			context.startActivity(intent);
		} else {
			gotoLogin(context);
		}
	}

	@OnClick({R.id.ll_date})
	public void onViewClick() {
		Calendar c = Calendar.getInstance();
		// 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
		new DoubleDatePickerDialog(mActivity, 0, new DoubleDatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear,
				int startDayOfMonth, DatePicker endDatePicker, int endYear, int endMonthOfYear,
				int endDayOfMonth) {
				String textString = String.format("%d-%d-%d ~ %d-%d-%d", startYear,
					startMonthOfYear + 1, startDayOfMonth, endYear, endMonthOfYear + 1,
					endDayOfMonth);
				applyBeginDate =
					startYear + changeFormat(startMonthOfYear + 1) + changeFormat(startDayOfMonth);
				applyEndDate =
					endYear + changeFormat(endMonthOfYear + 1) + changeFormat(endDayOfMonth);
				mTvDate.setText(textString);

				mFragments.get(mViewPager.getCurrentItem())
					.filterData(applyBeginDate, applyEndDate);
			}
		}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), true).show();
	}

	private String changeFormat(int i) {
		if (i >= 10) {
			return String.valueOf(i);
		} else {
			return "0" + i;
		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_statistical_analysis;
	}

	@Override
	protected void initView() {
		super.initView();

		initTopBar(mTopBar, "统计分析");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",
			Locale.CHINESE);// HH:mm:ss
		//获取当前时间
		Date date = new Date(System.currentTimeMillis());
		applyEndDate = simpleDateFormat.format(date);

		applyBeginDate = getMonthAgo(date);

		mTvDate.setText(applyBeginDate + " ~ " + applyEndDate);

		for (int i = 0; i < mTitles.length; i++) {
			mFragments
				.add(StatisticalAnalysisFragment
					.newInstance(mTitlesKey[i], applyBeginDate, applyEndDate));
		}

		mViewPager.setAdapter(
			new StatisticalAnalysisActivity.MyPagerAdapter(getSupportFragmentManager()));
		mSlidingTabLayout.setViewPager(mViewPager, mTitles);
	}

	/**
	 * 获取一个月前的日期
	 *
	 * @param date 传入的日期
	 */
	public String getMonthAgo(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -6);
		String monthAgo = simpleDateFormat.format(calendar.getTime());
		return monthAgo;
	}

	private class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mTitles[position];
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}
	}
}

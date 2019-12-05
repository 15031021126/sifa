package com.liuhezhineng.ximengsifa.module.mine.business;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.MyPagerAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.MyDraftActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author AIqinfeng
 * @description 业务列表
 */

public class MyBusinessActivity extends BaseActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.sliding_tab_layout)
	SlidingTabLayout mSlidingTabLayout;
	@BindView(R.id.view_pager)
	ViewPager mViewPager;

	private String[] mTitles;
	private ArrayList<Fragment> mFragments = new ArrayList<>();

	public static void actionStart(Context context) {
		if (UserHelper.isIsLogin()) {
			Intent intent = new Intent(context, MyBusinessActivity.class);
			context.startActivity(intent);
		} else {
			gotoLogin(context);
		}
	}

	public static void actionStart(Context context, String flag) {
		if (UserHelper.isIsLogin()) {
			Intent intent = new Intent(context, MyBusinessActivity.class);
			intent.putExtra(Constant.HAVE_DONE, flag);
			context.startActivity(intent);
		} else {
			gotoLogin(context);
		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_my_business;
	}
	@Override
	protected void initView() {
		super.initView();

		initTopBar(mTopBar, R.string.my_bussiness);
        mTopBar.addRightImageButton(R.drawable.draft, R.id.selected_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDraftActivity.actionStart(mActivity);
            }
        });

        String haveDone = getIntent().getStringExtra(Constant.HAVE_DONE);
        if (Constant.HAVE_DONE.equals(haveDone)) {
            mTitles = new String[]{"已办"};
        } else if (Constant.UP_COMING.equals(haveDone)) {
            mTitles = new String[]{"待办"};
        } else {
            mTitles = new String[]{"待办", "已办"};
        }
		for (String str : mTitles) {
			mFragments.add(MyBusinessFragment.newInstance(str));
		}
		mViewPager.setAdapter(
			new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
		mSlidingTabLayout.setViewPager(mViewPager, mTitles);

		if (!TextUtils.isEmpty(haveDone)) {
			mSlidingTabLayout.setCurrentTab(1);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (RESULT_OK == resultCode && requestCode == Constant.DEAL_BUSINESS_CODE) {
			for (Fragment fragment : mFragments) {
                if (fragment instanceof  MyBusinessFragment) {
                    ((MyBusinessFragment) fragment).initData();
                }
			}
		}
	}

	@Override
	protected void setListener() {
		super.setListener();
		mSlidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
			@Override
			public void onTabSelect(int position) {
				mViewPager.setCurrentItem(position);
			}

			@Override
			public void onTabReselect(int position) {

			}
		});
		mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
				mSlidingTabLayout.setCurrentTab(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}
}

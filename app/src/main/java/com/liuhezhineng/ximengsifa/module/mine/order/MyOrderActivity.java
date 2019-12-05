package com.liuhezhineng.ximengsifa.module.mine.order;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.MyPagerAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author AIqinfeng
 * @description 业务列表
 */

public class MyOrderActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.sliding_tab_layout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.view_pager)

    ViewPager mViewPager;
    List<BusinessBean> mList;
    private String[] mTitles = new String[]{"我的预约", "待处理预约"};
    private List<Fragment> mFragments = new ArrayList<>();

    public static void actionStart(Context context) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, MyOrderActivity.class);
            context.startActivity(intent);
        } else {
            gotoLogin(context);
        }
    }

    public static void actionStart(Context context, String flag) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, MyOrderActivity.class);
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

        initTopBar(mTopBar, R.string.my_order);

        mList = new ArrayList<>();
        for (String str : mTitles) {
            mFragments.add(MyOrderFragment.newInstance(str));
        }
        mViewPager.setAdapter(
                new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
        mSlidingTabLayout.setViewPager(mViewPager, mTitles);

        if (!TextUtils.isEmpty(getIntent().getStringExtra(Constant.HAVE_DONE))) {
            mSlidingTabLayout.setCurrentTab(1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode && requestCode == Constant.DEAL_BUSINESS_CODE) {
            for (Fragment fragment : mFragments) {
                if (fragment instanceof  MyOrderFragment) {
                    ((MyOrderFragment) fragment).initData();
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

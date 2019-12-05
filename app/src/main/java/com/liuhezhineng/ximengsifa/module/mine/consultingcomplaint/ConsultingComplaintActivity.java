package com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.MyPagerAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.utils.UserHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 留言咨询列表页面
 */
public class ConsultingComplaintActivity extends BaseActivity {

    // 咨询还是投诉
    public String type;
    @BindView(R.id.tv_want_advisory)
    TextView mTvWantAdvisory;
    @BindView(R.id.sliding_tab_layout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private String[] mTitles;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public static void actionStart(Context activity, String type) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(activity, ConsultingComplaintActivity.class);
            intent.putExtra(Constant.STRING, type);
            activity.startActivity(intent);
        } else {
            gotoLogin(activity);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advisory_messages;
    }

    @Override
    protected void initView() {
        super.initView();

        if (getIntent() != null) {
            type = getIntent().getStringExtra(Constant.STRING);
        }
        if (Constant.CONSULTING.contains(type)) {
//            initTopBar(R.string.i_want_advisory);
            initTopBar(R.string.knowledge_qa_consultation);
            mTvWantAdvisory.setText(R.string.i_want_advisory);
            if (UserHelper.isIsNormalUser()) {
                mTitles = new String[]{getString(R.string.my_advisory)};
            } else {
                mTitles = new String[]{getString(R.string.my_advisory), getString(R.string.my_consulting_to_do)};
            }
        } else if (Constant.COMPLAINT.equals(type)) {
            initTopBar(R.string.i_want_complaint);
            mTvWantAdvisory.setText(R.string.i_want_complaint);
            if (UserHelper.isIsNormalUser()) {
                mTitles = new String[]{getString(R.string.my_complaint)};
            } else {
                mTitles = new String[]{getString(R.string.my_complaint), getString(R.string.my_complaint_to_do)};
            }
        }

        for (String todoType : mTitles) {
            mFragments.add(ConsultingComplaintFragment.newInstance(todoType, type));
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
        boolean isRefresh = resultCode == RESULT_OK &&
                (requestCode == Constant.NORMAL_CODE ||
                        requestCode == ComplaintDistributionActivity.STATE_REFRESH);
        if (isRefresh) {
            for (Fragment fragment : mFragments) {
                if (fragment instanceof ConsultingComplaintFragment) {
                    ((ConsultingComplaintFragment) fragment).initData();
                }
            }
        }
    }

    /**
     * launchMode: singleTask
     * 这么加载是因为有两个入口来到这里。服务里面和个人中心咨询/投诉列表页。
     * 服务里面申请成功跳转到这里之后在返回就不能在回去了。
     *
     * @param intent {@link Intent}
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        for (Fragment fragment : mFragments) {
            ((ConsultingComplaintFragment) fragment).initData();
        }
    }

    @OnClick(R.id.tv_want_advisory)
    public void onViewClicked() {
        if (Constant.CONSULTING.equals(type)) {
            MyConsultationActivity.actionStart(mActivity);
        } else {
            MyComplaintsActivity.actionStart(mActivity);
        }
    }
}

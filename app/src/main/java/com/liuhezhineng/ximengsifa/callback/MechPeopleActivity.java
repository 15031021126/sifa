package com.liuhezhineng.ximengsifa.callback;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.flyco.tablayout.SlidingTabLayout;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.MechanismsubjectBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lishangnan on 2019/11/11.
 */

public class MechPeopleActivity extends BaseActivity {
    @BindView(R.id.top_bar)
    QMUITopBar topBar;
    @BindView(R.id.sliding_tab_layout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mechanism;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(topBar,"执法人员");
        insData();

    }
    private void insData() {
        Map<String, String> map = new HashMap<>(16);
        map.put("pageSize", "10");
        map.put("pageNo", "1");
        String queryStr = new JSONObject(map).toString();
        OkGo.<MechanismsubjectBean>post("http://61.134.97.201/WisdomJustice/api/100/500/90").params("query",queryStr).execute(new JsonCallback2<MechanismsubjectBean>() {
            @Override
            public void onSuccess(Response<MechanismsubjectBean> response) {
                List<MechanismsubjectBean.BodyBean.ListBean> list = response.body().getBody().getList();
                mTitles = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    mFragments
                            .add(MechPeopleFragment.newInstance(list.get(i)));
                    mTitles[i] = list.get(i).getName();
                }
                mViewPager.setAdapter(
                        new MyPagerAdapter(
                                getSupportFragmentManager()));
                slidingTabLayout.setViewPager(mViewPager, mTitles);

            }
        });
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

 
}

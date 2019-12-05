package com.liuhezhineng.ximengsifa.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.module.mine.MyFragment;
import com.liuhezhineng.ximengsifa.module.newhome.NewHomeFragment;
import com.liuhezhineng.ximengsifa.module.service.ServiceFragment;
import com.liuhezhineng.ximengsifa.utils.DataGenerator;
import com.liuhezhineng.ximengsifa.utils.UpdateUtils;

import butterknife.BindView;

/**
 * @author AIqinfeng
 * @description 主页
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_tab_layout)
    TabLayout mBottomTabLayout;
    FragmentManager mManager;
    private Fragment[] mFragments;
    private int preIndex;
    private Bundle state;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        state = savedInstanceState;
        super.onCreate(savedInstanceState);
        UpdateUtils.update(mActivity, false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        initFragments();
        initTabView();
    }

    private void initFragments() {
        mManager = getSupportFragmentManager();
        mFragments = new Fragment[3];
        if (state == null) {
            mFragments[0] = NewHomeFragment.newInstance(R.string.home);
//			mFragments[0] = HomeFragment.newInstance(R.string.home);
            mFragments[1] = ServiceFragment.newInstance(R.string.service);
            mFragments[2] = MyFragment.newInstance(R.string.my);
            FragmentTransaction transaction = mManager.beginTransaction();
            for (Fragment fragment : mFragments) {
                transaction.add(R.id.home_container, fragment, fragment.getClass().getName());
            }
            transaction.commitAllowingStateLoss();
        } else {
            mFragments[0] = mManager.findFragmentByTag(NewHomeFragment.class.getName());
//			mFragments[0] = mManager.findFragmentByTag(HomeFragment.class.getName());
            mFragments[1] = mManager.findFragmentByTag(ServiceFragment.class.getName());
            mFragments[2] = mManager.findFragmentByTag(MyFragment.class.getName());
        }
        FragmentTransaction transaction = mManager.beginTransaction();
        for (Fragment fragment : mFragments) {
            transaction.hide(fragment);
        }
        transaction.show(mFragments[preIndex]).commitAllowingStateLoss();
    }

    private void initTabView() {
        mBottomTabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                onTabItemSelected(tab.getPosition());
            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });

        for (int i = 0; i < 3; i++) {
            mBottomTabLayout.addTab(
                    mBottomTabLayout.newTab().setCustomView(DataGenerator.getTabView(this, i)));
        }
    }

    private void onTabItemSelected(int position) {
        FragmentTransaction transaction = mManager.beginTransaction();
        if (preIndex != position) {
            transaction.hide(mFragments[preIndex])
                    .show(mFragments[position]).commitAllowingStateLoss();
            preIndex = position;
        }

        for (int i = 0; i < mBottomTabLayout.getTabCount(); i++) {
            View view = mBottomTabLayout.getTabAt(i).getCustomView();
            ImageView icon = view.findViewById(R.id.tab_content_image);
            TextView text = view.findViewById(R.id.tab_content_text);
            if (i == position) {
                icon.setImageResource(DataGenerator.mTabResPressed[i]);
                text.setTextColor(getResources().getColor(R.color.blue));
            } else {
                icon.setImageResource(DataGenerator.mTabRes[i]);
                text.setTextColor(getResources().getColor(android.R.color.darker_gray));
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }
}
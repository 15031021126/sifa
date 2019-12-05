package com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone;

import android.view.View;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.business.base.BasePeoplesMediationActivity;

/**
 * @author AIqinfeng
 * @date 2018/6/11
 */

public abstract class BaseHaveDoneActivity extends BasePeoplesMediationActivity {

	@Override
	protected void initView() {
		super.initView();
		findViewById(R.id.tv_file).setVisibility(View.GONE);
	}

	@Override
	protected void initData() {
		super.initData();
		loadBusinessDetails();
	}
}

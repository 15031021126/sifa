package com.liuhezhineng.ximengsifa.callback;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.business.legalaid.ApplyForLegalAidActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 法律援助入口，是申请人还是代理人
 */
public class ReconsiderationActivity extends BaseActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;

	public static void actionStart(Context context) {
		if (UserHelper.isIsLogin()) {
			Intent intent = new Intent(context, ReconsiderationActivity.class);
			context.startActivity(intent);
		} else {
			gotoLogin(context);
		}
	}
	@Override
	protected int getLayoutId() {
		return R.layout.activity_renonsideration;
	}

	@Override
	protected void initView() {
		super.initView();
		initTopBar(mTopBar, "");
	}

	@Override
	@OnClick({R.id.tv_request_user, R.id.tv_proxy_user})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.tv_request_user:
				RFLegalAidActivity.actionStart(mActivity, Constant.REQUEST_USER);
				finishActivity();
				break;
			case R.id.tv_proxy_user:
				RFLegalAidActivity.actionStart(mActivity, Constant.AGENT_USER);
				finishActivity();
				break;
			default:
				break;
		}
	}
}

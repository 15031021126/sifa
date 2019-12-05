package com.liuhezhineng.ximengsifa.model;

import android.app.Activity;
import android.text.TextUtils;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.lzy.okgo.request.base.Request;

/**
 * @author AIqinfeng
 * @date 2018/4/22
 */

public abstract class DialogStringCallBack extends MyStringCallback {

	private BaseActivity mActivity;
	private String loadingText;

	protected DialogStringCallBack(Activity activity) {
		super(activity);
		mActivity = (BaseActivity) activity;
	}

	@Override
	public void onCacheSuccess(com.lzy.okgo.model.Response<String> response) {
		super.onCacheSuccess(response);
		onSuccess(response);
	}

	protected DialogStringCallBack(Activity activity, String loadingText) {
		super(activity);
		mActivity = (BaseActivity) activity;
		this.loadingText = loadingText;
	}

	@Override
	public void onStart(Request request) {
		super.onStart(request);
		if (isShowLoadingDialog()) {
			if (TextUtils.isEmpty(loadingText)) {
				mActivity.showLoadingDialog();
			} else {
				mActivity.showLoadingDialog(loadingText);
			}
		}
	}

	public boolean isShowLoadingDialog() {
		return true;
	}

	@Override
	public void onFinish() {
		super.onFinish();
		mActivity.dismissLoadingDialog();
	}
}

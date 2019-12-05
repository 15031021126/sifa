package com.liuhezhineng.ximengsifa.model;

import android.content.Context;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.lzy.okgo.request.base.Request;

/**
 * @author AIqinfeng
 * @date 2018/4/22
 */

public abstract class DialogCallBack<T> extends JsonCallback<T> {

	private BaseActivity mActivity;
	private String loadingText = "";

	protected DialogCallBack(Context activity) {
		super(activity);
		mActivity = (BaseActivity) activity;
	}

	protected DialogCallBack(Context activity, String loadingText) {
		super(activity);
		mActivity = (BaseActivity) activity;
		this.loadingText = loadingText;
	}

	@Override
	public void onStart(Request<T, ? extends Request> request) {
		super.onStart(request);
		if (isShowLoadingDialog()) {
			if ("".equals(loadingText)) {
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

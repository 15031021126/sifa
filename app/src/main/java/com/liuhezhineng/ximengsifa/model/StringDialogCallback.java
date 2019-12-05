package com.liuhezhineng.ximengsifa.model;

import android.app.Activity;
import android.text.TextUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @date 2018/4/22
 */

public abstract class StringDialogCallback extends MyStringCallback {

	private BaseActivity mActivity;
	private String loadingText;

	protected StringDialogCallback(Activity activity) {
		super(activity);
		mActivity = (BaseActivity) activity;
	}
	@Override
	public void onCacheSuccess(com.lzy.okgo.model.Response<String> response) {
		super.onCacheSuccess(response);
		onSuccess(response);
	}

	protected StringDialogCallback(Activity activity, String loadingText) {
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

	@Override
	public void onSuccess(Response<String> response) {
		try {
			JSONObject jsonObject = new JSONObject(response.body());
			int status = jsonObject.getInt(Constant.STATUS);
			if (status == 0) {
				responseSuccess(jsonObject);
			} else {
				ToastUtils.showLong(jsonObject.getString(Constant.MSG));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	protected abstract void responseSuccess(JSONObject object);
}

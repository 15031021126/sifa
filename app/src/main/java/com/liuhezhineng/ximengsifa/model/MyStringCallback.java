package com.liuhezhineng.ximengsifa.model;

import android.app.Activity;
import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.Request;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.module.mine.login.LoginActivity;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.db.CacheManager;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.HttpHeaders;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author AIqinfeng
 * @date 2018/6/27
 */

public abstract class MyStringCallback extends StringCallback {

	private Context mContext;

	public MyStringCallback(Context context) {
		mContext = context;
	}

	@Override
	public void onCacheSuccess(com.lzy.okgo.model.Response<String> response) {
		super.onCacheSuccess(response);
		onSuccess(response);
	}

	@Override
	public String convertResponse(Response response) throws Throwable {
		ResponseBody body = response.body();
		if (body != null) {
			JSONObject jsonObject = new JSONObject(body.string());
			int status = jsonObject.getInt(Constant.STATUS);
			switch (status) {
				case Request.SUCCESS:
					return jsonObject.toString();
				case Request.NOT_LOGIN:
					ToastUtils.showLong("用户信息失效，请重新登录");
					for (Activity activity : BaseActivity.mActivityArrayList) {
						if (activity != null) {
							activity.finish();
						}
					}
					UserHelper.clear();
					JPushInterface.deleteAlias(mContext, 0);
					OkGo.getInstance().addCommonHeaders(new HttpHeaders(NetConstant.TOKEN, ""));
					CacheManager.getInstance().clear();
					LoginActivity.actionStart(mContext);
					return "";
				default:
					throw new IllegalStateException(jsonObject.getString(Constant.MSG));
			}
		} else {
			return "";
		}
	}

	@Override
	public void onError(com.lzy.okgo.model.Response<String> response) {
		super.onError(response);
		Throwable exception = response.getException();
		if (exception != null) {
			exception.printStackTrace();
		}
		if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
			ToastUtils.showLong("网络连接失败，请检查网络连接");
		} else if (exception instanceof SocketTimeoutException) {
			ToastUtils.showLong("网络请求超时");
		} else if (exception instanceof HttpException) {
			ToastUtils.showLong(R.string.error_http_exception);
		} else if (exception instanceof StorageException) {
			ToastUtils.showLong("sd卡不存在或没有权限");
		} else if (exception instanceof IllegalStateException) {
			String message = exception.getMessage();
			ToastUtils.showLong(message);
		}
	}
}

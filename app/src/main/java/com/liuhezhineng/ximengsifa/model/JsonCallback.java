package com.liuhezhineng.ximengsifa.model;

import android.app.Activity;
import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.Request;
import com.liuhezhineng.ximengsifa.module.mine.login.LoginActivity;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.db.CacheManager;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import cn.jpush.android.api.JPushInterface;
import okhttp3.ResponseBody;

/**
 * @author AIqinfeng
 * @date 2018/4/17
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {

    private BaseActivity mActivity;

    public JsonCallback() {
    }

    public JsonCallback(Context activity) {
        mActivity = (BaseActivity) activity;
    }

    @Override
    public void onError(Response<T> response) {
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
            if ("用户信息失效，请重新登录".equals(message)) {
                for (Activity activity : BaseActivity.mActivityArrayList) {
                    if (activity != null) {
                        activity.finish();
                    }
                }
                UserHelper.clear();
                JPushInterface.deleteAlias(mActivity, 0);
                OkGo.getInstance().addCommonHeaders(new HttpHeaders(Constant.TOKEN, ""));
                OkGo.getInstance().getCommonHeaders().remove(Constant.TOKEN);
                CacheManager.getInstance().clear();
                LoginActivity.actionStart(mActivity);
            }
        }
    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        if (!(type instanceof ParameterizedType)) {
			throw new IllegalAccessError("没有添加泛型参数");
        }
        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];

        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (rawType != BaseBean.class) {
            T data = gson.fromJson(jsonReader, type);
            response.close();
            return data;
        } else {
            if (typeArgument == Void.class) {
                return null;
            } else {
                BaseBean baseBean = gson.fromJson(jsonReader, type);
                response.close();
                int code = baseBean.getStatus();
                switch (code) {
                    case Request.SUCCESS:
                        return (T) baseBean;
                    case Constant.Request.NOT_LOGIN:
                        throw new IllegalStateException("用户信息失效，请重新登录");
                    default:
                        throw new IllegalStateException(baseBean.getMsg());
                }
            }
        }
    }
}

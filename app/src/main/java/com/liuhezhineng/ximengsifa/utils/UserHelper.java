package com.liuhezhineng.ximengsifa.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.ProfileBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.SP;

/**
 * @author AIqinfeng
 * @date 2018/4/17
 */

public class UserHelper {

    private static boolean isLogin = false;
    private static String token;
    private static UserBean user;
    /**
     * 是否是普通用户，没有登录即默认为普通用户
     */
    private static boolean isNormalUser = true;
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static boolean isIsNormalUser() {
        if (isNormalUser) {
            SharedPreferences sp = mContext.getSharedPreferences(SP.USER_INFO, Context.MODE_PRIVATE);
            isNormalUser = sp.getBoolean(SP.IS_NORMAL_USER, true);
        }
        return isNormalUser;
    }

    public static void setIsNormalUser(boolean isNormalUser) {
        UserHelper.isNormalUser = isNormalUser;
    }

    public static void updateUser(ProfileBean bean) {
        user.setSex(bean.getUserExpand().getSex());
        user.setEthnic(bean.getEthnic());
        user.setEthnicDesc(bean.getEthnicDesc());
        user.setCounty(bean.getArea());
        user.setTown(bean.getTownarea());
        user.setBirthday(bean.getBirthday());
        user.setMobile(bean.getMobile());
        user.setEducation(bean.getUserExpand().getEducation());
        user.setEducationDesc(bean.getUserExpand().getEducationDesc());
        user.setUserSourceType(bean.getUserSourceType());
        user.setUserSourceTypeDesc(bean.getUserSourceTypeDesc());
        Log.i("qingfeng", "updateUser: " + user.toString());
        SharedPreferences sp = mContext.getSharedPreferences(SP.USER_INFO, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putString(SP.USER_INFO, new Gson().toJson(user));
        edit.apply();
    }

    public static UserBean getUser() {
        // 从 SharedPreferences 从获取用户信息
        if (user == null) {
            SharedPreferences sp = mContext.getSharedPreferences(SP.USER_INFO, Context.MODE_PRIVATE);
            String userJson = sp.getString(SP.USER_INFO, null);
            user = new Gson().fromJson(userJson, UserBean.class);
            // 保护性代码
            if (user == null) {
                user = new UserBean();
            }
        }
        return user;
    }

    public static void setUser(UserBean user) {
        UserHelper.user = user;
    }

    public static boolean isIsLogin() {
        if (!isLogin) {
            SharedPreferences sp = mContext.getSharedPreferences(SP.USER_INFO, Context.MODE_PRIVATE);
            isLogin = sp.getBoolean(SP.IS_LOGIN, false);
        }
        return isLogin;
    }

    public static void setIsLogin(boolean isLogin) {
        UserHelper.isLogin = isLogin;
    }

    public static String getToken() {
        if (TextUtils.isEmpty(token)) {
            SharedPreferences sp = mContext.getSharedPreferences(SP.USER_INFO, Context.MODE_PRIVATE);
            token = sp.getString(Constant.TOKEN, null);
        }
        return token;
    }

    public static void setToken(String token) {
        UserHelper.token = token;
    }

    public static void clear() {
        SharedPreferences sp = mContext.getSharedPreferences(SP.USER_INFO, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.clear();
        edit.apply();
        isLogin = false;
        token = null;
        user = null;
        isNormalUser = true;
    }

    public static void updateUserAvatar(BaseActivity activity, String path) {
        user.setPhoto(path);
        SharedPreferences sp = mContext.getSharedPreferences(SP.USER_INFO, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putString(SP.USER_INFO, new Gson().toJson(user));
        edit.apply();
        LocalBroadcastManager.getInstance(activity).sendBroadcast(
                new Intent(Constant.UPLOAD_AVATAR).putExtra(Constant.UPLOAD_AVATAR, path));
    }
}

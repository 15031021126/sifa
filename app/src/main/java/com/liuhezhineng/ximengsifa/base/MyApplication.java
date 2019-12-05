package com.liuhezhineng.ximengsifa.base;

import android.app.Notification;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.Utils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.im.IMUtils;
import com.liuhezhineng.ximengsifa.utils.PicassoImageLoader;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * @author AIqinfeng
 * @date 2018/4/15
 * @description 应用的入口，只会调用一次，最先调用
 */

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
//        CrashHandler.getInstance().register(getApplicationContext());
        UserHelper.init(this);
        initOkGo();
        initImagePicker();
        Utils.init(this);
        initJPush();
        initBaiduMap();
        initEaseIM();
        IMUtils.getInstance().init(getApplicationContext());

        disableAndroidPHideApiDialog();
    }

    private void disableAndroidPHideApiDialog() {
        try {
            Class clazzActivityThread = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = clazzActivityThread.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);
            Object activityThread = currentActivityThread.invoke(null);
            Field filedMHiddenApiWarningShown = clazzActivityThread.getDeclaredField("mHiddenApiWarningShown");
            filedMHiddenApiWarningShown.setAccessible(true);
            filedMHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initEaseIM() {
        EMOptions options = new EMOptions();
        //初始化
        EMClient.getInstance().init(getApplicationContext(), options);
        EaseUI.getInstance().init(getApplicationContext(), options);
    }

    private void initBaiduMap() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    private void initJPush() {
        JPushInterface.setDebugMode(true);

        JPushInterface.init(this);

        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable = R.mipmap.jpush_notification_icon;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1, builder);
    }

    private void initOkGo() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", "application/json;charset=utf-8");
//		headers.put(NetConstant.TIMESTAMP, System.currentTimeMillis() + "");
        // 用来标志是 Android 端发起的请求
        headers.put(NetConstant.TAG, String.valueOf(200));
        if (TextUtils.isEmpty(UserHelper.getToken())) {
            headers.put("token", UserHelper.getToken());
        }
        OkGo.getInstance().init(this)
                .addCommonHeaders(headers);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());
        imagePicker.setMultiMode(false);
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(true);
        imagePicker.setSaveRectangle(false);
        imagePicker.setSelectLimit(1);
        imagePicker.setStyle(CropImageView.Style.CIRCLE);
        imagePicker.setFocusWidth(800);
        imagePicker.setFocusHeight(800);
        imagePicker.setOutPutX(1000);
        imagePicker.setOutPutY(1000);
    }
}
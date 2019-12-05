package com.liuhezhineng.ximengsifa.receiver.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.liuhezhineng.ximengsifa.MsgDetailsActivity;
import com.liuhezhineng.ximengsifa.bean.vidyo.RoomBean;
import com.liuhezhineng.ximengsifa.module.mine.business.MyBusinessActivity;
import com.liuhezhineng.ximengsifa.vidyo.VideoResponseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * @author AIqinfeng
 * @date 2018/7/11
 */

public class MyJPushBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = MyJPushBroadcastReceiver.class.getName();

    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        Bundle bundle = intent.getExtras();
        if (action == null) {
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        Log.i(TAG, "onReceive: " + printBundle(bundle));
        switch (action) {
            case JPushInterface.ACTION_REGISTRATION_ID:
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Log.d(TAG, "[MyReceiver] 接收 Registration Id : " + regId);
                break;
            case JPushInterface.ACTION_MESSAGE_RECEIVED:
                receivedMessage(context, bundle);
                break;
            case JPushInterface.ACTION_NOTIFICATION_RECEIVED:
                receivingNotification(context, bundle);
                break;
            case JPushInterface.ACTION_NOTIFICATION_OPENED:
                openNotification(context, bundle);
                break;
            case JPushInterface.ACTION_NOTIFICATION_CLICK_ACTION:
                break;
            case JPushInterface.ACTION_CONNECTION_CHANGE:
                break;
            default:
                break;
        }
    }

    /**
     * 自定义消息不会展示在通知栏，完全要开发者写代码去处理
     * 目前的自定义消息只有视频邀请
     * 这里没处理 如果已经在视频通话了，再次收到视频邀请 （仿照电话：你拨打的电话正在通话中）
     * 排队，队列，太复杂了，暂时不做。
     */
    private void receivedMessage(Context context, Bundle bundle) {
        Log.d(TAG, "收到了自定义消息。");
        try {
            String string = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            RoomBean roomBean = new Gson().fromJson(string, RoomBean.class);
            VideoResponseActivity.actionStart(context, roomBean);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    private void receivingNotification(Context context, Bundle bundle) {
        Log.d(TAG, "收到了通知" + printBundle(bundle));

        // 在这里可以做些统计，或者做些其他工作
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Log.i(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        Log.i(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.i(TAG, "extras : " + extras);
        String contentType = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);
        Log.i(TAG, "contentType : " + contentType);
    }

    /**
     * 1 快速申请 sys_user_push_type 中文 推送消息来源 10 修改 删除 添加键值
     * 2 法律援助 sys_user_push_type 中文 推送消息来源 20 修改 删除 添加键值
     * 3 人民调解 sys_user_push_type 中文 推送消息来源 30 修改 删除 添加键值
     * 4 站内通知 sys_user_push_type 中文 推送消息来源 40 修改 删除 添加键值
     * 5 留言咨询 sys_user_push_type 中文 推送消息来源 50 修改 删除 添加键值
     * 6 意见反馈 sys_user_push_type 中文 推送消息来源 60 修改 删除 添加键值
     * 99 其他
     *
     * @param context {@link Context}
     * @param bundle  {@link Bundle}
     */
    private void openNotification(Context context, Bundle bundle) {
        Log.d(TAG, "用户点击打开了通知");
        // 在这里可以自己写代码去定义用户点击后的行为
        String s = bundle.getString(JPushInterface.EXTRA_EXTRA);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String type = jsonObject.getString("type");
            switch (type) {
                case "1":
                case "2":
                case "3":
                    Intent i = new Intent(context, MyBusinessActivity.class);  //自定义打开的界面
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                    break;
                case "4":
                    MsgDetailsActivity.actionStart(context, jsonObject.getString("oaNotifyId"));
                    break;
                case "5":
//                    ConsultingComplaintDetailsActivity.actionStart(context, null, Constant.CONSULTING);
                    break;
                case "6":
//                    ConsultingComplaintDetailsActivity.actionStart(context, null, Constant.COMPLAINT);
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
        }
        return sb.toString();
    }
}

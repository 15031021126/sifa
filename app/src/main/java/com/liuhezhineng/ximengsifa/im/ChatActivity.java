package com.liuhezhineng.ximengsifa.im;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.adapter.EaseConversationAdapter;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.exceptions.HyphenateException;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 聊天窗口页面
 * 1、现有问题：
 * 1.1、对方的姓名显示
 * 1.2、自己和对方的头像显示
 */
public class ChatActivity extends BaseActivity implements EaseConversationAdapter.AvatarAndNameListener {

    private String mNickname;
    private String userId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initView() {
        super.initView();

        userId = getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID);
        mNickname = getIntent().getStringExtra("nickname");
        final boolean fromConversation = getIntent().getBooleanExtra("fromConversation", false);
        Log.i(TAG, "initView: " + userId);
        showLoadingDialog("加载数据中...");
        if (fromConversation) {
            gotoChat();
        } else {
            createChat();
        }
    }

    private void createChat() {
        // 大众点击在线咨询进来
        new Thread() {
            @Override
            public void run() {
                super.run();
                // 1、先自己创建账号
                UserBean user = UserHelper.getUser();
                try {
                    // 创建对方账号 这种定位截取都是前闭后开 [a, c)
                    EMClient.getInstance().createAccount(userId, userId.substring(userId.lastIndexOf("_") + 1));//同步方法
                } catch (HyphenateException e) {
                    // com.hyphenate.exceptions.HyphenateException: Registration failed.
                    // 已经注册。
                    e.printStackTrace();
                    // 创建对方账号 这种定位截取都是前闭后开 [a, c)
                } finally {
                    // 登录自己账号
                    EMClient.getInstance().login(UnicodeUtils.string2Unicode(user.getRealName()) + "_" + user.getId(), user.getId(), new EMCallBack() {//回调
                        @Override
                        public void onSuccess() {
                            Log.d("main", "登录聊天服务器成功！");
                            addContact();
                        }

                        @Override
                        public void onProgress(int progress, String status) {
                        }

                        @Override
                        public void onError(int code, String message) {
                            Log.d("main", "登录聊天服务器失败！" + "code: " + code + ", msg: " + message);
                            // 已经登录了
                            addContact();
                        }
                    });
                }

            }
        }.start();
    }

    private void addContact() {
        // 3、两个人添加好友（必须两个人账号都创建好了，而且本人已经登录成功了）
        //参数为要添加的好友的username和添加理由
        try {
            Log.i(TAG, "addContact: " + userId);
            EMClient.getInstance().contactManager().addContact(userId, "");
        } catch (HyphenateException e) {
            e.printStackTrace();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gotoChat();
                }
            });
        } finally {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gotoChat();
                }
            });
        }
    }

    private void gotoChat() {
        dismissLoadingDialog();
        EaseChatFragment chatFragment = new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        bundle.putString(EaseConstant.EXTRA_USER_ID, getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID));
        bundle.putString("nickname", mNickname);
        chatFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, chatFragment).commit();
    }

    @Override
    public void setAvatarAndName(String userId, final TextView textView, final ImageView imageView) {
        // 从回话列表过来的，先通过 userId 加载出用户的姓名。
        Map<String, String> map = new HashMap<>(16);
        map.put("id", userId.substring(userId.lastIndexOf("_") + 1));
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<UserBean>>post(NetConstant.GET_USE_INFO_VIA_USER_ID)
                .params(NetConstant.QUERY, queryStr)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<UserBean>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<UserBean>> response) {
                        UserBean userBean = response.body().getBody();
//                        textView.setText(userBean.getRealName());
                        Picasso.get().load(NetConstant.FILE_URL + userBean.getPhoto()).into(imageView);
                    }
                });
    }
}

package com.liuhezhineng.ximengsifa.module.mine.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.base.MainActivity;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.SP;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.im.UnicodeUtils;
import com.liuhezhineng.ximengsifa.model.DialogStringCallBack;
import com.liuhezhineng.ximengsifa.module.mine.ForgetPwdActivity;
import com.liuhezhineng.ximengsifa.utils.MD5Utils;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import okhttp3.Headers;

/**
 * @author AIqinfeng
 * @date 2018/4/17
 * @description 登录页面：登录
 * to: 注册，忘记密码。
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.tv_register)
    TextView mTvRegister;

    private String name;
    private String pwd;

    private boolean flag;

    public static void actionStart(Context context) {
        OkGo.getInstance().cancelAll();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void actionStart(Context context, boolean flag) {
        OkGo.getInstance().cancelAll();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("flag", flag);
        ((BaseActivity) context).startActivityForResult(intent, Constant.NORMAL_CODE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(mTopBar, R.string.login);

        flag = getIntent().getBooleanExtra("flag", false);
    }

    @Override
    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (checkInput()) {
                    login();
                }
                break;
            case R.id.tv_register:
                RegisterActivity.actionStart(mActivity);
                break;
            case R.id.tv_forget_pwd:
                ForgetPwdActivity.actionStart(mActivity);
                break;
            default:
                break;
        }
    }

    private boolean checkInput() {
        name = mEtAccount.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            mEtAccount.requestFocus();
            mEtAccount.setError(getString(R.string.phone_num_do_not_null));
            return false;
        }
        pwd = mEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            mEtPassword.requestFocus();
            mEtPassword.setError(getString(R.string.pwd_not_null));
            return false;
        }
        return true;
    }

    private void login() {
        Map<String, String> map = new HashMap<>(16);
        map.put(NetConstant.USERNAME, name);
        map.put(NetConstant.PASSWORD, MD5Utils.getMD5(pwd + name));
        String queryStr = new JSONObject(map).toString();
        OkGo.<String>post(NetConstant.LOGIN)
                .cacheMode(CacheMode.NO_CACHE)
                .params(NetConstant.QUERY, queryStr)
                .execute(new DialogStringCallBack(mActivity, "登录中...") {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }
                });
    }

    private void handleResponse(Response<String> response) {
        String body = response.body();
        try {
            JSONObject jsonObject = new JSONObject(body);
            int status = jsonObject.getInt(Constant.STATUS);
            if (status == Constant.SUCCESS) {
                Headers headers = response.headers();
                String token = "";
                if (headers != null) {
                    token = headers.get(NetConstant.TOKEN);
                    UserHelper.setToken(token);
                    OkGo.getInstance().addCommonHeaders(new HttpHeaders(NetConstant.TOKEN, token));
                }
                UserHelper.setIsLogin(true);
                JSONObject userJson = jsonObject.getJSONObject(Constant.BODY);

                UserBean user = new Gson().fromJson(userJson.toString(), UserBean.class);
                UserHelper.setUser(user);
                // 判断登录用户是普通用户还是服务人员。 if-else
                // 1、身份列表长度唯一，且name为普通用户 2、没有登录一律按照普通用户来设置，即默认
                if (user.getRoleList().size() == 1 && "普通用户".equals(user.getRoleList().get(0).getName())) {
                    UserHelper.setIsNormalUser(true);
                } else {
                    UserHelper.setIsNormalUser(false);
                }

                SharedPreferences sp = getSharedPreferences(SP.USER_INFO, MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString(Constant.TOKEN, token);
                ed.putBoolean(SP.IS_LOGIN, true);
                ed.putBoolean(SP.IS_NORMAL_USER, UserHelper.isIsNormalUser());
                ed.putString(SP.USER_INFO, new Gson().toJson(user));
                ed.apply();

							/*
							 * public static void setAlias(Context context, int sequence, String alias);
							 参数定义
							 sequence
							 用户自定义的操作序列号, 同操作结果一起返回，用来标识一次操作的唯一性。
							 alias
							 每次调用设置有效的别名，覆盖之前的设置。
							 有效的别名组成：字母（区分大小写）、数字、下划线、汉字、特殊字符@!#$&*+=.|。
							 限制：alias 命名长度限制为 40 字节。（判断长度需采用UTF-8编码）
							 */
                JPushInterface.setAlias(mActivity, 0, user.getId());
                String registrationID = JPushInterface.getRegistrationID(mActivity);

                OkGo.<String>post(NetConstant.JPUSH)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {

                            }
                        });

                loginEaseIM();

                if (flag) {
                    setResult(RESULT_OK);
                    finishActivity();
                } else {
                    MainActivity.actionStart(mActivity);
                }
            } else {
                ToastUtils.showLong(jsonObject.getString(Constant.MSG));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loginEaseIM() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                UserBean user = UserHelper.getUser();
                try {
                    EMClient.getInstance().createAccount(UnicodeUtils.string2Unicode(user.getRealName()) + "_" + user.getId(), user.getId()); // 同步方法
                } catch (HyphenateException e) {
                    e.printStackTrace();
                } finally {
                    EMClient.getInstance().logout(true);
                    EMClient.getInstance().login(UnicodeUtils.string2Unicode(user.getRealName()) + "_" + user.getId(), user.getId(), new EMCallBack() {//回调
                        @Override
                        public void onSuccess() {
                            Log.d("qingfeng", "登录聊天服务器成功！");

                            EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {

                                @Override
                                public void onContactAdded(String username) {
                                    //增加了联系人时回调此方法
                                }

                                @Override
                                public void onContactDeleted(String username) {
                                    //被删除时回调此方法
                                }

                                @Override
                                public void onContactInvited(String username, String reason) {
                                    //收到好友邀请
                                    Log.i(TAG, "onContactInvited: " + username);
                                    try {
                                        EMClient.getInstance().contactManager().addContact(username, reason);
                                    } catch (HyphenateException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFriendRequestAccepted(String s) {
                                    Log.i(TAG, "onFriendRequestAccepted: ");
                                }

                                @Override
                                public void onFriendRequestDeclined(String s) {

                                }
                            });
                        }

                        @Override
                        public void onError(int code, String message) {
                            // 已经登录了
                            Log.d("qingfeng", "登录聊天服务器失败！" + "code: " + code + ", msg: " + message);
                        }

                        @Override
                        public void onProgress(int progress, String status) {

                        }
                    });
                }
            }
        }.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constant.REGISTER) {
            String phoneNum = data.getStringExtra(Constant.PHONE_NUM);
            mEtAccount.setText(phoneNum);
        }
    }
}

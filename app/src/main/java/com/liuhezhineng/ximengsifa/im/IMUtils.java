package com.liuhezhineng.ximengsifa.im;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseAvatarOptions;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.utils.UserHelper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     author : qingfeng
 *     e-mail : 1913518036@qq.com
 *     time   : 2018/12/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class IMUtils {

    private EaseUI easeUI;
    private static IMUtils instance;

    private IMUtils() {}

    public static IMUtils getInstance () {
        if (instance == null) {
            synchronized (IMUtils.class) {
                if (instance == null) {
                    instance = new IMUtils();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        //get easeui instance
        easeUI = EaseUI.getInstance();//to set user's profile and avatar
        setEaseUIProviders();
    }

    protected void setEaseUIProviders() {
        //set user avatar to circle shape
        EaseAvatarOptions avatarOptions = new EaseAvatarOptions();
        avatarOptions.setAvatarShape(1);
        easeUI.setAvatarOptions(avatarOptions);

        // set profile provider if you want easeUI to handle avatar and nickname
        easeUI.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {

            @Override
            public EaseUser getUser(String username) {
                return getUserInfo(username);
            }
        });
    }

    private EaseUser getUserInfo(String username) {
        Map<String, String> map = new HashMap<>(16);
        map.put(NetConstant.ID, username);
        String queryStr = new JSONObject(map).toString();
        try {
            // 同步方法必须开线程执行
//            Call<BaseBean<UserBean>> adapt = OkGo.<BaseBean<UserBean>>post(NetConstant.GET_USE_INFO_VIA_USER_ID)
//                    .params(NetConstant.QUERY, queryStr)
//                    .converter(new JsonConvert<BaseBean<UserBean>>())
//                    .adapt();
//            Response<BaseBean<UserBean>> response = adapt.execute();
//            return response.body().getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        EaseUser user = new EaseUser(username);
        EaseCommonUtils.setUserInitialLetter(user);
        return user;
    }

    public static void createAccount() {
        try {
            UserBean user = UserHelper.getUser();
            EMClient.getInstance().createAccount(user.getMobile() + "_" + user.getId(), user.getId()); // 同步方法
//                    EaseUser easeUser = EaseUserUtils.getUserInfo(user.getId());
//                    easeUser.setNickname(user.getRealName());
        } catch (HyphenateException e) {
            // com.hyphenate.exceptions.HyphenateException: Registration failed.
            // 已经注册。
            e.printStackTrace();
        } finally {
            logout();
        }
    }

    public static void logout() {
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                login();
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                ToastUtils.showLong("code: " + code + ", msg: " + message);
            }
        });
    }


    public static void login() {
        UserBean user = UserHelper.getUser();
        EMClient.getInstance().login(user.getMobile() + "_" + user.getId(), user.getId(), new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                Log.d("main", "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                // 已经登录了
                Log.d("main", "登录聊天服务器失败！" + "code: " + code + ", msg: " + message);
            }
        });
    }
}

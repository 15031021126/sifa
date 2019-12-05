package com.liuhezhineng.ximengsifa.im;


import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 继承自 EaseUI 库，用来进行一些自己的操作，比如设置头像，昵称等。
 */
public class ChatFragment extends EaseChatFragment{

    @Override
    protected void setUpView() {
        super.setUpView();
        String userId = getArguments().getString(EaseConstant.EXTRA_USER_ID);
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
                        titleBar.setTitle(response.body().getBody().getRealName());
                    }
                });
    }
}

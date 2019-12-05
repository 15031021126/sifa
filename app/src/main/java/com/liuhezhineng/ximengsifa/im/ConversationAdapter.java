package com.liuhezhineng.ximengsifa.im;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.adapter.EaseConversationAdapter;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     author : qingfeng
 *     e-mail : 1913518036@qq.com
 *     time   : 2018/12/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ConversationAdapter extends EaseConversationAdapter {

    public ConversationAdapter(Context context, int resource, List<EMConversation> objects) {
        super(context, resource, objects);
    }

    private void setAvatarAndName(int position, final TextView tvName, final ImageView ivAvatar) {
        // get conversation
        EMConversation conversation = getItem(position);
        // get username or group id
        final String userId = conversation.conversationId();

        // 从回话列表过来的，先通过 userId 加载出用户的姓名。
        Map<String, String> map = new HashMap<>(16);
        map.put("id", userId.substring(userId.indexOf("_") + 1));
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<UserBean>>post(NetConstant.GET_USE_INFO_VIA_USER_ID)
                .params(NetConstant.QUERY, queryStr)
                .execute(new JsonCallback<BaseBean<UserBean>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<UserBean>> response) {
                        UserBean userBean = response.body().getBody();
                        tvName.setText(userBean.getRealName());
                        Picasso.get().load(NetConstant.FILE_URL + userBean.getPhoto());
                    }
                });
    }
}

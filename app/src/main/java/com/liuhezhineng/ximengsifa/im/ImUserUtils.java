package com.liuhezhineng.ximengsifa.im;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
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
public class ImUserUtils {

    /**
     * set user avatar
     *
     * @param userId
     */
    public static void setUserAvatarAndName(Context context, String userId, final TextView textView, final ImageView imageView) {

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
                        textView.setText(userBean.getRealName());
                        Picasso.get().load(NetConstant.FILE_URL + userBean.getPhoto()).into(imageView);
                    }
                });
    }
}

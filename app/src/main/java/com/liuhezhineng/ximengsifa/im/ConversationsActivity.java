package com.liuhezhineng.ximengsifa.im;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.adapter.EaseConversationAdapter;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ConversationsActivity extends BaseActivity implements EaseConversationAdapter.AvatarAndNameListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_conversations;
    }

    @Override
    protected void initView() {
        super.initView();
        gotoConversation();
    }

    public static void actionStart(Context context) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, ConversationsActivity.class);
            context.startActivity(intent);
        } else {
            gotoLogin(context);
        }
    }

    private void gotoConversation() {
        EaseConversationListFragment conversationListFragment = new EaseConversationListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, conversationListFragment)
                .commit();

        conversationListFragment.setConversationListItemClickListener(
                new EaseConversationListFragment.EaseConversationListItemClickListener() {

                    @Override
                    public void onListItemClicked(EMConversation conversation) {
                        Intent intent = new Intent(ConversationsActivity.this, ChatActivity.class);
                        intent.putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId());
                        intent.putExtra("fromConversation", true);
                        startActivity(intent);
                    }
                });
    }

    @Override
    public void setAvatarAndName(String userId, final TextView textView, final ImageView imageView) {
        // 从回话列表过来的，先通过 userId 加载出用户的姓名。
        Map<String, String> map = new HashMap<>(16);
        map.put("id", userId.substring(userId.lastIndexOf("_") + 1));
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

package com.liuhezhineng.ximengsifa.module.mine;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.easeui.domain.UnReadEvent;
import com.liuhezhineng.ximengsifa.LocalMsgActivity;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseFragment;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.login.RoleBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.form.MyRemoteVisitApplyListActivity;
import com.liuhezhineng.ximengsifa.im.ConversationsActivity;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.module.mine.business.MyBusinessActivity;
import com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint.ConsultingComplaintActivity;
import com.liuhezhineng.ximengsifa.module.mine.login.LoginActivity;
import com.liuhezhineng.ximengsifa.module.mine.order.MyOrderActivity;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView.Section;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.liuhezhineng.ximengsifa.constant.Constant.IMAGE_PICKER;

/**
 * @author AIqinfeng
 * @description 模块：我的
 */
public class MyFragment extends BaseFragment implements OnClickListener {

    private final String myPendingStr = "我的案件";
    private final String myRemoteVisitorStr = "我的远程探视预约";
    private final String myDealRemoteVisitorStr = "待处理的远程探视预约";
    private final String myOrder = "我的预约";
    private final String myLocalMsg = "我的消息";
    private final String myConsultationStr = "知识问答咨询";
    private final String myIMStr = "我的在线咨询";
    private final String myComplaints = "投诉建议";
    private final String myEvaluate = "我的评价";
    private final String settingsStr = "设置";

    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.mime_list)
    QMUIGroupListView mMimeList;

    LocalBroadcastManager broadcastManager;
    IntentFilter intentFilter;
    BroadcastReceiver mReceiver;
    QMUICommonListItemView imItemView;
    private String path;

    public static MyFragment newInstance(@StringRes int moduleTitle) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.MODULE_TITLE, moduleTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UnReadEvent event) {
        Log.i("qingfeng", "onMessageEvent: " + event.getUnReadCount());
        /* Do something */
        if (event.getUnReadCount() > 0) {
            imItemView.setDetailText("你有" + event.getUnReadCount() + "条新的消息");
            imItemView.getDetailTextView().setTextColor(Color.RED);
        }
    }

    @OnClick({R.id.iv_avatar, R.id.tv_username})
    public void onViewClicked(View view) {
        if (!UserHelper.isIsLogin()) {
            LoginActivity.actionStart(mActivity);
            return;
        }
        switch (view.getId()) {
            case R.id.iv_avatar:
            case R.id.tv_username:
                ProfileActivity.actionStart(mActivity);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constant.LOGIN) {
                UserBean user = UserHelper.getUser();
                if (user != null) {
                    mTvUsername.setText(user.getRealName());
                    Picasso.get().load(NetConstant.FILE_URL + user.getPhoto())
                            .placeholder(R.drawable.user)
                            .into(mIvAvatar);
                }
            }
        }
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList images = (ArrayList) data
                        .getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                path = ((ImageItem) images.get(0)).path;
                uploadAvatar();
            } else {
                ToastUtils.showLong(R.string.no_data);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        broadcastManager = LocalBroadcastManager.getInstance(mActivity);
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.UPLOAD_AVATAR);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //收到广播后所作的操作
                String avatar = intent.getStringExtra(Constant.UPLOAD_AVATAR);
                Picasso.get().load(NetConstant.FILE_URL + avatar)
                        .placeholder(R.drawable.user)
                        .into(mIvAvatar);
            }
        };
        broadcastManager.registerReceiver(mReceiver, intentFilter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("qingfeng", "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (UserHelper.isIsLogin()) {
            UserBean user = UserHelper.getUser();
            mTvUsername.setText(user.getRealName());
            Picasso.get().load(NetConstant.FILE_URL + user.getPhoto())
                    .placeholder(R.drawable.user)
                    .into(mIvAvatar);
        } else {
            mIvAvatar.setImageResource(R.drawable.user);
            mTvUsername.setText(R.string.click_login_and_register);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void uploadAvatar() {
        OkGo.<BaseBean<String>>post(NetConstant.UPLOAD_AVATAR)
                .params(NetConstant.FILE, new File(path))
                .execute(new DialogCallBack<BaseBean<String>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        mIvAvatar.setImageURI(Uri.fromFile(new File(path)));
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        broadcastManager.unregisterReceiver(mReceiver);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        super.initView();

        initQMUIList();
    }

    private void initQMUIList() {
        mMimeList.setSeparatorStyle(QMUIGroupListView.SEPARATOR_STYLE_NORMAL);
        List<String> list = new ArrayList<>();
        list.add(myPendingStr);

        addRemoteVisitItem(list);
        list.add(myOrder);
//        list.add(myDraft);
        list.add(myLocalMsg);
        list.add(myConsultationStr);
        list.add(myComplaints);
        // IM 已登录并且，不是普通人员则是服务人员，则添加我的在线咨询
        if (UserHelper.isIsLogin() && !UserHelper.isIsNormalUser()) {
            list.add(myIMStr);
        }
        list.add(myEvaluate);
        list.add(settingsStr);
        createListItemView(list.toArray(new String[]{}));
    }

    /**
     * 设置远程探视栏目的显示
     * 普通大众：只有自己的申请栏目
     * 社区矫正管理员：有待处理的栏目
     * @param list 栏目列表 todo 后期优化可从服务端来控制
     */
    private void addRemoteVisitItem(List<String> list) {
        UserBean user = UserHelper.getUser();
        list.add(myRemoteVisitorStr);
        for (RoleBean bean : user.getRoleList()) {
            if ("社区矫正管理员".equals(bean.getName())) {
                list.add(myDealRemoteVisitorStr);
            }
        }
    }

    private void createListItemView(String... strings) {
        Section section = QMUIGroupListView.newSection(mActivity);
        for (String str : strings) {
            final QMUICommonListItemView itemView = mMimeList.createItemView(str);
            itemView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
            section.addItemView(itemView, this);
            if (myIMStr.equalsIgnoreCase(str)) {
                imItemView = itemView;
            }
        }
        section.addTo(mMimeList);
    }

    @Override
    protected void setListener() {
        super.setListener();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof QMUICommonListItemView) {
            String string = String.valueOf(((QMUICommonListItemView) v).getText());
            actionClickList(string);
        }
    }

    private void actionClickList(String content) {
        switch (content) {
            case myPendingStr:
                MyBusinessActivity.actionStart(mActivity);
                break;
            case myLocalMsg:
                LocalMsgActivity.actionStart(mActivity);
                break;
            case myOrder:
                MyOrderActivity.actionStart(mActivity);
                break;
            case myConsultationStr:
                ConsultingComplaintActivity.actionStart(mActivity, Constant.CONSULTING);
                break;
            case myComplaints:
                ConsultingComplaintActivity.actionStart(mActivity, Constant.COMPLAINT);
                break;
            case myEvaluate:
                MyEvaluateActivity.actionStart(mActivity);
                break;
            case myIMStr:
                ConversationsActivity.actionStart(mActivity);
                break;
            case myRemoteVisitorStr:
                MyRemoteVisitApplyListActivity.actionStart(mActivity, Constant.HAVE_DONE);
                break;
            case myDealRemoteVisitorStr:
                MyRemoteVisitApplyListActivity.actionStart(mActivity, Constant.UP_COMING);
                break;
            case settingsStr:
                SettingsActivity.actionStart(mActivity);
                break;
            default:
                SettingsActivity.actionStart(mActivity);
                break;
        }
    }
}

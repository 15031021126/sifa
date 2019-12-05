package com.liuhezhineng.ximengsifa.vidyo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.vidyo.Contacts;
import com.liuhezhineng.ximengsifa.bean.vidyo.RoomBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.permission.RuntimeRationale;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Setting;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author iqing
 * @description 视频通话发起
 */
public class VideoRequestActivity extends BaseActivity {

    public static final String VIDYO_12348 = "12348视频咨询";

    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.ll_vidyo_permission)
    LinearLayout mLlVidyoPermission;
    @BindView(R.id.rl_vidyo_info)
    RelativeLayout mRvVidyoInfo;
    @BindView(R.id.surface_view)
    FrameLayout mAspectLayout;
    String roomName;
    private boolean mCameraRequested;
    private RoomBean mRoomBean;
    private String name;
    private String caseTitle;
    private String userId;

    public static void actionStart(Context context, String name, String caseTitle, String userId) {
        if (userId.equals(UserHelper.getUser().getId())) {
            ToastUtils.showLong("不能对自己发起视频通话");
            return;
        }
        Intent intent = new Intent(context, VideoRequestActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("case_title", caseTitle);
        intent.putExtra("user_id", userId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_request;
    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {
        requestVidyoPermission();
    }

    @Override
    protected void initData() {
        super.initData();
        getIntentExtra();
        mTvName.setText(name);
    }

    private void getIntentExtra() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        caseTitle = intent.getStringExtra("case_title");
        userId = intent.getStringExtra("user_id");
    }

    @OnClick({R.id.btn_vidyo_permission, R.id.iv_hang_up})
    @Override
    public void onViewClicked(View v) {
        super.onViewClicked(v);
        switch (v.getId()) {
            case R.id.btn_vidyo_permission:
                requestVidyoPermission();
                break;
            case R.id.iv_hang_up:
                finishActivity();
                break;
            default:
                break;
        }
    }

    private void requestVidyoPermission() {
        // 申请 vidyo 视频会议所需要的权限。相机，内存和录音
        AndPermission.with(mActivity)
                .runtime()
                .permission(Permission.CAMERA,
                        Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.RECORD_AUDIO)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        mLlVidyoPermission.setVisibility(View.GONE);
                        mAspectLayout.setBackground(null);
                        mRvVidyoInfo.setVisibility(View.VISIBLE);
                        setupView();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (AndPermission.hasAlwaysDeniedPermission(VideoRequestActivity.this, data)) {
                            showSettingDialog(VideoRequestActivity.this, data);
                        } else {
                            mLlVidyoPermission.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .start();
    }

    /**
     * Display setting dialog.
     */
    public void showSettingDialog(Context context, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed, TextUtils.join("\n", permissionNames));

        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(R.string.title_dialog)
                .setMessage(message)
                .setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPermission();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mLlVidyoPermission.setVisibility(View.VISIBLE);
                    }
                })
                .show();
    }

    /**
     * Set permissions.
     */
    private void setPermission() {
        AndPermission.with(this)
                .runtime()
                .setting()
                .onComeback(new Setting.Action() {
                    @Override
                    public void onAction() {
                        requestVidyoPermission();
                    }
                })
                .start();
    }

    private void setupView() {
        mCameraRequested = true;
        mAspectLayout = findViewById(R.id.surface_view);
        CameraSurfaceView mCameraSurfaceView = new CameraSurfaceView(this);
        CameraUtils.calculateCameraPreviewOrientation(this);
        mAspectLayout.addView(mCameraSurfaceView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        OkGo.getInstance().cancelTag(VideoRequestActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        CameraUtils.stopPreview();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCameraRequested) {
            CameraUtils.startPreview();
            createVideoRoom();
        }
    }

    private void createVideoRoom() {
        if (VIDYO_12348.equals(caseTitle)) {
            OkGo.<BaseBean<List<Contacts>>>get(NetConstant.Vidyo.GET_ALL_12348)
                    .params("query", "12348")
                    .execute(new DialogCallBack<BaseBean<List<Contacts>>>(mActivity,
                            "获取所有 12348 服务人员...") {
                        @Override
                        public void onSuccess(Response<BaseBean<List<Contacts>>> response) {
                            List<Contacts> contactsList = response.body().getBody();
                            int i = 0;
                            for (; i < contactsList.size(); i++) {
                                Contacts contacts = contactsList.get(i);
                                if (contacts.getDisplayName().contains("12348") &&
                                        "Empty".equals(contacts.getRoomStatus()) &&
                                        "Online".equals(contacts.getMemberStatus())) {
                                    OkGo.<BaseBean<RoomBean>>post(NetConstant.Vidyo.GET_ROOM_BY_EXTENSION)
                                            .params("extension", contacts.getExtension())
                                            .execute(new DialogCallBack<BaseBean<RoomBean>>(mActivity, "获取房间信息中...") {
                                                @Override
                                                public void onSuccess(Response<BaseBean<RoomBean>> response) {
                                                    mRoomBean = response.body().getBody();
                                                    Log.i(TAG, "onSuccess: " + mRoomBean.toString());
                                                    OkGo.<BaseBean<String>>get(NetConstant.Vidyo.INVITE_CLIENT)
                                                            .tag(VideoRequestActivity.this)
                                                            .params("conferenceId", mRoomBean.getRoomID())
                                                            .params("members", mRoomBean.getRoomID())
                                                            .execute(new DialogCallBack<BaseBean<String>>(mActivity, "邀请 12348 服务人员入会中...") {
                                                                @Override
                                                                public void onSuccess(Response<BaseBean<String>> response) {
//                                                                    roomName = caseTitle;
                                                                    // 12348 房间信息
                                                                    //    "roomID":3,
                                                                    //        "roomName":"room4",
                                                                    //        "extension":"47570888",
                                                                    //        "roomURL":"http://portal.xlgl12348.gov.cn/flex.html?roomdirect.html&key=BXuOSooRoh",
                                                                    //        "roomKey":"BXuOSooRoh"
//                                                                    mRoomBean = new RoomBean();
//                                                                    mRoomBean.setRoomURL("http://portal.xlgl12348.gov.cn/flex.html?roomdirect.html&key=BXuOSooRoh");
//                                                                    mRoomBean.setRoomKey("BXuOSooRoh");
//                                                                    mRoomBean.setRoomName(VideoRequestActivity.VIDYO_12348);
//                                                                    mRoomBean.setRoomID("405");
                                                                    ConferenceActivity.actionStart(mActivity, mRoomBean);
                                                                    finishActivity();
                                                                }
                                                            });
                                                }
                                            });
                                    break;
                                }
                            }
                            if (i == contactsList.size()) {
                                ToastUtils.showLong("12348 服务人员全部忙碌中，请稍后再试");
                            }
                        }
                    });
        } else {
            roomName = caseTitle + System.currentTimeMillis();
            OkGo.<BaseBean<RoomBean>>post(NetConstant.Vidyo.CREATE_TEMP_ROOM)
                    .tag(VideoRequestActivity.this)
                    .retryCount(1)
                    // 房间名不能重复,所以设置了上面重连次数
                    .params("roomName", roomName)
                    //邀请进入的人员Id，支持英文逗号分割
                    .params("userIds", userId)
                    .execute(new JsonCallback<BaseBean<RoomBean>>() {

                        @Override
                        public void onSuccess(Response<BaseBean<RoomBean>> response) {
                            mRoomBean = response.body().getBody();
                            ConferenceActivity.actionStart(mActivity, mRoomBean);
                            finishActivity();
                        }
                    });
        }
    }
}

package com.liuhezhineng.ximengsifa.vidyo;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.audiorecord.MediaPlayerManager;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.vidyo.RoomBean;
import com.liuhezhineng.ximengsifa.permission.RuntimeRationale;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Setting;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author iqing
 */
public class VideoResponseActivity extends BaseActivity {

    public static final String VIDYO_RESPONSE = "vidyo_response";

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

    private boolean mCameraRequested;
    private RoomBean mRoomBean;

    /**
     * 这里是推送跳转过来的，所以要加 new task flag.
     *
     * @param context  {@link Context}
     * @param roomBean {@link RoomBean}
     */
    public static void actionStart(Context context, RoomBean roomBean) {
        Intent responseIntent = new Intent(context, VideoResponseActivity.class);
        responseIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        responseIntent.putExtra(VIDYO_RESPONSE, roomBean);
        context.startActivity(responseIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        CameraUtils.stopPreview();
        MediaPlayerManager.stopRing();
//        CameraUtils.releaseCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCameraRequested) {
            CameraUtils.startPreview();
            MediaPlayerManager.playRing(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vidyo_response;
    }

    @Override
    protected void initView() {
        wakeUpScreen();
        requestVidyoPermission();
    }

    @Override
    protected void initData() {
        super.initData();

        mRoomBean = (RoomBean) getIntent().getSerializableExtra(VIDYO_RESPONSE);
    }

    @OnClick({R.id.btn_vidyo_permission, R.id.iv_accept, R.id.iv_hang_up})
    @Override
    public void onViewClicked(View v) {
        super.onViewClicked(v);
        switch (v.getId()) {
            case R.id.btn_vidyo_permission:
                requestVidyoPermission();
                break;
            case R.id.iv_accept:
                MediaPlayerManager.stopRing();
                ConferenceActivity.actionStart(mActivity, mRoomBean);
                finishActivity();
                break;
            case R.id.iv_hang_up:
                MediaPlayerManager.stopRing();
                finishActivity();
//                if (mRoomBean != null && !"405".equals(mRoomBean.getRoomID())) {
//                    OkGo.<BaseBean>post(NetConstant.Vidyo.DELETE_TEMP_ROOM)
//                            .params("roomID", mRoomBean.getRoomID())
//                            .execute(new JsonCallback<BaseBean>() {
//                                @Override
//                                public void onSuccess(Response<BaseBean> response) {
//                                    finishActivity();
//                                }
//                            });
//                } else {
//                    finishActivity();
//                }
                break;
            default:
                break;
        }
    }

    private void wakeUpScreen() {
        // 键盘锁管理器对象
        KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        // 这里参数”kale”作为调试时LogCat中的Tag
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("kale");
        if (km.inKeyguardRestrictedInputMode()) {
            // 解锁键盘
            kl.disableKeyguard();
        }

        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);// init powerManager
        PowerManager.WakeLock mWakelock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.SCREEN_DIM_WAKE_LOCK, "wisdomjustice:target"); // this target for tell OS which app control screen
        mWakelock.acquire();
        mWakelock.release();
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
                        if (AndPermission.hasAlwaysDeniedPermission(VideoResponseActivity.this, data)) {
                            showSettingDialog(VideoResponseActivity.this, data);
                        } else {
                            mLlVidyoPermission.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .start();
    }

    private void setupView() {
        mCameraRequested = true;
        CameraSurfaceView mCameraSurfaceView = new CameraSurfaceView(this);
        mAspectLayout.addView(mCameraSurfaceView);
        CameraUtils.calculateCameraPreviewOrientation(this);
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
}

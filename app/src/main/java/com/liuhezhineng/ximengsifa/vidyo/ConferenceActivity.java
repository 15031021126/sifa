package com.liuhezhineng.ximengsifa.vidyo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.vidyo.RoomBean;
import com.vidyo.LmiDeviceManager.LmiVideoCapturerManager;
import com.vidyo.business.ConferenceHelper;
import com.vidyo.provider.ChitVideo;
import com.vidyo.utils.StorageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ConferenceActivity extends BaseActivity {

    private static final String TAG = "ConferenceActivity";
    public static ChitVideo mProvider;
    @BindView(R.id.iv_cancel)
    ImageView mIvCancel;
    MessageHandler mVidyoCallBackHandler;
    int usedCamera = LmiVideoCapturerManager.FRONT;
    ScrollView mScrollView;
    private ListView listView;
    private LinearLayout mChatWnd;
    private FrameLayout mVideoHolder;
    private int vidyoHeight, vidyoWidth;
    private ProgressDialog mDialog;
    private ConferenceHelper mConferenceHelper;

    private RoomBean mRoomBean;

    public static void actionStart(Context context, RoomBean roomBean) {
        Intent intent = new Intent(context, ConferenceActivity.class);
        intent.putExtra("room_bean", roomBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.vidyo_request;
    }

    @Override
    protected void initView() {
        super.initView();

        mRoomBean = (RoomBean) getIntent().getSerializableExtra("room_bean");

        WindowManager wm = this.getWindowManager();

        mVidyoCallBackHandler = new MessageHandler(ConferenceActivity.this);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        mVideoHolder = findViewById(R.id.glsurfaceview);
        mVideoHolder.getLayoutParams().width = width;
        mVideoHolder.getLayoutParams().height = (width / 16) * 12;
        mVideoHolder.getLayoutParams().height = height;
        vidyoWidth = width;
        vidyoHeight = (width / 16) * 12;
        initVidyoView();
    }

    @Override
    protected void setListener() {
        super.setListener();
        mIvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveConference();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProvider.unInitialize();

        if (mConferenceHelper != null) {
            mConferenceHelper.onDestroy();
            mConferenceHelper = null;
        }

        if (mDialog != null) {
            mDialog.cancel();
            mDialog = null;
        }
    }

    private void leaveConference() {
        if (mDialog != null) {
            mDialog.cancel();
        }
        mDialog = ProgressDialog
                .show(this, "", "退出视频通话...");
        mDialog.setCancelable(true);
        mProvider.leaveConference();
    }

    private void initVidyoView() {
        mConferenceHelper = new ConferenceHelper();
        mConferenceHelper.initial(this);

        View conferenceView = mConferenceHelper.createConferenceView(this);
        mVideoHolder.addView(conferenceView);

        mScrollView = findViewById(R.id.scorllview1);
        mChatWnd = findViewById(R.id.chatwnd);
        setupAudioForEngagement();
        // 获取Vidyo认证证书
        String caFileName = StorageUtils.writeCaCertificates(this, R.raw.ca_certificates);
        mProvider = ChitVideo.getInstance();
        mProvider.setHandler(mVidyoCallBackHandler);
        mProvider.initialize(caFileName, this, () -> {
            Log.e(TAG, "onLibraryStarted successfully");

            boolean enableForceProxy = SharePreferenceUtil.getEnableProxyServer(ConferenceActivity.this);
            mProvider.enableForceProxy(enableForceProxy);

            mProvider.setPreviewModeON(true);
            mProvider.setSpeakerVolume(65535);     // 设置speaker音量为最大
            mProvider.enableMenuBar(false);       // 是否显示底部工具栏
            mProvider.showParticipantName(true); // 是否显示参会人名称
            roomLinkTest();                  // guest入会
        });

    }

    List<String> getData() {
        List<String> list = new ArrayList<>();
        list.add("游客入会");
        list.add("结束会议");
        list.add("静音");
        list.add("取消静音");
        list.add("关闭扬声器");
        list.add("打开扬声器");
        list.add("关闭相机");
        list.add("打开相机");
        list.add("前置相机");
        list.add("后置相机");
        list.add("发送IM消息");
        list.add("跳转下一页");
        list.add("调整视频大小");
        list.add("会议中显示自己名称");
        list.add("会议中不显示自己名称");
        list.add("会议preview打开");
        list.add("会议preview关闭");
        list.add("分享图片");
        list.add("停止分享");
        list.add("显示隐藏参会人");
        list.add("不显示隐藏参会人");
        list.add("显示隐藏共享");
        list.add("不显示隐藏共享");
        list.add("Dock参会人是否显示");
        list.add("Dock共享是否显示");
        list.add("登陆");
        list.add("直呼");
        list.add("注销");
        return list;
    }

    private void setupAudioForEngagement() {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);

        final int mode = audioManager.getMode();

        if (mode == AudioManager.MODE_NORMAL) {
            final int volume = audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
            audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, volume, 0);
            audioManager.setMode(AudioManager.MODE_NORMAL);
            audioManager.setSpeakerphoneOn(true);
        } else {
            audioManager.setMode(AudioManager.MODE_IN_CALL);
            audioManager.setSpeakerphoneOn(false);
            audioManager.setMicrophoneMute(false);
        }
    }

    private void roomLinkTest() {
        if (mDialog != null) {
            mDialog.cancel();
        }

        mDialog = ProgressDialog.show(this, "", "进入视频通话...");

        Uri uri = Uri.parse(mRoomBean.getRoomURL());
        String roomUrl = uri.getHost();
        String roomKey = uri.getQueryParameter("key");
        String displayName = mRoomBean.getRoomName();
        String pin = "";
        mProvider.joinConference(roomUrl, roomKey, displayName, pin,
                false,
                false,
                false);
    }

    @Override
    public void onBackPressed() {
        if (mProvider.getCallState() == ChitVideo.CHITVIDEO_CALL_STATE_IDLE) {
            finish();
        } else {
            leaveConference();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mConferenceHelper.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        mConferenceHelper.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "in onConfigurationChanged.");

        mConferenceHelper.onConfigurationChanged(newConfig);

        Log.e(TAG, "vidyoHeight: " + vidyoHeight);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (vidyoHeight == 0) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500);
                mVideoHolder.setLayoutParams(lp);
            } else {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, vidyoHeight);
                mVideoHolder.setLayoutParams(lp);
            }
            mScrollView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.VISIBLE);
        }

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mVideoHolder.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            mScrollView.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
        }

    }

    @SuppressLint("SetTextI18n")
    public void RequestResult(int type, String msgInfo, int errCode) {

        Log.i(TAG, "type is: " + type + ", msgInfo is: " + msgInfo + ", errCode is: " + errCode);
        switch (type) {
            case ChitVideo.CHITVIDEO_LOG:
                Log.i(TAG, "CHITVIDEO_LOG: " + msgInfo); //打印log消息
                break;
            case ChitVideo.CHITVIDEO_CONFERENCE_ACTIVE:
                Log.e(TAG, "CHITVIDEO_CONFERENCE_ACTIVE");
                vidyoWidth = mVideoHolder.getWidth();
                vidyoHeight = mVideoHolder.getHeight();

                // 收到此消息表示已成功入会，可以做相关的会议设置操作
                mProvider.setPreviewModeON(false);   // 设置预览模式， false则不会显示自己的视频画面
                mProvider.switchCamera(usedCamera); // 切换摄像头，0 为后置摄像头，1为前置摄像头
                mConferenceHelper.startDevices();                 // 开始进行渲染

                int ori = mConferenceHelper.getOrientation();
                mProvider.setOrientation(ori);

                if (mDialog != null) {
                    mDialog.cancel();
                }

                break;
            case ChitVideo.CHITVIDEO_CONFERENCE_CLEARED:
                Log.e(TAG, "CHITVIDEO_CONFERENCE_CLEARED");
                if (mDialog != null) {
                    mDialog.dismiss();
                }
                mConferenceHelper.stopDevices();          // 停止渲染
                Toast.makeText(ConferenceActivity.this, "会议结束." + msgInfo, Toast.LENGTH_SHORT).show();
                // 如果不是 12348 视频房间则推出的时候删除
//                if (!"405".equals(mRoomBean.getRoomID())) {
//                    OkGo.<BaseBean>post(NetConstant.Vidyo.DELETE_TEMP_ROOM)
//                            .params("roomID", mRoomBean.getRoomID())
//                            .execute(new JsonCallback<BaseBean>() {
//                                @Override
//                                public void onSuccess(Response<BaseBean> response) {
//
//                                }
//                            });
//                }
                finishActivity();
                break;
            case ChitVideo.CHITVIDEO_DEVICE_SELECTION_CHANGED:
                // 收到此消息表示有设备切换事件发生.
                Log.e(TAG, "CHITVIDEO_DEVICE_SELECTION_CHANGED: " + msgInfo);
                break;
            case ChitVideo.CHITVIDEO_SIGNED_IN:
                Log.e(TAG, "CHITVIDEO_SIGNED_IN");
                Toast.makeText(ConferenceActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                // 收到此事件表示用户登录vidyo成功， guest入会也会有此事件回调
                break;
            case ChitVideo.CHITVIDEO_GROUP_CHAT:

                // 收到此事件表示有聊天事件发生, msgInfo代表聊天内容
                Log.e(TAG, "CHITVIDEO_GROUP_CHAT: " + msgInfo);
                if (msgInfo.equals("action:StartConference")) {
                    System.out.println("----收到消息指令");
                    mProvider.muteCamera(false);
                    mProvider.muteMicrophone(false);
                    mProvider.muteSpeaker(false);
                } else {
                    TextView tv = new TextView(ConferenceActivity.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.gravity = Gravity.END;
                    tv.setLayoutParams(lp);
                    tv.setText("对方：" + msgInfo);
                    mChatWnd.addView(tv);
                }
                break;
            case ChitVideo.CHITVIDEO_SIGNED_OUT:
                // 收到此事件表示用户退出登录或登陆失败， guest入会失败可能会回调此事件。
                Log.e(TAG, "CHITVIDEO_SIGNED_OUT: " + msgInfo);
                int cause = Integer.parseInt(msgInfo);
                if (cause == ChitVideo.CHITVIDEO_USER_SIGNED_OUT) {
                    Toast.makeText(ConferenceActivity.this, "账号注销成功", Toast.LENGTH_SHORT).show();
                } else if (cause == ChitVideo.CHITVIDEO_USER_SIGNED_IN) {
                    Toast.makeText(ConferenceActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case ChitVideo.CHITVIDEO_PARTICIPANTS_CHANGED:
                // 收到此事件表示参会人员有变化，可能有人参加会议或推出会议，msgInfo表示当前会议人数
                Log.e(TAG, "CHITVIDEO_PARTICIPANTS_CHANGED: ");
                break;
            case ChitVideo.CHITVIDEO_JOIN_FAILED:
                // 入会流程中间事件，如果收到此事件，表示入会失败
                Toast.makeText(ConferenceActivity.this, "加入会议失败，错误代码: " + errCode, Toast.LENGTH_LONG).show();
                finish();
                break;
            case ChitVideo.CHITVIDEO_MUTED_AUDIO_IN:
                // 禁音麦克风成功则返回0，否则失败
                if (errCode != ChitVideo.CHITVIDEO_ERROR_OK) {
                    Log.e(TAG, "mute/unMute MicroPhone failed: " + errCode);
                } else {
                    Log.e(TAG, "mute/unMute MicroPhone success. ");
                }
                break;
            case ChitVideo.CHITVIDEO_MUTED_AUDIO_OUT:
                // 禁用/启用 听筒成功则返回0，否则失败
                if (errCode != ChitVideo.CHITVIDEO_ERROR_OK) {
                    Log.e(TAG, "mute/unMute Speaker failed: " + errCode);
                } else {
                    Log.e(TAG, "mute/unMute Speaker success. ");
                }
                break;
            case ChitVideo.CHITVIDEO_MUTED_VIDEO:
                // 禁用/启用 摄像头操作成功则返回0，否则失败
                if (errCode != ChitVideo.CHITVIDEO_ERROR_OK) {
                    Log.e(TAG, "mute/unMute Camera failed: " + errCode);
                } else {
                    Log.e(TAG, "mute/unMute Camera  success. ");
                }
                break;
            case ChitVideo.CHITVIDEO_LOGIC_START_FAILED:
                Log.e(TAG, "CHITVIDEO_LOGIC_START_FAILED ");
                break;
            case ChitVideo.CHITVIDEO_LEAVE_CONFERENCE_FAILED:
                Log.e(TAG, "CHITVIDEO_LEAVE_CONFERENCE_FAILED");
                break;
            case ChitVideo.CHITVIDEO_INCOMING_CALL:
                Log.e(TAG, "CHITVIDEO_INCOMING_CALL RECEIVED ");
//                startActivity(new Intent(ConferenceActivity.this, IncomingCallActivity.class));
                break;
            case ChitVideo.CHITVIDEO_EVENT_JOINING:
                Toast.makeText(ConferenceActivity.this, "正在入会", Toast.LENGTH_SHORT).show();
                break;
            case ChitVideo.CHITVIDEO_DIRECT_CALL_RESPONSE:

                if (mDialog != null) {
                    mDialog.dismiss();
                }

                int response = Integer.parseInt(msgInfo);

                if (response == ChitVideo.CHITVIDEO_USER_NO_ANSWER) {
                    Toast.makeText(ConferenceActivity.this, "对方无应答", Toast.LENGTH_SHORT).show();
                } else if (response == ChitVideo.CHITVIDEO_USER_DECLINED) {
                    Toast.makeText(ConferenceActivity.this, "对方拒绝接听", Toast.LENGTH_SHORT).show();
                } else if (response == ChitVideo.CHITVIDEO_USER_HANGUP) {
                    Toast.makeText(ConferenceActivity.this, "对方已挂断", Toast.LENGTH_SHORT).show();
                } else if (response == ChitVideo.CHITVIDEO_USER_NOT_ONLINE) {
                    Toast.makeText(ConferenceActivity.this, "对方不在线", Toast.LENGTH_SHORT).show();
                } else if (response == ChitVideo.CHITVIDEO_USER_FAILED_DIRECT_CALL) {
                    Toast.makeText(ConferenceActivity.this, "直呼失败", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
    }

}

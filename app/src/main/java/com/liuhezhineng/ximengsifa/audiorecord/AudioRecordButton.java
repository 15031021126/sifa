package com.liuhezhineng.ximengsifa.audiorecord;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.constant.Constant;

import java.io.File;

public class AudioRecordButton extends android.support.v7.widget.AppCompatButton implements MediaRecordManager.AudioStateListener {

    private static final int DISTANCE_CANCEL = 50;
    private static final int STATE_NORMAL = 0;
    private static final int STATE_RECORD = 1;
    private static final int STATE_CANCEL = 2;

    /**
     * 录音是否被占用
     */
    private boolean isUsing;

    private int mCurSate = STATE_NORMAL;
    private boolean isRecording;
    private RecordDialogManager mDialogManager;

    private MediaRecordManager mAudioManager;
    private float mTime;
    private boolean mReady;
    private Runnable mGetVoiceLevelRunnable = new Runnable() {
        @Override
        public void run() {
            while (isRecording) {
                try {
                    Thread.sleep(100);
                    mTime += 0.1f;
                    mHandler.sendEmptyMessage(MSG_VOICE_CHANGED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public AudioRecordButton(Context context) {
        this(context, null);
    }

    public AudioRecordButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AudioRecordButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDialogManager = new RecordDialogManager(context);

        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.AUDIO_PATH;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        mAudioManager = MediaRecordManager.getInstance(dir);
        mAudioManager.setOnAudioStateListener(this);

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (isUsing()) {
                    ToastUtils.showLong("请先关闭正在使用的录音功能");
                    return true;
                }
                mReady = true;
                mAudioManager.prepareAudio();
                return true;
            }
        });
    }

    public interface OnAudioFinishRecordListener {
        void onFinish(float seconds, String filePath);
    }

    private OnAudioFinishRecordListener mListener;

    public void setOnAudioFinishRecordListener(OnAudioFinishRecordListener listener) {
        mListener = listener;
    }

    @Override
    public void wellPrepared() {
        mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);
    }

    private static final int MSG_AUDIO_PREPARED = 0X110;
    private static final int MSG_VOICE_CHANGED = 0X111;
    private static final int MSG_DIALOG_DISMISS = 0X112;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_AUDIO_PREPARED:
                    mDialogManager.showRecordingDialog();
                    isRecording = true;
                    new Thread(mGetVoiceLevelRunnable).start();
                    break;
                case MSG_VOICE_CHANGED:
                    mDialogManager.updateVoiceLevel(mAudioManager.getVoiceLevel(7));
                    break;
                case MSG_DIALOG_DISMISS:
                    mDialogManager.dismissDialog();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);
    }

    public boolean isUsing() {
        return isUsing;
    }

    public void setUsing(boolean using) {
        isUsing = using;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isUsing()) {
            ToastUtils.showLong("请先关闭正在使用的录音功能");
            return super.onTouchEvent(event);
        }
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isRecording = true;
                changeState(STATE_RECORD);
                break;
            case MotionEvent.ACTION_CANCEL:

            case MotionEvent.ACTION_UP:
                if (!mReady) {
                    reset();
                    return super.onTouchEvent(event);
                }
                if (!isRecording || mTime < 0.6f) {
                    mDialogManager.tooShort();
                    mAudioManager.wantToCancel();
                    mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DISMISS, 1300);
                } else if (mCurSate == STATE_RECORD) {
                    mDialogManager.dismissDialog();
                    mAudioManager.release();
                    if (mListener != null) {
                        mListener.onFinish(mTime, mAudioManager.getCurrentFilePath());
                    }
                } else if (mCurSate == STATE_CANCEL) {
                    mDialogManager.dismissDialog();
                    mAudioManager.wantToCancel();
                }
                reset();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isRecording) {
                    if (wantToCancel(x, y)) {
                        changeState(STATE_CANCEL);
                    } else {
                        changeState(STATE_RECORD);
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void reset() {
        mReady = false;
        isRecording = false;
        mTime = 0;
        changeState(STATE_NORMAL);
    }

    private boolean wantToCancel(int x, int y) {
        if (x < 0 || x > getWidth()) {
            return true;
        }
        return y < -DISTANCE_CANCEL || y > getHeight() + DISTANCE_CANCEL;
    }

    private void changeState(int state) {
        if (mCurSate != state) {
            mCurSate = state;
            switch (state) {
                case STATE_NORMAL:
                    setBackgroundResource(R.drawable.btn_recorder_normal);
                    setText(R.string.str_record_normal);
                    break;
                case STATE_CANCEL:
                    setBackgroundResource(R.drawable.btn_recording);
                    setText(R.string.str_record_want_cancel);
                    mDialogManager.wantToCancel();
                    break;
                case STATE_RECORD:
                    setBackgroundResource(R.drawable.btn_recording);
                    setText(R.string.str_record_send);
                    if (isRecording) {
                        mDialogManager.recording();
                    }
                    break;
                default:
                    break;
            }
        }
    }

}

package com.liuhezhineng.ximengsifa.audiorecord;

import android.media.MediaRecorder;

import com.liuhezhineng.ximengsifa.utils.UserHelper;

import java.io.File;
import java.io.IOException;

public class MediaRecordManager {

    private MediaRecorder mMediaRecorder;
    private String mDir;
    private String mCurrentFilePath;

    private static MediaRecordManager mInstance;

    private boolean isPrepared;

    private MediaRecordManager(String dir) {
        mDir = dir;
    }

    String getCurrentFilePath() {
        return mCurrentFilePath;
    }

    public interface AudioStateListener {
        void wellPrepared();
    }

    private AudioStateListener mListener;

    void setOnAudioStateListener(AudioStateListener listener) {
        mListener = listener;
    }

    public static MediaRecordManager getInstance(String dir) {

        if (mInstance == null) {
            synchronized (MediaRecordManager.class) {
                if (mInstance == null) {
                    mInstance = new MediaRecordManager(dir);
                }
            }
        }
        return mInstance;
    }

    void prepareAudio() {
        try {
            isPrepared = false;
            File dir = new File(mDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = UserHelper.getUser().getId() + System.currentTimeMillis() + ".mp3";
            File file = new File(dir, fileName);

            mCurrentFilePath = file.getAbsolutePath();
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setOutputFile(file.getAbsolutePath());
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mMediaRecorder.setAudioChannels(1);
            mMediaRecorder.setAudioSamplingRate(8000);
            mMediaRecorder.setAudioEncodingBitRate(64);
            mMediaRecorder.prepare();
            mMediaRecorder.start();
            isPrepared = true;
            if (mListener != null) {
                mListener.wellPrepared();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            //java.lang.IllegalStateException
            // at android.media.MediaRecorder.start(Native Method)
            // 这里如果先使用了云之声调用了录音，再华为、锤子手机上为报错，小米、魅族没事。
            // 应该是重复启动的错误。 ❌
            e.printStackTrace();
            release();
            prepareAudio();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int getVoiceLevel(int maxLevel) {
        if (isPrepared) {
            try {
                return maxLevel * mMediaRecorder.getMaxAmplitude() / 32768 + 1;
            } catch (Exception e) {
            }
        }
        return 1;
    }

    public void release() {
        mMediaRecorder.stop();
        mMediaRecorder.release();
        mMediaRecorder = null;
    }

    void wantToCancel() {
        release();
        if (mCurrentFilePath != null) {
            File file = new File(mCurrentFilePath);
            file.delete();
            mCurrentFilePath = null;
        }
    }
}

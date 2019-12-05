package com.liuhezhineng.ximengsifa.audiorecord;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;

import java.io.IOException;

public class MediaPlayerManager {

    private static MediaPlayer mMediaPlayer;

    public static void playSound(String filePath, MediaPlayer.OnCompletionListener listener) {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mMediaPlayer.reset();
                    return false;
                }
            });
        } else {
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
            mMediaPlayer.setOnCompletionListener(listener);
            Log.i("qingfeng", "playSound: " + filePath);
            mMediaPlayer.setDataSource(filePath);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                    mMediaPlayer.setVolume(1.0f, 1.0f);
                }
            });
        } catch (IOException e) {
            ToastUtils.showLong("播放出现错误，找不到语音文件");
            listener.onCompletion(mMediaPlayer);
            mMediaPlayer.stop();
            release();
            e.printStackTrace();
        }

    }

    public static void pause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    public static void resume() {
        if (mMediaPlayer != null && mMediaPlayer.isLooping()) {
            mMediaPlayer.start();
        }
    }

    public static void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    //开始播放
    public static void playRing(final Activity activity) {
        try {
            Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);//用于获取手机默认铃声的Uri
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(activity, alert);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);//告诉mediaPlayer播放的是铃声流
            mMediaPlayer.setLooping(true);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //停止播放
    public static void stopRing() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                release();
            }
        }
    }
}

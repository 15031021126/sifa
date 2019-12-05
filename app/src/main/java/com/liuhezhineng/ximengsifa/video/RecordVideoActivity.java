package com.liuhezhineng.ximengsifa.video;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Description:
 * 使用MediaRecorder录制视频
 *
 * @author jph
 * Date:2014.08.14
 * <br/>
 */
public class RecordVideoActivity extends Activity implements OnClickListener {

    public static final int VIDEO_CODE = 0x00;

    // 程序中的两个按钮
    ImageButton record;
    // 系统的视频文件
    File videoFile;
    MediaRecorder mRecorder;
    // 显示视频预览的SurfaceView
    SurfaceView sView;
    // 记录是否正在进行录制
    private boolean isRecording = false;
    private Camera mCamera;
    private TextView mTvVideoTime;
    private int mTime;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RecordVideoActivity.class);
        ((Activity) context).startActivityForResult(intent, RecordVideoActivity.VIDEO_CODE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉标题栏 ,必须放在setContentView之前
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_record_video);
        // 设置横屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 设置全屏   
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 选择支持半透明模式,在有surfaceview的activity中使用。
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        initView();
                    }
                })
                .start();
    }

    private void initView() {
        mTvVideoTime = findViewById(R.id.tv_video_time);
        // 获取程序界面中的两个按钮
        record = findViewById(R.id.record);
        // 为两个按钮的单击事件绑定监听器
        record.setOnClickListener(this);
        // 获取程序界面中的SurfaceView
        sView = this.findViewById(R.id.sView);
        // 设置分辨率
        sView.getHolder().setFixedSize(1280, 720);
        // 设置该组件让屏幕不会自动关闭
        sView.getHolder().setKeepScreenOn(true);

        int cameraIndex = FindBackCamera();//网上参考的一个函数，用来获取后置摄像头的info
        mCamera = Camera.open(cameraIndex);
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(sView.getHolder()); // 将SurfaceHolder给camera
        } catch (IOException e) {
            e.printStackTrace();
        }
        Camera.Parameters parameters = mCamera.getParameters();
        // 自动对焦
        List<String> focusModes = parameters.getSupportedFocusModes();
        if (focusModes != null) {
            for (String mode : focusModes) {
                mode.contains("continuous-video");
                parameters.setFocusMode("continuous-video");
            }
        }
        mCamera.setParameters(parameters);
        mCamera.startPreview();
        mCamera.unlock();
    }

    @Override
    public void onClick(View source) {
        switch (source.getId()) {
            // 单击录制按钮
            case R.id.record:
                if (isRecording) {
                    finishRecorder();

                    setResult(RESULT_OK);
                    finish();
                } else {
                    if (!Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
                        Toast.makeText(RecordVideoActivity.this, "SD卡不存在，请插入SD卡！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        String dir = Constant.Video.FILE_PATH;
                        File file = new File(dir);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        // 创建保存录制视频的视频文件
                        videoFile = new File(dir, Constant.Video.FILE_NAME);
                        // 创建MediaPlayer对象
                        if (mRecorder == null) {
                            mRecorder = new MediaRecorder();
                        } else {
                            mRecorder.reset();
                        }
                        mRecorder.setCamera(mCamera);
                        mRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {
                            @Override
                            public void onError(MediaRecorder mr, int what, int extra) {
                                Log.i("qingfeng", "onError: " + mr);
                                Log.i("qingfeng", "onError: " + what);
                                Log.i("qingfeng", "onError: " + extra);
                            }
                        });
                        // 设置从麦克风采集声音(或来自录像机的声音AudioSource.CAMCORDER)
                        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        // 设置从摄像头采集图像
                        mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                        // 设置视频文件的输出格式
                        // 必须在设置声音编码格式、图像编码格式之前设置
                        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                        // 设置声音编码的格式
                        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        // 设置图像编码的格式
                        mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
                        mRecorder.setVideoSize(640, 480);
                        // 每秒 4 帧
                        mRecorder.setVideoFrameRate(30);
                        mRecorder.setVideoEncodingBitRate(3 * 1024 * 1024);
                        // 指定使用SurfaceView来预览视频
                        mRecorder.setPreviewDisplay(sView.getHolder().getSurface());  //①
                        mRecorder.setOrientationHint(90);
                        //设置记录会话的最大持续时间（毫秒）
                        mRecorder.setMaxDuration(30 * 1000);
                        mRecorder.setOutputFile(videoFile.getAbsolutePath());
                        mRecorder.prepare();
                        mRecorder.start();
                        isRecording = true;
                        record.setImageResource(R.drawable.btn_video_rectangle);
                        new Thread() {
                            @Override
                            public void run() {
                                while (isRecording) {
                                    try {
                                        Thread.sleep(1000);
                                        mTime += 1;
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                int hour_minute = mTime / 600;
                                                int minute = (mTime - (hour_minute * 600)) / 60;
                                                int hour_second = (mTime - (hour_minute * 600) - (minute * 60)) / 10;
                                                int minute_second = mTime - (hour_minute * 600) - (minute * 60) - (hour_second * 10);
                                                String time = hour_minute + "" + minute + ":" + hour_second + "" + minute_second;
                                                mTvVideoTime.setText(String.valueOf(time));
                                            }
                                        });
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void finishRecorder() {
        // 停止录制 释放资源
        if (null != mRecorder) {
            mRecorder.setOnErrorListener(null);
            try {
                mRecorder.stop();
            } catch (RuntimeException stopException) {
                //handle cleanup here
                Log.i("qingfeng", "onClick: " + stopException.getMessage());
            }
            mRecorder.reset();
            mRecorder.release();
            mRecorder = null;
        }
        if (null != mCamera) {
            mCamera.stopPreview();
            mCamera.lock();
            mCamera.release();
            mCamera = null;
        }
        isRecording = false;
        record.setImageResource(R.drawable.btn_video_circle);
    }

    @SuppressLint("NewApi")
    private int FindFrontCamera() {
        int cameraCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras(); // get cameras number

        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo); // get camerainfo
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                // 代表摄像头的方位，目前有定义值两个分别为CAMERA_FACING_FRONT前置和CAMERA_FACING_BACK后置
                return camIdx;
            }
        }
        return -1;
    }

    //判断后置摄像头是否存在
    @SuppressLint("NewApi")
    private int FindBackCamera() {
        int cameraCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras(); // get cameras number

        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo); // get camerainfo
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                // 代表摄像头的方位，目前有定义值两个分别为CAMERA_FACING_FRONT前置和CAMERA_FACING_BACK后置
                return camIdx;
            }
        }
        return -1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != mRecorder && isRecording) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mRecorder.resume();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mRecorder != null && isRecording) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mRecorder.pause();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRecording) {
            finishRecorder();
        }
    }
}
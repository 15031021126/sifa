package com.liuhezhineng.ximengsifa.utils;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.provider.FileProviderUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;

/**
 * @author AIqinfeng
 * @description 下载服务
 */
public class DownloadService extends IntentService {

    public static final String EXTRA_DOWNLOAD_URL = "com.liuhezhineng.ximengsifa";
    public static final int NOTIFICATION_ID = 0;
    NotificationManager mManager;
    NotificationCompat.Builder mBuilder;
    private File mApkFile;
    private String mApkUrl;

    public DownloadService() {
        super(DownloadService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mApkUrl = intent.getStringExtra(EXTRA_DOWNLOAD_URL);

        AndPermission.with(this)
                .runtime()
                .permission(Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE)
                .onGranted(data -> downloadNewApk())
                .onDenied(data -> ToastUtils.showLong("没有存储权限，无法下载新版本文件"))
                .start();
    }

    private void downloadNewApk() {
        OkGo.<File>get(mApkUrl)
                .execute(new FileCallback() {
                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        super.onStart(request);
                        initNotification();
                        ToastUtils.showLong("下载中,可在通知栏中查看进度");
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        ToastUtils.showLong("下载出现错误");
                        mManager.cancel(NOTIFICATION_ID);
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                        updateNotification(progress.fraction);
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        mApkFile = response.body();
                        if (mApkFile != null) {
                            ToastUtils.showLong("新版本下载成功，如未自动安装请点击通知栏安装");
                            downloadFinish();
                        }
                    }
                });
    }

    private void initNotification() {
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String groupId = "notification_group_apk_install";
            NotificationChannelGroup group = new NotificationChannelGroup(groupId, "应用安装");
            mManager.createNotificationChannelGroup(group);

            String channelId = "notification_channel_apk_install";
            NotificationChannel apkChannel = new NotificationChannel(
                    channelId,
                    "应用安装",
                    NotificationManager.IMPORTANCE_MIN);
            apkChannel.setDescription("应用更新安装提示");
            apkChannel.setSound(null, null);
            apkChannel.enableVibration(false);
            apkChannel.setVibrationPattern(new long[]{0});
            apkChannel.setGroup(groupId);
            mManager.createNotificationChannel(apkChannel);

            mBuilder = new NotificationCompat.Builder(this, channelId);
        } else {
            mBuilder = new NotificationCompat.Builder(this);
        }
        mBuilder.setSmallIcon(R.drawable.app_icon)
                .setTicker("开始下载")
                .setContentTitle(getString(R.string.app_name))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setProgress(100, 0, false)
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis());
        mManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    private void updateNotification(float progress) {
        mBuilder.setProgress(100, (int) (progress * 100), false)
                .setContentText("下载进度：" + (int) (progress * 100) + "%");
        mManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    private void downloadFinish() {
        Intent installIntent = new Intent(Intent.ACTION_VIEW);
        installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String type = "application/vnd.android.package-archive";
        FileProviderUtils.setIntentDataAndType(this, installIntent, type, mApkFile, true);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                DownloadService.this,
                0,
                installIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentText("下载完成，请点击安装")
                .setTicker("下载完成")
                .setProgress(0, 0, false)
                .setContentIntent(pendingIntent);
        mManager.notify(NOTIFICATION_ID, mBuilder.build());

        AndPermission.with(this)
                .install()
                .file(mApkFile)
                .onDenied(data -> ToastUtils.showLong("为了安装新版本，请开启安装未知来源权限"))
                .start();
    }
}

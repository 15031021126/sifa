package com.liuhezhineng.ximengsifa.adapter;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AnnexAdapter.AnnexViewHolder;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @date 2018/6/11
 * @description 附件适配器 下载，删除（未做），上传
 * 可以优化打造成一个附件系统 自定义控件
 * 下载动画图标，圆环进度
 * 暂停 继续下载 取消下载 wifi 网络提示（文件超过20M，当前为移动流量，是否继续下载）
 * 通知：
 * 下载通知进度
 * 下载完成后 手动/自动（非隐私）打开相关文件
 */

public class AnnexAdapter extends Adapter<AnnexViewHolder> implements android.view.View.OnClickListener {

    private Context mContext;
    private ArrayList<String> mList;
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private LayoutInflater mLayoutInflater;

    public AnnexAdapter(Context context, ArrayList<String> list) {
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(list);
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public ArrayList<String> getData() {
        return mList;
    }

    public void setData(ArrayList<String> list) {
        mList = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public AnnexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.annex_item, parent, false);
        return new AnnexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnexViewHolder holder, final int position) {
        final String filePath = mList.get(position);
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
        try {
            fileName = URLDecoder.decode(fileName, "utf-8");
            String showName = (position + 1) + ". " + fileName;
            holder.mTvCaseFile.setText(showName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        holder.mTvDownloadFile.setTag(filePath);
        holder.mTvDownloadFile.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return null == mList ? 0 : mList.size();
    }

    public void addData(String annexList) {
        if (mList != null) {
            mList.add(annexList);
            notifyDataSetChanged();
        }
    }
    public void addLegData(String annexList) {
        if (mList != null) {
            mList.clear();
            mList.add(annexList);
            notifyDataSetChanged();
        }
    }

    public void initData(List<String> strings) {
        if (mList != null) {
            mList.clear();
            mList.addAll(strings);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        AndPermission.with(mContext)
                .runtime()
                .permission(Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE)
                .onGranted(data -> okDownloadFile((String) v.getTag()))
                .onDenied(data -> ToastUtils.showLong("下载图片需要写内存权限，请到设置中打开"))
                .start();
    }

    private void okDownloadFile(String filePath) {
        OkGo.<File>get(NetConstant.FILE_URL + filePath)
                .execute(new FileCallback() {
                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        super.onStart(request);
                        initNotification(EncodeUtils
                                .urlDecode(filePath.substring(filePath.lastIndexOf("/") + 1)));
                        ToastUtils.showLong("下载中...");
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        File file = response.body();
                        if (file != null) {
                            ToastUtils.showLong(
                                    file.getName() + "下载成功,可在通知栏点击打开");
                            downloadFinished(file);
                        }
                    }
                });
    }

    private void initNotification(String fileName) {
        mNotifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // unique
            String groupId = "notification_group_annex";
            NotificationChannelGroup annexGroup = new NotificationChannelGroup(groupId, "附件");
            mNotifyManager.createNotificationChannelGroup(annexGroup);

            String channelId = "notification_channel_annex";
            NotificationChannel annexChannel = new NotificationChannel(channelId,
                    "下载",
                    NotificationManager.IMPORTANCE_HIGH);
            annexChannel.setDescription("附件下载");
            annexChannel.setGroup(groupId);
            mNotifyManager.createNotificationChannel(annexChannel);
            mBuilder = new NotificationCompat.Builder(mContext, channelId);
        } else {
            mBuilder = new NotificationCompat.Builder(mContext);
        }
        mBuilder.setContentTitle(fileName)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.app_icon))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)
                .setContentText("正在下载")
                .setSmallIcon(R.drawable.app_icon)
                .setTicker("开始下载");
        mNotifyManager.notify(0, mBuilder.build());
    }

    private void downloadFinished(File file) {
        Intent intent = getFileIntent(file);
        PendingIntent pendingintent = PendingIntent.getActivity(mContext, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        mBuilder.setTicker("下载完成")
                .setContentIntent(pendingintent)
                .setAutoCancel(true)
                .setContentText("下载完成，点击打开文件");
        mNotifyManager.notify(0, mBuilder.build());
    }

    public Intent getFileIntent(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String type = getMIMEType(file);
        Uri fileUri = AndPermission.getFileUri(mContext, file);
        intent.setDataAndType(fileUri, type);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        return intent;
    }

    private String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();
        if ("pdf".equals(end)) {
            type = "application/pdf";
        } else if ("m4a".equals(end) || "mp3".equals(end) || "mid".equals(end) ||
                "xmf".equals(end) || "ogg".equals(end) || "wav".equals(end)) {
            type = "audio/*";
        } else if ("3gp".equals(end) || "mp4".equals(end)) {
            type = "video/*";
        } else if ("jpg".equals(end) || "gif".equals(end) || "png".equals(end) ||
                "jpeg".equals(end) || "bmp".equals(end)) {
            type = "image/*";
        } else if ("apk".equals(end)) {
            type = "application/vnd.android.package-archive";
        } else if ("pptx".equals(end) || "ppt".equals(end)) {
            type = "application/vnd.ms-powerpoint";
        } else if ("docx".equals(end) || "doc".equals(end)) {
            type = "application/vnd.ms-word";
        } else if ("xlsx".equals(end) || "xls".equals(end)) {
            type = "application/vnd.ms-excel";
        } else if ("txt".equals(end)) {
            type = "text/plain";
        } else {
            type = "*/*";
        }
        return type;
    }

    static class AnnexViewHolder extends ViewHolder {

        @BindView(R.id.tv_case_file)
        TextView mTvCaseFile;
        @BindView(R.id.tv_download_file)
        TextView mTvDownloadFile;
        @BindView(R.id.iv_annex_del)
        ImageView mIvAnnexDel;

        AnnexViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

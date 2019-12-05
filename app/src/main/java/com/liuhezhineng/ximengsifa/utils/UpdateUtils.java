package com.liuhezhineng.ximengsifa.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.BuildConfig;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.AppUpdateBean;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.provider.FileProviderUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;

import java.io.File;

/**
 * @author AIqinfeng
 * @date 2018/6/27
 */

public class UpdateUtils {

	private Context mContext;
	private AppUpdateBean appUpdateBean;

	private UpdateUtils(Context context) {
		mContext = context;
	}

	public static void update(Context context, boolean isShowToast) {
		new UpdateUtils(context).checkUpdateVersion(isShowToast);
	}

	private void checkUpdateVersion(final boolean isShowToast) {
		if (isShowToast) {
			ToastUtils.showLong("检测更新中...");
		}
		OkGo.<BaseBean<AppUpdateBean>>post(NetConstant.APP_UPDATE)
			.execute(new JsonCallback<BaseBean<AppUpdateBean>>(mContext) {
				@Override
				public void onSuccess(Response<BaseBean<AppUpdateBean>> response) {
					appUpdateBean = response.body().getBody();
					if (appUpdateBean != null
						&& (compareVersion(BuildConfig.VERSION_NAME, appUpdateBean.getVersion())
						< 0)) {
						ToastUtils.cancel();
						showUpdateDialog();
					} else {
						if (isShowToast) {
							ToastUtils.showLong("已经是最新版本了");
						}
					}
				}
			});
	}

	/**
	 * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
	 *
	 * @param currentVersion current apk version
	 * @param serviceVersion service apk version
	 * @return -1 : update
	 */
	private static int compareVersion(String currentVersion, String serviceVersion) {
		if (currentVersion == null || serviceVersion == null) {
			return 0;
		}
        //注意此处为正则匹配，不能用"."；
        String[] versionArray1 = currentVersion.split("\\.");
		String[] versionArray2 = serviceVersion.split("\\.");
		int idx = 0;
        //取最小长度值
        int minLength = Math.min(versionArray1.length, versionArray2.length);
		int diff = 0;
        while (idx < minLength
                //先比较长度
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0
                //再比较字符
			&& (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {
            ++idx;
		}
		//如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
		diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
		return diff;
	}

	private void showUpdateDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle(R.string.app_name)
			.setIcon(R.drawable.app_icon)
			.setMessage(
				"新版本：" + appUpdateBean.getVersion() + "\n"
					+ ("更新内容：" + "\n" + appUpdateBean.getDescription()))
			.setCancelable(true)
			.setPositiveButton("更新", (dialog, which) -> {
                Intent intent = new Intent(mContext, DownloadService.class);
                intent.putExtra(DownloadService.EXTRA_DOWNLOAD_URL,
                    NetConstant.FILE_URL + appUpdateBean.getUrl());
                mContext.startService(intent);
                dialog.dismiss();
            })
			.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
		if (((Activity) mContext).getWindow().isActive()) {
			builder.create().show();
		}
	}
}

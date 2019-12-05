package com.liuhezhineng.ximengsifa.utils;

import android.content.Context;
import android.os.Environment;
import com.blankj.utilcode.util.ToastUtils;
import java.io.File;

/**
 * @author AIqinfeng
 * @date 2018/7/5
 */

public class FileUtils {

	public static String getFilePath(Context context, String dirType) {
		String directoryPath = "";
		//判断外部存储是否可用
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			File filesDir = context.getExternalFilesDir(dirType);
			if (filesDir != null) {
				directoryPath = filesDir.getAbsolutePath();
			}
		} else {
			//没外部存储就使用内部存储
			directoryPath = context.getFilesDir() + File.separator + dirType;
		}
		File file = new File(directoryPath);
		//判断文件目录是否存在
		if (!file.exists()) {
			boolean mkdirsSuccess = file.mkdirs();
			if (!mkdirsSuccess) {
				ToastUtils.showLong("创建文件目录失败");
			}
		}
		return directoryPath;
	}

}

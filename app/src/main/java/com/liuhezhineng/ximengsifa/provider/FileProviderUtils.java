package com.liuhezhineng.ximengsifa.provider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.yanzhenjie.permission.AndPermission;

import java.io.File;

public class FileProviderUtils {

    public static Uri getUriForFile(Context context, File file) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context,
                    context.getPackageName() + ".fileprovider",
                    file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }


    public static void setIntentDataAndType (Context context,
                                             Intent intent,
                                             String type,
                                             File file,
                                             boolean writeAble) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            AndPermission.getFileUri(context, file);
            intent.setDataAndType(getUriForFile(context, file), type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
    }
}

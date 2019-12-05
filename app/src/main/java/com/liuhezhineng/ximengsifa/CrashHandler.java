package com.liuhezhineng.ximengsifa;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * <pre>
 *     author : qingfeng
 *     e-mail : 1913518036@qq.com
 *     time   : 2019/01/11
 *     desc   : 全局异常捕获
 *     version: 1.0
 * </pre>
 *
 * @author iqing
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler mInstance;
    private Context mContext;
    /**
     * 用于存储设备信息
     */
    private Map<String, String> mInfo = new HashMap<>();
    /**
     * 格式化时间，作为Log文件名
     */
    private java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss", Locale.CHINESE);

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (mInstance == null) {
            synchronized (CrashHandler.class) {
                if (mInstance == null) {
                    mInstance = new CrashHandler();
                }
            }
        }
        return mInstance;
    }

    public void register(Context context) {
        mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, final Throwable e) {
        saveErrorInfo2SD(e);
        /// 为什么要使用线程池而不线使创建线程？
        // 线程资源必须通过线程池提供，不允许在应用中自行显式创建线程。
        // 说明：使用线程池的好处是减少在创建和销毁线程上所花的时间以及系统资源的开销，解决资源不足的问题。
        // 如果不使用线程池，有可能造成系统创建大量同类线程而导致消耗完内存或者“过度切换”的问题。

        // Use Looper.myLooper() if looper is not specified.
        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    private void saveErrorInfo2SD(Throwable e) {
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            // 获取版本信息
            if (info != null) {
                String versionName = TextUtils.isEmpty(info.versionName) ? "未设置版本名称" : info.versionName;
                String versionCode = info.versionCode + "";
                mInfo.put("versionName", versionName);
                mInfo.put("versionCode", versionCode);
            }
            // 获取设备信息
            Field[] fields = Build.class.getFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    mInfo.put(field.getName(), field.get(null).toString());
                }
            }
            // 存储信息到 sd 卡指定目录
            saveErrorInfo(e);
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    /**
     * 存储信息到 sd 卡指定目录
     *
     * @param e {@link Throwable}
     */
    private void saveErrorInfo(Throwable e) {
        //StringBuffer stringBuffer' may be declared as 'StringBuilder'
        //Reports any variables declared as java.lang.StringBuffer which may be more efficiently declared as java.lang.StringBuilder.
        // java.lang.StringBuilder is a non-thread-safe replacement for java.lang.StringBuffer, available in Java 5 and newer.
//        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : mInfo.entrySet()) {
            String keyName = entry.getKey();
            String value = entry.getValue();
            stringBuilder.append(keyName)
                    .append("=")
                    .append(value)
                    .append("\n");
        }
        stringBuilder.append("\n-----Crash Log Begin-----\n");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(writer);
            cause = e.getCause();
        }
        writer.close();
        String string = stringWriter.toString();
        stringBuilder.append(string);
        stringBuilder.append("\n-----Crash Log End-----");
        String format = dateFormat.format(new Date());
        String fileName = "crash-" + format + ".log";

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // /data/data/[packageName]/files/crash
            String path = mContext.getFilesDir() + File.separator + "crash";
            File dir = new File(path);
            boolean mkdirs = false;
            if (!dir.exists()) {
                mkdirs = dir.mkdirs();
            }
            if (!mkdirs) {
                return;
            }
            FileOutputStream fou = null;
            try {
                fou = new FileOutputStream(new File(path, fileName));
                fou.write(stringBuilder.toString().getBytes());
                fou.flush();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    if (fou != null) {
                        fou.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

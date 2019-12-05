package com.liuhezhineng.ximengsifa.vidyo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharePreferenceUtil {

    private static final String KEY_CHIT_ENABLE_PROXY = "ENABLE_CHIT_PROXY";

    /**
     * Get SharedPreference
     *
     * @param context environment
     * @return share preference.
     */
    private static SharedPreferences getSps(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean getEnableProxyServer(Context context) {
        SharedPreferences sps = getSps(context);
        return sps.getBoolean(KEY_CHIT_ENABLE_PROXY, false);
    }

    public static void setEnableProxyServer(Context context, boolean flag) {
        SharedPreferences sps = getSps(context);
        sps.edit().putBoolean(KEY_CHIT_ENABLE_PROXY, flag).apply();
    }

}

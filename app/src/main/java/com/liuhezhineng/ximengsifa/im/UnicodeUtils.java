package com.liuhezhineng.ximengsifa.im;

import android.util.Log;

/**
 * <pre>
 *     author : qingfeng
 *     e-mail : 1913518036@qq.com
 *     time   : 2018/12/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class UnicodeUtils {

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {
        Log.i("qingfeng", "string2Unicode: " + string);
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
//            unicode.append("\\u" + Integer.toHexString(c));
            unicode.append("_u" + Integer.toHexString(c));
        }
        Log.i("qingfeng", "string2Unicode: " + unicode.toString());
        return unicode.toString();
    }

    public static String chineseToUnicode(String str) {
        char[] arChar = str.toCharArray();
        int iValue = 0;
        StringBuffer uStr = new StringBuffer();
        for (int i = 0; i < arChar.length; i++) {
            iValue = (int) str.charAt(i);
            uStr.append("&#" + iValue + ";");
        }
        Log.i("qingfeng", "chineseToUnicode: " + uStr.toString());
        String s = uStr.toString().replace("\\", "_");
        Log.i("qingfeng", "chineseToUnicode: " + s);
        return uStr.toString();
    }

    public static String unicodeToChinese(String str) {
        Log.i("qingfeng", "chineseToUnicode: " + str.toString());
        String replaceStr = str.replace("_", "\\");
        String[] strings = replaceStr.split(";");
        StringBuffer aStr = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            String s = strings[i].replace("&#", "");
            aStr.append((char) Integer.parseInt(s));
        }
        Log.i("qingfeng", "chineseToUnicode: " + aStr.toString());
        return aStr.toString();
    }
}

package com.liuhezhineng.ximengsifa.utils;

import java.security.MessageDigest;

/**
 * <pre>
 *     author : qingfeng
 *     e-mail : 1913518036@qq.com
 *     time   : 2018/12/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MD5Utils {

    public static String getMD5(String message) {
        String md5Result = "";
        try {
            //1.创建一个提供信息摘要算法的对象，初始化为MD5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            //2.将消息变为byte数组
            byte[] input = message.getBytes();
            //3.计算后获得字节数组，128位长度的MD5加密
            byte[] buff = md.digest(input);
            //4.把数组每一个字节（一个字节占8位）换成16进制的md5字符串
            md5Result = bytesHex(buff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5Result;
    }

    public static String bytesHex(byte[] bytes) {
        StringBuilder md5Result = new StringBuilder();
        //把数组每一字节换成换成16进制连成md5字符串
        int digital;
        for (byte aByte : bytes) {
            digital = aByte;
            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5Result.append("0");
            }
            md5Result.append(Integer.toHexString(digital));
        }
        return md5Result.toString();
    }
}

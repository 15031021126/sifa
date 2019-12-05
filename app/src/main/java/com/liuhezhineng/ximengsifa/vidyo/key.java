package com.liuhezhineng.ximengsifa.vidyo;

/**
 * Created by WXS on 2016/8/1.
 */
public class key {

    //{"status":0,"msg":"SUCCESS","body":{"roomID":405,"roomName":"12348视频咨询","extension":"4751224152032",
    //      "roomURL":"http://portal.xlgl12348.gov.cn/flex.html?roomdirect.html&key=bKEa3edwav","roomKey":"bKEa3edwav"}}

    public static final String ROOM_ID = "405";

    public static String roomId = "";

    public static String roomUrl = "";
    public static String roomKey = "";
    public static String pin = "";
    public static String displayName = "";

    public static String portal = "";
    public static String userName = "";
    public static String passWord = "";

    public static String content() {
        return "roomUrl: " + roomUrl + ", " +
                "roomKey：" + roomKey + ", " +
                "pin: " + pin + ", " +
                "displayName: " + displayName;
    }
}

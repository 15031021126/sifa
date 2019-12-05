package com.liuhezhineng.ximengsifa.bean.vidyo;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * <pre>
 *     author : qingfeng
 *     e-mail : 1913518036@qq.com
 *     time   : 2018/12/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class Contacts implements Base {

    private String displayName;
    private String roomStatus;
    private String extension;
    private String memberStatus;

    public String getMemberStatus() {
        return memberStatus == null ? "" : memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getDisplayName() {

        return displayName == null ? "" : displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRoomStatus() {
        return roomStatus == null ? "" : roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getExtension() {
        return extension == null ? "" : extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}

package com.liuhezhineng.ximengsifa.bean.vidyo;

import com.liuhezhineng.ximengsifa.bean.base.Base;

public class RoomBean implements Base {

    private String roomID;
    private String roomName;
    private String extension;
    private String roomURL;
    private String roomKey;

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getRoomURL() {
        return roomURL;
    }

    public void setRoomURL(String roomURL) {
        this.roomURL = roomURL;
    }

    public String getRoomKey() {
        return roomKey;
    }

    public void setRoomKey(String roomKey) {
        this.roomKey = roomKey;
    }

    @Override
    public String toString() {
        return "RoomBean{" +
                "roomID='" + roomID + '\'' +
                ", roomName='" + roomName + '\'' +
                ", extension='" + extension + '\'' +
                ", roomURL='" + roomURL + '\'' +
                ", roomKey='" + roomKey + '\'' +
                '}';
    }
}

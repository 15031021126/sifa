package com.liuhezhineng.ximengsifa.audiorecord;

import com.google.gson.annotations.SerializedName;
import com.liuhezhineng.ximengsifa.bean.base.Base;

public class RecordBean implements Base {

    private float seconds;
    @SerializedName("fileName")
    private String filePath;

    public RecordBean(float seconds, String filePath) {
        this.seconds = seconds;
        this.filePath = filePath;
    }

    public float getSeconds() {
        return seconds;
    }

    public void setSeconds(float seconds) {
        this.seconds = seconds;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

package com.liuhezhineng.ximengsifa.bean.video;

import com.google.gson.annotations.SerializedName;
import com.liuhezhineng.ximengsifa.bean.base.Base;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeHistoryBean implements Base {

    @SerializedName("平衡")
    private List<ResultBean> balance;
    @SerializedName("抑郁")
    private List<ResultBean> depressed;
    @SerializedName("活力")
    private List<ResultBean> energy;
    @SerializedName("压力")
    private List<ResultBean> presure;
    @SerializedName("自我调节")
    private List<ResultBean> selfControl;
    @SerializedName("可疑")
    private List<ResultBean> suspicious;
    @SerializedName("自信")
    private List<ResultBean> selfConfidence;
    @SerializedName("神经质")
    private List<ResultBean> nervousness;
    @SerializedName("焦虑")
    private List<ResultBean> anxiety;
    @SerializedName("攻击性")
    private List<ResultBean> aggressivity;

    public List<ResultBean> getBalance() {
        if (balance == null) {
            return new ArrayList<>();
        }
        return balance;
    }

    public void setBalance(List<ResultBean> balance) {
        this.balance = balance;
    }

    public List<ResultBean> getDepressed() {
        if (depressed == null) {
            return new ArrayList<>();
        }
        return depressed;
    }

    public void setDepressed(List<ResultBean> depressed) {
        this.depressed = depressed;
    }

    public List<ResultBean> getEnergy() {
        if (energy == null) {
            return new ArrayList<>();
        }
        return energy;
    }

    public void setEnergy(List<ResultBean> energy) {
        this.energy = energy;
    }

    public List<ResultBean> getPresure() {
        if (presure == null) {
            return new ArrayList<>();
        }
        return presure;
    }

    public void setPresure(List<ResultBean> presure) {
        this.presure = presure;
    }

    public List<ResultBean> getSelfControl() {
        if (selfControl == null) {
            return new ArrayList<>();
        }
        return selfControl;
    }

    public void setSelfControl(List<ResultBean> selfControl) {
        this.selfControl = selfControl;
    }

    public List<ResultBean> getSuspicious() {
        if (suspicious == null) {
            return new ArrayList<>();
        }
        return suspicious;
    }

    public void setSuspicious(List<ResultBean> suspicious) {
        this.suspicious = suspicious;
    }

    public List<ResultBean> getSelfConfidence() {
        if (selfConfidence == null) {
            return new ArrayList<>();
        }
        return selfConfidence;
    }

    public void setSelfConfidence(List<ResultBean> selfConfidence) {
        this.selfConfidence = selfConfidence;
    }

    public List<ResultBean> getNervousness() {
        if (nervousness == null) {
            return new ArrayList<>();
        }
        return nervousness;
    }

    public void setNervousness(List<ResultBean> nervousness) {
        this.nervousness = nervousness;
    }

    public List<ResultBean> getAnxiety() {
        if (anxiety == null) {
            return new ArrayList<>();
        }
        return anxiety;
    }

    public void setAnxiety(List<ResultBean> anxiety) {
        this.anxiety = anxiety;
    }

    public List<ResultBean> getAggressivity() {
        if (aggressivity == null) {
            return new ArrayList<>();
        }
        return aggressivity;
    }

    public void setAggressivity(List<ResultBean> aggressivity) {
        this.aggressivity = aggressivity;
    }
}

/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.video;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * Auto-generated: 2018-11-08 9:9:3
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ResultBean1 {

    private String idCard;
    private String analysisDate;
    private String errorRate;
    private String type;
    // id为该字段所在表格排序位置
    private String name;
    private String minValue;
    private String averageValue;
    private String maxValue;
    private String imageValue;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setAnalysisDate(String analysisDate) {
        this.analysisDate = analysisDate;
    }

    public String getAnalysisDate() {
        return analysisDate;
    }

    public void setErrorRate(String errorRate) {
        this.errorRate = errorRate;
    }

    public String getErrorRate() {
        return errorRate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setAverageValue(String averageValue) {
        this.averageValue = averageValue;
    }

    public String getAverageValue() {
        return averageValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setImageValue(String imageValue) {
        this.imageValue = imageValue;
    }

    public String getImageValue() {
        return imageValue;
    }

}
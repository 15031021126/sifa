/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.video;

import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;

/**
 * Auto-generated: 2018-11-06 9:31:19
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class RectificationPersonnelBean {

    private String name;
    private Office office;
    private String accusation;
    private String mainPenalty;
    private String mainPenaltyDesc;
    private String correctType;
    private String correctTypeDesc;
    private String responsibilityName;
    private String correctStatus;
    private String correctStatusDesc;
    private String correctLevel;
    private String idCard;
    private String sex;
    private String age;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Office getOffice() {
        if (office == null) {
            return new Office();
        }
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getAccusation() {
        return accusation == null ? "" : accusation;
    }

    public void setAccusation(String accusation) {
        this.accusation = accusation;
    }

    public String getMainPenalty() {
        return mainPenalty == null ? "" : mainPenalty;
    }

    public void setMainPenalty(String mainPenalty) {
        this.mainPenalty = mainPenalty;
    }

    public String getMainPenaltyDesc() {
        return mainPenaltyDesc == null ? "" : mainPenaltyDesc;
    }

    public void setMainPenaltyDesc(String mainPenaltyDesc) {
        this.mainPenaltyDesc = mainPenaltyDesc;
    }

    public String getCorrectType() {
        return correctType == null ? "" : correctType;
    }

    public void setCorrectType(String correctType) {
        this.correctType = correctType;
    }

    public String getCorrectTypeDesc() {
        return correctTypeDesc == null ? "" : correctTypeDesc;
    }

    public void setCorrectTypeDesc(String correctTypeDesc) {
        this.correctTypeDesc = correctTypeDesc;
    }

    public String getResponsibilityName() {
        return responsibilityName == null ? "" : responsibilityName;
    }

    public void setResponsibilityName(String responsibilityName) {
        this.responsibilityName = responsibilityName;
    }

    public String getCorrectStatus() {
        return correctStatus == null ? "" : correctStatus;
    }

    public void setCorrectStatus(String correctStatus) {
        this.correctStatus = correctStatus;
    }

    public String getCorrectStatusDesc() {
        return correctStatusDesc == null ? "" : correctStatusDesc;
    }

    public void setCorrectStatusDesc(String correctStatusDesc) {
        this.correctStatusDesc = correctStatusDesc;
    }

    public String getCorrectLevel() {
        return correctLevel == null ? "" : correctLevel;
    }

    public void setCorrectLevel(String correctLevel) {
        this.correctLevel = correctLevel;
    }

    public String getIdCard() {
        return idCard == null ? "" : idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex == null ? "" : sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age == null ? "" : age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
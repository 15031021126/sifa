/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.liuhezhineng.ximengsifa.bean.form;

import com.liuhezhineng.ximengsifa.bean.base.Base;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;

/**
 * 远程探视申请Entity
 *
 * @author 王召林
 * @version 2019-01-17
 */
public class VisitApply implements Base {

    private String id;

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String applyName;        // 申请人姓名
    private String applyPhone;        // 申请人手机号
    private String applySex;
    /**
     * 申请人性别
     */
    private String applySexDesc;
    private String applyNativePlace;// 申请人籍贯
    private String applyIdCard;        // 申请人身份证号
    private String relation;        // 探访人关系
    private String applyAddress;        // 现居地址
    private String applyWorkUnit;        // 工作单位
    private String agentName;        // 代理人姓名
    private String agentPhone;        // 代理人手机号
    private String visitorName;        // 探访人
    private String visitorSex;
    /**
     * 探访人性别
     */
    private String visitorSexDesc;
    private String visitorNativePlace;        // 探访人籍贯
    private String supervisionPlaceType;
    private String supervisionPlaceTypeDesc;
    private String supervisionPlace;        // 监管场所
    private String supervisionPlaceDesc;
    private String visitorIdCard;        // 谈访人身份证号
    private Office office;        // 探访审批司法局
    private String startTime;        // 探访开始时间
    private String endTime;        // 探访结束时间
    private String examineTime;        // 审核时间
    private String applyReason;        // 申请理由
    /**
     * 审核状态
     */
    private String state;
    private String stateDesc;

    private String rejectReson;        // 驳回理由

    private String createDate;
    private String files;

    public String getFiles() {
        return files == null ? "" : files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getSupervisionPlaceType() {
        return supervisionPlaceType == null ? "" : supervisionPlaceType;
    }

    public void setSupervisionPlaceType(String supervisionPlaceType) {
        this.supervisionPlaceType = supervisionPlaceType;
    }

    public String getSupervisionPlaceTypeDesc() {
        return supervisionPlaceTypeDesc == null ? "" : supervisionPlaceTypeDesc;
    }

    public void setSupervisionPlaceTypeDesc(String supervisionPlaceTypeDesc) {
        this.supervisionPlaceTypeDesc = supervisionPlaceTypeDesc;
    }

    public String getSupervisionPlaceDesc() {
        return supervisionPlaceDesc == null ? "" : supervisionPlaceDesc;
    }

    public void setSupervisionPlaceDesc(String supervisionPlaceDesc) {
        this.supervisionPlaceDesc = supervisionPlaceDesc;
    }

    public String getApplySexDesc() {
        return applySexDesc == null ? "" : applySexDesc;
    }

    public void setApplySexDesc(String applySexDesc) {
        this.applySexDesc = applySexDesc;
    }

    public String getVisitorSexDesc() {
        return visitorSexDesc == null ? "" : visitorSexDesc;
    }

    public void setVisitorSexDesc(String visitorSexDesc) {
        this.visitorSexDesc = visitorSexDesc;
    }

    public String getCreateDate() {
        return createDate == null ? "" : createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStateDesc() {
        return stateDesc == null ? "" : stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public String getApplyName() {
        return applyName == null ? "" : applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyPhone() {
        return applyPhone == null ? "" : applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }

    public String getApplySex() {
        return applySex == null ? "" : applySex;
    }

    public void setApplySex(String applySex) {
        this.applySex = applySex;
    }

    public String getApplyNativePlace() {
        return applyNativePlace == null ? "" : applyNativePlace;
    }

    public void setApplyNativePlace(String applyNativePlace) {
        this.applyNativePlace = applyNativePlace;
    }

    public String getApplyIdCard() {
        return applyIdCard == null ? "" : applyIdCard;
    }

    public void setApplyIdCard(String applyIdCard) {
        this.applyIdCard = applyIdCard;
    }

    public String getRelation() {
        return relation == null ? "" : relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getApplyAddress() {
        return applyAddress == null ? "" : applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
    }

    public String getApplyWorkUnit() {
        return applyWorkUnit == null ? "" : applyWorkUnit;
    }

    public void setApplyWorkUnit(String applyWorkUnit) {
        this.applyWorkUnit = applyWorkUnit;
    }

    public String getAgentName() {
        return agentName == null ? "" : agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentPhone() {
        return agentPhone == null ? "" : agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public String getVisitorName() {
        return visitorName == null ? "" : visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorSex() {
        return visitorSex == null ? "" : visitorSex;
    }

    public void setVisitorSex(String visitorSex) {
        this.visitorSex = visitorSex;
    }

    public String getVisitorNativePlace() {
        return visitorNativePlace == null ? "" : visitorNativePlace;
    }

    public void setVisitorNativePlace(String visitorNativePlace) {
        this.visitorNativePlace = visitorNativePlace;
    }

    public String getSupervisionPlace() {
        return supervisionPlace == null ? "" : supervisionPlace;
    }

    public void setSupervisionPlace(String supervisionPlace) {
        this.supervisionPlace = supervisionPlace;
    }

    public String getVisitorIdCard() {
        return visitorIdCard == null ? "" : visitorIdCard;
    }

    public void setVisitorIdCard(String visitorIdCard) {
        this.visitorIdCard = visitorIdCard;
    }

    public Office getOffice() {
        if (office == null) {
            office = new Office();
        }
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getStartTime() {
        return startTime == null ? "" : startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime == null ? "" : endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExamineTime() {
        return examineTime == null ? "" : examineTime;
    }

    public void setExamineTime(String examineTime) {
        this.examineTime = examineTime;
    }

    public String getApplyReason() {
        return applyReason == null ? "" : applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getState() {
        return state == null ? "" : state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRejectReson() {
        return rejectReson == null ? "" : rejectReson;
    }

    public void setRejectReson(String rejectReson) {
        this.rejectReson = rejectReson;
    }
}
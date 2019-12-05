package com.liuhezhineng.ximengsifa.bean.bussiness.fastlegal;

import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.base.Base;
import com.liuhezhineng.ximengsifa.bean.bussiness.Act;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;

/**
 * @author AIqinfeng
 * @date 2018/7/10
 * "accuserName":"张三",                                   //申请人姓名
 * "accuserPhone":"15835995891",                 //手机号码,必填 " caseClassify":"1",
 * //案件类别，来自字典act_type，必填 "caseType":"1", //案件类型，必填，随案件类别切换，人民调解时：纠纷类型dispute_type，法律援助：案件类型case_type
 * "caseCounty":{ "id":"15" //案发地区id，必填，旗县 }, "caseTitle":"测试01", //案件标题 "caseReason":"测试01"
 * //案件简要说明
 * 申请人所在机构: staffOffice(Office对象)
 *
 * 案件地址: caseAddress（String；字段长度225）
 *
 * 是否民转刑倾向：isTransferPenalty(String；字段长度：2；下拉框:0:否，1:是(字典类型：yes_no))
 *
 * 是否群体上访倾向: String isPetition(String；字段长度：2；下拉框:0:否，1:是(字典类型：yes_no))
 *
 * 是否影响稳定倾向: String isAffectStability(String；字段长度：2；下拉框:0:否，1:是(字典类型：yes_no))
 *
 * 涉及人数: caseInvolvecount(String；字段长度：8)
 *
 * 涉及金额(写实际金额): actualAmount（String；字段长度：64）
 *
 * 区分人员字段：personnelType (String；字段长度：2；0:社会大众；1:工作人员)
 */

public class FastRequestBean implements Base {

    private String accuserName;
    private String voicePath;

    private String accuserPhone;
    private String caseAddress;
    private String caseClassify;
    private String caseType;
    private Area caseCounty;
    private String caseTitle;
    private String caseReason;
    private String accuserIdCard;
    private Office staffOffice;
    private String isTransferPenalty;
    private String isPetition;
    private String isAffectStability;
    private String caseInvolvecount;
    private String actualAmount;
    private String personnelType;
    private Area caseTown;
    private String caseRank;
    private String caseTime;
    private String id;
    private Act act;


    private String procInsId;
    private String procDefId;
    private String procDefKey;
    private String taskId;
    private String taskDefKey;

    public String getProcInsId() {
        return procInsId;
    }

    public void setProcInsId(String procInsId) {
        this.procInsId = procInsId;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public String getProcDefKey() {
        return procDefKey;
    }

    public void setProcDefKey(String procDefKey) {
        this.procDefKey = procDefKey;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseTime() {
        return caseTime;
    }

    public void setCaseTime(String caseTime) {
        this.caseTime = caseTime;
    }

    public String getIsAffectStability() {
        return isAffectStability == null ? "" : isAffectStability;
    }

    public void setIsAffectStability(String isAffectStability) {
        this.isAffectStability = isAffectStability;
    }

    public String getCaseRank() {
        return caseRank == null ? "" : caseRank;
    }

    public void setCaseRank(String caseRank) {
        this.caseRank = caseRank;
    }

    public Area getCaseTown() {
        if (caseTown == null) {
            caseTown = new Area();
        }
        return caseTown;
    }

    public void setCaseTown(Area caseTown) {
        this.caseTown = caseTown;
    }

    public String getCaseAddress() {
        return caseAddress == null ? "" : caseAddress;
    }

    public void setCaseAddress(String caseAddress) {
        this.caseAddress = caseAddress;
    }

    public Office getStaffOffice() {
        if (staffOffice == null)
            staffOffice = new Office();
        return staffOffice;
    }

    public void setStaffOffice(Office staffOffice) {
        this.staffOffice = staffOffice;
    }

    public String getIsTransferPenalty() {
        return isTransferPenalty == null ? "" : isTransferPenalty;
    }

    public void setIsTransferPenalty(String isTransferPenalty) {
        this.isTransferPenalty = isTransferPenalty;
    }

    public String getIsPetition() {
        return isPetition == null ? "" : isPetition;
    }

    public void setIsPetition(String isPetition) {
        this.isPetition = isPetition;
    }

    public String getCaseInvolvecount() {
        return caseInvolvecount == null ? "" : caseInvolvecount;
    }

    public void setCaseInvolvecount(String caseInvolvecount) {
        this.caseInvolvecount = caseInvolvecount;
    }

    public String getActualAmount() {
        return actualAmount == null ? "" : actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getPersonnelType() {
        return personnelType == null ? "" : personnelType;
    }

    public void setPersonnelType(String personnelType) {
        this.personnelType = personnelType;
    }

    public String getVoicePath() {
        return voicePath;
    }

    public void setVoicePath(String voicePath) {
        this.voicePath = voicePath;
    }

    public String getAccuserIdCard() {
        return accuserIdCard == null ? "" : accuserIdCard;
    }

    public void setAccuserIdCard(String accuserIdCard) {
        this.accuserIdCard = accuserIdCard;
    }

    public String getAccuserName() {
        return accuserName == null ? "" : accuserName;
    }

    public void setAccuserName(String accuserName) {
        this.accuserName = accuserName;
    }

    public String getAccuserPhone() {
        return accuserPhone == null ? "" : accuserPhone;
    }

    public void setAccuserPhone(String accuserPhone) {
        this.accuserPhone = accuserPhone;
    }

    public String getCaseClassify() {
        return caseClassify == null ? "" : caseClassify;
    }

    public void setCaseClassify(String caseClassify) {
        this.caseClassify = caseClassify;
    }

    public String getCaseType() {
        return caseType == null ? "" : caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public Area getCaseCounty() {
        return caseCounty == null ? new Area() : caseCounty;
    }

    public void setCaseCounty(Area caseCounty) {
        this.caseCounty = caseCounty;
    }

    public String getCaseTitle() {
        return caseTitle == null ? "" : caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCaseReason() {
        return caseReason == null ? "" : caseReason;
    }

    public void setCaseReason(String caseReason) {
        this.caseReason = caseReason;
    }
}

package com.liuhezhineng.ximengsifa.bean.bussiness.fastlegal;

import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.advisory.CreateUser;
import com.liuhezhineng.ximengsifa.bean.bussiness.Act;
import com.liuhezhineng.ximengsifa.bean.bussiness.BaseBusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;

/**
 * @author AIqinfeng
 * @date 2018/7/12
 * @description 快速申请流程表单实体类
 */

public class FastFlowBean extends BaseBusinessBean {

    /**
     * 为true时是提交，false时是保存，字符串
     */
    private String isSubmit;

    private Act act;
    private String acceptManName;
    private String acceptManCode;
    private String caseAcceptCode;
    private String accuserName;
    private String accuserSex;
    private String accuserEthnic;
    private String accuserBirthday;
    private String accuserPhone;
    private String accuserIdCard;
    private Area accuserCounty;
    private Area accuserTown;
    private String caseClassify;
    private String caseType;
    private Area caseCounty;
    private Area caseTown;
    private String caseTitle;
    private String caseReason;
    private String caseTime;
    private String caseInvolvecount;
    private String caseSource;
    private String caseInvolveMoney;
    private String caseRank;
    private Office office;
    private UserBean transactor;
    private String handleResult;
    private String caseFile;
    private String caseClassifyDesc;
    private String caseTypeDesc;
    private String caseSourceDesc;
    private String caseRankDesc;
    private String accuserSexDesc;
    private String accuserEthnicDesc;
    private String caseInvolveMoneyDesc;
    private String voicePath;
    private CreateUser createBy;
    private Office staffOffice;
    private String isAffectStability;
    private String isPetition;
    private String isTransferPenalty;
    private String isAffectStabilityDesc;
    private String isPetitionDesc;
    private String isTransferPenaltyDesc;
    private String actualAmount;
    private String caseAddress;
    private String personnelType;
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

    /**
     * 督办：是否允许超时办理
     */
    private String warningState;
    /**
     * 督办：超时办理时间
     */
    private String warningTime;
    private String warningStateDesc;
    private String warningTimeDesc;

    public String getWarningStateDesc() {
        return warningStateDesc == null ? "" : warningStateDesc;
    }

    public void setWarningStateDesc(String warningStateDesc) {
        this.warningStateDesc = warningStateDesc;
    }

    public String getWarningTimeDesc() {
        return warningTimeDesc == null ? "" : warningTimeDesc;
    }

    public void setWarningTimeDesc(String warningTimeDesc) {
        this.warningTimeDesc = warningTimeDesc;
    }


    public String getWarningState() {
        return warningState == null ? "" : warningState;
    }

    public void setWarningState(String warningState) {
        this.warningState = warningState;
    }

    public String getWarningTime() {
        return warningTime == null ? "" : warningTime;
    }

    public void setWarningTime(String warningTime) {
        this.warningTime = warningTime;
    }

    public String getPersonnelType() {
        return personnelType == null ? "" : personnelType;
    }

    public void setPersonnelType(String personnelType) {
        this.personnelType = personnelType;
    }

    public String getIsAffectStabilityDesc() {
        return isAffectStabilityDesc == null ? "" : isAffectStabilityDesc;
    }

    public void setIsAffectStabilityDesc(String isAffectStabilityDesc) {
        this.isAffectStabilityDesc = isAffectStabilityDesc;
    }

    public String getIsPetitionDesc() {
        return isPetitionDesc == null ? "" : isPetitionDesc;
    }

    public void setIsPetitionDesc(String isPetitionDesc) {
        this.isPetitionDesc = isPetitionDesc;
    }

    public String getIsTransferPenaltyDesc() {
        return isTransferPenaltyDesc == null ? "" : isTransferPenaltyDesc;
    }

    public void setIsTransferPenaltyDesc(String isTransferPenaltyDesc) {
        this.isTransferPenaltyDesc = isTransferPenaltyDesc;
    }

    public Office getStaffOffice() {
        if (staffOffice == null)
            staffOffice = new Office();
        return staffOffice;
    }

    public void setStaffOffice(Office staffOffice) {
        this.staffOffice = staffOffice;
    }

    public String getIsAffectStability() {
        return isAffectStability == null ? "" : isAffectStability;
    }

    public void setIsAffectStability(String isAffectStability) {
        this.isAffectStability = isAffectStability;
    }

    public String getIsPetition() {
        return isPetition == null ? "" : isPetition;
    }

    public void setIsPetition(String isPetition) {
        this.isPetition = isPetition;
    }

    public String getIsTransferPenalty() {
        return isTransferPenalty == null ? "" : isTransferPenalty;
    }

    public void setIsTransferPenalty(String isTransferPenalty) {
        this.isTransferPenalty = isTransferPenalty;
    }

    public String getActualAmount() {
        return actualAmount == null ? "" : actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getCaseAddress() {
        return caseAddress == null ? "" : caseAddress;
    }

    public void setCaseAddress(String caseAddress) {
        this.caseAddress = caseAddress;
    }

    public CreateUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(CreateUser createBy) {
        this.createBy = createBy;
    }

    public String getVoicePath() {
        return voicePath;
    }

    public void setVoicePath(String voicePath) {
        this.voicePath = voicePath;
    }

    public String getCaseInvolveMoneyDesc() {
        return caseInvolveMoneyDesc == null ? "" : caseInvolveMoneyDesc;
    }

    public void setCaseInvolveMoneyDesc(String caseInvolveMoneyDesc) {
        this.caseInvolveMoneyDesc = caseInvolveMoneyDesc;
    }

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

    public String getIsSubmit() {
        return isSubmit == null ? "" : isSubmit;
    }

    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getAcceptManName() {
        return acceptManName == null ? "" : acceptManName;
    }

    public void setAcceptManName(String acceptManName) {
        this.acceptManName = acceptManName;
    }

    public String getAcceptManCode() {
        return acceptManCode == null ? "" : acceptManCode;
    }

    public void setAcceptManCode(String acceptManCode) {
        this.acceptManCode = acceptManCode;
    }

    public String getCaseAcceptCode() {
        return caseAcceptCode == null ? "" : caseAcceptCode;
    }

    public void setCaseAcceptCode(String caseAcceptCode) {
        this.caseAcceptCode = caseAcceptCode;
    }

    public String getAccuserName() {
        return accuserName == null ? "" : accuserName;
    }

    public void setAccuserName(String accuserName) {
        this.accuserName = accuserName;
    }

    public String getAccuserSex() {
        return accuserSex == null ? "" : accuserSex;
    }

    public void setAccuserSex(String accuserSex) {
        this.accuserSex = accuserSex;
    }

    public String getAccuserEthnic() {
        return accuserEthnic == null ? "" : accuserEthnic;
    }

    public void setAccuserEthnic(String accuserEthnic) {
        this.accuserEthnic = accuserEthnic;
    }

    public String getAccuserBirthday() {
        return accuserBirthday == null ? "" : accuserBirthday;
    }

    public void setAccuserBirthday(String accuserBirthday) {
        this.accuserBirthday = accuserBirthday;
    }

    public String getAccuserPhone() {
        return accuserPhone == null ? "" : accuserPhone;
    }

    public void setAccuserPhone(String accuserPhone) {
        this.accuserPhone = accuserPhone;
    }

    public String getAccuserIdCard() {
        return accuserIdCard == null ? "" : accuserIdCard;
    }

    public void setAccuserIdCard(String accuserIdCard) {
        this.accuserIdCard = accuserIdCard;
    }

    public Area getAccuserCounty() {
        if (accuserCounty == null) {
            accuserCounty = new Area();
        }
        return accuserCounty;
    }

    public void setAccuserCounty(Area accuserCounty) {
        this.accuserCounty = accuserCounty;
    }

    public Area getAccuserTown() {
        return accuserTown;
    }

    public void setAccuserTown(Area accuserTown) {
        this.accuserTown = accuserTown;
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
        if (caseCounty == null) {
            caseCounty = new Area();
        }
        return caseCounty;
    }

    public void setCaseCounty(Area caseCounty) {
        this.caseCounty = caseCounty;
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

    public String getCaseTime() {
        return caseTime == null ? "" : caseTime;
    }

    public void setCaseTime(String caseTime) {
        this.caseTime = caseTime;
    }

    public String getCaseInvolvecount() {
        return caseInvolvecount == null ? "" : caseInvolvecount;
    }

    public void setCaseInvolvecount(String caseInvolvecount) {
        this.caseInvolvecount = caseInvolvecount;
    }

    public String getCaseSource() {
        return caseSource == null ? "" : caseSource;
    }

    public void setCaseSource(String caseSource) {
        this.caseSource = caseSource;
    }

    public String getCaseInvolveMoney() {
        return caseInvolveMoney == null ? "" : caseInvolveMoney;
    }

    public void setCaseInvolveMoney(String caseInvolveMoney) {
        this.caseInvolveMoney = caseInvolveMoney;
    }

    public String getCaseRank() {
        return caseRank == null ? "" : caseRank;
    }

    public void setCaseRank(String caseRank) {
        this.caseRank = caseRank;
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

    public UserBean getTransactor() {
        return transactor;
    }

    public void setTransactor(UserBean transactor) {
        this.transactor = transactor;
    }

    public String getHandleResult() {
        return handleResult == null ? "" : handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public String getCaseFile() {
        return caseFile == null ? "" : caseFile;
    }

    public void setCaseFile(String caseFile) {
        this.caseFile = caseFile;
    }

    public String getCaseClassifyDesc() {
        return caseClassifyDesc == null ? "" : caseClassifyDesc;
    }

    public void setCaseClassifyDesc(String caseClassifyDesc) {
        this.caseClassifyDesc = caseClassifyDesc;
    }

    public String getCaseTypeDesc() {
        return caseTypeDesc == null ? "" : caseTypeDesc;
    }

    public void setCaseTypeDesc(String caseTypeDesc) {
        this.caseTypeDesc = caseTypeDesc;
    }

    public String getCaseSourceDesc() {
        return caseSourceDesc == null ? "" : caseSourceDesc;
    }

    public void setCaseSourceDesc(String caseSourceDesc) {
        this.caseSourceDesc = caseSourceDesc;
    }

    public String getCaseRankDesc() {
        return caseRankDesc == null ? "" : caseRankDesc;
    }

    public void setCaseRankDesc(String caseRankDesc) {
        this.caseRankDesc = caseRankDesc;
    }

    public String getAccuserSexDesc() {
        return accuserSexDesc == null ? "" : accuserSexDesc;
    }

    public void setAccuserSexDesc(String accuserSexDesc) {
        this.accuserSexDesc = accuserSexDesc;
    }

    public String getAccuserEthnicDesc() {
        return accuserEthnicDesc == null ? "" : accuserEthnicDesc;
    }

    public void setAccuserEthnicDesc(String accuserEthnicDesc) {
        this.accuserEthnicDesc = accuserEthnicDesc;
    }
}

/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.bussiness.legalaid;

import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.bussiness.BaseBusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.callback.bean.CaseTown;

/**
 * Auto-generated: 2018-05-17 10:23:47
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class RequestLawHelpWorkFlow extends BaseBusinessBean {

    private String isSubmit;
    private String name;
    private String sex;
    private String birthday;
    private Area area;
    private String ethnic;
    private String hasMongol;
    private String idCard;
    private String domicile;
    private String address;
    private String postCode;
    private String phone;
    private String employer;
    private String proxyName;
    private String proxyType;
    private String proxyIdCard;
    private String caseTitle;
    private String caseType;
    private String caseReason;
    private String caseFile;
    private LawOffice lawOffice;
    private Lawyer lawyer;
    private LegalOffice legalOffice;
    private LegalPerson legalPerson;
    private String thirdPartyScore;
    private String thirdPartyEvaluation;
    private String receiveSubsidy;
    private String aidCategory;
    private String caseNature;
    private String caseFile2;
    private Office lawAssistanceOffice;
    private Office lawAssistUser;
    private CaseTown town;

    private String  	educationDegreeDesc;
    private String     crowdTypeDesc;
    private String     caseSourceDesc;
    private  String     applyMatterDesc;
    private  String     caseStageDesc;
    private  String     economicSituationDesc;
    private  String     fyOpinion;
    private  String     fyfzrOpinion;

    public String getFyfzrOpinion() {
        return fyfzrOpinion;
    }

    public void setFyfzrOpinion(String fyfzrOpinion) {
        this.fyfzrOpinion = fyfzrOpinion;
    }

    public String getFyOpinion() {
        return fyOpinion;
    }

    public void setFyOpinion(String fyOpinion) {
        this.fyOpinion = fyOpinion;
    }

    public String getEconomicSituationDesc() {
        return economicSituationDesc;
    }

    public void setEconomicSituationDesc(String economicSituationDesc) {
        this.economicSituationDesc = economicSituationDesc;
    }

    public String getEducationDegreeDesc() {
        return educationDegreeDesc;
    }

    public void setEducationDegreeDesc(String educationDegreeDesc) {
        this.educationDegreeDesc = educationDegreeDesc;
    }

    public String getCrowdTypeDesc() {
        return crowdTypeDesc;
    }

    public void setCrowdTypeDesc(String crowdTypeDesc) {
        this.crowdTypeDesc = crowdTypeDesc;
    }

    public String getCaseSourceDesc() {
        return caseSourceDesc;
    }

    public void setCaseSourceDesc(String caseSourceDesc) {
        this.caseSourceDesc = caseSourceDesc;
    }

    public String getApplyMatterDesc() {
        return applyMatterDesc;
    }

    public void setApplyMatterDesc(String applyMatterDesc) {
        this.applyMatterDesc = applyMatterDesc;
    }

    public String getCaseStageDesc() {
        return caseStageDesc;
    }

    public void setCaseStageDesc(String caseStageDesc) {
        this.caseStageDesc = caseStageDesc;
    }

    public CaseTown getTown() {
        return town;
    }

    public void setTown(CaseTown town) {
        this.town = town;
    }

    private   String educationDegree;
    private    String crowdType;
    private    String caseSource;
    private    String applyMatter;
    private   String caseStage;
    private     String economicSituation;
    private     String approvalDate;
    private     String checkDate;


    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }

    public String getCrowdType() {
        return crowdType;
    }

    public void setCrowdType(String crowdType) {
        this.crowdType = crowdType;
    }

    public String getCaseSource() {
        return caseSource;
    }

    public void setCaseSource(String caseSource) {
        this.caseSource = caseSource;
    }

    public String getApplyMatter() {
        return applyMatter;
    }

    public void setApplyMatter(String applyMatter) {
        this.applyMatter = applyMatter;
    }

    public String getCaseStage() {
        return caseStage;
    }

    public void setCaseStage(String caseStage) {
        this.caseStage = caseStage;
    }

    public String getEconomicSituation() {
        return economicSituation;
    }

    public void setEconomicSituation(String economicSituation) {
        this.economicSituation = economicSituation;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
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

    public Office getLawAssistanceOffice() {
        if (lawAssistanceOffice == null) {
            lawAssistanceOffice = new Office();
        }
        return lawAssistanceOffice;
    }

    public void setLawAssistanceOffice(
            Office lawAssistanceOffice) {
        this.lawAssistanceOffice = lawAssistanceOffice;
    }

    public Office getLawAssistUser() {
        if (lawAssistUser == null) {
            lawAssistUser = new Office();
        }
        return lawAssistUser;
    }

    public void setLawAssistUser(
            Office lawAssistUser) {
        this.lawAssistUser = lawAssistUser;
    }

    public String getIsSubmit() {
        return isSubmit == null ? "" : isSubmit;
    }

    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getCaseFile2() {
        return caseFile2 == null ? "" : caseFile2;
    }

    public void setCaseFile2(String caseFile2) {
        this.caseFile2 = caseFile2;
    }

    public String getAidCategory() {
        return aidCategory == null ? "" : aidCategory;
    }

    public void setAidCategory(String aidCategory) {
        this.aidCategory = aidCategory;
    }

    public String getCaseNature() {
        return caseNature == null ? "" : caseNature;
    }

    public void setCaseNature(String caseNature) {
        this.caseNature = caseNature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Area getArea() {
        if (area == null) {
            area = new Area();
        }
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getHasMongol() {
        return hasMongol;
    }

    public void setHasMongol(String hasMongol) {
        this.hasMongol = hasMongol;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getProxyName() {
        return proxyName;
    }

    public void setProxyName(String proxyName) {
        this.proxyName = proxyName;
    }

    public String getProxyType() {
        return proxyType;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

    public String getProxyIdCard() {
        return proxyIdCard;
    }

    public void setProxyIdCard(String proxyIdCard) {
        this.proxyIdCard = proxyIdCard;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseReason() {
        return caseReason;
    }

    public void setCaseReason(String caseReason) {
        this.caseReason = caseReason;
    }

    public String getCaseFile() {
        return caseFile;
    }

    public void setCaseFile(String caseFile) {
        this.caseFile = caseFile;
    }

    public LawOffice getLawOffice() {
        return lawOffice;
    }

    public void setLawOffice(LawOffice lawOffice) {
        this.lawOffice = lawOffice;
    }

    public Lawyer getLawyer() {
        if (lawyer == null) {
            lawyer = new Lawyer();
        }
        return lawyer;
    }

    public void setLawyer(Lawyer lawyer) {
        this.lawyer = lawyer;
    }

    public LegalOffice getLegalOffice() {
        return legalOffice;
    }

    public void setLegalOffice(LegalOffice legalOffice) {
        this.legalOffice = legalOffice;
    }

    public LegalPerson getLegalPerson() {
        if (legalPerson == null) {
            legalPerson = new LegalPerson();
        }
        return legalPerson;
    }

    public void setLegalPerson(LegalPerson legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getThirdPartyScore() {
        return thirdPartyScore;
    }

    public void setThirdPartyScore(String thirdPartyScore) {
        this.thirdPartyScore = thirdPartyScore;
    }

    public String getThirdPartyEvaluation() {
        return thirdPartyEvaluation;
    }

    public void setThirdPartyEvaluation(String thirdPartyEvaluation) {
        this.thirdPartyEvaluation = thirdPartyEvaluation;
    }

    public String getReceiveSubsidy() {
        return receiveSubsidy;
    }

    public void setReceiveSubsidy(String receiveSubsidy) {
        this.receiveSubsidy = receiveSubsidy;
    }

}
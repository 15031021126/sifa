/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.bussiness.legalaid;

import com.liuhezhineng.ximengsifa.bean.Area;

/**
 * Auto-generated: 2018-05-17 10:23:47
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class RequestLawHelpBean {

	private String id;
	private String isSubmit;
	private String name;
	private String sex;
	private String sexDesc;
	private String birthday;
	private Area area;
	private String ethnic;
	private String ethnicDesc;
	private String hasMongol;
	private String hasMongolDesc;
	private String idCard;
	private String domicile;
	private String address;
	private String postCode;
	private String phone;
	private String employer;
	private String proxyName;
	private String proxyType;
	private String proxyTypeDesc;
	private String proxyIdCard;
	private String caseTitle;
	private String caseType;
	private String caseTypeDesc;
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
	private String aidCategoryDesc;
	private String caseNature;
	private String caseNatureDesc;
	private String caseFile2;
	private Town town;

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public String getSexDesc() {
		return sexDesc == null ? "" : sexDesc;
	}

	public void setSexDesc(String sexDesc) {
		this.sexDesc = sexDesc;
	}

	public String getHasMongolDesc() {
		return hasMongolDesc == null ? "" : hasMongolDesc;
	}

	public void setHasMongolDesc(String hasMongolDesc) {
		this.hasMongolDesc = hasMongolDesc;
	}

	public String getEthnicDesc() {
		return ethnicDesc == null ? "" : ethnicDesc;
	}

	public void setEthnicDesc(String ethnicDesc) {
		this.ethnicDesc = ethnicDesc;
	}

	public String getProxyTypeDesc() {
		return proxyTypeDesc == null ? "" : proxyTypeDesc;
	}

	public void setProxyTypeDesc(String proxyTypeDesc) {
		this.proxyTypeDesc = proxyTypeDesc;
	}

	public String getCaseTypeDesc() {
		return caseTypeDesc == null ? "" : caseTypeDesc;
	}

	public void setCaseTypeDesc(String caseTypeDesc) {
		this.caseTypeDesc = caseTypeDesc;
	}

	public String getAidCategoryDesc() {
		return aidCategoryDesc == null ? "" : aidCategoryDesc;
	}

	public void setAidCategoryDesc(String aidCategoryDesc) {
		this.aidCategoryDesc = aidCategoryDesc;
	}

	public String getCaseNatureDesc() {
		return caseNatureDesc == null ? "" : caseNatureDesc;
	}

	public void setCaseNatureDesc(String caseNatureDesc) {
		this.caseNatureDesc = caseNatureDesc;
	}

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
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
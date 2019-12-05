package com.liuhezhineng.ximengsifa.bean.bussiness.noticedefense;

import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.bussiness.Act;
import com.liuhezhineng.ximengsifa.bean.bussiness.BaseBusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;

/**
 * @author AIqinfeng
 * @date 2018/7/10
 */

public class NoticeRequestBean extends BaseBusinessBean {

	private String caseHandleProcess;
	private String thirdPartyScore;
	private String thirdPartyEvaluation;
	private String receiveSubsidy;
	private String caseFile2;
	private Act act;
	private String name;
	private String sex;
	private String sexdesc;
	private String birthday;
	private Area dom;
	private String ethnic;
	private String ethnicdesc;
	private String education;
	private String educationdesc;
	private String internation;
	private String internationdesc;
	private String proxyName;
	private String phone;
	private String renyuanType;
	private String renyuanTypedesc;
	private String casesum;
	private String idCard;
	private String domicile;
	private String address;
	private String sldate;
	private String caseTitle;
	private String officeType;
	private String officeNamee;
	private String jgPerson;
	private String jgphone;
	private String caseTelevancy;
	private String caseTelevancydesc;
	private String caseGuilt;
	private String caseGuiltdesc;
	private String caseInform;
	private String year;
	private String yearNo;
	private String informReson;
	private String informResondesc;
	private String cumName;
	private String scdate;
	private String caseReason;
	private String casesStage;
	private String casesStagedesc;
	private String caseFile;
	private Office legalOffice;
	private Office lawOffice;
	private Office lawyer;
	private String isSubmit;
	private String modality;
	private String modalitydesc;
	private String fyzhurenCom;
	private String approveCom;

	public String getThirdPartyScore() {
		return thirdPartyScore == null ? "" : thirdPartyScore;
	}

	public void setThirdPartyScore(String thirdPartyScore) {
		this.thirdPartyScore = thirdPartyScore;
	}

	public String getFyzhurenCom() {
		return fyzhurenCom == null ? "" : fyzhurenCom;
	}

	public void setFyzhurenCom(String fyzhurenCom) {
		this.fyzhurenCom = fyzhurenCom;
	}

	public String getApproveCom() {
		return approveCom == null ? "" : approveCom;
	}

	public void setApproveCom(String approveCom) {
		this.approveCom = approveCom;
	}

	public String getCaseHandleProcess() {
		return caseHandleProcess == null ? "" : caseHandleProcess;
	}

	public void setCaseHandleProcess(String caseHandleProcess) {
		this.caseHandleProcess = caseHandleProcess;
	}

	public String getThirdPartyEvaluation() {
		return thirdPartyEvaluation == null ? "" : thirdPartyEvaluation;
	}

	public void setThirdPartyEvaluation(String thirdPartyEvaluation) {
		this.thirdPartyEvaluation = thirdPartyEvaluation;
	}

	public String getReceiveSubsidy() {
		return receiveSubsidy == null ? "" : receiveSubsidy;
	}

	public void setReceiveSubsidy(String receiveSubsidy) {
		this.receiveSubsidy = receiveSubsidy;
	}

	public String getCaseFile2() {
		return caseFile2 == null ? "" : caseFile2;
	}

	public void setCaseFile2(String caseFile2) {
		this.caseFile2 = caseFile2;
	}

	public Act getAct() {
		return act;
	}

	public void setAct(Act act) {
		this.act = act;
	}

	public String getSexdesc() {
		return sexdesc == null ? "" : sexdesc;
	}

	public void setSexdesc(String sexdesc) {
		this.sexdesc = sexdesc;
	}

	public String getEthnicdesc() {
		return ethnicdesc == null ? "" : ethnicdesc;
	}

	public void setEthnicdesc(String ethnicdesc) {
		this.ethnicdesc = ethnicdesc;
	}

	public String getEducationdesc() {
		return educationdesc == null ? "" : educationdesc;
	}

	public void setEducationdesc(String educationdesc) {
		this.educationdesc = educationdesc;
	}

	public String getInternationdesc() {
		return internationdesc == null ? "" : internationdesc;
	}

	public void setInternationdesc(String internationdesc) {
		this.internationdesc = internationdesc;
	}

	public String getRenyuanTypedesc() {
		return renyuanTypedesc == null ? "" : renyuanTypedesc;
	}

	public void setRenyuanTypedesc(String renyuanTypedesc) {
		this.renyuanTypedesc = renyuanTypedesc;
	}

	public String getCaseTelevancydesc() {
		return caseTelevancydesc == null ? "" : caseTelevancydesc;
	}

	public void setCaseTelevancydesc(String caseTelevancydesc) {
		this.caseTelevancydesc = caseTelevancydesc;
	}

	public String getCaseGuiltdesc() {
		return caseGuiltdesc == null ? "" : caseGuiltdesc;
	}

	public void setCaseGuiltdesc(String caseGuiltdesc) {
		this.caseGuiltdesc = caseGuiltdesc;
	}

	public String getInformResondesc() {
		return informResondesc == null ? "" : informResondesc;
	}

	public void setInformResondesc(String informResondesc) {
		this.informResondesc = informResondesc;
	}

	public String getCasesStagedesc() {
		return casesStagedesc == null ? "" : casesStagedesc;
	}

	public void setCasesStagedesc(String casesStagedesc) {
		this.casesStagedesc = casesStagedesc;
	}

	public String getModality() {
		return modality == null ? "" : modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getModalitydesc() {
		return modalitydesc == null ? "" : modalitydesc;
	}

	public void setModalitydesc(String modalitydesc) {
		this.modalitydesc = modalitydesc;
	}

	public String getScdate() {
		return scdate == null ? "" : scdate;
	}

	public void setScdate(String scdate) {
		this.scdate = scdate;
	}

	public String getCaseReason() {
		return caseReason == null ? "" : caseReason;
	}

	public void setCaseReason(String caseReason) {
		this.caseReason = caseReason;
	}

	public String getJgphone() {
		return jgphone == null ? "" : jgphone;
	}

	public void setJgphone(String jgphone) {
		this.jgphone = jgphone;
	}

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex == null ? "" : sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday == null ? "" : birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Area getDom() {
		if (dom == null) {
			dom = new Area();
		}
		return dom;
	}

	public void setDom(Area dom) {
		this.dom = dom;
	}

	public String getEthnic() {
		return ethnic == null ? "" : ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getEducation() {
		return education == null ? "" : education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getInternation() {
		return internation == null ? "" : internation;
	}

	public void setInternation(String internation) {
		this.internation = internation;
	}

	public String getProxyName() {
		return proxyName == null ? "" : proxyName;
	}

	public void setProxyName(String proxyName) {
		this.proxyName = proxyName;
	}

	public String getPhone() {
		return phone == null ? "" : phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRenyuanType() {
		return renyuanType == null ? "" : renyuanType;
	}

	public void setRenyuanType(String renyuanType) {
		this.renyuanType = renyuanType;
	}

	public String getCasesum() {
		return casesum == null ? "" : casesum;
	}

	public void setCasesum(String casesum) {
		this.casesum = casesum;
	}

	public String getIdCard() {
		return idCard == null ? "" : idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getDomicile() {
		return domicile == null ? "" : domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}

	public String getAddress() {
		return address == null ? "" : address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSldate() {
		return sldate == null ? "" : sldate;
	}

	public void setSldate(String sldate) {
		this.sldate = sldate;
	}

	public String getCaseTitle() {
		return caseTitle == null ? "" : caseTitle;
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	public String getOfficeType() {
		return officeType == null ? "" : officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

	public String getOfficeNamee() {
		return officeNamee == null ? "" : officeNamee;
	}

	public void setOfficeNamee(String officeNamee) {
		this.officeNamee = officeNamee;
	}

	public String getJgPerson() {
		return jgPerson == null ? "" : jgPerson;
	}

	public void setJgPerson(String jgPerson) {
		this.jgPerson = jgPerson;
	}

	public String getCaseTelevancy() {
		return caseTelevancy == null ? "" : caseTelevancy;
	}

	public void setCaseTelevancy(String caseTelevancy) {
		this.caseTelevancy = caseTelevancy;
	}

	public String getCaseGuilt() {
		return caseGuilt == null ? "" : caseGuilt;
	}

	public void setCaseGuilt(String caseGuilt) {
		this.caseGuilt = caseGuilt;
	}

	public String getCaseInform() {
		return caseInform == null ? "" : caseInform;
	}

	public void setCaseInform(String caseInform) {
		this.caseInform = caseInform;
	}

	public String getYear() {
		return year == null ? "" : year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYearNo() {
		return yearNo == null ? "" : yearNo;
	}

	public void setYearNo(String yearNo) {
		this.yearNo = yearNo;
	}

	public String getInformReson() {
		return informReson == null ? "" : informReson;
	}

	public void setInformReson(String informReson) {
		this.informReson = informReson;
	}

	public String getCumName() {
		return cumName == null ? "" : cumName;
	}

	public void setCumName(String cumName) {
		this.cumName = cumName;
	}

	public String getCasesStage() {
		return casesStage == null ? "" : casesStage;
	}

	public void setCasesStage(String casesStage) {
		this.casesStage = casesStage;
	}

	public String getCaseFile() {
		return caseFile == null ? "" : caseFile;
	}

	public void setCaseFile(String caseFile) {
		this.caseFile = caseFile;
	}

	public Office getLegalOffice() {
		if (legalOffice == null) {
			legalOffice = new Office();
		}
		return legalOffice;
	}

	public void setLegalOffice(
		Office legalOffice) {
		this.legalOffice = legalOffice;
	}

	public Office getLawOffice() {
		if (lawOffice == null) {
			lawOffice = new Office();
		}
		return lawOffice;
	}

	public void setLawOffice(Office lawOffice) {
		this.lawOffice = lawOffice;
	}

	public Office getLawyer() {
		if (lawyer == null) {
			lawyer = new Office();
		}
		return lawyer;
	}

	public void setLawyer(Office lawyer) {
		this.lawyer = lawyer;
	}

	public String getIsSubmit() {
		return isSubmit == null ? "" : isSubmit;
	}

	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}
}

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.liuhezhineng.ximengsifa.callback;

import java.io.Serializable;

/**
 * 行政复议申请表Entity
 * @author wanglin
 * @version 2019-09-11
 */
public class OaReconsiderationApply implements Serializable {


	/**
	 * applyName : 李尚南
	 * applySex : 1
	 * applyBirthday : 1991-01-10
	 * applyPapernum : 130627199101105614
	 * applyWorkUnit : 测试测试
	 * applyAddress : 测试测试
	 * applyZipCode : 测试测试
	 * applyPhone : 13521257475
	 * agentName : 测试测试
	 * agentWorkUnit : 测试测试
	 * agentAddress : 测试测试
	 * agentZipCode : 测试测试
	 * agentPhone : 测试测试
	 * respondentName : 测试测试
	 * respondentLegalName : 测试测试
	 * respondentPost : 测试测试
	 * respondentAddress : 测试测试
	 * respondentZipCode : 测试测试测试
	 * incidentDate : 2019-11-14
	 * administrationBehavior : 测试测试
	 * reconsiderationOfficeId : {"id":"f321dbfc67f54c11a45817e0aa5e5b27","name":"镶黄旗司法局行政复议与应诉股"}
	 * reconsiderationRequest : 测试测试测试测试测试测试
	 * reconsiderationReason : 测试测试测试测试测试测试测试
	 * applyDate :
	 * caseFile :
	 */
	private  String applyPost;
	private  String applyLegalName;
	private  String caseTitle;

	public String getApplyPost() {
		return applyPost;
	}

	public void setApplyPost(String applyPost) {
		this.applyPost = applyPost;
	}

	public String getApplyLegalName() {
		return applyLegalName;
	}

	public void setApplyLegalName(String applyLegalName) {
		this.applyLegalName = applyLegalName;
	}

	public String getCaseTitle() {
		return caseTitle;
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	private String applyName;
	private String applySex;
	private String applyBirthday;
	private String applyPapernum;
	private String applyWorkUnit;
	private String applyAddress;
	private String applyZipCode;
	private String applyPhone;
	private String agentName;
	private String agentWorkUnit;
	private String agentAddress;
	private String agentZipCode;
	private String agentPhone;
	private String respondentName;
	private String respondentLegalName;
	private String respondentPost;
	private String respondentAddress;
	private String respondentZipCode;
	private String incidentDate;
	private String administrationBehavior;
	private ReconsiderationOfficeIdBean reconsiderationOfficeId;
	private String reconsiderationRequest;
	private String reconsiderationReason;
	private String applyDate;
	private String caseFile;

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getApplySex() {
		return applySex;
	}

	public void setApplySex(String applySex) {
		this.applySex = applySex;
	}

	public String getApplyBirthday() {
		return applyBirthday;
	}

	public void setApplyBirthday(String applyBirthday) {
		this.applyBirthday = applyBirthday;
	}

	public String getApplyPapernum() {
		return applyPapernum;
	}

	public void setApplyPapernum(String applyPapernum) {
		this.applyPapernum = applyPapernum;
	}

	public String getApplyWorkUnit() {
		return applyWorkUnit;
	}

	public void setApplyWorkUnit(String applyWorkUnit) {
		this.applyWorkUnit = applyWorkUnit;
	}

	public String getApplyAddress() {
		return applyAddress;
	}

	public void setApplyAddress(String applyAddress) {
		this.applyAddress = applyAddress;
	}

	public String getApplyZipCode() {
		return applyZipCode;
	}

	public void setApplyZipCode(String applyZipCode) {
		this.applyZipCode = applyZipCode;
	}

	public String getApplyPhone() {
		return applyPhone;
	}

	public void setApplyPhone(String applyPhone) {
		this.applyPhone = applyPhone;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentWorkUnit() {
		return agentWorkUnit;
	}

	public void setAgentWorkUnit(String agentWorkUnit) {
		this.agentWorkUnit = agentWorkUnit;
	}

	public String getAgentAddress() {
		return agentAddress;
	}

	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}

	public String getAgentZipCode() {
		return agentZipCode;
	}

	public void setAgentZipCode(String agentZipCode) {
		this.agentZipCode = agentZipCode;
	}

	public String getAgentPhone() {
		return agentPhone;
	}

	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}

	public String getRespondentName() {
		return respondentName;
	}

	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
	}

	public String getRespondentLegalName() {
		return respondentLegalName;
	}

	public void setRespondentLegalName(String respondentLegalName) {
		this.respondentLegalName = respondentLegalName;
	}

	public String getRespondentPost() {
		return respondentPost;
	}

	public void setRespondentPost(String respondentPost) {
		this.respondentPost = respondentPost;
	}

	public String getRespondentAddress() {
		return respondentAddress;
	}

	public void setRespondentAddress(String respondentAddress) {
		this.respondentAddress = respondentAddress;
	}

	public String getRespondentZipCode() {
		return respondentZipCode;
	}

	public void setRespondentZipCode(String respondentZipCode) {
		this.respondentZipCode = respondentZipCode;
	}

	public String getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(String incidentDate) {
		this.incidentDate = incidentDate;
	}

	public String getAdministrationBehavior() {
		return administrationBehavior;
	}

	public void setAdministrationBehavior(String administrationBehavior) {
		this.administrationBehavior = administrationBehavior;
	}

	public ReconsiderationOfficeIdBean getReconsiderationOfficeId() {
		return reconsiderationOfficeId;
	}

	public void setReconsiderationOfficeId(ReconsiderationOfficeIdBean reconsiderationOfficeId) {
		this.reconsiderationOfficeId = reconsiderationOfficeId;
	}

	public String getReconsiderationRequest() {
		return reconsiderationRequest;
	}

	public void setReconsiderationRequest(String reconsiderationRequest) {
		this.reconsiderationRequest = reconsiderationRequest;
	}

	public String getReconsiderationReason() {
		return reconsiderationReason;
	}

	public void setReconsiderationReason(String reconsiderationReason) {
		this.reconsiderationReason = reconsiderationReason;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getCaseFile() {
		return caseFile;
	}

	public void setCaseFile(String caseFile) {
		this.caseFile = caseFile;
	}

	public static class ReconsiderationOfficeIdBean  implements Serializable {
		/**
		 * id : f321dbfc67f54c11a45817e0aa5e5b27
		 * name : 镶黄旗司法局行政复议与应诉股
		 */

		private String id;
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
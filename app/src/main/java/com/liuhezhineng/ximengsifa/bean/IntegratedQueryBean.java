package com.liuhezhineng.ximengsifa.bean;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/7/19
 */

public class IntegratedQueryBean implements Base {

	private String title;
	private String applyDate;
	private Area caseArea;
	private String state;
	private String phone;
	private String applyUser;
	private String caseType;
	private String caseTypeDesc;
	private String stateDesc;
	private String severityDesc;

	public String getCaseTypeDesc() {
		return caseTypeDesc == null ? "" : caseTypeDesc;
	}

	public void setCaseTypeDesc(String caseTypeDesc) {
		this.caseTypeDesc = caseTypeDesc;
	}

	public String getStateDesc() {
		return stateDesc == null ? "" : stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public String getSeverityDesc() {
		return severityDesc == null ? "" : severityDesc;
	}

	public void setSeverityDesc(String severityDesc) {
		this.severityDesc = severityDesc;
	}

	public String getCaseType() {
		return caseType == null ? "" : caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getTitle() {
		return title == null ? "" : title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApplyDate() {
		return applyDate == null ? "" : applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public Area getCaseArea() {
		if (caseArea == null) {
			caseArea = new Area();
		}
		return caseArea;
	}

	public void setCaseArea(Area caseArea) {
		this.caseArea = caseArea;
	}

	public String getState() {
		return state == null ? "" : state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone == null ? "" : phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getApplyUser() {
		return applyUser == null ? "" : applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
}

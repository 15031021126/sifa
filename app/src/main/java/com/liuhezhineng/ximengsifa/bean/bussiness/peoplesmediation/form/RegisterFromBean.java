package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/6/7
 * @description 人民调解注册登记表单数据
 */

public class RegisterFromBean extends MediationWorkflow implements Base {

	private String caseSource;
	private String hasMongol;
	private String caseRank;
	private String caseTime;
	private String caseArea;
	private String caseInvolveCount;
	private String disputeSituation;

	public String getHasMongol() {
		return hasMongol == null ? "" : hasMongol;
	}

	public void setHasMongol(String hasMongol) {
		this.hasMongol = hasMongol;
	}

	public String getCaseSource() {
		return caseSource == null ? "" : caseSource;
	}

	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}

	public String getCaseRank() {
		return caseRank == null ? "" : caseRank;
	}

	public void setCaseRank(String caseRank) {
		this.caseRank = caseRank;
	}

	public String getCaseTime() {
		return caseTime == null ? "" : caseTime;
	}

	public void setCaseTime(String caseTime) {
		this.caseTime = caseTime;
	}

	public String getCaseArea() {
		return caseArea == null ? "" : caseArea;
	}

	public void setCaseArea(String caseArea) {
		this.caseArea = caseArea;
	}

	public String getCaseInvolveCount() {
		return caseInvolveCount == null ? "" : caseInvolveCount;
	}

	public void setCaseInvolveCount(String caseInvolveCount) {
		this.caseInvolveCount = caseInvolveCount;
	}

	public String getDisputeSituation() {
		return disputeSituation == null ? "" : disputeSituation;
	}

	public void setDisputeSituation(String disputeSituation) {
		this.disputeSituation = disputeSituation;
	}
}

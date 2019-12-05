package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation;

import com.liuhezhineng.ximengsifa.bean.Area;

/**
 * @author AIqinfeng
 * @date 2018/6/8
 */

public class MediationWrapper {

	private String id;
	private String caseSource;
	private String hasMongol;
	private String caseRank;
	private Area caseCounty;
	private Area caseTown;
	private String caseTime;
	private String caseArea;
	private String caseInvolveCount;
	private String caseType;
	private String disputeSituation;
	private String caseFile;
	private PeoplesMediationData oaPeopleMediationApply;

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaseSource() {
		return caseSource == null ? "" : caseSource;
	}

	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}

	public String getHasMongol() {
		return hasMongol == null ? "" : hasMongol;
	}

	public void setHasMongol(String hasMongol) {
		this.hasMongol = hasMongol;
	}

	public String getCaseRank() {
		return caseRank == null ? "" : caseRank;
	}

	public void setCaseRank(String caseRank) {
		this.caseRank = caseRank;
	}

	public Area getCaseCounty() {
		return caseCounty;
	}

	public void setCaseCounty(Area caseCounty) {
		this.caseCounty = caseCounty;
	}

	public Area getCaseTown() {
		return caseTown;
	}

	public void setCaseTown(Area caseTown) {
		this.caseTown = caseTown;
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

	public String getCaseType() {
		return caseType == null ? "" : caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getDisputeSituation() {
		return disputeSituation == null ? "" : disputeSituation;
	}

	public void setDisputeSituation(String disputeSituation) {
		this.disputeSituation = disputeSituation;
	}

	public String getCaseFile() {
		return caseFile == null ? "" : caseFile;
	}

	public void setCaseFile(String caseFile) {
		this.caseFile = caseFile;
	}

	public PeoplesMediationData getOaPeopleMediationApply() {
		return oaPeopleMediationApply;
	}

	public void setOaPeopleMediationApply(
		PeoplesMediationData oaPeopleMediationApply) {
		this.oaPeopleMediationApply = oaPeopleMediationApply;
	}
}

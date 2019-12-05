package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation;

import com.liuhezhineng.ximengsifa.bean.base.Base;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form.RecordFromBean;

/**
 * @author AIqinfeng
 * @date 2018/6/8
 */

public class MediateRecordWrapper implements Base {

	private String id;
	private RecordFromBean oaPeopleMediationExamine;
	private PeoplesMediationData oaPeopleMediationApply;
	private String agreementCode;

	public String getAgreementCode() {
		return agreementCode == null ? "" : agreementCode;
	}

	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RecordFromBean getOaPeopleMediationExamine() {
		return oaPeopleMediationExamine;
	}

	public void setOaPeopleMediationExamine(
		RecordFromBean oaPeopleMediationExamine) {
		this.oaPeopleMediationExamine = oaPeopleMediationExamine;
	}

	public PeoplesMediationData getOaPeopleMediationApply() {
		return oaPeopleMediationApply;
	}

	public void setOaPeopleMediationApply(
		PeoplesMediationData oaPeopleMediationApply) {
		this.oaPeopleMediationApply = oaPeopleMediationApply;
	}
}

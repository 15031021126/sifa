package com.liuhezhineng.ximengsifa.bean.evaluate;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/7/10
 */

public class BeEvaluateCommitInfoBean implements Base {

	private String prescription;
	private String proposal;
	private String beEvaluatedUserId;

	public String getPrescription() {
		return prescription == null ? "" : prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getProposal() {
		return proposal == null ? "" : proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public String getBeEvaluatedUserId() {
		return beEvaluatedUserId == null ? "" : beEvaluatedUserId;
	}

	public void setBeEvaluatedUserId(String beEvaluatedUserId) {
		this.beEvaluatedUserId = beEvaluatedUserId;
	}
}

/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean;

import com.google.gson.annotations.SerializedName;
import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * Auto-generated: 2018-06-29 9:16:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class EvaluateBean implements Base {

	private String commentId;
	private String prescription;
	private String proposal;
	@SerializedName("createBy")
	private BeEvaluatedUser beEvaluatedUser;
	private String type;
	private String createDate;

	public String getCreateDate() {
		return createDate == null ? "" : createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public BeEvaluatedUser getBeEvaluatedUser() {
		return beEvaluatedUser;
	}

	public void setBeEvaluatedUser(BeEvaluatedUser beEvaluatedUser) {
		this.beEvaluatedUser = beEvaluatedUser;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
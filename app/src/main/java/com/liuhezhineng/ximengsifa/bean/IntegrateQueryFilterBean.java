package com.liuhezhineng.ximengsifa.bean;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/7/19
 */

public class IntegrateQueryFilterBean implements Base {

	/**
	 * "type":"1",                          //办理渠道 字典oa_handle_ channels  一级字典
	 * "caseType":"",                    //案件类别 字典 oa_handle_ channels 二级字典
	 * "state":"1",                        //案件进度 字典oa_act_progress
	 * "caseArea":{"id":"15"},            //案发地区
	 * "title":"",                                //案件标题
	 * "applyBeginDate":"2018-06-10",    //申请时间开始
	 * "applyEndDate":"2018-08-20",        //申请时间结束
	 * "acceptBeginDate":"2018-06-10",    //受理时间开始
	 * "acceptEndDate":"2018-08-20",    //受理时间结束
	 * "severity":"1",                                //案件严重等级 字典case_rank
	 * "transactor":"",                            //承办人姓名
	 * "applyUser":""    //申请人姓名
	 */
	private String type;
	private String caseType;
	private String state;
	private Area caseArea;
	private String title;
	private String applyBeginDate;
	private String applyEndDate;
	private String acceptBeginDate;
	private String acceptEndDate;
	private String severity;
	private String transactor;
	private String applyUser;
	private PageBean page;

	public PageBean getPage() {
		if (page == null) {
			page = new PageBean();
		}
		return page;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}

	public String getType() {
		return type == null ? "" : type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCaseType() {
		return caseType == null ? "" : caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getState() {
		return state == null ? "" : state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getTitle() {
		return title == null ? "" : title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApplyBeginDate() {
		return applyBeginDate == null ? "" : applyBeginDate;
	}

	public void setApplyBeginDate(String applyBeginDate) {
		this.applyBeginDate = applyBeginDate;
	}

	public String getApplyEndDate() {
		return applyEndDate == null ? "" : applyEndDate;
	}

	public void setApplyEndDate(String applyEndDate) {
		this.applyEndDate = applyEndDate;
	}

	public String getAcceptBeginDate() {
		return acceptBeginDate == null ? "" : acceptBeginDate;
	}

	public void setAcceptBeginDate(String acceptBeginDate) {
		this.acceptBeginDate = acceptBeginDate;
	}

	public String getAcceptEndDate() {
		return acceptEndDate == null ? "" : acceptEndDate;
	}

	public void setAcceptEndDate(String acceptEndDate) {
		this.acceptEndDate = acceptEndDate;
	}

	public String getSeverity() {
		return severity == null ? "" : severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getTransactor() {
		return transactor == null ? "" : transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public String getApplyUser() {
		return applyUser == null ? "" : applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
}

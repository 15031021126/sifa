package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/6/8
 */

public class RecordFromBean extends MediationWorkflow implements Base {

	/**
	 * "examineDate":"",   //调查时间
	 * "examinePlace":"",   //调查地点
	 * "participants":"",   //参加人
	 * "inquirer":"",   //调查人
	 * "respondent":"",   //被调查人
	 * "recordContent":"",   //记录内容
	 * "recorder":"",   //记录人
	 * "caseFile":""   //案件相关文件
	 */
	private String examineDate;
	private String examinePlace;
	private String participants;
	private String inquirer;
	private String respondent;
	private String recordContent;
	private String recorder;

	public String getExamineDate() {
		return examineDate == null ? "" : examineDate;
	}

	public void setExamineDate(String examineDate) {
		this.examineDate = examineDate;
	}

	public String getExaminePlace() {
		return examinePlace == null ? "" : examinePlace;
	}

	public void setExaminePlace(String examinePlace) {
		this.examinePlace = examinePlace;
	}

	public String getParticipants() {
		return participants == null ? "" : participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public String getInquirer() {
		return inquirer == null ? "" : inquirer;
	}

	public void setInquirer(String inquirer) {
		this.inquirer = inquirer;
	}

	public String getRespondent() {
		return respondent == null ? "" : respondent;
	}

	public void setRespondent(String respondent) {
		this.respondent = respondent;
	}

	public String getRecordContent() {
		return recordContent == null ? "" : recordContent;
	}

	public void setRecordContent(String recordContent) {
		this.recordContent = recordContent;
	}

	public String getRecorder() {
		return recorder == null ? "" : recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
}

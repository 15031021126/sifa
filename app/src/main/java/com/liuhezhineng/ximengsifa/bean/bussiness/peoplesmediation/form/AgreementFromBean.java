package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/6/8
 */

public class AgreementFromBean extends MediationWorkflow implements Base {

	/**
	 * "agreementCode":"",   //协议书编号
	 * "disputeFact":"",   //纠纷事实
	 * "disputeMatter":"",   //争议事项
	 * "agreementContent":"",   //达成协议内容
	 * "performMode":"",   //旅行方式
	 * "timeLimit":"",   //时限
	 * "caseFile":"",   //案件相关文件
	 * "recorder":""   //协议记录人
	 */
	private String agreementCode;
	private String disputeFact;
	private String disputeMatter;
	private String agreementContent;
	private String performMode;
	private String timeLimit;
	private String recorder;

	public String getRecorder() {
		return recorder == null ? "" : recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public String getAgreementCode() {
		return agreementCode == null ? "" : agreementCode;
	}

	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	public String getDisputeFact() {
		return disputeFact == null ? "" : disputeFact;
	}

	public void setDisputeFact(String disputeFace) {
		this.disputeFact = disputeFace;
	}

	public String getDisputeMatter() {
		return disputeMatter == null ? "" : disputeMatter;
	}

	public void setDisputeMatter(String disputeMatter) {
		this.disputeMatter = disputeMatter;
	}

	public String getAgreementContent() {
		return agreementContent == null ? "" : agreementContent;
	}

	public void setAgreementContent(String agreementContent) {
		this.agreementContent = agreementContent;
	}

	public String getPerformMode() {
		return performMode == null ? "" : performMode;
	}

	public void setPerformMode(String performMode) {
		this.performMode = performMode;
	}

	public String getTimeLimit() {
		return timeLimit == null ? "" : timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
}

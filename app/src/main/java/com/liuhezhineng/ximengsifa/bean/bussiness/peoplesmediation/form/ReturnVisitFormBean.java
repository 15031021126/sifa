package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/6/8
 */

public class ReturnVisitFormBean extends MediationWorkflow implements Base {

	/**
	 * "visitCause":"",  //  回访事由
	 * "visitDate":"",  // 回访时间
	 * "visitSituation":"",  // 回访内容
	 * "caseFile":""  // 案件相关文件
	 */

	private String visitCause;
	private String visitDate;
	private String visitSituation;

	public String getVisitCause() {
		return visitCause == null ? "" : visitCause;
	}

	public void setVisitCause(String visitCause) {
		this.visitCause = visitCause;
	}

	public String getVisitDate() {
		return visitDate == null ? "" : visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitSituation() {
		return visitSituation == null ? "" : visitSituation;
	}

	public void setVisitSituation(String visitSituation) {
		this.visitSituation = visitSituation;
	}
}

package com.liuhezhineng.ximengsifa.bean.bussiness;

import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.RequestLawHelpWorkFlow;

/**
 * @author AIqinfeng
 * @description 法援流程表单
 */
public class LegalAidWorkflow extends RequestLawHelpWorkFlow {

	private Act act;

	public Act getAct() {
		return act;
	}

	public void setAct(Act act) {
		this.act = act;
	}

}
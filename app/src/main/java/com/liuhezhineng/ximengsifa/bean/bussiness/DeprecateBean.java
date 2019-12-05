package com.liuhezhineng.ximengsifa.bean.bussiness;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/7/22
 */

public class DeprecateBean implements Base {

	private String id;
	private String remarks;
	private Act act;

	public String getRemarks() {
		return remarks == null ? "" : remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Act getAct() {
		return act;
	}

	public void setAct(Act act) {
		this.act = act;
	}
}

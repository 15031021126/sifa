package com.liuhezhineng.ximengsifa.bean.bussiness;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/5/29
 */

public class BaseBusinessBean implements Base {

	private String id;

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

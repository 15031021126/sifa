package com.liuhezhineng.ximengsifa.bean.article;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/7/4
 */

public class Commentator implements Base {

	private String name;

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

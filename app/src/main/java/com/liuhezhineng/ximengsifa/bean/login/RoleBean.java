package com.liuhezhineng.ximengsifa.bean.login;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/6/20
 * @description 用户角色
 */

public class RoleBean implements Base {

	private String id;
	private String name;

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

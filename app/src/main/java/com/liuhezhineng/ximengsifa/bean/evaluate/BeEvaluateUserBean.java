package com.liuhezhineng.ximengsifa.bean.evaluate;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/7/9
 */

public class BeEvaluateUserBean implements Base {

	private String avatar;
	private String beEvaluatedUserId;
	private String name;
	private String roleName;

	public String getAvatar() {
		return avatar == null ? "" : avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBeEvaluatedUserId() {
		return beEvaluatedUserId == null ? "" : beEvaluatedUserId;
	}

	public void setBeEvaluatedUserId(String beEvaluatedUserId) {
		this.beEvaluatedUserId = beEvaluatedUserId;
	}

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleName() {
		return roleName == null ? "" : roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}

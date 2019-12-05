/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.advisory;

import android.os.Parcel;
import android.os.Parcelable;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * Auto-generated: 2018-06-21 14:20:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class CreateUser implements Base {

	private String id;
	private String name;
	private String loginFlag;
	private String loginCount;
	private String roleNames;
	private String photo;
	private boolean admin;



	public String getPhoto() {
		return photo == null ? "" : photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isAdmin() {
		return admin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	public String getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


}
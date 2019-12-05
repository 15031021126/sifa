/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * Auto-generated: 2018-06-29 9:16:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class BeEvaluatedUser implements Base {

	private String id;
	private String name;
	private String photo;

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo == null ? "" : photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
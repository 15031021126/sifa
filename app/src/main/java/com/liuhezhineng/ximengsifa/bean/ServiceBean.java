package com.liuhezhineng.ximengsifa.bean;

import java.io.Serializable;

/**
 * @author AIqinfeng
 * @date 2018/4/16
 */

public class ServiceBean implements Serializable {

	private String id;
	private String logo;
	private String title;

	public ServiceBean() {
	}

	public ServiceBean(String id, String logo, String title) {
		this.id = id;
		this.logo = logo;
		this.title = title;
	}

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogo() {
		return logo == null ? "" : logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return title == null ? "" : title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ServiceBean{" +
			"id='" + id + '\'' +
			", logo='" + logo + '\'' +
			", title='" + title + '\'' +
			'}';
	}
}

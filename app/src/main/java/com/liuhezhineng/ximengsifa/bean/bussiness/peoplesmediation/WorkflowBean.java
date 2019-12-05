package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation;

/**
 * @author AIqinfeng
 * @date 2018/6/10
 */

public class WorkflowBean {

	private String name;
	private String androidUrl;

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAndroidUrl() {
		return androidUrl == null ? "" : androidUrl;
	}

	public void setAndroidUrl(String androidUrl) {
		this.androidUrl = androidUrl;
	}
}

package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation;

/**
 * @author AIqinfeng
 * @date 2018/6/10
 */

public class WorkflowShowBean {

	private int status;
	private String taskDefKey;
	private String name;
	private String androidUrl;

	public String getTaskDefKey() {
		return taskDefKey == null ? "" : taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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

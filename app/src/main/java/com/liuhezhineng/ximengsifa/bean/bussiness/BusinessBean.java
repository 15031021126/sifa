/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.bussiness;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * Auto-generated: 2018-05-23 10:42:42
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class BusinessBean implements Base {

	private String status;
	private String procInsId;
	private String procDefId;
	private String procDefKey;
	private Task task;
	private String assignee;
	private String procDefName;
	private Vars vars;
	private String version;

	public String getVersion() {
		return version == null ? "" : version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus() {
		return status == null ? "" : status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}

	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	public String getProcDefKey() {
		return procDefKey;
	}

	public void setProcDefKey(String procDefKey) {
		this.procDefKey = procDefKey;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getProcDefName() {
		return procDefName;
	}

	public void setProcDefName(String procDefName) {
		this.procDefName = procDefName;
	}

	public Vars getVars() {
		return vars;
	}

	public void setVars(Vars vars) {
		this.vars = vars;
	}

}
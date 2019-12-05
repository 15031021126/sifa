package com.liuhezhineng.ximengsifa.bean.bussiness.legalaid;

import com.liuhezhineng.ximengsifa.bean.base.Base;
import com.liuhezhineng.ximengsifa.bean.bussiness.Task;

/**
 * @author AIqinfeng
 * @date 2018/7/9
 */

public class AidFlowInfoBean implements Base {

	private String taskName;
	private String status;
	private String businessId;
	private String procDefId;
	private String procDefKey;
	private String procInsId;
	private String taskId;
	private String taskDefKey;
	private String comment;
	private Task task;

	public String getComment() {
		return comment == null ? "" : comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBusinessId() {
		return businessId == null ? "" : businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getProcDefId() {
		return procDefId == null ? "" : procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	public String getProcDefKey() {
		return procDefKey == null ? "" : procDefKey;
	}

	public void setProcDefKey(String procDefKey) {
		this.procDefKey = procDefKey;
	}

	public String getProcInsId() {
		return procInsId == null ? "" : procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}

	public String getTaskId() {
		return taskId == null ? "" : taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskDefKey() {
		return taskDefKey == null ? "" : taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getTaskName() {
		return taskName == null ? "" : taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStatus() {
		return status == null ? "" : status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

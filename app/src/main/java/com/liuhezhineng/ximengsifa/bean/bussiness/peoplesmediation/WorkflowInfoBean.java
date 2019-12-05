package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation;

/**
 * @author AIqinfeng
 * @date 2018/6/10
 */

public class WorkflowInfoBean {

	/**
	 * {
	 * "id":"",
	 * "taskId":"3aa8073978d94e2bb5c04726e4913f5e",
	 * "taskName":"法援人员审批",
	 * "taskDefKey":"adi_approve",
	 * "procDefId":"legal_aid:15:4950e509316a499ab133b06b21ae3629",
	 * "procDefKey":"legal_aid",
	 * "businessTable":"oa_legal_aid",
	 * "businessId":"98c3aaa5bff84374b4c6ffd4410902d6",
	 * "status":"2",
	 * "comment":"",
	 * "todoTask":false,
	 * "durationTime":"",
	 * "finishTask":false
	 * },
	 */

	private String taskId;
	private String taskName;
	private String taskDefKey;
	private String procDefId;
	private String procDefKey;
	private String status;
	private String todoTask;

	public String getTaskId() {
		return taskId == null ? "" : taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName == null ? "" : taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDefKey() {
		return taskDefKey == null ? "" : taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
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

	public String getStatus() {
		return status == null ? "" : status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTodoTask() {
		return todoTask == null ? "" : todoTask;
	}

	public void setTodoTask(String todoTask) {
		this.todoTask = todoTask;
	}
}

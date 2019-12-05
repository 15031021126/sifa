/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.bussiness;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * Auto-generated: 2018-07-01 10:29:5
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DraftBean implements Base {

	private String id;
	private String procDefKey;
	private String title;
	private String comment;
	private String beginDate;
	private boolean todoTask;
	private String durationTime;
	private boolean finishTask;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcDefKey() {
		return procDefKey;
	}

	public void setProcDefKey(String procDefKey) {
		this.procDefKey = procDefKey;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public boolean getTodoTask() {
		return todoTask;
	}

	public void setTodoTask(boolean todoTask) {
		this.todoTask = todoTask;
	}

	public String getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}

	public boolean getFinishTask() {
		return finishTask;
	}

	public void setFinishTask(boolean finishTask) {
		this.finishTask = finishTask;
	}

}
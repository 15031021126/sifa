package com.liuhezhineng.ximengsifa.bean.evaluate;

import com.liuhezhineng.ximengsifa.bean.base.Base;
import com.liuhezhineng.ximengsifa.bean.bussiness.Task;
import com.liuhezhineng.ximengsifa.bean.bussiness.Vars;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/7/4
 */

public class MyEvaluateBean implements Base {

	private String commentId;
	private String currentUserId;
	private Vars vars;
	private Task task;
	private String procDefName;
	private String procDefKey;
	private String avatar;
	private String isEvaluate;
	private List<BeEvaluateUserBean> evaluatedList;

	public String getProcDefKey() {
		return procDefKey == null ? "" : procDefKey;
	}

	public void setProcDefKey(String procDefKey) {
		this.procDefKey = procDefKey;
	}

	public List<BeEvaluateUserBean> getEvaluatedList() {
		if (evaluatedList == null) {
			return new ArrayList<>();
		}
		return evaluatedList;
	}

	public void setEvaluatedList(
		List<BeEvaluateUserBean> evaluatedList) {
		this.evaluatedList = evaluatedList;
	}

	public String getIsEvaluate() {
		return isEvaluate == null ? "" : isEvaluate;
	}

	public void setIsEvaluate(String isEvaluate) {
		this.isEvaluate = isEvaluate;
	}

	public String getAvatar() {
		return avatar == null ? "" : avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getProcDefName() {
		return procDefName == null ? "" : procDefName;
	}

	public void setProcDefName(String procDefName) {
		this.procDefName = procDefName;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Vars getVars() {
		return vars;
	}

	public void setVars(Vars vars) {
		this.vars = vars;
	}

	public String getCommentId() {
		return commentId == null ? "" : commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCurrentUserId() {
		return currentUserId == null ? "" : currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}
}

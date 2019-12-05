package com.liuhezhineng.ximengsifa.bean.evaluate;

import com.liuhezhineng.ximengsifa.bean.base.Base;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/7/10
 */

public class MyEvaluateCommitInfoBean implements Base {

	private String commentId;
	private List<BeEvaluateCommitInfoBean> evaluatedList;
	private String type;

	public String getCommentId() {
		return commentId == null ? "" : commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public List<BeEvaluateCommitInfoBean> getEvaluatedList() {
		if (evaluatedList == null) {
			return new ArrayList<>();
		}
		return evaluatedList;
	}

	public void setEvaluatedList(
		List<BeEvaluateCommitInfoBean> evaluatedList) {
		this.evaluatedList = evaluatedList;
	}

	public String getType() {
		return type == null ? "" : type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

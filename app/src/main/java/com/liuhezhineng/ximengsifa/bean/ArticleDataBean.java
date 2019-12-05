package com.liuhezhineng.ximengsifa.bean;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * @author AIqinfeng
 * @date 2018/4/22
 */

public class ArticleDataBean implements Serializable {

	private String id;
	private String content;
	@SerializedName("copyfrom")
	private String from;
	private String relation;
	private String allowComment;

	public ArticleDataBean(String id, String content, String from, String relation,
		String allowComment) {
		this.id = id;
		this.content = content;
		this.from = from;
		this.relation = relation;
		this.allowComment = allowComment;
	}

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content == null ? "" : content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFrom() {
		return from == null ? "" : from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getRelation() {
		return relation == null ? "" : relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getAllowComment() {
		return allowComment == null ? "" : allowComment;
	}

	public void setAllowComment(String allowComment) {
		this.allowComment = allowComment;
	}

	@Override
	public String toString() {
		return "ArticleDataBean{" +
			"id='" + id + '\'' +
			", content='" + content + '\'' +
			", from='" + from + '\'' +
			", relation='" + relation + '\'' +
			", allowComment='" + allowComment + '\'' +
			'}';
	}
}

/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.advisory;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * Auto-generated: 2018-06-21 14:20:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class GuestbookCommentReList implements Base {

	private String id;
	private String createDate;
	private String commentId;
	private String commentType;
	private String guestbookId;
	private String content;
	private CreateUser createUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public String getGuestbookId() {
		return guestbookId;
	}

	public void setGuestbookId(String guestbookId) {
		this.guestbookId = guestbookId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CreateUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(CreateUser createUser) {
		this.createUser = createUser;
	}

}
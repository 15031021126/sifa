/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.advisory;

import com.liuhezhineng.ximengsifa.bean.base.Base;
import java.util.List;

/**
 * Auto-generated: 2018-06-21 14:20:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class CommentList implements Base {

	private String id;
	private String createDate;
	private String guestbookId;
	private String content;
	private CreateUser createUser;
	private String commentType;
	private List<GuestbookCommentReList> guestbookCommentReList;
	private String thumbsUpTrue;
	private String thumbsUpFalse;
	private String isEvaluate;

	public String getIsEvaluate() {
		return isEvaluate == null ? "" : isEvaluate;
	}

	public void setIsEvaluate(String isEvaluate) {
		this.isEvaluate = isEvaluate;
	}

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

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public List<GuestbookCommentReList> getGuestbookCommentReList() {
		return guestbookCommentReList;
	}

	public void setGuestbookCommentReList(List<GuestbookCommentReList> guestbookCommentReList) {
		this.guestbookCommentReList = guestbookCommentReList;
	}

	public String getThumbsUpTrue() {
		return thumbsUpTrue;
	}

	public void setThumbsUpTrue(String thumbsUpTrue) {
		this.thumbsUpTrue = thumbsUpTrue;
	}

	public String getThumbsUpFalse() {
		return thumbsUpFalse;
	}

	public void setThumbsUpFalse(String thumbsUpFalse) {
		this.thumbsUpFalse = thumbsUpFalse;
	}

}
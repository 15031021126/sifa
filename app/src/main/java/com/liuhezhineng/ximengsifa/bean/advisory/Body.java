/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.advisory;

import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.base.Base;
import java.util.List;

/**
 * Auto-generated: 2018-06-21 14:20:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Body implements Base {

	private String id;
	private String createDate;
	private String type;
	private String typeDesc;
	private String problemType;
	private String problemTypeDesc;
	private String title;
	private String content;
	private String name;
	private String phone;
	private Area area;
	private List<CommentList> commentList;
	private String isComment;
	private String isInquiries;
	private String inquiriesCount;
	private String reContent;

	public String getTypeDesc() {
		return typeDesc == null ? "" : typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getProblemTypeDesc() {
		return problemTypeDesc == null ? "" : problemTypeDesc;
	}

	public void setProblemTypeDesc(String problemTypeDesc) {
		this.problemTypeDesc = problemTypeDesc;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<CommentList> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentList> commentList) {
		this.commentList = commentList;
	}

	public String getIsComment() {
		return isComment;
	}

	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}

	public String getIsInquiries() {
		return isInquiries;
	}

	public void setIsInquiries(String isInquiries) {
		this.isInquiries = isInquiries;
	}

	public String getInquiriesCount() {
		return inquiriesCount;
	}

	public void setInquiriesCount(String inquiriesCount) {
		this.inquiriesCount = inquiriesCount;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

}
package com.liuhezhineng.ximengsifa.bean.article;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/7/2
 * @description 文章评论实体类
 */

public class CommentBean implements Base {

	private String name;
	private String photo;
	private String content;
	private String createDate;

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo == null ? "" : photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getContent() {
		return content == null ? "" : content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate == null ? "" : createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}

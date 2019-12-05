package com.liuhezhineng.ximengsifa.bean;

import java.io.Serializable;

/**
 * @author AIqinfeng
 * @date 2018/4/20
 */

public class AgencyCategoryVo implements Serializable {

	private String categoryId;
	private String categoryName;

	public AgencyCategoryVo(String categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public String getCategoryId() {
		return categoryId == null ? "" : categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName == null ? "" : categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "AgencyCategoryVo{" +
			"categoryId='" + categoryId + '\'' +
			", categoryName='" + categoryName + '\'' +
			'}';
	}
}

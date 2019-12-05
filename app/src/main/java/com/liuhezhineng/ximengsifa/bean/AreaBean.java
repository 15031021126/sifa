package com.liuhezhineng.ximengsifa.bean;

import java.io.Serializable;

/**
 * @author AIqinfeng
 * @date 2018/5/2
 */

public class AreaBean implements Serializable {

	private String id;
	private boolean isNewRecord;
	private String name;
	private int sort;
	private String parentId;

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isNewRecord() {
		return isNewRecord;
	}

	public void setNewRecord(boolean newRecord) {
		isNewRecord = newRecord;
	}

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getParentId() {
		return parentId == null ? "" : parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}

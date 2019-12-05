/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean;

import com.contrarywind.interfaces.IPickerViewData;
import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * Auto-generated: 2018-06-23 9:7:20
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Area implements Base, IPickerViewData {

	private String id;
	private String name;
	private String sort;
	private String parentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String getPickerViewText() {
		return name == null ? "暂无数据" : name;
	}
}
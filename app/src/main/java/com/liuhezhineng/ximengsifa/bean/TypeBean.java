/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean;

import com.contrarywind.interfaces.IPickerViewData;
import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * Auto-generated: 2018-05-15 10:29:5
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class TypeBean implements Base, IPickerViewData {

	private String value;
	private String label;
	private String type;
	private String description;
	private String sort;
	private String parentId;
	private String remarks;
	private String languageType;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	@Override
	public String getPickerViewText() {
		return label;
	}
}
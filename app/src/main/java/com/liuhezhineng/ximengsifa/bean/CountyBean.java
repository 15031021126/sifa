/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean;

import com.contrarywind.interfaces.IPickerViewData;
import java.io.Serializable;

/**
 * Auto-generated: 2018-05-15 10:8:6
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 *
 * {
"id":"8",
"isNewRecord":false,
"remarks":"",
"createDate":"2018-04-16 10:08:23",
"updateDate":"2018-04-16 10:08:23",
"parentIds":"0,1,4,5,",
"name":"阿巴嘎旗",
"sort":30,
"code":"152522",
"type":"4",
"parentId":"5"
}
 */
public class CountyBean implements Serializable,IPickerViewData {

	private String id;
	private boolean isNewRecord;
	private String remarks;
	private String createDate;
	private String updateDate;
	private String parentIds;
	private String name;
	private int sort;
	private String code;
	private String type;
	private String parentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsNewRecord() {
		return isNewRecord;
	}

	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getName() {
		return name;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String getPickerViewText() {
		return name;
	}
}
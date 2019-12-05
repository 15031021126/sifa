/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.bussiness;

import com.contrarywind.interfaces.IPickerViewData;

/**
 * Auto-generated: 2018-05-26 8:53:58
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Users implements IPickerViewData {

	private String name;
	private String id;
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getPickerViewText() {
		return name;
	}
}
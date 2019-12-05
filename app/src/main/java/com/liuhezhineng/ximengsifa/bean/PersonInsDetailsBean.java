package com.liuhezhineng.ximengsifa.bean;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/6/29
 */

public class PersonInsDetailsBean implements Base {

	private String key;
	private String value;

	public PersonInsDetailsBean(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key == null ? "" : key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value == null ? "" : value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

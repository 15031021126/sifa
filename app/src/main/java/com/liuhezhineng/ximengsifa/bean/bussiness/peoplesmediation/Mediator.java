package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation;

import com.contrarywind.interfaces.IPickerViewData;
import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/6/6
 */

public class Mediator implements IPickerViewData, Base {

	private String id;
	private String name;

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPickerViewText() {
		return name;
	}
}

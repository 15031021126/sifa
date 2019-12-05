package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation;

import com.contrarywind.interfaces.IPickerViewData;
import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/6/6
 */

public class Committee implements IPickerViewData, Base {

	/**
	 * 根据此 id 去查找人
	 */
	private String id;
	private String agencyName;

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgencyName() {
		return agencyName == null ? "" : agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	@Override
	public String getPickerViewText() {
		return agencyName;
	}
}

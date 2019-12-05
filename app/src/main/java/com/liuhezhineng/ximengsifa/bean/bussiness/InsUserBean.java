/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.bussiness;

import com.contrarywind.interfaces.IPickerViewData;
import com.liuhezhineng.ximengsifa.bean.base.Base;

import java.util.ArrayList;
import java.util.List;

/**
 * Auto-generated: 2018-05-26 8:51:32
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class InsUserBean implements IPickerViewData, Base {

	private String id;
	private String name;
	private List<InsUserBean> users;

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

    public List<InsUserBean> getUsers() {
        if (users == null) {
            return new ArrayList<>();
        }
        return users;
    }

    public void setUsers(List<InsUserBean> users) {
        this.users = users;
    }

    @Override
	public String getPickerViewText() {
		return name;
	}
}
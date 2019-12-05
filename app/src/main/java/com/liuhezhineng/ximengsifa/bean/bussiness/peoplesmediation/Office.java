package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation;

import com.contrarywind.interfaces.IPickerViewData;
import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/6/4
 */

public class Office implements IPickerViewData, Base {

    /* 机构或人员id */
    private String id;
    /* 机构或人员名 */
    private String name;
    /* 上级id */
    private String pid;
    /* 0人员 1机构 */
    private String type;

    public String getPid() {
        return pid == null ? "" : pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}

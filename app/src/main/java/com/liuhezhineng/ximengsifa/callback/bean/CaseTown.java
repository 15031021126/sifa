package com.liuhezhineng.ximengsifa.callback.bean;

import java.io.Serializable;

/**
 * Created by lishangnan on 2019/11/19.
 */

public class CaseTown implements Serializable{
    String id;
    String name;
    String sort;
    String parentId;

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
}

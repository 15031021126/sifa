/**
  * Copyright 2018 bejson.com 
  */
package com.liuhezhineng.ximengsifa.bean.personal;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * Auto-generated: 2018-06-23 9:7:20
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Town implements Base {

    private String id;
    private String name;
    private String sort;
    private String parentId;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setSort(String sort) {
         this.sort = sort;
     }


    public void setParentId(String parentId) {
         this.parentId = parentId;
     }
     public String getParentId() {
         return parentId;
     }

}
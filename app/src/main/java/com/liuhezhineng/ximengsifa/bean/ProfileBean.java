package com.liuhezhineng.ximengsifa.bean;

import com.liuhezhineng.ximengsifa.bean.base.Base;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;

/**
 * @author AIqinfeng
 * @date 2018/7/3
 * 用户信息展示、修改实体类
 * /**
 * * "mobile":"15269162329",
 * * "userSourceType":"用户职业",
 * * "birthday":"1993-01-01",
 * * "userExpand":{
 * * "sex":"2",
 * * "education":"研究生"
 * * },
 * * "area":{
 * * "id":"15"
 * * },
 * * "townarea":{
 * * "id":"15"
 * * }
 */

public class ProfileBean implements Base {

    /**
     * 性别 学历
     */
    private UserExpandBean userExpand;
    /**
     * 民族
     */
    private String ethnic;
    private String ethnicDesc;
    /**
     * 旗县
     */
    private Area area;
    /**
     * 乡镇
     */
    private Area townarea;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 职业
     */
    private String userSourceType;
    private String userSourceTypeDesc;

    public ProfileBean() {
    }

    public ProfileBean(UserBean bean) {
        this.userExpand = new UserExpandBean(bean.getSex(), bean.getEducation(), bean.getEducationDesc());
        this.ethnic = bean.getEthnic();
        this.ethnicDesc = bean.getEthnicDesc();
        this.area = bean.getCounty();
        this.townarea = bean.getTown();
        this.birthday = bean.getBirthday();
        this.mobile = bean.getMobile();
        this.userSourceType = bean.getUserSourceType();
        this.userSourceTypeDesc = bean.getUserSourceTypeDesc();
    }

    public String getUserSourceTypeDesc() {
        return userSourceTypeDesc == null ? "" : userSourceTypeDesc;
    }

    public void setUserSourceTypeDesc(String userSourceTypeDesc) {
        this.userSourceTypeDesc = userSourceTypeDesc;
    }

    public String getEthnicDesc() {
        return ethnicDesc == null ? "" : ethnicDesc;
    }

    public void setEthnicDesc(String ethnicDesc) {
        this.ethnicDesc = ethnicDesc;
    }

    public String getEthnic() {
        return ethnic == null ? "" : ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getMobile() {
        return mobile == null ? "" : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserSourceType() {
        return userSourceType == null ? "" : userSourceType;
    }

    public void setUserSourceType(String userSourceType) {
        this.userSourceType = userSourceType;
    }

    public String getBirthday() {
        return birthday == null ? "" : birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public UserExpandBean getUserExpand() {
        if (userExpand == null) {
            userExpand = new UserExpandBean();
        }
        return userExpand;
    }

    public void setUserExpand(UserExpandBean userExpand) {
        this.userExpand = userExpand;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Area getTownarea() {
        return townarea;
    }

    public void setTownarea(Area townarea) {
        this.townarea = townarea;
    }
}

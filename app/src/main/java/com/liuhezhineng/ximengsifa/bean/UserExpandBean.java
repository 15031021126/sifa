package com.liuhezhineng.ximengsifa.bean;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/7/3
 */

public class UserExpandBean implements Base {

    private String sex;
    private String education;
    private String educationDesc;

    public UserExpandBean() {
    }

    UserExpandBean(String sex, String education, String educationDesc) {
        this.sex = sex;
        this.education = education;
        this.educationDesc = educationDesc;
    }

    public String getEducationDesc() {
        return educationDesc == null ? "" : educationDesc;
    }

    public void setEducationDesc(String educationDesc) {
        this.educationDesc = educationDesc;
    }

    public String getSex() {
        return sex == null ? "" : sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEducation() {
        return education == null ? "" : education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}

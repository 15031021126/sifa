package com.liuhezhineng.ximengsifa.bean.login;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.constant.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/4/19
 * @description 用户数据实体类
 */

public class UserBean implements Serializable {

    private String id;
    private String name;
    private String loginName;
    private String photo;
    @SerializedName("realname")
    private String realName;
    @SerializedName("papernum")
    private String paperNum;
    private String mobile;
    private List<RoleBean> roleList;
    @SerializedName("area")
    private Area county;
    @SerializedName("townarea")
    private Area town;
    private String userType;
    private String birthday;
    private String sex;
    private String sexDesc;
    private String education;
    private String educationDesc;
    private String userSourceType;
    private String userSourceTypeDesc;
    private String ethnic;
    private String ethnicDesc;
    private Office office;

    public Office getOffice() {
        if (office == null) {
            office = new Office();
        }
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getEducationDesc() {
        return educationDesc == null ? "" : educationDesc;
    }

    public void setEducationDesc(String educationDesc) {
        this.educationDesc = educationDesc;
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

    public UserBean() {
    }

    public UserBean(String id, String photo, String realName, String mobile) {
        this.id = id;
        this.photo = photo;
        this.realName = realName;
        this.mobile = mobile;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserBean(String str) {
        id = getString(str);
    }

    private String getString(String str) {
        try {
            JSONObject jsonObject = new JSONObject(str);
            jsonObject.getString("");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPaperNum() {
        return paperNum == null ? "" : paperNum;
    }

    public void setPaperNum(String paperNum) {
        this.paperNum = paperNum;
    }

    public String getUserSourceType() {
        return userSourceType == null ? "" : userSourceType;
    }

    public void setUserSourceType(String userSourceType) {
        this.userSourceType = userSourceType;
    }

    public String getUserSourceTypeDesc() {
        return userSourceTypeDesc == null ? "" : userSourceTypeDesc;
    }

    public void setUserSourceTypeDesc(String userSourceTypeDesc) {
        this.userSourceTypeDesc = userSourceTypeDesc;
    }

    public String getLoginName() {
        return loginName == null ? "" : loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Area getCounty() {
        if (county == null) {
            county = new Area();
        }
        return county;
    }

    public void setCounty(Area county) {
        this.county = county;
    }

    public Area getTown() {
        if (town == null) {
            town = new Area();
        }
        return town;
    }

    public void setTown(Area town) {
        this.town = town;
    }

    public String getUserType() {
        return userType == null ? "" : userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getBirthday() {
        return birthday == null ? "" : birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex == null ? "" : sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSexDesc() {
        if (TextUtils.isEmpty(sexDesc)) {
            switch (sex) {
                case Constant.MALE:
                    return "男";
                case Constant.FEMALE:
                    return "女";
                default:
                    return "";
            }
        } else {
            return sexDesc;
        }
    }

    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc;
    }

    public String getEducation() {
        return education == null ? "" : education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public List<RoleBean> getRoleList() {
        if (roleList == null) {
            return new ArrayList<>();
        }
        return roleList;
    }

    public void setRoleList(List<RoleBean> roleList) {
        this.roleList = roleList;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo == null ? "" : photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRealName() {
        return realName == null ? "" : realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile == null ? "" : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", photo='" + photo + '\'' +
                ", realName='" + realName + '\'' +
                ", paperNum='" + paperNum + '\'' +
                ", mobile='" + mobile + '\'' +
                ", roleList=" + roleList +
                ", county=" + county +
                ", town=" + town +
                ", userType='" + userType + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", sexDesc='" + sexDesc + '\'' +
                ", education='" + education + '\'' +
                ", educationDesc='" + educationDesc + '\'' +
                ", userSourceType='" + userSourceType + '\'' +
                ", userSourceTypeDesc='" + userSourceTypeDesc + '\'' +
                ", ethnic='" + ethnic + '\'' +
                ", ethnicDesc='" + ethnicDesc + '\'' +
                '}';
    }
}

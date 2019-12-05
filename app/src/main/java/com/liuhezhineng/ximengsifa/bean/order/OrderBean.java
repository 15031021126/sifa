package com.liuhezhineng.ximengsifa.bean.order;

import com.liuhezhineng.ximengsifa.bean.advisory.CreateUser;
import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * <pre>
 *     author : qingfeng
 *     e-mail : 1913518036@qq.com
 *     time   : 2018/12/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class OrderBean implements Base {

    private String id;
    private CreateUser createBy;
    private String createDate;
    private String updateDate;
    private String appointmentDate;
    private String type;
    private String typeDesc;
    private String mold;
    private String moldDesc;
    private String content;
    private CreateUser bookedUser;
    private String phone;
    private String returnContent;
    private String num;
    private String state;
    private String stateDesc;

    /**
     * 预约状态0待处理1预约成功2预约失败
     *
     * @return 预约状态
     */
    public String getStateDesc() {
        switch (state) {
            case "0":
                return "待处理";
            case "1":
                return "预约成功";
            default:
                return "预约失败";
        }
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CreateUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(CreateUser createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate == null ? "" : createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate == null ? "" : updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getAppointmentDate() {
        return appointmentDate == null ? "" : appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc == null ? "" : typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getMold() {
        return mold == null ? "" : mold;
    }

    public void setMold(String mold) {
        this.mold = mold;
    }

    public String getMoldDesc() {
        return moldDesc == null ? "" : moldDesc;
    }

    public void setMoldDesc(String moldDesc) {
        this.moldDesc = moldDesc;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CreateUser getBookedUser() {
        return bookedUser;
    }

    public void setBookedUser(CreateUser bookedUser) {
        this.bookedUser = bookedUser;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReturnContent() {
        return returnContent == null ? "" : returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }

    public String getNum() {
        return num == null ? "" : num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getState() {
        return state == null ? "" : state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

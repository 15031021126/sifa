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
public class OrderRequestBean implements Base {

    private String appointmentDate;
    private String type;
    private String mold;
    private String content;
    private String phone;
    private CreateUser bookedUser;

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

    public String getMold() {
        return mold == null ? "" : mold;
    }

    public void setMold(String mold) {
        this.mold = mold;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CreateUser getBookedUser() {
        return bookedUser;
    }

    public void setBookedUser(CreateUser bookedUser) {
        this.bookedUser = bookedUser;
    }
}

package com.liuhezhineng.ximengsifa.bean.advisory;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/6/21
 */

public class AdvisoryBean implements Base {

    private String id;
    private String title;
    private String content;
    private String name;
    private String isComment;
    private String isInquiries;
    private String problemType;
    private String type;
    private String createDate;
    /* 分配 处理 */
    private String state;
    private String stateDesc;

    /* 分配者，用这个来判断分配给了自己还是他人 */
    private CreateUser distributeUser;
    /* 处理者 */
    private CreateUser handleUser;

    public CreateUser getHandleUser() {
        if (handleUser == null)
            handleUser = new CreateUser();
        return handleUser;
    }

    public void setHandleUser(CreateUser handleUser) {
        this.handleUser = handleUser;
    }

    public CreateUser getDistributeUser() {
        if (distributeUser == null)
            distributeUser = new CreateUser();
        return distributeUser;
    }

    public void setDistributeUser(CreateUser distributeUser) {
        this.distributeUser = distributeUser;
    }

    public String getState() {
        return state == null ? "" : state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateDesc() {
        return stateDesc == null ? "" : stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public String getCreateDate() {
        return createDate == null ? "" : createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getProblemType() {
        return problemType == null ? "" : problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsComment() {
        return isComment == null ? "" : isComment;
    }

    public void setIsComment(String isComment) {
        this.isComment = isComment;
    }

    public String getIsInquiries() {
        return isInquiries == null ? "" : isInquiries;
    }

    public void setIsInquiries(String isInquiries) {
        this.isInquiries = isInquiries;
    }
}

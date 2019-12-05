package com.liuhezhineng.ximengsifa.bean.msg;

import com.liuhezhineng.ximengsifa.bean.advisory.CreateUser;
import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * <pre>
 *     author : qingfeng
 *     e-mail : 1913518036@qq.com
 *     time   : 2018/12/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class LocalMsg implements Base {

    private String id;
    private CreateUser createBy;
    private String createDate;
    private String updateDate;
    private String type;        // 类型
    private String typeDesc;
    private String title;        // 标题
    private String content;        // 类型
    private String files;        // 附件
    private String status;        // 状态
    private String readNum;        // 已读
    private String unReadNum;    // 未读
    private boolean isSelf;        // 是否只查询自己的通知
    private String readFlag;    // 本人阅读状态
    private String companyId; //创建人所属部门

    public CreateUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(CreateUser createBy) {
        this.createBy = createBy;
    }

    public String getTypeDesc() {
        return typeDesc == null ? "" : typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getFiles() {
        return files == null ? "" : files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReadNum() {
        return readNum == null ? "" : readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getUnReadNum() {
        return unReadNum == null ? "" : unReadNum;
    }

    public void setUnReadNum(String unReadNum) {
        this.unReadNum = unReadNum;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    public String getReadFlag() {
        return readFlag == null ? "" : readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }

    public String getCompanyId() {
        return companyId == null ? "" : companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}

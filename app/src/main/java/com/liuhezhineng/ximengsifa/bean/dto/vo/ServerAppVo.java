package com.liuhezhineng.ximengsifa.bean.dto.vo;

import com.liuhezhineng.ximengsifa.bean.base.Base;

import java.util.List;

/**
 * 服务应用数据展示对象
 *
 * @author kakasun
 * @create 2018-04-25 下午3:29
 */
public class ServerAppVo implements Base {

    String id;
    String name;        // 名称
    String logo;        // logo图片链接
    String serverType;        // 服务类别 一级服务：0；文章咨询：1；机构人员：2；
    String categoryId;//机构人员类别id
    List<ColumnVo> columnList;
    String pid;        // 父服务id
    String link;        // 服务的外部连接
    Integer leve;        // 层级
    private String sourceId;
    private String mobileLogo;
    private String pname;

    public ServerAppVo() {
    }

    public ServerAppVo(String name, String logo, String link) {
        this.name = name;
        this.logo = logo;
        this.link = link;
    }

    public ServerAppVo(String name) {
        this.name = name;
    }

    public String getPname() {
        return pname == null ? "" : pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getMobileLogo() {
        return mobileLogo == null ? "" : mobileLogo;
    }

    public void setMobileLogo(String mobileLogo) {
        this.mobileLogo = mobileLogo;
    }

    public String getSourceId() {
        return sourceId == null ? "" : sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<ColumnVo> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnVo> columnList) {
        this.columnList = columnList;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getLeve() {
        return leve;
    }

    public void setLeve(Integer leve) {
        this.leve = leve;
    }
}

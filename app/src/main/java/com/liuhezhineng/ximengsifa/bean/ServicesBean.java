package com.liuhezhineng.ximengsifa.bean;

import java.io.Serializable;

/**
 * @author AIqinfeng
 * @date 2018/4/17
 */

public class ServicesBean implements Serializable {

	private String id;
	private String parentIds;
	private String name;
	private String siteId;
	private String officeId;
	private String module;
	private String image;
	private String href;
	private String inMenu;
	private String allowComment;
	private String isAudit;

	public ServicesBean() {
	}

	public ServicesBean(String id, String parentIds, String name, String siteId,
		String officeId, String module, String image, String href, String inMenu,
		String allowComment, String isAudit) {
		this.id = id;
		this.parentIds = parentIds;
		this.name = name;
		this.siteId = siteId;
		this.officeId = officeId;
		this.module = module;
		this.image = image;
		this.href = href;
		this.inMenu = inMenu;
		this.allowComment = allowComment;
		this.isAudit = isAudit;
	}

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentIds() {
		return parentIds == null ? "" : parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSiteId() {
		return siteId == null ? "" : siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getOfficeId() {
		return officeId == null ? "" : officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getModule() {
		return module == null ? "" : module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getImage() {
		return image == null ? "" : image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHref() {
		return href == null ? "" : href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getInMenu() {
		return inMenu == null ? "" : inMenu;
	}

	public void setInMenu(String inMenu) {
		this.inMenu = inMenu;
	}

	public String getAllowComment() {
		return allowComment == null ? "" : allowComment;
	}

	public void setAllowComment(String allowComment) {
		this.allowComment = allowComment;
	}

	public String getIsAudit() {
		return isAudit == null ? "" : isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	@Override
	public String toString() {
		return "ServicesBean{" +
			"id='" + id + '\'' +
			", parentIds='" + parentIds + '\'' +
			", name='" + name + '\'' +
			", siteId='" + siteId + '\'' +
			", officeId='" + officeId + '\'' +
			", module='" + module + '\'' +
			", image='" + image + '\'' +
			", href='" + href + '\'' +
			", inMenu='" + inMenu + '\'' +
			", allowComment='" + allowComment + '\'' +
			", isAudit='" + isAudit + '\'' +
			'}';
	}
}

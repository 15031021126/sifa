package com.liuhezhineng.ximengsifa.bean.advisory;

import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.base.Base;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;

/**
 * @author AIqinfeng
 * @date 2018/6/24
 */

public class ComplaintsRequestBean implements Base {

	private String name;
	private String phone;
	private String sex;
	private Area area;

	private String remarks;
	private Area noarea;
	private Area notown;
	private Office organization;
	private String nonumber;
	private String anonymous;

	private String suchComplaints;
	private String classWorker;

	private String title;
	private String content;
	private String files;
	/* 意见投诉标记 1投诉 2意见（不能为空） */
	private String remarkType;

    public String getRemarkType() {
        return remarkType == null ? "" : remarkType;
    }

    public void setRemarkType(String remarkType) {
        this.remarkType = remarkType;
    }

    public String getFiles() {
		return files == null ? "" : files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getSuchComplaints() {
		return suchComplaints == null ? "" : suchComplaints;
	}

	public void setSuchComplaints(String suchComplaints) {
		this.suchComplaints = suchComplaints;
	}

	public String getClassWorker() {
		return classWorker == null ? "" : classWorker;
	}

	public void setClassWorker(String classWorker) {
		this.classWorker = classWorker;
	}

	public Area getNoarea() {
		return noarea;
	}

	public void setNoarea(Area noarea) {
		this.noarea = noarea;
	}

	public Area getNotown() {
		return notown;
	}

	public void setNotown(Area notown) {
		this.notown = notown;
	}

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone == null ? "" : phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex == null ? "" : sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getRemarks() {
		return remarks == null ? "" : remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Office getOrganization() {
		return organization;
	}

	public void setOrganization(
		Office organization) {
		this.organization = organization;
	}

	public String getNonumber() {
		return nonumber == null ? "" : nonumber;
	}

	public void setNonumber(String nonumber) {
		this.nonumber = nonumber;
	}

	public String getAnonymous() {
		return anonymous == null ? "" : anonymous;
	}

	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
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
}

package com.liuhezhineng.ximengsifa.bean;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/7/22
 */

public class StatisticalBean implements Base {

	private String color;
	private String count;
	private String name;
	private Area caseArea;
	private String type;
	private String caseType;
	private String applyBeginDate;
	private String applyEndDate;

	public Area getCaseArea() {
		return caseArea;
	}

	public void setCaseArea(Area caseArea) {
		this.caseArea = caseArea;
	}

	public String getType() {
		return type == null ? "" : type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCaseType() {
		return caseType == null ? "" : caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getApplyBeginDate() {
		return applyBeginDate == null ? "" : applyBeginDate;
	}

	public void setApplyBeginDate(String applyBeginDate) {
		this.applyBeginDate = applyBeginDate;
	}

	public String getApplyEndDate() {
		return applyEndDate == null ? "" : applyEndDate;
	}

	public void setApplyEndDate(String applyEndDate) {
		this.applyEndDate = applyEndDate;
	}

	public String getColor() {
		return color == null ? "" : color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCount() {
		return count == null ? "" : count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.personal;

import com.liuhezhineng.ximengsifa.bean.base.Base;
import java.util.List;

/**
 * Auto-generated: 2018-06-22 18:42:54
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PersonalInstitutionsBean implements Base {

	private String id;
	private String name;
	private String logo;
	private String serverType;
	private String categoryId;
	private String pid;
	private String link;
	private String leve;
	private String sourceId;
	private List<DictList> dictList;

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

	public String getLeve() {
		return leve;
	}

	public void setLeve(String leve) {
		this.leve = leve;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public List<DictList> getDictList() {
		return dictList;
	}

	public void setDictList(List<DictList> dictList) {
		this.dictList = dictList;
	}

}
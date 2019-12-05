package com.liuhezhineng.ximengsifa.bean.dto.vo;

import java.io.Serializable;

/**
 * 栏目
 *
 * @author kakasun
 * @create 2018-04-17 上午9:58
 */
public class ColumnVo implements Serializable {

	String id;
	/**
	 * 所有父级编号
	 */
	String parentIds;
	/**
	 * 名称
	 */
	String name;
	/**
	 * 归属站点
	 */
	String siteId;

	/**
	 * 点击量
	 */
	String hits;

	public ColumnVo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}


	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "ColumnVo{" +
			"id='" + id + '\'' +
			", siteId='" + siteId + '\'' +
			", name='" + name + '\'' +
			", hits='" + hits + '\'' +
			'}';
	}
}

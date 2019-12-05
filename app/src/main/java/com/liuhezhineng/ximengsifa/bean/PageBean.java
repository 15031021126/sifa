package com.liuhezhineng.ximengsifa.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/4/20
 */

public class PageBean<T> implements Serializable {

	/**
	 * "pageNo":1,
	 * "pageSize":30,
	 * "count":1,
	 * "list":[]
	 */
	private int pageNo;
	private int pageSize;
	private int count;
	private List<T> list;

	public PageBean() {
	}

	public PageBean(int pageNo, int pageSize, int count, List<T> list) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.count = count;
		this.list = list;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getList() {
		if (list == null) {
			return new ArrayList<>();
		}
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean{" +
			"pageNo=" + pageNo +
			", pageSize=" + pageSize +
			", count=" + count +
			", list=" + list +
			'}';
	}
}

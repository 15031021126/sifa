package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/6/8
 */

public class MediateRecordFromBean extends MediationWorkflow implements Base {

	private String mediateDate;
	private String mediatePlace;
	private String mediateRecord;

	public String getMediateDate() {
		return mediateDate == null ? "" : mediateDate;
	}

	public void setMediateDate(String mediateDate) {
		this.mediateDate = mediateDate;
	}

	public String getMediatePlace() {
		return mediatePlace == null ? "" : mediatePlace;
	}

	public void setMediatePlace(String mediatePlace) {
		this.mediatePlace = mediatePlace;
	}

	public String getMediateRecord() {
		return mediateRecord == null ? "" : mediateRecord;
	}

	public void setMediateRecord(String mediateRecord) {
		this.mediateRecord = mediateRecord;
	}
}

package com.liuhezhineng.ximengsifa.bean.voice;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/8/5
 */

public class NetNlu implements Base {

	private String history;
	private String text;
	private String responseId;
	private String service;
	private String nluProcessTime;
	private String code;
	private String netTag;
	private int rc;

	public String getHistory() {
		return history == null ? "" : history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getText() {
		return text == null ? "" : text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getResponseId() {
		return responseId == null ? "" : responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public String getService() {
		return service == null ? "" : service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getNluProcessTime() {
		return nluProcessTime == null ? "" : nluProcessTime;
	}

	public void setNluProcessTime(String nluProcessTime) {
		this.nluProcessTime = nluProcessTime;
	}

	public String getCode() {
		return code == null ? "" : code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNetTag() {
		return netTag == null ? "" : netTag;
	}

	public void setNetTag(String netTag) {
		this.netTag = netTag;
	}

	public int getRc() {
		return rc;
	}

	public void setRc(int rc) {
		this.rc = rc;
	}
}

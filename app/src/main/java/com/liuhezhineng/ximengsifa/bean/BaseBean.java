package com.liuhezhineng.ximengsifa.bean;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @date 2018/4/17
 */

public class BaseBean<T> implements Serializable {

	private int status;
	private String msg;
	private T body;

	public BaseBean(int status, String msg, T body) {
		this.status = status;
		this.msg = msg;
		this.body = body;
	}

	public BaseBean(String str) {
		try {
			JSONObject jsonObject = new JSONObject(str);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getMsg() {
		return msg == null ? "" : msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}

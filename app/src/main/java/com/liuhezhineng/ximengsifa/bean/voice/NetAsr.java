package com.liuhezhineng.ximengsifa.bean.voice;

import com.google.gson.annotations.SerializedName;
import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/8/5
 */

public class NetAsr implements Base {

	@SerializedName("result_type")
	private String resultType;
	@SerializedName("engine_mode")
	private String engineMode;
	@SerializedName("sessionID")
	private String sessionID;
	@SerializedName("last_result")
	private boolean lastResult;
	@SerializedName("recognition_result")
	private String recognitionResult;

	public String getResultType() {
		return resultType == null ? "" : resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getEngineMode() {
		return engineMode == null ? "" : engineMode;
	}

	public void setEngineMode(String engineMode) {
		this.engineMode = engineMode;
	}

	public String getSessionID() {
		return sessionID == null ? "" : sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public boolean isLastResult() {
		return lastResult;
	}

	public void setLastResult(boolean lastResult) {
		this.lastResult = lastResult;
	}

	public String getRecognitionResult() {
		return recognitionResult == null ? "" : recognitionResult;
	}

	public void setRecognitionResult(String recognitionResult) {
		this.recognitionResult = recognitionResult;
	}
}

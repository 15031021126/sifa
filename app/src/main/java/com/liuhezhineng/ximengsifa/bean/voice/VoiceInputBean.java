package com.liuhezhineng.ximengsifa.bean.voice;

import com.google.gson.annotations.SerializedName;
import com.liuhezhineng.ximengsifa.bean.base.Base;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/8/5
 */

public class VoiceInputBean implements Base {

	@SerializedName("net_asr")
	private List<NetAsr> netAsr;

	public List<NetAsr> getNetAsr() {
		if (netAsr == null) {
			return new ArrayList<>();
		}
		return netAsr;
	}

	public void setNetAsr(List<NetAsr> netAsr) {
		this.netAsr = netAsr;
	}
}

package com.liuhezhineng.ximengsifa.utils;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @date 2018/4/23
 */

public class QueryParamsUtils {

	private Map<String, String> mMap;

	public QueryParamsUtils() {
		mMap = new HashMap<>(16);
	}

	public QueryParamsUtils addParams(String key, String value) {
		mMap.put(key, value);
		return this;
	}

	@Override
	public String toString() {
		return new JSONObject(mMap).toString();
	}

}

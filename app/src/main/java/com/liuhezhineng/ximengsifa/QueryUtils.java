package com.liuhezhineng.ximengsifa;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * author : qingfeng
 * e-mail : 1913518036@qq.com
 * time   : 2019/01/19
 * desc   :
 * version: 1.0
 */
public class QueryUtils {
    Map<String, String> map;

    public QueryUtils() {
        map = new HashMap<>(16);
    }

    public QueryUtils params(String key, String value) {
        map.put(key, value);
        return this;
    }

    public String getQueryStr() {
        return new JSONObject(map).toString();
    }
}

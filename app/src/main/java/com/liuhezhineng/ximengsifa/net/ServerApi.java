//package com.liuhezhineng.ximengsifa.net;
//
//import com.liuhezhineng.ximengsifa.constant.Constant;
//import com.liuhezhineng.ximengsifa.constant.NetConstant;
//import com.liuhezhineng.ximengsifa.model.DialogStringCallBack;
//import com.lzy.okgo.OkGo;
//import com.lzy.okgo.cache.CacheMode;
//import com.lzy.okgo.convert.StringConvert;
//import com.lzy.okgo.model.HttpMethod;
//import com.lzy.okgo.model.HttpParams;
//import com.lzy.okgo.model.Response;
//
//import org.json.JSONObject;
//
//import java.util.HashMap;
//
//import io.reactivex.Observable;
//
///**
// * <pre>
// *     author : qingfeng
// *     e-mail : 1913518036@qq.com
// *     time   : 2018/11/28
// *     desc   :
// *     version: 1.0
// * </pre>
// */
//public class ServerApi {
//
//    public static Observable<String> getString(String account, String password) {
//        HashMap<String, String> paramsMap = new HashMap<>(16);
//        paramsMap.put(Constant.USERNAME, account);
//        paramsMap.put(Constant.PASSWORD, password);
//        String paramsJson = new JSONObject(paramsMap).toString();
//        HttpParams params = new HttpParams();
//        params.put(Constant.QUERY, paramsJson);
//        return RxUtils.request(HttpMethod.POST, NetConstant.LOGIN, String.class, params);
//    }
//}

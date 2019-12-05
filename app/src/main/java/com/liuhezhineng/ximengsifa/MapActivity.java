package com.liuhezhineng.ximengsifa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.utils.OpenLocalMapUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.OnClick;

public class MapActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.map_view)
    MapView mMapView;
    BaiduMap mBaiduMap;
    @BindView(R.id.tv_address)
    TextView mTvAddress;

    public LocationClient mLocationClient = null;
    String mAddressStr;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_map;
    }

    public static void actionStart(Context context, double lng, double lat, String address) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra("lng", lng);
        intent.putExtra("lat", lat);
        intent.putExtra("address", address);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(mTopBar, "地址地图");
        locateViaPoint();
        selfLocation();
    }

    @OnClick(R.id.tv_go)
    void goToAddress() {
        try {
            if (!OpenLocalMapUtil.isBaiduMapInstalled()) {
                ToastUtils.showLong("请先安装百度地图");
                return;
            }
            // 116.093615,43.932303 我用坐标拾取拾取到的
            // 116.093697,43.932341 盟局给的
            if (lng == 116.093615 && lat == 43.932303
                    && !TextUtils.isEmpty(mAddressStr)) {
                gotoBaiduMapViaAddress();
            } else {
                gotoMapViaPoint();
            }
        } catch (Exception e) {
            ToastUtils.showLong("暂时无法定位");
        }
    }

    private void gotoMapViaPoint() {
        Intent i1 = new Intent();
        // 反向地址解析
        i1.setData(Uri.parse("baidumap://map/geocoder?location=" + lat + "," + lng));
        startActivity(i1);
    }

    private void gotoBaiduMapViaAddress() {
        Intent i1 = new Intent();
        // 地址解析
        i1.setData(Uri.parse(
                "baidumap://map/geocoder?src=openApiDemo&address=" + mAddressStr));
        startActivity(i1);
    }

    double lng;
    double lat;

    private void locateViaPoint() {
        Intent intent = getIntent();
        lng = intent.getDoubleExtra("lng", 116.093615);
        lat = intent.getDoubleExtra("lat", 43.932303);
        mAddressStr = intent.getStringExtra("address");
        mTvAddress.setText(mAddressStr);

        mBaiduMap = mMapView.getMap();

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
//                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100)
//                .latitude(location.getLatitude())
//                .longitude(location.getLongitude())
                .latitude(lat)
                .longitude(lng)
                .build();

        // 设置定位数据
        mBaiduMap.setMyLocationData(locData);
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromAssetWithDpi("locate.png");
        MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;//定位跟随态
//        mCurrentMode = LocationMode.NORMAL;   //默认为 LocationMode.NORMAL 普通态
//        mCurrentMode = LocationMode.COMPASS;  //定位罗盘态
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, false, mCurrentMarker);
        mBaiduMap.setMyLocationConfiguration(config);
    }


    private void selfLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
                //以下只列举部分获取经纬度相关（常用）的结果信息
                //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

                double latitude = location.getLatitude();    //获取纬度信息
                double longitude = location.getLongitude();    //获取经度信息
                float radius = location.getRadius();    //获取定位精度，默认值为0.0f

                String coorType = location.getCoorType();
                //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

                int errorCode = location.getLocType();
                //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明

                //定义Maker坐标点
                LatLng point = new LatLng(latitude, longitude);

                //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.locate);

                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(point)
                        .icon(bitmap);

                //在地图上添加Marker，并显示
                mBaiduMap.addOverlay(option);
            }
        });
        //注册监听函数
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，设置定位模式，默认高精度
//LocationMode.Hight_Accuracy：高精度；
//LocationMode. Battery_Saving：低功耗；
//LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认GCJ02
//GCJ02：国测局坐标；
//BD09ll：百度经纬度坐标；
//BD09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标

        option.setScanSpan(1000 * 60 * 60);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
//可选，V7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位

        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.start();
//mLocationClient为第二步初始化过的LocationClient对象
//调用LocationClient的start()方法，便可发起定位请求
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
// 当不需要定位图层时关闭定位图层
        if (mBaiduMap != null) {
            mBaiduMap.setMyLocationEnabled(false);
        }
    }
}

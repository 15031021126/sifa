package com.liuhezhineng.ximengsifa.module.service.personnelinstitutions;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.hyphenate.easeui.EaseConstant;
import com.liuhezhineng.ximengsifa.MapActivity;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.PersonInsDetailsItemAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.PersonInsDetailsBean;
import com.liuhezhineng.ximengsifa.bean.personal.PersonInsBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.im.ChatActivity;
import com.liuhezhineng.ximengsifa.im.UnicodeUtils;
import com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint.MyConsultationActivity;
import com.liuhezhineng.ximengsifa.module.mine.order.ApplyAppointmentActivity;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.liuhezhineng.ximengsifa.vidyo.VideoRequestActivity;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 机构详情、人员详情
 */
public class PersonnelInstitutionsDetailsActivity extends BaseActivity {

    @BindView(R.id.ll_video_consultation)
    LinearLayout mLlVideoConsultation;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_online_qa)
    TextView mTvOnlineQa;
    @BindView(R.id.tv_advisory)
    TextView mTvAdvisory;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.map_view)
    MapView mMapView;
    @BindView(R.id.ll_person_operate)
    LinearLayout mLlPersonOperate;

    double lat;
    double lng;

    private BaiduMap mBaiduMap;
    private BitmapDescriptor mCurrentMarker;
    private MyLocationConfiguration.LocationMode mCurrentMode;

    private PersonInsBean mBean;
    private ArrayList<PersonInsDetailsBean> mList;
    private String name;
    private String categoryId;

    public static void actionStart(Context context,
                                   PersonInsBean bean, String name, String perInsFlag, String categoryId) {
        Intent intent = new Intent(context, PersonnelInstitutionsDetailsActivity.class);
        intent.putExtra(Constant.BEAN, bean);
        intent.putExtra(Constant.STRING, name);
        intent.putExtra("perInsFlag", perInsFlag);
        intent.putExtra("categoryId", categoryId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_institutions_details;
    }

    @Override
    protected void initView() {
        super.initView();

        name = getIntent().getStringExtra(Constant.STRING);
        String perInsFlag = getIntent().getStringExtra("perInsFlag");
        mBean = (PersonInsBean) getIntent().getSerializableExtra(Constant.BEAN);
        categoryId = getIntent().getStringExtra("categoryId");

        int placeholderImage;
        if ("0".equals(perInsFlag)) {
            initTopBar(R.string.personnel_details);
            mTvName.setText(mBean.getPersonName());
            placeholderImage = R.drawable.default_personnel_rect;
            mMapView.setVisibility(View.GONE);
        } else {
            initTopBar(R.string.institutions_details);
            mLlVideoConsultation.setVisibility(View.GONE);
            mTvName.setText(mBean.getAgencyName());
            placeholderImage = R.drawable.default_institution_rect;

            mLlPersonOperate.setVisibility(View.GONE);

            mBaiduMap = mMapView.getMap();
            if (ContextCompat.checkSelfPermission(this, Permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                initLocate();
            } else {
                AndPermission.with(this)
                        .runtime()
                        .permission(Permission.WRITE_EXTERNAL_STORAGE,
                                Permission.ACCESS_COARSE_LOCATION,
                                Permission.ACCESS_FINE_LOCATION,
                                Permission.READ_PHONE_STATE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                initLocate();
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                Toast.makeText(PersonnelInstitutionsDetailsActivity.this,
                                        "允许sd卡写权限，需写入地图数据，禁用后无法显示地图",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .start();
            }
        }
        Picasso.get().load(NetConstant.FILE_URL + mBean.getImageUrl())
                .placeholder(placeholderImage)
//                .transform(new CircleTransform())
//                .resize(80, 80)
//                .centerCrop(Gravity.TOP)
                .into(mIvAvatar);
        mTvPhone.setText(mBean.getAgencyPhone());
    }

    private void initLocate() {
        mMapView.showZoomControls(false);
        mMapView.showScaleControl(false);
//        mBaiduMap.setMaxAndMinZoomLevel(mBaiduMap.getMaxZoomLevel(), mBaiduMap.getMaxZoomLevel());
        mBaiduMap.setMaxAndMinZoomLevel(15, 15);
        UiSettings mUiSettings = mBaiduMap.getUiSettings();
        //实例化UiSettings类对象
        mUiSettings.setCompassEnabled(false);
        mUiSettings.setScrollGesturesEnabled(false);
        mUiSettings.setZoomGesturesEnabled(false);
        mUiSettings.setOverlookingGesturesEnabled(false);
        mUiSettings.setRotateGesturesEnabled(false);
        mUiSettings.setAllGesturesEnabled(false);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        try {
            String coordinate = mBean.getCoordinate();
            String[] point = coordinate.split(",");
            lat = Double.valueOf(point[1]);
            lng = Double.valueOf(point[0]);
        } catch (Exception e) {
            e.printStackTrace();
            // 提供的数据可能有问题，如果格式不对就默认定位到锡盟司法局
            // 116.093615,43.932303 我用坐标拾取拾取到的
            // 116.093697,43.932341 盟局给的
            lat = 43.932303;
            lng = 116.093615;
        }
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
//        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.locate);
        mCurrentMarker = BitmapDescriptorFactory.fromAssetWithDpi("locate.png");
        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;//定位跟随态
//        mCurrentMode = LocationMode.NORMAL;   //默认为 LocationMode.NORMAL 普通态
//        mCurrentMode = LocationMode.COMPASS;  //定位罗盘态
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, false, mCurrentMarker);
        mBaiduMap.setMyLocationConfiguration(config);
    }

    @Override
    protected void initData() {
        super.initData();

        mList = new ArrayList<>();
        addData();
        PersonInsDetailsItemAdapter adapter = new PersonInsDetailsItemAdapter(mActivity, mList);
        mRvList.setAdapter(adapter);
        mRvList.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
//                return super.canScrollVertically();
                return false;
            }
        });
        mRvList.addItemDecoration(
                new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void setListener() {
        super.setListener();
        if (mBaiduMap != null) {
            mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    MapActivity.actionStart(mActivity, lng, lat, mBean.getAgencyAddress());
                }

                @Override
                public boolean onMapPoiClick(MapPoi mapPoi) {
                    return false;
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        // 当不需要定位图层时关闭定位图层
        if (mBaiduMap != null) {
            mBaiduMap.setMyLocationEnabled(false);
        }
    }

    @Override
    @OnClick({R.id.tv_online_order, R.id.ll_video_consultation, R.id.tv_phone, R.id.tv_online_qa, R.id.tv_advisory})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.tv_online_order:
                ApplyAppointmentActivity.actionStart(mActivity, categoryId, mBean.getId());
                break;
            case R.id.ll_video_consultation:
                VideoRequestActivity.actionStart(mActivity, mBean.getPersonName(), getString(R.string.personnel_video_consultation), mBean.getId());
                break;
            case R.id.tv_phone:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mBean.getAgencyPhone()));
                startActivity(intent);
                break;
            case R.id.tv_online_qa:
                if (UserHelper.isIsLogin()) {
                    Intent i = new Intent(mActivity, ChatActivity.class);
                    i.putExtra(EaseConstant.EXTRA_USER_ID, UnicodeUtils.string2Unicode(mBean.getPersonName()) + "_" + mBean.getId());
                    i.putExtra("nickname", mBean.getPersonName());
                    startActivity(i);
                } else {
                    gotoLogin(mActivity);
                }
                break;
            case R.id.tv_advisory:
                MyConsultationActivity.actionStart(mActivity, mBean.getId());
                break;
            default:
                break;
        }
    }

    private void addData() {
        String[] keyArr;
        String[] valueArr;
        switch (name) {
            case "法援中心":
                keyArr = new String[]{"负责人", "所属地区",
                        "邮政编码", "人员数量",
                        "办公时间",
                        "机构地址",
                        "机构简介"};
                valueArr = new String[]{mBean.getPersonName(), mBean.getArea().getName(),
                        mBean.getZipCode(), mBean.getTeamSize(),
                        mBean.getWorktime(),
                        mBean.getAgencyAddress(),
                        mBean.getIntroduction()};
                break;
            case "司法所":
                keyArr = new String[]{"所长", "所属地区",
                        "邮政编码",
                        "机构地址",
                        "机构简介"};
                valueArr = new String[]{mBean.getPersonName(), mBean.getArea().getName(),
                        mBean.getZipCode(),
                        mBean.getAgencyAddress(),
                        mBean.getIntroduction()};
                break;
            case "人民调解委员会":
                keyArr = new String[]{"负责人", "所属地区",
                        "邮政编码",
                        "机构地址",
                        "成立时间",
                        "机构简介"};
                valueArr = new String[]{mBean.getPersonName(), mBean.getArea().getName(),
                        mBean.getZipCode(),
                        mBean.getAgencyAddress(),
                        mBean.getBirthday(),
                        mBean.getIntroduction()};
                break;
            case "基层法律服务所":
                keyArr = new String[]{"负责人", "所属地区",
                        "邮政编码", "机构证号", "组织形式",
                        "业务专长", "机构地址",
                        "基层法律服务所简介"};
                valueArr = new String[]{mBean.getPersonName(), mBean.getArea().getName(),
                        mBean.getZipCode(), mBean.getNo(), mBean.getLicenseForm(),
                        mBean.getBusinessExpertise(), mBean.getAgencyAddress(),
                        mBean.getIntroduction()};
                break;
            case "律师事务所":
                keyArr = new String[]{"负责人", "所属地区",
                        "执业证号", "执业年限", "主管机关",
                        "组织形式", "执业状态", "团队规模",
                        "传真号码", "电子邮箱", "邮政编码",
                        "机构地址",
                        "业务专长",
                        "机构简介"};
                valueArr = new String[]{mBean.getPersonName(), mBean.getArea().getName(),
                        mBean.getNo(), mBean.getPractisingYear(), mBean.getMainOrgans(),
                        mBean.getLicenseForm(), mBean.getStatus(), mBean.getTeamSize(),
                        mBean.getFax(), mBean.getEmail(), mBean.getZipCode(),
                        mBean.getAgencyAddress(),
                        mBean.getBusinessExpertise(),
                        mBean.getIntroduction()};
                break;
            case "公证机构":
                keyArr = new String[]{"负责人", "所属地区",
                        "执业证号", "服务时间", "业务专长",
                        "团队规模", "执业年限", "主管机关",
                        "传真号码", "机构地址", "邮政编码",
                        "机构简介"};
                valueArr = new String[]{mBean.getPersonName(), mBean.getArea().getName(),
                        mBean.getNo(), mBean.getWorktime(), mBean.getBusinessExpertise(),
                        mBean.getTeamSize(), mBean.getPractisingYear(), mBean.getMainOrgans(),
                        mBean.getFax(), mBean.getAgencyAddress(), mBean.getZipCode(),
                        mBean.getIntroduction()};
                break;
            case "司法鉴定机构":
                keyArr = new String[]{"负责人", "所属地区",
                        "许可证号", "邮政编码", "传真号码",
                        "团队规模", "业务范围",
                        "联系地址",
                        "机构简介"};
                valueArr = new String[]{mBean.getPersonName(), mBean.getArea().getName(),
                        mBean.getNo(), mBean.getZipCode(), mBean.getFax(),
                        mBean.getTeamSize(), mBean.getScopeOfBussess(),
                        mBean.getAgencyAddress(),
                        mBean.getIntroduction()};
                break;
            case "法援中心工作人员":
                keyArr = new String[]{"性别", "年龄", "学历", "民族", "政治面貌",
                        "执业证号", "所属地区", "蒙汉双通",
                        "执业机构",
                        "个人简介"};
                valueArr = new String[]{mBean.getSex(), mBean.getAge(), mBean.getEducation(),
                        mBean.getEthnic(), mBean.getPoliticalFace(),
                        mBean.getNo(), mBean.getArea().getName(), mBean.getIsMongolian(),
                        mBean.getAgencyName(),
                        mBean.getIntroduction()};
                break;
            case "人民调解员":
                keyArr = new String[]{"性别", "年龄", "学历", "民族", "政治面貌",
                        "执业年限", "所属地区", "调委会职务",
                        "执业机构",
                        "蒙汉双通",
                        "个人简介"};
                valueArr = new String[]{mBean.getSex(), mBean.getAge(), mBean.getEducation(),
                        mBean.getEthnic(), mBean.getPoliticalFace(),
                        mBean.getPractisingYear(), mBean.getArea().getName(), mBean.getRoleId(),
                        mBean.getAgencyName(),
                        mBean.getIsMongolian(),
                        mBean.getIntroduction()};
                break;
            case "基层法律服务工作者":
                keyArr = new String[]{"性别", "年龄", "学历", "民族", "政治面貌",
                        "执业证号",
                        "执业机构",
                        "所属地区", "蒙汉双通",
                        "个人简介"};
                valueArr = new String[]{mBean.getSex(), mBean.getAge(), mBean.getEducation(),
                        mBean.getEthnic(), mBean.getPoliticalFace(),
                        mBean.getNo(),
                        mBean.getAgencyName(),
                        mBean.getArea().getName(), mBean.getIsMongolian(),
                        mBean.getIntroduction()};
                break;
            case "人民监督员":
                keyArr = new String[]{"性别", "年龄", "学历", "民族", "政治面貌",
                        "所属地区", "执业机构", "蒙汉双通",
                        "个人简介"};
                valueArr = new String[]{mBean.getSex(), mBean.getAge(), mBean.getEducation(),
                        mBean.getEthnic(), mBean.getPoliticalFace(),
                        mBean.getArea().getName(), mBean.getAgencyName(), mBean.getIsMongolian(),
                        mBean.getIntroduction()};
                break;
            case "司法所工作人员":
                keyArr = new String[]{"性别", "年龄", "学历", "民族", "政治面貌",
                        "职务", "执业机构", "所属地区",
                        "蒙汉双通",
                        "机构地址",
                        "个人简介"};
                valueArr = new String[]{mBean.getSex(), mBean.getAge(), mBean.getEducation(),
                        mBean.getEthnic(), mBean.getPoliticalFace(),
                        mBean.getRoleId(), mBean.getAgencyName(), mBean.getArea().getName(),
                        mBean.getIsMongolian(),
                        mBean.getAgencyAddress(),
                        mBean.getIntroduction()};
                break;
            case "律师":
                keyArr = new String[]{"性别", "年龄", "学历", "民族", "政治面貌",
                        "执业证号", "执业机构", "职业类别",
                        "职业状态", "蒙汉双通", "法援律师",
                        "所属地区",
                        "联系地址",
                        "业务专长",
                        "主管机关",
                        "个人简介"};
                valueArr = new String[]{mBean.getSex(), mBean.getAge(), mBean.getEducation(),
                        mBean.getEthnic(), mBean.getPoliticalFace(),
                        mBean.getNo(), mBean.getAgencyName(), mBean.getType(),
                        mBean.getStatus(), mBean.getIsMongolian(), mBean.getIsAidLawyer(),
                        mBean.getArea().getName(),
                        mBean.getAgencyAddress(),
                        mBean.getBusinessExpertise(),
                        mBean.getMainOrgans(),
                        mBean.getIntroduction()};
                break;
            case "公证员":
                keyArr = new String[]{"性别", "年龄", "学历", "民族", "政治面貌",
                        "执业机构", "业务专长",
                        "执业证号", "蒙汉双通",
                        "联系地址",
                        "个人简介"};
                valueArr = new String[]{mBean.getSex(), mBean.getAge(), mBean.getEducation(),
                        mBean.getEthnic(), mBean.getPoliticalFace(),
                        mBean.getAgencyName(), mBean.getBusinessExpertise(),
                        mBean.getNo(), mBean.getIsMongolian(),
                        mBean.getAgencyAddress(),
                        mBean.getIntroduction()};
                break;
            case "司法鉴定人员":
                keyArr = new String[]{"性别", "年龄", "学历", "民族", "政治面貌",
                        "执业证号", "执业范围",
                        "蒙汉双通", "执业年限",
                        "执业机构",
                        "联系地址",
                        "个人简介"};
                valueArr = new String[]{mBean.getSex(), mBean.getAge(), mBean.getEducation(),
                        mBean.getEthnic(), mBean.getPoliticalFace(),
                        mBean.getNo(), mBean.getScopeOfBussess(),
                        mBean.getIsMongolian(), mBean.getPractisingYear(),
                        mBean.getAgencyName(),
                        mBean.getAgencyAddress(),
                        mBean.getIntroduction()};
                break;
            default:
                keyArr = new String[]{};
                valueArr = new String[]{};
                break;
        }
        for (int i = 0; i < keyArr.length; i++) {
            mList.add(new PersonInsDetailsBean(keyArr[i], valueArr[i]));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
}

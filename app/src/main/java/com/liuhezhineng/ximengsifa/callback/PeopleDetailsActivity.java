package com.liuhezhineng.ximengsifa.callback;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.MapView;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.callback.bean.PeopleBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lishangnan on 2019/11/12.
 */

public class PeopleDetailsActivity extends BaseActivity {


    @BindView(R.id.top_bar)
    QMUITopBar topBar;
    @BindView(R.id.ll_video_consultation)
    LinearLayout llVideoConsultation;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_online_order)
    TextView tvOnlineOrder;
    @BindView(R.id.tv_online_qa)
    TextView tvOnlineQa;
    @BindView(R.id.tv_advisory)
    TextView tvAdvisory;
    @BindView(R.id.ll_person_operate)
    LinearLayout llPersonOperate;
    @BindView(R.id.tv_arrdes)
    TextView tvArrdes;
    @BindView(R.id.tv_town)
    TextView tvTown;
    @BindView(R.id.tv_ethnic)
    TextView tvEthnic;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_politicalFace)
    TextView tvPoliticalFace;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.map_view)
    MapView mapView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_people_details;
    }

    private String id;

    @Override
    protected void initView() {
        super.initView();
        id = getIntent().getStringExtra("kay");
        initTopBar(topBar, "执法人员详情");
        initPeople();
    }

    private void initPeople() {
        Map<String, String> map = new HashMap<>();
        map.put("categoryId", "18");
        map.put("id", id);
        String queryStr = new JSONObject(map).toString();

        OkGo.<PeopleBean>get(NetConstant.POST_PEOPLE).params("query", queryStr).execute(new JsonCallback2<PeopleBean>() {
            @Override
            public void onSuccess(Response<PeopleBean> response) {
                if (response != null) {
                    PeopleBean.BodyBean body = response.body().getBody();
                    Picasso.get().load(NetConstant.BASE_URL + body.getImageUrl()).into(ivAvatar);
                    tvName.setText(body.getPersonName());
                    tvEthnic.setText(body.getEthnic());
                    tvSex.setText(body.getSex());
                    tvPoliticalFace.setText(body.getPoliticalFace());
                    tvEmail.setText(body.getEmail());
                    tvType.setText(body.getType());
                    tvPhone.setText(body.getAgencyPhone());
                    tvArrdes.setText(body.getArea().getName());
                    tvTown.setText(body.getTown().getName());
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}

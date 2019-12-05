package com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.PeoplesMediationData;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import butterknife.BindView;

/**
 * @author AIqinfeng
 * @description 人民调解员审核，司法局指定承办人，申请人申请，修改
 * 大众提交申请的表单
 */
public class RequestActivity extends BaseHaveDoneActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.tv_ethnic)
    TextView mTvEthnic;
    @BindView(R.id.tv_county)
    TextView mTvCounty;
    @BindView(R.id.tv_town)
    TextView mTvTown;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;
    @BindView(R.id.tv_id_card_num)
    TextView mTvIdCardNum;
    @BindView(R.id.tv_occupation)
    TextView mTvOccupation;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_domicile)
    TextView mTvDomicile;
    @BindView(R.id.tv_name_1)
    TextView mTvName1;
    @BindView(R.id.tv_sex_1)
    TextView mTvSex1;
    @BindView(R.id.tv_ethnic_1)
    TextView mTvEthnic1;
    @BindView(R.id.tv_county_1)
    TextView mTvCounty1;
    @BindView(R.id.tv_town_1)
    TextView mTvTown1;
    @BindView(R.id.tv_phone_num_1)
    TextView mTvPhoneNum1;
    @BindView(R.id.tv_birthday_1)
    TextView mTvBirthday1;
    @BindView(R.id.tv_id_card_num_1)
    TextView mTvIdCardNum1;
    @BindView(R.id.tv_occupation_1)
    TextView mTvOccupation1;
    @BindView(R.id.tv_address_1)
    TextView mTvAddress1;
    @BindView(R.id.tv_domicile_1)
    TextView mTvDomicile1;
    @BindView(R.id.tv_case_title)
    TextView mTvCaseTitle;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.tv_peoples_mediation_committee)
    TextView mTvPeoplesMediationCommittee;
    @BindView(R.id.tv_peoples_mediator)
    TextView mTvPeoplesMediator;
    @BindView(R.id.tv_agent_name)
    TextView mTvAgentName;
    @BindView(R.id.tv_agent_id_card)
    TextView mTvAgentIdCard;
    @BindView(R.id.tv_agent_type)
    TextView mTvAgentType;

    public static void actionStart(Context activity, BusinessBean item) {
        Class<RequestActivity> clazz = RequestActivity.class;
        Intent intent = new Intent(activity, clazz);
        intent.putExtra(Constant.BUSINESS, item);
        activity.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(mTopBar, "人民调解申请表");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_request;
    }

    @Override
    protected void parseData(JSONObject data) throws Exception {
        PeoplesMediationData bean = new Gson()
                .fromJson(data.toString(), PeoplesMediationData.class);
        mTvAgentName.setText(bean.getProxyName());
        mTvAgentIdCard.setText(bean.getProxyIdCard());
        if (!TextUtils.isEmpty(bean.getProxyType())) {
            mTvAgentType.setText("工作人员");
        }

        mTvName.setText(bean.getAccuserName());
        mTvName1.setText(bean.getDefendantName());
        mTvSex.setText(bean.getAccuserSexDesc());
        mTvSex1.setText(bean.getDefendantSexDesc());
        mTvEthnic.setText(bean.getAccuserEthnicDesc());
        mTvEthnic1.setText(bean.getDefendantEthnicDesc());
        if (bean.getAccuserCounty() != null) {
            mTvCounty.setText(bean.getAccuserCounty().getName());
        }
        if (bean.getDefendantCounty() != null) {
            mTvCounty1.setText(bean.getDefendantCounty().getName());
        }
        if (bean.getAccuserTown() != null) {
            mTvTown.setText(bean.getAccuserTown().getName());
        }
        if (bean.getDefendantTown() != null) {
            mTvTown1.setText(bean.getDefendantTown().getName());
        }
        mTvPhone.setText(bean.getAccuserPhone());
        mTvPhoneNum1.setText(bean.getDefendantPhone());
        mTvBirthday.setText(bean.getAccuserBirthday());
        mTvBirthday1.setText(bean.getDefendantBirthday());
        mTvIdCardNum.setText(bean.getAccuserIdcard());
        mTvIdCardNum1.setText(bean.getDefendantIdcard());
        mTvOccupation.setText(bean.getAccuserOccupation());
        mTvOccupation1.setText(bean.getDefendantOccupation());
        mTvAddress.setText(bean.getAccuserAddress());
        mTvAddress1.setText(bean.getDefendantAddress());
        mTvDomicile.setText(bean.getAccuserDomicile());
        mTvDomicile1.setText(bean.getDefendantDomicile());
        mTvCaseTitle.setText(bean.getCaseTitle());
        mTvContent.setText(bean.getCaseSituation());
        if (bean.getPeopleMediationCommittee() != null) {
            mTvPeoplesMediationCommittee.setText(bean.getPeopleMediationCommittee().getName());
        }
        if (bean.getMediator() != null) {
            mTvPeoplesMediator.setText(bean.getMediator().getName());
        }
    }
}

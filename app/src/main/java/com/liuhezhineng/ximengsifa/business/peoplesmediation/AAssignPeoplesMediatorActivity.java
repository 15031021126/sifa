package com.liuhezhineng.ximengsifa.business.peoplesmediation;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.InsUserBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Committee;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.PeoplesMediationData;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form.MediationWorkflow;
import com.liuhezhineng.ximengsifa.business.base.BasePeoplesMediationActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.Business;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 指定人民调解员 所长
 */
public class AAssignPeoplesMediatorActivity extends BasePeoplesMediationActivity {

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
    @BindView(R.id.btn_yes)
    Button mBtnYes;
    @BindView(R.id.tv_dispute_type)
    TextView mTvDisputeType;
    @BindView(R.id.tv_case_county)
    TextView mTvCaseCounty;
    @BindView(R.id.tv_case_town)
    TextView mTvCaseTown;
    @BindView(R.id.tv_allow_timeout)
    TextView mTvAllowTimeout;
    @BindView(R.id.tv_timeout_date)
    TextView mTvTimeoutDate;

    private Office mCommittee = new Office();
    private Office mMediator = new Office();

    public static void actionStart(Context activity, BusinessBean item) {
        Intent intent = new Intent(activity, AAssignPeoplesMediatorActivity.class);
        intent.putExtra(Constant.BUSINESS, item);
        ((BaseActivity) activity).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_assign_peoples_mediator;
    }

    @Override
    protected void initData() {
        super.initData();
        mBean = new MediationWorkflow();
        loadBusinessDetails();

        initWarningStateTypePicker((option1, option2, option3, v) -> {
            TypeBean typeBean = warningStateTypeList.get(option1);
            mTvAllowTimeout.setText(typeBean.getLabel());
            mBean.setWarningState(typeBean.getValue());
        });
        initWarningTimeTypePicker((options1, options2, options3, v) -> {
            TypeBean typeBean = warningTimeTypeList.get(options1);
            mTvTimeoutDate.setText(typeBean.getLabel());
            mBean.setWarningTime(typeBean.getValue());
        });
    }

    @Override
    protected void parseData(JSONObject data) throws Exception {
        mData = new Gson().fromJson(data.toString(), PeoplesMediationData.class);

        // 显示已有数据
        setFormText();

        if (mData.getCaseTown() != null && !TextUtils.isEmpty(mData.getCaseTown().getId())) {
            // 提前加载调解机构数据
            loadCommittee();
        }

        mCommittee = mData.getPeopleMediationCommittee();
        if (!TextUtils.isEmpty(mCommittee.getId())) {
            loadMediatorData(mCommittee.getId(),
                    mData.getCaseCounty().getId(),
                    mData.getCaseTown().getId());
        }

        setRequestInfo(mData);
    }

    private void setFormText() {
        mTvName.setText(mData.getAccuserName());
        mTvName1.setText(mData.getDefendantName());
        mTvSex.setText(mData.getAccuserSexDesc());
        mTvSex1.setText(mData.getDefendantSexDesc());
        mTvEthnic.setText(mData.getAccuserEthnicDesc());
        mTvEthnic1.setText(mData.getDefendantEthnicDesc());
        mTvCounty.setText(mData.getAccuserCounty().getName());
        mTvCounty1.setText(mData.getDefendantCounty().getName());
        mTvTown.setText(mData.getAccuserTown().getName());
        mTvTown1.setText(mData.getDefendantTown().getName());
        mTvPhone.setText(mData.getAccuserPhone());
        mTvPhoneNum1.setText(mData.getDefendantPhone());
        mTvBirthday.setText(mData.getAccuserBirthday());
        mTvBirthday1.setText(mData.getDefendantBirthday());
        mTvIdCardNum.setText(mData.getAccuserIdcard());
        mTvIdCardNum1.setText(mData.getDefendantIdcard());
        mTvOccupation.setText(mData.getAccuserOccupation());
        mTvOccupation1.setText(mData.getDefendantOccupation());
        mTvAddress.setText(mData.getAccuserAddress());
        mTvAddress1.setText(mData.getDefendantAddress());
        mTvDomicile.setText(mData.getAccuserDomicile());
        mTvDomicile1.setText(mData.getDefendantDomicile());
        mTvCaseTitle.setText(mData.getCaseTitle());
        mTvContent.setText(mData.getCaseSituation());
        mTvPeoplesMediationCommittee.setText(mData.getPeopleMediationCommittee().getName());
        mTvPeoplesMediator.setText(mData.getMediator().getName());
        mTvDisputeType.setText(mData.getCaseTypeDesc());
        mTvCaseCounty.setText(mData.getCaseCounty().getName());
        mTvCaseTown.setText(mData.getCaseTown().getName());
        mTvAllowTimeout.setText(mData.getWarningStateDesc());
        mTvTimeoutDate.setText(mData.getWarningTimeDesc());
    }

    private void loadCommittee() {
        initCommitteePickerView((options1, options2, options3, v) -> {
            if (options1 < 0) {
                ToastUtils.showLong("数据加载中，请等待数据加载完成");
                return;
            }
            Committee committee = committeeList.get(options1);
            if (Constant.PICKER_NO_DATA.equals(committee.getId())) {
                ToastUtils.showLong("该地区暂无人民调解委员会");
                return;
            }
            mCommittee.setId(committee.getId());
            mCommittee.setName(committee.getAgencyName());
            mBean.setPeopleMediationCommittee(mCommittee);

            mTvPeoplesMediationCommittee.setText(committee.getAgencyName());

            // 重置调解员
            mTvPeoplesMediator.setText("");
            mMediator = new Office();
            mBean.setMediator(mMediator);

            loadMediatorData(committee.getId(), mData.getCaseCounty().getId(),
                    mData.getCaseTown().getId());
        }, mData.getCaseCounty().getId(), mData.getCaseTown().getId());
    }


    private void loadMediatorData(String committeeId, String countyId, String townId) {

        initPeoplePicker((options1, options2, options3, v) -> {
            if (options1 < 0) {
                ToastUtils.showLong("请等待数据加载完成");
                return;
            }
            InsUserBean users = peopleList.get(options1);
            if (Constant.PICKER_NO_DATA.equals(users.getId())) {
                ToastUtils.showLong("该调委会暂无调解员");
                return;
            }
            mMediator.setId(users.getId());
            mMediator.setName(users.getName());
            mTvPeoplesMediator.setText(users.getName());
            mBean.setMediator(mMediator);
        }, Business.PEOPLE_MEDIATION, committeeId, countyId, townId);
    }

    @Override
    protected void createFrom() {

    }

    @Override
    protected void initView() {
        super.initView();
        findViewById(R.id.tv_file).setVisibility(View.GONE);
    }

    @Override
    @OnClick({R.id.tv_peoples_mediation_committee, R.id.tv_peoples_mediator,
            R.id.btn_yes})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.tv_peoples_mediation_committee:
                showPickerView(committeePickerView);
                break;
            case R.id.tv_peoples_mediator:
                if (!TextUtils.isEmpty(mCommittee.getId())) {
                    showPickerView(peoplePicker);
                } else {
                    ToastUtils.showLong("请先选择人民调解委员会");
                }
                break;
            case R.id.btn_yes:
                if (checkInput()) {
                    commitRequest1(NetConstant.REVIEW);
                }
                break;
            default:
                break;
        }
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(mBean.getMediator().getName())) {
            ToastUtils.showLong("请指定调解员");
            return false;
        } else {
            return true;
        }
    }
}

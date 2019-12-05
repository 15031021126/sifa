package com.liuhezhineng.ximengsifa.business.peoplesmediation;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.PeoplesMediationData;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form.RegisterFromBean;
import com.liuhezhineng.ximengsifa.business.base.BasePeoplesMediationActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 人民调解受理登记表
 */
public class CRegisterFromActivity extends BasePeoplesMediationActivity {

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
	@BindView(R.id.tv_case_from)
	TextView mTvCaseFrom;
	@BindView(R.id.tv_involving_mongolian)
	TextView mTvInvolvingMongolian;
	@BindView(R.id.tv_case_important_level)
	TextView mTvCaseImportantLevel;
	@BindView(R.id.tv_case_date)
	TextView mTvCaseDate;
	@BindView(R.id.tv_case_flag_county)
	TextView mTvCaseFlagCounty;
	@BindView(R.id.tv_case_township)
	TextView mTvCaseTownship;
	@BindView(R.id.et_case_address)
	EditText mEtCaseAddress;
	@BindView(R.id.et_case_involving_people_count)
	EditText mEtCaseInvolvingPeopleCount;
	@BindView(R.id.tv_dispute_type)
	TextView mTvDisputeType;
	@BindView(R.id.et_case_title)
	EditText mEtCaseTitle;
	@BindView(R.id.et_content)
	EditText mEtContent;
	@BindView(R.id.tv_peoples_mediator_committee)
	TextView mTvPeoplesMediatorCommittee;
	@BindView(R.id.tv_peoples_mediator)
	TextView mTvPeoplesMediator;

    @BindView(R.id.tv_agent_name)
    TextView mTvAgentName;
    @BindView(R.id.tv_agent_id_card)
    TextView mTvAgentIdCard;
    @BindView(R.id.tv_agent_type)
    TextView mTvAgentType;

	RegisterFromBean bean;

	private Area county = new Area();
	private String caseSource;
	private String hasMongol;
	private String caseRank;
	private Area caseCounty = new Area();
	private Area caseTown = new Area();
	private String caseTime;
	private String caseArea;
	private String caseInvolveCount;
	private String caseType;
	private String disputeSituation;
	private String caseTitle;

	public static void actionStart(Context context, BusinessBean bean) {
		Intent intent = new Intent(context, CRegisterFromActivity.class);
		intent.putExtra(Constant.BUSINESS, bean);
		((BaseActivity) context).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);

	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_register_from;
	}

	@Override
	protected void initData() {
		super.initData();
		mBean = new RegisterFromBean();
		bean = (RegisterFromBean) mBean;
		loadBusinessDetails();
		initPickerView();
	}

	private void initPickerView() {
		initBirthdayPicker(new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				mTvCaseDate.setText(getTime(date));
				bean.setCaseTime(getTime(date));
			}
		});

		initMongolPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvInvolvingMongolian.setText(mongolList.get(options1));
				bean.setHasMongol("是".equals(mongolList.get(options1)) ? "1" : "0");
			}
		});

		initCaseResourcePickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvCaseFrom.setText(caseResourceList.get(options1).getLabel());
				bean.setCaseSource(caseResourceList.get(options1).getValue());
			}
		});

		initCaseRankView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvCaseImportantLevel.setText(caseRankList.get(options1).getLabel());
				bean.setCaseRank(caseRankList.get(options1).getValue());
			}
		});

		initDisputeTypePickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvDisputeType.setText(disputeTypeList.get(options1).getLabel());
				bean.setCaseType(disputeTypeList.get(options1).getValue());
			}
		});

		initCountyPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				Area countyBean = countyList.get(options1);
				getTownInfo(countyBean.getId());
				mTvCaseFlagCounty.setText(countyBean.getName());
				county.setName(countyBean.getName());
				county.setId(countyBean.getId());
				bean.setCaseCounty(county);
				mTvCaseTownship.setText("");
				bean.setAccuserTown(new Area());
			}
		});
	}

	private void getTownInfo(String countyId) {
		initTownPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3,
				View v) {
				if (options1 < 0) {
					ToastUtils.showLong("请等待数据加载完成");
					return;
				}
				Area townBean = townList.get(options1);
				mTvCaseTownship.setText(townBean.getName());
				Area town = new Area();
				town.setName(townBean.getName());
				town.setId(townBean.getId());
				bean.setCaseTown(town);
			}
		}, countyId);
	}

	@Override
	protected void parseData(JSONObject data) {
		try {
			procInsId = data.getString("id");
			JSONObject apply = data.getJSONObject("oaPeopleMediationApply");

			mData = new Gson().fromJson(
			        apply.toString(),
                    PeoplesMediationData.class);
			mTvAgentName.setText(mData.getProxyName());
			mTvAgentIdCard.setText(mData.getProxyIdCard());
            if (!TextUtils.isEmpty(mData.getProxyType())) {
                mTvAgentType.setText("工作人员");
            }

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
			mEtCaseTitle.setText(mData.getCaseTitle());
			mTvPeoplesMediator.setText(mData.getMediator().getName());
			mTvPeoplesMediatorCommittee.setText(mData.getPeopleMediationCommittee().getName());
			mTvDisputeType.setText(mData.getCaseTypeDesc());
			mTvCaseFlagCounty.setText(mData.getCaseCounty().getName());
			mTvCaseTownship.setText(mData.getCaseTown().getName());

			county = mData.getCaseCounty();
			if (county != null && !TextUtils.isEmpty(county.getId())) {
				getTownInfo(county.getId());
			}

			setRequestInfo(mData);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void createFrom() {
		super.createFrom();
		RegisterFromBean bean = (RegisterFromBean) mBean;
		bean.setCaseInvolveCount(caseInvolveCount);
		bean.setCaseArea(caseArea);
		bean.setDisputeSituation(disputeSituation);
		bean.setCaseTitle(caseTitle);
	}

	@Override
	@OnClick({R.id.tv_case_from, R.id.tv_involving_mongolian, R.id.tv_case_important_level,
		R.id.tv_case_date, R.id.tv_case_flag_county, R.id.tv_case_township, R.id.tv_dispute_type,
		R.id.btn_yes})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		switch (view.getId()) {
			case R.id.tv_case_from:
				showPickerView(caseResourcePickerView);
				break;
			case R.id.tv_involving_mongolian:
				showPickerView(mongolPickerView);
				break;
			case R.id.tv_case_important_level:
				showPickerView(caseRankPickerView);
				break;
			case R.id.tv_case_date:
				birthdayPickerView.show();
				break;
			case R.id.tv_case_flag_county:
				showPickerView(countyPickerView);
				break;
			case R.id.tv_case_township:
				if (!TextUtils.isEmpty(county.getId())) {
					showPickerView(townPickerView);
				} else {
					ToastUtils.showLong("请先选择旗县");
				}
				break;
			case R.id.tv_dispute_type:
				showPickerView(disputeTypePickerView);
				break;
			case R.id.btn_yes:
				if (checkInput()) {
					commitRequest1(NetConstant.REGISTER_FROM);
				}
				break;
			default:
				break;
		}
	}

	private boolean checkInput() {

		caseTitle = mEtCaseTitle.getText().toString().trim();
		caseSource = getText(mTvCaseFrom);
		hasMongol = getText(mTvInvolvingMongolian);
		caseRank = getText(mTvCaseImportantLevel);
		caseTime = getText(mTvCaseDate);
		caseArea = getText(mEtCaseAddress);
		caseInvolveCount = getText(mEtCaseInvolvingPeopleCount);
		caseType = getText(mTvDisputeType);
		disputeSituation = getText(mEtContent);

		if (isEmpty(caseSource)) {
			ToastUtils.showLong("案件来源不能为空");
			return false;
		} else if (isEmpty(hasMongol)) {
			ToastUtils.showLong("是否涉及蒙语不能为空");
			return false;
		} else if (isEmpty(caseRank)) {
			ToastUtils.showLong("案件重要等级不能为空");
			return false;
		} else if (bean.getCaseCounty() != null && isEmpty(bean.getCaseCounty().getName())) {
			ToastUtils.showLong("案件发生旗县不能为空");
			return false;
		} else if (bean.getCaseTown() != null && isEmpty(bean.getCaseTown().getName())) {
			ToastUtils.showLong("案件发生乡镇不能为空");
			return false;
		} else if (isEmpty(caseArea)) {
			ToastUtils.showLong("案件发生详细地址不能为空");
			return false;
		} else if (isEmpty(caseInvolveCount)) {
			ToastUtils.showLong("涉及人员数量不能为空");
			return false;
		} else if (isEmpty(caseType)) {
			ToastUtils.showLong("设计纠纷类型不能为空");
			return false;
		} else if (isEmpty(disputeSituation)) {
			ToastUtils.showLong("记录内容不能为空");
			return false;
		} else {
			return true;
		}
	}
}

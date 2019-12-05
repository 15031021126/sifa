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

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * @author AIqinfeng
 * @description 已办登记注册
 */
public class HaveDoneRegisterActivity extends BaseHaveDoneActivity {

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
	@BindView(R.id.tv_case_address)
	TextView mTvCaseAddress;
	@BindView(R.id.tv_case_involving_people_count)
	TextView mTvCaseInvolvingPeopleCount;
	@BindView(R.id.tv_dispute_type)
	TextView mTvDisputeType;
	@BindView(R.id.tv_case_title)
	TextView mTvCaseTitle;
	@BindView(R.id.tv_content)
	TextView mTvContent;
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

	public static void actionStart(Context activity, BusinessBean item) {
		Intent intent = new Intent(activity, HaveDoneRegisterActivity.class);
		intent.putExtra(Constant.BUSINESS, item);
		activity.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_have_done_register;
	}

	@Override
	protected void parseData(JSONObject data) {
		try {
			mTvCaseFrom.setText(data.getString("caseSourceDesc"));
			mTvInvolvingMongolian.setText(data.getString("hasMongolDesc"));
			mTvCaseImportantLevel.setText(data.getString("caseRankDesc"));
			mTvCaseDate.setText(data.getString("caseTime"));
			mTvCaseAddress.setText(data.getString("caseArea"));

			mTvCaseInvolvingPeopleCount.setText(data.getString("caseInvolveCount"));
			mTvContent.setText(data.getString("disputeSituation"));

			JSONObject apply = data.getJSONObject("oaPeopleMediationApply");

			Gson gson = new Gson();
			PeoplesMediationData mediationData = gson
				.fromJson(apply.toString(), PeoplesMediationData.class);
			mTvAgentName.setText(mediationData.getProxyName());
			mTvAgentIdCard.setText(mediationData.getProxyIdCard());
            if (!TextUtils.isEmpty(mediationData.getProxyType())) {
                mTvAgentType.setText("工作人员");
            }

			mTvDisputeType.setText(mediationData.getCaseTypeDesc());
			mTvCaseFlagCounty.setText(mediationData.getCaseCounty().getName());
			mTvCaseTownship.setText(mediationData.getCaseTown().getName());
			mTvPeoplesMediatorCommittee
				.setText(mediationData.getPeopleMediationCommittee().getName());
			mTvPeoplesMediator.setText(mediationData.getMediator().getName());
			mTvName.setText(mediationData.getAccuserName());
			mTvName1.setText(mediationData.getDefendantName());
			mTvSex.setText(mediationData.getAccuserSexDesc());
			mTvSex1.setText(mediationData.getDefendantSexDesc());
			mTvEthnic.setText(mediationData.getAccuserEthnicDesc());
			mTvEthnic1.setText(mediationData.getDefendantEthnicDesc());
			mTvCounty.setText(mediationData.getAccuserCounty().getName());
			mTvCounty1.setText(mediationData.getDefendantCounty().getName());
			mTvTown.setText(mediationData.getAccuserTown().getName());
			mTvTown1.setText(mediationData.getDefendantTown().getName());
			mTvPhone.setText(mediationData.getAccuserPhone());
			mTvPhoneNum1.setText(mediationData.getDefendantPhone());
			mTvBirthday.setText(mediationData.getAccuserBirthday());
			mTvBirthday1.setText(mediationData.getDefendantBirthday());
			mTvIdCardNum.setText(mediationData.getAccuserIdcard());
			mTvIdCardNum1.setText(mediationData.getDefendantIdcard());
			mTvOccupation.setText(mediationData.getAccuserOccupation());
			mTvOccupation1.setText(mediationData.getDefendantOccupation());
			mTvAddress.setText(mediationData.getAccuserAddress());
			mTvAddress1.setText(mediationData.getDefendantAddress());
			mTvDomicile.setText(mediationData.getAccuserDomicile());
			mTvDomicile1.setText(mediationData.getDefendantDomicile());
			mTvCaseTitle.setText(mediationData.getCaseTitle());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}

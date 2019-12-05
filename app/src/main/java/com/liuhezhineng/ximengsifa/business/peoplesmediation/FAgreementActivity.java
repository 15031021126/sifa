package com.liuhezhineng.ximengsifa.business.peoplesmediation;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.PeoplesMediationData;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form.AgreementFromBean;
import com.liuhezhineng.ximengsifa.business.base.BasePeoplesMediationActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 人名调解协议书
 */
public class FAgreementActivity extends BasePeoplesMediationActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.tv_code)
	TextView mTvCode;
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
	@BindView(R.id.tv_title)
	TextView mTvTitle;
	@BindView(R.id.tv_content)
	TextView mTvContent;
	@BindView(R.id.et_dispute_face)
	EditText mEtDisputeFace;
	@BindView(R.id.et_dispute_matter)
	EditText mEtDisputeMatter;
	@BindView(R.id.et_agreemen_content)
	EditText mEtAgreemenContent;
	@BindView(R.id.et_perform_mode)
	EditText mEtPerformMode;
	@BindView(R.id.tv_time_limit)
	TextView mTvTimeLimit;
	@BindView(R.id.tv_peoples_mediator_committee)
	TextView mTvPeoplesMediatorCommittee;
	@BindView(R.id.tv_peoples_mediator)
	TextView mTvPeoplesMediator;
	@BindView(R.id.et_recorder)
	EditText mEtRecorder;

	String agreementCode;
	String disputeFact;
	String disputeMatter;
	String agreementContent;
	String performMode;
	String timeLimit;
	String recorder;

	public static void actionStart(Context context, BusinessBean item) {
		Intent intent = new Intent(context, FAgreementActivity.class);
		intent.putExtra(Constant.BUSINESS, item);
		((BaseActivity) context).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_agreement;
	}

	@Override
	protected void initData() {
		super.initData();
		mBean = new AgreementFromBean();
		loadBusinessDetails();
		initBirthdayPicker(new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				mTvTimeLimit.setText(getTime(date));
			}
		});
	}

	@Override
	protected void parseData(JSONObject data) throws Exception {
		mTvCode.setText(data.getString("agreementCode"));

		JSONObject apply = data.getJSONObject("oaPeopleMediationApply");
		mData = getIgnoreGson("createBy").fromJson(apply.toString(), PeoplesMediationData.class);

		mTvPeoplesMediatorCommittee
			.setText(mData.getPeopleMediationCommittee().getName());
		mTvPeoplesMediator.setText(mData.getMediator().getName());
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
		mTvPeoplesMediator.setText(mData.getMediator().getName());
		mTvTitle.setText(mData.getCaseTitle());
		mTvContent.setText(mData.getCaseSituation());
		mEtRecorder.setText(mData.getMediator().getName());

		setRequestInfo(mData);
	}

	@Override
	protected void createFrom() {
		AgreementFromBean bean = (AgreementFromBean) mBean;
		bean.setAgreementCode(agreementCode);
		bean.setAgreementContent(agreementContent);
		bean.setDisputeFact(disputeFact);
		bean.setPerformMode(performMode);
		bean.setTimeLimit(timeLimit);
		bean.setDisputeMatter(disputeMatter);
		bean.setRecorder(getText(mEtRecorder));
	}

	@Override
	@OnClick({R.id.tv_time_limit,R.id.btn_yes})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		switch (view.getId()) {
			case R.id.tv_time_limit:
				birthdayPickerView.show();
				break;
			case R.id.btn_yes:
				if (checkInput()) {
					commitRequest1(NetConstant.AGREEMENT_FORM);
				}
				break;
			default:
				break;
		}
	}

	private boolean checkInput() {
		agreementCode = getText(mTvCode);
		disputeFact = getText(mEtDisputeFace);
		disputeMatter = getText(mEtDisputeMatter);
		agreementContent = getText(mEtAgreemenContent);
		performMode = getText(mEtPerformMode);
		timeLimit = getText(mTvTimeLimit);
		recorder = getText(mEtRecorder);

		if (isEmpty(disputeFact)) {
			ToastUtils.showLong("纠纷事实不能为空");
			return false;
		} else if (isEmpty(disputeMatter)) {
			ToastUtils.showLong("争议事项不能为空");
			return false;
		} else if (isEmpty(agreementContent)) {
			ToastUtils.showLong("协议内容不能为空");
			return false;
		} else if (isEmpty(performMode)) {
			ToastUtils.showLong("履行方式不能为空");
			return false;
		} else if (isEmpty(timeLimit)) {
			ToastUtils.showLong("时限不能为空");
			return false;
		} else if (isEmpty(recorder)) {
			ToastUtils.showLong("记录人不能为空");
			return false;
		} else {
			return true;
		}
	}
}

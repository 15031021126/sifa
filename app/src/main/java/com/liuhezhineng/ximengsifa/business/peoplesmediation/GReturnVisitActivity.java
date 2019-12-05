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
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form.ReturnVisitFormBean;
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
 * @description 人民调解回访记录
 */
public class GReturnVisitActivity extends BasePeoplesMediationActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.tv_code)
	TextView mTvCode;
	@BindView(R.id.tv_date)
	TextView mTvDate;
	@BindView(R.id.et_reason)
	EditText mEtReason;
	@BindView(R.id.tv_plaintiff)
	TextView mTvPlaintiff;
	@BindView(R.id.tv_defendant)
	TextView mTvDefendant;
	@BindView(R.id.tv_return_visit_people)
	TextView mTvReturnVisitPeople;
	@BindView(R.id.tv_case_title)
	TextView mTvCaseTitle;
	@BindView(R.id.et_content)
	EditText mEtContent;
	@BindView(R.id.tv_peoples_mediator_committee)
	TextView mTvPeoplesMediatorCommittee;
	@BindView(R.id.tv_peoples_mediator)
	TextView mTvPeoplesMediator;
	private String visitCause;
	private String visitDate;
	private String visitSituation;

	public static void actionStart(Context activity, BusinessBean item) {
		Intent intent = new Intent(activity, GReturnVisitActivity.class);
		intent.putExtra(Constant.BUSINESS, item);
		((BaseActivity) activity).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_return_visit;
	}

	@Override
	protected void initData() {
		super.initData();
		mBean = new ReturnVisitFormBean();
		loadBusinessDetails();
		initBirthdayPicker(new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				mTvDate.setText(getTime(date));
			}
		});
	}

	@Override
	protected void parseData(JSONObject data) throws Exception {
		JSONObject agreement = data.getJSONObject("oaPeopleMediationAgreement");
		mTvCode.setText(agreement.getString("agreementCode"));

		JSONObject apply = data.getJSONObject("oaPeopleMediationApply");
		JSONObject committee = apply.getJSONObject("peopleMediationCommittee");
		mTvPeoplesMediatorCommittee.setText(committee.getString("name"));
		JSONObject mediator = apply.getJSONObject("mediator");
		mTvPeoplesMediator.setText(mediator.getString("name"));

		mData = getIgnoreGson("createBy")
			.fromJson(apply.toString(), PeoplesMediationData.class);
		mTvPlaintiff.setText(mData.getAccuserName());
		mTvDefendant.setText(mData.getDefendantName());
		mTvReturnVisitPeople.setText(mData.getMediator().getName());
		mTvCaseTitle.setText(mData.getCaseTitle());

		setRequestInfo(mData);
	}

	@Override
	protected void createFrom() {
		ReturnVisitFormBean bean = (ReturnVisitFormBean) mBean;
		bean.setVisitCause(visitCause);
		bean.setVisitDate(visitDate);
		bean.setVisitSituation(visitSituation);
	}

	@Override
	@OnClick({R.id.tv_date,
		R.id.btn_yes})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		switch (view.getId()) {
			case R.id.tv_date:
				birthdayPickerView.show();
				break;
			case R.id.btn_yes:
				if (checkInput()) {
					commitRequest1(NetConstant.RETURN_VISIT_FORM);
				}
				break;
			default:
				break;
		}
	}

	private boolean checkInput() {
		visitCause = getText(mEtReason);
		visitDate = getText(mTvDate);
		visitSituation = getText(mEtContent);
		if (isEmpty(visitCause)) {
			ToastUtils.showLong("回访事由不能为空");
			return false;
		} else if (isEmpty(visitDate)) {
			ToastUtils.showLong("回访日期不能为空");
			return false;
		} else if (isEmpty(visitSituation)) {
			ToastUtils.showLong("回访内容不能为空");
			return false;
		} else {
			return true;
		}
	}
}

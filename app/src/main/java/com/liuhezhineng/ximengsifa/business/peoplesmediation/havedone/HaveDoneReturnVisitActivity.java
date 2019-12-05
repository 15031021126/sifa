package com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import butterknife.BindView;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.qmuiteam.qmui.widget.QMUITopBar;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @description 已办回访
 */
public class HaveDoneReturnVisitActivity extends BaseHaveDoneActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.tv_code)
	TextView mTvCode;
	@BindView(R.id.tv_date)
	TextView mTvDate;
	@BindView(R.id.tv_reason)
	TextView mTvReason;
	@BindView(R.id.tv_plaintiff)
	TextView mTvPlaintiff;
	@BindView(R.id.tv_defendant)
	TextView mTvDefendant;
	@BindView(R.id.tv_return_visit_people)
	TextView mTvReturnVisitPeople;
	@BindView(R.id.tv_case_title)
	TextView mTvCaseTitle;
	@BindView(R.id.tv_content)
	TextView mTvContent;
	@BindView(R.id.tv_peoples_mediator_committee)
	TextView mTvPeoplesMediatorCommittee;
	@BindView(R.id.tv_peoples_mediator)
	TextView mTvPeoplesMediator;

	public static void actionStart(Context activity, BusinessBean item) {
		Intent intent = new Intent(activity, HaveDoneReturnVisitActivity.class);
		intent.putExtra(Constant.BUSINESS, item);
		activity.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_have_done_return_visit;
	}

	@Override
	protected void parseData(JSONObject data) {
		try {
			mTvDate.setText(data.getString("visitDate"));
			mTvReason.setText(data.getString("visitCause"));
			mTvContent.setText(data.getString("visitSituation"));

			JSONObject agreement = data.getJSONObject("oaPeopleMediationAgreement");
			mTvCode.setText(agreement.getString("agreementCode"));

			JSONObject apply = data.getJSONObject("oaPeopleMediationApply");
			mTvPlaintiff.setText(apply.getString("accuserName"));
			mTvDefendant.setText(apply.getString("defendantName"));
			mTvCaseTitle.setText(apply.getString("caseTitle"));

			JSONObject committee = apply.getJSONObject("peopleMediationCommittee");
			mTvPeoplesMediatorCommittee.setText(committee.getString("name"));

			JSONObject mediator = apply.getJSONObject("mediator");
			mTvPeoplesMediator.setText(mediator.getString("name"));
			mTvReturnVisitPeople.setText(mediator.getString("name"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}

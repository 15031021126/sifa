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
 * @description 已办调查记录
 */
public class HaveDoneRecordActivity extends BaseHaveDoneActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.tv_mediate_date)
	TextView mTvMediateDate;
	@BindView(R.id.tv_mediate_place)
	TextView mTvMediatePlace;
	@BindView(R.id.tv_survey_people)
	TextView mTvSurveyPeople;
	@BindView(R.id.tv_survey_people_1)
	TextView mTvSurveyPeople1;
	@BindView(R.id.tv_participant)
	TextView mTvParticipant;
	@BindView(R.id.tv_recoder)
	TextView mTvRecoder;
	@BindView(R.id.tv_case_title)
	TextView mTvCaseTitle;
	@BindView(R.id.tv_content)
	TextView mTvContent;
	@BindView(R.id.tv_peoples_mediator_committee)
	TextView mTvPeoplesMediatorCommittee;
	@BindView(R.id.tv_peoples_mediator)
	TextView mTvPeoplesMediator;

	public static void actionStart(Context activity, BusinessBean item) {
		Intent intent = new Intent(activity, HaveDoneRecordActivity.class);
		intent.putExtra(Constant.BUSINESS, item);
		activity.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_have_done_record;
	}

	@Override
	protected void parseData(JSONObject data) {
		try {
			mTvMediateDate.setText(data.getString("examineDate"));
			mTvMediatePlace.setText(data.getString("examinePlace"));
			mTvParticipant.setText(data.getString("participants"));
			mTvContent.setText(data.getString("recordContent"));
			mTvSurveyPeople.setText(data.getString("inquirer"));
			mTvSurveyPeople1.setText(data.getString("respondent"));
			mTvRecoder.setText(data.getString("recorder"));

			JSONObject apply = data.getJSONObject("oaPeopleMediationApply");
			mTvCaseTitle.setText(apply.getString("caseTitle"));

			JSONObject committee = apply.getJSONObject("peopleMediationCommittee");
			mTvPeoplesMediatorCommittee.setText(committee.getString("name"));

			JSONObject mediator = apply.getJSONObject("mediator");
			mTvPeoplesMediator.setText(mediator.getString("name"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}

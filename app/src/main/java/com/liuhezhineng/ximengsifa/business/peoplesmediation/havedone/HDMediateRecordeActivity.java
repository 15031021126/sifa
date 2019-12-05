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
 * @description 调解记录
 */
public class HDMediateRecordeActivity extends BaseHaveDoneActivity {

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
	TextView mTvRecorder;
	@BindView(R.id.tv_case_title)
	TextView mTvCaseTitle;
	@BindView(R.id.tv_content)
	TextView mTvContent;
	@BindView(R.id.tv_peoples_mediator_committee)
	TextView mTvPeoplesMediatorCommittee;
	@BindView(R.id.tv_peoples_mediator)
	TextView mTvPeoplesMediator;

	public static void actionStart(Context activity, BusinessBean item) {
		Intent intent = new Intent(activity, HDMediateRecordeActivity.class);
		intent.putExtra(Constant.BUSINESS, item);
		activity.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_hdmediate_recorde;
	}

	@Override
	protected void parseData(JSONObject data) throws JSONException {
		mTvMediateDate.setText(data.getString("mediateDate"));
		mTvMediatePlace.setText(data.getString("mediatePlace"));
		mTvContent.setText(data.getString("mediateRecord"));

		JSONObject examine = data.getJSONObject("oaPeopleMediationExamine");
		mTvSurveyPeople.setText(examine.getString("inquirer"));
		mTvSurveyPeople1.setText(examine.getString("respondent"));
		mTvRecorder.setText(examine.getString("recorder"));
		mTvParticipant.setText(examine.getString("participants"));

		JSONObject apply = data.getJSONObject("oaPeopleMediationApply");
		mTvCaseTitle.setText(apply.getString("caseTitle"));

		JSONObject committee = apply.getJSONObject("peopleMediationCommittee");
		mTvPeoplesMediatorCommittee.setText(committee.getString("name"));

		JSONObject mediator = apply.getJSONObject("mediator");
		mTvPeoplesMediator.setText(mediator.getString("name"));
	}
}

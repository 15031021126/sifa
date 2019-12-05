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
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form.RecordFromBean;
import com.liuhezhineng.ximengsifa.business.base.BasePeoplesMediationActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 人民调解调查记录表
 */
public class DRecordActivity extends BasePeoplesMediationActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.tv_survey_date)
	TextView mTvSurveyDate;
	@BindView(R.id.et_survey_address)
	EditText mEtSurveyAddress;
	@BindView(R.id.et_survey_people)
	EditText mEtSurveyPeople;
	@BindView(R.id.et_survey_people_1)
	EditText mEtSurveyPeople1;
	@BindView(R.id.et_participant)
	EditText mEtParticipant;
	@BindView(R.id.et_recoder)
	EditText mEtRecoder;
	@BindView(R.id.tv_case_title)
	TextView mTvCaseTitle;
	@BindView(R.id.et_content)
	EditText mEtContent;
	@BindView(R.id.tv_peoples_mediator_committee)
	TextView mTvPeoplesMediatorCommittee;
	@BindView(R.id.tv_peoples_mediator)
	TextView mTvPeoplesMediator;

	private String examineDate;
	private String examinePlace;
	private String participants;
	private String inquirer;
	private String respondent;
	private String recordContent;
	private String recorder;

	public static void actionStart(Context activity, BusinessBean item) {
		Intent intent = new Intent(activity, DRecordActivity.class);
		intent.putExtra(Constant.BUSINESS, item);
		((BaseActivity) activity).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_record;
	}

	@Override
	protected void initData() {
		super.initData();
		mBean = new RecordFromBean();
		loadBusinessDetails();
		initBirthdayPicker(new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				mTvSurveyDate.setText(getTime(date));
			}
		});
	}

	@Override
	protected void parseData(JSONObject data) {
		try {
			JSONObject apply = data.getJSONObject("oaPeopleMediationApply");
			mData = getIgnoreGson("createBy").fromJson(
			        apply.toString(), PeoplesMediationData.class);

            mTvCaseTitle.setText(mData.getCaseTitle());
            mTvPeoplesMediatorCommittee.setText(
                    mData.getPeopleMediationCommittee().getName());
            mTvPeoplesMediator.setText(mData.getMediator().getName());

			setRequestInfo(mData);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void createFrom() {
		RecordFromBean bean = (RecordFromBean) mBean;
		bean.setExamineDate(getText(mTvSurveyDate));
		bean.setExaminePlace(getText(mEtSurveyAddress));
		bean.setInquirer(getText(mEtSurveyPeople));
		bean.setRespondent(getText(mEtSurveyPeople1));
		bean.setParticipants(getText(mEtParticipant));
		bean.setRecorder(getText(mEtRecoder));
		bean.setRecordContent(getText(mEtContent));
	}

	@Override
	@OnClick({R.id.tv_survey_date, R.id.btn_yes})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		switch (view.getId()) {
			case R.id.tv_survey_date:
				birthdayPickerView.show();
				break;
			case R.id.btn_yes:
				if (checkInput()) {
					commitRequest1(NetConstant.RECORD_FORM);
				}
				break;
			default:
				break;
		}
	}

	private boolean checkInput() {
		examineDate = getText(mTvSurveyDate);
		examinePlace = getText(mEtSurveyAddress);
		participants = getText(mEtParticipant);
		inquirer = getText(mEtSurveyPeople);
		respondent = getText(mEtSurveyPeople1);
		recorder = getText(mEtRecoder);
		recordContent = getText(mEtContent);

		if (isEmpty(examineDate)) {
			ToastUtils.showLong("调查时间不能为空");
			return false;
		} else if (isEmpty(examinePlace)) {
			ToastUtils.showLong("调查地点不能为空");
			return false;
		} else if (isEmpty(inquirer)) {
			ToastUtils.showLong("调查人不能为空");
			return false;
		} else if (isEmpty(respondent)) {
			ToastUtils.showLong("被调查人不能为空");
			return false;
		} else if (isEmpty(participants)) {
			ToastUtils.showLong(" 参加人不能为空");
			return false;
		} else if (isEmpty(recorder)) {
			ToastUtils.showLong("记录人不能为空");
			return false;
		} else if (isEmpty(recordContent)) {
			ToastUtils.showLong("记录内容不能为空");
			return false;
		} else {
			return true;
		}
	}
}

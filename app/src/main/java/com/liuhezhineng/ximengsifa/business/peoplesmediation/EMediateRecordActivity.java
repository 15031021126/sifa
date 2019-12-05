package com.liuhezhineng.ximengsifa.business.peoplesmediation;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.PeoplesMediationData;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form.MediateRecordFromBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form.RecordFromBean;
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
 * @description 调解记录表
 */
public class EMediateRecordActivity extends BasePeoplesMediationActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.tv_mediate_date)
	TextView mTvMediateDate;
	@BindView(R.id.et_mediate_place)
	EditText mEtMediatePlace;
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
	@BindView(R.id.et_content)
	EditText mEtContent;
	@BindView(R.id.tv_peoples_mediator_committee)
	TextView mTvPeoplesMediatorCommittee;
	@BindView(R.id.tv_peoples_mediator)
	TextView mTvPeoplesMediator;

	private String mediateDate;
	private String mediatePlace;
	private String mediateRecord;

	public static void actionStart(Context activity, BusinessBean item) {
		Intent intent = new Intent(activity, EMediateRecordActivity.class);
		intent.putExtra(Constant.BUSINESS, item);
		((BaseActivity) activity).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_mediate_record;
	}

	@Override
	protected void initData() {
		super.initData();
		mBean = new MediateRecordFromBean();
		loadBusinessDetails();
		initBirthdayPicker(new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				mTvMediateDate.setText(getTime(date));
			}
		});
	}

	@Override
	protected void parseData(JSONObject data) throws Exception {
		JSONObject apply = data.getJSONObject("oaPeopleMediationApply");
		mData = getIgnoreGson("createBy").fromJson(apply.toString(), PeoplesMediationData.class);

		setRequestInfo(mData);

		mTvCaseTitle.setText(mData.getCaseTitle());
		mTvPeoplesMediatorCommittee.setText(mData.getPeopleMediationCommittee().getName());
		mTvPeoplesMediator.setText(mData.getMediator().getName());

		JSONObject examineJson = data.getJSONObject("oaPeopleMediationExamine");
		RecordFromBean examine = new Gson()
			.fromJson(examineJson.toString(), RecordFromBean.class);
		mTvSurveyPeople.setText(examine.getInquirer());
		mTvSurveyPeople1.setText(examine.getRespondent());
		mTvRecorder.setText(examine.getRecorder());
		mTvParticipant.setText(examine.getParticipants());
	}

	@Override
	protected void createFrom() {
		MediateRecordFromBean bean = (MediateRecordFromBean) mBean;
		bean.setMediateDate(mediateDate);
		bean.setMediatePlace(mediatePlace);
		bean.setMediateRecord(mediateRecord);
	}

	@Override
	@OnClick({R.id.tv_mediate_date, R.id.btn_yes})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		switch (view.getId()) {
			case R.id.tv_mediate_date:
				birthdayPickerView.show();
				break;
			case R.id.btn_yes:
				if (checkInput()) {
					commitRequest1(NetConstant.MEDIATE_RECORD_FORM);
				}
				break;
			default:
				break;
		}
	}

	private boolean checkInput() {

		mediateDate = getText(mTvMediateDate);
		mediatePlace = getText(mEtMediatePlace);
		mediateRecord = getText(mEtContent);

		if (isEmpty(mediateDate)) {
			ToastUtils.showLong("调查时间不能为空");
			return false;
		} else if (isEmpty(mediatePlace)) {
			ToastUtils.showLong("调查地点不能为空");
			return false;
		} else if (isEmpty(mediateRecord)) {
			ToastUtils.showLong("调查记录不能为空");
			return false;
		} else {
			return true;
		}
	}
}

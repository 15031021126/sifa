package com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

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
 * @description 已办卷宗
 */
public class HaveDoneDossierActivity extends BaseHaveDoneActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.tv_numbering)
	TextView mTvNumbering;
	@BindView(R.id.tv_dossier_title)
	TextView mTvDossierTitle;
	@BindView(R.id.tv_content)
	TextView mTvContent;
	@BindView(R.id.tv_peoples_mediator_committee)
	TextView mTvPeoplesMediatorCommittee;
	@BindView(R.id.tv_peoples_mediator)
	TextView mTvPeoplesMediator;

	public static void actionStart(Context activity, BusinessBean item) {
		Intent intent = new Intent(activity, HaveDoneDossierActivity.class);
		intent.putExtra(Constant.BUSINESS, item);
		activity.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_have_done_dosssier;
	}

	@Override
	protected void parseData(JSONObject data) {
		try {
			mTvDossierTitle.setText(data.getString("dossierTitle"));
			mTvContent.setText(data.getString("dossierContent"));

			JSONObject agreement = data.getJSONObject("oaPeopleMediationAgreement");
			mTvNumbering.setText(agreement.getString("agreementCode"));

			JSONObject apply = data.getJSONObject("oaPeopleMediationApply");
			PeoplesMediationData mediationData = getIgnoreGson("createBy")
				.fromJson(apply.toString(), PeoplesMediationData.class);
			mTvPeoplesMediatorCommittee.setText(
			        mediationData.getPeopleMediationCommittee().getName());
			mTvPeoplesMediator.setText(mediationData.getMediator().getName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}

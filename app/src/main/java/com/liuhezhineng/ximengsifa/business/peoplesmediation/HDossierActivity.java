package com.liuhezhineng.ximengsifa.business.peoplesmediation;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.PeoplesMediationData;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form.DossierFormBean;
import com.liuhezhineng.ximengsifa.business.base.BasePeoplesMediationActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description dossier 卷宗情况
 */
public class HDossierActivity extends BasePeoplesMediationActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;

	@BindView(R.id.tv_numbering)
	TextView mTvNumbering;

	@BindView(R.id.et_dossier_title)
	EditText mEtDossierTitle;
	@BindView(R.id.et_content)
	EditText mEtContent;

	@BindView(R.id.tv_peoples_mediator_committee)
	TextView mTvPeoplesMediatorCommittee;
	@BindView(R.id.tv_peoples_mediator)
	TextView mTvPeoplesMediator;

	private String dossierTitle;
	private String dossierContent;

	public static void actionStart(Context activity, BusinessBean item) {
		Intent intent = new Intent(activity, HDossierActivity.class);
		intent.putExtra(Constant.BUSINESS, item);
		((BaseActivity) activity).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_dossier;
	}

	@Override
	protected void initData() {
		super.initData();
		mBean = new DossierFormBean();
		loadBusinessDetails();
	}

	@Override
	protected void parseData(JSONObject data) throws Exception {
		JSONObject agreement = data.getJSONObject("oaPeopleMediationAgreement");

		mTvNumbering.setText(agreement.getString("agreementCode"));

		JSONObject apply = data.getJSONObject("oaPeopleMediationApply");
		JSONObject committee = apply.getJSONObject("peopleMediationCommittee");
		mTvPeoplesMediatorCommittee.setText(committee.getString("name"));
		JSONObject mediator = apply.getJSONObject("mediator");
		mTvPeoplesMediator.setText(mediator.getString("name"));

		mData = getIgnoreGson("createBy")
			.fromJson(apply.toString(), PeoplesMediationData.class);

		mEtDossierTitle.setText(mData.getCaseTitle());

		setRequestInfo(mData);
	}

	@Override
	@OnClick({R.id.btn_archiving})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		switch (view.getId()) {
			case R.id.btn_archiving:
				if (checkInput()) {
					commitRequest1(NetConstant.DOSSIER_FORM);
				}
				break;
			default:
				break;
		}
	}

	@Override
	protected void createFrom() {
		DossierFormBean bean = (DossierFormBean) mBean;
		bean.setDossierTitle(dossierTitle);
		bean.setDossierContent(dossierContent);
	}

	private boolean checkInput() {
		dossierTitle = getText(mEtDossierTitle);
		dossierContent = getText(mEtContent);

		if (isEmpty(dossierTitle)) {
			ToastUtils.showLong("归档标题不能为空");
			return false;
		} else if (isEmpty(dossierContent)) {
			ToastUtils.showLong("归档内容不能为空");
			return false;
		} else {
			return true;
		}
	}
}

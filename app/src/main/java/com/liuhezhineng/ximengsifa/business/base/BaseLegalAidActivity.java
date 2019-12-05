package com.liuhezhineng.ximengsifa.business.base;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessDetailsWrapper;
import com.liuhezhineng.ximengsifa.bean.bussiness.LegalAidData;
import com.liuhezhineng.ximengsifa.bean.bussiness.LegalAidWorkflow;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * @author AIqinfeng
 * @date 2018/6/7
 */

public abstract class BaseLegalAidActivity extends BaseBusinessActivity {

    /**
     * 流程表单数据实体类
     */
	protected LegalAidWorkflow mLegalAidWorkflow = new LegalAidWorkflow();
	protected BusinessDetailsWrapper<LegalAidData> wrapper;

	@BindView(R.id.tv_aid_category)
	TextView mTvAidCategory;
	@BindView(R.id.tv_case_nature)
	TextView mTvCaseNature;

	@Override
	protected void dialogCommit() {
		flag = Constant.NO;
		commitRequest();
	}

	protected void commitRequest() {
		OkGo.<BaseBean<Object>>post(NetConstant.LEGAL_AID_WORKFLOW)
			.params(NetConstant.QUERY, getQueryStr())
			.execute(new DialogCallBack<BaseBean<Object>>(mActivity, "提交中...") {
				@Override
				public void onSuccess(Response<BaseBean<Object>> response) {
					if ("yes".equals(flag)) {
						ToastUtils.showShort("通过成功");
					} else {
						ToastUtils.showShort("退回成功");
					}
					LocalBroadcastManager.getInstance(mActivity).sendBroadcast(new Intent(Constant.DEAL_BUSINESS));
					setResult(RESULT_OK);
					finishActivity();
				}
			});
	}

	private String getQueryStr() {
		mLegalAidWorkflow.setId(wrapper.getBusinessId());
		mLegalAidWorkflow.setAct(getAct());
		mLegalAidWorkflow.setCaseFile(caseFile);
		mLegalAidWorkflow.setCaseFile2(caseFile2);
		createFrom();
		return new Gson().toJson(mLegalAidWorkflow);
	}

	protected void createFrom() {
	}

	@Override
	protected void initData() {
		super.initData();
		mBaseBusinessBean = new LegalAidData();
		loadBusinessDetails();
	}

	protected void loadBusinessDetails() {
		OkGo.<BaseBean<BusinessDetailsWrapper<LegalAidData>>>post(
			NetConstant.GET_LEGAL_AID_WORKFLOW)
			.params(NetConstant.QUERY, getBusinessDataQueryJson())
			.execute(new DialogCallBack<BaseBean<BusinessDetailsWrapper<LegalAidData>>>(mActivity) {
				@Override
				public void onSuccess(
					Response<BaseBean<BusinessDetailsWrapper<LegalAidData>>> response) {
					wrapper = response.body().getBody();
					mBaseBusinessBean = wrapper.getBusinessData();
					showDetails(response);
				}
			});
	}

	protected void showDetails(
		Response<BaseBean<BusinessDetailsWrapper<LegalAidData>>> response) {
		LegalAidData data = response.body().getBody().getBusinessData();

		LinearLayout llAgency = findViewById(R.id.ll_agency);
		if (TextUtils.isEmpty(data.getProxyName()) && llAgency != null) {
			llAgency.setVisibility(View.GONE);
		}

		caseFile = data.getCaseFile();
		caseFile2 = data.getCaseFile2();

		procInsId = data.getProcInsId();
		mTvAidCategory.setText(data.getAidCategoryDesc());
		mTvCaseNature.setText(data.getCaseNatureDesc());
		if (mLegalAidWorkflow != null) {
			mLegalAidWorkflow.setAidCategory(data.getAidCategory());
			mLegalAidWorkflow.setCaseNature(data.getCaseNature());
		}

		setCaseFile(data.getCaseFile());
	}

	@Override
	protected void initView() {
		super.initView();
		findViewById(R.id.tv_file).setVisibility(View.GONE);
	}
}

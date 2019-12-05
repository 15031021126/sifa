package com.liuhezhineng.ximengsifa.business.base;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessDetailsWrapper;
import com.liuhezhineng.ximengsifa.bean.bussiness.noticedefense.NoticeRequestBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.constant.NetConstant.Notice;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.StringDialogCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @date 2018/7/15
 */

public abstract class BaseNoticeActivity extends BaseBusinessActivity {

	protected Button mBtnBreak;
	protected NoticeRequestBean mNoticeRequestBean;
	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.tv_name)
	TextView mTvName;
	@BindView(R.id.tv_ethnic)
	TextView mTvEthnic;
	@BindView(R.id.tv_id_card)
	TextView mTvIdCard;
	@BindView(R.id.tv_sex)
	TextView mTvSex;
	@BindView(R.id.tv_birthday)
	TextView mTvBirthday;
	@BindView(R.id.tv_education)
	TextView mTvEducation;
	@BindView(R.id.tv_case_involved_count)
	TextView mTvCaseInvolvedCount;
	@BindView(R.id.tv_domicile)
	TextView mTvDomicile;
	@BindView(R.id.tv_domicile_details)
	TextView mTvDomicileDetails;
	@BindView(R.id.tv_custody)
	TextView mTvCustody;
	@BindView(R.id.tv_nationality)
	TextView mTvNationality;
	@BindView(R.id.tv_contract)
	TextView mTvContract;
	@BindView(R.id.tv_phone)
	TextView mTvPhone;
	@BindView(R.id.tv_aid_person_category)
	TextView mTvAidPersonCategory;
	@BindView(R.id.tv_accept_time)
	TextView mTvAcceptTime;
	@BindView(R.id.tv_case_name)
	TextView mTvCaseName;
	@BindView(R.id.tv_notice_office_category)
	TextView mTvNoticeOfficeCategory;
	@BindView(R.id.tv_notice_office_name)
	TextView mTvNoticeOfficeName;
	@BindView(R.id.tv_office_contract)
	TextView mTvOfficeContract;
	@BindView(R.id.tv_notice_phone)
	TextView mTvNoticePhone;
	@BindView(R.id.tv_case_involved)
	TextView mTvCaseInvolved;
	@BindView(R.id.tv_notice_number)
	TextView mTvNoticeNumber;
	@BindView(R.id.tv_involved_crime)
	TextView mTvInvolvedCrime;
	@BindView(R.id.tv_case_year_no)
	TextView mTvCaseYearNo;
	@BindView(R.id.tv_case_number)
	TextView mTvCaseNumber;
	@BindView(R.id.tv_notice_reason)
	TextView mTvNoticeReason;
	@BindView(R.id.tv_litigation_stage)
	TextView mTvLitigationStage;
	@BindView(R.id.tv_incidental_plaintiff)
	TextView mTvIncidentalPlaintiff;
	@BindView(R.id.tv_review_opinion)
	TextView mTvReviewOpinion;
	@BindView(R.id.tv_file)
	TextView mTvFile;
	@BindView(R.id.rv_annex)
	RecyclerView mRvAnnex;
	@BindView(R.id.ll_annex)
	LinearLayout mLlAnnex;
	@BindView(R.id.tv_aid_center)
	TextView mTvAidCenter;
	@BindView(R.id.tv_crime_content)
	TextView mTvCrimeContent;

	@Override
	protected void initView() {
		super.initView();
		mTvFile.setVisibility(View.GONE);

		mBtnBreak = findViewById(R.id.btn_deprecate);
	}

	@Override
	protected void initData() {
		super.initData();
		mBaseBusinessBean = new NoticeRequestBean();
		loadBusinessDetails();
	}

	protected void loadBusinessDetails() {
		OkGo.<BaseBean<BusinessDetailsWrapper<NoticeRequestBean>>>post(
			NetConstant.GET_LEGAL_AID_WORKFLOW)
			.params(NetConstant.QUERY, getBusinessDataQueryJson())
			.execute(
				new DialogCallBack<BaseBean<BusinessDetailsWrapper<NoticeRequestBean>>>(mActivity) {
					@Override
					public void onSuccess(
						Response<BaseBean<BusinessDetailsWrapper<NoticeRequestBean>>> response) {
						BaseBean<BusinessDetailsWrapper<NoticeRequestBean>> baseBean = response
							.body();
						BusinessDetailsWrapper<NoticeRequestBean> wrapper = baseBean.getBody();
						NoticeRequestBean data = wrapper.getBusinessData();
						mBaseBusinessBean = data;
						mNoticeRequestBean = data;
						showDetails(data);
					}
				});
	}

	protected void showDetails(NoticeRequestBean data) {

		mTvName.setText(data.getName());
		mTvEthnic.setText(data.getEthnicdesc());
		mTvIdCard.setText(data.getIdCard());
		mTvSex.setText(data.getSexdesc());
		mTvBirthday.setText(data.getBirthday());
		mTvEducation.setText(data.getEducationdesc());
		mTvCaseInvolvedCount.setText(data.getCasesum());
		mTvDomicile.setText(data.getDom().getName());
		mTvDomicileDetails.setText(data.getDomicile());
		mTvCustody.setText(data.getAddress());
		mTvNationality.setText(data.getInternationdesc());
		mTvContract.setText(data.getProxyName());
		mTvPhone.setText(data.getPhone());
		mTvAidPersonCategory.setText(data.getRenyuanTypedesc());

		mTvAcceptTime.setText(data.getSldate());
		mTvCaseName.setText(data.getCaseTitle());
		mTvNoticeOfficeCategory.setText(data.getOfficeType());
		mTvNoticeOfficeName.setText(data.getOfficeNamee());
		mTvOfficeContract.setText(data.getJgPerson());
		mTvNoticePhone.setText(data.getJgphone());
		mTvCaseInvolved.setText(data.getCaseTelevancydesc());
		mTvNoticeNumber.setText(data.getCaseInform());
		mTvCrimeContent.setText(data.getCaseGuiltdesc());
		mTvCaseYearNo.setText(data.getYear());
		mTvCaseNumber.setText(data.getYearNo());
		mTvNoticeReason.setText(data.getInformResondesc());
		mTvLitigationStage.setText(data.getCasesStagedesc());
		mTvIncidentalPlaintiff.setText(data.getCumName());
		mTvReviewOpinion.setText(data.getCaseReason());

		setCaseFile(data.getCaseFile());

		mTvAidCenter.setText(data.getLegalOffice().getName());
	}

	@Override
	protected void setListener() {
		super.setListener();
		if (mBtnBreak != null) {
			mBtnBreak.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					flag = Constant.END;
					showNoDialog();
				}
			});
		}
	}

	@Override
	protected void dialogCommit() {
		commitNotice();
	}

	protected void commitNotice() {
		mNoticeRequestBean.setAct(getAct());
		String queryStr = new Gson().toJson(mNoticeRequestBean);
		String loadingText = "提交中...";
		if (Constant.YES.equals(flag)) {
			loadingText = "提交中...";
		} else if (Constant.NO.equals(flag)) {
			loadingText = "退回中...";
		}
		OkGo.<String>post(Notice.FLOW)
			.params(Constant.QUERY, queryStr)
			.execute(new StringDialogCallback(mActivity, loadingText) {
				@Override
				protected void responseSuccess(JSONObject object) {
					resultToast();
				}
			});
	}

	protected void resultToast() {
		if (Constant.YES.equals(flag)) {
			ToastUtils.showLong("提交成功");
		} else if (Constant.NO.equals(flag)) {
			ToastUtils.showLong("退回成功");
		}
		LocalBroadcastManager.getInstance(mActivity)
			.sendBroadcast(new Intent(Constant.DEAL_BUSINESS));
		setResult(RESULT_OK);
		finishActivity();
	}
}

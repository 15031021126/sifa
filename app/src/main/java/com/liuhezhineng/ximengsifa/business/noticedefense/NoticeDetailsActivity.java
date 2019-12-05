package com.liuhezhineng.ximengsifa.business.noticedefense;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.noticedefense.NoticeRequestBean;
import com.liuhezhineng.ximengsifa.business.base.BaseNoticeActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;

/**
 * @author AIqinfeng
 */
public class NoticeDetailsActivity extends BaseNoticeActivity {

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
	@BindView(R.id.tv_aid_way)
	TextView mTvAidWay;
	@BindView(R.id.tv_review_date)
	TextView mTvReviewDate;
	@BindView(R.id.tv_review_content)
	TextView mTvReviewContent;
	@BindView(R.id.tv_lawyer)
	TextView mTvLawyer;
	@BindView(R.id.tv_write_case_content)
	TextView mTvWriteCaseContent;
	@BindView(R.id.tv_evaluate_content)
	TextView mTvEvaluateContent;
	@BindView(R.id.tv_notice_subsidy)
	TextView mTvNoticeSubsidy;
	@BindView(R.id.layout_notice_review)
	View mViewReview;
	@BindView(R.id.layout_notice_approval)
	View mViewApproval;
	@BindView(R.id.layout_notice_assign_lawyer)
	View mViewLawyer;
	@BindView(R.id.layout_notice_write_case_content)
	View mViewCaseProcess;
	@BindView(R.id.layout_notice_evaluation)
	View mViewEvaluation;
	@BindView(R.id.layout_notice_subsidy)
	View mViewSubsidy;
	@BindView(R.id.et_approval_opinion)
	TextView mEtApprovalOpinion;
	@BindView(R.id.tv_lawyer_office)
	TextView mTvLawyerOffice;
	@BindView(R.id.tv_evaluate_score)
	TextView mTvEvaluateScore;

	public static void actionStart(BaseActivity activity, BusinessBean bean) {
		Intent intent = new Intent(activity, NoticeDetailsActivity.class);
		intent.putExtra(Constant.BUSINESS, bean);
		activity.startActivity(intent);
	}

	@Override
	protected void initView() {
		super.initView();

		mTvReviewContent.setEnabled(false);
		mEtApprovalOpinion.setEnabled(false);
		mTvWriteCaseContent.setEnabled(false);
		mTvEvaluateContent.setEnabled(false);
		mTvNoticeSubsidy.setEnabled(false);

		mTvFile.setVisibility(View.GONE);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_notice_details;
	}

	@Override
	protected void showDetails(NoticeRequestBean data) {
		super.showDetails(data);

		if (!TextUtils.isEmpty(data.getModalitydesc())) {
			mViewReview.setVisibility(View.VISIBLE);
			mTvAidWay.setText(data.getModalitydesc());
			mTvReviewDate.setText(data.getScdate());
			mTvReviewContent.setText(data.getApproveCom());
		}

		if (!TextUtils.isEmpty(data.getFyzhurenCom())) {
			mViewApproval.setVisibility(View.VISIBLE);
			mTvLawyerOffice.setText(data.getLawOffice().getName());
			mEtApprovalOpinion.setText(data.getFyzhurenCom());
		}

		if (!TextUtils.isEmpty(data.getLawyer().getName())) {
			mViewLawyer.setVisibility(View.VISIBLE);
			mTvLawyer.setText(data.getLawyer().getName());
		}

		if (!TextUtils.isEmpty(data.getCaseHandleProcess())) {
			mViewCaseProcess.setVisibility(View.VISIBLE);
			mTvWriteCaseContent.setText(data.getCaseHandleProcess());
		}
		if (!TextUtils.isEmpty(data.getThirdPartyEvaluation())) {
			mViewEvaluation.setVisibility(View.VISIBLE);
			mTvEvaluateScore.setText(data.getThirdPartyScore());
			mTvEvaluateContent.setText(data.getThirdPartyEvaluation());
		}
		if (!TextUtils.isEmpty(data.getReceiveSubsidy())) {
			mViewSubsidy.setVisibility(View.VISIBLE);
			mTvNoticeSubsidy.setText(data.getReceiveSubsidy());
		}
	}
}

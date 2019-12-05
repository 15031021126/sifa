package com.liuhezhineng.ximengsifa.business.noticedefense;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.InsUserBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.noticedefense.NoticeRequestBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.business.base.BaseNoticeActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.Notice;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.utils.PickerViewBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 */
public class DLawyerDealActivity extends BaseNoticeActivity {

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
		Intent intent = new Intent(activity, DLawyerDealActivity.class);
		intent.putExtra(Constant.BUSINESS, bean);
		activity.startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
	}

	@Override
	protected void initView() {
		super.initView();

		switch (mBusinessBean.getTask().getTaskDefinitionKey()) {
			case Notice.SUBSIDY:
				mTvEvaluateScore.setEnabled(false);
				mTvEvaluateContent.setEnabled(false);
				mViewSubsidy.setVisibility(View.VISIBLE);
			case Notice.EVALUATE:
				mBtnBreak.setVisibility(View.GONE);
				mTvWriteCaseContent.setEnabled(false);
				mViewEvaluation.setVisibility(View.VISIBLE);
			case Notice.DEAL:
				mViewCaseProcess.setVisibility(View.VISIBLE);
			case Notice.AID_CENTER_CONFIRM:
				mTvLawyer.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						showPickerView(lawyerPersonPickerView);
					}
				});
				mViewLawyer.setVisibility(View.VISIBLE);
				mViewApproval.setVisibility(View.VISIBLE);
				mEtApprovalOpinion.setEnabled(false);
				mTvReviewContent.setEnabled(false);
				break;
			default:
				break;
		}

	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_lawyer_deal;
	}

	@Override
	protected void showDetails(NoticeRequestBean data) {
		super.showDetails(data);

		if (mBusinessBean.getTask().getTaskDefinitionKey().equals(Notice.AID_CENTER_CONFIRM)) {
			initLawyerPersonPickerView();
		}
		if (!TextUtils.isEmpty(data.getModalitydesc())) {
			mTvAidWay.setText(data.getModalitydesc());
			mTvReviewDate.setText(data.getScdate());
			mTvReviewContent.setText(data.getApproveCom());
		}

		if (!TextUtils.isEmpty(data.getFyzhurenCom())) {
			mTvLawyerOffice.setText(data.getLawOffice().getName());
			mEtApprovalOpinion.setText(data.getFyzhurenCom());
		}

		if (!TextUtils.isEmpty(data.getLawyer().getName())) {
			mTvLawyer.setText(data.getLawyer().getName());
		}

		if (!TextUtils.isEmpty(data.getCaseHandleProcess())) {
			mTvWriteCaseContent.setText(data.getCaseHandleProcess());
		}
		if (!TextUtils.isEmpty(data.getThirdPartyEvaluation())) {
			mTvEvaluateScore.setText(data.getThirdPartyScore());
			mTvEvaluateContent.setText(data.getThirdPartyEvaluation());
		}
		if (!TextUtils.isEmpty(data.getReceiveSubsidy())) {
			mTvNoticeSubsidy.setText(data.getReceiveSubsidy());
		}
	}

	private void initLawyerPersonPickerView() {
		Map<String, String> map = new HashMap<>(16);
		map.put("type", "1");
		map.put("id", mNoticeRequestBean.getLawOffice().getId());
		String queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<List<InsUserBean>>>post(NetConstant.GET_LAWYER)
			.params(NetConstant.QUERY, queryStr)
			.execute(new JsonCallback<BaseBean<List<InsUserBean>>>() {
				@Override
				public void onSuccess(Response<BaseBean<List<InsUserBean>>> response) {
					List<InsUserBean> officeList = response.body().getBody();
					if (officeList != null && officeList.size() > 0) {
						final List<InsUserBean> users = officeList.get(0).getUsers();
						lawyerPersonPickerView = new PickerViewBuilder(mActivity,
							new OnOptionsSelectListener() {
								@Override
								public void onOptionsSelect(int options1, int options2,
									int options3,
									View v) {
                                    InsUserBean user = users.get(options1);
									Office lawyer = new Office();
									lawyer.setId(user.getId());
									lawyer.setName(user.getName());
									mTvLawyer.setText(user.getName());
									mNoticeRequestBean.setLawyer(lawyer);
								}
							})
							.build();
						lawyerPersonPickerView.setPicker(users);
					} else {
						ToastUtils.showLong("暂无数据");
					}
				}
			});
	}

	@OnClick(R.id.btn_commit)
	public void onViewClicked() {
		if (checkInput()) {
			flag = "yes";
			commitNotice();
		}
	}

	private boolean checkInput() {

		mNoticeRequestBean.setCaseHandleProcess(mTvWriteCaseContent.getText().toString().trim());
		mNoticeRequestBean.setThirdPartyEvaluation(mTvEvaluateContent.getText().toString().trim());
		mNoticeRequestBean.setThirdPartyScore(mTvEvaluateScore.getText().toString().trim());
		mNoticeRequestBean.setReceiveSubsidy(mTvNoticeSubsidy.getText().toString().trim());

		return true;
	}
}

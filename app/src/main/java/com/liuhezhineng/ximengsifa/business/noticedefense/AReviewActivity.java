package com.liuhezhineng.ximengsifa.business.noticedefense;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.noticedefense.NoticeRequestBean;
import com.liuhezhineng.ximengsifa.business.base.BaseNoticeActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.qmuiteam.qmui.widget.QMUITopBar;
import java.util.Date;

/**
 * @author AIqinfeng
 */
public class AReviewActivity extends BaseNoticeActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.ll_user_info_header)
	LinearLayout mLlUserInfoHeader;
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
	EditText mTvReviewContent;
	@BindView(R.id.btn_return)
	Button mBtnSave;
	@BindView(R.id.btn_commit)
	Button mBtnCommit;
	@BindView(R.id.ll_btn)
	LinearLayout mLlBtn;

	public static void actionStart(BaseActivity activity, BusinessBean bean) {
		Intent intent = new Intent(activity, AReviewActivity.class);
		intent.putExtra(Constant.BUSINESS, bean);
		activity.startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
	}

	@Override
	@OnClick({R.id.tv_aid_way, R.id.tv_review_date, R.id.btn_return, R.id.btn_commit})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		switch (view.getId()) {
			case R.id.tv_aid_way:
				showPickerView(modalityPicker);
				break;
			case R.id.tv_review_date:
				birthdayPickerView.show();
				break;
			case R.id.btn_return:
				flag = "no";
				showNoDialog();
				break;
			case R.id.btn_commit:
				flag = "yes";
				createForm();
				if (checkInput()) {
					commitNotice();
				}
				break;
			default:
				break;
		}
	}

	private void createForm() {

		mNoticeRequestBean.setApproveCom(mTvReviewContent.getText().toString().trim());
	}

	private boolean checkInput() {
		if (TextUtils.isEmpty(mNoticeRequestBean.getModality())) {
			ToastUtils.showLong("援助方式不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getScdate())) {
			ToastUtils.showLong("审查时间不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getApproveCom())) {
			ToastUtils.showLong("审查意见不能为空");
			return false;
		} else {
			return true;
		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_review_notice;
	}

	@Override
	protected void initData() {
		super.initData();

		initModalityPicker(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvAidWay.setText(modalityList.get(options1).getLabel());
				mNoticeRequestBean.setModality(modalityList.get(options1).getValue());
			}
		});

		initBirthdayPicker(new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				mTvReviewDate.setText(getTime(date));
				mNoticeRequestBean.setScdate(getTime(date));
			}
		});
	}

	@Override
	protected void showDetails(NoticeRequestBean data) {
		super.showDetails(data);

	}

	@Override
	protected void initView() {
		super.initView();

		mTvFile.setVisibility(View.GONE);
	}
}

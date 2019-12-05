package com.liuhezhineng.ximengsifa.business.noticedefense;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.noticedefense.NoticeRequestBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.bean.picker.InsBean;
import com.liuhezhineng.ximengsifa.business.base.BaseNoticeActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;

/**
 * @author AIqinfeng
 */
public class BApprovalActivity extends BaseNoticeActivity {

	@BindView(R.id.et_approval_opinion)
	EditText mEtApprovalOpinion;
	@BindView(R.id.btn_return)
	Button mBtnSave;
	@BindView(R.id.btn_commit)
	Button mBtnCommit;
	@BindView(R.id.tv_aid_way)
	TextView mTvAidWay;
	@BindView(R.id.tv_review_date)
	TextView mTvReviewDate;
	@BindView(R.id.tv_review_content)
	TextView mTvReviewContent;
	@BindView(R.id.tv_lawyer_office)
	TextView mTvLawyerOffice;

	public static void actionStart(BaseActivity activity, BusinessBean bean) {
		Intent intent = new Intent(activity, BApprovalActivity.class);
		intent.putExtra(Constant.BUSINESS, bean);
		activity.startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
	}

	@Override
	protected void initView() {
		super.initView();

		mTvReviewContent.setEnabled(false);
	}

	@Override
	@OnClick({R.id.btn_return, R.id.btn_commit, R.id.tv_lawyer_office})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.tv_lawyer_office:
				showPickerView(lawyerOfficePicker);
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

		mNoticeRequestBean.setFyzhurenCom(mEtApprovalOpinion.getText().toString().trim());
	}

	private boolean checkInput() {

		if (TextUtils.isEmpty(mNoticeRequestBean.getLawOffice().getName())) {
			ToastUtils.showLong("律所不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getFyzhurenCom())) {
			ToastUtils.showLong("受理审批意见不能为空");
			return false;
		} else {
			return true;
		}
	}

	@Override
	protected void initData() {
		super.initData();

		initLawyerOfficePicker(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				InsBean bean = lawyerOfficeList.get(options1);
				mTvLawyerOffice.setText(bean.getAgencyName());
				Office office = new Office();
				office.setId(bean.getId());
				office.setName(bean.getAgencyName());
				mNoticeRequestBean.setLawOffice(office);
			}
		}, "");
	}

	@Override
	protected void showDetails(NoticeRequestBean data) {
		super.showDetails(data);

		if (!TextUtils.isEmpty(data.getModalitydesc())) {
			mTvAidWay.setText(data.getModalitydesc());
			mTvReviewDate.setText(data.getScdate());
			mTvReviewContent.setText(data.getApproveCom());
		}

	}

	@Override
	protected void resultToast() {
		if ("yes".equals(flag)) {
			ToastUtils.showLong("审批通过成功");
		} else if ("no".equals(flag)) {
			ToastUtils.showLong("退回成功");
		}
		LocalBroadcastManager.getInstance(mActivity)
			.sendBroadcast(new Intent(Constant.DEAL_BUSINESS));
		setResult(RESULT_OK);
		finishActivity();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_approval;
	}
}

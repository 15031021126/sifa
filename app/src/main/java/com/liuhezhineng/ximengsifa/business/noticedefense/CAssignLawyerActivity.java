package com.liuhezhineng.ximengsifa.business.noticedefense;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
public class CAssignLawyerActivity extends BaseNoticeActivity {

	@BindView(R.id.tv_lawyer)
	TextView mTvLawyer;
	@BindView(R.id.tv_aid_way)
	TextView mTvAidWay;
	@BindView(R.id.tv_review_date)
	TextView mTvReviewDate;
	@BindView(R.id.tv_review_content)
	EditText mTvReviewContent;
	@BindView(R.id.tv_lawyer_office)
	TextView mTvLawyerOffice;
	@BindView(R.id.et_approval_opinion)
	EditText mEtApprovalOpinion;

	public static void actionStart(BaseActivity activity, BusinessBean bean) {
		Intent intent = new Intent(activity, CAssignLawyerActivity.class);
		intent.putExtra(Constant.BUSINESS, bean);
		activity.startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_assign_lawyer;
	}

	@Override
	protected void initData() {
		super.initData();

		mTvReviewContent.setEnabled(false);
		mEtApprovalOpinion.setEnabled(false);
	}

	@Override
	protected void showDetails(NoticeRequestBean data) {
		super.showDetails(data);

		mTvAidWay.setText(data.getModalitydesc());
		mTvReviewDate.setText(data.getScdate());
		mTvReviewContent.setText(data.getApproveCom());

		mTvLawyerOffice.setText(data.getLawOffice().getName());
		mEtApprovalOpinion.setText(data.getFyzhurenCom());

		initLawyerPersonPickerView();

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

	@Override
	@OnClick({R.id.tv_lawyer, R.id.btn_commit})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		switch (view.getId()) {
			case R.id.tv_lawyer:
				showPickerView(lawyerPersonPickerView);
				break;
			case R.id.btn_commit:
				if (checkInput()) {
					flag = "yes";
					commitNotice();
				}
				break;
			default:
				break;
		}
	}

	private boolean checkInput() {

		if (TextUtils.isEmpty(mNoticeRequestBean.getLawyer().getId())) {
			ToastUtils.showLong("请指定律师");
			return false;
		} else {
			return true;
		}
	}
}

package com.liuhezhineng.ximengsifa.callback;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.MediatorWorkflowAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.Act;
import com.liuhezhineng.ximengsifa.bean.bussiness.BaseBusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.DeprecateBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.AidFlowInfoBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.WorkflowShowBean;
import com.liuhezhineng.ximengsifa.business.fastlegal.AQuickAcceptLegalAidActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.Business;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.StringDialogCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author AIqinfeng
 * @description 工作流程
 */
public class NewWorkflowActivity extends BaseActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.rv_workflow)
	RecyclerView mRvWorkflow;

	ArrayList<WorkflowShowBean> mList;
	MediatorWorkflowAdapter mAdapter;
	BusinessBean mBean;
	 BaseBusinessBean mBaseBusinessBean;
	public static void actionStart(Context context, BusinessBean bean, BaseBusinessBean mBaseBusinessBean) {
		Intent intent = new Intent(context, NewWorkflowActivity.class);
		intent.putExtra(Constant.BUSINESS, bean);
		intent.putExtra("basebusiness", mBaseBusinessBean);
		context.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_workflow;
	}

	@Override
	protected void initView() {
		super.initView();
		mBean = (BusinessBean) getIntent().getSerializableExtra(Constant.BUSINESS);
		mBaseBusinessBean = (BaseBusinessBean) getIntent().getSerializableExtra("basebusiness");
		initTopBar(mTopBar, "流程进展");
		Button btnWorkflow = mTopBar.addRightTextButton("结束流程", R.id.tv_case_title);
		btnWorkflow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				flag = Constant.END;
				showNoDialog();

			}
		});
		mList = new ArrayList<>();
		mBean = (BusinessBean) getIntent().getSerializableExtra(Constant.BUSINESS);
		mAdapter = new MediatorWorkflowAdapter(mActivity, mList, mBean.getProcInsId());
		mRvWorkflow.setAdapter(mAdapter);
	}
	protected String flag;
	protected String comment;

	protected void showNoDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
		final AlertDialog dialog = builder.create();
		View dialogView = View.inflate(mActivity, R.layout.dialog_no, null);

		TextView tvTitle = dialogView.findViewById(R.id.tv_title);
		final EditText etReason = dialogView.findViewById(R.id.et_no_reason);
		Button btnCancel = dialogView.findViewById(R.id.btn_no);
		Button btnConfirm = dialogView.findViewById(R.id.btn_yes);

		String errorStr = "退回原因不能为空";
		if (Constant.END.equals(flag)) {
			etReason.setHint(getResources().getText(R.string.deprecate_hint));
			tvTitle.setText(getResources().getText(R.string.deprecate_reason));
			btnConfirm.setText(getResources().getText(R.string.confirm_deprecate));
			errorStr = (String) getResources().getText(R.string.deprecated_reason_not_null);
		}

		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		final String finalErrorStr = errorStr;
		btnConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				comment = etReason.getText().toString().trim();
				if (TextUtils.isEmpty(comment)) {
					ToastUtils.showLong(finalErrorStr);
					return;
				}
				if (Constant.END.equals(flag)) {
					deprecateBusiness();
				} else {
//					dialogCommit();
				}
				dialog.dismiss();
			}
		});
		dialog.setView(dialogView);
		dialog.show();
	}
	@NonNull
	protected Act getAct() {
		Act act = new Act();
		act.setProcInsId(mBean.getProcInsId());
		act.setProcDefId(mBean.getProcDefId());
		act.setProcDefKey(mBean.getProcDefKey());
		act.setTaskId(mBean.getTask().getId());
		act.setTaskName(mBean.getTask().getName());
		act.setTaskDefKey(mBean.getTask().getTaskDefinitionKey());
		act.setFlag(flag);
		act.setComment(comment);
		return act;
	}
	protected boolean requestFlag;
	protected void deprecateBusiness() {
		DeprecateBean	mDeprecateBean = new DeprecateBean();
		Act act = getAct();
		if (requestFlag) {
			act.setFlag("request user");
		}
		mDeprecateBean.setRemarks(comment);
		mDeprecateBean.setAct(act);
		mDeprecateBean.setId(mBaseBusinessBean.getId());
		String queryStr = new Gson().toJson(mDeprecateBean);
		OkGo.<String>post(NetConstant.Notice.DEPRECATE_BUSINESS)
				.params(Constant.QUERY, queryStr)
				.execute(new StringDialogCallback(mActivity,
						(String) getResources().getText(R.string.deprecate_loading)) {
					@Override
					protected void responseSuccess(JSONObject object) {
						ToastUtils.showLong("结束成功");
						setResult(RESULT_OK);
						finishActivity();
					}
				});
	}


	@Override
	protected void initData() {
		super.initData();

		switch (mBean.getProcDefKey()) {
			case Business.LEGAL_AID:
			case Business.FAST_LEGAL:
			case Business.NOTIFICATION_DEFENSE:
				loadFlowNode(NetConstant.LEGAL_AID_WORKFLOW_INFO, mBean.getProcInsId());
				break;
			case Business.PEOPLE_MEDIATION:
				loadFlowNode(NetConstant.MEDIATION_WORKFLOW_INFO, mBean.getProcInsId());
				break;
			case "reconsider_apply":
				loadFlowNode(NetConstant.MEDIATION_WORKFLOW_INFO, mBean.getProcInsId());
				break;
			default:
				break;
		}
	}
	private void loadFlowNode(String url, String procInsId) {
		Map<String, String> map = new HashMap<>(16);
		map.put("procInsId", procInsId);
		String queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<List<AidFlowInfoBean>>>post(url)
			.params(NetConstant.QUERY, queryStr)
			.execute(new DialogCallBack<BaseBean<List<AidFlowInfoBean>>>(mActivity, "加载流程节点信息...") {
				@Override
				public void onSuccess(Response<BaseBean<List<AidFlowInfoBean>>> response) {
					mAdapter.initData(response.body().getBody());
				}
			});
	}
}

package com.liuhezhineng.ximengsifa.business.peoplesmediation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.MediatorWorkflowAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.AidFlowInfoBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.WorkflowShowBean;
import com.liuhezhineng.ximengsifa.callback.LogUtils;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.Business;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @description 工作流程
 */
public class WorkflowActivity extends BaseActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.rv_workflow)
	RecyclerView mRvWorkflow;

	ArrayList<WorkflowShowBean> mList;
	MediatorWorkflowAdapter mAdapter;
	BusinessBean mBean;

	public static void actionStart(Context context, BusinessBean bean) {
		Intent intent = new Intent(context, WorkflowActivity.class);
		intent.putExtra(Constant.BUSINESS, bean);
		context.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_workflow;
	}

	@Override
	protected void initView() {
		super.initView();
		initTopBar(mTopBar, "流程进展");
		mList = new ArrayList<>();
		mBean = (BusinessBean) getIntent().getSerializableExtra(Constant.BUSINESS);
		mAdapter = new MediatorWorkflowAdapter(mActivity, mList, mBean.getProcInsId());
		mRvWorkflow.setAdapter(mAdapter);
	}

	@Override
	protected void initData() {
		super.initData();
		LogUtils.logi("",""+mBean.getProcDefKey());
		LogUtils.logi("",""+mBean.getProcInsId());
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

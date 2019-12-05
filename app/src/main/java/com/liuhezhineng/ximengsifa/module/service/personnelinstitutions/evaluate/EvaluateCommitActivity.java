package com.liuhezhineng.ximengsifa.module.service.personnelinstitutions.evaluate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import butterknife.BindView;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.BeEvaluateUserAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.evaluate.BeEvaluateCommitInfoBean;
import com.liuhezhineng.ximengsifa.bean.evaluate.BeEvaluateUserBean;
import com.liuhezhineng.ximengsifa.bean.evaluate.MyEvaluateCommitInfoBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.interfaces.OnPrescriptionListener;
import com.liuhezhineng.ximengsifa.model.StringDialogCallback;
import com.lzy.okgo.OkGo;
import com.qmuiteam.qmui.widget.QMUITopBar;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @description 发表评价
 */
public class EvaluateCommitActivity extends BaseActivity implements OnPrescriptionListener {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.rv_be_evaluate_user)
	RecyclerView mRvBeEvaluateUser;
	@BindView(R.id.et_evaluate)
	EditText mEtEvaluate;

	BeEvaluateUserAdapter mAdapter;
	ArrayList<BeEvaluateUserBean> mList;

	private MyEvaluateCommitInfoBean mBean;
	ArrayList<BeEvaluateCommitInfoBean> mBeEvaluateCommitInfoBeanArrayList;
	private String commentId;
	private String type;

	public static void actionStart(Context context, String commentId, List<BeEvaluateUserBean> list, String type) {
		Intent intent = new Intent(context, EvaluateCommitActivity.class);
		intent.putExtra("commentId", commentId);
		intent.putExtra("user_list", new Gson().toJson(list));
		intent.putExtra("type", type);
		((BaseActivity) context).startActivityForResult(intent, Constant.NORMAL_CODE);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_evaluate_commit;
	}

	@Override
	protected void initView() {
		super.initView();

		initTopBar(mTopBar, "发表评价");
		mTopBar.addRightTextButton("发表", R.id.btn_ok).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				commitEvaluation();
			}
		});

		String listStr = getIntent().getStringExtra("user_list");
		Type type = new TypeToken<List<BeEvaluateUserBean>>() {}.getType();
		mList = new Gson().fromJson(listStr, type);
		mAdapter = new BeEvaluateUserAdapter(mActivity, mList);
		mRvBeEvaluateUser.setAdapter(mAdapter);
	}

	private void commitEvaluation() {
		String proposal = mEtEvaluate.getText().toString().trim();
		if (mBeEvaluateCommitInfoBeanArrayList == null
			|| mBeEvaluateCommitInfoBeanArrayList.size() <= 0) {
			ToastUtils.showLong("请对人员做出星级评价");
			return;
		}
		for (int i = 0; i < mBeEvaluateCommitInfoBeanArrayList.size(); i++) {
			BeEvaluateCommitInfoBean bean = mBeEvaluateCommitInfoBeanArrayList.get(i);
			if ((TextUtils.isEmpty(bean.getPrescription())
				|| Integer.parseInt(bean.getPrescription()) < 1)) {
				ToastUtils.showLong("请对" + mList.get(i).getName() + "做出星级评价");
				return;
			}
			bean.setProposal(proposal);
		}
		mBean.setCommentId(commentId);
		mBean.setEvaluatedList(mBeEvaluateCommitInfoBeanArrayList);
		mBean.setType(type);

		String queryStr = new Gson().toJson(mBean);
		OkGo.<String>post(NetConstant.COMMIT_MY_EVALUATE)
			.params(NetConstant.QUERY, queryStr)
			.execute(new StringDialogCallback(mActivity, "评价提交中...") {
				@Override
				protected void responseSuccess(JSONObject object) {
					ToastUtils.showLong("评价成功");
					setResult(RESULT_OK);
					finishActivity();
				}
			});
	}

	@Override
	protected void initData() {
		super.initData();

		mBean = new MyEvaluateCommitInfoBean();
		mBeEvaluateCommitInfoBeanArrayList = new ArrayList<>();

		commentId = getIntent().getStringExtra("commentId");
		type = getIntent().getStringExtra("type");
	}

	@Override
	public void setPrescription(List<BeEvaluateCommitInfoBean> list) {
		if (mBeEvaluateCommitInfoBeanArrayList != null) {
			mBeEvaluateCommitInfoBeanArrayList.clear();
			mBeEvaluateCommitInfoBeanArrayList.addAll(list);
		}
	}
}

package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.MyEvaluateAdapter.MyEvaluateViewHolder;
import com.liuhezhineng.ximengsifa.bean.evaluate.MyEvaluateBean;
import com.liuhezhineng.ximengsifa.constant.Constant.Business;
import com.liuhezhineng.ximengsifa.module.service.personnelinstitutions.evaluate.EvaluateCommitActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/7/4
 * @description 我的评价：法援，人调，直通车
 */

public class MyEvaluateAdapter extends Adapter<MyEvaluateViewHolder> {

	private static final String LEGAL_AID = "3";
	private static final String MEDIATION = "4";
	private static final String FAST_LEGAL = "5";

	private Context mContext;
	private ArrayList<MyEvaluateBean> mList;

	public MyEvaluateAdapter(Context context, ArrayList<MyEvaluateBean> list) {
		mContext = context;
		mList = new ArrayList<>();
		mList.addAll(list);
	}

	@NonNull
	@Override
	public MyEvaluateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new MyEvaluateViewHolder(
			LayoutInflater.from(mContext).inflate(R.layout.my_evaluate_item, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull MyEvaluateViewHolder holder, int position) {
		final MyEvaluateBean bean = mList.get(position);
		holder.mTvTitle.setText(bean.getVars().getMap().getTitle());
		holder.mTvDate.setText(bean.getTask().getCreateTime());
		holder.mTvType.setText("类型：" + bean.getProcDefName());
		if ("1".equals(bean.getIsEvaluate())) {
			holder.mTvEvaluate.setText("已评价");
			holder.mTvEvaluate.setBackgroundResource(R.drawable.gray_shape);
			holder.mTvEvaluate.setOnClickListener(null);
		} else {
			holder.mTvEvaluate.setText("去评价");
			holder.mTvEvaluate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String type = "";
					switch (bean.getProcDefKey()) {
						case Business.LEGAL_AID:
							type = LEGAL_AID;
							break;
						case Business.PEOPLE_MEDIATION:
							type = MEDIATION;
							break;
						case Business.FAST_LEGAL:
							type = FAST_LEGAL;
							break;
						default:
							break;
					}
					EvaluateCommitActivity.actionStart(mContext,
						bean.getCommentId(),
						bean.getEvaluatedList(),
						type);
				}
			});
		}
	}

	@Override
	public int getItemCount() {
		return mList == null ? 0 : mList.size();
	}

	public void initData(List<MyEvaluateBean> list) {
		if (mList != null) {
			mList.clear();
			mList.addAll(list);
			notifyDataSetChanged();
		}
	}

	public void addData(List<MyEvaluateBean> list) {
		if (mList != null) {
			mList.addAll(list);
			notifyDataSetChanged();
		}
	}

	static class MyEvaluateViewHolder extends ViewHolder {

		@BindView(R.id.tv_title)
		TextView mTvTitle;
		@BindView(R.id.tv_type)
		TextView mTvType;
		@BindView(R.id.tv_date)
		TextView mTvDate;
		@BindView(R.id.tv_evaluate)
		TextView mTvEvaluate;

		MyEvaluateViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}

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
import com.liuhezhineng.ximengsifa.adapter.StatisticalAdapter.StatisticalViewHolder;
import com.liuhezhineng.ximengsifa.bean.IntegrateQueryFilterBean;
import com.liuhezhineng.ximengsifa.bean.StatisticalBean;
import com.liuhezhineng.ximengsifa.module.IntegratedQueryActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/7/22
 */

public class StatisticalAdapter extends Adapter<StatisticalViewHolder> {

	private Context mContext;
	private ArrayList<StatisticalBean> mList;

	public StatisticalAdapter(Context context,
		ArrayList<StatisticalBean> list) {
		mContext = context;
		mList = new ArrayList<>();
		mList.addAll(list);
	}

	@NonNull
	@Override
	public StatisticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new StatisticalViewHolder(
			LayoutInflater.from(mContext).inflate(R.layout.statistical_item, parent, false)
		);
	}

	@Override
	public void onBindViewHolder(@NonNull StatisticalViewHolder holder, int position) {
		final StatisticalBean bean = mList.get(position);
		holder.mTvKey.setText(bean.getName());
		holder.mTvValue.setText(bean.getCount());

		holder.itemView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IntegrateQueryFilterBean mBean = new IntegrateQueryFilterBean();
				mBean.setCaseArea(bean.getCaseArea());
				mBean.setType(bean.getType());
				mBean.setCaseType(bean.getCaseType());
				mBean.setApplyBeginDate(bean.getApplyBeginDate());
				mBean.setAcceptEndDate(bean.getApplyEndDate());
				IntegratedQueryActivity.actionStart(mContext, mBean);
			}
		});
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	public void initData(List<StatisticalBean> list) {
		mList.clear();
		mList.addAll(list);
		notifyDataSetChanged();
	}

	static class StatisticalViewHolder extends ViewHolder {

		@BindView(R.id.tv_key)
		TextView mTvKey;
		@BindView(R.id.tv_value)
		TextView mTvValue;

		StatisticalViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}

package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.IntegratedQueryAdapter.IntegratedQueryViewHolder;
import com.liuhezhineng.ximengsifa.bean.IntegratedQueryBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/7/18
 */

public class IntegratedQueryAdapter extends Adapter<IntegratedQueryViewHolder> {

	private Context mContext;
	private ArrayList<IntegratedQueryBean> mList;

	public IntegratedQueryAdapter(Context context, ArrayList<IntegratedQueryBean> list) {
		mContext = context;
		mList = new ArrayList<>();
		mList.addAll(list);
	}

	@NonNull
	@Override
	public IntegratedQueryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new IntegratedQueryViewHolder(
			LayoutInflater.from(mContext)
				.inflate(R.layout.integrated_query_item, parent, false)
		);
	}

	@Override
	public void onBindViewHolder(@NonNull IntegratedQueryViewHolder holder, int position) {

		IntegratedQueryBean bean = mList.get(position);
		holder.mTvTitle.setText(bean.getTitle());
		holder.mTvDate.setText(bean.getApplyDate());
		holder.mTvArea.setText(bean.getCaseArea().getName());
		holder.mTvProcess.setText(bean.getStateDesc());
		holder.mTvRequestUser.setText(bean.getApplyUser());
		holder.mTvPhone.setText(bean.getPhone());
		holder.mTvCategory.setText(bean.getCaseTypeDesc());
	}

	@Override
	public int getItemCount() {
		return mList == null ? 0 : mList.size();
	}

	public void initData(List<IntegratedQueryBean> list) {
		mList.clear();
		mList.addAll(list);
		notifyDataSetChanged();
	}

	public void addData(List<IntegratedQueryBean> list) {
		mList.addAll(list);
		notifyDataSetChanged();
	}

	static class IntegratedQueryViewHolder extends ViewHolder {

		@BindView(R.id.tv_title)
		TextView mTvTitle;
		@BindView(R.id.tv_date)
		TextView mTvDate;
		@BindView(R.id.tv_area)
		TextView mTvArea;
		@BindView(R.id.tv_category)
		TextView mTvCategory;
		@BindView(R.id.tv_process)
		TextView mTvProcess;
		@BindView(R.id.tv_request_user)
		TextView mTvRequestUser;
		@BindView(R.id.tv_phone)
		TextView mTvPhone;

		IntegratedQueryViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}

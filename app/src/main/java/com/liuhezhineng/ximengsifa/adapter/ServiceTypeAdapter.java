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
import com.liuhezhineng.ximengsifa.adapter.ServiceTypeAdapter.ServiceTypeViewHolder;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.module.service.personnelinstitutions.evaluate.EvaluateDetailsActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/6/25
 */

public class ServiceTypeAdapter extends Adapter<ServiceTypeViewHolder> {

	private Context mContext;
	private List<TypeBean> mList;

	public ServiceTypeAdapter(Context context, List<TypeBean> list) {
		mContext = context;
		mList = new ArrayList<>();
		mList.addAll(list);
	}

	@NonNull
	@Override
	public ServiceTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(mContext)
			.inflate(R.layout.rv_item, parent, false);
		return new ServiceTypeViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ServiceTypeViewHolder holder, int position) {
		final TypeBean bean = mList.get(position);
		holder.mTvItem.setText(bean.getLabel());
		holder.itemView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((EvaluateDetailsActivity) mContext).typeFilter(bean);
			}
		});
	}

	@Override
	public int getItemCount() {
		return mList == null ? 0 : mList.size();
	}

	public void initData(List<TypeBean> list) {
		if (mList != null) {
			mList.clear();
			mList.addAll(list);
			notifyDataSetChanged();
		}
	}

	static class ServiceTypeViewHolder extends ViewHolder {

		@BindView(R.id.tv_item)
		TextView mTvItem;

		ServiceTypeViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}

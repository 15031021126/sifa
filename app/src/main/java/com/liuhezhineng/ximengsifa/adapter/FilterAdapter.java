package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.liuhezhineng.ximengsifa.adapter.FilterAdapter.FilterViewHolder;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.interfaces.OnFilterClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/7/18
 */

public class FilterAdapter extends Adapter<FilterViewHolder> {

	private Context mContext;
	private ArrayList<TypeBean> mList;
	private OnFilterClickListener mListener;
	private int flag;
	private TextView preView;

	public FilterAdapter(Context context, ArrayList<TypeBean> list, int flag) {
		mContext = context;
		mList = new ArrayList<>();
		mList.addAll(list);
		mListener = (OnFilterClickListener) mContext;
		this.flag = flag;
	}

	@NonNull
	@Override
	public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new FilterViewHolder(
			LayoutInflater.from(mContext).inflate(R.layout.sliding_filter_item, parent, false)
		);
	}

	@Override
	public void onBindViewHolder(@NonNull final FilterViewHolder holder, final int position) {
		final TypeBean bean = mList.get(position);
		holder.mTvFilter.setText(bean.getLabel());
		holder.mTvFilter.setTextColor(Color.BLACK);
		holder.mTvFilter.setBackgroundResource(R.drawable.gray_shape);
		holder.mTvFilter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 不是第一次点击
				if (preView != null) {
					preView.setBackgroundResource(R.drawable.gray_shape);
					preView.setTextColor(Color.BLACK);
					// 如果再次点击同一个
					if (preView == holder.mTvFilter) {
						mListener.filterClick(new TypeBean(), flag);
						preView = null;
						return;
					}
				}
				preView = holder.mTvFilter;
				holder.mTvFilter.setBackgroundResource(R.drawable.item_selected);
				holder.mTvFilter.setTextColor(Color.parseColor("#63AEFF"));
				mListener.filterClick(bean, flag);
			}
		});
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	public void reset() {
		if (preView != null) {
			preView.setTextColor(Color.BLACK);
			preView.setBackgroundResource(R.drawable.gray_shape);
			preView = null;
		}
	}

	public void initData(List<TypeBean> list) {
		mList.clear();
		mList.addAll(list);
		notifyDataSetChanged();
	}

	static class FilterViewHolder extends ViewHolder {

		@BindView(R.id.tv_filter)
		TextView mTvFilter;

		FilterViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}

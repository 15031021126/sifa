package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.CrimeAdapter.CrimeViewHolder;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author AIqinfeng
 * @date 2018/7/19
 */

public class CrimeAdapter extends Adapter<CrimeViewHolder> {

	private Context mContext;
	private ArrayList<TypeBean> mList;
	private Map<Integer, Boolean> isCheckMap;

	public CrimeAdapter(Context context,
		ArrayList<TypeBean> list,
		Map<Integer, Boolean> isCheckMap) {
		mContext = context;
		mList = new ArrayList<>();
		mList.addAll(list);
		this.isCheckMap = isCheckMap;
	}

	@NonNull
	@Override
	public CrimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new CrimeViewHolder(
			LayoutInflater.from(mContext).inflate(R.layout.crime_item, parent, false)
		);
	}

	@Override
	public void onBindViewHolder(@NonNull final CrimeViewHolder holder, final int position) {
		TypeBean bean = mList.get(position);
		holder.mTvCrime.setText(bean.getLabel());
		isCheckMap.put(position, false);
		holder.mCbCrime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isCheckMap.put(position, !isCheckMap.get(position));
				holder.mCbCrime.setChecked(isCheckMap.get(position));
			}
		});
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	public void initData(List<TypeBean> body) {
		mList.clear();
		mList.addAll(body);
		notifyDataSetChanged();
	}

	static class CrimeViewHolder extends ViewHolder {

		@BindView(R.id.tv_crime)
		TextView mTvCrime;
		@BindView(R.id.cb_crime)
		CheckBox mCbCrime;

		CrimeViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}

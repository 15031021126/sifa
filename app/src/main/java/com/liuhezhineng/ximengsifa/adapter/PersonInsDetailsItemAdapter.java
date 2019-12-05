package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.PersonInsDetailsBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @date 2018/6/29
 */

public class PersonInsDetailsItemAdapter extends Adapter<ViewHolder> {

	private static final int NORMAL_ITEM = 0;
	// footer 为机构简介，多行，所以需要靠左对齐
	private static final int FOOTER = 1;

	private Context mContext;
	private ArrayList<PersonInsDetailsBean> mList;

	public PersonInsDetailsItemAdapter(Context context,
		ArrayList<PersonInsDetailsBean> list) {
		mContext = context;
		mList = new ArrayList<>();
		mList.addAll(list);
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		if (viewType == FOOTER) {
			return new PersonInsDetailsFooterViewHolder(LayoutInflater.from(mContext)
				.inflate(R.layout.person_ins_details_footer, parent, false));
		} else {
			return new PersonInsDetailsViewHolder(LayoutInflater.from(mContext)
				.inflate(R.layout.institutions_details_item, parent, false));
		}
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		PersonInsDetailsBean bean = mList.get(position);
		if (TextUtils.isEmpty(bean.getValue())) {
			bean.setValue("暂无");
		}
		if (position == mList.size() - 1) {
			PersonInsDetailsFooterViewHolder footerViewHolder = (PersonInsDetailsFooterViewHolder) holder;
			footerViewHolder.mTvIntoKey.setText(bean.getKey());
			String footerValueStr = "\t\t\t\t" + bean.getValue();
			footerViewHolder.mTvIntroValue.setText(footerValueStr);
		} else {
			PersonInsDetailsViewHolder detailsViewHolder = (PersonInsDetailsViewHolder) holder;
			detailsViewHolder.mTvKey.setText(bean.getKey());
			detailsViewHolder.mTvValue.setText(bean.getValue());
		}
	}

	@Override
	public int getItemViewType(int position) {
		if (position == mList.size() - 1) {
			return FOOTER;
		} else {
			return NORMAL_ITEM;
		}
	}

	@Override
	public int getItemCount() {
		return mList == null ? 0 : mList.size();
	}

	static class PersonInsDetailsViewHolder extends ViewHolder {

		@BindView(R.id.tv_key)
		TextView mTvKey;
		@BindView(R.id.tv_value)
		TextView mTvValue;

		PersonInsDetailsViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}

	static class PersonInsDetailsFooterViewHolder extends ViewHolder {

		@BindView(R.id.tv_intro_key)
		TextView mTvIntoKey;
		@BindView(R.id.tv_intro_value)
		TextView mTvIntroValue;

		PersonInsDetailsFooterViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}

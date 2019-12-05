package com.liuhezhineng.ximengsifa.adapter;

import android.graphics.Color;
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
import com.liuhezhineng.ximengsifa.adapter.QaAdapter.QaViewHolder;
import com.liuhezhineng.ximengsifa.bean.advisory.GuestbookCommentReList;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/6/21
 * @description 追问追答适配器
 */

public class QaAdapter extends Adapter<QaViewHolder> {

	private ArrayList<GuestbookCommentReList> mList;

	public QaAdapter(List<GuestbookCommentReList> list) {
		mList = new ArrayList<>();
		mList.addAll(list);
	}

	@NonNull
	@Override
	public QaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.advisory_qa_item, null, false);
		return new QaViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull QaViewHolder holder, int position) {
		GuestbookCommentReList bean = mList.get(position);
		holder.mTvDate.setText(bean.getCreateDate());
		holder.mTvQaContent.setText(bean.getContent());
		if (bean.getCommentType().equals("0")) {
			holder.mTvQa.setText("追问：");
			holder.mTvQa.setTextColor(Color.RED);
		} else {
			holder.mTvQa.setText("追答：");
			holder.mTvQa.setTextColor(Color.BLUE);
		}
	}

	@Override
	public int getItemCount() {
		return mList == null ? 0 : mList.size();
	}

	public void initData(List<GuestbookCommentReList> list) {
		mList.clear();
		mList.addAll(list);
		notifyDataSetChanged();
	}

	static class QaViewHolder extends ViewHolder {

		@BindView(R.id.tv_qa)
		TextView mTvQa;
		@BindView(R.id.tv_qa_content)
		TextView mTvQaContent;
		@BindView(R.id.tv_date)
		TextView mTvDate;

		QaViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}

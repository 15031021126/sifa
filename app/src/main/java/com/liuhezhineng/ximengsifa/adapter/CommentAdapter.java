package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.CommentAdapter.CommentViewHolder;
import com.liuhezhineng.ximengsifa.bean.article.CommentBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/7/2
 */

public class CommentAdapter extends Adapter<CommentViewHolder> {

	private Context mContext;
	private ArrayList<CommentBean> mList;

	public CommentAdapter(Context context,
		ArrayList<CommentBean> list) {
		mContext = context;
		mList = new ArrayList<>();
		mList.addAll(list);
	}

	@NonNull
	@Override
	public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new CommentViewHolder(
			LayoutInflater.from(mContext).inflate(R.layout.comment_item, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
		CommentBean bean = mList.get(position);
		Picasso.get().load(NetConstant.FILE_URL + bean.getPhoto())
			.placeholder(R.drawable.default_person)
			.into(holder.mIvAvatar);
		holder.mTvName.setText(bean.getName());
		holder.mTvCommentDate.setText(bean.getCreateDate());
		holder.mTvCommentContent.setText(bean.getContent());
	}

	@Override
	public int getItemCount() {
		return mList == null ? 0 : mList.size();
	}

	public void initData(List<CommentBean> list) {
		if (mList != null) {
			mList.clear();
			mList.addAll(list);
			notifyDataSetChanged();
		}
	}

	static class CommentViewHolder extends ViewHolder {

		@BindView(R.id.iv_avatar)
		ImageView mIvAvatar;
		@BindView(R.id.tv_name)
		TextView mTvName;
		@BindView(R.id.tv_comment_date)
		TextView mTvCommentDate;
		@BindView(R.id.tv_comment_content)
		TextView mTvCommentContent;

		CommentViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}

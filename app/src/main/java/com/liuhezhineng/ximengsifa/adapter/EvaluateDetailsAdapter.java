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
import com.liuhezhineng.ximengsifa.adapter.EvaluateDetailsAdapter.EvaluateDetailsViewHolder;
import com.liuhezhineng.ximengsifa.bean.EvaluateBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/6/26
 * @description 评价详情业评价列表适配器
 */

public class EvaluateDetailsAdapter extends Adapter<EvaluateDetailsViewHolder> {

	private Context mContext;
	private ArrayList<EvaluateBean> mList;
	private ImageView[] ivArray;

	public EvaluateDetailsAdapter(Context context, ArrayList<EvaluateBean> list) {
		mContext = context;
		mList = new ArrayList<>();
		mList.addAll(list);
	}

	@NonNull
	@Override
	public EvaluateDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new EvaluateDetailsViewHolder(
			LayoutInflater.from(mContext).inflate(R.layout.evaluate_details_item, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull EvaluateDetailsViewHolder holder, int position) {
		EvaluateBean bean = mList.get(position);
		holder.mTvType.setText(bean.getType());
		holder.mTvEvaluateContent.setText(bean.getProposal());
		Integer prescription = Integer.parseInt(bean.getPrescription());
		ivArray = new ImageView[]{holder.mIv1, holder.mIv2, holder.mIv3, holder.mIv4, holder.mIv5};
		setRating(prescription);
		Picasso.get().load(NetConstant.FILE_URL + bean.getBeEvaluatedUser().getPhoto())
			.placeholder(R.drawable.default_person)
			.into(holder.mIvAvatar);
		holder.mTvName.setText(bean.getBeEvaluatedUser().getName());
		holder.mTvDate.setText(bean.getCreateDate());
	}

	private void setRating(int rating) {
		for (int i = 0; i < rating; i++) {
			ivArray[i].setBackgroundResource(R.drawable.rating_checked);
		}
		for (int i = rating; i < ivArray.length; i++) {
			ivArray[i].setBackgroundResource(R.drawable.rating_not_checked);
		}
	}

	@Override
	public int getItemCount() {
		return mList == null ? 0 : mList.size();
	}

	public void initData(List<EvaluateBean> list) {
		if (mList != null) {
			mList.clear();
			mList.addAll(list);
			notifyDataSetChanged();
		}
	}

	public void addData(List<EvaluateBean> list) {
		if (mList != null) {
			mList.addAll(list);
			notifyDataSetChanged();
		}
	}

	static class EvaluateDetailsViewHolder extends ViewHolder {

		@BindView(R.id.tv_type)
		TextView mTvType;
		@BindView(R.id.iv_1)
		ImageView mIv1;
		@BindView(R.id.iv_2)
		ImageView mIv2;
		@BindView(R.id.iv_3)
		ImageView mIv3;
		@BindView(R.id.iv_4)
		ImageView mIv4;
		@BindView(R.id.iv_5)
		ImageView mIv5;
		@BindView(R.id.tv_evaluate_content)
		TextView mTvEvaluateContent;
		@BindView(R.id.iv_avatar)
		ImageView mIvAvatar;
		@BindView(R.id.tv_name)
		TextView mTvName;
		@BindView(R.id.tv_date)
		TextView mTvDate;

		EvaluateDetailsViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}

package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.bussiness.DraftBean;
import com.liuhezhineng.ximengsifa.business.legalaid.ApplyForLegalAidActivity;
import com.liuhezhineng.ximengsifa.business.noticedefense.NoticeDefenseActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.ApplyForPeoplesMediationActivity;
import com.liuhezhineng.ximengsifa.constant.Constant.Draft;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @date 2018/5/22
 * @description 业务列表适配器
 */

public class DraftAdapter extends Adapter<DraftViewHolder> {

	public static final int DEL_DRAFT = 0;
	private final List<DraftBean> mList;
	private Context mContext;

	public DraftAdapter(Context context, List<DraftBean> items) {
		mContext = context;
		mList = new ArrayList<>();
		mList.addAll(items);
	}

	@NonNull
	@Override
	public DraftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.business_item, parent, false);
		return new DraftViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull DraftViewHolder holder, int position) {
		final DraftBean bean = mList.get(position);
		if (!TextUtils.isEmpty(bean.getTitle())) {
			holder.mTvTitle.setText(bean.getTitle());
		} else {
			holder.mTvTitle.setText("无标题");
		}
		String title = "";
		if (!TextUtils.isEmpty(bean.getProcDefKey())) {
			switch (bean.getProcDefKey()) {
				case Draft.PEOPLE_MEDIATION:
					title = "人民调解";
					break;
				case Draft.LEGAL_AID:
					title = "申请法援";
					break;
				case Draft.NOTICE_DEFENSE:
					title = "通知辩护";
					break;
				default:
					break;
			}
		}
		holder.mTvTaskName.setText(title);
		holder.mTvProcDefName.setText("草稿");
		holder.mTvCreateTime.setText(bean.getBeginDate());

		holder.itemView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (bean.getProcDefKey()) {
					case Draft.PEOPLE_MEDIATION:
						ApplyForPeoplesMediationActivity
							.actionStart(mContext, bean.getId(), bean.getProcDefKey());
						break;
					case Draft.LEGAL_AID:
						ApplyForLegalAidActivity
							.actionStart(mContext, bean.getId(), bean.getProcDefKey());
						break;
					case Draft.NOTICE_DEFENSE:
						NoticeDefenseActivity
							.actionStart(mContext, bean.getId(), bean.getProcDefKey());
						break;
					default:
						break;
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return mList == null ? 0 : mList.size();
	}

	public void addData(List<DraftBean> list) {
		if (mList != null) {
			mList.addAll(list);
			notifyDataSetChanged();
		}
	}

	public void initData(List<DraftBean> list) {
		if (mList != null) {
			mList.clear();
			mList.addAll(list);
			notifyDataSetChanged();
		}
	}
}

class DraftViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.tv_title)
	TextView mTvTitle;
	@BindView(R.id.tv_task_name)
	TextView mTvTaskName;
	@BindView(R.id.tv_proc_def_name)
	TextView mTvProcDefName;
	@BindView(R.id.tv_create_time)
	TextView mTvCreateTime;

	DraftViewHolder(View view) {
		super(view);
		ButterKnife.bind(this, view);
	}
}


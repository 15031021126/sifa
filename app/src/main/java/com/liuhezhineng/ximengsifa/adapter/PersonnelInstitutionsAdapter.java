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
import com.liuhezhineng.ximengsifa.adapter.PersonnelInstitutionsAdapter.PersonnelInstitutionsViewHolder;
import com.liuhezhineng.ximengsifa.bean.AgencyBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/5/2
 */

public class PersonnelInstitutionsAdapter extends Adapter<PersonnelInstitutionsViewHolder> {

	private Context mContext;
	private List mList;

	public PersonnelInstitutionsAdapter(Context context, List list) {
		mContext = context;
		mList = new ArrayList();
		mList.addAll(list);
	}

	@NonNull
	@Override
	public PersonnelInstitutionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
		int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.personnel_institutions_item, parent, false);
		return new PersonnelInstitutionsViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PersonnelInstitutionsViewHolder holder, int position) {
		AgencyBean bean = (AgencyBean) mList.get(position);
		holder.mTvInstitutionsName.setText(bean.getAgencyName());
		holder.mTvInstitutionsTel.setText("电话：" + bean.getAgencyPhone());
		holder.mTvInstitutionsAddress.setText("地址：" + bean.getAgencyAddress());
	}

	@Override
	public int getItemCount() {
		return mList == null ? 0 : mList.size();
	}

	public void updateData(List<AgencyBean> list) {
		if (mList != null) {
			mList.clear();
			mList.addAll(list);
			notifyDataSetChanged();
		}
	}

	static class PersonnelInstitutionsViewHolder extends ViewHolder {

		@BindView(R.id.iv_institutions)
		ImageView mIvInstitutions;
		@BindView(R.id.tv_institutions_name)
		TextView mTvInstitutionsName;
		@BindView(R.id.tv_institutions_tel)
		TextView mTvInstitutionsTel;
		@BindView(R.id.tv_institutions_address)
		TextView mTvInstitutionsAddress;

		PersonnelInstitutionsViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}

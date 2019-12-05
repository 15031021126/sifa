package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.dto.vo.ServerAppVo;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/4/16
 * @description 一级服务适配器
 */

public class ServiceAdapter extends BaseAdapter {

	private int selectedItemPosition;

	private Context mContext;
	private List<ServerAppVo> mList;

	public ServiceAdapter(Context context, List<ServerAppVo> list) {
		mContext = context;
		mList = list;
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext)
				.inflate(R.layout.service_item, parent, false);
			holder.mView = convertView.findViewById(R.id.selected_view);
			holder.mTvServiceCategory = convertView.findViewById(R.id.tv_service_category);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final ServerAppVo service = mList.get(position);
		holder.mTvServiceCategory.setText(service.getName());
		if (selectedItemPosition == position) {
			holder.mView.setVisibility(View.VISIBLE);
			holder.mTvServiceCategory
				.setTextColor(mContext.getResources()
					.getColor(R.color.qmui_config_color_red));
			holder.mTvServiceCategory.setTextSize(18);
		} else {
			holder.mView.setVisibility(View.GONE);
			holder.mTvServiceCategory
				.setTextColor(mContext.getResources().getColor(android.R.color.background_dark));
			holder.mTvServiceCategory.setTextSize(16);
		}
		return convertView;
	}

	public void setItemSelected(int position) {
		this.selectedItemPosition = position;
		notifyDataSetChanged();
	}

	public void updateData(List<ServerAppVo> list) {
		if (mList != null) {
			mList.clear();
			mList.addAll(list);
			notifyDataSetChanged();
		}
	}

	private static class ViewHolder {

		private View mView;
		private TextView mTvServiceCategory;
	}
}
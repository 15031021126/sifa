package com.liuhezhineng.ximengsifa.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.BusinessAdapter.BusinessViewHolder;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.interfaces.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @date 2018/5/22
 * @description 业务列表适配器
 */

public class BusinessAdapter extends Adapter<BusinessViewHolder> {

    private final List<BusinessBean> mList;
    private final OnListFragmentInteractionListener mListener;

    public BusinessAdapter(List<BusinessBean> items,
                           OnListFragmentInteractionListener listener) {
        mList = new ArrayList<>();
        mList.addAll(items);
        mListener = listener;
    }

    @NonNull
    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.business_item, parent, false);
        return new BusinessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessViewHolder holder, int position) {
        final BusinessBean bean = mList.get(position);
        holder.mTvTitle.setText(bean.getVars().getMap().getTitle());
        holder.mTvTaskName.setText(bean.getTask().getName());
        holder.mTvProcDefName.setText(bean.getProcDefName());
        holder.mTvCreateTime.setText(bean.getTask().getCreateTime());

        holder.itemView.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onListFragmentInteraction(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == mList ? 0 : mList.size();
    }

    public void addData(List<BusinessBean> list) {
        if (mList != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void initData(List<BusinessBean> list) {
        if (mList != null) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    static class BusinessViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_task_name)
        TextView mTvTaskName;
        @BindView(R.id.tv_proc_def_name)
        TextView mTvProcDefName;
        @BindView(R.id.tv_create_time)
        TextView mTvCreateTime;

        BusinessViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}


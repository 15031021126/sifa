package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.MsgDetailsActivity;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.msg.LocalMsg;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @date 2018/6/20
 * @description 站内消息列表
 */

public class LocalMsgAdapter extends Adapter<LocalMsgAdapter.ViewHolder> {

    private final List<LocalMsg> mList;
    private Context mContext;

    public LocalMsgAdapter(Context context, List<LocalMsg> items) {
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_local_msg, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final LocalMsg bean = mList.get(position);
        holder.mTvMsgName.setText(bean.getTitle());
        // 0:unread;1:read TODO: spannable string
        if ("1".equals(bean.getReadFlag())) {
            holder.mTvReadStatus.setText(R.string.read);
            holder.mTvMsgName.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
            holder.mTvReadStatus.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
        } else {
            holder.mTvReadStatus.setText(R.string.unread);
            holder.mTvReadStatus.setTextColor(mContext.getResources().getColor(android.R.color.holo_red_dark));
        }
        holder.mTvCreateUser.setText(bean.getCreateBy().getName());
        holder.mTvMsgType.setText(bean.getTypeDesc());
        holder.mTvDate.setText(bean.getCreateDate());
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MsgDetailsActivity.actionStart(mContext, bean.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void initData(List<LocalMsg> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(List<LocalMsg> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_msg_name)
        TextView mTvMsgName;
        @BindView(R.id.tv_read_status)
        TextView mTvReadStatus;
        @BindView(R.id.tv_create_user)
        TextView mTvCreateUser;
        @BindView(R.id.tv_msg_type)
        TextView mTvMsgType;
        @BindView(R.id.tv_date)
        TextView mTvDate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

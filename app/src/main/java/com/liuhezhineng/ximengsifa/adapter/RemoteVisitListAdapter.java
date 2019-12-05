package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.form.VisitApply;
import com.liuhezhineng.ximengsifa.form.RemoteVisitorReviewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : qingfeng
 * e-mail : 1913518036@qq.com
 * time   : 2019/01/19
 * desc   :
 * version: 1.0
 */
public class RemoteVisitListAdapter extends RecyclerView.Adapter<RemoteVisitListAdapter.RemoteVisitListViewHolder> {

    private Context mContext;
    private ArrayList<VisitApply> mList;
    private String todoType;

    public RemoteVisitListAdapter(Context context, List<VisitApply> list, String todoType) {
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(list);
        this.todoType = todoType;
    }

    @NonNull
    @Override
    public RemoteVisitListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_remote_visit_list, parent, false);
        return new RemoteVisitListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RemoteVisitListViewHolder holder, int position) {
        final VisitApply visitApply = mList.get(position);
        holder.mTvState.setText(visitApply.getStateDesc());
        holder.mTvTitle.setText(visitApply.getApplyName() + "探访" + visitApply.getVisitorName());
        holder.mTvDate.setText(visitApply.getCreateDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoteVisitorReviewActivity.actionStart(mContext, visitApply, todoType);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void initData(List<VisitApply> list) {
        if (mList != null) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addData(List<VisitApply> list) {
        if (mList != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    static class RemoteVisitListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_state)
        TextView mTvState;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_date)
        TextView mTvDate;

        RemoteVisitListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

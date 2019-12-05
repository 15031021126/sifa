package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.order.OrderBean;
import com.liuhezhineng.ximengsifa.module.mine.order.OrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @date 2018/6/21
 * @description 我的预约适配器
 */

public class OrderAdapter extends Adapter<OrderAdapter.OrderViewHolder> {

    private Context mContext;
    private ArrayList<OrderBean> mList;
    private String from;

    public OrderAdapter(Context context, List<OrderBean> list, String from) {
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(list);
        this.from = from;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_order_list, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        final OrderBean bean = mList.get(position);
        holder.mTvIsDeal.setText(bean.getStateDesc());
        // 预约状态0待处理1预约成功2预约失败
        switch (bean.getState()) {
            case "0":
                holder.mTvIsDeal.setBackgroundColor(Color.BLUE);
                break;
            case "1":
                holder.mTvIsDeal.setBackgroundColor(Color.GRAY);
                break;
            default:
                holder.mTvIsDeal.setBackgroundColor(Color.RED);
                break;
        }
        holder.mTvMold.setText(bean.getMoldDesc());
        holder.mTvDate.setText(bean.getCreateDate());
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDetailsActivity.actionStart(mContext, bean, from);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void initData(List<OrderBean> list) {
        if (mList != null) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addData(List<OrderBean> list) {
        if (mList != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    static class OrderViewHolder extends ViewHolder {

        @BindView(R.id.tv_is_deal)
        TextView mTvIsDeal;
        @BindView(R.id.tv_mold)
        TextView mTvMold;
        @BindView(R.id.tv_date)
        TextView mTvDate;

        OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

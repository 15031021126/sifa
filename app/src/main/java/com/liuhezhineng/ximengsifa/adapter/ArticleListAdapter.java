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

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.ArticleListAdapter.ViewHolder;
import com.liuhezhineng.ximengsifa.bean.ArticleBean;
import com.liuhezhineng.ximengsifa.module.home.ArticleDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @date 2018/6/20
 * @description 文章列表
 */

public class ArticleListAdapter extends Adapter<ViewHolder> {

    private final List<ArticleBean> mValues;
    private Context mContext;

    public ArticleListAdapter(Context context, List<ArticleBean> items) {
        mContext = context;
        mValues = new ArrayList<>();
        mValues.addAll(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_article_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ArticleBean bean = mValues.get(position);
        holder.mTvName.setText(bean.getTitle());
        holder.mTvDate.setText(bean.getUpdateDate());
        holder.mTvCreateUser.setText(bean.getCreateByName());
        holder.mTvArea.setText(bean.getAreaName());

        holder.itemView.setOnClickListener(v -> ArticleDetailsActivity.actionStart(mContext, bean));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void initData(List<ArticleBean> list) {
        if (mValues != null) {
            mValues.clear();
            mValues.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addData(List<ArticleBean> list) {
        mValues.addAll(list);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.tv_create_user)
        TextView mTvCreateUser;
        @BindView(R.id.tv_area)
        TextView mTvArea;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

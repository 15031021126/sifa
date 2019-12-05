package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.ArticleBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.module.home.ArticleDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 */
public class ArticleAdapter extends Adapter<ArticleAdapter.InfoViewHolder> {

    private List<ArticleBean> mList;
    private OnClickListener mListener;
    private LayoutInflater mLayoutInflater;

    public ArticleAdapter(Context context, List list) {
        mList = new ArrayList<>();
        mList.addAll(list);
        mLayoutInflater = LayoutInflater.from(context);
        mListener = view ->
                ArticleDetailsActivity.actionStart(context, (ArticleBean) view.getTag());
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.article_item, parent, false);
        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        final ArticleBean bean = mList.get(position);
        Picasso.get()
                .load(NetConstant.FILE_URL + bean.getImage())
                .placeholder(R.drawable.info_show_test)
                .into(holder.mIvInfoShow);
        holder.mTvInfoTitle.setText(bean.getTitle());
        holder.mTvInfoFrom.setText(bean.getSiteName());
        holder.itemView.setTag(bean);
        holder.itemView.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return null == mList ? 0 : mList.size();
    }

    public void initData(List<ArticleBean> list) {
        if (mList != null) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addData(List<ArticleBean> list) {
        if (mList != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    static class InfoViewHolder extends ViewHolder {

        @BindView(R.id.iv_article_show)
        ImageView mIvInfoShow;
        @BindView(R.id.tv_article_title)
        TextView mTvInfoTitle;
        @BindView(R.id.tv_info_from)
        TextView mTvInfoFrom;

        InfoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
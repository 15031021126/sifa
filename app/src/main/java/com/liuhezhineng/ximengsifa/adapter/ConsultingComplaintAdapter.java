package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.ConsultingComplaintAdapter.AdvisoryMessagesViewHolder;
import com.liuhezhineng.ximengsifa.bean.advisory.AdvisoryBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint.ComplaintDistributionActivity;
import com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint.ConsultingComplaintDetailsActivity;
import com.liuhezhineng.ximengsifa.utils.UserHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @date 2018/6/21
 * @description 我的咨询、投诉建议列表适配器
 */

public class ConsultingComplaintAdapter extends Adapter<AdvisoryMessagesViewHolder> {

    private Context mContext;
    private ArrayList<AdvisoryBean> mList;
    /**
     * 留言咨询还是意见投诉
     */
    private String businessType;
    /**
     * 我的留言咨询还是待处理的留言咨询
     */
    private String todoType;

    public ConsultingComplaintAdapter(Context context, ArrayList<AdvisoryBean> list, String businessType, String todoType) {
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(list);
        this.businessType = businessType;
        this.todoType = todoType;
    }

    @NonNull
    @Override
    public AdvisoryMessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.advisory_messages_item, parent, false);
        return new AdvisoryMessagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvisoryMessagesViewHolder holder, int position) {
        final AdvisoryBean bean = mList.get(position);
        holder.mTitle.setText(bean.getTitle());
        if (Constant.CONSULTING.equals(businessType)) {
            holder.mCaseType.setText(bean.getIsComment().equals("0") ? "无回复" : "已回复");
        } else {
            if (!UserHelper.isIsNormalUser() && todoType.equals(mContext.getString(R.string.my_complaint_to_do))) {
                holder.mCaseType.setText(bean.getStateDesc());
            } else {
                holder.mCaseType.setText(bean.getIsComment().equals("0") ? "无回复" : "已回复");
            }
        }
        holder.mTvDate.setText(bean.getCreateDate());
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UserHelper.isIsNormalUser() && todoType.equals(mContext.getString(R.string.my_complaint_to_do))) {
                    switch (bean.getState()) {
                        // 待分配
                        case "0":
                            ComplaintDistributionActivity.actionStart(mContext, bean);
                            break;
                        // 待处理
                        case "1":
                            // 分配给自己的话，就进入回复页面
                            if (bean.getDistributeUser().getId().equals(bean.getHandleUser().getId())) {
                                ConsultingComplaintDetailsActivity.actionStart(mContext, bean, businessType, todoType);
                            } else { // 分配给他人的话就进入只能查看页面，相当于 ->
                                ConsultingComplaintDetailsActivity.actionStart(mContext, bean, businessType, mContext.getString(R.string.my_complaint));
                            }
                            break;
                        // 已处理
                        case "2":
                            ConsultingComplaintDetailsActivity.actionStart(mContext, bean, businessType, todoType);
                            break;
                        default:
                            break;
                    }
                } else {
                    ConsultingComplaintDetailsActivity.actionStart(mContext, bean, businessType, todoType);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void initData(List<AdvisoryBean> list) {
        if (mList != null) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addData(List<AdvisoryBean> list) {
        if (mList != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    static class AdvisoryMessagesViewHolder extends ViewHolder {

        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.tv_is_comment)
        TextView mCaseType;
        @BindView(R.id.tv_date)
        TextView mTvDate;

        AdvisoryMessagesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

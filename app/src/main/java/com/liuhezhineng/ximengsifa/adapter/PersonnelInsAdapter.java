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
import android.widget.ImageView;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.personal.PersonInsBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.module.service.personnelinstitutions.PersonnelInstitutionsActivity;
import com.liuhezhineng.ximengsifa.module.service.personnelinstitutions.PersonnelInstitutionsDetailsActivity;
import com.liuhezhineng.ximengsifa.module.service.personnelinstitutions.evaluate.EvaluateDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @date 2018/6/23
 * @description 人员机构适配器 满意度
 */

public class PersonnelInsAdapter extends Adapter<ViewHolder> {

    private Context mContext;
    private ArrayList<PersonInsBean> mList;
    private String name;
    private ImageView[] ivArray;
    private String categoryId;

    public PersonnelInsAdapter(Context context, ArrayList<PersonInsBean> list,
                               String name, String categoryId) {
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(list);
        this.name = name;
        this.categoryId = categoryId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if ("满意度评价".equals(((PersonnelInstitutionsActivity) mContext).mServerAppVo.getLink())) {
            return new EvaluationViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.evaluation_item, parent, false));
        } else {
            return new PersonnelViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.person_ins_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PersonInsBean bean = mList.get(position);

        String link = ((PersonnelInstitutionsActivity) mContext).mServerAppVo.getLink();
        if ("满意度评价".equals(link)) {
            EvaluationViewHolder evaluationViewHolder = (EvaluationViewHolder) holder;
            evaluationViewHolder.mIvAvatar.setBackgroundResource(R.drawable.default_person);
            Picasso.get().load(NetConstant.FILE_URL + bean.getImageUrl())
                    .placeholder(R.drawable.default_personnel_rect)
                    .into(evaluationViewHolder.mIvAvatar);
            evaluationViewHolder.mTvName.setText(bean.getPersonName());
            evaluationViewHolder.mTvPhone.setText(bean.getAgencyPhone());
            evaluationViewHolder.mTvPersonType.setText(bean.getRoleId());
            evaluationViewHolder.mTvPersonType.setText("工号：" + bean.getNo());
            evaluationViewHolder.mTvCommittee.setText(bean.getAgencyName());
            ivArray = new ImageView[]{evaluationViewHolder.mIv1, evaluationViewHolder.mIv2,
                    evaluationViewHolder.mIv3, evaluationViewHolder.mIv4, evaluationViewHolder.mIv5};
            try {
                int evaluate = Integer.parseInt(bean.getEvaluation());
                setRating(evaluate);
            } catch (Exception e) {
                setRating(0);
            }

            evaluationViewHolder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    EvaluateDetailsActivity.actionStart(mContext, bean);
                }
            });
        } else {
            PersonnelViewHolder personnelViewHolder = (PersonnelViewHolder) holder;
            if ("人员查询".equals(link) || "法援人员查询".equals(link)) {
                // 0:离线;1:在线 getLoginStatus TODO: spannable string
                if ("1".equals(bean.getLoginStatus())) {
                    personnelViewHolder.mTvLoginStatus.setText(R.string.online);
                    personnelViewHolder.mTvLoginStatus.setTextColor(Color.BLACK);
                } else {
                    personnelViewHolder.mTvLoginStatus.setText(R.string.off_line);
                    personnelViewHolder.mTvLoginStatus.setTextColor(Color.GRAY);
                }
                Picasso.get().load(NetConstant.FILE_URL + bean.getImageUrl())
                        .placeholder(R.drawable.default_personnel_rect)
                        .into(personnelViewHolder.mIvAvatar);
                personnelViewHolder.mTvName.setText(bean.getPersonName());
                personnelViewHolder.mTvIsMongolian.setText("蒙汉双通：" + bean.getIsMongolian());
                personnelViewHolder.mTvCommittee.setText("所属机构：" + bean.getAgencyName());
            } else {
                Picasso.get().load(NetConstant.FILE_URL + bean.getImageUrl())
                        .placeholder(R.drawable.default_institution_rect)
                        .into(personnelViewHolder.mIvAvatar);
                personnelViewHolder.mTvName.setText(bean.getAgencyName());
                personnelViewHolder.mTvIsMongolian.setText("联系电话：" + bean.getAgencyPhone());
                personnelViewHolder.mTvCommittee.setText("所在地址：" + bean.getAgencyAddress());
            }
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String link = ((PersonnelInstitutionsActivity) mContext).mServerAppVo.getLink();
                    if ("人员查询".equals(link) || "法援人员查询".equals(link)) {
                        PersonnelInstitutionsDetailsActivity.actionStart(mContext, bean, name, "0", categoryId);
                    } else {
                        PersonnelInstitutionsDetailsActivity.actionStart(mContext, bean, name, "1", categoryId);
                    }
                }
            });
        }
    }

    private void setRating(int rating) {
        for (int i = 0; i < rating; i++) {
            ivArray[i].setBackgroundResource(R.drawable.rating_checked);
        }
        for (int i = rating; i < ivArray.length; i++) {
            ivArray[i].setBackgroundResource(R.drawable.rating_not_checked);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private void setEvaluate(EvaluationViewHolder holder, int evaluate) {
        switch (evaluate) {
            case 1:
                holder.mIv1.setBackgroundResource(R.drawable.rating_checked);
                break;
            case 2:
                holder.mIv1.setBackgroundResource(R.drawable.rating_checked);
                holder.mIv2.setBackgroundResource(R.drawable.rating_checked);
                break;
            case 3:
                holder.mIv1.setBackgroundResource(R.drawable.rating_checked);
                holder.mIv2.setBackgroundResource(R.drawable.rating_checked);
                holder.mIv3.setBackgroundResource(R.drawable.rating_checked);
                break;
            case 4:
                holder.mIv1.setBackgroundResource(R.drawable.rating_checked);
                holder.mIv2.setBackgroundResource(R.drawable.rating_checked);
                holder.mIv3.setBackgroundResource(R.drawable.rating_checked);
                holder.mIv4.setBackgroundResource(R.drawable.rating_checked);
                break;
            case 5:
                holder.mIv1.setBackgroundResource(R.drawable.rating_checked);
                holder.mIv2.setBackgroundResource(R.drawable.rating_checked);
                holder.mIv3.setBackgroundResource(R.drawable.rating_checked);
                holder.mIv4.setBackgroundResource(R.drawable.rating_checked);
                holder.mIv5.setBackgroundResource(R.drawable.rating_checked);
                break;
            default:
                break;
        }
    }

    public void initData(List<PersonInsBean> list) {
        if (mList != null) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addData(List<PersonInsBean> list) {
        if (mList != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    static class PersonnelViewHolder extends ViewHolder {

        @BindView(R.id.iv_avatar)
        ImageView mIvAvatar;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_is_mongolian)
        TextView mTvIsMongolian;
        @BindView(R.id.tv_committee)
        TextView mTvCommittee;
        @BindView(R.id.tv_login_status)
        TextView mTvLoginStatus;

        PersonnelViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class EvaluationViewHolder extends ViewHolder {

        @BindView(R.id.iv_avatar)
        ImageView mIvAvatar;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_phone)
        TextView mTvPhone;
        @BindView(R.id.tv_person_type)
        TextView mTvPersonType;
        @BindView(R.id.tv_committee)
        TextView mTvCommittee;
        @BindView(R.id.iv_1)
        ImageView mIv1;
        @BindView(R.id.iv_2)
        ImageView mIv2;
        @BindView(R.id.iv_3)
        ImageView mIv3;
        @BindView(R.id.iv_4)
        ImageView mIv4;
        @BindView(R.id.iv_5)
        ImageView mIv5;

        EvaluationViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

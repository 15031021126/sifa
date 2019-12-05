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
import com.liuhezhineng.ximengsifa.adapter.BeEvaluateUserAdapter.BeEvaluateUserViewHolder;
import com.liuhezhineng.ximengsifa.bean.evaluate.BeEvaluateCommitInfoBean;
import com.liuhezhineng.ximengsifa.bean.evaluate.BeEvaluateUserBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.interfaces.OnPrescriptionListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author AIqinfeng
 * @date 2018/7/9
 */

public class BeEvaluateUserAdapter extends Adapter<BeEvaluateUserViewHolder> {

    private ArrayList<BeEvaluateUserBean> mList;
    private OnPrescriptionListener mListener;
    private ArrayList<BeEvaluateCommitInfoBean> mBeEvaluateCommitInfoBeanArrayList;
    private LayoutInflater mInflater;

    public BeEvaluateUserAdapter(Context context,
                                 ArrayList<BeEvaluateUserBean> list) {
        mListener = (OnPrescriptionListener) context;
        mList = new ArrayList<>();
        mList.addAll(list);
        mBeEvaluateCommitInfoBeanArrayList = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BeEvaluateUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BeEvaluateUserViewHolder(
                mInflater.inflate(R.layout.layout_be_evaluate_user_item,
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull BeEvaluateUserViewHolder holder, int position) {
        BeEvaluateUserBean beEvaluateUserBean = mList.get(position);
        Picasso.get().load(NetConstant.FILE_URL + beEvaluateUserBean.getAvatar())
                .placeholder(R.drawable.default_person)
                .into(holder.mIvAvatar);
        holder.mTvRole.setText(beEvaluateUserBean.getName() + "-" + beEvaluateUserBean.getRoleName());

        final BeEvaluateCommitInfoBean bean = new BeEvaluateCommitInfoBean();
        mBeEvaluateCommitInfoBeanArrayList.add(bean);
        bean.setBeEvaluatedUserId(beEvaluateUserBean.getBeEvaluatedUserId());

        final ImageView[] ivArray = new ImageView[]{holder.mIv1, holder.mIv2, holder.mIv3, holder.mIv4, holder.mIv5};
        holder.mIv1.setOnClickListener(v -> {
            bean.setPrescription("1");
            setRating(1, ivArray);
        });
        holder.mIv2.setOnClickListener(v -> {
            bean.setPrescription("2");
            setRating(2, ivArray);
        });
        holder.mIv3.setOnClickListener(v -> {
            bean.setPrescription("3");
            setRating(3, ivArray);
        });
        holder.mIv4.setOnClickListener(v -> {
            bean.setPrescription("4");
            setRating(4, ivArray);
        });
        holder.mIv5.setOnClickListener(v -> {
            bean.setPrescription("5");
            setRating(5, ivArray);
        });
    }

    private void setRating(int rating, ImageView[] ivArray) {
        mListener.setPrescription(mBeEvaluateCommitInfoBeanArrayList);
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

    static class BeEvaluateUserViewHolder extends ViewHolder {

        @BindView(R.id.iv_avatar)
        CircleImageView mIvAvatar;
        @BindView(R.id.tv_role)
        TextView mTvRole;
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

        BeEvaluateUserViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

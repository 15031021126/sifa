package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.ConsultingComplaintReplyAdapter.AdvisoryReplyViewHolder;
import com.liuhezhineng.ximengsifa.bean.advisory.CommentList;
import com.liuhezhineng.ximengsifa.bean.advisory.CreateUser;
import com.liuhezhineng.ximengsifa.bean.advisory.GuestbookCommentReList;
import com.liuhezhineng.ximengsifa.bean.evaluate.BeEvaluateUserBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.MyStringCallback;
import com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint.QaActivity;
import com.liuhezhineng.ximengsifa.module.service.personnelinstitutions.evaluate.EvaluateCommitActivity;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @date 2018/6/21
 * @description 咨询、投诉回复适配器
 */

public class ConsultingComplaintReplyAdapter extends Adapter<AdvisoryReplyViewHolder> {

    private Context mContext;
    private ArrayList<CommentList> mList;
    private String type;
    private String todoType;

    public ConsultingComplaintReplyAdapter(Context context,
                                           ArrayList<CommentList> list, String type, String todoType) {
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(list);
        this.type = type;
        this.todoType = todoType;
    }

    @NonNull
    @Override
    public AdvisoryReplyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdvisoryReplyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.reply_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final AdvisoryReplyViewHolder holder, int position) {
        final CommentList commentList = mList.get(position);

        holder.mTvReplyUser.setText(commentList.getCreateUser().getName());
        holder.mTvPraise.setText("(" + commentList.getThumbsUpTrue() + ")");
        holder.mTvGeneral.setText("(" + commentList.getThumbsUpFalse() + ")");
        holder.mTvReplyContent.setText(commentList.getContent());
        if (commentList.getIsEvaluate().equals("1")) {
            holder.mTvEvaluate.setText("已评价");
            holder.mTvEvaluate.setOnClickListener(null);
        } else {
            holder.mTvEvaluate.setText("去评价");
            holder.mLlEvaluate.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<BeEvaluateUserBean> list = new ArrayList<>();
                    BeEvaluateUserBean bean = new BeEvaluateUserBean();
                    CreateUser user = commentList.getCreateUser();
                    bean.setAvatar(user.getPhoto());
                    bean.setBeEvaluatedUserId(user.getId());
                    bean.setName(user.getName());
                    bean.setRoleName(user.getRoleNames());
                    list.add(bean);
                    EvaluateCommitActivity.actionStart(mContext,
                            commentList.getId(),
                            list,
                            type);
                }
            });
        }

        holder.mTvPraise.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mTvPraise
                        .setText("(" + (Integer.parseInt(commentList.getThumbsUpTrue()) + 1) + ")");
                Map<String, String> map = new HashMap<>(16);
                map.put("commentId", commentList.getId());
                map.put("thumbsUp", "true");
                String queryStr = new JSONObject(map).toString();
                OkGo.<String>post(NetConstant.THUMBS_UP)
                        .params(NetConstant.QUERY, queryStr)
                        .execute(new MyStringCallback(mContext) {
                            @Override
                            public void onSuccess(Response<String> response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    int status = jsonObject.getInt(Constant.STATUS);
                                    if (status == 0) {
                                        String body = jsonObject.getString(Constant.BODY);
                                        if ("0".equals(body)) {
                                            holder.mTvGeneral.setOnClickListener(new OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    ToastUtils.showLong("已经点过啦");
                                                }
                                            });
                                            holder.mTvPraise
                                                    .setText("(" + commentList.getThumbsUpTrue() + ")");
                                        } else {
                                            ToastUtils.showShort("点赞成功");
                                        }
                                    } else {
                                        holder.mTvPraise
                                                .setText("(" + commentList.getThumbsUpTrue() + ")");
                                        ToastUtils.showLong(jsonObject.getString(Constant.MSG));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });

        holder.mTvGeneral.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mTvGeneral
                        .setText("(" + (Integer.parseInt(commentList.getThumbsUpFalse()) + 1) + ")");
                Map<String, String> map = new HashMap<>(16);
                map.put("commentId", commentList.getId());
                map.put("thumbsUp", "false");
                String queryStr = new JSONObject(map).toString();
                OkGo.<String>post(NetConstant.THUMBS_UP)
                        .params(NetConstant.QUERY, queryStr)
                        .execute(new MyStringCallback(mContext) {
                            @Override
                            public void onSuccess(Response<String> response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    int status = jsonObject.getInt(Constant.STATUS);
                                    if (status == 0) {
                                        String body = jsonObject.getString(Constant.BODY);
                                        if ("0".equals(body)) {
                                            holder.mTvGeneral.setOnClickListener(new OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    ToastUtils.showLong("已经点过啦");
                                                }
                                            });
                                            holder.mTvGeneral
                                                    .setText("(" + commentList.getThumbsUpFalse() + ")");
                                        } else {
                                            ToastUtils.showShort("点赞成功");
                                        }
                                    } else {
                                        holder.mTvGeneral
                                                .setText("(" + commentList.getThumbsUpFalse() + ")");
                                        ToastUtils.showLong(jsonObject.getString(Constant.MSG));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });

        int idRes = R.string.asked;
        // 待处理的
        if (mContext.getString(R.string.my_complaint_to_do).equals(todoType) ||
                mContext.getString(R.string.my_consulting_to_do).equals(todoType)) {
            // 待处理的
            // 追问还是追答
            // 追答：是自己的回复下面显示追答，不是自己的隐藏掉
            if (commentList.getCreateUser().getId().equals(UserHelper.getUser().getId())) {
                idRes = R.string.answered;
                holder.mTvQuestion.setText(idRes);
            } else {
                holder.mTvQuestion.setVisibility(View.GONE);
            }
        }

        final int finalIdRes = idRes;
        holder.mTvQuestion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                QaActivity.actionStart(mContext, commentList, finalIdRes, type);
            }
        });

        List<GuestbookCommentReList> guestbookCommentReList = commentList
                .getGuestbookCommentReList();
        if (guestbookCommentReList == null) {
            holder.mLlQa.setVisibility(View.GONE);
        } else {
            holder.mLlQa.setVisibility(View.VISIBLE);
            QaAdapter adapter = new QaAdapter(guestbookCommentReList);
            holder.mRvQa.setAdapter(adapter);
            holder.mRvQa.setLayoutManager(new LinearLayoutManager(mContext) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void initData(List<CommentList> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    static class AdvisoryReplyViewHolder extends ViewHolder {

        @BindView(R.id.tv_reply_user)
        TextView mTvReplyUser;
        @BindView(R.id.tv_reply_content)
        TextView mTvReplyContent;
        @BindView(R.id.ll_qa)
        LinearLayout mLlQa;
        @BindView(R.id.rv_qa)
        RecyclerView mRvQa;
        @BindView(R.id.iv_praise)
        ImageView mIvPraise;
        @BindView(R.id.iv_general)
        ImageView mIvGeneral;
        @BindView(R.id.tv_praise)
        TextView mTvPraise;
        @BindView(R.id.tv_general)
        TextView mTvGeneral;
        @BindView(R.id.tv_question)
        TextView mTvQuestion;
        @BindView(R.id.ll_evaluate)
        LinearLayout mLlEvaluate;
        @BindView(R.id.tv_evaluate)
        TextView mTvEvaluate;

        AdvisoryReplyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

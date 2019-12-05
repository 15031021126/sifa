package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.MediatorWorkflowAdapter.WorkflowViewHolder;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.Task;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.AidFlowInfoBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.WorkflowInfoBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.WorkflowShowBean;
import com.liuhezhineng.ximengsifa.business.legalaid.ApplyForLegalAidActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.AAssignPeoplesMediatorActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.BReviewActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.CRegisterFromActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.DRecordActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.EMediateRecordActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.FAgreementActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.GReturnVisitActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.HDossierActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.ModifyRequestActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.HDMediateRecordeActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.HaveDoneAgreementActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.HaveDoneDossierActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.HaveDoneRecordActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.HaveDoneRegisterActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.HaveDoneReturnVisitActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.RequestActivity;
import com.liuhezhineng.ximengsifa.constant.Constant.Mediation;
import com.liuhezhineng.ximengsifa.utils.UserHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @date 2018/6/10
 * @description 流程进展 adapter
 */

public class MediatorWorkflowAdapter extends Adapter<WorkflowViewHolder> {

    Map<String, BusinessBean> mMap;
    private Context mContext;
    private List mList;
    private String proInsId;

    public MediatorWorkflowAdapter(Context context, List list, String proInsId) {
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(list);
        this.proInsId = proInsId;
    }

    @NonNull
    @Override
    public WorkflowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.workflow_item, null);
        return new WorkflowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WorkflowViewHolder holder, final int position) {
        final Object object = mList.get(position);
        if (position == mList.size() - 1) {
            holder.mIvArrow.setVisibility(View.INVISIBLE);
        }
        final WorkflowShowBean bean;
        if (object instanceof WorkflowInfoBean) {
            bean = (WorkflowShowBean) object;
            holder.mTvWorkflow.setText(bean.getName());
            // 先全部赋予未完成
            holder.mTvStatus.setBackgroundColor(Color.GRAY);
            holder.mTvStatus.setText("未办理");
            bean.setStatus(3);
            String taskDefKey = bean.getTaskDefKey();
            // 这里的 map 里面是已完成和办理中的节点状态
            for (String str : mMap.keySet()) {
                if (mMap.get(str).getProcDefKey().equals("legal_aid")) {
                    if (taskDefKey.contains(str)) {
                        if ("1".equals(mMap.get(str).getStatus())) {
                            holder.mTvStatus.setBackgroundColor(Color.RED);
                            holder.mTvStatus.setText("办理中");
                            bean.setStatus(1);
                        } else if ("2".equals(mMap.get(str).getStatus())) {
                            bean.setStatus(2);
                            holder.mTvStatus.setBackgroundColor(
                                    Color.parseColor("#36b12f"));
                            holder.mTvStatus.setText("已完成");
                        }
                    }
                } else {
                    if (taskDefKey.contains(str)) {
                        if (taskDefKey.equals(str)) {
                            if ("1".equals(mMap.get(str).getStatus())) {
                                holder.mTvStatus.setBackgroundColor(Color.RED);
                                holder.mTvStatus.setText("办理中");
                                bean.setStatus(1);
                            } else if ("2".equals(mMap.get(str).getStatus())) {
                                bean.setStatus(2);
                                holder.mTvStatus.setBackgroundColor(
                                        Color.parseColor("#36b12f"));
                                holder.mTvStatus.setText("已完成");
                            }
                        }
                        if (!taskDefKey.equals(str)) {
                            switch (str) {
                                case "mediation_start":
                                    bean.setTaskDefKey("mediation_start");
                                    break;
                                case "mediation_zhiding":
                                    bean.setTaskDefKey("mediation_zhiding");
                                    break;
                                case "mediation_shenhe":
                                    bean.setTaskDefKey("mediation_shenhe");
                                    break;
                                case "mediation_xiugai":
                                    bean.setTaskDefKey("mediation_xiugai");
                                    break;
                                default:
                                    break;
                            }
                            if ("1".equals(mMap.get(str).getStatus())) {
                                holder.mTvStatus.setBackgroundColor(Color.RED);
                                holder.mTvStatus.setText("办理中");
                                bean.setStatus(1);
                                break;
                            } else if ("2".equals(mMap.get(str).getStatus())) {
                                bean.setStatus(2);
                                holder.mTvStatus.setBackgroundColor(
                                        Color.parseColor("#36b12f"));
                                holder.mTvStatus.setText("已完成");
                            }
                        }
                    }
                }
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (bean.getStatus() == 3 && bean.getTaskDefKey().contains("mediation")) {
                            ToastUtils.showLong("业务还未进展到此步骤");
                            return;
                        }
                        BusinessBean bean1 = mMap.get(bean.getTaskDefKey());
                        switch (bean.getAndroidUrl()) {
                            case Mediation.APPLY:
                                if (bean.getStatus() == 1) {
                                    if (UserHelper.isIsNormalUser()) {
                                        RequestActivity.actionStart(mContext, bean1);
                                        return;
                                    }
                                    switch (bean.getTaskDefKey()) {
                                        case "mediation_start":
                                            ApplyForLegalAidActivity.actionStart(mContext);
                                            break;
                                        case "mediation_zhidiwng":
                                            AAssignPeoplesMediatorActivity
                                                    .actionStart(mContext, bean1);
                                            break;
                                        case "mediation_shenhe":
                                            BReviewActivity
                                                    .actionStart(mContext, bean1);
                                            break;
                                        case "mediation_xiugai":
                                            ModifyRequestActivity
                                                    .actionStart(mContext, bean1);
                                            break;
                                        default:
                                            break;
                                    }
                                } else {
                                    RequestActivity.actionStart(mContext, bean1);
                                }
                                break;
                            case Mediation.ACCEPT:
                                if (bean.getStatus() == 1) {
                                    CRegisterFromActivity
                                            .actionStart(mContext, bean1);
                                } else {
                                    HaveDoneRegisterActivity
                                            .actionStart(mContext, bean1);
                                }
                                break;
                            case Mediation.AGREEMENT:
                                if (bean.getStatus() == 1) {
                                    FAgreementActivity.actionStart(mContext, bean1);
                                } else {
                                    HaveDoneAgreementActivity
                                            .actionStart(mContext, bean1);
                                }
                                break;
                            case Mediation.ARCHIVE:
                                if (bean.getStatus() == 1) {
                                    HDossierActivity.actionStart(mContext, bean1);
                                } else {
                                    HaveDoneDossierActivity
                                            .actionStart(mContext, bean1);
                                }
                                break;
                            case Mediation.INVESTIGATION:
                                if (bean.getStatus() == 1) {
                                    DRecordActivity.actionStart(mContext, bean1);
                                } else {
                                    HaveDoneRecordActivity
                                            .actionStart(mContext, bean1);
                                }
                                break;
                            case Mediation.RECORD:
                                if (bean.getStatus() == 1) {
                                    EMediateRecordActivity
                                            .actionStart(mContext, bean1);
                                } else {
                                    HDMediateRecordeActivity
                                            .actionStart(mContext, bean1);
                                }
                                break;
                            case Mediation.RETURN_VISIT:
                                if (bean.getStatus() == 1) {
                                    GReturnVisitActivity
                                            .actionStart(mContext, bean1);
                                } else {
                                    HaveDoneReturnVisitActivity
                                            .actionStart(mContext, bean1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
        } else if (object instanceof AidFlowInfoBean) {
            final AidFlowInfoBean aidFlowInfoBean = (AidFlowInfoBean) object;
            holder.mTvWorkflow.setText(aidFlowInfoBean.getTaskName());
//			1是办理中，2是已办理
            switch (aidFlowInfoBean.getStatus()) {
                case "1":
                    holder.mTvStatus.setBackgroundColor(Color.RED);
                    holder.mTvStatus.setText("办理中");
                    break;
                case "2":
                    holder.mTvStatus.setBackgroundColor(
                            Color.parseColor("#36b12f"));
                    holder.mTvStatus.setText("已完成");
                    break;
                case "0":
                    holder.mTvStatus.setBackgroundColor(Color.GRAY);
                    holder.mTvStatus.setText("未办理");
                    break;
                default:
                    break;
            }
            if ("people_mediation".equals(aidFlowInfoBean.getProcDefKey())) {
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ("0".equals(aidFlowInfoBean.getStatus())) {
                            ToastUtils.showLong("业务还未进展到此步骤");
                            return;
                        }
                        BusinessBean bean1 = new BusinessBean();
                        bean1.setProcDefKey(aidFlowInfoBean.getProcDefKey());
                        bean1.setProcDefId(aidFlowInfoBean.getProcDefId());
                        bean1.setProcInsId(aidFlowInfoBean.getProcInsId());
                        Task task = new Task();
                        task.setName(aidFlowInfoBean.getTaskName());
                        task.setId(aidFlowInfoBean.getTaskId());
                        task.setTaskDefinitionKey(aidFlowInfoBean.getTaskDefKey());
                        bean1.setTask(task);
                        switch (aidFlowInfoBean.getTaskDefKey()) {
                            case "mediation_start":
                                if (aidFlowInfoBean.getStatus().equals("2")) {
                                    RequestActivity.actionStart(mContext, bean1);
                                    return;
                                } else {
                                    if (!TextUtils.isEmpty(aidFlowInfoBean.getComment())) {
                                        Task task1 = bean1.getTask();
                                        task1.setTaskDefinitionKey(aidFlowInfoBean.getComment());
                                        if (UserHelper.isIsNormalUser()) {
                                            RequestActivity
                                                    .actionStart(mContext, bean1);
                                            return;
                                        }
                                        switch (aidFlowInfoBean.getComment()) {
                                            case "mediation_zhiding":
                                                task1.setName("指定承办人");
                                                bean1.setTask(task1);
                                                AAssignPeoplesMediatorActivity
                                                        .actionStart(mContext, bean1);
                                                break;
                                            case "mediation_shenhe":
                                                task1.setName("科员审核");
                                                bean1.setTask(task1);
                                                BReviewActivity
                                                        .actionStart(mContext, bean1);
                                                break;
                                            case "mediation_xiugai":
                                                task1.setName("申请人修改");
                                                bean1.setTask(task1);
                                                ModifyRequestActivity
                                                        .actionStart(mContext, bean1);
                                                break;
                                            default:
                                                break;
                                        }

                                    }
                                }
                                break;
                            case Mediation.ACCEPT:
                                if ("1".equals(aidFlowInfoBean.getStatus())) {
                                    CRegisterFromActivity
                                            .actionStart(mContext, bean1);
                                } else {
                                    HaveDoneRegisterActivity
                                            .actionStart(mContext, bean1);
                                }
                                break;
                            case Mediation.AGREEMENT:
                                if ("1".equals(aidFlowInfoBean.getStatus())) {
                                    FAgreementActivity.actionStart(mContext, bean1);
                                } else {
                                    HaveDoneAgreementActivity
                                            .actionStart(mContext, bean1);
                                }
                                break;
                            case Mediation.ARCHIVE:
                                if ("1".equals(aidFlowInfoBean.getStatus())) {
                                    HDossierActivity.actionStart(mContext, bean1);
                                } else {
                                    HaveDoneDossierActivity
                                            .actionStart(mContext, bean1);
                                }
                                break;
                            case Mediation.INVESTIGATION:
                                if ("1".equals(aidFlowInfoBean.getStatus())) {
                                    DRecordActivity.actionStart(mContext, bean1);
                                } else {
                                    HaveDoneRecordActivity
                                            .actionStart(mContext, bean1);
                                }
                                break;
                            case Mediation.RECORD:
                                if ("1".equals(aidFlowInfoBean.getStatus())) {
                                    EMediateRecordActivity
                                            .actionStart(mContext, bean1);
                                } else {
                                    HDMediateRecordeActivity
                                            .actionStart(mContext, bean1);
                                }
                                break;
                            case Mediation.RETURN_VISIT:
                                if ("1".equals(aidFlowInfoBean.getStatus())) {
                                    GReturnVisitActivity
                                            .actionStart(mContext, bean1);
                                } else {
                                    HaveDoneReturnVisitActivity
                                            .actionStart(mContext, bean1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void initData(ArrayList list,
                         Map<String, BusinessBean> map) {
        if (mList != null) {
            mMap = map;
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void initData(List<AidFlowInfoBean> list) {
        if (mList != null) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    static class WorkflowViewHolder extends ViewHolder {

        @BindView(R.id.tv_workflow)
        TextView mTvWorkflow;
        @BindView(R.id.tv_status)
        TextView mTvStatus;
        @BindView(R.id.iv_workflow_arrow)
        ImageView mIvArrow;

        WorkflowViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.dto.vo.ServerAppVo;
import com.liuhezhineng.ximengsifa.business.fastlegal.QuickLegalAidActivity;
import com.liuhezhineng.ximengsifa.business.legalaid.ApplyForLegalAidActivity;
import com.liuhezhineng.ximengsifa.business.legalaid.LegalAidDoorActivity;
import com.liuhezhineng.ximengsifa.business.noticedefense.NoticeDefenseActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.ApplyForPeoplesMediationActivity;
import com.liuhezhineng.ximengsifa.callback.ALEnforcementActivity;
import com.liuhezhineng.ximengsifa.callback.MechPeopleActivity;
import com.liuhezhineng.ximengsifa.callback.MechanismActivity;
import com.liuhezhineng.ximengsifa.callback.ReconsiderationActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.form.RemoteVisitFormActivity;
import com.liuhezhineng.ximengsifa.module.IntegratedQueryActivity;
import com.liuhezhineng.ximengsifa.module.StatisticalAnalysisActivity;
import com.liuhezhineng.ximengsifa.module.mine.business.MyBusinessActivity;
import com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint.ConsultingComplaintActivity;
import com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint.MyComplaintsActivity;
import com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint.MyConsultationActivity;
import com.liuhezhineng.ximengsifa.module.mine.order.MyOrderActivity;
import com.liuhezhineng.ximengsifa.module.service.article.ArticleActivity;
import com.liuhezhineng.ximengsifa.module.service.personnelinstitutions.PersonnelInstitutionsActivity;
import com.liuhezhineng.ximengsifa.module.service.webview.WebViewBaseActivity;
import com.liuhezhineng.ximengsifa.utils.PicassoImageLoader;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.liuhezhineng.ximengsifa.video.RectificationPersonnelActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @date 2018/4/16
 * @description 二级服务适配器
 */

public class ServicesAdapter extends Adapter<ViewHolder> {

    private Context mContext;
    private List mList;
    private int fromFlag;

    public ServicesAdapter(Context context, List list) {
        mContext = context;
        mList = new ArrayList();
        mList.addAll(list);
    }

    /**
     * @param context
     * @param list
     * @param from    来自首页还是服务模块
     */
    public ServicesAdapter(Context context, List list, int from) {
        mContext = context;
        mList = new ArrayList();
        mList.addAll(list);
        fromFlag = from;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (fromFlag == 1) {
            return new NewHomeServicesHolder(
                    LayoutInflater.from(mContext).
                            inflate(R.layout.layout_new_home_services_item, parent, false)
            );
        }
        return new ServicesViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.services_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ServerAppVo services = (ServerAppVo) mList.get(position);
        if (fromFlag == 1) {
            PicassoImageLoader.displayImage(
                    services.getLogo(),
                    ((NewHomeServicesHolder) holder).mIvHomeServices);
            final String title = services.getName();
            NewHomeServicesHolder newHomeServicesHolder = (NewHomeServicesHolder) holder;
            switch (title) {
                case "找律所":
                    newHomeServicesHolder.mIvMongolian.setImageResource(R.drawable.home_page_find_lawyer);
                    break;
                case "办公证":
                    newHomeServicesHolder.mIvMongolian.setImageResource(R.drawable.home_page_do_natarization);
                    break;
                case "求鉴定":
                    newHomeServicesHolder.mIvMongolian.setImageResource(R.drawable.home_page_beg_appraisal);
                    break;
                case "学法律":
                    newHomeServicesHolder.mIvMongolian.setImageResource(R.drawable.home_page_study_law);
                    break;
                case "求法援":
                    newHomeServicesHolder.mIvMongolian.setImageResource(R.drawable.home_page_this_assistance);
                    break;
                case "要调解":
                    newHomeServicesHolder.mIvMongolian.setImageResource(R.drawable.home_page_to_mediate);
                    break;
                case "待办":
                    newHomeServicesHolder.mIvMongolian.setImageResource(R.drawable.home_page_up_coming);
                    break;
                case "已办":
                    newHomeServicesHolder.mIvMongolian.setImageResource(R.drawable.home_page_have_done);
                    break;
                case "留言咨询":
                    newHomeServicesHolder.mIvMongolian.setImageResource(R.drawable.home_page_consulting);
                    break;
                case "投诉建议":
                    newHomeServicesHolder.mIvMongolian.setImageResource(R.drawable.home_page_complaint);
                    break;
                case "综合查询":
                    newHomeServicesHolder.mIvMongolian.setImageResource(R.drawable.home_page_query);
                    break;
                case "我的预约":
                    newHomeServicesHolder.mIvMongolian.setImageResource(R.drawable.home_page_order);
                    break;
                default:
                    break;
            }
            ((NewHomeServicesHolder) holder).mTvHomeServicesName.setText(title);
        } else {
            PicassoImageLoader.displayImage(services.getLogo(), ((ServicesViewHolder) holder).mIvServices);
            final String title = services.getName();
            ((ServicesViewHolder) holder).mTvServicesName.setText(title);
            ServicesViewHolder servicesViewHolder = (ServicesViewHolder) holder;
        }
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("sss","lishangnan="+services.getLink());
                if (services.getLink().contains(mContext.getString(R.string.http)) ||
                        services.getLink().contains(mContext.getString(R.string.https))) {
                    WebViewBaseActivity.actionStart(mContext, services.getLink(), services.getName());
                    return;
                }
                switch (services.getLink()) {
                    case "综合查询":
                        IntegratedQueryActivity.actionStart(((BaseActivity) mContext));
                        break;
                    case "统计分析":
                        StatisticalAnalysisActivity.actionStart(mContext);
                        break;
                    case "/chart/legalAid/toCountCaseType":
                        WebViewBaseActivity.actionStart(mContext,
                                NetConstant.BASE_URL + "/chart/legalAid/toCountCaseType",
                                services.getName());
                        break;
                    case "人民调解":
                        ApplyForPeoplesMediationActivity.actionStart(mContext);
                        break;
                    case "申请法援":
                        if (UserHelper.isIsNormalUser()) {
                            LegalAidDoorActivity.actionStart(mContext);
                        }else {
                            ApplyForLegalAidActivity.actionStart(mContext, 100);
                        }

                        break;
                    case "满意度评价":
                    case "法援人员查询":
                    case "法援中心":
                    case "人员查询":
                    case "机构查询":
                        PersonnelInstitutionsActivity.actionStart(mContext, services);
                        break;
                    case NetConstant.A_L_K:
                        WebViewBaseActivity.actionStart(mContext, services.getLink(), services.getName());
                        break;
                    case NetConstant.ZHSK_12348_GOV_CN:
                        WebViewBaseActivity.actionStart(mContext, services.getLink(), services.getName());
                        break;
                    case "留言咨询":
                        MyConsultationActivity.actionStart(mContext);
                        break;
                    case "意见投诉":
                        MyComplaintsActivity.actionStart(mContext);
                        break;
                    case "通知辩护":
                        NoticeDefenseActivity.actionStart(mContext);
                        break;
                    case "快速申请":
                        QuickLegalAidActivity.actionStart(mContext);
                        break;
                    case "政策发布":
                    case "服务动态":
                    case "法律法规":
                    case "服务指南":
                    case "文书表格":
                    case "谁执法谁普法":
                        ArticleActivity.actionStart(mContext, services);
                        break;
                    // 这三个是二级服务，所以需要加上 serviceId
                    case "法制宣传":
                    case "普法形式":
                    case "法制阵地":
                        ArticleActivity.actionStart(
                                mContext,
                                services,
                                "4f6761122ac0418f8a88359adf087985");
                        break;
                    case "人员分析":
                        mContext.startActivity(new Intent(mContext, RectificationPersonnelActivity.class));
                        break;
                    case "已办":
                        MyBusinessActivity.actionStart(mContext, Constant.HAVE_DONE);
                        break;
                    case "待办":
                        MyBusinessActivity.actionStart(mContext, Constant.UP_COMING);
                        break;
                    case "我的咨询":
                        ConsultingComplaintActivity.actionStart(mContext, Constant.CONSULTING);
                        break;
                    case "我的投诉":
                        ConsultingComplaintActivity.actionStart(mContext, Constant.COMPLAINT);
                        break;
                    case "我的预约":
                        MyOrderActivity.actionStart(mContext);
                        break;
                    case "远程探视申请":
                        RemoteVisitFormActivity.actionStart(mContext);
                        break;
                    case "执法人员":
                        // TODO: 2019/11/16
                            Intent intent=new Intent(mContext, MechPeopleActivity.class);
                            mContext.startActivity(intent);
                        break;
                    case "执法主体":
                        // TODO: 2019/11/16
                        Intent intent1=new Intent(mContext, MechanismActivity.class);
                        mContext.startActivity(intent1);
                        break;
                    case "行政执法公示专栏":
                    case "行政执法全过程记录专栏":
                    case "重大执法决定法制审核专栏":
                    case "知识问答":
                        String id = services.getId();
                        Intent intent2=new Intent(mContext, ALEnforcementActivity.class);
                        intent2.putExtra("servicesid",id);
                        mContext.startActivity(intent2);
//                        ArticleActivity.actionStart(mContext, services);
                        break;
                    case "申请复议":
                        ReconsiderationActivity.actionStart(mContext);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void initData(List servicesList) {
        if (mList != null) {
            mList.clear();
            mList.addAll(servicesList);
            notifyDataSetChanged();
        }
    }

    static class ServicesViewHolder extends ViewHolder {

        @BindView(R.id.tv_service_name)
        TextView mTvServicesName;
        @BindView(R.id.iv_service)
        ImageView mIvServices;

        ServicesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class NewHomeServicesHolder extends ViewHolder {

        @BindView(R.id.iv_home_services)
        ImageView mIvHomeServices;
        @BindView(R.id.tv_home_services_name)
        TextView mTvHomeServicesName;
        @BindView(R.id.iv_mongolian)
        ImageView mIvMongolian;

        NewHomeServicesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

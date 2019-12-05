package com.liuhezhineng.ximengsifa.business.fastlegal.havedone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.fastlegal.FastFlowBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.business.base.BaseFastLegalActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @description 快速申请的已办（包含：12348 服务人员审核，承办人审核，承办人办理）
 */
public class HaveDoneActivity extends BaseFastLegalActivity {

    @BindView(R.id.tv_undertaker)
    TextView mTvUndertaker;
    @BindView(R.id.tv_deal_content)
    EditText mTvDealContent;
    @BindView(R.id.tv_file)
    TextView mTvFile;
    @BindView(R.id.layout_fast_accept_assign_undertaker)
    LinearLayout mLlAssignUndertaker;
    @BindView(R.id.layout_fast_accept_undertaker_deal)
    LinearLayout mLlUndertakerDeal;
    @BindView(R.id.tv_staff_office)
    TextView mTvStaffOffice;
    @BindView(R.id.ll_staff_office)
    LinearLayout mLlStaffOffice;
    @BindView(R.id.tv_is_transfer_penalty)
    TextView mTvIsTransferPenalty;
    @BindView(R.id.ll_is_transfer_penalty)
    LinearLayout mLlIsTransferPenalty;
    @BindView(R.id.tv_is_Petition)
    TextView mTvIsPetition;
    @BindView(R.id.ll_is_petition)
    LinearLayout mLlIsPetition;
    @BindView(R.id.tv_is_affect_stability)
    TextView mTvIsAffectStability;
    @BindView(R.id.ll_is_affect_stability)
    LinearLayout mLlIsAffectStability;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.et_actual_amount)
    EditText mEtActualAmount;

    public static void actionStart(Context context, BusinessBean bean) {
        Intent intent = new Intent(context, HaveDoneActivity.class);
        intent.putExtra(Constant.BUSINESS, bean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_have_done;
    }

    @Override
    protected void initView() {
        super.initView();
        mTvFile.setVisibility(View.GONE);
        mTvDealContent.setEnabled(false);
    }

    @Override
    protected void showDetails(FastFlowBean data) {
        super.showDetails(data);

        // 区分人员字段：personnelType (String；字段长度：2；0:社会大众；1:工作人员)
        if ("0".equals(data.getPersonnelType())) {
            mLlIsAffectStability.setVisibility(View.GONE);
            mLlIsPetition.setVisibility(View.GONE);
            mLlIsTransferPenalty.setVisibility(View.GONE);
            mLlStaffOffice.setVisibility(View.GONE);
        } else {
            mLlAddress.setVisibility(View.GONE);
        }
        mEtActualAmount.setText(data.getActualAmount());
        mTvStaffOffice.setText(data.getStaffOffice().getName());
        mTvAddress.setText(data.getCaseAddress());
        mTvIsAffectStability.setText(data.getIsAffectStabilityDesc());
        mTvIsPetition.setText(data.getIsPetitionDesc());
        mTvIsTransferPenalty.setText(data.getIsTransferPenaltyDesc());

        UserBean transactor = data.getTransactor();
        if (transactor != null && !TextUtils.isEmpty(transactor.getName())) {
            mLlAssignUndertaker.setVisibility(View.VISIBLE);
            mTvUndertaker.setText(transactor.getName());

            if (!TextUtils.isEmpty(data.getHandleResult())) {
                mLlUndertakerDeal.setVisibility(View.VISIBLE);
                mTvDealContent.setText(data.getHandleResult());
            }
        }
    }
}

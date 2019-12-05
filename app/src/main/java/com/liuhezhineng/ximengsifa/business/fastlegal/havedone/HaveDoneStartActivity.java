package com.liuhezhineng.ximengsifa.business.fastlegal.havedone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AudioAdapter;
import com.liuhezhineng.ximengsifa.audiorecord.MediaPlayerManager;
import com.liuhezhineng.ximengsifa.audiorecord.RecordBean;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessDetailsWrapper;
import com.liuhezhineng.ximengsifa.bean.bussiness.fastlegal.FastFlowBean;
import com.liuhezhineng.ximengsifa.business.base.BaseBusinessActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.constant.NetConstant.FastLegal;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AIqinfeng
 * @description 申请人申请成功后查看成功案件的详情页
 */
public class HaveDoneStartActivity extends BaseBusinessActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_case_category)
    TextView mTvCaseCategory;
    @BindView(R.id.tv_case_type)
    TextView mTvCaseType;
    @BindView(R.id.tv_case_county)
    TextView mTvCaseArea;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_case_content)
    TextView mTvCaseContent;
    @BindView(R.id.rv_audio)
    RecyclerView mRvAudio;
    ArrayList<RecordBean> mAudioList;
    AudioAdapter mAudioAdapter;
    @BindView(R.id.tv_staff_office)
    TextView mTvStaffOffice;
    @BindView(R.id.tv_case_town)
    TextView mTvCaseTown;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_case_involved_count)
    TextView mTvCaseInvolvedCount;
    @BindView(R.id.tv_actual_amount)
    TextView mTvActualAmount;
    @BindView(R.id.tv_is_transfer_penalty)
    TextView mTvIsTransferPenalty;
    @BindView(R.id.tv_is_Petition)
    TextView mTvIsPetition;
    @BindView(R.id.tv_is_affect_stability)
    TextView mTvIsAffectStability;
    @BindView(R.id.tv_case_rank)
    TextView mtVCaseRank;
    @BindView(R.id.ll_staff_office)
    LinearLayout mLlStaffOffice;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.ll_is_transfer_penalty)
    LinearLayout mLlIsTransferPenalty;
    @BindView(R.id.ll_is_petition)
    LinearLayout mLlIsPetition;
    @BindView(R.id.ll_is_affect_stability)
    LinearLayout mLlIsAffectStability;
    @BindView(R.id.ll_case_rank)
    LinearLayout mLlCaseRank;
    @BindView(R.id.tv_case_data)
    TextView tvCaseData;

    public static void actionStart(Context context, BusinessBean bean) {
        Intent intent = new Intent(context, HaveDoneStartActivity.class);
        intent.putExtra(Constant.BUSINESS, bean);
        context.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaPlayerManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaPlayerManager.resume();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_have_done_quick_legal_aid;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayerManager.release();
    }

    @Override
    protected void initView() {
        super.initView();
        mAudioList = new ArrayList<>();
        mAudioAdapter = new AudioAdapter(this, mAudioList);
        mRvAudio.setAdapter(mAudioAdapter);
        mRvAudio.setLayoutManager(new LinearLayoutManager(this));
        mRvAudio.setHasFixedSize(true);

        if (UserHelper.isIsNormalUser()) {
            mLlStaffOffice.setVisibility(View.GONE);
            mLlIsTransferPenalty.setVisibility(View.GONE);
            mLlIsPetition.setVisibility(View.GONE);
            mLlIsAffectStability.setVisibility(View.GONE);
            mLlCaseRank.setVisibility(View.GONE);
        } else {
            mLlAddress.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        loadBusinessDetails();
    }

    protected void loadBusinessDetails() {
        OkGo.<BaseBean<BusinessDetailsWrapper<FastFlowBean>>>post(
                FastLegal.LOAD_FLOW_INFO)
                .params(NetConstant.QUERY, getBusinessDataQueryJson())
                .execute(new DialogCallBack<BaseBean<BusinessDetailsWrapper<FastFlowBean>>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseBean<BusinessDetailsWrapper<FastFlowBean>>> response) {
                        BusinessDetailsWrapper<FastFlowBean> wrapper = response.body().getBody();
                        FastFlowBean data = wrapper.getBusinessData();
                        showDetails(data);
                    }
                });
    }

    private void showDetails(FastFlowBean data) {
        mTvName.setText(data.getAccuserName());
        mTvStaffOffice.setText(data.getStaffOffice().getName());
        mTvPhone.setText(data.getAccuserPhone());
        mTvCaseArea.setText(data.getCaseCounty().getName());
        mTvCaseTown.setText(data.getCaseTown().getName());
        mTvAddress.setText(data.getCaseAddress());
        mTvTitle.setText(data.getCaseTitle());
        mTvCaseInvolvedCount.setText(data.getCaseInvolvecount());
        mTvActualAmount.setText(data.getActualAmount());
        mTvCaseCategory.setText(data.getCaseClassifyDesc());
        mTvCaseType.setText(data.getCaseTypeDesc());
        mTvCaseContent.setText(data.getCaseReason());
        mTvIsTransferPenalty.setText(data.getIsTransferPenaltyDesc());
        mTvIsPetition.setText(data.getIsPetitionDesc());
        mTvIsAffectStability.setText(data.getIsAffectStabilityDesc());
        mtVCaseRank.setText(data.getCaseRankDesc());
        tvCaseData.setText(data.getCaseTime());

        String audioFilePath = data.getVoicePath();
        if (!TextUtils.isEmpty(audioFilePath)) {
            Type type = new TypeToken<ArrayList<RecordBean>>() {
            }.getType();
            mAudioList.addAll((Collection<? extends RecordBean>) new Gson().fromJson(audioFilePath, type));
            mAudioAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
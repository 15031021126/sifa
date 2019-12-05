package com.liuhezhineng.ximengsifa.business.fastlegal;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.Act;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.InsUserBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.fastlegal.FastFlowBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.business.base.BaseFastLegalActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant.FastLegal;
import com.liuhezhineng.ximengsifa.model.StringDialogCallback;
import com.liuhezhineng.ximengsifa.vidyo.VideoRequestActivity;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 指定承办人
 * 承办人办理
 */
public class BAssignUndertakerActivity extends BaseFastLegalActivity {

    @BindView(R.id.tv_undertaker)
    TextView mTvUndertaker;
    @BindView(R.id.tv_deal_content)
    EditText mTvDealContent;
    @BindView(R.id.layout_fast_accept_undertaker_deal)
    LinearLayout mLlUndertakerDeal;
    @BindView(R.id.tv_commit)
    Button mBtnCommit;
    @BindView(R.id.btn_vidyo)
    Button mBtnVidyo;
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
    @BindView(R.id.ll_oversea_case)
    LinearLayout mLlOverseaCase;
    @BindView(R.id.tv_allow_timeout)
    TextView mTvAllowTimeout;
    @BindView(R.id.tv_timeout_date)
    TextView mTvTimeoutDate;
    FastFlowBean mFastFlowBean;
    private String taskDefKey;

    public static void actionStart(Context context, BusinessBean bean) {
        Intent intent = new Intent(context, BAssignUndertakerActivity.class);
        intent.putExtra(Constant.BUSINESS, bean);
        ((BaseActivity) context).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
    }

    @Override
    protected void initView() {
        super.initView();
        taskDefKey = mBusinessBean.getTask().getTaskDefinitionKey();
        switch (taskDefKey) {
            case Constant.FastLegal.DEAL:
                mBtnCommit.setText("办结");
                mLlOverseaCase.setVisibility(View.GONE);
                break;
            case Constant.FastLegal.ASSIGN_PEOPLE:
                mBtnCommit.setText("指定承办人");
                mLlUndertakerDeal.setVisibility(View.GONE);
                break;
            case Constant.FastLegal.REVIEW:
                break;
            default:
                break;
        }

        mTvDealContent.setEnabled(true);
    }

    @Override
    protected void initData() {
        super.initData();
        initWarningStateTypePicker((option1, option2, option3, v) -> {
            TypeBean typeBean = warningStateTypeList.get(option1);
            mTvAllowTimeout.setText(typeBean.getLabel());
            mFastFlowBean.setWarningState(typeBean.getValue());
        });
        initWarningTimeTypePicker((options1, options2, options3, v) -> {
            TypeBean typeBean = warningTimeTypeList.get(options1);
            mTvTimeoutDate.setText(typeBean.getLabel());
            mFastFlowBean.setWarningTime(typeBean.getValue());
        });
    }

    @Override
    protected void showDetails(FastFlowBean data) {
        super.showDetails(data);
        mFastFlowBean = data;
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
            mTvUndertaker.setText(transactor.getName());
        }
        initPeoplePicker((options1, options2, options3, v) -> {
                    if (options1 < 0) {
                        ToastUtils.showLong("请等待数据加载完成");
                        return;
                    }
                    InsUserBean bean = peopleList.get(options1);
                    if (Constant.PICKER_NO_DATA.equals(bean.getId())) {
                        ToastUtils.showLong(bean.getName());
                        return;
                    }
                    mTvUndertaker.setText(bean.getName());
                    UserBean user = new UserBean();
                    user.setId(bean.getId());
                    mFastFlowBean.setTransactor(user);
                },
                data.getCaseClassify(),
                data.getOffice().getId(),
                mFastFlowBean.getCaseCounty().getId(),
                data.getCaseTown().getId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_assign_undertaker;
    }

    @Override
    @OnClick({R.id.tv_undertaker,
            R.id.tv_allow_timeout, R.id.tv_timeout_date,
            R.id.tv_commit, R.id.btn_vidyo})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.tv_allow_timeout:
                showPickerView(warningStateTypePicker);
                break;
            case R.id.tv_timeout_date:
                showPickerView(warningTimeTypePicker);
                break;
            case R.id.btn_vidyo:
                VideoRequestActivity.actionStart(mActivity, mFastFlowBean.getAccuserName(), mFastFlowBean.getCaseTitle(), mFastFlowBean.getCreateBy().getId());
                break;
            case R.id.tv_undertaker:
                if (Constant.FastLegal.ASSIGN_PEOPLE.equals(taskDefKey)) {
                    showPickerView(peoplePicker);
                }
                break;
            case R.id.tv_commit:
                if (checkInput()) {
                    commitAcceptForm();
                }
                break;
            default:
                break;
        }
    }

    private boolean checkInput() {
        switch (taskDefKey) {
            case Constant.FastLegal.DEAL:
                String dealContent = mTvDealContent.getText().toString().trim();
                if (TextUtils.isEmpty(dealContent)) {
                    ToastUtils.showLong("处理结果不能为空");
                    mTvDealContent.requestFocus();
                    mTvDealContent.setError("处理结果不能为空");
                    return false;
                }
                mFastFlowBean.setHandleResult(dealContent);
                return true;
            case Constant.FastLegal.ASSIGN_PEOPLE:
                mLlUndertakerDeal.setVisibility(View.GONE);
                String underTaker = mTvUndertaker.getText().toString().trim();
                UserBean transactor = mFastFlowBean.getTransactor();
                if (TextUtils.isEmpty(underTaker) ||
                        transactor == null || TextUtils.isEmpty(transactor.getId())) {
                    ToastUtils.showLong("请指定承办人");
                    mTvUndertaker.requestFocus();
                    mTvUndertaker.setError("请指定承办人");
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    private void commitAcceptForm() {
        mFastFlowBean.setIsSubmit("true");
        mFastFlowBean.setCaseFile(caseFile);
        Act act = new Act();
        act.setProcInsId(mBusinessBean.getProcInsId());
        act.setProcDefId(mBusinessBean.getProcDefId());
        act.setProcDefKey(mBusinessBean.getProcDefKey());
        act.setTaskId(mBusinessBean.getTask().getId());
        act.setTaskName(mBusinessBean.getTask().getName());
        act.setTaskDefKey(mBusinessBean.getTask().getTaskDefinitionKey());
        act.setFlag(flag);
        act.setComment(comment);
        mFastFlowBean.setAct(act);
        String queryStr = new Gson().toJson(mFastFlowBean);

        OkGo.<String>post(FastLegal.COMMIT_FLOW)
                .params(Constant.QUERY, queryStr)
                .execute(new StringDialogCallback(mActivity) {
                    @Override
                    protected void responseSuccess(JSONObject object) {
                        ToastUtils.showLong("提交成功");
                        LocalBroadcastManager.getInstance(mActivity)
                                .sendBroadcast(new Intent(Constant.DEAL_BUSINESS));
                        setResult(RESULT_OK);
                        finishActivity();
                    }
                });
    }
}

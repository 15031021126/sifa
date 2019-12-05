package com.liuhezhineng.ximengsifa.form;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AnnexAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.form.VisitApply;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author iqing
 * @description remote visitor
 */
public class RemoteVisitorReviewActivity extends BaseActivity {

    @BindView(R.id.et_request_user_name)
    TextView mTvRequestUserName;
    @BindView(R.id.et_contact_phone_number)
    TextView mTvContactPhoneNumber;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.et_native_place)
    TextView mTvNativePlace;
    @BindView(R.id.et_visitor_relations)
    TextView mTvVisitorRelations;
    @BindView(R.id.et_id_card)
    TextView mTvIdCard;
    @BindView(R.id.et_now_address)
    TextView mTvNowAddress;
    @BindView(R.id.et_work_unit)
    TextView mTvWorkUnit;
    @BindView(R.id.et_visitor_name)
    TextView mTvVisitorName;
    @BindView(R.id.tv_visitor_sex)
    TextView mTvVisitorSex;
    @BindView(R.id.et_visitor_native_place)
    TextView mTvVisitorNativePlace;
    @BindView(R.id.tv_supervision_place_type)
    TextView mTvSupervisionPlaceType;
    @BindView(R.id.tv_supervision_place)
    TextView mTvSupervisionPlace;
    @BindView(R.id.et_visitor_id_card)
    TextView mTvVisitorIdCard;
    @BindView(R.id.tv_judicial_bureau)
    TextView mTvJudicialBureau;
    @BindView(R.id.tv_start_date)
    TextView mTvStartDate;
    @BindView(R.id.tv_end_date)
    TextView mTvEndDate;
    @BindView(R.id.et_apply_reason)
    TextView mTvApplyReason;
    @BindView(R.id.et_agent_name)
    TextView mTvAgentName;
    @BindView(R.id.et_agent_phone_number)
    TextView mTvAgentPhoneNumber;
    @BindView(R.id.ll_proxy)
    LinearLayout mLlProxy;
    @BindView(R.id.ll_btn)
    LinearLayout mLlBtn;
    @BindView(R.id.ll_examine_time)
    LinearLayout mLlExamineTime;
    @BindView(R.id.ll_reject_reason)
    LinearLayout mLlRejectReason;
    @BindView(R.id.tv_reject_reason)
    TextView mTvRejectReason;
    @BindView(R.id.tv_examine_time)
    TextView mTvExamineTime;
    @BindView(R.id.btn_reject)
    Button mBtnReject;
    @BindView(R.id.btn_pass)
    Button mBtnPass;
    private VisitApply mVisitApplyBean;

    public static void actionStart(Context context, VisitApply visitApply, String todoType) {
        Intent intent = new Intent(context, RemoteVisitorReviewActivity.class);
        intent.putExtra("visit_apply", visitApply);
        intent.putExtra(Constant.TODO_TYPE, todoType);
        ((BaseActivity) context).startActivityForResult(intent, Constant.STATE_REFRESH);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remote_visitor_review;
    }

    String todoType;

    @Override
    protected void initView() {
        super.initView();

        initTopBar(R.string.remote_visitor_door);
        initAnnex();
        findViewById(R.id.tv_file).setVisibility(View.GONE);

        todoType = getIntent().getStringExtra(Constant.TODO_TYPE);
        mVisitApplyBean = (VisitApply) getIntent().getSerializableExtra("visit_apply");
        if (Constant.UP_COMING.equals(todoType)) {
            switch (mVisitApplyBean.getStateDesc()) {
                case "已审核":
                    mLlExamineTime.setVisibility(View.VISIBLE);
                    break;
                case "已驳回":
                    mLlRejectReason.setVisibility(View.VISIBLE);
                    break;
                case "待审核":
                    mLlBtn.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }

    }


    @Override
    protected void initData() {
        super.initData();

        setFromText();
    }

    private void setFromText() {
        mTvRequestUserName.setText(mVisitApplyBean.getApplyName());
        mTvContactPhoneNumber.setText(mVisitApplyBean.getApplyPhone());
        mTvSex.setText(mVisitApplyBean.getApplySexDesc());
        mTvNativePlace.setText(mVisitApplyBean.getApplyNativePlace());
        mTvVisitorRelations.setText(mVisitApplyBean.getRelation());
        mTvIdCard.setText(mVisitApplyBean.getApplyIdCard());
        mTvNowAddress.setText(mVisitApplyBean.getApplyAddress());
        mTvWorkUnit.setText(mVisitApplyBean.getApplyWorkUnit());

        if (TextUtils.isEmpty(mVisitApplyBean.getAgentName())) {
            mLlProxy.setVisibility(View.GONE);
        } else {
            mTvAgentName.setText(mVisitApplyBean.getAgentName());
            mTvAgentPhoneNumber.setText(mVisitApplyBean.getAgentPhone());
        }

        mTvVisitorName.setText(mVisitApplyBean.getVisitorName());
        mTvVisitorSex.setText(mVisitApplyBean.getVisitorSexDesc());
        mTvVisitorNativePlace.setText(mVisitApplyBean.getVisitorNativePlace());
        mTvSupervisionPlaceType.setText(mVisitApplyBean.getSupervisionPlaceTypeDesc());
        mTvSupervisionPlace.setText(mVisitApplyBean.getSupervisionPlaceDesc());
        mTvVisitorIdCard.setText(mVisitApplyBean.getVisitorIdCard());

        mTvJudicialBureau.setText(mVisitApplyBean.getOffice().getName());
        mTvStartDate.setText(mVisitApplyBean.getStartTime());
        mTvEndDate.setText(mVisitApplyBean.getEndTime());
        mTvApplyReason.setText(mVisitApplyBean.getApplyReason());

        mTvExamineTime.setText(mVisitApplyBean.getExamineTime());
        mTvRejectReason.setText(mVisitApplyBean.getRejectReson());

        setCaseFile(mVisitApplyBean.getFiles());
    }

    protected AnnexAdapter mAdapter;
    protected ArrayList<String> mAnnexList;

    private void initAnnex() {
        RecyclerView mRvAnnex = findViewById(R.id.rv_annex);
        mAnnexList = new ArrayList<>();
        mAdapter = new AnnexAdapter(mActivity, mAnnexList);
        mRvAnnex.setAdapter(mAdapter);
    }

    protected void setCaseFile(String caseFile) {
        // caseFile: "", "|xx", "null|xx"
        if (!"".equals(caseFile)) {
            ArrayList<String> list = new ArrayList<>();
            String[] fileArray = caseFile.split("\\|");
            for (String path : fileArray) {
                if (!TextUtils.isEmpty(path)) {
                    list.add(path);
                }
            }
            mAdapter.initData(list);
        }
    }

    @Override
    protected String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        return format.format(date);
    }

    @Override
    @OnClick({R.id.btn_reject, R.id.btn_pass})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_reject:
                showRejectDialog();
                break;
            case R.id.btn_pass:
                showTimeDialog();
                break;
            default:
                break;
        }
    }

    private void showRejectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(mActivity, R.layout.dialog_no, null);

        TextView tvTitle = dialogView.findViewById(R.id.tv_title);
        final EditText etReason = dialogView.findViewById(R.id.et_no_reason);
        Button btnCancel = dialogView.findViewById(R.id.btn_no);
        Button btnConfirm = dialogView.findViewById(R.id.btn_yes);

        etReason.setHint(getResources().getText(R.string.reject_hint));
        tvTitle.setText(getResources().getText(R.string.reject_reason));
        btnConfirm.setText(getResources().getText(R.string.confirm_reject));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = etReason.getText().toString().trim();
                if (TextUtils.isEmpty(comment)) {
                    ToastUtils.showLong(R.string.please_enter_reject_reason);
                    return;
                }
                mVisitApplyBean.setRejectReson(comment);
                rejectOrPass("3");
                dialog.dismiss();
            }
        });
        dialog.setView(dialogView);
        dialog.show();
    }

    private void showTimeDialog() {
        Calendar startDate = Calendar.getInstance();
        startDate.setTimeInMillis(System.currentTimeMillis());
        String endTimeStr = mVisitApplyBean.getEndTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        Date endTime = null;
        try {
            endTime = format.parse(endTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(endTime);
        birthdayPickerView = new TimePickerBuilder(mActivity,
                new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mVisitApplyBean.setExamineTime(getTime(date));
                        rejectOrPass("2");
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true)
                .setTitleText("请选择探视审批时间")
                .setRangDate(startDate, endDate)
                .build();
        birthdayPickerView.show();
    }

    private void rejectOrPass(String state) {
        /*
        id	|	[string]	|		|	远程申请的id
examineTime	|	[string]	|		|	审核时间，同意时该字段填写
rejectReson	|	[string]	|		|	驳回理由 ，驳回是填写该字段
    state	|	[string]	|		|	同意填2，驳回填3
         */
        mVisitApplyBean.setState(state);
        OkGo.<BaseBean>post(NetConstant.RemoteVisit.REJECT_OR_PASS)
                .params(NetConstant.QUERY, new Gson().toJson(mVisitApplyBean))
                .execute(new DialogCallBack<BaseBean>(mActivity, "操作进行中...") {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        ToastUtils.showLong(R.string.operate_successful);
                        setResult(RESULT_OK);
                        finishActivity();
                    }
                });
    }
}

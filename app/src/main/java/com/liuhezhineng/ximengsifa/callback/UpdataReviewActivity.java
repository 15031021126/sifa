package com.liuhezhineng.ximengsifa.callback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessDetailsWrapper;
import com.liuhezhineng.ximengsifa.bean.bussiness.LegalAidData;
import com.liuhezhineng.ximengsifa.business.base.BaseLegalAidActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 法援审批
 */
public class UpdataReviewActivity extends BaseLegalAidActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.et_name)
    TextView mEtName;
    @BindView(R.id.tv_sex)
    TextView mEtSex;
    @BindView(R.id.tv_ethnic)
    TextView mEtEthnic;
    @BindView(R.id.tv_birthday)
    TextView mEtBirthday;
    @BindView(R.id.et_id_card_num)
    TextView mEtIdCardNum;
    @BindView(R.id.et_area)
    TextView mEtArea;
    @BindView(R.id.et_work_address)
    TextView mEtWorkAddress;
    @BindView(R.id.et_address)
    TextView mEtAddress;
    @BindView(R.id.et_home_address)
    TextView mEtHomeAddress;
    @BindView(R.id.et_phone)
    TextView mEtPhoneNum;
    @BindView(R.id.et_agent_name)
    TextView mEtAgentName;
    @BindView(R.id.et_agent_id_card_num)
    TextView mEtAgentIdCardNum;
    @BindView(R.id.et_agent_person)
    TextView mEtAgentPerson;
    @BindView(R.id.et_title)
    TextView mEtTitle;
    @BindView(R.id.et_type)
    TextView mEtType;
    @BindView(R.id.et_line_mengyu)
    TextView mEtLineMengyu;
    @BindView(R.id.et_content)
    TextView mEtContent;
    @BindView(R.id.ll_agency)
    LinearLayout mLlAgency;
    @BindView(R.id.tv_file)
    TextView tvFile;
    @BindView(R.id.rv_annex)
    RecyclerView rvAnnex;
    @BindView(R.id.ll_annex)
    LinearLayout llAnnex;
    @BindView(R.id.rv_annex1)
    RecyclerView rvAnnex1;
    @BindView(R.id.ll_annex1)
    LinearLayout llAnnex1;
    @BindView(R.id.tv_wenhua)
    TextView tvWenhua;
    @BindView(R.id.tv_renqun)
        TextView tvRenqun;
    @BindView(R.id.tv_biaozhun)
    TextView tvBiaozhun;
    @BindView(R.id.tv_case)
    TextView tvCase;
    @BindView(R.id.tv_matters)
    TextView tvMatters;
    @BindView(R.id.tv_matters_stage)
    TextView tvMattersStage;
    @BindView(R.id.tv_review_data)
    TextView tvReviewData;
    @BindView(R.id.et_examination_opinion)
    EditText etExaminationOpinion;
    @BindView(R.id.btn_deprecate)
    Button btnDeprecate;


    @BindView(R.id.tv_shenpi_data)
    TextView shenpiData;


    @BindView(R.id.et_shenpi_opinion)
    EditText shenpiOpinion;

    @BindView(R.id.ll_shenpi)
     LinearLayout llShenpi;
    private BusinessDetailsWrapper wrapper;
    private LegalAidData bean;
    @Override
    protected void initView() {
        super.initView();
        llShenpi.setVisibility(View.VISIBLE);
        btnDeprecate.setVisibility(View.GONE);
        btnWorkflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewWorkflowActivity.actionStart(UpdataReviewActivity.this, mBusinessBean,mBaseBusinessBean);
            }
        });
    }
    public static void actionStart(Context context, BusinessBean businessBean) {
        Intent intent = new Intent(context, UpdataReviewActivity.class);
        intent.putExtra(Constant.BUSINESS, businessBean);
        ((BaseActivity) context).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_aid_review;
    }

    @Override
    protected void showDetails(Response<BaseBean<BusinessDetailsWrapper<LegalAidData>>> response) {
//        RequestLawHelpWorkFlow
        super.showDetails(response);
        wrapper = response.body().getBody();
        bean = (LegalAidData) wrapper.getBusinessData();
//        private String     caseSourceDesc;
//        private  String     applyMatterDesc;
//        private  String     caseStageDesc;

        tvReviewData.setText(bean.getCheckDate());//审批日期
        tvWenhua.setText( bean.getEducationDegreeDesc());
        tvRenqun.setText(bean.getCrowdTypeDesc());
        tvCase.setText(bean.getCaseSourceDesc());

        tvMatters.setText(bean.getApplyMatterDesc());

        tvMattersStage.setText(bean.getCaseStageDesc());
        tvBiaozhun.setText(bean.getEconomicSituationDesc());//setEconomicSituationd
        etExaminationOpinion.setText(bean.getFyOpinion());//fyOpinion

//        mLegalAidWorkflow.setEducationDegreeDesc(bean.getEducationDegreeDesc());
//        mLegalAidWorkflow.setCrowdTypeDesc(bean.getCrowdTypeDesc());
//        mLegalAidWorkflow.setCaseSourceDesc(bean.getCaseSourceDesc());
//        mLegalAidWorkflow.setApplyMatterDesc(bean.getApplyMatterDesc());
//        mLegalAidWorkflow.setCaseStageDesc(bean.getCaseStageDesc());
//        mLegalAidWorkflow.setEconomicSituationDesc(bean.getEconomicSituationDesc());


        if (TextUtils.isEmpty(bean.getProxyName())) {
            mLlAgency.setVisibility(View.GONE);
        }
        mEtName.setText(bean.getName());
        mEtSex.setText(bean.getSexDesc());
        mEtEthnic.setText(bean.getEthnicDesc());
        mEtBirthday.setText(bean.getBirthday());
        mEtIdCardNum.setText(bean.getIdCard());
        Area area = bean.getArea();
        if (area != null) {
            mEtArea.setText(area.getName());
        }
        mEtWorkAddress.setText(bean.getEmployer());
        mEtHomeAddress.setText(bean.getDomicile());
        mEtAddress.setText(bean.getAddress());
        mEtPhoneNum.setText(bean.getPhone());
        mEtAgentName.setText(bean.getProxyName());
        mEtAgentIdCardNum.setText(bean.getProxyIdCard());
        mEtAgentPerson.setText(bean.getProxyTypeDesc());
        mEtTitle.setText(bean.getCaseTitle());
        mEtType.setText(bean.getCaseTypeDesc());
        mEtLineMengyu.setText(bean.getHasMongolDesc());
        mEtContent.setText(bean.getCaseReason());

    }

    @Override
    @OnClick({R.id.btn_no, R.id.btn_yes,R.id.tv_wenhua,R.id.tv_renqun,R.id.tv_biaozhun,R.id.tv_case
            ,R.id.tv_matters,R.id.tv_review_data,R.id.tv_matters_stage,R.id.tv_shenpi_data
    })
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.btn_no:
                showNoDialog();
                break;
            case R.id.btn_yes:
                flag = Constant.YES;
                colycommitRequest();
                break;
            case R.id.tv_wenhua:
//                showPickerView(educPickerView);
                break;
            case R.id.tv_renqun:
//                showPickerView(peoplePickerView);
                break;

            case R.id.tv_biaozhun:
//                showPickerView(yesnoPickerView);
                break;

            case R.id.tv_case:
//                showPickerView(sourcePickerView);
                break;
            case R.id.tv_matters:
//                showPickerView(matterPickerView);
                break;
            case R.id.tv_matters_stage:
//                showPickerView(casePickerView);
                break;
            case R.id.tv_shenpi_data:
                birthdayPickerView = new TimePickerBuilder(mActivity, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        //申请日期
                        shenpiData.setText(getTime(date));
                        //        String approvalDate; //审批日期
                        mLegalAidWorkflow.setApprovalDate(getTime(date));
                    }
                })
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .isDialog(true)
                        .build();
                birthdayPickerView.show();
                break;
            default:
                break;
        }
    }
    private String getQueryStr() {
        mLegalAidWorkflow.setId(wrapper.getBusinessId());
        mLegalAidWorkflow.setAct(getAct());
        mLegalAidWorkflow.setCaseFile(caseFile);
        mLegalAidWorkflow.setCaseFile2(caseFile2);
        mLegalAidWorkflow.setFyfzrOpinion(shenpiOpinion.getText().toString().trim());//shenpiOpinion
        createFrom();
        return new Gson().toJson(mLegalAidWorkflow);
    }
    private void colycommitRequest() {
        OkGo.<BaseBean<Object>>post(NetConstant.LEGAL_AID_WORKFLOW)
                .params(NetConstant.QUERY, getQueryStr())
                .execute(new DialogCallBack<BaseBean<Object>>(mActivity, "提交中...") {
                    @Override
                    public void onSuccess(Response<BaseBean<Object>> response) {
                        if ("yes".equals(flag)) {
                            ToastUtils.showShort("通过成功");
                        } else {
                            ToastUtils.showShort("退回成功");
                        }
                        LocalBroadcastManager.getInstance(mActivity).sendBroadcast(new Intent(Constant.DEAL_BUSINESS));
                        setResult(RESULT_OK);
                        finishActivity();
                    }
                });
    }
    protected String getTime(Date date) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
        return s.format(date);
        // 此方法得出的结果是 xxxx 年 x 月 x 日，这里必须是 xxxx-xx-xx
        // return SimpleDateFormat.getDateInstance().format(date);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initPickerView();
    }
    private void initPickerView() {
//        String educationDegree; 人群类别,字段类型:crowd_type
//        String crowdType; 人群类别,字段类型:crowd_type
//        String caseSource; ;//案件来源,字段类型:case_source
//        String applyMatter; 申请事项,字段类型:apply_matter_type
//        String caseStage;  /申请事项所处阶段,字段类型:case_stage_type
//        String economicSituation;、、/是否符合法律援助经济困难标准,
//        String approvalDate; //审批日期
        initeducationrView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvWenhua.setText(educationrList.get(options1).getLabel());
                mLegalAidWorkflow.setEducationDegree(educationrList.get(options1).getValue());
            }
        });
        initPeopleView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvRenqun.setText(peopletypeList.get(options1).getLabel());
                mLegalAidWorkflow.setCrowdType(peopletypeList.get(options1).getValue());
            }
        });
        initSourceView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvCase.setText(sourceList.get(options1).getLabel());
                mLegalAidWorkflow.setCaseSource(sourceList.get(options1).getValue());
            }
        });

        initMatterView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvMatters.setText(matterList.get(options1).getLabel());
                mLegalAidWorkflow.setApplyMatter(educationrList.get(options1).getValue());
//                /       String applyMatter; 申请事项,字段类型:apply_matter_type
            }
        });
        initstageView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvMattersStage.setText(stageList.get(options1).getLabel());
                mLegalAidWorkflow.setCaseStage(stageList.get(options1).getValue());
//                String caseStage;  /申请事项所处阶段,字段类型:case_stage_type
            }
        });

        initCompanyView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

            }
        });
        initYesNoView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvBiaozhun.setText(yesnoList.get(options1).getLabel());
                mLegalAidWorkflow.setEconomicSituation(yesnoList.get(options1).getValue());
//                String economicSituation;、、/是否符合法律援助经济困难标准,
            }
        });
        initExamineView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {


            }
        });
    }
}

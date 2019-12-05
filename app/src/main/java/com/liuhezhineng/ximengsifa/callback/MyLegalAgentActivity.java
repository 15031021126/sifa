package com.liuhezhineng.ximengsifa.callback;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AnnexAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.Act;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.WorkflowActivity;
import com.liuhezhineng.ximengsifa.callback.bean.LegAgree;
import com.liuhezhineng.ximengsifa.callback.bean.MyLegaBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.module.mine.business.MyBusinessActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 申请法律援助
 */
public class MyLegalAgentActivity extends BaseActivity {
    @BindView(R.id.top_bar)
    QMUITopBar topBar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_id_card_num)
    EditText etIdCardNum;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.ll_birthday)
    LinearLayout llBirthday;
    @BindView(R.id.ll_sex_and_birthday)
    LinearLayout llSexAndBirthday;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_work_address)
    EditText etWorkAddress;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_home_address)
    EditText etHomeAddress;
    @BindView(R.id.et_home_addr)
    EditText etHomeAddr;
    @BindView(R.id.et_home_add)
    EditText etHomeAdd;
    @BindView(R.id.et_agent_name)
    EditText etAgentName;
    @BindView(R.id.et_agent_workunit)
    EditText etAgentWorkunit;
    @BindView(R.id.et_agent_residence)
    EditText etAgentResidence;
    @BindView(R.id.et_postal_Code)
    EditText etPostalCode;
    @BindView(R.id.et_postal_phone)
    EditText etPostalPhone;
    @BindView(R.id.ll_proxy)
    LinearLayout llProxy;
    @BindView(R.id.ed_beiname)
    EditText edBeiname;
    @BindView(R.id.ed_bei_fname)
    EditText edBeiFname;
    @BindView(R.id.ed_bei_post)
    EditText edBeiPost;
    @BindView(R.id.ed_bei_arrdes)
    EditText edBeiArrdes;
    @BindView(R.id.ed_bei_arrdesnum)
    EditText edBeiArrdesnum;
    @BindView(R.id.ed_bei_nodata)
    TextView edBeiNodata;
    @BindView(R.id.ll_nodata)
    LinearLayout llNodata;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_ti)
    EditText etTi;
    @BindView(R.id.et_behavior)
    EditText etBehavior;
    @BindView(R.id.et_behavior_jg)
    TextView etBehaviorJg;
    @BindView(R.id.et_behavior_qq)
    EditText etBehaviorQq;
    @BindView(R.id.tv_file)
    TextView tvFile;
    @BindView(R.id.rv_annex)
    RecyclerView rvAnnex;
    @BindView(R.id.ll_annex)
    LinearLayout llAnnex;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_no)
    Button btnNo;
    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.ll_btn)
    LinearLayout llBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mylega;
    }

    private String stringExtra;

    @Override
    protected void initView() {
        super.initView();
        tvFile.setVisibility(View.GONE);
        parcelableExtra = (BusinessBean) getIntent().getSerializableExtra(Constant.BUSINESS);
        stringExtra = getIntent().getStringExtra(Constant.UP_COMING);
        if (stringExtra.equals("已办")) {
            tvFile.setVisibility(View.GONE);
            llBtn.setVisibility(View.GONE);
        }
        if (stringExtra.equals("审核中")) {
            btnNo.setText("不予受理");
            btnYes.setText("受理");
        }
        if (stringExtra.equals("update")) {
            btnNo.setVisibility(View.GONE);
            btnYes.setText("提交");
        }

        initTopBar(topBar, parcelableExtra.getTask().getName());
        if (parcelableExtra != null) {
            Button btnWorkflow = topBar.addRightTextButton("查看流程", R.id.tv_case_title);
            btnWorkflow.setTextColor(Color.WHITE);
            btnWorkflow.setOnClickListener(
                    v -> WorkflowActivity.actionStart(mActivity, parcelableExtra));
        }
        initAnnex();
    }

    protected AnnexAdapter mAdapter;
    protected ArrayList<String> mAnnexList;

    private void initAnnex() {
        mAnnexList = new ArrayList<>();
        mAdapter = new AnnexAdapter(mActivity, mAnnexList);
        rvAnnex.setAdapter(mAdapter);
    }

    BusinessBean parcelableExtra;

    @Override
    protected void initData() {
        super.initData();
        initPeoData();
    }

    private MyLegaBean.BodyBean bodyBean;

    @Override
    protected void onRestart() {
        super.onRestart();
        initPeoData();
    }

    private void initPeoData() {
        Map<String, String> map = new HashMap<>();
        map.put("procInsId", parcelableExtra.getProcInsId());
        map.put("procDefId", parcelableExtra.getProcDefId());
        map.put("procDefKey", parcelableExtra.getProcDefKey());
        map.put("taskId", parcelableExtra.getTask().getId());
        map.put("taskName", parcelableExtra.getTask().getName());
        map.put("taskDefKey", parcelableExtra.getTask().getTaskDefinitionKey());
        OkGo.<MyLegaBean>get(NetConstant.GET_PEOPLES_MEDIATION_WORKFLOW).params("query", new Gson().toJson(map)).execute(new JsonCallback2<MyLegaBean>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSuccess(Response<MyLegaBean> response) {
                ///api/200/730/10
                bodyBean = response.body().getBody();
                MyLegaBean.BodyBean.BusinessDataBean businessData = bodyBean.getBusinessData();

                if (stringExtra.equals("update")){
                    //update才回去里面取值
                    LegDialog legDialog=new LegDialog(MyLegalAgentActivity.this,businessData,R.style.DialogTheme);
                    legDialog.create();
                    legDialog.show();

                }

                String caseFile = businessData.getCaseFile();
                if (caseFile != null) {
                    String[] split = caseFile.split("\\|");
                    for (int i = 0; i < split.length; i++) {
                        if (split[i].equals(""))continue;
                        mAdapter.addLegData(split[i]);
                    }
                }

                etName.setText(businessData.getApplyName());
                tvBirthday.setText(businessData.getApplyBirthday());
                etPhone.setText(businessData.getApplyPhone());
                if (businessData.getApplySex().equals("1")) {
                    tvSex.setText("男");
                } else {
                    tvSex.setText("女");
                }
                etIdCardNum.setText(businessData.getApplyPapernum());
                etHomeAdd.setText(businessData.getApplyZipCode());
                etHomeAddr.setText(businessData.getApplyPost());
                etWorkAddress.setText(businessData.getApplyWorkUnit());
                etAddress.setText(businessData.getApplyAddress());
                etHomeAddress.setText(businessData.getApplyLegalName());
                //代理人信息
                etAgentResidence.setText(businessData.getAgentAddress());
                etAgentName.setText(businessData.getAgentName());
                etAgentWorkunit.setText(businessData.getAgentWorkUnit());
                etPostalPhone.setText(businessData.getAgentPhone());
                etPostalCode.setText(businessData.getAgentZipCode());
                edBeiname.setText(businessData.getRespondentName());
                edBeiFname.setText(businessData.getRespondentLegalName());
                edBeiPost.setText(businessData.getRespondentPost());
                edBeiArrdes.setText(businessData.getRespondentAddress());
                edBeiArrdesnum.setText(businessData.getRespondentZipCode());
                edBeiNodata.setText(businessData.getIncidentDate());
                etBehavior.setText(businessData.getAdministrationBehavior());
                etTitle.setText(businessData.getCaseTitle());
                etTi.setText(businessData.getApplyDate());
                etBehaviorQq.setText(businessData.getReconsiderationRequest());
                etContent.setText(businessData.getReconsiderationReason());
                etBehaviorJg.setText(businessData.getReconsiderationOfficeId().getName());


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_no, R.id.btn_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_no:
                disagree();
                break;
            case R.id.btn_yes:
                agree();
                break;
        }
    }

    private void disagree() {
        switch (bodyBean.getTaskDefKey()) {
            case "reconsider_approve":
                Intent intent = new Intent(MyLegalAgentActivity.this, LegaCorrectionActivity.class);
                intent.putExtra("bean", bodyBean);
                intent.putExtra("KEY", "不同意");
                startActivity(intent);
                finish();
                break;
            case "reconsider_verify":
                Intent intent1 = new Intent(MyLegalAgentActivity.this, LegaCorrectionActivity.class);
                intent1.putExtra("bean", bodyBean);
                intent1.putExtra("KEY", "不受理");
                startActivity(intent1);
                finish();
                break;
        }
    }

    private void agree() {
        switch (bodyBean.getTaskDefKey()) {
            case "reconsider_verify":
                Intent intent1 = new Intent(MyLegalAgentActivity.this, LegaCorrectionActivity.class);
                intent1.putExtra("bean", bodyBean);
                intent1.putExtra("KEY", "受理");
                startActivity(intent1);
                break;
            case "reconsider_approve":
            case "reconsider_update":
                //同意
                initLegDialog();
                LegAgree legAgree = new LegAgree();
                LegAgree.ActBean actBean = new LegAgree.ActBean();
                actBean.setFlag("yes");
                actBean.setProcDefId(bodyBean.getProcDefId());
                actBean.setProcDefKey(bodyBean.getProcDefKey());
                actBean.setProcInsId(bodyBean.getProcInsId());
                actBean.setTaskId(bodyBean.getTaskId());
                actBean.setTaskName(bodyBean.getTaskName());
                actBean.setTaskDefKey(bodyBean.getTaskDefKey());
                legAgree.setAct(actBean);
                legAgree.setId(bodyBean.getBusinessData().getId());
                String toJson = new Gson().toJson(legAgree);
                Log.i("sss", toJson);
                //同意
                OkGo.<BaseBean>post(NetConstant.POST_AGREE).params("query", toJson).execute(new JsonCallback2<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        dialog.dismiss();
                        if (response.body().getStatus() == 0) {
                            Toast.makeText(MyLegalAgentActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MyLegalAgentActivity.this, "上传失败，请重试", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                        MyBusinessActivity.actionStart(mActivity);
                    }
                });
                break;
        }
    }

    private ProgressDialog dialog;

    private void initLegDialog() {
        dialog = new ProgressDialog(mActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(mActivity.getString(R.string.loading));
    }
}

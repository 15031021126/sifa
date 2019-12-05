package com.liuhezhineng.ximengsifa.callback;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.formatter.IFillFormatter;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.callback.bean.Accept;
import com.liuhezhineng.ximengsifa.callback.bean.LegNoAgree;
import com.liuhezhineng.ximengsifa.callback.bean.MyLegaBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.module.mine.business.MyBusinessActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lishangnan on 2019/11/14.
 */

public class LegaCorrectionActivity extends BaseActivity {
    @BindView(R.id.top_bar)
    QMUITopBar topBar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_symbol)
    EditText etSymbol;
    @BindView(R.id.et_matter)
    EditText etMatter;
    @BindView(R.id.et_material_science)
    EditText etMaterialScience;
    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.ll_btn)
    LinearLayout llBtn;
    @BindView(R.id.ll_edt)
    LinearLayout llEdt;
    @BindView(R.id.tv_liyou)
    TextView tvLiyou;
    @BindView(R.id.tv_yiju)
    TextView tvYiju;
    @BindView(R.id.tv_name_title)
    TextView tvNameTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_correction_lega;
    }


    private MyLegaBean.BodyBean bodyBean;
    private String key;

    @Override
    protected void initView() {
        super.initView();
        bodyBean = getIntent().getParcelableExtra("bean");
        key = getIntent().getStringExtra("KEY");
        Log.i("sss", "同key"+key);
        initTopBar(topBar, "");
        switch (key) {
            case "受理":
                tvNameTitle.setText("行政复议受理通知书");
                break;
            case "不受理":
                tvNameTitle.setText("不予受理行政复议申请决定书");
                llEdt.setVisibility(View.VISIBLE);
                tvLiyou.setText("不予受理理由");
                tvYiju.setText("不予受理依据");
                break;
            case "不同意":
                tvNameTitle.setText("行政复议申请不同意通知书");
                llEdt.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_yes)
    public void onViewClicked() {
        switch (bodyBean.getTaskDefKey()) {
            case "reconsider_verify":
                if (key.equals("受理")) {
                    Log.i("sss", "受理");
                    ///api/200/730/40 accept 
                    accept();
                } else {
                    Log.i("sss", "不受理");
                    noAccept();
                }
                break;
            case "reconsider_approve":
                //同意
                if (key.equals("不同意")) {
                    Log.i("sss", "不同意");
                    goOn();
                } else {
                    Log.i("sss", "同意");
                }
                break;
        }
    }
    private void noAccept() {
        if (!chenk()){
            return;
        }
        String trim2 = etMatter.getText().toString().trim();
        String trim3 = etMaterialScience.getText().toString().trim();
        if (trim2.equals("")){
            Toast.makeText(LegaCorrectionActivity.this,"内容填写错误",Toast.LENGTH_SHORT).show();
            return;
        }
        if (trim3.equals("")){
            Toast.makeText(LegaCorrectionActivity.this,"内容填写错误",Toast.LENGTH_SHORT).show();
            return;
        }
        LegNoAgree legAgree = new LegNoAgree();
        LegNoAgree.ActBean actBean = new LegNoAgree.ActBean();
        actBean.setFlag("no");
        actBean.setProcDefId(bodyBean.getProcDefId());
        actBean.setProcDefKey(bodyBean.getProcDefKey());
        actBean.setProcInsId(bodyBean.getProcInsId());
        actBean.setTaskId(bodyBean.getTaskId());
        actBean.setTaskName(bodyBean.getTaskName());
        actBean.setTaskDefKey(bodyBean.getTaskDefKey());
        legAgree.setAct(actBean);
        legAgree.setId("");
        legAgree.setApplyId(bodyBean.getBusinessId());
        legAgree.setTitle(trim);
        legAgree.setSymbol(trim1);
        legAgree.setReason(trim2);
        legAgree.setRequiredMaterials(trim3);
        Log.i("sss", new Gson().toJson(legAgree));
        OkGo.<BaseBean>post(NetConstant.POST_NOACCEPT).params("query", new Gson().toJson(legAgree)).execute(new JsonCallback2<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                if (response.body().getStatus()==0){
                    Toast.makeText(LegaCorrectionActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LegaCorrectionActivity.this,"提交失败稍后再试",Toast.LENGTH_SHORT).show();
                }
                finish();
                MyBusinessActivity.actionStart(mActivity);
            }
        });

    }

    //受理
    private void accept() {
        if (!chenk()){
                return;
        }
        Accept legAgree = new Accept();
        Accept.ActBean actBean = new Accept.ActBean();
        actBean.setFlag("yes");
        actBean.setProcDefId(bodyBean.getProcDefId());
        actBean.setProcDefKey(bodyBean.getProcDefKey());
        actBean.setProcInsId(bodyBean.getProcInsId());
        actBean.setTaskId(bodyBean.getTaskId());
        actBean.setTaskName(bodyBean.getTaskName());
        actBean.setTaskDefKey(bodyBean.getTaskDefKey());
        legAgree.setAct(actBean);
        legAgree.setId("");
        legAgree.setApplyId(bodyBean.getBusinessId());
        legAgree.setTitle(trim);
        legAgree.setSymbol(trim1);
        Log.i("sss", new Gson().toJson(legAgree));
        OkGo.<BaseBean>post(NetConstant.POST_ACCEPT).params("query", new Gson().toJson(legAgree)).execute(new JsonCallback2<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                if (response.body().getStatus()==0){
                        Toast.makeText(LegaCorrectionActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LegaCorrectionActivity.this,"提交失败稍后再试",Toast.LENGTH_SHORT).show();
                }
                finish();
                MyBusinessActivity.actionStart(mActivity);
            }
        });
    }
    private   String trim;
    private   String trim1;
    private boolean chenk() {
         trim = etName.getText().toString().trim();
         trim1 = etSymbol.getText().toString().trim();
         if (trim.equals("")){
             Toast.makeText(LegaCorrectionActivity.this,"内容填写错误",Toast.LENGTH_SHORT).show();
             return false;
         }
         if (trim1.equals("")){
             Toast.makeText(LegaCorrectionActivity.this,"内容填写错误",Toast.LENGTH_SHORT).show();
             return false;
         }
         return  true;
    }

    //驳回
    private void goOn( ) {
        if (!chenk()){
            return;
        }
        String trim2 = etMatter.getText().toString().trim();
        String trim3 = etMaterialScience.getText().toString().trim();
        if (trim2.equals("")){
            Toast.makeText(LegaCorrectionActivity.this,"内容填写错误",Toast.LENGTH_SHORT).show();
            return;
        }
        if (trim3.equals("")){
            Toast.makeText(LegaCorrectionActivity.this,"内容填写错误",Toast.LENGTH_SHORT).show();
            return;
        }
        LegNoAgree legAgree = new LegNoAgree();
        LegNoAgree.ActBean actBean = new LegNoAgree.ActBean();
        actBean.setFlag("no");
        actBean.setProcDefId(bodyBean.getProcDefId());
        actBean.setProcDefKey(bodyBean.getProcDefKey());
        actBean.setProcInsId(bodyBean.getProcInsId());
        actBean.setTaskId(bodyBean.getTaskId());
        actBean.setTaskName(bodyBean.getTaskName());
        actBean.setTaskDefKey(bodyBean.getTaskDefKey());
        legAgree.setAct(actBean);
        legAgree.setId("");
        legAgree.setApplyId(bodyBean.getBusinessId());
        legAgree.setTitle(trim);
        legAgree.setSymbol(trim1);
        legAgree.setReason(trim2);
        legAgree.setRequiredMaterials(trim3);
        Log.i("sss", new Gson().toJson(legAgree));
        OkGo.<BaseBean>post(NetConstant.POST_NOAGREE).params("query", new Gson().toJson(legAgree)).execute(new JsonCallback2<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                if (response.body().getStatus()==0){
                    Toast.makeText(LegaCorrectionActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LegaCorrectionActivity.this,"提交失败稍后再试",Toast.LENGTH_SHORT).show();
                }
                finish();
                MyBusinessActivity.actionStart(mActivity);
            }
        });
    }
}

package com.liuhezhineng.ximengsifa.business.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AnnexAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.Act;
import com.liuhezhineng.ximengsifa.bean.bussiness.BaseBusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.DeprecateBean;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.WorkflowActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.constant.NetConstant.Notice;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.StringDialogCallback;
import com.liuhezhineng.ximengsifa.module.mine.business.MyBusinessActivity;
import com.liuhezhineng.ximengsifa.utils.UploadUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 业务基类: 申请法援、人民调解、通知辩护、快速申请
 * 业务中一些共有操作 一些方便的方法
 */
public abstract class BaseBusinessActivity extends BaseActivity {

    public static final int FROM_SERVICE = 0;
    public static final int FROM_DRAFT = 1;
    protected BaseBusinessBean mBaseBusinessBean;
    protected BusinessBean mBusinessBean;
    protected DeprecateBean mDeprecateBean;
    protected String procInsId;
    /**
     * 从哪里进入申请页面：1、服务；2、草稿。
     */
    protected int fromFlag;
    /**
     * 案件附件
     */
    protected String caseFile = "";
    /**
     * 承办人办理附件
     */
    protected String caseFile2 = "";
    protected String flag;
    protected String comment;
    protected boolean requestFlag;
    protected AnnexAdapter mAdapter;
    protected ArrayList<String> mAnnexList;

    protected View mViewBreak;
    protected TextView mTvFileUpload;
    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
  public   Button btnWorkflow;
    @Override
    protected void initView() {
        super.initView();
        mBusinessBean = (BusinessBean) getIntent().getSerializableExtra(Constant.BUSINESS);
        if (mBusinessBean != null) {
            initTopBar(mTopBar, mBusinessBean.getTask().getName());
            if (mBusinessBean != null) {
                 btnWorkflow = mTopBar.addRightTextButton("查看流程", R.id.tv_case_title);
                btnWorkflow.setTextColor(Color.WHITE);
                btnWorkflow.setOnClickListener(
                        new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WorkflowActivity.actionStart(mActivity, mBusinessBean);
                            }
                        });
            }
        }

        mViewBreak = findViewById(R.id.btn_deprecate);

        // fast legal 没有附件上传
        mTvFileUpload = findViewById(R.id.tv_file);
        if (mTvFileUpload != null) {
            initAnnex();
        }
    }

    @Override
    protected void initData() {
        super.initData();

        String id = getIntent().getStringExtra("id");
        String procDefKey = getIntent().getStringExtra("procDefKey");
        if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(procDefKey)) {
            fromFlag = FROM_DRAFT;
            loadDraft(id, procDefKey);
        } else {
            fromFlag = FROM_SERVICE;
            loadUserInfo();
        }
    }

    protected void loadDraft(String id, String procDefKey) {
    }

    protected void loadUserInfo() {
    }

    @Override
    protected void setListener() {
        super.setListener();
        if (mViewBreak != null) {
            mViewBreak.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag = Constant.END;
                    showNoDialog();
                }
            });
        }
        if (mTvFileUpload != null) {
            mTvFileUpload.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//					openFileManager();
                    UploadUtils.openFileManager(mActivity);
                }
            });
        }
    }

    protected void showNoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(mActivity, R.layout.dialog_no, null);

        TextView tvTitle = dialogView.findViewById(R.id.tv_title);
        final EditText etReason = dialogView.findViewById(R.id.et_no_reason);
        Button btnCancel = dialogView.findViewById(R.id.btn_no);
        Button btnConfirm = dialogView.findViewById(R.id.btn_yes);

        String errorStr = "退回原因不能为空";
        if (Constant.END.equals(flag)) {
            etReason.setHint(getResources().getText(R.string.deprecate_hint));
            tvTitle.setText(getResources().getText(R.string.deprecate_reason));
            btnConfirm.setText(getResources().getText(R.string.confirm_deprecate));
            errorStr = (String) getResources().getText(R.string.deprecated_reason_not_null);
        }

        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        final String finalErrorStr = errorStr;
        btnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                comment = etReason.getText().toString().trim();
                if (TextUtils.isEmpty(comment)) {
                    ToastUtils.showLong(finalErrorStr);
                    return;
                }
                if (Constant.END.equals(flag)) {
                    deprecateBusiness();
                } else {
                    dialogCommit();
                }
                dialog.dismiss();
            }
        });
        dialog.setView(dialogView);
        dialog.show();
    }

    protected void deprecateBusiness() {
        mDeprecateBean = new DeprecateBean();
        Act act = getAct();
        if (requestFlag) {
            act.setFlag("request user");
        }
        mDeprecateBean.setRemarks(comment);
        mDeprecateBean.setAct(act);
        mDeprecateBean.setId(mBaseBusinessBean.getId());
        String queryStr = new Gson().toJson(mDeprecateBean);
        OkGo.<String>post(Notice.DEPRECATE_BUSINESS)
                .params(Constant.QUERY, queryStr)
                .execute(new StringDialogCallback(mActivity,
                        (String) getResources().getText(R.string.deprecate_loading)) {
                    @Override
                    protected void responseSuccess(JSONObject object) {
                        ToastUtils.showLong(R.string.deprecate_success);
                        setResult(RESULT_OK);
                        finishActivity();
                    }
                });
    }

    protected void dialogCommit() {
    }

    @NonNull
    protected Act getAct() {
        Act act = new Act();
        act.setProcInsId(mBusinessBean.getProcInsId());
        act.setProcDefId(mBusinessBean.getProcDefId());
        act.setProcDefKey(mBusinessBean.getProcDefKey());
        act.setTaskId(mBusinessBean.getTask().getId());
        act.setTaskName(mBusinessBean.getTask().getName());
        act.setTaskDefKey(mBusinessBean.getTask().getTaskDefinitionKey());
        act.setFlag(flag);
        act.setComment(comment);
        return act;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == UploadUtils.ACTION_GET_CONTENT) {
            String path;
            Uri uri = data.getData();
            if (uri != null) {
                path = UploadUtils.getFilePathViaUri(mActivity, uri);
                uploadFile(path);
            }
        }
    }

    @Override
    @OnClick()
    public void onViewClicked(View view) {
        KeyboardUtils.hideSoftInput(mActivity);
    }

    protected void uploadFile(final String path) {
        OkGo.<BaseBean<String>>post(NetConstant.UPLOAD_FILE)
                .params(NetConstant.FILE, new File(path))
                .execute(new DialogCallBack<BaseBean<String>>(mActivity, "附件上传中") {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        uploadSuccess(response.body().getBody());
                        ToastUtils.showShort("附件上传成功");
                    }
                });
    }
    public String  stringBuffer;
    protected void uploadSuccess(String path) {
        caseFile += "|" + path;
        // substring(begin, end) [begin, end) start 0.
        mAdapter.addData(path);
    }

    private void initAnnex() {
        RecyclerView mRvAnnex = findViewById(R.id.rv_annex);
        mAnnexList = new ArrayList<>();
        mAdapter = new AnnexAdapter(mActivity, mAnnexList);
        mRvAnnex.setAdapter(mAdapter);
    }

    protected void commitResult() {
//		if (FROM_SERVICE == fromFlag) {
        MyBusinessActivity.actionStart(mActivity, Constant.HAVE_DONE);
//		} else if (FROM_DRAFT == fromFlag) {
//			setResult(RESULT_OK);
//		}
    }

    protected String getBusinessDataQueryJson() {
        Map<String, String> map = new HashMap<>(16);
        map.put(Constant.PROC_INS_ID, mBusinessBean.getProcInsId());
        map.put(Constant.PROC_DEF_ID, mBusinessBean.getProcDefId());
        map.put(Constant.PROC_DEF_KEY, mBusinessBean.getProcDefKey());
        map.put(Constant.TASK_ID, mBusinessBean.getTask().getId());
        map.put(Constant.TASK_NAME, mBusinessBean.getTask().getName());
        map.put(Constant.TASK_DEF_KEY, mBusinessBean.getTask().getTaskDefinitionKey());
        return new JSONObject(map).toString();
    }

    protected void setCaseFile(String caseFile) {
        this.caseFile = caseFile;
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

    protected boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    protected String getText(TextView view) {
        return view.getText().toString().trim();
    }
}

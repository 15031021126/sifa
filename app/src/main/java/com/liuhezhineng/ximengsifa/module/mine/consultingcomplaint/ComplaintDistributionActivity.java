package com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AnnexAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.ComplaintDistributeBean;
import com.liuhezhineng.ximengsifa.bean.advisory.AdvisoryBean;
import com.liuhezhineng.ximengsifa.bean.advisory.ConsultingDetailsBean;
import com.liuhezhineng.ximengsifa.bean.advisory.CreateUser;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @description 投诉分配页面
 */
public class ComplaintDistributionActivity extends BaseActivity {

    public static final int STATE_REFRESH = 0x00;

    protected AnnexAdapter mAnnexAdapter;
    protected ArrayList<String> mAnnexList;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_advisory_type)
    TextView mTvAdvisoryType;
    @BindView(R.id.tv_advisory_date)
    TextView mTvAdvisoryDate;
    @BindView(R.id.tv_advisory_content)
    TextView mTvAdvisoryContent;
    @BindView(R.id.rv_annex)
    RecyclerView mRvAnnex;
    @BindView(R.id.ll_annex)
    LinearLayout mLlAnnex;
    @BindView(R.id.tv_file)
    TextView mTvFile;
    @BindView(R.id.tv_personnel)
    TextView mTvPersonnel;
    AdvisoryBean mBean;
    String id;
    ComplaintDistributeBean mComplaintDistributeBean;
    private String caseFile;

    /**
     * @param context
     * @param bean
     */
    public static void actionStart(Context context, AdvisoryBean bean) {
        Intent intent = new Intent(context, ComplaintDistributionActivity.class);
        intent.putExtra(Constant.ADVISORY, bean);
        ((BaseActivity)context).startActivityForResult(intent, STATE_REFRESH);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_complaint_distribution;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(R.string.distribute_personnel);
        initAnnex();
    }

    private void initAnnex() {
        mTvFile.setVisibility(View.GONE);
        mAnnexList = new ArrayList<>();
        mAnnexAdapter = new AnnexAdapter(mActivity, mAnnexList);
        mRvAnnex.setAdapter(mAnnexAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        mComplaintDistributeBean = new ComplaintDistributeBean();
        mBean = (AdvisoryBean) getIntent().getSerializableExtra(Constant.ADVISORY);
        getComplaintsDetails();
    }

    @Override
    @OnClick({R.id.tv_personnel, R.id.btn_distribute})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.tv_personnel:
                ComplaintDistributeActivity.actionStart(mActivity);
                break;
            case R.id.btn_distribute:
                distribute();
                break;
            default:
                break;
        }
    }

    private void distribute() {
        if (TextUtils.isEmpty(mTvPersonnel.getText())) {
            ToastUtils.showLong("请选择要分配到的人员");
            return;
        }
        String queryStr = new Gson().toJson(mComplaintDistributeBean);
        OkGo.<BaseBean<String>>post(NetConstant.ConsultingComplaint.DISTRIBUTE)
                .params(Constant.QUERY, queryStr)
                .execute(new DialogCallBack<BaseBean<String>>(mActivity, "分配中...") {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        ToastUtils.showLong("分配成功");
                        setResult(RESULT_OK);
                        finishActivity();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constant.QA || requestCode == Constant.NORMAL_CODE) {
                initData();
            }
            if (requestCode == ComplaintDistributeActivity.REQUEST_CODE) {
                Office office = (Office) data.getSerializableExtra(ComplaintDistributeActivity.INTENT_EXTRA);
                mTvPersonnel.setText(office.getName());
                CreateUser createUser = new CreateUser();
                createUser.setId(office.getId());
                mComplaintDistributeBean.setHandleUser(createUser);
                mComplaintDistributeBean.setId(mBean.getId());
            }
        }
    }

    private void getComplaintsDetails() {
        Map<String, String> map = new HashMap<>(16);
        // 投诉建议 id
        map.put(Constant.ID, mBean.getId());
        String queryStr = new JSONObject(map).toString();
        commit(queryStr, NetConstant.GET_COMPLAINTS_DETAILS);
    }

    private void commit(String queryStr, String url) {
        OkGo.<BaseBean<ConsultingDetailsBean>>post(url)
                .params(NetConstant.QUERY, queryStr)
                .execute(new DialogCallBack<BaseBean<ConsultingDetailsBean>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseBean<ConsultingDetailsBean>> response) {
                        ConsultingDetailsBean consultingDetailsBean = response.body().getBody();
                        showDetails(consultingDetailsBean);
                    }
                });
    }

    private void showDetails(ConsultingDetailsBean consultingDetailsBean) {
        id = consultingDetailsBean.getId();
        setCaseFile(consultingDetailsBean.getFiles());
        mTvAdvisoryType.setText(consultingDetailsBean.getTypeDesc() + "-" + consultingDetailsBean.getProblemTypeDesc());
        // 如果为空这说明是投诉建议，显示其类型
        if (TextUtils.isEmpty(consultingDetailsBean.getTypeDesc())) {
            mTvAdvisoryType.setText(consultingDetailsBean.getRemarkTypeDesc());
        }
        mTvTitle.setText(consultingDetailsBean.getTitle());
        mTvAdvisoryDate.setText(consultingDetailsBean.getCreateDate());
        mTvAdvisoryContent.setText(consultingDetailsBean.getContent());
    }

    protected void setCaseFile(String caseFile) {
        this.caseFile = caseFile;
        // caseFile: "", "|xx", "null|xx"
        if (!TextUtils.isEmpty(caseFile)) {
            ArrayList<String> list = new ArrayList<>();
            String[] fileArray = caseFile.split("\\|");
            for (String path : fileArray) {
                if (!TextUtils.isEmpty(path)) {
                    list.add(path);
                }
            }
            mAnnexAdapter.initData(list);
        } else {
            mLlAnnex.setVisibility(View.GONE);
        }
    }
}


package com.liuhezhineng.ximengsifa.business.legalaid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AnnexAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessDetailsWrapper;
import com.liuhezhineng.ximengsifa.bean.bussiness.LegalAidData;
import com.liuhezhineng.ximengsifa.business.base.BaseLegalAidActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.utils.UploadUtils;
import com.liuhezhineng.ximengsifa.vidyo.VideoRequestActivity;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 承办人审核 办理
 */
public class EUndertakerActivity extends BaseLegalAidActivity {

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
    @BindView(R.id.tv_lawyer_office)
    TextView mTvLawyerOffice;
    @BindView(R.id.tv_legal_office)
    TextView mTvLegalOffice;
    @BindView(R.id.tv_lawyer)
    TextView mTvLawyer;
    @BindView(R.id.tv_legal_person)
    TextView mTvLegalPerson;
    @BindView(R.id.ll_lawyer)
    LinearLayout mLlLawyer;
    @BindView(R.id.ll_legal_person)
    LinearLayout mLlLegalPerson;
    @BindView(R.id.ll_law_office)
    LinearLayout mLlLawOffice;
    @BindView(R.id.ll_legal_office)
    LinearLayout mLlLegalOffice;
    @BindView(R.id.btn_no)
    Button mBtnNo;
    @BindView(R.id.btn_yes)
    Button mBtnYes;
    @BindView(R.id.tv_file1)
    TextView mtvFile1;
    @BindView(R.id.ll_aid_center)
    LinearLayout mLlAidCenter;
    @BindView(R.id.tv_aid_center)
    TextView mTvAidCenter;
    @BindView(R.id.ll_aid_lawyer)
    LinearLayout mLlAidLawyer;
    @BindView(R.id.tv_aid_lawyer)
    TextView mTvAidLawyer;
    @BindView(R.id.btn_vidyo)
    Button mBtnVidyo;

    LegalAidData bean;
    BusinessDetailsWrapper wrapper;
    @BindView(R.id.ll_annex2)
    LinearLayout mLlAnnex1;
    @BindView(R.id.rv_annex2)
    RecyclerView mRvAnnex1;
    ArrayList<String> mAnnexList1;
    AnnexAdapter mAdapter1;

    public static void actionStart(Context context, BusinessBean businessBean) {
        Intent intent = new Intent(context, EUndertakerActivity.class);
        intent.putExtra(Constant.BUSINESS, businessBean);
        ((BaseActivity) context).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_undertaker;
    }

    @Override
    @OnClick({R.id.btn_no, R.id.btn_yes, R.id.tv_file1, R.id.btn_vidyo})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.btn_vidyo:
                VideoRequestActivity.actionStart(mActivity, bean.getName(), bean.getCaseTitle(), bean.getCreateBy().getId());
                break;
            case R.id.tv_file1:
                UploadUtils.openFileManager(mActivity);
                break;
            case R.id.btn_no:
                showNoDialog();
                break;
            case R.id.btn_yes:
                flag = Constant.YES;
                commitRequest();
                break;
            default:
                break;
        }
    }

    @Override
    protected void uploadSuccess(String path) {
        caseFile2 += "|" + path;
        mLegalAidWorkflow.setCaseFile2(caseFile2);
        mAdapter1.addData(path);
    }

    @Override
    protected void showDetails(Response<BaseBean<BusinessDetailsWrapper<LegalAidData>>> response) {
        super.showDetails(response);
        wrapper = response.body().getBody();
        bean = (LegalAidData) wrapper.getBusinessData();

        mLegalAidWorkflow.setLawOffice(bean.getLawOffice());
        mLegalAidWorkflow.setLegalOffice(bean.getLegalOffice());
        mLegalAidWorkflow.setLawyer(bean.getLawyer());
        mLegalAidWorkflow.setLegalPerson(bean.getLegalPerson());

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

        if (TextUtils.isEmpty(bean.getLawAssistanceOffice().getName())) {
            mLlAidCenter.setVisibility(View.GONE);
        } else {
            mLlAidCenter.setVisibility(View.VISIBLE);
            mTvAidCenter.setText(bean.getLawAssistanceOffice().getName());
        }
        if (TextUtils.isEmpty(bean.getLawAssistUser().getName())) {
            mLlAidLawyer.setVisibility(View.GONE);
        } else {
            mLlAidLawyer.setVisibility(View.VISIBLE);
            mTvAidLawyer.setText(bean.getLawAssistUser().getName());
        }
        if (TextUtils.isEmpty(bean.getLawOffice().getName())) {
            mLlLawOffice.setVisibility(View.GONE);
        } else {
            mLlLawOffice.setVisibility(View.VISIBLE);
            mTvLawyerOffice.setText(bean.getLawOffice().getName());
        }
        if (StringUtils.isEmpty(bean.getLegalOffice().getName())) {
            mLlLegalOffice.setVisibility(View.GONE);
        } else {
            mLlLegalOffice.setVisibility(View.VISIBLE);
            mTvLegalOffice.setText(bean.getLegalOffice().getName());
        }
        if (TextUtils.isEmpty(bean.getLawyer().getName())) {
            mLlLawyer.setVisibility(View.GONE);
        } else {
            mLlLawyer.setVisibility(View.VISIBLE);
            mTvLawyer.setText(bean.getLawyer().getName());
        }
        if (TextUtils.isEmpty(bean.getLegalPerson().getName())) {
            mLlLegalPerson.setVisibility(View.GONE);
        } else {
            mLlLegalPerson.setVisibility(View.VISIBLE);
            mTvLegalPerson.setText(bean.getLegalPerson().getName());
        }
    }

    @Override
    protected void initView() {
        super.initView();
        String key = mBusinessBean.getTask().getTaskDefinitionKey();
        if ("aid_chengbanren_banli".equals(key)) {
            mBtnNo.setVisibility(View.GONE);
            mBtnYes.setText("办结");
            mLlAnnex1.setVisibility(View.VISIBLE);
            initAnnex1();
        } else {
            mLlAnnex1.setVisibility(View.GONE);
        }
    }

    private void initAnnex1() {
        mAnnexList1 = new ArrayList<>();
        mAdapter1 = new AnnexAdapter(mActivity, mAnnexList1);
        mRvAnnex1.setAdapter(mAdapter1);
    }
}
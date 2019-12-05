package com.liuhezhineng.ximengsifa.business.legalaid;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessDetailsWrapper;
import com.liuhezhineng.ximengsifa.bean.bussiness.LegalAidData;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.LawOffice;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.LegalOffice;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.bean.picker.InsBean;
import com.liuhezhineng.ximengsifa.business.base.BaseLegalAidActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 科员指定承办机构：如果申请人没有指定承办人，则有科员来指定承办机构
 */
public class CDesignatedOrganizerActivity extends BaseLegalAidActivity {

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
    @BindView(R.id.tv_aid_center)
    TextView mTvAidCenter;
    @BindView(R.id.tv_lawyer_office)
    TextView mTvLawyerOffice;
    @BindView(R.id.tv_legal_office)
    TextView mTvLegalOffice;
    @BindView(R.id.tv_aid_category)
    TextView mTvAidCategory;
    @BindView(R.id.tv_case_nature)
    TextView mTvCaseNature;
    @BindView(R.id.ll_legal_office)
    LinearLayout mLlLegalOffice;
    private BusinessDetailsWrapper wrapper;
    private LegalAidData bean;

    public static void actionStart(Context context, BusinessBean businessBean) {
        Intent intent = new Intent(context, CDesignatedOrganizerActivity.class);
        intent.putExtra(Constant.BUSINESS, businessBean);
        ((BaseActivity) context).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
    }

    @Override
    @OnClick({R.id.tv_legal_office,
            R.id.tv_lawyer_office,
            R.id.tv_aid_center,
            R.id.btn_yes})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.tv_aid_center:
                showPickerView(aidCenterPicker);
                break;
            case R.id.tv_lawyer_office:
                showPickerView(lawyerOfficePicker);
                break;
            case R.id.tv_legal_office:
                showPickerView(legalOfficePicker);
                break;
            case R.id.btn_yes:
                Office aidCenter = mLegalAidWorkflow.getLawAssistanceOffice();
                LawOffice lawOffice = mLegalAidWorkflow.getLawOffice();
                LegalOffice legalOffice = mLegalAidWorkflow.getLegalOffice();
                boolean isDesignatedOrganizer =
                        (lawOffice == null || TextUtils.isEmpty(lawOffice.getName()))
                                && (legalOffice == null || TextUtils.isEmpty(legalOffice.getName()))
                                && (aidCenter == null || TextUtils.isEmpty(aidCenter.getName()));
                if (isDesignatedOrganizer) {
                    ToastUtils.showLong("未指定承办机构");
                    return;
                }
                flag = Constant.YES;
                commitRequest();
                break;
            default:
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhi_ding_cheng_ban_ji_gou;
    }

    @Override
    protected void showDetails(Response<BaseBean<BusinessDetailsWrapper<LegalAidData>>> response) {
        super.showDetails(response);
        wrapper = response.body().getBody();
        bean = (LegalAidData) wrapper.getBusinessData();

        // 设置表单上一步填好的文本
        setFromText();

        if (!TextUtils.isEmpty(bean.getArea().getId())) {
            initAidCenterPicker(new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    if (options1 < 0) {
                        ToastUtils.showLong("请等待数据加载完成");
                        return;
                    }
                    InsBean bean = aidCenterList.get(options1);
                    if (Constant.PICKER_NO_DATA.equals(bean.getId())) {
                        ToastUtils.showLong(bean.getAgencyName());
                        return;
                    }
                    Office lawOffice = new Office();
                    lawOffice.setId(bean.getId());
                    lawOffice.setName(bean.getAgencyName());

                    mTvAidCenter.setText(bean.getAgencyName());
                    mLegalAidWorkflow.setLawAssistanceOffice(lawOffice);

                    mTvLawyerOffice.setText("");
                    mLegalAidWorkflow.setLawOffice(new LawOffice());

                    mTvLegalOffice.setText("");
                    mLegalAidWorkflow.setLegalOffice(new LegalOffice());
                }
            }, bean.getArea().getId());
            initLawyerOfficePicker(new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    if (options1 < 0) {
                        ToastUtils.showLong("请等待数据加载完成");
                        return;
                    }
                    InsBean person = lawyerOfficeList.get(options1);
                    if (Constant.PICKER_NO_DATA.equals(person.getId())) {
                        ToastUtils.showLong("在地区下暂无律所");
                        return;
                    }
                    InsBean bean = lawyerOfficeList.get(options1);
                    LawOffice lawOffice = new LawOffice();
                    lawOffice.setId(bean.getId());
                    lawOffice.setName(bean.getAgencyName());

                    mTvLawyerOffice.setText(bean.getAgencyName());
                    mLegalAidWorkflow.setLawOffice(lawOffice);

                    mTvLegalOffice.setText("");
                    mLegalAidWorkflow.setLegalOffice(new LegalOffice());

                    mTvAidCenter.setText("");
                    mLegalAidWorkflow.setLawAssistanceOffice(new Office());
                }
            }, bean.getArea().getId());

            if (!"刑事".contains(bean.getCaseTypeDesc())) {
                initLegalOfficePicker(new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        if (options1 < 0) {
                            ToastUtils.showLong("请等待数据加载完成");
                            return;
                        }
                        InsBean person = legalOfficeList.get(options1);
                        if (Constant.PICKER_NO_DATA.equals(person.getId())) {
                            ToastUtils.showLong("在地区下暂无基层法律服务所");
                            return;
                        }
                        InsBean bean = legalOfficeList.get(options1);
                        LegalOffice legalOffice = new LegalOffice();
                        legalOffice.setId(bean.getId());
                        legalOffice.setName(bean.getAgencyName());

                        mTvLegalOffice.setText(bean.getAgencyName());
                        mLegalAidWorkflow.setLegalOffice(legalOffice);

                        mTvLawyerOffice.setText("");
                        mLegalAidWorkflow.setLawOffice(new LawOffice());

                        mTvAidCenter.setText("");
                        mLegalAidWorkflow.setLawAssistanceOffice(new Office());
                    }
                }, bean.getArea().getId());
            } else {
                mLlLegalOffice.setVisibility(View.GONE);
            }
        }
    }

    private void setFromText() {
        mEtName.setText(bean.getName());
        mEtSex.setText(bean.getSexDesc());
        mEtEthnic.setText(bean.getEthnicDesc());
        mEtBirthday.setText(bean.getBirthday());
        mEtIdCardNum.setText(bean.getIdCard());
        mEtArea.setText(bean.getArea().getName());
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
}

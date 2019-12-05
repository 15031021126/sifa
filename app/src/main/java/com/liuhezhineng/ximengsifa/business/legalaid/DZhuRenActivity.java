package com.liuhezhineng.ximengsifa.business.legalaid;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessDetailsWrapper;
import com.liuhezhineng.ximengsifa.bean.bussiness.InsUserBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.LegalAidData;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.LawOffice;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.Lawyer;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.LegalOffice;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.LegalPerson;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.bean.personal.PersonInsBean;
import com.liuhezhineng.ximengsifa.business.base.BaseLegalAidActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.utils.PickerViewBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 主任审核：法援科员指定了承办机构，承办机构主任审核，指定承办人，审核是否通过。
 */
public class DZhuRenActivity extends BaseLegalAidActivity {

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
    @BindView(R.id.ll_aid_center)
    LinearLayout mLlAidCenter;
    @BindView(R.id.tv_aid_center)
    TextView mTvAidCenter;
    @BindView(R.id.ll_aid_lawyer)
    LinearLayout mLlAidLawyer;
    @BindView(R.id.tv_aid_lawyer)
    TextView mTvAidLawyer;
    @BindView(R.id.tv_allow_timeout)
    TextView mTvAllowTimeout;
    @BindView(R.id.tv_timeout_date)
    TextView mTvTimeoutDate;

    LegalAidData bean;
    BusinessDetailsWrapper wrapper;
    private OptionsPickerView lawyerPersonPickerView;
    private OptionsPickerView legalPersonView;

    public static void actionStart(Context context, BusinessBean businessBean) {
        Intent intent = new Intent(context, DZhuRenActivity.class);
        intent.putExtra(Constant.BUSINESS, businessBean);
        ((BaseActivity) context).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
    }

    @Override
    @OnClick({R.id.tv_aid_lawyer, R.id.tv_lawyer, R.id.tv_legal_person,
            R.id.tv_allow_timeout, R.id.tv_timeout_date,
            R.id.btn_no, R.id.btn_yes})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.tv_aid_lawyer:
                showPickerView(aidLawyerPicker);
                break;
            case R.id.tv_lawyer:
                showPickerView(lawyerPersonPickerView);
                break;
            case R.id.tv_legal_person:
                showPickerView(legalPersonView);
                break;
            case R.id.tv_allow_timeout:
                showPickerView(warningStateTypePicker);
                break;
            case R.id.tv_timeout_date:
                showPickerView(warningTimeTypePicker);
                break;
            case R.id.btn_no:
                showNoDialog();
                break;
            case R.id.btn_yes:
                contractorAudlt();
                break;
            default:
                break;
        }
    }

    private void contractorAudlt() {
        Office aidLawyer = mLegalAidWorkflow.getLawAssistUser();
        Lawyer lawyer = mLegalAidWorkflow.getLawyer();
        LegalPerson person = mLegalAidWorkflow.getLegalPerson();
        boolean isDesignatedUndertaker =
                (aidLawyer == null || TextUtils.isEmpty(aidLawyer.getName()))
                        && (lawyer == null || TextUtils.isEmpty(lawyer.getName()))
                        && (person == null || TextUtils.isEmpty(person.getName()));
        if (isDesignatedUndertaker) {
            ToastUtils.showLong("未指定承办人");
            return;
        }
        flag = Constant.YES;
        commitRequest();
    }

    @Override
    protected void initData() {
        super.initData();
        initWarningStateTypePicker((option1, option2, option3, v) -> {
            TypeBean typeBean = warningStateTypeList.get(option1);
            mTvAllowTimeout.setText(typeBean.getLabel());
            mLegalAidWorkflow.setWarningState(typeBean.getValue());
        });
        initWarningTimeTypePicker((options1, options2, options3, v) -> {
            TypeBean typeBean = warningTimeTypeList.get(options1);
            mTvTimeoutDate.setText(typeBean.getLabel());
            mLegalAidWorkflow.setWarningTime(typeBean.getValue());
        });
    }

    @Override
    protected void showDetails(Response<BaseBean<BusinessDetailsWrapper<LegalAidData>>> response) {
        super.showDetails(response);
        wrapper = response.body().getBody();
        bean = (LegalAidData) wrapper.getBusinessData();

        mLegalAidWorkflow.setLawAssistanceOffice(bean.getLawAssistanceOffice());
        mLegalAidWorkflow.setLawAssistUser(bean.getLawAssistUser());
        mLegalAidWorkflow.setLawOffice(bean.getLawOffice());
        mLegalAidWorkflow.setLegalOffice(bean.getLegalOffice());
        mLegalAidWorkflow.setLawyer(bean.getLawyer());
        mLegalAidWorkflow.setLegalPerson(bean.getLegalPerson());

        Area area = bean.getArea();
        Office aidCenter = bean.getLawAssistanceOffice();
        if (aidCenter == null || TextUtils.isEmpty(aidCenter.getId())) {
            mLlAidCenter.setVisibility(View.GONE);
            mLlAidLawyer.setVisibility(View.GONE);
        } else {
            mTvAidCenter.setText(aidCenter.getName());
            initAidLawyerPicker(new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3,
                                            View v) {
                    if (options1 < 0) {
                        ToastUtils.showLong("请等待数据加载完成");
                        return;
                    }
                    PersonInsBean person = aidLawyerList.get(options1);
                    if (Constant.PICKER_NO_DATA.equals(person.getId())) {
                        ToastUtils.showLong(person.getPersonName());
                        return;
                    }
                    Office aidLawyer = new Office();
                    aidLawyer.setId(person.getId());
                    aidLawyer.setName(person.getPersonName());
                    mTvAidLawyer.setText(aidLawyer.getName());
                    mLegalAidWorkflow.setLawAssistUser(aidLawyer);

                }
            }, area.getId(), aidCenter.getId());
        }

        LawOffice lawOffice = bean.getLawOffice();
        if (lawOffice == null || TextUtils.isEmpty(lawOffice.getId())) {
            mLlLawOffice.setVisibility(View.GONE);
            mLlLawyer.setVisibility(View.GONE);
        } else {
            mTvLawyerOffice.setText(bean.getLawOffice().getName());
            initLawyerPersonPickerView();
        }
        LegalOffice legalOffice = bean.getLegalOffice();
        if (legalOffice == null || TextUtils.isEmpty(legalOffice.getId())) {
            mLlLegalOffice.setVisibility(View.GONE);
            mLlLegalPerson.setVisibility(View.GONE);
        } else {
            mTvLegalOffice.setText(legalOffice.getName());
            initLegalPersonPickerView();
        }

        setFromText();
    }

    private void initLawyerPersonPickerView() {
        Map<String, String> map = new HashMap<>(16);
        map.put("type", "1");
        map.put("id", bean.getLawOffice().getId());
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<List<InsUserBean>>>post(NetConstant.GET_LAWYER)
                .params(NetConstant.QUERY, queryStr)
                .execute(new JsonCallback<BaseBean<List<InsUserBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<InsUserBean>>> response) {
                        List<InsUserBean> officeList = response.body().getBody();
                        if (officeList != null && officeList.size() > 0) {
                            final List<InsUserBean> users = officeList.get(0).getUsers();
                            lawyerPersonPickerView = new PickerViewBuilder(mActivity,
                                    (options1, options2, options3, v) -> {
                                        InsUserBean user = users.get(options1);
                                        Lawyer lawyer = new Lawyer();
                                        lawyer.setId(user.getId());
                                        lawyer.setName(user.getName());
                                        mLegalAidWorkflow.setLawyer(lawyer);
                                        mTvLawyer.setText(user.getName());
                                    })
                                    .build();
                            lawyerPersonPickerView.setPicker(users);
                        } else {
                            ToastUtils.showLong("暂无数据");
                        }
                    }
                });
    }

    private void initLegalPersonPickerView() {
        Map<String, String> map = new HashMap<>(16);
        map.put("type", "1");
        map.put("id", bean.getLegalOffice().getId());
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<List<InsUserBean>>>post(NetConstant.GET_LEGAL_PERSON)
                .params(NetConstant.QUERY, queryStr)
                .execute(new JsonCallback<BaseBean<List<InsUserBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<InsUserBean>>> response) {
                        List<InsUserBean> officeList = response.body().getBody();
                        if (officeList != null && officeList.size() > 0) {
                            final List<InsUserBean> users = officeList.get(0).getUsers();
                            legalPersonView = new PickerViewBuilder(mActivity,
                                    (options1, options2, options3, v) -> {
                                        InsUserBean user = users.get(options1);
                                        LegalPerson legalPerson = new LegalPerson();
                                        legalPerson.setId(user.getId());
                                        legalPerson.setName(user.getName());
                                        mTvLegalPerson.setText(user.getName());
                                        mLegalAidWorkflow.setLegalPerson(legalPerson);
                                    })
                                    .build();
                            legalPersonView.setPicker(users);
                        } else {
                            ToastUtils.showLong("暂无数据");
                        }
                    }
                });
    }

    private void setFromText() {
        mTvAidLawyer.setText(bean.getLawAssistUser().getName());
        mTvLawyer.setText(bean.getLawyer().getName());
        mTvLegalPerson.setText(bean.getLegalPerson().getName());

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhu_ren;
    }
}

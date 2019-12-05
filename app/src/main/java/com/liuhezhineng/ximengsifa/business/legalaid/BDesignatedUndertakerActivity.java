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
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessDetailsWrapper;
import com.liuhezhineng.ximengsifa.bean.bussiness.InsUserBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.LegalAidData;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.LawOffice;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.Lawyer;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.LegalOffice;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.LegalPerson;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.business.base.BaseLegalAidActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.utils.PickerViewBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 指定承办人：申请人指定承办人， 1、可以不指定 2、可以指定：先选择相关承办机构，在选择旗下相关承办人员
 */
public class BDesignatedUndertakerActivity extends BaseLegalAidActivity {

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
    @BindView(R.id.tv_aid_lawyer)
    TextView mTvAidLawyer;
    @BindView(R.id.tv_lawyer)
    TextView mTvLawyer;
    @BindView(R.id.tv_legal_person)
    TextView mTvLegalPerson;
    @BindView(R.id.ll_aid_lawyer)
    LinearLayout mLlAidLawyer;
    @BindView(R.id.ll_lawyer)
    LinearLayout mLlLawyer;
    @BindView(R.id.ll_legal_person)
    LinearLayout mLlLegalPerson;
    @BindView(R.id.tv_aid_category)
    TextView mTvAidCategory;
    @BindView(R.id.tv_case_nature)
    TextView mTvCaseNature;
    @BindView(R.id.tv_aid_center)
    TextView mTvAidCenter;
    @BindView(R.id.tv_lawyer_office)
    TextView mTvLawyerOffice;
    @BindView(R.id.tv_legal_office)
    TextView mTvLegalOffice;
    @BindView(R.id.ll_legal_office)
    LinearLayout mLlLegalOffice;
    @BindView(R.id.ll_zhiding_chengbanren)
    LinearLayout mLlZhiDingChengBanRen;

    LegalAidData bean;
    BusinessDetailsWrapper wrapper;
    String pickerType;
    ArrayList<InsUserBean> aidCenterList = new ArrayList<>();
    OptionsPickerView<InsUserBean> aidCenterPicker;
    ArrayList<InsUserBean> aidLawyerList = new ArrayList<>();
    OptionsPickerView<InsUserBean> aidLawyerPicker;
    ArrayList<InsUserBean> lawOfficeList = new ArrayList<>();
    OptionsPickerView<InsUserBean> lawOfficePicker;
    ArrayList<InsUserBean> legalOfficeList = new ArrayList<>();
    OptionsPickerView<InsUserBean> legalOfficePicker;
    ArrayList<InsUserBean> lawyerList = new ArrayList<>();
    OptionsPickerView<InsUserBean> lawyerPicker;
    ArrayList<InsUserBean> legalPersonList = new ArrayList<>();
    OptionsPickerView<InsUserBean> legalPersonPicker;
    OnOptionsSelectListener listener = new OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            if (options1 < 0) {
                ToastUtils.showLong("数据加载中...");
                return;
            }
            switch (pickerType) {
                case Constant.Business.LEGAL_AID + Constant.Business.IS_INS:
                    aidCenterCallback(options1);
                    break;
                case Constant.Business.LEGAL_AID + Constant.Business.IS_USER:
                    aidLawyerCallback(options1);
                    break;
                case Constant.Business.APPLY_LAWYER + Constant.Business.IS_INS:
                    lawOfficeCallback(options1);
                    break;
                case Constant.Business.APPLY_LAWYER + Constant.Business.IS_USER:
                    lawyerCallback(options1);
                    break;
                case Constant.Business.LEGAL_SERVICE_OFFICE + Constant.Business.IS_INS:
                    legalOfficeCallback(options1);
                    break;
                case Constant.Business.LEGAL_SERVICE_OFFICE + Constant.Business.IS_USER:
                    legalPersonCallback(options1);
                    break;
                default:
                    break;
            }
        }
    };

    public static void actionStart(Context context, BusinessBean businessBean) {
        Intent intent = new Intent(context, BDesignatedUndertakerActivity.class);
        intent.putExtra(Constant.BUSINESS, businessBean);
        ((BaseActivity) context).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
    }

    @Override
    @OnClick({R.id.tv_legal_office,
            R.id.tv_lawyer_office,
            R.id.tv_aid_center,
            R.id.tv_lawyer,
            R.id.tv_legal_person,
            R.id.tv_aid_lawyer,
            R.id.btn_yes})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.tv_aid_center:
                pickerType = Constant.Business.LEGAL_AID + Constant.Business.IS_INS;
                showPickerView(aidCenterPicker);
                break;
            case R.id.tv_aid_lawyer:
                pickerType = Constant.Business.LEGAL_AID + Constant.Business.IS_USER;
                showPickerView(aidLawyerPicker);
                break;
            case R.id.tv_lawyer_office:
                pickerType = Constant.Business.APPLY_LAWYER + Constant.Business.IS_INS;
                showPickerView(lawOfficePicker);
                break;
            case R.id.tv_lawyer:
                pickerType = Constant.Business.APPLY_LAWYER + Constant.Business.IS_USER;
                showPickerView(lawyerPicker);
                break;
            case R.id.tv_legal_office:
                pickerType = Constant.Business.LEGAL_SERVICE_OFFICE + Constant.Business.IS_INS;
                showPickerView(legalOfficePicker);
                break;
            case R.id.tv_legal_person:
                pickerType = Constant.Business.LEGAL_SERVICE_OFFICE + Constant.Business.IS_USER;
                showPickerView(legalPersonPicker);
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
    protected int getLayoutId() {
        return R.layout.activity_zhi_ding_cheng_ban_ren;
    }

    @Override
    protected void showDetails(Response<BaseBean<BusinessDetailsWrapper<LegalAidData>>> response) {
        super.showDetails(response);
        wrapper = response.body().getBody();
        bean = (LegalAidData) wrapper.getBusinessData();

        // 通过地区预加载数据：法援中心、律所、基层法律服务所
        final Area area = bean.getArea();
        if (area != null && !TextUtils.isEmpty(area.getId())) {
            loadAidCenter(area);
            loadLawOffice(area);
            // 刑事案件不能由基层法律服务所处理，所以隐藏。
            if (!"刑事".contains(bean.getCaseTypeDesc())) {
                loadLegalOffice(area);
            } else {
                mLlLegalOffice.setVisibility(View.GONE);
            }
        }

        mLegalAidWorkflow.setLawOffice(bean.getLawOffice());
        mLegalAidWorkflow.setLegalOffice(bean.getLegalOffice());
        mLegalAidWorkflow.setLawyer(bean.getLawyer());
        mLegalAidWorkflow.setLegalPerson(bean.getLegalPerson());

        mTvAidCategory.setText(bean.getAidCategoryDesc());
        mTvCaseNature.setText(bean.getCaseNatureDesc());
        mEtName.setText(bean.getName());
        mEtSex.setText(bean.getSexDesc());
        mEtEthnic.setText(bean.getEthnicDesc());
        mEtBirthday.setText(bean.getBirthday());
        mEtIdCardNum.setText(bean.getIdCard());
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
    protected void initView() {
        super.initView();
        aidCenterPicker = new PickerViewBuilder(this, listener).build();
        aidLawyerPicker = new PickerViewBuilder(this, listener).build();
        lawOfficePicker = new PickerViewBuilder(this, listener).build();
        legalOfficePicker = new PickerViewBuilder(this, listener).build();
        lawyerPicker = new PickerViewBuilder(this, listener).build();
        legalPersonPicker = new PickerViewBuilder(this, listener).build();
    }

    private void loadAidCenter(final Area area) {
        initPicker(Constant.Business.LEGAL_AID, Constant.Business.IS_INS,
                "",
                area.getId(), "",
                aidCenterList, aidCenterPicker);
    }

    private void aidCenterCallback(int options1) {
        // 无数据的提示
        InsUserBean bean = aidCenterList.get(options1);
        if (Constant.PICKER_NO_DATA.equals(bean.getId())) {
            ToastUtils.showLong(bean.getName());
            return;
        }

        // 设置文本显示和表单数据
        mTvAidCenter.setText(bean.getName());
        Office lawOffice = new Office();
        lawOffice.setId(bean.getId());
        lawOffice.setName(bean.getName());
        mLegalAidWorkflow.setLawAssistanceOffice(lawOffice);

        // 控制相关视图的显示和隐藏
        mLlZhiDingChengBanRen.setVisibility(View.VISIBLE);
        mLlAidLawyer.setVisibility(View.VISIBLE);
        mLlLawyer.setVisibility(View.GONE);
        mLlLegalPerson.setVisibility(View.GONE);

        // 重置级联数据
        resetLawOffice();
        resetLegalOffice();
        resetAidLawyer();

        // 预加载下级数据
        loadAidLawyer(bean);
    }

    private void resetLawOffice() {
        mTvLawyerOffice.setText("");
        mLegalAidWorkflow.setLawOffice(new LawOffice());

        resetLawyer();
    }

    private void resetLegalOffice() {
        mTvLegalOffice.setText("");
        mLegalAidWorkflow.setLegalOffice(new LegalOffice());

        resetLegalPerson();
    }

    private void resetAidLawyer() {
        mTvAidLawyer.setText("");
        mLegalAidWorkflow.setLawAssistUser(new Office());
    }

    private void loadAidLawyer(InsUserBean bean) {
        aidLawyerList.clear();
        InsUserBean insUserBean = new InsUserBean();
        insUserBean.setId(Constant.PICKER_NO_DATA);
        insUserBean.setName("数据加载中，请稍等...");
        aidLawyerList.add(insUserBean);
        aidLawyerPicker.setPicker(aidLawyerList);
        Map<String, String> map = new HashMap<>(16);
        // 这里 type 为 1 就会去查用户，不然去查机构了
        // 1/12/15:36 之后不要了，后台这里定死
        map.put("type", "1");
        // 法援机构的 id
        map.put("id", bean.getId());
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<List<InsUserBean>>>post(NetConstant.GET_AID_LAWYER)
                .params(Constant.QUERY, queryStr)
                .execute(new JsonCallback<BaseBean<List<InsUserBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<InsUserBean>>> response) {
                        aidLawyerList.clear();
                        List<InsUserBean> list = response.body().getBody();
                        for (InsUserBean bean : list) {
                            aidLawyerList.addAll(bean.getUsers());
                        }
                        if (aidLawyerList.size() <= 0) {
                            InsUserBean insUserBean = new InsUserBean();
                            insUserBean.setId(Constant.PICKER_NO_DATA);
                            insUserBean.setName("该机构下暂无相关人员数据");
                            aidLawyerList.add(insUserBean);
                        }
                        aidLawyerPicker.setPicker(aidLawyerList);
                    }
                });
    }

    private void resetLawyer() {
        mTvLawyer.setText("");
        mLegalAidWorkflow.setLawyer(new Lawyer());
    }

    private void resetLegalPerson() {
        mTvLegalPerson.setText("");
        mLegalAidWorkflow.setLegalPerson(new LegalPerson());
    }

    private void aidLawyerCallback(int options1) {
        InsUserBean person = aidLawyerList.get(options1);
        if (Constant.PICKER_NO_DATA.equals(person.getId())) {
            ToastUtils.showLong(person.getName());
            return;
        }

        mTvAidLawyer.setText(person.getName());
        Office aidLawyer = new Office();
        aidLawyer.setId(person.getId());
        aidLawyer.setName(person.getName());
        mLegalAidWorkflow.setLawAssistUser(aidLawyer);

        resetLegalPerson();
        resetLawyer();
    }

    private void loadLawOffice(final Area area) {
        initPicker(Constant.Business.APPLY_LAWYER, Constant.Business.IS_INS,
                "",
                area.getId(), "",
                lawOfficeList, lawOfficePicker);
    }

    private void lawOfficeCallback(int options1) {
        InsUserBean person = lawOfficeList.get(options1);
        if (Constant.PICKER_NO_DATA.equals(person.getId())) {
            ToastUtils.showLong(person.getName());
            return;
        }
        InsUserBean bean = lawOfficeList.get(options1);
        LawOffice lawOffice = new LawOffice();
        lawOffice.setId(bean.getId());
        lawOffice.setName(bean.getName());

        mTvLawyerOffice.setText(bean.getName());
        mLegalAidWorkflow.setLawOffice(lawOffice);
        mLlZhiDingChengBanRen.setVisibility(View.VISIBLE);
        mLlLawyer.setVisibility(View.VISIBLE);
        mLlAidLawyer.setVisibility(View.GONE);
        mLlLegalPerson.setVisibility(View.GONE);

        resetLegalOffice();
        resetAidCenter();
        resetLawyer();

        loadLawyer(bean);
    }

    private void resetAidCenter() {
        mTvAidCenter.setText("");
        mLegalAidWorkflow.setLawAssistanceOffice(new Office());

        resetAidLawyer();
    }

    private void loadLawyer(InsUserBean bean) {
        initPicker(Constant.Business.APPLY_LAWYER, Constant.Business.IS_USER,
                bean.getId(),
                "", "",
                lawyerList, lawyerPicker);
    }

    void initPicker(String type, final String isUser, String insId,
                    String countId, String townId,
                    final ArrayList<InsUserBean> arrayList,
                    final OptionsPickerView<InsUserBean> picker) {
        arrayList.clear();
        InsUserBean insUserBean = new InsUserBean();
        insUserBean.setId(Constant.PICKER_NO_DATA);
        insUserBean.setName("数据加载中，请稍等...");
        arrayList.add(insUserBean);
        picker.setPicker(arrayList);
        initInsOrUserPicker(type, isUser, insId, countId, townId,
                new JsonCallback<BaseBean<List<InsUserBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<InsUserBean>>> response) {
                        arrayList.clear();
                        List<InsUserBean> list = response.body().getBody();
                        if (Constant.Business.IS_USER.equals(isUser)) {
                            for (InsUserBean bean : list) {
                                arrayList.addAll(bean.getUsers());

                            }
                        } else {
                            arrayList.addAll(list);
                        }
                        if (arrayList.size() <= 0) {
                            InsUserBean insUserBean = new InsUserBean();
                            insUserBean.setId(Constant.PICKER_NO_DATA);
                            if (Constant.Business.IS_USER.equals(isUser)) {
                                insUserBean.setName("该机构下暂无相关人员数据");
                            } else {
                                insUserBean.setName("该地区下暂无相关承办机构数据");
                            }
                            arrayList.add(insUserBean);
                        }
                        picker.setPicker(arrayList);
                    }
                });
    }

    /**
     * @param type
     * @param isUser   0: jigou, 1: jigou and renyuan
     * @param countyId
     * @param townId
     */
    void initInsOrUserPicker(String type, String isUser, String insId, String countyId, String townId
            , JsonCallback listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put("type", type);
        map.put("isUser", isUser);
        map.put("id", insId);
        map.put("areaId", countyId);
        map.put("townId", townId);
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<List<InsUserBean>>>post(NetConstant.FastLegal.GET_INS_OR_PERSON)
                .params(NetConstant.QUERY, queryStr)
//                .cacheKey(UserHelper.getToken() + queryStr)
//                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(listener);
    }

    private void lawyerCallback(int options1) {
        InsUserBean person = lawyerList.get(options1);
        if (Constant.PICKER_NO_DATA.equals(person.getId())) {
            ToastUtils.showLong(person.getName());
            return;
        }

        Lawyer lawyer = new Lawyer();
        lawyer.setId(person.getId());
        lawyer.setName(person.getName());
        mLegalAidWorkflow.setLawyer(lawyer);
        mTvLawyer.setText(person.getName());

        resetAidLawyer();
        resetLegalPerson();
    }

    private void loadLegalOffice(final Area area) {
        initPicker(Constant.Business.LEGAL_SERVICE_OFFICE, Constant.Business.IS_INS,
                "",
                area.getId(), "",
                legalOfficeList, legalOfficePicker);
    }

    private void legalOfficeCallback(int options1) {
        InsUserBean person = legalOfficeList.get(options1);
        if (Constant.PICKER_NO_DATA.equals(person.getId())) {
            ToastUtils.showLong(person.getName());
            return;
        }

        InsUserBean bean = legalOfficeList.get(options1);
        LegalOffice legalOffice = new LegalOffice();
        legalOffice.setId(bean.getId());
        legalOffice.setName(bean.getName());
        mTvLegalOffice.setText(bean.getName());
        mLegalAidWorkflow.setLegalOffice(legalOffice);

        mLlZhiDingChengBanRen.setVisibility(View.VISIBLE);
        mLlLegalPerson.setVisibility(View.VISIBLE);
        mLlLawyer.setVisibility(View.GONE);
        mLlAidLawyer.setVisibility(View.GONE);

        resetLawOffice();
        resetAidCenter();
        resetLegalPerson();

        loadLegalPerson(bean);
    }

    private void loadLegalPerson(InsUserBean bean) {
        initPicker(Constant.Business.LEGAL_SERVICE_OFFICE, Constant.Business.IS_USER,
                bean.getId(),
                "", "",
                legalPersonList, legalPersonPicker);
    }

    private void legalPersonCallback(int options1) {
        InsUserBean person = legalPersonList.get(options1);
        if (Constant.PICKER_NO_DATA.equals(person.getId())) {
            ToastUtils.showLong(person.getName());
            return;
        }
        LegalPerson legalPerson = new LegalPerson();
        legalPerson.setId(person.getId());
        legalPerson.setName(person.getName());

        mTvLegalPerson.setText(person.getName());
        mLegalAidWorkflow.setLegalPerson(legalPerson);

        resetLawyer();
        resetAidLawyer();
    }
}

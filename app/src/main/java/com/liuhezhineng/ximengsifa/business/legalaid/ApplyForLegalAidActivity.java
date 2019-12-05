package com.liuhezhineng.ximengsifa.business.legalaid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.DraftAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.RequestLawHelpBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.Town;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.business.base.BaseBusinessActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.MyDraftActivity;
import com.liuhezhineng.ximengsifa.callback.LogUtils;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.utils.IDCardInfoExtractor;
import com.liuhezhineng.ximengsifa.utils.IDCardValidator;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 申请法律援助
 */
public class ApplyForLegalAidActivity extends BaseBusinessActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.et_agent_name)
    EditText mEtAgentName;
    @BindView(R.id.tv_sex)
    TextView mEtSex;
    @BindView(R.id.tv_ethnic)
    TextView mEtEthnic;
    @BindView(R.id.tv_birthday)
    TextView mEtBirthday;
    @BindView(R.id.et_id_card_num)
    EditText mEtIdCard;
    @BindView(R.id.et_area)
    TextView mEtArea;
    @BindView(R.id.et_work_address)
    EditText mEtWorkAddress;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.et_home_address)
    EditText mEtHomeAddress;
    @BindView(R.id.et_phone)
    EditText mEtPhoneNum;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_agent_id_card_num)
    EditText mEtAgentIdCardNum;
    @BindView(R.id.et_agent_person)
    TextView mEtAgentType;
    @BindView(R.id.et_title)
    EditText mEtTitle;
    @BindView(R.id.et_type)
    TextView mEtCaseType;
    @BindView(R.id.tv_aid_category)
    TextView mTvAidCategory;
    @BindView(R.id.tv_case_nature)
    TextView mTvCaseNature;
    @BindView(R.id.et_line_mengyu)
    TextView mEtLineMengyu;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.ll_proxy)
    LinearLayout mLlProxy;
    @BindView(R.id.ll_sex_and_birthday)
    LinearLayout mLlSexAndBirthday;

    RequestLawHelpBean requestLawHelpBean = new RequestLawHelpBean();
    @BindView(R.id.et_township)
    TextView etTownship;
    @BindView(R.id.ll_township)
    LinearLayout llTownship;
    @BindView(R.id.tv_file)
    TextView tvFile;
    @BindView(R.id.rv_annex)
    RecyclerView rvAnnex;
    @BindView(R.id.ll_annex)
    LinearLayout llAnnex;
    @BindView(R.id.btn_no)
    Button btnNo;
    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.ll_btn)
    LinearLayout llBtn;

    //仅代表草稿箱的id，第一次保存时不用提交，修改草稿必须有值，否则按新增处理。
    //正式提交时必须有值，会根据此id删除草稿箱内容
    private String id;
    //是否提交，为0保存到草稿，非0位正式提交内容
    private String isSubmit;
    private String name;
    private String sex;
    private String birthday;
    private Area area = new Area();
    private Town town = new Town();
    private String ethnic;
    private String idCard;
    private String employer;
    private String address;
    private String domicile;
    private String phone;
    private String proxyName;

    private String proxyIdCard;
    private String proxyType;
    private String caseTitle;

    private String caseType;
    private String aidCategory;
    private String caseNature;
    private String hasMongol;
    private String caseReason;

    public static void actionStart(Context context) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, ApplyForLegalAidActivity.class);
            context.startActivity(intent);
        } else {
            gotoLogin(context);
        }
    }

    /**
     * 服务入口
     *
     * @param userType 是申请人还是代理人
     */
    public static void actionStart(Context context, int userType) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, ApplyForLegalAidActivity.class);
            intent.putExtra("userType", userType);
            context.startActivity(intent);
        } else {
            gotoLogin(context);
        }
    }

    /**
     * 草稿入口，需要回调刷新
     */
    public static void actionStart(Context context, String id, String proDefKey) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, ApplyForLegalAidActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("procDefKey", proDefKey);
            ((BaseActivity) context).startActivityForResult(intent, DraftAdapter.DEL_DRAFT);
        } else {
            gotoLogin(context);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_for_legal_aid;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(mTopBar, R.string.request_law_help);
        findViewById(R.id.tv_file).setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        initPickerView();
    }

    private void initPickerView() {

        initSexPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mEtSex.setText(sexList.get(options1));
                sex = getSex(sexList.get(options1));
            }
        });
        initEthnicPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mEtEthnic.setText(ethnicList.get(options1).getLabel());
                ethnic = ethnicList.get(options1).getValue();
            }
        });
        initBirthdayPicker(new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                birthday = getTime(date);
                mEtBirthday.setText(birthday);
            }
        });
        requestLawHelpBean.setArea(area);
        initCountyPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Area cityBean = countyList.get(options1);
                mEtArea.setText(cityBean.getName());
                area.setId(cityBean.getId());
                area.setName(cityBean.getName());
                LogUtils.logi("0", cityBean.getName());
                LogUtils.logi("0", cityBean.getId());
                initTownPickerView(new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        etTownship.setText(townList.get(options1).getName());
                        town.setId(townList.get(options1).getId());
                        town.setName(townList.get(options1).getName());
                        requestLawHelpBean.setTown(town);
                    }
                }, cityBean.getId());
            }
        });
        initAgentTypePickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                proxyType = agentTypeList.get(options1).getValue();
                mEtAgentType.setText(agentTypeList.get(options1).getLabel());
            }
        });
        initMongolPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mEtLineMengyu.setText(mongolList.get(options1));
                hasMongol = hasMongolian(mongolList.get(options1));
            }
        });
        initCaseTypePicker(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                caseType = caseTypeList.get(options1).getValue();
                mEtCaseType.setText(caseTypeList.get(options1).getLabel());
            }
        });
        initAidCategoryPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                aidCategory = aidCategoryList.get(options1).getValue();
                mTvAidCategory.setText(aidCategoryList.get(options1).getLabel());
            }
        });
        initCaseNatureView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                caseNature = caseNatureList.get(options1).getValue();
                mTvCaseNature.setText(caseNatureList.get(options1).getLabel());
            }
        });
    }

    @Override
    protected void loadDraft(String id, String procDefKey) {
        Map<String, String> map = new HashMap<>(16);
        map.put("id", id);
        map.put("procDefKey", procDefKey);
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<PageBean<RequestLawHelpBean>>>post(NetConstant.GET_DRAFT_DATA)
                .params(NetConstant.QUERY, queryStr)
                .execute(new DialogCallBack<BaseBean<PageBean<RequestLawHelpBean>>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<RequestLawHelpBean>>> response) {
                        RequestLawHelpBean bean = response.body().getBody().getList().get(0);
//                        showDraftData(bean);
                    }
                });
    }

    @Override
    protected void loadUserInfo() {
        int userType = getIntent().getIntExtra("userType", -1);
        UserBean user = UserHelper.getUser();

        LogUtils.logi("","asd="+new Gson().toJson(user).toString());
        if (Constant.REQUEST_USER == userType) {
            mLlProxy.setVisibility(View.GONE);
            mEtName.setText(user.getRealName());
            mEtName.setEnabled(false);
            mEtSex.setText(user.getSexDesc());
            sex = user.getSex();
            // 个人信息里面加个民族
//			mEtEthnic.setText(user.);
            mEtBirthday.setText(user.getBirthday());
            mEtIdCard.setText(user.getPaperNum());
            mEtIdCard.setEnabled(false);
            area = user.getCounty();
            if (area != null) {
//                mEtArea.setText(area.getName());
            }
            mEtPhoneNum.setText(user.getMobile());
        } else if (Constant.AGENT_USER == userType) {

            LogUtils.logi("asdasd","asd="+new Gson().toJson(user).toString());
            mEtAgentName.setText(user.getRealName());
            mEtAgentName.setEnabled(false);
            mEtAgentIdCardNum.setText(user.getPaperNum());
            mEtAgentIdCardNum.setEnabled(false);
        }else  if (100==userType){
            mEtArea.setText(user.getTown().getName());
        }
    }

    @Override
    protected void setListener() {
        super.setListener();

        if (mEtIdCard.getText().toString().trim().length() == 18) {
            getSexAndBirthdayByIdCard();
        }

        mEtIdCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() == 18 && new IDCardValidator()
                        .isValidatedAllIdCard(s.toString().trim())) {
                    getSexAndBirthdayByIdCard();

                    mLlSexAndBirthday.setVisibility(View.VISIBLE);
                } else {
                    mLlSexAndBirthday.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getSexAndBirthdayByIdCard() {
        String idCard = mEtIdCard.getText().toString().trim();
        ApplyForLegalAidActivity.this.idCard = idCard;
        requestLawHelpBean.setIdCard(idCard);

        IDCardInfoExtractor idCardInfo = new IDCardInfoExtractor(
                idCard);
        String month = idCardInfo.getMonth() + "";
        if (idCardInfo.getMonth() < 10) {
            month = "0" + month;
        }
        String day = idCardInfo.getDay() + "";
        if (idCardInfo.getDay() < 10) {
            day = "0" + day;
        }

        String birthday =
                idCardInfo.getYear() + "-" + month + "-" + day;
        mEtBirthday.setText(birthday);
        ApplyForLegalAidActivity.this.birthday = birthday;
        requestLawHelpBean.setBirthday(birthday);

        String gender = idCardInfo.getGender();
        mEtSex.setText(gender);
        ApplyForLegalAidActivity.this.sex = getSex(gender);
        requestLawHelpBean.setSex(getSex(gender));
    }

    @Override
    @OnClick({R.id.tv_sex, R.id.tv_ethnic, R.id.et_area, R.id.tv_birthday,
            R.id.et_agent_person, R.id.et_type, R.id.tv_aid_category, R.id.tv_case_nature,
            R.id.et_line_mengyu, R.id.et_township,
            R.id.btn_yes, R.id.btn_no})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.tv_aid_category:
                showPickerView(aidCategoryPickerView);
                break;
            case R.id.tv_case_nature:
                showPickerView(caseNaturePickerView);
                break;
            case R.id.btn_no:
                isSubmit = "0";
                createForm();
                if (TextUtils.isEmpty(caseType)) {
                    ToastUtils.showLong("案件类型不能为空");
                    return;
                }
                commitRequest();
                break;
            case R.id.btn_yes:
                isSubmit = "1";
                createForm();
                if (checkInput()) {
                    commitRequest();
                }


                break;
            case R.id.tv_ethnic:
                showPickerView(ethnicPickerView);
                break;
            case R.id.et_area:
                showPickerView(countyPickerView);
                break;
            case R.id.et_agent_person:
                showPickerView(agentTypePickerView);
                break;
            case R.id.et_type:
                showPickerView(caseTypePicker);
                break;
            case R.id.et_line_mengyu:
                showPickerView(mongolPickerView);
                break;

            case R.id.et_township:
                if (TextUtils.isEmpty(mEtArea.getText().toString().trim())) {
                    ToastUtils.showLong("请先选择所属地区");
                } else {
                    showPickerView(townPickerView);
                }
                break;
//			case R.id.tv_sex:
//				showPickerView(sexPickerView);
//				break;
//			case R.id.tv_birthday:
//				birthdayPickerView.show();
//				break;
            default:
                break;
        }
    }

    private String township;

    private void createForm() {
        name = getText(mEtName);
        birthday = getText(mEtBirthday);
        idCard = getText(mEtIdCard);
        employer = getText(mEtWorkAddress);
        address = getText(mEtAddress);
        domicile = getText(mEtHomeAddress);
        phone = getText(mEtPhoneNum);

        proxyName = getText(mEtAgentName);
        proxyIdCard = getText(mEtAgentIdCardNum);

        caseTitle = getText(mEtTitle);
        caseReason = getText(mEtContent);

        township = getText(etTownship);
    }

    protected void commitRequest() {
        String loadingText = "";
        if ("0".equals(isSubmit)) {
            loadingText = "保存中...";
        } else {
            loadingText = "提交中...";
        }
        OkGo.<BaseBean<String>>post(NetConstant.REQUEST_LAW_HELP)
                .params(NetConstant.QUERY, getQueryJson())
                .execute(new DialogCallBack<BaseBean<String>>(mActivity, loadingText) {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        if ("0".equals(isSubmit)) {
                            ToastUtils.showLong("保存成功");
                            MyDraftActivity.actionStart(mActivity);
                        } else {
                            ToastUtils.showLong("提交成功");
                            commitResult();
                        }
                        finishActivity();
                    }
                });
    }

    private boolean checkInput() {

        if (!TextUtils.isEmpty(proxyName)) {
            if (TextUtils.isEmpty(proxyType)) {
                ToastUtils.showLong("代理人类型不能为空");
                return false;
            }
        }
        if (TextUtils.isEmpty(name)) {
            ToastUtils.showLong("用户名不能为空");
            return false;
        } else if (TextUtils.isEmpty(township)) {
            ToastUtils.showLong("案件乡镇不能为空");
            return false;
        } else if (TextUtils.isEmpty(sex)) {
            ToastUtils.showLong("性别不能为空");
            return false;
        } else if (TextUtils.isEmpty(ethnic)) {
            ToastUtils.showLong("民族不能为空");
            return false;
        } else if (TextUtils.isEmpty(birthday)) {
            ToastUtils.showLong("出生日期不能为空");
            return false;
        } else if (TextUtils.isEmpty(idCard)) {
            ToastUtils.showLong("身份证不能为空");
            return false;
        } else if (area == null || TextUtils.isEmpty(area.getName())) {
            ToastUtils.showLong("所属地区不能为空");
            return false;
        } else if (TextUtils.isEmpty(phone)) {
            ToastUtils.showLong("手机号码不能为空");
            return false;
        } else if (TextUtils.isEmpty(caseType)) {
            ToastUtils.showLong("案件类型不能为空");
            return false;
        } else if (isEmpty(aidCategory)) {
            ToastUtils.showLong("案件所属分类不能为空");
            return false;
        } else if (isEmpty(caseNature)) {
            ToastUtils.showLong("案件性质不能为空");
            return false;
        } else if (TextUtils.isEmpty(hasMongol)) {
            ToastUtils.showLong("涉及蒙语不能为空");
            return false;
        } else if (TextUtils.isEmpty(caseReason)) {
            ToastUtils.showLong("案情及申请理由不能为空");
            return false;
        } else if ((!name.equals(UserHelper.getUser().getRealName()))
                && (!proxyName.equals(UserHelper.getUser().getRealName()))) {
            ToastUtils.showLong("申请人或代理人其中一个的姓名和身份证必须和登录人一致");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 性别，民族等字典值传 value 而不是 label
     */
    private String getQueryJson() {
        requestLawHelpBean.setName(name); //申请人姓名
        requestLawHelpBean.setEthnic(ethnic); ///民族
        requestLawHelpBean.setId("");       //类型
        requestLawHelpBean.setSex(sex); //xingbie
        requestLawHelpBean.setIdCard(idCard);
        requestLawHelpBean.setIsSubmit(isSubmit);
        requestLawHelpBean.setBirthday(birthday);
        requestLawHelpBean.setArea(area);

        requestLawHelpBean.setEmployer(employer);
        requestLawHelpBean.setAddress(address);
        requestLawHelpBean.setDomicile(domicile);
        requestLawHelpBean.setPhone(phone);
        requestLawHelpBean.setProxyName(proxyName);
        requestLawHelpBean.setProxyIdCard(proxyIdCard);
        requestLawHelpBean.setProxyType(proxyType);
        requestLawHelpBean.setCaseTitle(caseTitle);
        requestLawHelpBean.setCaseType(caseType);
        requestLawHelpBean.setAidCategory(aidCategory);
        requestLawHelpBean.setCaseNature(caseNature);
        requestLawHelpBean.setCaseFile(caseFile);
        requestLawHelpBean.setHasMongol(hasMongol);
        requestLawHelpBean.setCaseReason(caseReason);
        String s = new Gson().toJson(requestLawHelpBean);
        LogUtils.logi("0", s);
        return new Gson().toJson(requestLawHelpBean);
    }

    private void showDraftData(RequestLawHelpBean bean) {
        sex = bean.getSex();
        ethnic = bean.getEthnic();
        area = bean.getArea();
        proxyType = bean.getProxyType();
        caseType = bean.getCaseType();
        aidCategory = bean.getAidCategory();
        caseNature = bean.getCaseNature();
        hasMongol = bean.getHasMongol();
        caseFile = bean.getCaseFile();

        id = bean.getId();

        if (TextUtils.isEmpty(bean.getProxyName())) {
            mLlProxy.setVisibility(View.GONE);
            mEtName.setEnabled(false);
            mEtIdCard.setEnabled(false);
        } else {
            mEtAgentName.setEnabled(false);
            mEtAgentIdCardNum.setEnabled(false);
        }

        mEtName.setText(bean.getName());
        mEtSex.setText(bean.getSexDesc());
        mEtEthnic.setText(bean.getEthnicDesc());
        mEtBirthday.setText(bean.getBirthday());
        mEtIdCard.setText(bean.getIdCard());
        if (area != null) {
            mEtArea.setText(area.getName());
        }
        mEtWorkAddress.setText(bean.getEmployer());
        mEtAddress.setText(bean.getAddress());
        mEtHomeAddress.setText(bean.getDomicile());
        mEtPhoneNum.setText(bean.getPhone());

        mEtAgentName.setText(bean.getProxyName());
        mEtAgentIdCardNum.setText(bean.getProxyIdCard());
        mEtAgentType.setText(bean.getProxyTypeDesc());

        mEtTitle.setText(bean.getCaseTitle());
        mEtCaseType.setText(bean.getCaseTypeDesc());
        mTvAidCategory.setText(bean.getAidCategoryDesc());
        mTvCaseNature.setText(bean.getCaseNatureDesc());
        mEtLineMengyu.setText(bean.getHasMongolDesc());
        mEtContent.setText(bean.getCaseReason());

        setCaseFile(bean.getCaseFile());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

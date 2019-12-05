package com.liuhezhineng.ximengsifa.business.peoplesmediation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
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
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.InsUserBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Committee;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.PeoplesMediationData;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.business.base.BaseBusinessActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.MyDraftActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.Business;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.DialogStringCallBack;
import com.liuhezhineng.ximengsifa.utils.IDCardInfoExtractor;
import com.liuhezhineng.ximengsifa.utils.IDCardValidator;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 申请人民调解
 * 填表
 * 校验
 * 提交
 */
public class ApplyForPeoplesMediationActivity extends BaseBusinessActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.et_name)
    EditText mTvName;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.tv_ethnic)
    TextView mTvEthnic;
    @BindView(R.id.tv_county)
    TextView mTvCounty;
    @BindView(R.id.tv_town)
    TextView mTvTown;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;
    @BindView(R.id.et_id_card_num)
    EditText mEtIdCard;
    @BindView(R.id.et_occupation)
    TextView mEtOccupation;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.et_domicile)
    EditText mEtDomicile;
    @BindView(R.id.et_name_1)
    EditText mEtName1;
    @BindView(R.id.tv_sex_1)
    TextView mTvSex1;
    @BindView(R.id.tv_ethnic_1)
    TextView mTvEthnic1;
    @BindView(R.id.tv_county_1)
    TextView mTvCounty1;
    @BindView(R.id.tv_town_1)
    TextView mTvTown1;
    @BindView(R.id.et_phone_num_1)
    EditText mEtPhoneNum1;
    @BindView(R.id.tv_birthday_1)
    TextView mTvBirthday1;
    @BindView(R.id.et_id_card_num_1)
    EditText mEtIdCardNum1;
    @BindView(R.id.et_occupation_1)
    TextView mEtOccupation1;
    @BindView(R.id.et_address_1)
    EditText mEtAddress1;
    @BindView(R.id.et_domicile_1)
    EditText mEtDomicile1;
    @BindView(R.id.et_case_title)
    EditText mEtCaseTitle;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.tv_peoples_mediator)
    TextView mTvPeoplesMediator;
    @BindView(R.id.tv_peoples_mediation_committee)
    TextView mTvPeoplesMediationCommittee;
    @BindView(R.id.tv_dispute_type)
    TextView mTvDisputeType;
    @BindView(R.id.tv_case_county)
    TextView mTvCaseCounty;
    @BindView(R.id.tv_case_town)
    TextView mTvCaseTown;
    @BindView(R.id.et_agent_name)
    EditText mEtAgentName;
    @BindView(R.id.et_agent_id_card)
    EditText mEtAgentIdCard;
    @BindView(R.id.tv_agent_type)
    TextView mTvAgentType;
    @BindView(R.id.ll_proxy)
    LinearLayout mLlProxy;

    PeoplesMediationData mBean;
    String areaFlag = Constant.ACCUSER;

    private String id = "";
    private String accuserName;
    private String accuserIdCard;
    private String accuserSex;
    private String accuserBirthday;
    private String accuserEthnic;
    private Area accuserCounty = new Area();
    private Area accuserTown = new Area();
    private String accuserOccupation;
    private String accuserDomicile;
    private String accuserAddress;
    private String accuserPhone;
    private String defendantName;
    private String defendantIdCard;
    private String defendantSex;
    private String defendantBirthday;
    private String defendantEthnic;
    private Area defendantCounty = new Area();
    private Area defendantTown = new Area();
    private String defendantOccupation;
    private String defendantPhone;
    private String defendantDomicile;
    private String defendantAddress;

    private String caseTitle;
    private String caseContent;
    private String caseType;
    private String caseTypeDesc;
    private Area caseCounty = new Area();
    private Area caseTown = new Area();

    private Office mMediator = new Office();
    private Office mCommittee = new Office();

    public static void actionStart(Context context) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, ApplyForPeoplesMediationActivity.class);
            context.startActivity(intent);
        } else {
            gotoLogin(context);
        }
    }

    public static void actionStart(Context context, String id, String procDefKey) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, ApplyForPeoplesMediationActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("procDefKey", procDefKey);
            ((BaseActivity) context).startActivityForResult(intent, DraftAdapter.DEL_DRAFT);
        } else {
            gotoLogin(context);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_for_peoples_mediation;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(mTopBar, "人民调解");
    }

    @Override
    protected void initData() {
        super.initData();
        mBean = new PeoplesMediationData();

        initPickerView();
    }

    Dialog mAgentDialog;

    void showAgentDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_legal_aid_door, null, false);
        ((QMUITopBar) dialogView.findViewById(R.id.top_bar)).setTitle(R.string.peoples_mediation_door);
        dialogView.findViewById(R.id.tv_request_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserInfo();
                mLlProxy.setVisibility(View.GONE);
                mAgentDialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.tv_proxy_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean user = UserHelper.getUser();
                mEtAgentName.setText(user.getRealName());
                mEtAgentIdCard.setText(user.getPaperNum());
                mTvAgentType.setText("工作人员");
                mBean.setProxyName(user.getRealName());
                mBean.setProxyIdCard(user.getPaperNum());
                mBean.setProxyType("1");
                mAgentDialog.dismiss();
                mEtAgentName.setEnabled(false);
                mEtAgentIdCard.setEnabled(false);
            }
        });
        mAgentDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();
        mAgentDialog.setCanceledOnTouchOutside(false);
        mAgentDialog.show();
    }

    private void setUserInfo() {
        UserBean user = UserHelper.getUser();
        mTvName.setText(user.getRealName());
        accuserName = user.getRealName();

        mTvSex.setText(user.getSexDesc());
        accuserSex = user.getSex();

        mEtPhone.setText(user.getMobile());
        accuserPhone = user.getMobile();

        if (user.getCounty() != null) {
            mTvCounty.setText(user.getCounty().getName());
            accuserCounty = user.getCounty();
            if (accuserCounty != null && !TextUtils.isEmpty(accuserCounty.getId())) {
                areaFlag = Constant.ACCUSER;
                loadTownData(accuserCounty.getId());
            }
        }

        if (user.getTown() != null) {
            mTvTown.setText(user.getTown().getName());
            accuserTown = user.getTown();
        }

        mTvBirthday.setText(user.getBirthday());
        accuserBirthday = user.getBirthday();

        mEtIdCard.setText(user.getPaperNum());
        mEtIdCard.setEnabled(false);
        accuserIdCard = user.getPaperNum();

        mEtOccupation.setText(user.getUserSourceTypeDesc());
        accuserOccupation = user.getUserSourceType();
    }

    @Override
    protected void loadDraft(String id, String procDefKey) {
        Map<String, String> map = new HashMap<>(16);
        map.put("id", id);
        map.put("procDefKey", procDefKey);
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<PageBean<PeoplesMediationData>>>post(NetConstant.GET_DRAFT_DATA)
                .params(NetConstant.QUERY, queryStr)
                .execute(new DialogCallBack<BaseBean<PageBean<PeoplesMediationData>>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<PeoplesMediationData>>> response) {
                        List<PeoplesMediationData> list = response.body().getBody().getList();
                        if (list != null && list.size() > 0) {
                            PeoplesMediationData bean = list.get(0);
                            showDraftData(bean);
                        }
                    }
                });
    }

    @Override
    protected void loadUserInfo() {
        showAgentDialog();
    }

    @Override
    protected void setListener() {
        super.setListener();

        if (mEtIdCard.getText().toString().trim().length() == 18) {
            getAccuserSexAndBirthdayByIdCard();
        }

        mEtIdCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 18) {
                    getAccuserSexAndBirthdayByIdCard();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEtIdCardNum1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 18 && new IDCardValidator()
                        .isValidatedAllIdCard(s.toString().trim())) {
                    String idCard = mEtIdCardNum1.getText().toString().trim();
                    ApplyForPeoplesMediationActivity.this.defendantIdCard = idCard;
                    mBean.setDefendantIdcard(idCard);

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

                    if (idCardInfo.getYear() > 1900) {
                        String birthday =
                                idCardInfo.getYear() + "-" + month + "-" + day;
                        mTvBirthday1.setText(birthday);
                        ApplyForPeoplesMediationActivity.this.defendantBirthday = birthday;
                        mBean.setDefendantBirthday(birthday);
                    }

                    String gender = idCardInfo.getGender();
                    mTvSex1.setText(gender);
                    ApplyForPeoplesMediationActivity.this.defendantSex = getSex(gender);
                    mBean.setDefendantSex(getSex(gender));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getAccuserSexAndBirthdayByIdCard() {
        String idCard = mEtIdCard.getText().toString().trim();
        ApplyForPeoplesMediationActivity.this.accuserIdCard = idCard;
        mBean.setAccuserIdcard(idCard);

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

        if (idCardInfo.getYear() > 1900) {
            String birthday =
                    idCardInfo.getYear() + "-" + month + "-" + day;
            mTvBirthday.setText(birthday);
            ApplyForPeoplesMediationActivity.this.accuserBirthday = birthday;
            mBean.setAccuserBirthday(birthday);
        }

        String gender = idCardInfo.getGender();
        mTvSex.setText(gender);
        ApplyForPeoplesMediationActivity.this.accuserSex = getSex(gender);
        mBean.setAccuserSex(getSex(gender));
    }

    @Override
    @OnClick({R.id.tv_sex, R.id.tv_ethnic, R.id.tv_county, R.id.tv_birthday, R.id.tv_town,
            R.id.tv_sex_1, R.id.tv_ethnic_1, R.id.tv_county_1, R.id.tv_birthday_1, R.id.tv_town_1,
            R.id.et_occupation, R.id.et_occupation_1,
            R.id.btn_no, R.id.btn_yes,
            R.id.tv_peoples_mediator, R.id.tv_peoples_mediation_committee,
            R.id.tv_dispute_type,
            R.id.tv_case_county, R.id.tv_case_town/*,
    R.id.tv_agent_type*/})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
//            case R.id.tv_agent_type:
//                showPickerView(agentTypePickerView);
//                break;
            case R.id.et_occupation:
                flag = Constant.ACCUSER;
                showPickerView(occupationPickerView);
                break;
            case R.id.et_occupation_1:
                flag = Constant.DEFENDANT;
                showPickerView(occupationPickerView);
                break;
            case R.id.tv_case_county:
                areaFlag = Constant.CASE;
                showPickerView(countyPickerView);
                break;
            case R.id.tv_case_town:
                areaFlag = Constant.CASE;
                if (!TextUtils.isEmpty(caseCounty.getId())) {
                    showPickerView(townPickerView);
                } else {
                    ToastUtils.showLong("请先选择旗县");
                }
                break;
            case R.id.tv_dispute_type:
                showPickerView(disputeTypePickerView);
                break;
            case R.id.tv_ethnic:
                areaFlag = Constant.ACCUSER;
                showPickerView(ethnicPickerView);
                break;
            case R.id.tv_county:
                areaFlag = Constant.ACCUSER;
                showPickerView(countyPickerView);
                break;
            case R.id.tv_town:
                areaFlag = Constant.ACCUSER;
                if (!TextUtils.isEmpty(accuserCounty.getId())) {
                    showPickerView(townPickerView);
                } else {
                    ToastUtils.showLong("请先选择旗县");
                }
                break;
            case R.id.tv_ethnic_1:
                areaFlag = Constant.DEFENDANT;
                showPickerView(ethnicPickerView);
                break;
            case R.id.tv_county_1:
                areaFlag = Constant.DEFENDANT;
                showPickerView(countyPickerView);
                break;
            case R.id.tv_town_1:
                areaFlag = Constant.DEFENDANT;
                if (!TextUtils.isEmpty(defendantCounty.getId())) {
                    showPickerView(townPickerView);
                } else {
                    ToastUtils.showLong("请先选择旗县");
                }
                break;
            case R.id.tv_peoples_mediation_committee:
                if (!TextUtils.isEmpty(caseTown.getId())) {
                    showPickerView(committeePickerView);
                } else {
                    ToastUtils.showLong("请先选择案发乡镇");
                    mTvCaseTown.requestFocus();
                    mTvCaseTown.setError("请先选择案发乡镇");
                }
                break;
            case R.id.tv_peoples_mediator:
                if (!TextUtils.isEmpty(mCommittee.getId())) {
                    showPickerView(peoplePicker);
                } else {
                    ToastUtils.showLong("请先选择人民调解委员会");
                    mTvPeoplesMediationCommittee.requestFocus();
                    mTvPeoplesMediationCommittee.setError("请先选择人民调解委员会");
                }
                break;
            case R.id.btn_no:
                createForm();
                saveForm(NetConstant.SAVE_MEDIATION_REQUEST, getQueryJson());
                break;
            case R.id.btn_yes:
                createForm();
                if (checkInput()) {
                    commitRequest(NetConstant.APPLY_FOR_PEOPLES_MEDIATION, getQueryJson(), "yes");
                }
                break;
			case R.id.tv_sex:
				areaFlag = Constant.ACCUSER;
				showPickerView(sexPickerView);
				break;
			case R.id.tv_birthday:
				areaFlag = Constant.ACCUSER;
				birthdayPickerView.show();
				break;
			case R.id.tv_sex_1:
				areaFlag = Constant.DEFENDANT;
				showPickerView(sexPickerView);
				break;
			case R.id.tv_birthday_1:
				areaFlag = Constant.DEFENDANT;
				birthdayPickerView.show();
				break;
            default:
                break;
        }
    }

    private void createForm() {
        accuserName = getText(mTvName);
        defendantName = getText(mEtName1);
        accuserSex = getSex(getText(mTvSex));
        defendantSex = getSex(getText(mTvSex1));
        accuserPhone = getText(mEtPhone);
        defendantPhone = getText(mEtPhoneNum1);
        accuserIdCard = getText(mEtIdCard);
        defendantIdCard = getText(mEtIdCardNum1);
        accuserOccupation = getText(mEtOccupation);
        defendantOccupation = getText(mEtOccupation1);
        accuserAddress = getText(mEtAddress);
        defendantAddress = getText(mEtAddress1);
        accuserDomicile = getText(mEtDomicile);
        defendantDomicile = getText(mEtDomicile1);

        caseTitle = getText(mEtCaseTitle);
        caseTypeDesc = getText(mTvDisputeType);
        caseContent = getText(mEtContent);
    }

    protected void saveForm(String url, String queryStr) {
        OkGo.<String>post(url)
                .params(NetConstant.QUERY, queryStr)
                .execute(new DialogStringCallBack(mActivity, "保存中...") {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ToastUtils.showLong("保存成功");
                        MyDraftActivity.actionStart(mActivity);
                        finishActivity();
                    }
                });
    }

    private String getQueryJson() {
        mBean.setId(id);

        mBean.setAccuserName(accuserName);
        mBean.setDefendantName(defendantName);
        mBean.setAccuserSex(accuserSex);
        mBean.setDefendantSex(defendantSex);
        mBean.setAccuserEthnic(accuserEthnic);
        mBean.setDefendantEthnic(defendantEthnic);
        mBean.setAccuserCounty(accuserCounty);
        mBean.setDefendantCounty(defendantCounty);
        mBean.setAccuserTown(accuserTown);
        mBean.setDefendantTown(defendantTown);
        mBean.setAccuserPhone(accuserPhone);
        mBean.setDefendantPhone(defendantPhone);
        mBean.setAccuserBirthday(accuserBirthday);
        mBean.setDefendantBirthday(defendantBirthday);
        mBean.setAccuserIdcard(accuserIdCard);
        mBean.setDefendantIdcard(defendantIdCard);
        mBean.setAccuserOccupation(accuserOccupation);
        mBean.setDefendantOccupation(defendantOccupation);
        mBean.setAccuserDomicile(accuserDomicile);
        mBean.setDefendantDomicile(defendantDomicile);
        mBean.setAccuserAddress(accuserAddress);
        mBean.setDefendantAddress(defendantAddress);

        mBean.setCaseTitle(caseTitle);
        mBean.setCaseType(caseType);
        mBean.setCaseTypeDesc(caseTypeDesc);
        mBean.setCaseCounty(caseCounty);
        mBean.setCaseTown(caseTown);
        mBean.setCaseFile(caseFile);
        mBean.setCaseSituation(caseContent);

        mBean.setMediator(mMediator);
        mBean.setPeopleMediationCommittee(mCommittee);

        return new Gson().toJson(mBean);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(accuserSex)) {
            ToastUtils.showLong("申请人性别不能为空");
            return false;
        } else if (TextUtils.isEmpty(accuserEthnic)) {
            ToastUtils.showLong("申请人民族不能为空");
            return false;
        } else if (accuserCounty == null || TextUtils.isEmpty(accuserCounty.getName())) {
            ToastUtils.showLong("申请人所在旗县不能为空");
            return false;
        } else if (TextUtils.isEmpty(accuserPhone)) {
            ToastUtils.showLong("申请人联系电话不能为空");
            return false;
        } else if (TextUtils.isEmpty(accuserBirthday)) {
            ToastUtils.showLong("申请人出生日期不能为空");
            return false;
        } else if (TextUtils.isEmpty(accuserIdCard)) {
            ToastUtils.showLong("申请人身份证号不能为空");
            return false;
        } else if (TextUtils.isEmpty(caseTypeDesc)) {
            ToastUtils.showLong("纠纷类型不能为空");
            return false;
        } else if (caseCounty == null || TextUtils.isEmpty(caseCounty.getName())) {
            ToastUtils.showLong("案发旗县不能为空");
            return false;
        } else if (TextUtils.isEmpty(caseContent)) {
            ToastUtils.showLong("案情及申请理由不能为空");
            return false;
        } else {
            return true;
        }
    }

    protected void commitRequest(String url, String queryStr, final String flag) {
        OkGo.<String>post(url)
                .params(NetConstant.QUERY, queryStr)
                .execute(new DialogStringCallBack(mActivity, "提交中...") {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int status = jsonObject.getInt(Constant.STATUS);
                            if (status == 0) {
                                if ("yes".equals(flag)) {
                                    ToastUtils.showShort("通过成功");
                                } else {
                                    ToastUtils.showShort("退回成功");
                                }
                                LocalBroadcastManager.getInstance(mActivity)
                                        .sendBroadcast(new Intent(Constant.DEAL_BUSINESS));
                                commitResult();
                                finishActivity();
                            } else {
                                ToastUtils.showLong(jsonObject.getString(Constant.MSG));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    protected void showDraftData(PeoplesMediationData bean) {
        id = bean.getId();
        accuserSex = bean.getAccuserSex();
        defendantSex = bean.getDefendantSex();
        accuserEthnic = bean.getAccuserEthnic();
        defendantEthnic = bean.getDefendantEthnic();
        accuserCounty = bean.getAccuserCounty();
        defendantCounty = bean.getDefendantCounty();
        accuserTown = bean.getAccuserTown();
        defendantTown = bean.getDefendantTown();
        caseType = bean.getCaseType();
        caseCounty = bean.getCaseCounty();
        caseTown = bean.getCaseTown();
        caseFile = bean.getCaseFile();
        mCommittee = bean.getPeopleMediationCommittee();
        mMediator = bean.getMediator();

        mTvName.setText(bean.getAccuserName());
        mEtName1.setText(bean.getDefendantName());
        mTvSex.setText(bean.getAccuserSexDesc());
        mTvSex1.setText(bean.getDefendantSexDesc());
        mTvEthnic.setText(bean.getAccuserEthnicDesc());
        mTvEthnic1.setText(bean.getDefendantEthnicDesc());
        if (accuserCounty != null) {
            mTvCounty.setText(bean.getAccuserCounty().getName());
            if (accuserCounty != null && !TextUtils.isEmpty(accuserCounty.getId())) {
                areaFlag = Constant.ACCUSER;
                loadTownData(accuserCounty.getId());
            }
            if (accuserTown != null) {
                mTvTown.setText(bean.getAccuserTown().getName());
            }
        }
        if (defendantCounty != null) {
            mTvCounty1.setText(bean.getDefendantCounty().getName());
            if (defendantCounty != null && !TextUtils.isEmpty(defendantCounty.getId())) {
                areaFlag = Constant.DEFENDANT;
                loadTownData(defendantCounty.getId());
            }
            if (defendantTown != null) {
                mTvTown1.setText(bean.getDefendantTown().getName());
            }
        }
        mEtPhone.setText(bean.getAccuserPhone());
        mEtPhoneNum1.setText(bean.getDefendantPhone());
        mTvBirthday.setText(bean.getAccuserBirthday());
        mTvBirthday1.setText(bean.getDefendantBirthday());
        mEtIdCard.setText(bean.getAccuserIdcard());
        mEtIdCardNum1.setText(bean.getDefendantIdcard());
        mEtOccupation.setText(bean.getAccuserOccupation());
        mEtOccupation1.setText(bean.getDefendantOccupation());
        mEtAddress.setText(bean.getAccuserAddress());
        mEtAddress1.setText(bean.getDefendantAddress());
        mEtDomicile.setText(bean.getAccuserDomicile());
        mEtDomicile1.setText(bean.getDefendantDomicile());

        mEtCaseTitle.setText(bean.getCaseTitle());
        mTvDisputeType.setText(bean.getCaseTypeDesc());
        if (caseCounty != null) {
            mTvCaseCounty.setText(bean.getCaseCounty().getName());
            if (caseCounty != null && !TextUtils.isEmpty(caseCounty.getId())) {
                areaFlag = Constant.CASE;
                loadTownData(caseCounty.getId());
            }
            if (caseTown != null) {
                mTvCaseTown.setText(bean.getCaseTown().getName());
            }
        }
        mEtContent.setText(bean.getCaseSituation());

        if (mCommittee != null) {
            mTvPeoplesMediationCommittee.setText(bean.getPeopleMediationCommittee().getName());
            if (!TextUtils.isEmpty(mCommittee.getId())) {
                loadMediatorData(mCommittee.getId(), caseCounty.getId(), caseTown.getId());
            }
            if (mMediator != null) {
                mTvPeoplesMediator.setText(bean.getMediator().getName());
            }
        }

        setCaseFile(bean.getCaseFile());
    }

    private void initPickerView() {
//        initAgentTypePickerView(new OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                mTvAgentType.setText(agentTypeList.get(options1).getLabel());
//            }
//        });

        initOccupationPickerViewView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                TypeBean bean = occupationList.get(options1);
                if (Constant.ACCUSER.equals(flag)) {
                    mEtOccupation.setText(bean.getLabel());
                    accuserOccupation = bean.getValue();
                } else {
                    mEtOccupation1.setText(bean.getLabel());
                    defendantOccupation = bean.getValue();
                }
            }
        });

        initDisputeTypePickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvDisputeType.setText(disputeTypeList.get(options1).getLabel());
                caseType = disputeTypeList.get(options1).getValue();
                caseTypeDesc = disputeTypeList.get(options1).getLabel();
            }
        });

        initSexPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (Constant.ACCUSER.equals(areaFlag)) {
                    mTvSex.setText(sexList.get(options1));
                    accuserSex = getSex(sexList.get(options1));
                } else {
                    mTvSex1.setText(sexList.get(options1));
                    defendantSex = getSex(sexList.get(options1));
                }
            }
        });

        initEthnicPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (Constant.ACCUSER.equals(areaFlag)) {
                    accuserEthnic = ethnicList.get(options1).getValue();
                    mTvEthnic.setText(ethnicList.get(options1).getLabel());
                } else {
                    defendantEthnic = ethnicList.get(options1).getValue();
                    mTvEthnic1.setText(ethnicList.get(options1).getLabel());
                }
            }
        });
        initBirthdayPicker(new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (Constant.ACCUSER.equals(areaFlag)) {
                    mTvBirthday.setText(getTime(date));
                    accuserBirthday = getTime(date);
                } else {
                    defendantBirthday = getTime(date);
                    mTvBirthday1.setText(getTime(date));
                }
            }
        });
        initCountyPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Area bean = countyList.get(options1);

                loadTownData(bean.getId());

                if (Constant.ACCUSER.equals(areaFlag)) {
                    accuserCounty.setId(bean.getId());
                    accuserCounty.setName(bean.getName());
                    mTvCounty.setText(bean.getName());

                    mTvTown.setText("");
                    accuserTown = new Area();

                } else if (Constant.DEFENDANT.equals(areaFlag)) {
                    defendantCounty.setId(bean.getId());
                    defendantCounty.setName(bean.getName());
                    mTvCounty1.setText(bean.getName());

                    mTvTown1.setText("");
                    defendantTown = new Area();
                } else {
                    caseCounty.setId(bean.getId());
                    caseCounty.setName(bean.getName());
                    mTvCaseCounty.setText(bean.getName());

                    mTvCaseTown.setText("");
                    caseTown = new Area();

                    mTvPeoplesMediationCommittee.setText("");
                    mCommittee = new Office();

                    mTvPeoplesMediator.setText("");
                    mMediator = new Office();
                }
            }
        });
    }

    private void loadTownData(String countyId) {
        initTownPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3,
                                        View v) {
                if (options1 < 0) {
                    ToastUtils.showLong("请等待数据加载完成");
                    return;
                }
                Area bean = townList.get(options1);
                if (Constant.ACCUSER.equals(areaFlag)) {
                    mTvTown.setText(bean.getName());
                    accuserTown.setId(bean.getId());
                    accuserTown.setName(bean.getName());
                    mBean.setAccuserTown(accuserTown);
                } else if (Constant.DEFENDANT.equals(areaFlag)) {
                    mTvTown1.setText(bean.getName());
                    defendantTown.setId(bean.getId());
                    defendantTown.setName(bean.getName());
                    mBean.setDefendantTown(defendantTown);
                } else if (Constant.CASE.equals(areaFlag)) {
                    mTvCaseTown.setText(bean.getName());
                    caseTown.setId(bean.getId());
                    caseTown.setName(bean.getName());
                    mBean.setCaseTown(caseTown);

                    //  case town -> committee
                    loadCommitteeData();
                }
            }
        }, countyId);
    }

    private void loadCommitteeData() {
        initCommitteePickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2,
                                        int options3,
                                        View v) {
                if (options1 < 0) {
                    ToastUtils.showLong("数据在加载中，请等待数据加载完成");
                    return;
                }
                Committee committee = committeeList.get(options1);
                if (Constant.PICKER_NO_DATA.equals(committee.getId())) {
                    ToastUtils.showLong("该地区下暂无调解委员会");
                    return;
                }
                mCommittee
                        .setId(committee.getId());
                mTvPeoplesMediationCommittee
                        .setText(committee.getAgencyName());
                mBean.setPeopleMediationCommittee(
                        mCommittee);
                mTvPeoplesMediator.setText("");
                mMediator = new Office();
                mBean.setMediator(mMediator);
                loadMediatorData(mCommittee.getId(), caseCounty.getId(), caseTown.getId());
            }
        }, caseCounty.getId(), caseTown.getId());
    }
//  与 200/530/100 功能相同，弃用
//	private void loadMediatorData(String committeeId) {
//
//		initMediatorPickerView(new OnOptionsSelectListener() {
//			@Override
//			public void onOptionsSelect(int options1, int options2,
//				int options3,
//				View v) {
//				if (options1 < 0 || mediatorList.size() < options1) {
//					ToastUtils.showLong("请等待数据加载完成");
//					return;
//				}
//				Mediator mediator = mediatorList.get(options1);
//				if (Constant.PICKER_NO_DATA.equals(mediator.getId())) {
//					ToastUtils.showLong("该调委会暂无调解员");
//					return;
//				}
//				mMediator.setId(mediator.getId());
//				mTvPeoplesMediator
//					.setText(mediator.getName());
//				mBean.setMediator(mMediator);
//			}
//		}, committeeId);
//	}

    private void loadMediatorData(String committeeId, String countyId, String townId) {

        initPeoplePicker(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (options1 < 0) {
                    ToastUtils.showLong("请等待数据加载完成");
                    return;
                }
                InsUserBean users = peopleList.get(options1);
                if (Constant.PICKER_NO_DATA.equals(users.getId())) {
                    ToastUtils.showLong("该调委会暂无调解员");
                    return;
                }
                mMediator.setId(users.getId());
                mMediator.setName(users.getName());
                mTvPeoplesMediator.setText(users.getName());
                mBean.setMediator(mMediator);
            }
        }, Business.PEOPLE_MEDIATION, committeeId, countyId, townId);
    }
}

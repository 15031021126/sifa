package com.liuhezhineng.ximengsifa.business.fastlegal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.InsUserBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.fastlegal.FastFlowBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.business.base.BaseFastLegalActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.WorkflowActivity;
import com.liuhezhineng.ximengsifa.callback.NewWorkflowActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant.FastLegal;
import com.liuhezhineng.ximengsifa.model.StringDialogCallback;
import com.liuhezhineng.ximengsifa.utils.IDCardInfoExtractor;
import com.liuhezhineng.ximengsifa.utils.IDCardValidator;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.liuhezhineng.ximengsifa.vidyo.VideoRequestActivity;
import com.lzy.okgo.OkGo;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 快速受理表
 */
public class AQuickAcceptLegalAidActivity extends BaseFastLegalActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.tv_accept_people)
    TextView mTvAcceptPeople;
    @BindView(R.id.tv_accept_people_no)
    TextView mTvAcceptPeopleNo;
    @BindView(R.id.tv_case_no)
    TextView mTvCaseNo;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.tv_ethnic)
    TextView mTvEthnic;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_id_card_num)
    EditText mEtIdCard;
    @BindView(R.id.tv_county)
    TextView mTvCounty;
    @BindView(R.id.tv_town)
    TextView mTvTown;
    @BindView(R.id.tv_case_title)
    EditText mTvCaseTitle;
    @BindView(R.id.tv_case_category)
    TextView mTvCaseCategory;
    @BindView(R.id.tv_case_type)
    TextView mTvCaseType;
    @BindView(R.id.tv_case_county)
    TextView mTvCaseCounty;
    @BindView(R.id.tv_case_town)
    TextView mTvCaseTown;
    @BindView(R.id.tv_case_content)
    EditText mTvCaseContent;
    @BindView(R.id.tv_case_date)
    TextView mTvCaseDate;
    @BindView(R.id.tv_case_count)
    TextView mTvCaseCount;
    @BindView(R.id.tv_accept_way)
    TextView mTvAcceptWay;
    @BindView(R.id.tv_case_money)
    TextView mTvCaseMoney;
    @BindView(R.id.tv_case_level)
    TextView mTvCaseLevel;
    @BindView(R.id.tv_undertaker_organize)
    TextView mTvUndertakerOrganize;
    @BindView(R.id.tv_undertaker)
    TextView mTvUndertaker;
    @BindView(R.id.tv_deal_content)
    EditText mTvDealContent;
    @BindView(R.id.tv_file)
    TextView mTvFile;
    @BindView(R.id.rv_annex)
    RecyclerView mRvAnnex;
    @BindView(R.id.ll_annex)
    LinearLayout mLlAnnex;
    @BindView(R.id.btn_no)
    Button mBtnNo;
    @BindView(R.id.btn_yes)
    Button mBtnYes;
    @BindView(R.id.ll_btn)
    LinearLayout mLlBtn;
    @BindView(R.id.layout_fast_accept_assign_undertaker)
    LinearLayout mLlAssignUndertaker;
    @BindView(R.id.layout_fast_accept_undertaker_deal)
    LinearLayout mLlUndertakerDeal;
    @BindView(R.id.btn_vidyo)
    Button mBtnVidyo;
    @BindView(R.id.tv_staff_office)
    TextView mTvStaffOffice;
    @BindView(R.id.ll_staff_office)
    LinearLayout mLlStaffOffice;
    @BindView(R.id.tv_is_transfer_penalty)
    TextView mTvIsTransferPenalty;
    @BindView(R.id.ll_is_transfer_penalty)
    LinearLayout mLlIsTransferPenalty;
    @BindView(R.id.tv_is_Petition)
    TextView mTvIsPetition;
    @BindView(R.id.ll_is_petition)
    LinearLayout mLlIsPetition;
    @BindView(R.id.tv_is_affect_stability)
    TextView mTvIsAffectStability;
    @BindView(R.id.ll_is_affect_stability)
    LinearLayout mLlIsAffectStability;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.et_actual_amount)
    EditText mEtActualAmount;

    private String pickerFlag;
    private FastFlowBean mBean;
    private String isSubmit;

    public static void actionStart(Context context, BusinessBean item) {
        Intent intent = new Intent(context, AQuickAcceptLegalAidActivity.class);
        intent.putExtra(Constant.BUSINESS, item);
        ((BaseActivity) context).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(mTopBar, "法律服务快速受理表");
        btnWorkflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewWorkflowActivity.actionStart(AQuickAcceptLegalAidActivity.this, mBusinessBean,mBaseBusinessBean);
            }
        });
        mEtIdCard.setEnabled(true);
        mTvCaseTitle.setEnabled(true);
        mTvCaseContent.setEnabled(true);
        mTvCaseCount.setEnabled(true);
        switch (mBusinessBean.getTask().getTaskDefinitionKey()) {
            case Constant.FastLegal.DEAL:
                mLlUndertakerDeal.setVisibility(View.VISIBLE);
            case Constant.FastLegal.ASSIGN_PEOPLE:
                mLlAssignUndertaker.setVisibility(View.VISIBLE);
                break;
            case Constant.FastLegal.REVIEW:
                break;
            default:
                break;
        }

    }

    @Override
    protected void initData() {
        super.initData();
        mBean = new FastFlowBean();
        initPicker();
    }

    @Override
    protected void showDetails(FastFlowBean data) {
        super.showDetails(data);
        mBean = data;
        // 区分人员字段：personnelType (String；字段长度：2；0:社会大众；1:工作人员)
        if ("0".equals(data.getPersonnelType())) {
            mLlIsAffectStability.setVisibility(View.GONE);
            mLlIsPetition.setVisibility(View.GONE);
            mLlIsTransferPenalty.setVisibility(View.GONE);
            mLlStaffOffice.setVisibility(View.GONE);
        } else {
            mLlAddress.setVisibility(View.GONE);
        }
        mEtActualAmount.setText(data.getActualAmount());
        mTvStaffOffice.setText(data.getStaffOffice().getName());
        mTvAddress.setText(data.getCaseAddress());
        mTvIsAffectStability.setText(data.getIsAffectStabilityDesc());
        mTvIsPetition.setText(data.getIsPetitionDesc());
        mTvIsTransferPenalty.setText(data.getIsTransferPenaltyDesc());
        mTvCaseLevel.setText(data.getCaseRank());

        if (data.getCreateBy().getId().equals(UserHelper.getUser().getId())) {
            mBtnVidyo.setVisibility(View.GONE);
        }

        mTvAcceptPeople.setText(data.getAcceptManName());
        mTvAcceptPeopleNo.setText(data.getAcceptManCode());
        mTvCaseNo.setText(data.getCaseAcceptCode());

        mEtName.setText(data.getAccuserName());
        mTvSex.setText(data.getAccuserSexDesc());
        mTvEthnic.setText(data.getAccuserEthnicDesc());
        mTvBirthday.setText(data.getAccuserBirthday());
        mEtPhone.setText(data.getAccuserPhone());
        mEtIdCard.setText(data.getAccuserIdCard());

        generateSexAndBirthday();

        Area county = data.getAccuserCounty();
        if (county != null && !TextUtils.isEmpty(county.getName())) {
            mTvCounty.setText(county.getName());
            pickerFlag = Constant.ACCUSER;
            loadTownData(county.getId());
        }
        Area town = data.getAccuserTown();
        if (town != null && !TextUtils.isEmpty(town.getName())) {
            mTvTown.setText(town.getName());
        }

        mTvCaseTitle.setText(data.getCaseTitle());
        mTvCaseCategory.setText(data.getCaseClassifyDesc());
        if (!TextUtils.isEmpty(data.getCaseClassify())) {
            loadCaseType(data.getCaseClassify());
        }
        mTvCaseType.setText(data.getCaseTypeDesc());
        Area caseCounty = data.getCaseCounty();
        if (caseCounty != null && !TextUtils.isEmpty(caseCounty.getName())) {
            mTvCaseCounty.setText(caseCounty.getName());
            pickerFlag = Constant.CASE;
            loadTownData(caseCounty.getId());
        }
        Area caseTown = data.getCaseTown();
        if (caseTown != null && !TextUtils.isEmpty(caseTown.getName())) {
            mTvCaseTown.setText(caseTown.getName());
        }
        mTvCaseContent.setText(data.getCaseReason());

        mTvCaseDate.setText(data.getCaseTime());
        mTvCaseCount.setText(data.getCaseInvolvecount());
        mTvAcceptWay.setText(data.getCaseSourceDesc());
        mTvCaseMoney.setText(data.getCaseInvolveMoneyDesc());
        mTvCaseLevel.setText(data.getCaseRankDesc());

        Office office = data.getOffice();
        if (office != null && !TextUtils.isEmpty(office.getName())) {
            mTvUndertakerOrganize.setText(office.getName());
        }
        UserBean transactor = data.getTransactor();
        if (transactor != null && !TextUtils.isEmpty(transactor.getName())) {
            mTvUndertaker.setText(transactor.getName());
        }

        mTvDealContent.setText(data.getHandleResult());

        loadInsPicker();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_quick_accept_legal_aid;
    }

    @Override
    protected void setListener() {
        super.setListener();

        mEtIdCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 18) {
                    generateSexAndBirthday();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void generateSexAndBirthday() {
        String idCard = mEtIdCard.getText().toString().trim();
        mBean.setAccuserIdCard(idCard);

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
        mTvBirthday.setText(birthday);
        mBean.setAccuserBirthday(birthday);

        String gender = idCardInfo.getGender();
        mTvSex.setText(gender);
        mBean.setAccuserSex(getSex(gender));
    }

    @Override
    @OnClick({R.id.tv_sex, R.id.tv_ethnic, R.id.tv_birthday, R.id.tv_county, R.id.tv_town,
            R.id.tv_case_category, R.id.tv_case_type, R.id.tv_case_county, R.id.tv_case_town,
            R.id.tv_case_date, R.id.tv_accept_way, R.id.tv_case_level, R.id.tv_undertaker_organize,
            R.id.tv_undertaker, R.id.btn_no, R.id.btn_yes, R.id.tv_case_money, R.id.btn_save,
            R.id.btn_vidyo})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.btn_vidyo:
                VideoRequestActivity.actionStart(mActivity, mBean.getAccuserName(), mBean.getCaseTitle(), mBean.getCreateBy().getId());
                break;
            case R.id.btn_save:
                isSubmit = "false";
                flag = "no";
                createForm();
                commitAcceptForm();
                break;
            case R.id.tv_case_money:
                showPickerView(caseMoneyPickerView);
                break;
            case R.id.tv_ethnic:
                showPickerView(ethnicPickerView);
                break;
            case R.id.tv_county:
                pickerFlag = Constant.ACCUSER;
                showPickerView(countyPickerView);
                break;
            case R.id.tv_town:
                pickerFlag = Constant.ACCUSER;
                loadTownData(mBean.getAccuserCounty().getId());
                showPickerView(townPickerView);
                break;
            case R.id.tv_case_category:
                showPickerView(actTypePickerView);
                break;
            case R.id.tv_case_type:
                showPickerView(fastCaseTypePickerView);
                break;
            case R.id.tv_case_county:
                pickerFlag = Constant.CASE;
                showPickerView(countyPickerView);
                break;
            case R.id.tv_case_town:
                pickerFlag = Constant.CASE;
                loadTownData(mBean.getCaseCounty().getId());
                showPickerView(townPickerView);
                break;
            case R.id.tv_case_date:
                pickerFlag = Constant.CASE;
                birthdayPickerView.show();
                break;
            case R.id.tv_accept_way:
                showPickerView(aidWayPickerView);
                break;
            case R.id.tv_case_level:
                showPickerView(caseRankPickerView);
                break;
            case R.id.tv_undertaker_organize:
                showPickerView(insPicker);
                break;
            case R.id.tv_undertaker:
                showPickerView(peoplePicker);
                break;
            // 退回
            case R.id.btn_no:
                Log.i("sss","sadasdasdasd="+"mBtnNo");
                isSubmit = "true";
                flag = "no";
                commitAcceptForm();
                break;
            case R.id.btn_yes:
                isSubmit = "true";
                flag = "yes";
                createForm();
                if (checkInput()) {
                    commitAcceptForm();
                }
                break;
            case R.id.tv_sex:
                showPickerView(sexPickerView);
                break;
            case R.id.tv_birthday:
                pickerFlag = Constant.ACCUSER;
                birthdayPickerView.show();
                break;
            default:
                break;
        }
    }

    private void createForm() {
        mBean.setAccuserName(mEtName.getText().toString().trim());
        mBean.setAccuserPhone(mEtPhone.getText().toString().trim());
        mBean.setAccuserIdCard(mEtIdCard.getText().toString().trim());
        mBean.setCaseTitle(mTvCaseTitle.getText().toString().trim());
        mBean.setCaseReason(mTvCaseContent.getText().toString().trim());
        mBean.setCaseInvolvecount(mTvCaseCount.getText().toString().trim());
        mBean.setHandleResult(mTvDealContent.getText().toString().trim());
        mBean.setCaseFile(caseFile);
    }

    private void commitAcceptForm() {
        mBean.setIsSubmit(isSubmit);
        mBean.setAct(getAct());
        String queryStr = new Gson().toJson(mBean);

        OkGo.<String>post(FastLegal.COMMIT_FLOW)
                .params(Constant.QUERY, queryStr)
                .execute(new StringDialogCallback(mActivity) {
                    @Override
                    protected void responseSuccess(JSONObject object) {
                        if (Boolean.parseBoolean(isSubmit)) {
                            if ("yes".equals(flag)) {
                                ToastUtils.showLong("提交成功");
                            } else {
                                ToastUtils.showLong("退回成功");
                            }
                            LocalBroadcastManager.getInstance(mActivity)
                                    .sendBroadcast(new Intent(Constant.DEAL_BUSINESS));
                            setResult(RESULT_OK);
                            finishActivity();
                        } else {
                            ToastUtils.showLong("保存成功");
                        }
                    }
                });
    }

    private void loadTownData(String countyId) {
        initTownPickerView((options1, options2, options3, v) -> {
            if (options1 < 0) {
                ToastUtils.showLong("请等待数据加载完成");
                return;
            }
            Area townArea = townList.get(options1);
            if (Constant.ACCUSER.equals(pickerFlag)) {
                mTvTown.setText(townArea.getName());

                mBean.setAccuserTown(townArea);
            } else {
                mTvCaseTown.setText(townArea.getName());

                mBean.setCaseTown(townArea);
                resetOrganize();
                resetUndertaker();
            }
        }, countyId);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(mBean.getAccuserName())) {
            ToastUtils.showLong("申请人姓名不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getAccuserEthnic())) {
            ToastUtils.showLong("申请人民族不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getAccuserIdCard())) {
            ToastUtils.showLong("申请人身份证号不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getAccuserSex())) {
            ToastUtils.showLong("申请人性别不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getAccuserBirthday())) {
            ToastUtils.showLong("申请人出生日期不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getAccuserPhone())) {
            ToastUtils.showLong("申请人联系方式不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getAccuserCounty().getId())) {
            ToastUtils.showLong("申请人所在地区不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getCaseTitle())) {
            ToastUtils.showLong("案件标题不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getCaseClassify())) {
            ToastUtils.showLong("案件类别不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getCaseType())) {
            ToastUtils.showLong("案件类型不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getCaseCounty().getId())) {
            ToastUtils.showLong("案件地区不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getCaseReason())) {
            ToastUtils.showLong("案件内容不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getCaseTime())) {
            ToastUtils.showLong("案发时间不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getCaseInvolvecount())) {
            ToastUtils.showLong("涉案人数不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getCaseSource())) {
            ToastUtils.showLong("受理方式不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getCaseRank())) {
            ToastUtils.showLong("案件重要等级不能为空");
            return false;
        } else if (TextUtils.isEmpty(mBean.getOffice().getId())) {
            ToastUtils.showLong("承办机构不能为空");
            return false;
        }
        return true;
    }

    private void resetOrganize() {
        mTvUndertakerOrganize.setText("");
        mBean.setTransactor(null);

        loadInsPicker();
    }

    private void resetUndertaker() {
        mTvUndertaker.setText("");
        mBean.setTransactor(null);

//		loadPeoplePicker();
    }

    private void loadInsPicker() {
        initInsPicker((options1, options2, options3, v) -> {
            if (options1 < 0) {
                ToastUtils.showLong("请等待数据加载完成");
                return;
            }
            InsUserBean bean = insList.get(options1);
            if (Constant.PICKER_NO_DATA.equals(bean.getId())) {
                ToastUtils.showLong(bean.getName());
                return;
            }
            mTvUndertakerOrganize.setText(bean.getName());
            Office office = new Office();
            office.setId(bean.getId());
            office.setName(bean.getName());
            mBean.setOffice(office);

            resetUndertaker();
        }, mBean.getCaseClassify(), mBean.getCaseCounty().getId(), mBean.getCaseTown().getId());
    }

    private void initPicker() {
        initSexPickerView((options1, options2, options3, v) -> {
            mTvSex.setText(sexList.get(options1));
            mBean.setAccuserSex(getSex(sexList.get(options1)));
        });

        initEthnicPickerView((options1, options2, options3, v) -> {
            mTvEthnic.setText(ethnicList.get(options1).getLabel());
            mBean.setAccuserEthnic(ethnicList.get(options1).getValue());
        });

        initBirthdayPicker((date, v) -> {
            if (Constant.ACCUSER.equals(pickerFlag)) {
                mTvBirthday.setText(getTime(date));
                mBean.setAccuserBirthday(getTime(date));
            } else {
                mTvCaseDate.setText(getTime(date));
                mBean.setCaseTime(getTime(date));
            }
        });

        initCountyPickerView((options1, options2, options3, v) -> {
            Area area = countyList.get(options1);
            if (Constant.ACCUSER.equals(pickerFlag)) {
                mTvCounty.setText(area.getName());
                mTvTown.setText("");

                mBean.setAccuserCounty(area);
                mBean.setAccuserTown(null);
            } else {
                mTvCaseCounty.setText(area.getName());
                mTvCaseTown.setText("");

                mBean.setCaseCounty(area);
                mBean.setCaseTown(null);

                resetOrganize();
                resetUndertaker();
            }

            loadTownData(area.getId());
        });

        initActTypePicker((options1, options2, options3, v) -> {
            mTvCaseCategory.setText(actTypeList.get(options1).getLabel());
            mTvCaseType.setText("");

            mBean.setCaseClassify(actTypeList.get(options1).getValue());
            mBean.setCaseType("");

            loadCaseType(actTypeList.get(options1).getValue());
            resetUndertaker();
            resetOrganize();
        });

        initAidWayView((options1, options2, options3, v) -> {
            mTvAcceptWay.setText(aidWayList.get(options1).getLabel());
            mBean.setCaseSource(aidWayList.get(options1).getValue());
        });

        initCaseRankView((options1, options2, options3, v) -> {
            mTvCaseLevel.setText(caseRankList.get(options1).getLabel());
            mBean.setCaseRank(caseRankList.get(options1).getValue());
        });

        initCaseMoneyPicker((options1, options2, options3, v) -> {
            mTvCaseMoney.setText(caseMoneyList.get(options1).getLabel());
            mBean.setCaseInvolveMoney(caseMoneyList.get(options1).getValue());
        });
    }

    private void loadCaseType(String value) {
        initFastCastTypePicker((options1, options2, options3, v) -> {
            if (options1 < 0) {
                ToastUtils.showLong("请等待数据加载完成");
                return;
            }
            TypeBean bean = fastCaseTypeList.get(options1);
            if (Constant.PICKER_NO_DATA.equals(bean.getValue())) {
                ToastUtils.showLong(bean.getLabel());
                return;
            }
            mTvCaseType.setText(bean.getLabel());
            mBean.setCaseType(bean.getValue());
        }, value);
    }
}

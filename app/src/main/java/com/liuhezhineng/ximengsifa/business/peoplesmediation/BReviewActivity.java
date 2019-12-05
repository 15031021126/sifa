package com.liuhezhineng.ximengsifa.business.peoplesmediation;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.PeoplesMediationData;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form.MediationWorkflow;
import com.liuhezhineng.ximengsifa.business.base.BasePeoplesMediationActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.vidyo.VideoRequestActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 人民调解员审核
 */
public class BReviewActivity extends BasePeoplesMediationActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.et_name)
    TextView mEtName;
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
    TextView mEtIdCardNum;
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

    String areaFlag = Constant.ACCUSER;
    @BindView(R.id.tv_dispute_type)
    TextView mTvDisputeType;
    @BindView(R.id.tv_case_county)
    TextView mTvCaseCounty;
    @BindView(R.id.tv_case_town)
    TextView mTvCaseTown;
    @BindView(R.id.tv_agent_name)
    TextView mTvAgentName;
    @BindView(R.id.tv_agent_id_card)
    TextView mTvAgentIdCard;
    @BindView(R.id.tv_agent_type)
    TextView mTvAgentType;

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
    private String accuserPostCode;
    private String accuserPhone;
    private String defendantName;
    private String defendantIdCard;
    private String defendantSex;
    private String defendantBirthday;
    private String defendantEthnic;
    private Area defendantCounty = new Area();
    private Area defendantTown = new Area();
    private String defendantOccupation;
    private String defendantDomicile;
    private String defendantAddress;
    private String defendantPostCode;
    private String defendantPhone;
    private String caseTitle;
    private String caseContent;
    private Office mMediator = new Office();
    private Office mCommittee = new Office();
    private String caseType;
    private String caseTypeDesc;
    private Area caseCounty = new Area();
    private Area caseTown = new Area();
    private boolean isLoadAccuserTown;
    private boolean isLoadDefendantTown;
    private boolean isLoadCaseTown;

    public static void actionStart(Context context,
                                   BusinessBean item) {
        Intent intent = new Intent(context, BReviewActivity.class);
        intent.putExtra(Constant.BUSINESS, item);
        ((BaseActivity) context).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_review;
    }

    @Override
    protected void initData() {
        super.initData();
        mBean = new MediationWorkflow();
        mData = new PeoplesMediationData();
        initPickerView();
        loadBusinessDetails();
    }

    private void initPickerView() {
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
                mBean.setCaseType(disputeTypeList.get(options1).getValue());
                mBean.setCaseTypeDesc(disputeTypeList.get(options1).getLabel());
            }
        });
        initSexPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (Constant.ACCUSER.equals(areaFlag)) {
                    mTvSex.setText(sexList.get(options1));
                    accuserSex = sexList.get(options1);
                    mBean.setAccuserSex("女".equals(sexList.get(options1)) ? "2" : "1");
                } else {
                    mTvSex1.setText(sexList.get(options1));
                    defendantSex = sexList.get(options1);
                    mBean.setDefendantSex("女".equals(sexList.get(options1)) ? "2" : "1");
                }
            }
        });
        initEthnicPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (Constant.ACCUSER.equals(areaFlag)) {
                    mTvEthnic.setText(ethnicList.get(options1).getLabel());
                    accuserEthnic = ethnicList.get(options1).getValue();
                    mBean.setAccuserEthnic(ethnicList.get(options1).getValue());
                } else {
                    mTvEthnic1.setText(ethnicList.get(options1).getLabel());
                    defendantEthnic = ethnicList.get(options1).getValue();
                    mBean.setDefendantEthnic(ethnicList.get(options1).getValue());
                }
            }
        });
        initBirthdayPicker(new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (Constant.ACCUSER.equals(areaFlag)) {
                    mTvBirthday.setText(getTime(date));
                    accuserBirthday = getTime(date);
                    mBean.setAccuserBirthday(getTime(date));
                } else {
                    mTvBirthday1.setText(getTime(date));
                    defendantBirthday = getTime(date);
                    mBean.setDefendantBirthday(getTime(date));
                }
            }
        });

        initCountyPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Area bean = countyList.get(options1);
                loadTownData(bean.getId());
                if (Constant.ACCUSER.equals(areaFlag)) {
                    isLoadAccuserTown = true;
                    accuserCounty.setId(bean.getId());
                    accuserCounty.setName(bean.getName());
                    mTvCounty.setText(bean.getName());

                    mTvTown.setText("");
                    accuserTown = new Area();
                } else if (Constant.DEFENDANT.equals(areaFlag)) {
                    isLoadDefendantTown = true;
                    defendantCounty.setId(bean.getId());
                    defendantCounty.setName(bean.getName());
                    mTvCounty1.setText(bean.getName());
                    mBean.setDefendantCounty(defendantCounty);
                    mTvTown1.setText("");
                    defendantTown = new Area();
                    mBean.setAccuserTown(defendantTown);
                } else {
                    isLoadCaseTown = true;
                    caseCounty.setId(bean.getId());
                    caseCounty.setName(bean.getName());

                    mTvCaseCounty.setText(bean.getName());
                    mBean.setCaseCounty(caseCounty);
                    mTvCaseTown.setText("");
                    caseTown = new Area();
                    mBean.setAccuserTown(caseTown);
                    mTvPeoplesMediationCommittee.setText("");
                    mCommittee = new Office();
                    mBean.setPeopleMediationCommittee(mCommittee);
                    mTvPeoplesMediator.setText("");
                    mMediator = new Office();
                    mBean.setMediator(mMediator);
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
                } else {
                    mTvCaseTown.setText(bean.getName());
                    caseTown.setId(bean.getId());
                    caseTown.setName(bean.getName());
                    mBean.setCaseTown(caseTown);
                }
            }
        }, countyId);
    }

    @Override
    protected void parseData(JSONObject data) {
        mData = new Gson()
                .fromJson(data.toString(), PeoplesMediationData.class);
        mTvAgentName.setText(mData.getProxyName());
        mTvAgentIdCard.setText(mData.getProxyIdCard());
        if (!TextUtils.isEmpty(mData.getProxyType())) {
            mTvAgentType.setText("工作人员");
        }

        accuserName = mData.getAccuserName();
        defendantName = mData.getDefendantName();
        accuserSex = mData.getAccuserSex();
        defendantSex = mData.getDefendantSex();
        accuserEthnic = mData.getAccuserEthnic();
        defendantEthnic = mData.getDefendantEthnic();
        accuserCounty = mData.getAccuserCounty();
        accuserTown = mData.getAccuserTown();
        defendantCounty = mData.getDefendantCounty();
        defendantTown = mData.getDefendantTown();
        accuserPostCode = mData.getAccuserPostCode();
        defendantPostCode = mData.getDefendantPostCode();
        accuserPhone = mData.getAccuserPhone();
        defendantPhone = mData.getDefendantPhone();
        accuserBirthday = mData.getAccuserBirthday();
        accuserIdCard = mData.getAccuserIdcard();
        defendantIdCard = mData.getDefendantIdcard();
        defendantBirthday = mData.getDefendantBirthday();
        accuserOccupation = mData.getAccuserOccupation();
        defendantOccupation = mData.getDefendantOccupation();
        accuserAddress = mData.getAccuserAddress();
        defendantAddress = mData.getDefendantAddress();
        accuserDomicile = mData.getAccuserDomicile();
        defendantDomicile = mData.getDefendantDomicile();
        caseTitle = mData.getCaseTitle();
        caseFile = mData.getCaseFile();
        caseContent = mData.getCaseSituation();
        mMediator = mData.getMediator();
        mCommittee = mData.getPeopleMediationCommittee();
        caseType = mData.getCaseType();
        caseTypeDesc = mData.getCaseTypeDesc();
        caseCounty = mData.getCaseCounty();

        if (accuserCounty != null && !TextUtils.isEmpty(accuserCounty.getId())) {
            areaFlag = Constant.ACCUSER;
            loadTownData(accuserCounty.getId());
        }
        if (defendantCounty != null && !TextUtils.isEmpty(defendantCounty.getId())) {
            areaFlag = Constant.DEFENDANT;
            loadTownData(defendantCounty.getId());
        }
        if (caseCounty != null && !TextUtils.isEmpty(caseCounty.getId())) {
            areaFlag = Constant.CASE;
            loadTownData(caseCounty.getId());
        }

        mEtName.setText(mData.getAccuserName());
        mEtName1.setText(mData.getDefendantName());
        mTvSex.setText(mData.getAccuserSexDesc());
        mTvSex1.setText(mData.getDefendantSexDesc());
        mTvEthnic.setText(mData.getAccuserEthnicDesc());
        mTvEthnic1.setText(mData.getDefendantEthnicDesc());
        mTvCounty.setText(mData.getAccuserCounty().getName());
        mTvCounty1.setText(mData.getDefendantCounty().getName());
        mTvTown.setText(mData.getAccuserTown().getName());
        mTvTown1.setText(mData.getDefendantTown().getName());
        mEtPhone.setText(mData.getAccuserPhone());
        mEtPhoneNum1.setText(mData.getDefendantPhone());
        mTvBirthday.setText(mData.getAccuserBirthday());
        mTvBirthday1.setText(mData.getDefendantBirthday());
        mEtIdCardNum.setText(mData.getAccuserIdcard());
        mEtIdCardNum1.setText(mData.getDefendantIdcard());
        mEtOccupation.setText(mData.getAccuserOccupation());
        mEtOccupation1.setText(mData.getDefendantOccupation());
        mEtAddress.setText(mData.getAccuserAddress());
        mEtAddress1.setText(mData.getDefendantAddress());
        mEtDomicile.setText(mData.getAccuserDomicile());
        mEtDomicile1.setText(mData.getDefendantDomicile());
        mEtCaseTitle.setText(mData.getCaseTitle());
        mEtContent.setText(mData.getCaseSituation());
        mTvPeoplesMediationCommittee.setText(mData.getPeopleMediationCommittee().getName());
        mTvPeoplesMediator.setText(mData.getMediator().getName());

        mTvCaseCounty.setText(mData.getCaseCounty().getName());
        mTvCaseTown.setText(mData.getCaseTown().getName());
        mTvDisputeType.setText(mData.getCaseTypeDesc());

        setRequestInfo(mData);
    }

    @Override
    protected void createFrom() {
        mBean.setAccuserName(accuserName);
        mBean.setAccuserIdcard(accuserIdCard);
        mBean.setAccuserBirthday(accuserBirthday);
        mBean.setAccuserCounty(accuserCounty);
        mBean.setAccuserTown(accuserTown);
        mBean.setAccuserOccupation(accuserOccupation);
        mBean.setAccuserDomicile(accuserDomicile);
        mBean.setAccuserAddress(accuserAddress);
        mBean.setAccuserPostCode(accuserPostCode);
        mBean.setAccuserPhone(accuserPhone);
        mBean.setDefendantName(defendantName);
        mBean.setDefendantIdcard(defendantIdCard);
        mBean.setDefendantBirthday(defendantBirthday);
        mBean.setDefendantCounty(defendantCounty);
        mBean.setDefendantTown(defendantTown);
        mBean.setDefendantOccupation(defendantOccupation);
        mBean.setDefendantDomicile(defendantDomicile);
        mBean.setDefendantAddress(defendantAddress);
        mBean.setDefendantPostCode(defendantPostCode);
        mBean.setDefendantPhone(defendantPhone);
        mBean.setMediator(mMediator);
        mBean.setPeopleMediationCommittee(mCommittee);
        mBean.setCaseTitle(caseTitle);
        mBean.setCaseSituation(caseContent);
        mBean.setCaseFile(caseFile);
    }

    @Override
    @OnClick({R.id.tv_sex, R.id.tv_ethnic, R.id.tv_county, R.id.tv_birthday, R.id.tv_town,
            R.id.tv_sex_1, R.id.tv_ethnic_1, R.id.tv_county_1, R.id.tv_birthday_1, R.id.tv_town_1,
            R.id.et_occupation_1, R.id.et_occupation,
            R.id.btn_no, R.id.btn_yes, R.id.tv_peoples_mediator,
            R.id.tv_peoples_mediation_committee, R.id.tv_dispute_type,
    R.id.btn_vidyo})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.btn_vidyo:
                VideoRequestActivity.actionStart(mActivity,
                        mData.getAccuserName(),
                        mData.getCaseTitle(),
                        mData.getCreateBy().getId());
                break;
            case R.id.et_occupation:
                flag = Constant.ACCUSER;
                showPickerView(occupationPickerView);
                break;
            case R.id.et_occupation_1:
                flag = Constant.DEFENDANT;
                showPickerView(occupationPickerView);
                break;
            case R.id.tv_dispute_type:
                showPickerView(disputeTypePickerView);
                break;
            case R.id.tv_sex:
                areaFlag = Constant.ACCUSER;
                showPickerView(sexPickerView);
                break;
            case R.id.tv_ethnic:
                areaFlag = Constant.ACCUSER;
                showPickerView(ethnicPickerView);
                break;
            case R.id.tv_county:
                areaFlag = Constant.ACCUSER;
                showPickerView(countyPickerView);
                break;
            case R.id.tv_birthday:
                areaFlag = Constant.ACCUSER;
                birthdayPickerView.show();
                break;
            case R.id.tv_town:
                areaFlag = Constant.ACCUSER;
                if (!TextUtils.isEmpty(accuserCounty.getId())) {
                    if (!isLoadAccuserTown) {
                        initTownPickerView(new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3,
                                                        View v) {
                                if (options1 < 0) {
                                    ToastUtils.showLong("请等待数据加载完成");
                                    return;
                                }
                                if (areaFlag.equals(Constant.ACCUSER)) {
                                    Area bean = townList.get(options1);
                                    mTvTown.setText(bean.getName());
                                    accuserTown.setId(bean.getId());
                                    accuserTown.setName(bean.getName());
                                    mData.setAccuserTown(accuserTown);
                                }
                            }
                        }, accuserCounty.getId());
                        showPickerView(townPickerView);
                    } else {
                        showPickerView(townPickerView);
                    }
                } else {
                    ToastUtils.showLong("请先选择旗县");
                }
                break;
            case R.id.tv_sex_1:
                areaFlag = Constant.DEFENDANT;
                showPickerView(sexPickerView);
                break;
            case R.id.tv_ethnic_1:
                areaFlag = Constant.DEFENDANT;
                showPickerView(ethnicPickerView);
                break;
            case R.id.tv_county_1:
                areaFlag = Constant.DEFENDANT;
                showPickerView(countyPickerView);
                break;
            case R.id.tv_birthday_1:
                areaFlag = Constant.DEFENDANT;
                birthdayPickerView.show();
                break;
            case R.id.tv_town_1:
                areaFlag = Constant.DEFENDANT;
                if (!TextUtils.isEmpty(defendantCounty.getId())) {
                    if (isLoadDefendantTown) {
                        showPickerView(townPickerView);
                    } else {
                        areaFlag = Constant.DEFENDANT;
                        loadTownData(defendantCounty.getId());
                        showPickerView(townPickerView);
                    }
                } else {
                    ToastUtils.showLong("请先选择旗县");
                }
                break;
            case R.id.btn_no:
                flag = Constant.NO;
                showNoDialog();
                break;
            case R.id.btn_yes:
                flag = Constant.YES;
                if (checkInput(flag)) {
                    commitRequest1(NetConstant.REVIEW);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void dialogCommit() {
        super.dialogCommit();
        commitRequest1(NetConstant.REVIEW);
    }

    private boolean checkInput(String flag) {
        accuserName = getText(mEtName);
        defendantName = getText(mEtName1);
        accuserSex = getText(mTvSex);
        defendantSex = getText(mTvSex1);
        accuserEthnic = getText(mTvEthnic);
        defendantEthnic = getText(mTvEthnic1);
        accuserBirthday = getText(mTvBirthday);
        defendantBirthday = getText(mTvBirthday1);
        accuserPhone = getText(mEtPhone);
        defendantPhone = getText(mEtPhoneNum1);
        accuserIdCard = getText(mEtIdCardNum);
        defendantIdCard = getText(mEtIdCardNum1);
        accuserOccupation = getText(mEtOccupation);
        defendantOccupation = getText(mEtOccupation1);
        accuserAddress = getText(mEtAddress);
        defendantAddress = getText(mEtAddress1);
        accuserDomicile = getText(mEtDomicile);
        defendantDomicile = getText(mEtDomicile1);
        caseTitle = getText(mEtCaseTitle);
        caseContent = getText(mEtContent);

        if (flag.equals("no")) {
            return true;
        }

        if (isEmpty(accuserName)) {
            ToastUtils.showLong("申请人姓名不能为空");
            return false;
        } else if (isEmpty(accuserSex)) {
            ToastUtils.showLong("申请人性别不能为空");
            return false;
        } else if (isEmpty(accuserEthnic)) {
            ToastUtils.showLong("申请人民族不能为空");
            return false;
        } else if (isEmpty(accuserCounty.getName())) {
            ToastUtils.showLong("申请人所在旗县不能为空");
            return false;
        } else if (isEmpty(accuserIdCard)) {
            ToastUtils.showLong("申请人身份证号不能为空");
            return false;
        } else if (TextUtils.isEmpty(accuserPhone)) {
            ToastUtils.showLong("申请人联系电话不能为空");
            return false;
        } else if (isEmpty(accuserBirthday)) {
            ToastUtils.showLong("申请人出生年月日不能为空");
            return false;
        } else if (isEmpty(defendantName)) {
            ToastUtils.showLong("被申请人姓名不能为空");
            return false;
        } else if (isEmpty(defendantSex)) {
            ToastUtils.showLong("被申请人性别不能为空");
            return false;
        } else if (isEmpty(defendantEthnic)) {
            ToastUtils.showLong("被申请人民族不能为空");
            return false;
        } else if (isEmpty(defendantIdCard)) {
            ToastUtils.showLong("被申请人身份证号不能为空");
            return false;
        } else if (isEmpty(defendantCounty.getName())) {
            ToastUtils.showLong("被申请人所在旗县不能为空");
            return false;
        } else if (isEmpty(defendantPhone)) {
            ToastUtils.showLong("被申请人联系电话不能为空");
            return false;
        } else if (isEmpty(defendantBirthday)) {
            ToastUtils.showLong("被申请人出生年月日不能为空");
            return false;
        } else if (TextUtils.isEmpty(caseType)) {
            ToastUtils.showLong("纠纷类型不能为空");
            return false;
        } else if (caseCounty != null && TextUtils.isEmpty(caseCounty.getId())) {
            ToastUtils.showLong("案发旗县不能为空");
            return false;
        } else if (isEmpty(caseContent)) {
            ToastUtils.showLong("案情及申请理由不能为空");
            return false;
        } else {
            return true;
        }
    }

    private void createForm() {
        mBean.setAccuserName(accuserName);
        mBean.setAccuserIdcard(accuserIdCard);
        mBean.setAccuserBirthday(accuserBirthday);
        mBean.setAccuserCounty(accuserCounty);
        mBean.setAccuserTown(accuserTown);
        mBean.setAccuserOccupation(accuserOccupation);
        mBean.setAccuserDomicile(accuserDomicile);
        mBean.setAccuserAddress(accuserAddress);
        mBean.setAccuserPostCode(accuserPostCode);
        mBean.setAccuserPhone(accuserPhone);
        mBean.setDefendantName(defendantName);
        mBean.setDefendantIdcard(defendantIdCard);
        mBean.setDefendantBirthday(defendantBirthday);
        mBean.setDefendantCounty(defendantCounty);
        mBean.setDefendantTown(defendantTown);
        mBean.setDefendantOccupation(defendantOccupation);
        mBean.setDefendantDomicile(defendantDomicile);
        mBean.setDefendantAddress(defendantAddress);
        mBean.setDefendantPostCode(defendantPostCode);
        mBean.setDefendantPhone(defendantPhone);
        mBean.setMediator(mMediator);
        mBean.setPeopleMediationCommittee(mCommittee);
        mBean.setCaseTitle(caseTitle);
        mBean.setCaseSituation(caseContent);
        mBean.setCaseFile(caseFile);
    }
}

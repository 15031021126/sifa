package com.liuhezhineng.ximengsifa.callback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.business.base.BaseBusinessActivity;
import com.liuhezhineng.ximengsifa.callback.bean.TownBean;
import com.liuhezhineng.ximengsifa.callback.bean.TownIdNameBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.module.mine.business.MyBusinessActivity;
import com.liuhezhineng.ximengsifa.utils.PickerViewBuilder;
import com.liuhezhineng.ximengsifa.utils.UploadUtils;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 申请法律援助
 */
public class RFLegalAidActivity extends BaseBusinessActivity {

    // TODO: 2019/11/14  lishangnan
    @BindView(R.id.top_bar)
    QMUITopBar topBar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_id_card_num)
    EditText etIdCardNum;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.ll_sex_and_birthday)
    LinearLayout llSexAndBirthday;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_work_address)
    EditText etWorkAddress;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_home_address)
    EditText etHomeAddress;
    @BindView(R.id.et_home_addr)
    EditText etHomeAddr;
    @BindView(R.id.et_home_add)
    EditText etHomeAdd;
    @BindView(R.id.et_agent_name)
    EditText etAgentName;
    @BindView(R.id.et_agent_workunit)
    EditText etAgentWorkunit;
    @BindView(R.id.et_agent_residence)
    EditText etAgentResidence;
    @BindView(R.id.et_postal_Code)
    EditText etPostalCode;
    @BindView(R.id.et_postal_phone)
    EditText etPostalPhone;
    @BindView(R.id.ll_proxy)
    LinearLayout llProxy;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_ti)
    TextView etTi;
    @BindView(R.id.tv_file)
    TextView tvFile;
    @BindView(R.id.rv_annex)
    RecyclerView rvAnnex;
    @BindView(R.id.ll_annex)
    LinearLayout llAnnex;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_no)
    Button btnNo;
    @BindView(R.id.btn_updatayes)
    Button btnUpdataYes;
    @BindView(R.id.ll_btn)
    LinearLayout llBtn;
    @BindView(R.id.ll_birthday)
    LinearLayout llBirthday;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.ed_beiname)
    EditText edBeiname;
    @BindView(R.id.ed_bei_fname)
    EditText edBeiFname;
    @BindView(R.id.ed_bei_post)
    EditText edBeiPost;
    @BindView(R.id.ed_bei_arrdes)
    EditText edBeiArrdes;
    @BindView(R.id.ed_bei_arrdesnum)
    EditText edBeiArrdesnum;
    @BindView(R.id.ed_bei_nodata)
    TextView edBeiNodata;
    @BindView(R.id.et_behavior)
    EditText etBehavior;
    @BindView(R.id.et_behavior_jg)
    TextView etBehaviorJg;
    @BindView(R.id.et_behavior_qq)
    EditText etBehaviorQq;
    @BindView(R.id.ll_nodata)
    LinearLayout llNodata;
        //@+id/et_behavior_jg
    @OnClick({R.id.ll_sex, R.id.ll_birthday, R.id.tv_file, R.id.btn_updatayes,R.id.ll_nodata,R.id.et_behavior_jg,R.id.et_ti})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_ti:
                birthdayPickerView = new TimePickerBuilder(mActivity, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        etTi.setText(getTime(date));
                        //申请日期
                        oaReconsiderationApply.setApplyDate(getTime(date));
                    }
                })
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .isDialog(true)
                        .build();
                birthdayPickerView.show();
                break;
            case R.id.et_behavior_jg:
                ArrayList<TownIdNameBean> CenterList = new ArrayList<>();
                for (int i = 0; i <bodyBean.size() ; i++) {
                    TownIdNameBean  townIdNameBean=new TownIdNameBean();
                    townIdNameBean.setId(bodyBean.get(i).getId());
                    townIdNameBean.setName(bodyBean.get(i).getName());
                    CenterList.add(townIdNameBean);
                }
                OptionsPickerView<TownIdNameBean> aidCenterPicker;
                aidCenterPicker = new PickerViewBuilder(mActivity, new OnOptionsSelectListener() {
                   @Override
                   public void onOptionsSelect(int options1, int options2, int options3, View v) {
                       etBehaviorJg.setText(bodyBean.get(options1).getName());
                       // 请求地址
                       reconsiderationOfficeIdBean.setId(bodyBean.get(options1).getId());
                       reconsiderationOfficeIdBean.setName(bodyBean.get(options1).getName());
                       oaReconsiderationApply.setReconsiderationOfficeId(reconsiderationOfficeIdBean);
                   }
               }).build();
                aidCenterPicker.setPicker(CenterList);
                aidCenterPicker.show();
                break;
            case R.id.ll_sex:
                showPickerView(sexPickerView);
                break;
            case R.id.ll_birthday:
                birthdayPickerView.show();
                break;
            case R.id.ll_nodata:
                birthdayPickerView = new TimePickerBuilder(mActivity, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        edBeiNodata.setText(getTime(date));
                        oaReconsiderationApply.setIncidentDate(getTime(date));
                    }
                })
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .isDialog(true)
                        .build();
                birthdayPickerView.show();
                break;
            case R.id.btn_updatayes:
                if (!chenk()){
                    return;
                }
                commitRequest();
                break;
            case R.id.tv_file:
                UploadUtils.openFileManager(mActivity);
                break;
        }
    }
    protected void commitRequest() {
        LogUtils.logi("",new Gson().toJson(oaReconsiderationApply).toString());
        OkGo.<BaseBean<String>>post(NetConstant.GET_FUYI)
                .params("query", new Gson().toJson(oaReconsiderationApply))
                .execute(new DialogCallBack<BaseBean<String>>(RFLegalAidActivity.this, "提交中...") {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        ToastUtils.showLong("提交成功");
                        commitResult();
                        finishActivity();
                    }
                });
    }

    OaReconsiderationApply oaReconsiderationApply;

    private Boolean chenk() {
        //申請人名字
        if (TextUtils.isEmpty(etName.getText().toString().trim())){
            ToastUtils.showLong("请输入申请人姓名");
            return false;
        }
        oaReconsiderationApply.setApplyName(etName.getText().toString().trim());
        //生日
        if (TextUtils.isEmpty(etIdCardNum.getText().toString().trim())){
            ToastUtils.showLong("请输入申请人生日");
            return false;
        }
        oaReconsiderationApply.setApplyPapernum(etIdCardNum.getText().toString().trim());
        //电话
        if (TextUtils.isEmpty(etPhone.getText().toString().trim())){
            ToastUtils.showLong("请输入申请人电话");
            return false;
        }
        if (TextUtils.isEmpty(etPhone.getText().toString().trim())){
            ToastUtils.showLong("请输入申请人性别");
            return false;
        }
        oaReconsiderationApply.setApplyPhone(etPhone.getText().toString().trim());
        if (tvSex.getText().toString().trim().equals("男")){
            oaReconsiderationApply.setApplySex("1");
        }else {
            //性别
            oaReconsiderationApply.setApplySex("0");
        }
        //邮政编码
        if (TextUtils.isEmpty(etHomeAdd.getText().toString().trim())){
            ToastUtils.showLong("请输入申请人邮政编码");
            return false;
        }
        oaReconsiderationApply.setApplyZipCode(etHomeAdd.getText().toString().trim());
        //“applyPost”:””, // 申请人法定代表职务
        if (TextUtils.isEmpty(etHomeAddr.getText().toString().trim())){
            ToastUtils.showLong("申请人法定代表职务");
            return false;
        }
        oaReconsiderationApply.setApplyPost(etHomeAddr.getText().toString().trim());
        //“agentWorkUnit”:””, // 代理人工作单位
        if (TextUtils.isEmpty(etWorkAddress.getText().toString().trim())){
            ToastUtils.showLong("代理人工作单位");
            return false;
        }
        oaReconsiderationApply.setApplyWorkUnit(etWorkAddress.getText().toString().trim());
        //“applyAddress”:””, // 申请人地址//
        if (TextUtils.isEmpty(etAddress.getText().toString().trim())){
            ToastUtils.showLong("申请人地址");
            return false;
        }
        oaReconsiderationApply.setApplyAddress(etAddress.getText().toString().trim());
        //applyLegalName”:””, // 申请人法定代表
        if (TextUtils.isEmpty(etHomeAddress.getText().toString().trim())){
            ToastUtils.showLong("申请人法定代表");
            return false;
        }
        oaReconsiderationApply.setApplyLegalName(etHomeAddress.getText().toString().trim());
        if (Constant.REQUEST_USER == userType) {
        } else {
            //代理人信息
            //agentAddress”:””, // 代理人住址
            oaReconsiderationApply.setAgentAddress(etAgentResidence.getText().toString().trim());
            //agentAddress”:””, // 代理人名字
            oaReconsiderationApply.setAgentName(etAgentName.getText().toString().trim());
            //agentAddress”:””, // 代理人住址工作单位
            oaReconsiderationApply.setAgentWorkUnit(etAgentWorkunit.getText().toString().trim());
            //agentAddress”:””, // 代理人电话
            oaReconsiderationApply.setAgentPhone(etPostalPhone.getText().toString().trim());
            //agentAddress”:””, // 代理人邮编
            oaReconsiderationApply.setAgentZipCode(etPostalCode.getText().toString().trim());
        }
        //“respondentName”:””, // 被申请人姓名
        if (TextUtils.isEmpty(edBeiname.getText().toString().trim())){
            ToastUtils.showLong("请输入被申请人姓名");
            return false;
        }
        oaReconsiderationApply.setRespondentName(edBeiname.getText().toString().trim());
        //“respondentName”:””, //  // 被申请人法定代表
        if (TextUtils.isEmpty(edBeiFname.getText().toString().trim())){
            ToastUtils.showLong("请输入被申请人法定代表");
            return false;
        }
        oaReconsiderationApply.setRespondentLegalName(edBeiFname.getText().toString().trim());
        //// 被申请人职务
        if (TextUtils.isEmpty(edBeiPost.getText().toString().trim())){
            ToastUtils.showLong("请输入被申请人职务");
            return false;
        }
        oaReconsiderationApply.setRespondentPost(edBeiPost.getText().toString().trim());
        // 被申请人地址
        if (TextUtils.isEmpty(edBeiArrdes.getText().toString().trim())){
            ToastUtils.showLong("请输入被申请人地址");
            return false;
        }
        oaReconsiderationApply.setRespondentAddress(edBeiArrdes.getText().toString().trim());
        // 被申请人邮编
        if (TextUtils.isEmpty(edBeiArrdes.getText().toString().trim())){
            ToastUtils.showLong("请输入被申请人邮编");
            return false;
        }

        if (TextUtils.isEmpty(edBeiArrdesnum.getText().toString().trim())){
            ToastUtils.showLong("请输入被申请人邮编");
            return false;
        }

        oaReconsiderationApply.setRespondentZipCode(edBeiArrdesnum.getText().toString().trim());
        if (TextUtils.isEmpty(edBeiNodata.getText().toString().trim())){
            ToastUtils.showLong("请输入不服时间");
            return false;
        }
        oaReconsiderationApply.setIncidentDate(edBeiNodata.getText().toString().trim());
        // 行政行为
        if (TextUtils.isEmpty(etBehavior.getText().toString().trim())){
            ToastUtils.showLong("请输入行政行为");
            return false;
        }
        oaReconsiderationApply.setAdministrationBehavior(etBehavior.getText().toString().trim());
        //案件标题
        if (TextUtils.isEmpty(etTitle.getText().toString().trim())){
            ToastUtils.showLong("请输入案件标题");
            return false;
        }
        oaReconsiderationApply.setCaseTitle(etTitle.getText().toString().trim());
        //// 行政复议请求
        if (TextUtils.isEmpty(etBehaviorQq.getText().toString().trim())){
            ToastUtils.showLong("请输入行政复议请求");
            return false;
        }
        oaReconsiderationApply.setReconsiderationRequest(etBehaviorQq.getText().toString().trim());
        //  // 行政复议理由
        if (TextUtils.isEmpty(etContent.getText().toString().trim())){
            ToastUtils.showLong("请输入行政复议理由");
            return false;
        }
        //  // 行政复议机关
        if (TextUtils.isEmpty(etBehaviorJg.getText().toString().trim())){
            ToastUtils.showLong("请输入行政复议机关");
            return false;
        }
        //  // 申请日期
        if (TextUtils.isEmpty(etTi.getText().toString().trim())){
            ToastUtils.showLong("请输入案件申请日期");
            return false;
        }
        oaReconsiderationApply.setReconsiderationReason(etContent.getText().toString().trim());
        // 附件
        oaReconsiderationApply.setCaseFile(caseFile);
         return  true;
    }
    public static void actionStart(Context context) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, RFLegalAidActivity.class);
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
            Intent intent = new Intent(context, RFLegalAidActivity.class);
            intent.putExtra("userType", userType);
            context.startActivity(intent);
        } else {
            gotoLogin(context);
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_rfl;
    }
    private   OaReconsiderationApply.ReconsiderationOfficeIdBean reconsiderationOfficeIdBean;
    @Override
    protected void initView() {
        super.initView();
        initTopBar(topBar, "申请复议");
        findViewById(R.id.tv_file).setVisibility(View.VISIBLE);
        oaReconsiderationApply = new OaReconsiderationApply();
        reconsiderationOfficeIdBean = new OaReconsiderationApply.ReconsiderationOfficeIdBean();
    }
    @Override
    protected void initData() {
        super.initData();
        initPickerView();
        initPeoData();
    }
   private List<TownBean.BodyBean> bodyBean;
    private void initPeoData() {
        //获取案件城市地址
        Map<String,String> map=new HashMap<>();
        map.put("type","19");
        map.put("id","");
        map.put("isUser","0");
        OkGo.<TownBean>get(NetConstant.GET_TOWN).params("query",new Gson().toJson(map)).execute(new JsonCallback2<TownBean>() {
            @Override
            public void onSuccess(Response<TownBean> response) {
                ///api/200/730/10
               bodyBean = response.body().getBody();

            }
        });

    }

    private String caseType;

    private void initPickerView() {
        initSexPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvSex.setText(sexList.get(options1));
            }
        });
        initEthnicPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
            }
        });
        initBirthdayPicker(new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String birthday = getTime(date);
                tvBirthday.setText(birthday);
                oaReconsiderationApply.setApplyBirthday(birthday);
            }
        });
        initCountyPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Area cityBean = countyList.get(options1);
                initTownPickerView(new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    }
                }, cityBean.getId());
            }
        });
        initAgentTypePickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
            }
        });
        initMongolPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
            }
        });
        initCaseTypePicker(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                caseType = caseTypeList.get(options1).getValue();
            }
        });
        initAidCategoryPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
            }
        });
        initCaseNatureView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
            }
        });
    }

    @Override
    protected void loadDraft(String id, String procDefKey) {
//        Map<String, String> map = new HashMap<>(16);
//        map.put("id", id);
//        map.put("procDefKey", procDefKey);
//        String queryStr = new JSONObject(map).toString();
//        OkGo.<BaseBean<PageBean<RequestLawHelpBean>>>post(NetConstant.GET_DRAFT_DATA)
//                .params(NetConstant.QUERY, queryStr)
//                .execute(new DialogCallBack<BaseBean<PageBean<RequestLawHelpBean>>>(mActivity) {
//                    @Override
//                    public void onSuccess(Response<BaseBean<PageBean<RequestLawHelpBean>>> response) {
//                    }
//                });
    }

    private int userType;

    @Override
    protected void loadUserInfo() {
        userType = getIntent().getIntExtra("userType", -1);
        UserBean user = UserHelper.getUser();
        if (Constant.REQUEST_USER == userType) {
            etPhone.setText(user.getMobile());
            etPhone.setEnabled(false);
            if (user.getSex().equals("1")) {
                tvSex.setText("男");
            } else {
                tvSex.setText("女");
            }
            tvSex.setEnabled(false);
            etName.setText(user.getRealName());
            etName.setEnabled(false);
            etIdCardNum.setText(user.getPaperNum());
            etIdCardNum.setEnabled(false);
            tvBirthday.setText(user.getBirthday());
            oaReconsiderationApply.setApplyBirthday(user.getBirthday());
            tvBirthday.setEnabled(false);
        } else if (Constant.AGENT_USER == userType) {
            etAgentName.setText(user.getRealName());
            etPostalPhone.setText(user.getMobile());
            etAgentName.setEnabled(false);
            etPostalPhone.setEnabled(false);
        }
    }
    /**
     * 性别，民族等字典值传 value 而不是 label
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}

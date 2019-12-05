package com.liuhezhineng.ximengsifa.form;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AnnexAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.bean.form.VisitApply;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.utils.PickerViewBuilder;
import com.liuhezhineng.ximengsifa.utils.UploadUtils;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author iqing
 * @description 远程亲情探视帮教申请表
 */
public class RemoteVisitFormActivity extends BaseActivity {

    @BindView(R.id.et_request_user_name)
    EditText mEtRequestUserName;
    @BindView(R.id.et_contact_phone_number)
    EditText mEtContactPhoneNumber;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.et_native_place)
    EditText mEtNativePlace;
    @BindView(R.id.et_visitor_relations)
    EditText mEtVisitorRelations;
    @BindView(R.id.et_id_card)
    EditText mEtIdCard;
    @BindView(R.id.et_now_address)
    EditText mEtNowAddress;
    @BindView(R.id.et_work_unit)
    EditText mEtWorkUnit;
    @BindView(R.id.et_visitor_name)
    EditText mEtVisitorName;
    @BindView(R.id.tv_visitor_sex)
    TextView mTvVisitorSex;
    @BindView(R.id.et_visitor_native_place)
    EditText mEtVisitorNativePlace;
    @BindView(R.id.tv_supervision_place_type)
    TextView mTvSupervisionPlaceType;
    @BindView(R.id.tv_supervision_place)
    TextView mTvSupervisionPlace;
    @BindView(R.id.et_visitor_id_card)
    EditText mEtVisitorIdCard;
    @BindView(R.id.tv_judicial_bureau)
    TextView mTvJudicialBureau;
    @BindView(R.id.tv_start_date)
    TextView mTvStartDate;
    @BindView(R.id.tv_end_date)
    TextView mTvEndDate;
    @BindView(R.id.et_apply_reason)
    EditText mEtApplyReason;
    @BindView(R.id.et_agent_name)
    EditText mEtAgentName;
    @BindView(R.id.et_agent_phone_number)
    EditText mEtAGentPhoneNumber;
    Dialog mAgentDialog;
    ArrayList<TypeBean> supervisionPlaceTypeList = new ArrayList<>();
    OptionsPickerView<TypeBean> supervisionPlaceTypePicker;
    ArrayList<TypeBean> supervisionPlaceList = new ArrayList<>();
    OptionsPickerView<TypeBean> supervisionPlacePicker;
    ArrayList<Office> judicialList = new ArrayList<>();
    OptionsPickerView<Office> judicialPicker;
    private int timeFlag;
    private int sexFlag;
    private VisitApply mVisitApplyBean;

    public static void actionStart(Context context) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, RemoteVisitFormActivity.class);
            context.startActivity(intent);
        } else {
            gotoLogin(context);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remote_visit_form;
    }

    @Override
    protected void initView() {
        super.initView();

        initTopBar(R.string.remote_visitor_door);
        initAnnex();

        showAgentDialog();
    }

    void showAgentDialog() {
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.activity_legal_aid_door, null, false);
        ((QMUITopBar) dialogView.findViewById(R.id.top_bar)).setTitle(R.string.remote_visitor_door);
        dialogView.findViewById(R.id.tv_request_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserInfo();
                mAgentDialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.tv_proxy_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoteVisitFormActivity.this.findViewById(R.id.ll_proxy)
                        .setVisibility(View.VISIBLE);
                UserBean user = UserHelper.getUser();
                mEtAgentName.setText(user.getRealName());
                mEtAGentPhoneNumber.setText(user.getMobile());
                mAgentDialog.dismiss();
                mEtAgentName.setEnabled(false);
                mEtAGentPhoneNumber.setEnabled(false);
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
        mEtRequestUserName.setText(user.getRealName());
        mEtContactPhoneNumber.setText(user.getMobile());
        mTvSex.setText(user.getSexDesc());
        mEtIdCard.setText(user.getPaperNum());
    }

    @Override
    protected void initData() {
        super.initData();

        mVisitApplyBean = new VisitApply();
        initPicker();

        preLoadSupervisionPlaceType();

        preLoadJudicial();
    }

    private void initPicker() {
        initBirthdayPicker(new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (0x11 == timeFlag) {
                    mTvStartDate.setText(getTime(date));
                } else {
                    mTvEndDate.setText(getTime(date));
                }
            }
        });

        initSexPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (0x11 == sexFlag) {
                    mTvSex.setText(sexList.get(options1));
                } else {
                    mTvVisitorSex.setText(sexList.get(options1));
                }
            }
        });
    }

    private void preLoadSupervisionPlaceType() {
        Map<String, String> map = new HashMap<>(16);
        map.put(Constant.KEY, Constant.DictKey.SUPERVISION_PLACE);
        OkGo.<BaseBean<List<TypeBean>>>post(NetConstant.GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(NetConstant.QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        supervisionPlaceTypeList.clear();
                        List<TypeBean> list = response.body().getBody();
                        supervisionPlaceTypeList.addAll(list);
                        supervisionPlaceTypePicker = new PickerViewBuilder(mActivity,
                                new OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1,
                                                                int options2,
                                                                int options3,
                                                                View v) {
                                        mTvSupervisionPlaceType.setText(supervisionPlaceTypeList.get(options1).getLabel());
                                        mVisitApplyBean.setSupervisionPlace(supervisionPlaceTypeList.get(options1).getValue());

                                        loadSupervisionPlace(supervisionPlaceTypeList.get(options1).getValue());
                                    }
                                }).build();
                        supervisionPlaceTypePicker.setPicker(supervisionPlaceTypeList);
                    }
                });
    }

    private void preLoadJudicial() {
        OkGo.<BaseBean<List<Office>>>post(NetConstant.GET_ALL_JUDICIAL)
                .execute(new JsonCallback<BaseBean<List<Office>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<Office>>> response) {
                        judicialList.clear();
                        judicialList.addAll(response.body().getBody());
                        judicialPicker = new PickerViewBuilder(mActivity,
                                new OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        mTvJudicialBureau.setText(judicialList.get(options1).getName());
                                        mVisitApplyBean.setOffice(judicialList.get(options1));
                                    }
                                }).build();
                        judicialPicker.setPicker(judicialList);
                    }
                });
    }

    private void loadSupervisionPlace(String parentId) {
        Map<String, String> map = new HashMap<>(16);
        map.put(Constant.KEY, Constant.DictKey.SUPERVISION_PLACE);
        map.put(Constant.DictKey.PARENT_ID, parentId);
        OkGo.<BaseBean<List<TypeBean>>>post(NetConstant.GET_SUB_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(NetConstant.QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        supervisionPlaceList.clear();
                        supervisionPlaceList.addAll(response.body().getBody());
                        supervisionPlacePicker = new PickerViewBuilder(mActivity,
                                new OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        mTvSupervisionPlace.setText(supervisionPlaceList.get(options1).getLabel());
                                        mVisitApplyBean.setSupervisionPlace(supervisionPlaceList.get(options1).getValue());
                                        mVisitApplyBean.setSupervisionPlaceDesc(supervisionPlaceList.get(options1).getLabel());
                                    }
                                }).build();
                        supervisionPlacePicker.setPicker(supervisionPlaceList);
                    }
                });
    }

    @Override
    protected void initBirthdayPicker(OnTimeSelectListener listener) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTimeInMillis(System.currentTimeMillis());
        Calendar endDate = Calendar.getInstance();
        // 2222 year 2 month 2 day 2 hour 2 min 2 second
        // https://tool.chinaz.com/Tools/unixtime.aspx
        endDate.setTimeInMillis(7955085722000L);
        // 这里设置一下开始时间和结束时间 只能预约未来 TODO 还有时分秒没有控制好
        // Caused by: java.lang.IllegalArgumentException: startDate can't be later than endDate
        birthdayPickerView = new TimePickerBuilder(mActivity, listener)
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true)
                .setRangDate(startDate, endDate)
                .build();
    }

    @Override
    protected String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        return format.format(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == UploadUtils.ACTION_GET_CONTENT) {
            String path;
            Uri uri = data.getData();
            if (uri != null) {
                path = UploadUtils.getFilePathViaUri(mActivity, uri);
                uploadFile(path);
            }
        }
    }

    protected void uploadFile(final String path) {
        OkGo.<BaseBean<String>>post(NetConstant.UPLOAD_FILE)
                .params(NetConstant.FILE, new File(path))
                .execute(new DialogCallBack<BaseBean<String>>(mActivity, "附件上传中") {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        uploadSuccess(response.body().getBody());
                        ToastUtils.showShort("附件上传成功");
                    }
                });
    }

    protected void uploadSuccess(String path) {
        caseFile += "|" + path;
        // substring(begin, end) [begin, end) start 0.
        mAdapter.addData(path);
    }

    private void initAnnex() {
        RecyclerView mRvAnnex = findViewById(R.id.rv_annex);
        mAnnexList = new ArrayList<>();
        mAdapter = new AnnexAdapter(mActivity, mAnnexList);
        mRvAnnex.setAdapter(mAdapter);
    }

    protected AnnexAdapter mAdapter;
    protected ArrayList<String> mAnnexList;
    protected String caseFile = "";

    @Override
    @OnClick({R.id.tv_sex, R.id.tv_visitor_sex,
            R.id.tv_start_date, R.id.tv_end_date,
            R.id.tv_supervision_place_type, R.id.tv_supervision_place,
            R.id.tv_judicial_bureau,
            R.id.tv_file,
            R.id.btn_commit_request})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_file:
                UploadUtils.openFileManager(this);
                break;
            case R.id.btn_commit_request:
                if (checkInput()) {
                    commitForm();
                }
                break;
            case R.id.tv_sex:
                sexFlag = 0x11;
                showPickerView(sexPickerView);
                break;
            case R.id.tv_visitor_sex:
                sexFlag = 0x22;
                showPickerView(sexPickerView);
                break;
            case R.id.tv_start_date:
                timeFlag = 0x11;
                birthdayPickerView.show();
                break;
            case R.id.tv_end_date:
                timeFlag = 0x22;
                birthdayPickerView.show();
                break;
            case R.id.tv_supervision_place_type:
                showPickerView(supervisionPlaceTypePicker);
                break;
            case R.id.tv_supervision_place:
                if (TextUtils.isEmpty(mTvSupervisionPlaceType.getText())) {
                    ToastUtils.showLong(R.string.please_select_supervision_type);
                    return;
                }
                showPickerView(supervisionPlacePicker);
                break;
            case R.id.tv_judicial_bureau:
                showPickerView(judicialPicker);
                break;
            default:
                break;
        }
    }

    private boolean checkInput() {
        String requestUserName = mEtRequestUserName.getText().toString().trim();
        if (TextUtils.isEmpty(requestUserName)) {
            ToastUtils.showLong(R.string.request_user_nam_not_null);
            return false;
        }
        mVisitApplyBean.setApplyName(requestUserName);
        String requestUserPhoneNumber = mEtContactPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(requestUserPhoneNumber)) {
            ToastUtils.showLong("申请人联系手机号不能为空");
            return false;
        }
        mVisitApplyBean.setApplyPhone(requestUserPhoneNumber);
        String requestUserSex = mTvSex.getText().toString().trim();
        mVisitApplyBean.setApplySexDesc(requestUserSex);
        mVisitApplyBean.setApplySex(getSex(requestUserSex));
        String requestUserNativePlace = mEtNativePlace.getText().toString().trim();
        mVisitApplyBean.setApplyNativePlace(requestUserNativePlace);
        String visitorRelations = mEtVisitorRelations.getText().toString().trim();
        if (TextUtils.isEmpty(visitorRelations)) {
            ToastUtils.showLong("探访人关系不能为空");
            return false;
        }
        mVisitApplyBean.setRelation(visitorRelations);
        String requestUserIdCard = mEtIdCard.getText().toString().trim();
        if (TextUtils.isEmpty(requestUserIdCard)) {
            ToastUtils.showLong("申请人身份证号不能为空");
            return false;
        }
        mVisitApplyBean.setApplyIdCard(requestUserIdCard);
        String nowAddress = mEtNowAddress.getText().toString().trim();
        mVisitApplyBean.setApplyAddress(nowAddress);
        String workUnit = mEtWorkUnit.getText().toString().trim();
        mVisitApplyBean.setApplyWorkUnit(workUnit);
        String agentName = mEtAgentName.getText().toString().trim();
        mVisitApplyBean.setAgentName(agentName);
        String agentPhone = mEtAGentPhoneNumber.getText().toString().trim();
        mVisitApplyBean.setAgentPhone(agentPhone);
        String visitor = mEtVisitorName.getText().toString().trim();
        if (TextUtils.isEmpty(visitor)) {
            ToastUtils.showLong("探访人不能为空");
            return false;
        }
        mVisitApplyBean.setVisitorName(visitor);
        String visitorSex = mTvVisitorSex.getText().toString().trim();
        mVisitApplyBean.setVisitorSexDesc(visitorSex);
        mVisitApplyBean.setVisitorSex(getSex(visitorSex));
        String visitorNativePlace = mEtVisitorNativePlace.getText().toString().trim();
        mVisitApplyBean.setVisitorNativePlace(visitorNativePlace);
        // 监管场所
        String supervisionPlace = mTvSupervisionPlace.getText().toString().trim();
        if (TextUtils.isEmpty(supervisionPlace)) {
            ToastUtils.showLong("监管场所不能为空");
            return false;
        }
        String visitorIdCard = mEtVisitorIdCard.getText().toString().trim();
        mVisitApplyBean.setVisitorIdCard(visitorIdCard);
        // 司法局
        String judicial = mTvJudicialBureau.getText().toString().trim();
        if (TextUtils.isEmpty(judicial)) {
            ToastUtils.showLong("探访审批司法局不能为空");
            return false;
        }
        String startDate = mTvStartDate.getText().toString().trim();
        if (TextUtils.isEmpty(startDate)) {
            ToastUtils.showLong("探访申请时间区间的开始时间不能为空");
            return false;
        }
        mVisitApplyBean.setStartTime(startDate);
        String endDate = mTvEndDate.getText().toString().trim();
        if (TextUtils.isEmpty(endDate)) {
            ToastUtils.showLong("探访申请时间区间的结束时间不能为空");
            return false;
        }
        mVisitApplyBean.setEndTime(endDate);
        String requestReason = mEtApplyReason.getText().toString().trim();
        mVisitApplyBean.setApplyReason(requestReason);
        mVisitApplyBean.setFiles(caseFile);
        return true;
    }

    private void commitForm() {
        String queryStr = new Gson().toJson(mVisitApplyBean);
        OkGo.<BaseBean<String>>post(NetConstant.COMMIT_REMOTE_VISIT_FORM)
                .params(NetConstant.QUERY, queryStr)
                .execute(new DialogCallBack<BaseBean<String>>(mActivity, "提交中...") {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        ToastUtils.showLong(R.string.commit_request_successful);
                        MyRemoteVisitApplyListActivity.actionStart(mActivity, Constant.HAVE_DONE);
                        finishActivity();
                    }
                });
    }
}

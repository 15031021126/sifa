package com.liuhezhineng.ximengsifa.callback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liuhezhineng.ximengsifa.Config;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AudioAdapter;
import com.liuhezhineng.ximengsifa.audiorecord.MediaPlayerManager;
import com.liuhezhineng.ximengsifa.audiorecord.RecordBean;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.Act;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.fastlegal.FastRequestBean;
import com.liuhezhineng.ximengsifa.business.base.BaseBusinessActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.constant.NetConstant.FastLegal;
import com.liuhezhineng.ximengsifa.model.StringDialogCallback;
import com.liuhezhineng.ximengsifa.module.mine.business.MyBusinessActivity;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.unisound.client.SpeechConstants;
import com.unisound.client.SpeechUnderstander;
import com.unisound.client.SpeechUnderstanderListener;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 申请人申请成功后查看成功案件的详情页
 */
public class HaveDoneUpdataActivity extends BaseBusinessActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.tv_name)
    EditText mTvName;
    @BindView(R.id.tv_phone)
    EditText mTvPhone;
    @BindView(R.id.tv_case_category)
    TextView mTvCaseCategory;
    @BindView(R.id.tv_case_type)
    TextView mTvCaseType;
    @BindView(R.id.tv_case_county)
    TextView mTvCaseArea;
    @BindView(R.id.tv_title)
    EditText mTvTitle;
    @BindView(R.id.tv_case_content)
    EditText mTvCaseContent;
    @BindView(R.id.rv_audio)
    RecyclerView mRvAudio;
    ArrayList<RecordBean> mAudioList;
    AudioAdapter mAudioAdapter;
    @BindView(R.id.tv_staff_office)
    TextView mTvStaffOffice;
    @BindView(R.id.tv_case_town)
    TextView mTvCaseTown;
    @BindView(R.id.tv_address)
    EditText mTvAddress;
    @BindView(R.id.tv_case_involved_count)
    EditText mTvCaseInvolvedCount;
    @BindView(R.id.tv_actual_amount)
    EditText mTvActualAmount;
    @BindView(R.id.tv_is_transfer_penalty)
    TextView mTvIsTransferPenalty;
    @BindView(R.id.tv_is_Petition)
    TextView mTvIsPetition;
    @BindView(R.id.tv_is_affect_stability)
    TextView mTvIsAffectStability;
    @BindView(R.id.tv_case_rank)
    TextView mtVCaseRank;
    @BindView(R.id.ll_staff_office)
    LinearLayout mLlStaffOffice;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.ll_is_transfer_penalty)
    LinearLayout mLlIsTransferPenalty;
    @BindView(R.id.ll_is_petition)
    LinearLayout mLlIsPetition;
    @BindView(R.id.ll_is_affect_stability)
    LinearLayout mLlIsAffectStability;
    @BindView(R.id.ll_case_rank)
    LinearLayout mLlCaseRank;
    @BindView(R.id.tv_case_data)
    TextView tvCaseData;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    FastRequestBean mBean;
    @BindView(R.id.tv_voice_input)
    TextView mTvVoiceInput;
    @BindView(R.id.pb_volume)
    ProgressBar mPbVolume;
    @BindView(R.id.iv_voice_input)
    ImageView ivVoiceInput;

    public static void actionStart(Context context, BusinessBean bean) {
        Intent intent = new Intent(context, HaveDoneUpdataActivity.class);
        intent.putExtra(Constant.BUSINESS, bean);
        context.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaPlayerManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaPlayerManager.resume();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_have_done_quick_legal_aid;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayerManager.release();
    }

    @Override
    protected void initView() {
        super.initView();
        mAudioList = new ArrayList<>();
        mAudioAdapter = new AudioAdapter(this, mAudioList);
        mRvAudio.setAdapter(mAudioAdapter);
        mRvAudio.setLayoutManager(new LinearLayoutManager(this));
        mRvAudio.setHasFixedSize(true);
//
//        if (UserHelper.isIsNormalUser()) {
//            mLlStaffOffice.setVisibility(View.GONE);
//            mLlIsTransferPenalty.setVisibility(View.GONE);
//            mLlIsPetition.setVisibility(View.GONE);
//            mLlIsAffectStability.setVisibility(View.GONE);
//            mLlCaseRank.setVisibility(View.GONE);
//        } else {
//            mLlAddress.setVisibility(View.GONE);
//        }
        initSpeech();
    }
    private Act act;

    @Override
    protected void initData() {
        super.initData();
        mBean = new FastRequestBean();
        act = new Act();
        loadBusinessDetails();
        initPicker();
    }
    /**
     * 方法【initSpeechUnderstander】的总行数不要超过80行。
     * <p>
     * 单个方法的总行数不超过80行。 说明：包括方法签名、结束右大括号、方法内代码、注释、空行、回车及任何不可见字符的总行数不超过80行
     */
    private void initSpeechUnderstander() {
        understander = new SpeechUnderstander(this,
                Config.Unisound.appKey,
                Config.Unisound.secret);
        understander.setOption(SpeechConstants.ASR_SERVICE_MODE,
                SpeechConstants.ASR_SERVICE_MODE_NET);
        understander.setListener(new SpeechUnderstanderListener() {
            @Override
            public void onEvent(int i, int i1) {
                switch (i) {
                    case SpeechConstants.ASR_EVENT_ENGINE_INIT_DONE:
                        mTvVoiceInput.setText("语音初始化完成");
                        break;
                    case SpeechConstants.ASR_EVENT_RECORDING_PREPARED:
                        mTvVoiceInput.setText("语音输入就绪");
                        break;
                    case SpeechConstants.ASR_EVENT_RECORDING_START:
                        mTvVoiceInput.setText("开始语音输入");
                        break;
                    case SpeechConstants.ASR_EVENT_SPEECH_DETECTED:
                        mTvVoiceInput.setText("用户开始说话");
                        break;
                    case SpeechConstants.ASR_EVENT_RECORDING_STOP:
                        mTvVoiceInput.setText("停止语音输入");
                        break;
                    case SpeechConstants.ASR_EVENT_VAD_TIMEOUT:
                        mTvVoiceInput.setText("用户停止说话");
                        break;
                    case SpeechConstants.ASR_EVENT_END:
                        mTvVoiceInput.setText("在线识别结束");
                        break;
                    case SpeechConstants.ASR_EVENT_RECOGNITION_END:
                        mTvVoiceInput.setText("识别结束");
                        break;
                    case SpeechConstants.ASR_EVENT_VOLUMECHANGE:
                        int volume = (int) understander.getOption(SpeechConstants.GENERAL_UPDATE_VOLUME);
                        mPbVolume.setProgress(volume);
                        if (mPbVolume.getProgress() > 0) {
                            mPbVolume.setVisibility(View.VISIBLE);
                        } else {
                            mPbVolume.setVisibility(View.GONE);
                        }
                    default:
                        break;
                }
            }

            @Override
            public void onError(int type, final String errorMSG) {
                if (errorMSG != null) {
                    // 显示错误信息
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showLong(errorMSG);
                        }
                    });
                } else {
                    if ("".equals(mTvCaseContent.getText().toString())) {
                        ToastUtils.showLong("没有听到声音");
                    }
                }
            }

            @Override
            public void onResult(int type, String jsonResult) {
                switch (type) {
                    case SpeechConstants.ASR_RESULT_NET:
                        // 在线识别结果，通常onResult接口多次返回结果，保留识别结果组成完整的识别内容。
                        // 通常onResult接口多次返回结果，保留识别结果组成完整的识别内容。
                        if (jsonResult.contains("net_asr")) {
                            try {
                                JSONObject json = new JSONObject(jsonResult);
                                JSONArray jsonArray = json.getJSONArray("net_asr");
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String status = jsonObject.getString("result_type");
                                if ("partial".equals(status)) {
                                    mTvCaseContent.append((String) jsonObject.get("recognition_result"));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        understander.init(null);
    }
    private void showDataPickerView() {
        //时间选择器
        birthdayPickerView = new TimePickerBuilder(mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                tvCaseData.setText(format.format(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true)
                .build();
        birthdayPickerView.show();
    }
    private void initSpeech() {
        AndPermission.with(mActivity)
                .runtime()
                .permission(Permission.READ_PHONE_STATE,
                        Permission.RECORD_AUDIO,
                        Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        initSpeechUnderstander();
                        haveRecordingRight = true;
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        ToastUtils.showLong("若想使用语音识别、语音留言，请开启相关权限：录音、存储");
                        haveRecordingRight = false;
                    }
                })
                .start();
    }
    /**
     * 有无录音权限
     */
    private boolean haveRecordingRight;
    SpeechUnderstander understander;
    @OnClick({R.id.tv_case_data, R.id.tv_case_town, R.id.tv_case_county, R.id.tv_case_category, R.id.tv_case_type, R.id.tv_commit,R.id.iv_voice_input
    ,R.id.tv_case_rank,R.id.tv_is_transfer_penalty,R.id.tv_is_Petition,R.id.tv_is_affect_stability})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_case_rank:
                showPickerView(caseRankPickerView);
                break;
            case R.id.tv_is_transfer_penalty:
                yesOrNoFlag = IS_TRANSFER_PENALTY;
                showPickerView(yesOrNoPicker);
                break;
            case R.id.tv_is_Petition:
                yesOrNoFlag = IS_PETITION;
                showPickerView(yesOrNoPicker);
                break;
            case R.id.tv_is_affect_stability:
                yesOrNoFlag = IS_AFFECT_STABILITY;
                showPickerView(yesOrNoPicker);
                break;

            case R.id.iv_voice_input:
                if (haveRecordingRight) {
                    understander.start();
                } else {
                    ToastUtils.showLong("暂无语音识别需要的相关权限（录音，存储）。");
                }
                break;
            case R.id.tv_case_data:
                showDataPickerView();
                break;
            case R.id.tv_case_county:
                showPickerView(countyPickerView);
                break;
            case R.id.tv_case_category:
                showPickerView(actTypePickerView);
                break;
            case R.id.tv_case_type:
                if (!TextUtils.isEmpty(caseClassify)) {
                    showPickerView(fastCaseTypePickerView);
                } else {
                    ToastUtils.showLong("请先选择案件类别");
                }
                break;
            case R.id.tv_case_town:
                if (TextUtils.isEmpty(mTvCaseArea.getText().toString().trim())) {
                    ToastUtils.showLong("请先选择案发旗县");
                } else {
                    showPickerView(townPickerView);
                }
                break;
            case R.id.tv_commit:
                if (checkInput()) {
                    mBean.setAct(act);
                    mBean.setAccuserName(accuserName);
                    mBean.setAccuserPhone(accuserPhone);
                    mBean.setCaseClassify(caseClassify);
                    mBean.setCaseType(caseType);
                    mBean.setCaseCounty(caseCounty);
                    mBean.setCaseTitle(mtvTitle);
                    mBean.setCaseReason(mtvcasecontent);
                    mBean.setAccuserIdCard(UserHelper.getUser().getPaperNum());
                    mBean.setCaseAddress(mTvAddress.getText().toString().trim());
                    mBean.setCaseInvolvecount(mTvCaseInvolvedCount.getText().toString().trim());
                    mBean.setActualAmount(mTvActualAmount.getText().toString().trim());
                    // 区分人员字段：personnelType (String；字段长度：2；0:社会大众；1:工作人员)
                    mBean.setPersonnelType("0");
                    mBean.setCaseTime(tvCaseData.getText().toString().trim());
//                    if (mAudioList.size() > 0) {
//                        uploadAudio();
//                    } else {
                        commitFastLegal();
//                    }
                }
                break;
        }
    }
    private void commitFastLegal() {
        String queryStr = new Gson().toJson(mBean);
        Log.i(TAG, "commitFastLegal: " + queryStr.toString());
        OkGo.<String>post(NetConstant.COMMIT_FAST_AID_UPDATA)
                .params(NetConstant.QUERY, queryStr)
                .execute(new StringDialogCallback(mActivity, "修改中...") {
                    @Override
                    protected void responseSuccess(JSONObject object) {
                        ToastUtils.showLong("修改成功");
                        //需要刷新页面
                        MyBusinessActivity.actionStart(mActivity);
                        finishActivity();
                    }
                });
    }
    private String accuserName;
    private String accuserPhone;
    private String mtvAddress;
    private String mtvTitle;
    private String mtvcaseinvolvedcount;
    private String mtvactualamount;
    private String mtvcasecontent;

    private boolean checkInput() {
        accuserName = mTvName.getText().toString().trim();
        accuserPhone = mTvPhone.getText().toString().trim();
        mtvAddress = mTvAddress.getText().toString().trim();//案发地址
        mtvTitle = mTvTitle.getText().toString().trim();//案发标题
        mtvcaseinvolvedcount = mTvCaseInvolvedCount.getText().toString().trim();//案发人数
        mtvactualamount = mTvActualAmount.getText().toString().trim();//涉及金额
        mtvcasecontent = mTvCaseContent.getText().toString().trim();//说明不能理由
        // 这里巧妙的运用了短路运算符，前面判断了非空，避免了后面空指针
        if (TextUtils.isEmpty(accuserPhone)
                || accuserPhone.length() < Constant.PHONE_LENGHT) {
            ToastUtils.showLong("请输入正确的联系电话");
            return false;
        } else if (TextUtils.isEmpty(accuserName)) {
            ToastUtils.showLong("名字不能为空");
            return false;
        } else if (TextUtils.isEmpty(mtvAddress)) {
            ToastUtils.showLong("案件地址不能为空");
            return false;
        } else if (TextUtils.isEmpty(mtvTitle)) {
            ToastUtils.showLong("案件标题不能为空");
            return false;
        } else if (TextUtils.isEmpty(mtvcaseinvolvedcount)) {
            ToastUtils.showLong("案件人数不能为空");
            return false;
        } else if (TextUtils.isEmpty(mtvactualamount)) {
            ToastUtils.showLong("涉及金额不能为空");
            return false;
        } else if (TextUtils.isEmpty(mtvcasecontent)) {
            ToastUtils.showLong("补充说明不能为空");
            return false;
        } else {
            return true;
        }
    }
    private  String    yesOrNoFlag;
    public static final String IS_TRANSFER_PENALTY = "is_transfer_penalty";
    public static final String IS_PETITION = "is_petition";
    public static final String IS_AFFECT_STABILITY = "is_affect_stability";
    private void initPicker() {
        initCaseRankView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mtVCaseRank.setText(caseRankList.get(options1).getLabel());
                mBean.setCaseRank(caseRankList.get(options1).getValue());
            }
        });
        initYesOrNoPicker(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                TypeBean typeBean = yesOrNoList.get(options1);
                switch (yesOrNoFlag) {
                    case IS_TRANSFER_PENALTY:
                        mTvIsTransferPenalty.setText(typeBean.getLabel());
                        mBean.setIsTransferPenalty(typeBean.getValue());
                        Log.i(TAG, "commitFastLegal: " + typeBean.getValue());
                        break;
                    case IS_PETITION:
                        mTvIsPetition.setText(typeBean.getLabel());
                        mBean.setIsPetition(typeBean.getValue());
                        Log.i(TAG, "commitFastLegal: " + typeBean.getValue());
                        break;
                    case IS_AFFECT_STABILITY:
                        mTvIsAffectStability.setText(typeBean.getLabel());
                        mBean.setIsAffectStability(typeBean.getValue());
                        Log.i(TAG, "commitFastLegal: " + typeBean.getValue());
                        break;
                    default:
                        break;
                }
            }
        });
        initActTypePicker(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvCaseCategory.setText(actTypeList.get(options1).getLabel());
                caseClassify = actTypeList.get(options1).getValue();
                caseType = "";
                mTvCaseType.setText("");
                initFastCastTypePicker(new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        if (options1 < 0) {
                            ToastUtils.showLong("请等待数据加载完成,请选择地区");
                            return;
                        }
                        mTvCaseType.setText(fastCaseTypeList.get(options1).getLabel());
                        TypeBean bean = fastCaseTypeList.get(options1);
                        if (Constant.PICKER_NO_DATA.equals(bean.getValue())) {
                            ToastUtils.showLong(bean.getLabel());
                            return;
                        }
                        mTvCaseType.setText(bean.getLabel());
                        caseType = bean.getValue();
                    }
                }, actTypeList.get(options1).getValue());
            }
        });
        initCountyPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                final Area bean = countyList.get(options1);
                mTvCaseArea.setText(bean.getName());
                caseCounty.setId(bean.getId());
                caseCounty.setName(bean.getName());
                caseCounty.setParentId(bean.getParentId());
                caseCounty.setSort(bean.getSort());
                initTownPickerView(new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        mTvCaseTown.setText(townList.get(options1).getName());
                        mBean.setCaseTown(townList.get(options1));
                    }
                }, bean.getId());
            }
        });
    }
    protected void loadBusinessDetails() {
        OkGo.<UpdataBean>post(FastLegal.LOAD_FLOW_INFO).params(NetConstant.QUERY, getBusinessDataQueryJson()).execute(new JsonCallback2<UpdataBean>() {
            @Override
            public void onSuccess(Response<UpdataBean> response) {
                showDetails(response.body().getBody());
            }
        });
    }
    private String caseClassify;
    private String caseType;
    private Area caseCounty = new Area();
    private Area caseTown = new Area();
    private void showDetails(UpdataBean.BodyBean updatabean) {
        UpdataBean.BodyBean.BusinessDataBean data = updatabean.getBusinessData();
        act.setProcDefId(updatabean.getProcDefId());
        act.setProcDefKey(updatabean.getProcDefKey());
        act.setProcInsId(updatabean.getProcInsId());
        act.setTaskDefKey(updatabean.getTaskDefKey());
        act.setTaskId(updatabean.getTaskId());
        act.setTaskName(updatabean.getTaskName());

        mBean.setId(data.getId());
        caseClassify = data.getCaseClassify();
        caseType = data.getCaseType();
        caseCounty.setId(data.getCaseCounty().getId());
        caseCounty.setName(data.getCaseCounty().getName());
        caseCounty.setParentId(data.getCaseCounty().getParentId());
        caseCounty.setSort(data.getCaseCounty().getSort() + "");
        caseTown.setName(data.getCaseTown().getName());
        caseTown.setId(data.getCaseTown().getId());
        caseTown.setSort(data.getCaseTown().getSort() + "");
        caseTown.setParentId(data.getCaseTown().getParentId());
        mBean.setCaseTown(caseTown);
        //----------------------华丽的分割线---------------------

        mTvIsTransferPenalty.setText(data.getIsTransferPenaltyDesc());//是否民转刑倾向
        mTvIsPetition.setText(data.getIsPetitionDesc());//是否群体上访倾向
        mTvIsAffectStability.setText(data.getIsAffectStabilityDesc());//是否影响稳定倾向：
        mtVCaseRank.setText(data.getCaseRankDesc());//案情严重等级






        mTvName.setText(data.getAccuserName());
        mTvStaffOffice.setText(data.getStaffOffice().getName());
        mTvPhone.setText(data.getAccuserPhone());
        mTvCaseArea.setText(data.getCaseCounty().getName());
        mTvCaseTown.setText(data.getCaseTown().getName());
        mTvAddress.setText(data.getCaseAddress());
        mTvTitle.setText(data.getCaseTitle());
        mTvCaseInvolvedCount.setText(data.getCaseInvolvecount());
        mTvActualAmount.setText(data.getActualAmount());
        mTvCaseCategory.setText(data.getCaseClassifyDesc());
        mTvCaseType.setText(data.getCaseTypeDesc());
        mTvCaseContent.setText(data.getCaseReason());
        mTvIsTransferPenalty.setText(data.getIsTransferPenaltyDesc());
        mTvIsPetition.setText(data.getIsPetitionDesc());
        mTvIsAffectStability.setText(data.getIsAffectStabilityDesc());
        mtVCaseRank.setText(data.getCaseRankDesc());
        tvCaseData.setText(data.getCaseTime());

        String audioFilePath = data.getVoicePath();
        if (!TextUtils.isEmpty(audioFilePath)) {
            Type type = new TypeToken<ArrayList<RecordBean>>() {}.getType();
            mAudioList.addAll((Collection<? extends RecordBean>) new Gson().fromJson(audioFilePath, type));
            mAudioAdapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
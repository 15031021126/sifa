package com.liuhezhineng.ximengsifa.business.fastlegal;

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
import com.liuhezhineng.ximengsifa.Config;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AudioAdapter;
import com.liuhezhineng.ximengsifa.audiorecord.AudioRecordButton;
import com.liuhezhineng.ximengsifa.audiorecord.MediaPlayerManager;
import com.liuhezhineng.ximengsifa.audiorecord.RecordBean;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.fastlegal.FastRequestBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.business.base.BaseBusinessActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.StringDialogCallback;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 快速申请
 */
public class QuickLegalAidActivity extends BaseBusinessActivity implements AudioRecordButton.OnAudioFinishRecordListener {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_case_category)
    TextView mTvCaseCategory;
    @BindView(R.id.tv_case_type)
    TextView mTvCaseType;
    @BindView(R.id.tv_case_county)
    TextView mTvCaseArea;
    @BindView(R.id.tv_case_town)
    TextView mTvCaseTown;
    @BindView(R.id.et_address)
    TextView mEtAddress;
    @BindView(R.id.et_case_involve_count)
    EditText mEtCaseInvolveCount;
    @BindView(R.id.et_actual_amount)
    EditText mEtActualAmount;
    @BindView(R.id.et_title)
    EditText mEtTitle;
    @BindView(R.id.et_case_content)
    EditText mEtCaseContent;
    @BindView(R.id.tv_voice_input)
    TextView mTvVoiceInput;
    @BindView(R.id.pb_volume)
    ProgressBar mPbVolume;
    @BindView(R.id.tv_recorder)
    AudioRecordButton mTVRecorder;
    @BindView(R.id.rv_audio)
    RecyclerView mRvAudio;
    @BindView(R.id.iv_voice_input)
    ImageView mIvVoiceInput;

    FastRequestBean mBean;
    SpeechUnderstander understander;
    @BindView(R.id.et_data)
    TextView etData;
    @BindView(R.id.ll_case_data)
    LinearLayout llCaseData;
    private ArrayList<RecordBean> mAudioList;
    private AudioAdapter mAudioAdapter;
    private String accuserName;
    private String accuserPhone;
    private String caseClassify;
    private String caseType;
    private Area caseCounty = new Area();
    private String caseTitle;
    private String caseReason;
    private int index;

    /**
     * 有无录音权限
     */
    private boolean haveRecordingRight;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, QuickLegalAidActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_quick_legal_aid;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayerManager.release();
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(mTopBar, "社会公众人员报告案件");
        initRecyclerView();
        initSpeech();
    }

    private void initRecyclerView() {
        mAudioList = new ArrayList<>();
        mAudioAdapter = new AudioAdapter(this, mAudioList);
        mRvAudio.setAdapter(mAudioAdapter);
        mRvAudio.setLayoutManager(new LinearLayoutManager(this));
        mRvAudio.setHasFixedSize(true);
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
                        mTVRecorder.setVisibility(View.VISIBLE);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        ToastUtils.showLong("若想使用语音识别、语音留言，请开启相关权限：录音、存储");
                        haveRecordingRight = false;
                        mTVRecorder.setVisibility(View.GONE);
                    }
                })
                .start();
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
                        mTVRecorder.setUsing(false);
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
                mTVRecorder.setUsing(false);
                if (errorMSG != null) {
                    // 显示错误信息
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showLong(errorMSG);
                        }
                    });
                } else {
                    if ("".equals(mEtCaseContent.getText().toString())) {
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
                                    mEtCaseContent.append((String) jsonObject.get("recognition_result"));
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

    @Override
    protected void initData() {
        mBean = new FastRequestBean();
        super.initData();
        initPicker();
    }

    private void initPicker() {
        initActTypePicker(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvCaseCategory.setText(actTypeList.get(options1).getLabel());
                caseClassify = actTypeList.get(options1).getValue();

                caseType = "";
                mTvCaseType.setText("");

                initFastCastTypePicker(new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3,
                                                View v) {
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

    @Override
    protected void loadUserInfo() {
        UserBean user = UserHelper.getUser();
        mEtName.setText(user.getRealName());
        mEtPhone.setText(user.getMobile());
        Area county = user.getCounty();
        mTvCaseArea.setText(county.getName());
        caseCounty = county;
        if (!TextUtils.isEmpty(county.getId())) {
            initTownPickerView(new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    mTvCaseTown.setText(townList.get(options1).getName());
                    mBean.setCaseTown(townList.get(options1));
                }
            }, county.getId());
        }
        mTvCaseTown.setText(user.getTown().getName());
        mBean.setCaseTown(user.getTown());
    }

    @Override
    protected void setListener() {
        super.setListener();
        mTVRecorder.setOnAudioFinishRecordListener(this);
    }

    @Override
    @OnClick({R.id.iv_voice_input,
            R.id.tv_case_category, R.id.tv_case_type,
            R.id.tv_case_county, R.id.tv_case_town,R.id.ll_case_data,
            R.id.tv_commit})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.iv_voice_input:
                if (haveRecordingRight) {
                    understander.start();
                    mTVRecorder.setUsing(true);
                } else {
                    ToastUtils.showLong("暂无语音识别需要的相关权限（录音，存储）。");
                }
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
            case R.id.tv_case_county:
                showPickerView(countyPickerView);
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
                    mBean.setAccuserName(accuserName);
                    mBean.setAccuserPhone(accuserPhone);
                    mBean.setCaseClassify(caseClassify);
                    mBean.setCaseType(caseType);
                    mBean.setCaseCounty(caseCounty);
                    mBean.setCaseTitle(caseTitle);
                    mBean.setCaseReason(caseReason);
                    mBean.setAccuserIdCard(UserHelper.getUser().getPaperNum());
                    mBean.setCaseAddress(mEtAddress.getText().toString().trim());
                    mBean.setCaseInvolvecount(mEtCaseInvolveCount.getText().toString().trim());
                    mBean.setActualAmount(mEtActualAmount.getText().toString().trim());
                    // 区分人员字段：personnelType (String；字段长度：2；0:社会大众；1:工作人员)
                    mBean.setPersonnelType("0");
                    mBean.setCaseTime(etData.getText().toString().trim());
                    if (mAudioList.size() > 0) {
                        uploadAudio();
                    } else {
                        commitFastLegal();
                    }
                }
                break;

            case  R.id.ll_case_data:
                showDataPickerView();
                break;
            default:
                break;
        }
    }
    private void showDataPickerView() {
        //时间选择器
        birthdayPickerView = new TimePickerBuilder(mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                etData.setText(format.format(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true)
                .build();
        birthdayPickerView.show();
    }
    String  casedata;
    private boolean checkInput() {
        accuserName = mEtName.getText().toString().trim();
        accuserPhone = mEtPhone.getText().toString().trim();
        casedata= etData.getText().toString().trim();
        caseTitle = mEtTitle.getText().toString().trim();
        caseReason = mEtCaseContent.getText().toString().trim();

        String caseArea = mTvCaseArea.getText().toString().trim();

        // 这里巧妙的运用了短路运算符，前面判断了非空，避免了后面空指针
        if (TextUtils.isEmpty(accuserPhone)
                || accuserPhone.length() < Constant.PHONE_LENGHT) {
            ToastUtils.showLong("请输入正确的联系电话");
            return false;
        } else if (TextUtils.isEmpty(caseClassify)) {
            ToastUtils.showLong("案件类别不能为空");
            return false;
        } else if (TextUtils.isEmpty(caseType)) {
            ToastUtils.showLong("案件类型不能为空");
            return false;
        } else if (caseCounty != null
                && TextUtils.isEmpty(caseCounty.getId())
                && TextUtils.isEmpty(caseArea)) {
            ToastUtils.showLong("案发地区不能为空");
            return false;
        }else  if (TextUtils.isEmpty(casedata)){
            ToastUtils.showLong("案发时间不能为空");
            return false;
        }

        else {
            return true;
        }
    }

    private void commitFastLegal() {
        String queryStr = new Gson().toJson(mBean);
        Log.i(TAG, "commitFastLegal: " + queryStr.toString());
        OkGo.<String>post(NetConstant.COMMIT_FAST_AID)
                .params(NetConstant.QUERY, queryStr)
                .execute(new StringDialogCallback(mActivity, "快速登记中...") {
                    @Override
                    protected void responseSuccess(JSONObject object) {
                        ToastUtils.showLong("快速申请成功");
                        commitResult();
                        finishActivity();
                    }
                });
    }

    private void uploadAudio() {
        if (index >= mAudioList.size()) {
            mBean.setVoicePath(new Gson().toJson(mAudioList));
            commitFastLegal();
        } else {
            OkGo.<BaseBean<String>>post(NetConstant.UPLOAD_FILE)
                    .params(NetConstant.FILE, new File(mAudioList.get(index).getFilePath()))
                    .execute(new DialogCallBack<BaseBean<String>>(mActivity, "语音文件上传中...") {
                        @Override
                        public void onSuccess(Response<BaseBean<String>> response) {
                            mAudioList.get(index).setFilePath(response.body().getBody());
                            index++;
                            uploadAudio();
                        }
                    });
        }
    }

    @Override
    public void onFinish(float seconds, String filePath) {
        RecordBean recordBean = new RecordBean(seconds, filePath);
        mAudioList.add(recordBean);
        mAudioAdapter.notifyDataSetChanged();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

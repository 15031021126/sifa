package com.liuhezhineng.ximengsifa.video;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.video.RectificationPersonnelBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RectificationPersonnelActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.sp_rectification_personnel)
    Spinner mSpRectificationPersonnel;
    @BindView(R.id.tv_person_profile)
    TextView mTvPersonProfile;
    @BindView(R.id.tv_start_recording)
    TextView mTvStartRecording;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.et_id_card)
    EditText mEtIdCard;
    @BindView(R.id.ll_person)
    LinearLayout mLlPerson;
    @BindView(R.id.ll_person_profile)
    LinearLayout mLlPersonProfile;
    @BindView(R.id.tv_history)
    TextView mTvHistory;

    String idCard;

    ArrayAdapter mAdapter;
    List<RectificationPersonnelBean> mList;
    List<String> mNameList;
    RectificationPersonnelBean mBean;
    private static final int VIDEO_CAPTURE = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rectification_personnel;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(mTopBar, R.string.rectification_personnel_analyze);
        initSpinner();
    }

    private void initSpinner() {
        mList = new ArrayList<>();
        mNameList = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mNameList);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpRectificationPersonnel.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        getRectificationPerson();

        String s = mEtIdCard.getText().toString();
        if (s.length() == 18) {
            idCard = s.toString();
            Map<String, String> map = new HashMap<>(16);
            map.put("idCard", idCard);
            OkGo.<BaseBean<RectificationPersonnelBean>>post(NetConstant.Video.GET_USER_INFO_VIA_ID_CARD)
                    .params(Constant.QUERY, new Gson().toJson(map))
                    .execute(new JsonCallback<BaseBean<RectificationPersonnelBean>>() {
                        @Override
                        public void onSuccess(Response<BaseBean<RectificationPersonnelBean>> response) {
                            RectificationPersonnelBean bean = response.body().getBody();
                            String profileStr = "姓名：" + bean.getName() + "\n"
                                    + "性别：" + ("1".equals(bean.getSex()) ? "男" : "女") + "\n"
                                    + "年龄：" + (TextUtils.isEmpty(bean.getAge()) ? "未知" : bean.getAge()) + "\n"
                                    + "所在司法所：" + bean.getOffice().getName() + "\n"
                                    + "罪名：" + bean.getAccusation() + "\n"
                                    + "主刑：" + bean.getMainPenaltyDesc() + "\n"
                                    + "矫正类别：" + bean.getCorrectTypeDesc() + "\n"
                                    + "负责人：" + bean.getResponsibilityName() + "\n"
                                    + "矫正状态：" + bean.getCorrectStatusDesc() + "\n"
                                    + "矫正等级：" + bean.getCorrectLevel();
                            idCardValid = true;
                            mTvPersonProfile.setText(profileStr);
                            mLlPersonProfile.setVisibility(View.VISIBLE);
                            mLlPerson.setVisibility(View.VISIBLE);
                            mTvStartRecording.setBackgroundColor(getResources().getColor(R.color.blue));
                        }
                    });
        } else {
            idCardValid = false;
            mTvPersonProfile.setText("");
            mLlPersonProfile.setVisibility(View.GONE);
            mLlPerson.setVisibility(View.GONE);
            mTvStartRecording.setBackgroundColor(getResources().getColor(R.color.gray));
        }
    }

    private void getRectificationPerson() {
        Map<String, String> map = new HashMap<>(16);
        map.put(Constant.PAGE_NO, "1");
        map.put(Constant.PAGE_SIZE, String.valueOf(10));
        OkGo.<BaseBean<PageBean<RectificationPersonnelBean>>>post(NetConstant.Video.GET_RECTIFICATION_PERSONNEL)
                .params(Constant.QUERY, new Gson().toJson(map))
                .execute(new JsonCallback<BaseBean<PageBean<RectificationPersonnelBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<RectificationPersonnelBean>>> response) {
                        mList.addAll(response.body().getBody().getList());
                        for (RectificationPersonnelBean bean : mList) {
                            mNameList.add(bean.getName());
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void setListener() {
        super.setListener();
        setIdCardChangeListener();
//        setSpinnerItemSelectedListener();
    }

    private boolean idCardValid;

    private void setIdCardChangeListener() {
        mEtIdCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 18) {
                    idCard = s.toString();
                    Map<String, String> map = new HashMap<>(16);
                    map.put("idCard", idCard);
                    OkGo.<BaseBean<RectificationPersonnelBean>>post(NetConstant.Video.GET_USER_INFO_VIA_ID_CARD)
                            .params(Constant.QUERY, new Gson().toJson(map))
                            .execute(new JsonCallback<BaseBean<RectificationPersonnelBean>>() {
                                @Override
                                public void onSuccess(Response<BaseBean<RectificationPersonnelBean>> response) {
                                    RectificationPersonnelBean bean = response.body().getBody();
                                    String profileStr = "姓名：" + bean.getName() + "\n"
                                            + "性别：" + ("1".equals(bean.getSex()) ? "男" : "女") + "\n"
                                            + "年龄：" + (TextUtils.isEmpty(bean.getAge()) ? "未知" : bean.getAge()) + "\n"
                                            + "所在司法所：" + bean.getOffice().getName() + "\n"
                                            + "罪名：" + bean.getAccusation() + "\n"
                                            + "主刑：" + bean.getMainPenaltyDesc() + "\n"
                                            + "矫正类别：" + bean.getCorrectTypeDesc() + "\n"
                                            + "负责人：" + bean.getResponsibilityName() + "\n"
                                            + "矫正状态：" + bean.getCorrectStatusDesc() + "\n"
                                            + "矫正等级：" + bean.getCorrectLevel();
                                    idCardValid = true;
                                    mTvPersonProfile.setText(profileStr);
                                    mLlPersonProfile.setVisibility(View.VISIBLE);
                                    mTvStartRecording.setBackgroundColor(getResources().getColor(R.color.blue));
                                }
                            });
                } else {
                    idCardValid = false;
                    mTvPersonProfile.setText("");
                    mLlPersonProfile.setVisibility(View.GONE);
                    mTvStartRecording.setBackgroundColor(getResources().getColor(R.color.gray));
                }
            }
        });
    }

    private void setSpinnerItemSelectedListener() {
        mSpRectificationPersonnel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RectificationPersonnelBean bean = mList.get(position);
                mBean = bean;
                String profileStr = "姓名：" + bean.getName() + "\n"
                        + "性别：" + ("1".equals(bean.getSex()) ? "男" : "女") + "\n"
                        + "年龄：" + (TextUtils.isEmpty(bean.getAge()) ? "未知" : bean.getAge()) + "\n"
                        + "所在司法所：" + bean.getOffice().getName() + "\n"
                        + "罪名：" + bean.getAccusation() + "\n"
                        + "主刑：" + bean.getMainPenaltyDesc() + "\n"
                        + "矫正类别：" + bean.getCorrectTypeDesc() + "\n"
                        + "负责人：" + bean.getResponsibilityName() + "\n"
                        + "矫正状态：" + bean.getCorrectStatusDesc() + "\n"
                        + "矫正等级：" + bean.getCorrectLevel();
                mTvPersonProfile.setText(profileStr);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.tv_start_recording, R.id.tv_history})
    @Override
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_start_recording:
                if (idCardValid) {
//                    RecordVideoActivity.actionStart(this);
                    AndPermission.with(this)
                            .runtime()
                            .permission(Permission.CAMERA,
                                    Permission.WRITE_EXTERNAL_STORAGE,
                                    Permission.READ_EXTERNAL_STORAGE)
                            .onGranted(new Action<List<String>>() {
                                @Override
                                public void onAction(List<String> data) {
                                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                                    //好使
                                    intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 10485760L);
                                    intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 60);
                                    startActivityForResult(intent, VIDEO_CAPTURE);
                                }
                            }).start();
                } else {
                    ToastUtils.showShort(R.string.please_enter_correct_id_card_num);
                }
                break;
            case R.id.tv_history:
                AnalyzeResultTableActivity.actionStart(this, idCard);
                break;
            default:
                break;
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == VIDEO_CAPTURE) {
            Uri videoUri = data.getData();
            File file = new File(getImagePath(videoUri, null));
            if (file.exists()) {
                OkGo.<BaseBean<String>>post(NetConstant.UPLOAD_FILE)
                        .params(NetConstant.FILE, file)
                        .execute(new DialogCallBack<BaseBean<String>>(this) {
                            @Override
                            public void onSuccess(Response<BaseBean<String>> response) {
                                mTvState.setText(R.string.video_upload_success);

                                Map<String, String> map = new HashMap<>(16);
                                map.put("idCard", idCard);
//                                map.put("idCard", mBean.getIdCard());
                                map.put("videoPath", response.body().getBody());
                                OkGo.<BaseBean>post(NetConstant.Video.UPLOAD_VIDEO_ANALYZE)
                                        .params(NetConstant.QUERY, new Gson().toJson(map))
                                        .execute(new JsonCallback<BaseBean>() {
                                            @Override
                                            public void onSuccess(Response<BaseBean> response) {
                                                mTvState.setText(R.string.personnel_analyze_success);
                                                Intent intent = new Intent(RectificationPersonnelActivity.this, AnalyzeResultActivity.class);
                                                intent.putExtra("person_info", mTvPersonProfile.getText().toString().trim());
                                                startActivity(intent);
                                                mTvState.setText("");
                                            }
                                        });
                            }

                            @Override
                            public void onFinish() {
                                super.onFinish();
                                mTvState.setText(R.string.personnel_analysing);
                            }

                            @Override
                            public void onStart(Request<BaseBean<String>, ? extends Request> request) {
                                mTvState.setText(R.string.video_uploading);
                            }
                        });
            }
        }
        if (resultCode == RESULT_OK && requestCode == RecordVideoActivity.VIDEO_CODE) {
            File file = new File(Constant.Video.FILE_PATH, Constant.Video.FILE_NAME);
            if (file.exists()) {
                OkGo.<BaseBean<String>>post(NetConstant.UPLOAD_FILE)
                        .params(NetConstant.FILE, file)
                        .execute(new DialogCallBack<BaseBean<String>>(this) {
                            @Override
                            public void onSuccess(Response<BaseBean<String>> response) {
                                mTvState.setText(R.string.video_upload_success);

                                Map<String, String> map = new HashMap<>(16);
                                map.put("idCard", idCard);
//                                map.put("idCard", mBean.getIdCard());
                                map.put("videoPath", response.body().getBody());
                                OkGo.<BaseBean>post(NetConstant.Video.UPLOAD_VIDEO_ANALYZE)
                                        .params(NetConstant.QUERY, new Gson().toJson(map))
                                        .execute(new JsonCallback<BaseBean>() {
                                            @Override
                                            public void onSuccess(Response<BaseBean> response) {
                                                mTvState.setText(R.string.personnel_analyze_success);
                                                Intent intent = new Intent(RectificationPersonnelActivity.this, AnalyzeResultActivity.class);
                                                intent.putExtra("person_info", mTvPersonProfile.getText().toString().trim());
                                                startActivity(intent);
                                                mTvState.setText("");
                                            }
                                        });
                            }

                            @Override
                            public void onFinish() {
                                super.onFinish();
                                mTvState.setText(R.string.personnel_analysing);
                            }

                            @Override
                            public void onStart(Request<BaseBean<String>, ? extends Request> request) {
                                mTvState.setText(R.string.video_uploading);
                            }
                        });
            }
        }
    }
}

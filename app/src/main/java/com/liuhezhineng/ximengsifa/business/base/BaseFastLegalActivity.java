package com.liuhezhineng.ximengsifa.business.base;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AudioAdapter;
import com.liuhezhineng.ximengsifa.audiorecord.MediaPlayerManager;
import com.liuhezhineng.ximengsifa.audiorecord.RecordBean;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessDetailsWrapper;
import com.liuhezhineng.ximengsifa.bean.bussiness.fastlegal.FastFlowBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.constant.NetConstant.FastLegal;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/7/13
 */

public abstract class BaseFastLegalActivity extends BaseBusinessActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.tv_accept_people)
    TextView mTvAcceptPeople;
    @BindView(R.id.tv_accept_people_no)
    TextView mTvAcceptPeopleNo;
    @BindView(R.id.tv_case_no)
    TextView mTvCaseNo;
    @BindView(R.id.et_name)
    TextView mEtName;
    @BindView(R.id.tv_ethnic)
    TextView mTvEthnic;
    @BindView(R.id.et_id_card_num)
    TextView mEtIdCardNum;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;
    @BindView(R.id.et_phone)
    TextView mEtPhone;
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
    EditText mTvCaseCount;
    @BindView(R.id.tv_accept_way)
    TextView mTvAcceptWay;
    @BindView(R.id.tv_case_money)
    TextView mTvCaseMoney;
    @BindView(R.id.tv_case_level)
    TextView mTvCaseLevel;
    @BindView(R.id.tv_undertaker_organize)
    TextView mTvUndertakerOrganize;

    @BindView(R.id.rv_audio)
    RecyclerView mRvAudio;
    private ArrayList<RecordBean> mAudioList;
    private AudioAdapter mAudioAdapter;

    @Override
    protected void initView() {
        super.initView();

        mTvCaseTitle.setEnabled(false);
        mTvCaseContent.setEnabled(false);
        mTvCaseCount.setEnabled(false);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mAudioList = new ArrayList<>();
        mAudioAdapter = new AudioAdapter(this, mAudioList);
        mRvAudio.setAdapter(mAudioAdapter);
        mRvAudio.setLayoutManager(new LinearLayoutManager(this));
        mRvAudio.setHasFixedSize(true);
    }

    @Override
    protected void initData() {
        super.initData();
        mBaseBusinessBean = new FastFlowBean();
        loadBusinessDetails();
    }

    protected void loadBusinessDetails() {
        OkGo.<BaseBean<BusinessDetailsWrapper<FastFlowBean>>>post(
                FastLegal.LOAD_FLOW_INFO)
                .params(NetConstant.QUERY, getBusinessDataQueryJson())
                .execute(new DialogCallBack<BaseBean<BusinessDetailsWrapper<FastFlowBean>>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseBean<BusinessDetailsWrapper<FastFlowBean>>> response) {

                        BusinessDetailsWrapper<FastFlowBean> wrapper = response.body().getBody();

                        FastFlowBean data = wrapper.getBusinessData();
                        mBaseBusinessBean = data;

                        showDetails(data);
                    }
                });
    }

    protected void showDetails(FastFlowBean data) {

        String audioFilePath = data.getVoicePath();
        if (!TextUtils.isEmpty(audioFilePath)) {
            Type type = new TypeToken<ArrayList<RecordBean>>() {
            }.getType();
            try {
                List<RecordBean> audioList = new Gson().fromJson(audioFilePath, type);
                mAudioList.addAll(audioList);
                mAudioAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                Log.i(TAG, "audio json: " + e.getMessage());
            }
        }

        mTvAcceptPeople.setText(data.getAcceptManName());
        mTvAcceptPeopleNo.setText(data.getAcceptManCode());
        mTvCaseNo.setText(data.getCaseAcceptCode());

        mEtName.setText(data.getAccuserName());
        mTvSex.setText(data.getAccuserSexDesc());
        mTvEthnic.setText(data.getAccuserEthnicDesc());
        mTvBirthday.setText(data.getAccuserBirthday());
        mEtPhone.setText(data.getAccuserPhone());
        mEtIdCardNum.setText(data.getAccuserIdCard());
        Area county = data.getAccuserCounty();
        if (county != null && !TextUtils.isEmpty(county.getName())) {
            mTvCounty.setText(county.getName());
        }
        Area town = data.getAccuserTown();
        if (town != null && !TextUtils.isEmpty(town.getName())) {
            mTvTown.setText(town.getName());
        }

        mTvCaseTitle.setText(data.getCaseTitle());
        mTvCaseCategory.setText(data.getCaseClassifyDesc());
        mTvCaseType.setText(data.getCaseTypeDesc());
        Area caseCounty = data.getCaseCounty();
        if (caseCounty != null && !TextUtils.isEmpty(caseCounty.getName())) {
            mTvCaseCounty.setText(caseCounty.getName());
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

        setCaseFile(data.getCaseFile());
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
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayerManager.release();
    }
}

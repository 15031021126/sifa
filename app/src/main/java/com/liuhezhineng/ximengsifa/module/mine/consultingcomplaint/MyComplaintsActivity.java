package com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AnnexAdapter;
import com.liuhezhineng.ximengsifa.adapter.AnnexDiffCallback;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.advisory.ComplaintsRequestBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.DialogStringCallBack;
import com.liuhezhineng.ximengsifa.utils.UploadUtils;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 我要投诉
 */
public class MyComplaintsActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.tv_county)
    TextView mTvCounty;
    @BindView(R.id.et_no_name)
    EditText mEtNoName;
    @BindView(R.id.et_no_number)
    EditText mEtNoNumber;
    @BindView(R.id.tv_no_county)
    TextView mTvNoCounty;
    @BindView(R.id.tv_no_town)
    TextView mTvNoTown;
    @BindView(R.id.tv_organization)
    TextView mTvOrganization;
    @BindView(R.id.et_title)
    EditText mEtTitle;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.cb_anonymous)
    CheckBox mCbAnonymous;
    @BindView(R.id.tv_class_worker)
    TextView mTvClassWorker;
    @BindView(R.id.tv_such_complaints)
    TextView mTvSuchComplaints;
    @BindView(R.id.tv_type)
    TextView mTvType;

    private boolean flag;
    private ComplaintsRequestBean mBean;
    private String sex;
    private Area area = new Area();
    private String remarks;
    private Area noarea = new Area();
    private Area notown = new Area();
    private Office organization = new Office();
    private String nonumber;
    private String title;
    private String suchComplaints;
    private String classWorker;
    private String content;

    private String caseFile = "";
    protected AnnexAdapter mAdapter;
    protected ArrayList<String> mAnnexList;

    private void initAnnex() {
        RecyclerView mRvAnnex = findViewById(R.id.rv_annex);
        mAnnexList = new ArrayList<>();
        mAdapter = new AnnexAdapter(mActivity, mAnnexList);
        mRvAnnex.setAdapter(mAdapter);
    }

    public static void actionStart(Context context) {
        if (!UserHelper.isIsLogin()) {
            gotoLogin(context);
        } else {
            Intent intent = new Intent(context, MyComplaintsActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_complaints;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(mTopBar, "我要投诉");
        initAnnex();

        mTvSex.setFocusable(true);
        mTvSex.requestFocus();

        UserBean user = UserHelper.getUser();
        mTvName.setText(user.getRealName());
        mTvSex.setText(user.getSexDesc());
        mTvCounty.setText(user.getCounty().getName());

        sex = user.getSex();
        area = user.getCounty();
    }

    @Override
    protected void initData() {
        super.initData();
        mBean = new ComplaintsRequestBean();
        initPicker();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == UploadUtils.ACTION_GET_CONTENT) {
            Uri uri = data.getData();
            if (uri != null) {
                String path = UploadUtils.getFilePathViaUri(mActivity, uri);
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
        // 服务器定义的规则，[annexPath]|[annexPath]...
        caseFile += "|" + path;
        mAnnexList.add(path);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                new AnnexDiffCallback(mAdapter.getData(), mAnnexList));
        diffResult.dispatchUpdatesTo(mAdapter);
        mAdapter.setData(mAnnexList);
    }

    @Override
    @OnClick({R.id.tv_sex, R.id.tv_county, R.id.tv_no_county, R.id.tv_no_town,
            R.id.tv_organization, R.id.btn_yes, R.id.tv_such_complaints, R.id.tv_class_worker,
            R.id.tv_file, R.id.tv_type})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        flag = true;
        switch (view.getId()) {
            case R.id.tv_type:
                showPickerView(consultingComplaintTypeView);
                break;
            case R.id.tv_file:
                UploadUtils.openFileManager(mActivity);
                break;
            case R.id.tv_such_complaints:
                showPickerView(suchComplaintsPickerView);
                break;
            case R.id.tv_class_worker:
                showPickerView(classWorkerPickerView);
                break;
            case R.id.tv_sex:
                showPickerView(sexPickerView);
                break;
            case R.id.tv_county:
                showPickerView(countyPickerView);
                break;
            case R.id.tv_no_county:
                flag = false;
                showPickerView(countyPickerView);
                break;
            case R.id.tv_no_town:
                flag = false;
                if (TextUtils.isEmpty(noarea.getId())) {
                    ToastUtils.showLong("请先选择旗县");
                } else {
                    showPickerView(townPickerView);
                }
                break;
            case R.id.tv_organization:
                if (TextUtils.isEmpty(noarea.getId())) {
                    ToastUtils.showLong("请先选择旗县");
                } else {
                    showPickerView(officePickerView);
                }
                break;
            case R.id.btn_yes:
                if (checkInput()) {
                    commitComplaints();
                }
                break;
            default:
                break;
        }
    }

    private boolean checkInput() {

        sex = mTvSex.getText().toString().trim();
        title = mEtTitle.getText().toString().trim();
        suchComplaints = mTvSuchComplaints.getText().toString().trim();
        content = mEtContent.getText().toString().trim();

        remarks = mEtNoName.getText().toString().trim();
        classWorker = mTvClassWorker.getText().toString().trim();
        nonumber = mEtNoNumber.getText().toString().trim();

        if (TextUtils.isEmpty(sex)) {
            ToastUtils.showLong("性别不能为空");
            return false;
        } else if (TextUtils.isEmpty(area.getId())) {
            ToastUtils.showLong("投诉人旗县不能为空");
            return false;
        } else if (TextUtils.isEmpty(organization.getId())) {
            ToastUtils.showLong("被投诉人所属机构不能为空");
            return false;
        } else if (TextUtils.isEmpty(title)) {
            ToastUtils.showLong("投诉标题不能为空");
            return false;
        } else if (TextUtils.isEmpty(content)) {
            ToastUtils.showLong("投诉内容不能为空");
            return false;
        } else {
            UserBean user = UserHelper.getUser();
            mBean.setName(user.getRealName());
            mBean.setPhone(user.getMobile());
            mBean.setRemarks(remarks);
            mBean.setNonumber(nonumber);
            mBean.setTitle(title);
            mBean.setContent(content);
            mBean.setAnonymous(mCbAnonymous.isChecked() + "");
            mBean.setFiles(caseFile);
            return true;
        }
    }

    private void commitComplaints() {
        String query = new Gson().toJson(mBean);
        OkGo.<String>post(NetConstant.ADD_COMPLAINTS)
                .params(NetConstant.QUERY, query)
                .execute(new DialogStringCallBack(mActivity, "提交中") {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            int status = jsonObject.getInt(Constant.STATUS);
                            if (status != Constant.SUCCESS) {
                                ToastUtils.showLong(jsonObject.getString(Constant.MSG));
                            } else {
                                ToastUtils.showShort("提交成功");
                                ConsultingComplaintActivity.actionStart(mActivity, Constant.COMPLAINT);
                                finishActivity();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initPicker() {
        initConsultingComplaintTypePicker(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvType.setText(consultingComplaintTypeList.get(options1).getLabel());
                mBean.setRemarkType(consultingComplaintTypeList.get(options1).getValue());
            }
        });

        initSexPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvSex.setText(sexList.get(options1));
                mBean.setSex("女".equals(sexList.get(options1)) ? "2" : "1");
            }
        });

        initCountyPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Area bean = countyList.get(options1);
                initTownPickerView(new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3,
                                                View v) {
                        if (options1 < 0) {
                            ToastUtils.showLong("请等待数据加载完成");
                            return;
                        }
                        Area bean1 = townList.get(options1);
                        mTvNoTown.setText(bean1.getName());
                        notown.setId(bean1.getId());
                        mBean.setNotown(notown);
                    }
                }, bean.getId());
                initOfficePickerView(new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        if (options1 < 0) {
                            ToastUtils.showLong("请等待数据加载完成");
                            return;
                        }
                        Office office = officeList.get(options1);
                        mTvOrganization.setText(office.getName());
                        organization.setId(office.getId());
                        mBean.setOrganization(organization);
                    }
                }, bean.getId());
                if (flag) {
                    mTvCounty.setText(bean.getName());
                    area.setId(bean.getId());
                    mBean.setArea(area);
                } else {
                    noarea.setId(bean.getId());
                    mBean.setNoarea(noarea);
                    mTvNoCounty.setText(bean.getName());

                    mTvNoTown.setText("");
                    notown.setId("");
                    mBean.setNotown(notown);

                    mTvOrganization.setText("");
                    organization.setId("");
                    mBean.setOrganization(organization);
                }
            }
        });

        initSuchComplaintsView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvSuchComplaints.setText(suchComplaintsList.get(options1).getLabel());
                mBean.setSuchComplaints(suchComplaintsList.get(options1).getValue());
            }
        });

        initClassWorkerPickerViewView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvClassWorker.setText(classWorkerList.get(options1).getLabel());
                mBean.setClassWorker(classWorkerList.get(options1).getValue());
            }
        });
    }
}

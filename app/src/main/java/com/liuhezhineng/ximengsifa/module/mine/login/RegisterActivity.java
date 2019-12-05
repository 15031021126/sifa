package com.liuhezhineng.ximengsifa.module.mine.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogStringCallBack;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.utils.IDCardValidator;
import com.liuhezhineng.ximengsifa.utils.MD5Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @date 2018/4/17
 * @description 注册
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_id_card_num)
    EditText mEtIdCardNum;
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_confirm_pwd)
    EditText mEtConfirmPwd;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.et_verification_code)
    EditText mEtVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
    @BindView(R.id.empty_view)
    QMUIEmptyView mEmptyView;
    private String name;
    private String idCardNum;
    private String phoneNum;
    private String pwd;
    private String smsToken;
    private String code;
    private CountDownTimer timer = new CountDownTimer(Constant.COUNT_DOWN_TIME,
            Constant.COUNT_DOWN_INTERVAL) {

        @Override
        public void onTick(long millisUntilFinished) {
            mTvGetVerificationCode.setText((millisUntilFinished / 1000) + "秒后可重发");
            mTvGetVerificationCode.setTextColor(Color.GRAY);
        }

        @Override
        public void onFinish() {
            mTvGetVerificationCode.setEnabled(true);
            mTvGetVerificationCode.setText("获取验证码");
        }
    };

    public static void actionStart(Activity context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivityForResult(intent, Constant.REGISTER);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
//		return R.layout.activity_register_constraint_layout;
    }

    @Override
    protected void initView() {
        super.initView();
//		mCountHandler = new CountHandler(this);
        initTopBar(mTopBar, R.string.register);
    }

    @Override
    @OnClick({R.id.btn_register, R.id.tv_get_verification_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                if (checkInput()) {
                    mEmptyView.show(true);
                    register();
                }
                break;
            case R.id.tv_get_verification_code:
                phoneNum = mEtAccount.getText().toString().trim();
                if (phoneNum.length() <= 0) {
                    errorHint(mEtAccount, R.string.phone_num_do_not_null);
                    return;
                }
                if (!RegexUtils.isMobileExact(phoneNum)) {
                    ToastUtils.showLong("请输入正确的手机号");
                    mEtAccount.requestFocus();
                    mEtAccount.setError("请输入正确的手机号");
                    return;
                }
                mTvGetVerificationCode.setEnabled(false);
                timer.start();
                getSmsToken();
                break;
            default:
                break;
        }
    }

    private boolean checkInput() {
        name = mEtName.getText().toString().trim();
        if (!errorHint(mEtName, R.string.name_not_null)) {
            return false;
        }
        idCardNum = mEtIdCardNum.getText().toString().trim();
        if (!errorHint(mEtIdCardNum, R.string.id_card_num_not_null)) {
            return false;
        }
        if (!new IDCardValidator().isValidatedAllIdCard(idCardNum)) {
            ToastUtils.showLong("请输入正确的身份证号码");
            mEtIdCardNum.requestFocus();
            mEtIdCardNum.setError("请输入正确的身份证号码");
            return false;
        }
        phoneNum = mEtAccount.getText().toString().trim();
        if (!errorHint(mEtAccount, R.string.phone_num_do_not_null)) {
            return false;
        }
        if (!RegexUtils.isMobileExact(phoneNum)) {
            ToastUtils.showLong("请输入正确的手机号码");
            mEtAccount.requestFocus();
            mEtAccount.setError("请输入正确的手机号码");
            return false;
        }
        pwd = mEtPassword.getText().toString().trim();
        if (!errorHint(mEtPassword, R.string.pwd_not_null)) {
            return false;
        }
        String confirmPwd = mEtConfirmPwd.getText().toString().trim();
        if (!errorHint(mEtConfirmPwd, R.string.confirm_pwd_not_null)) {
            return false;
        }
        if (!confirmPwd.equals(pwd)) {
            mEtConfirmPwd.requestFocus();
            mEtConfirmPwd.setError(getString(R.string.confirm_pwd_error));
            return false;
        }
        code = mEtVerificationCode.getText().toString();
        return errorHint(mEtVerificationCode, R.string.verification_code_not_null);
    }

    private void register() {
        Map<String, String> map1 = new HashMap<>(16);
        map1.put(NetConstant.SMS_TOKEN, smsToken);
        map1.put(NetConstant.NAME, name);
        map1.put(NetConstant.PAPER_NUM, idCardNum);
        map1.put(NetConstant.PWD, MD5Utils.getMD5(pwd + phoneNum));
        map1.put(NetConstant.CODE, code);
        JSONObject jsonObject1 = new JSONObject(map1);
        OkGo.<String>post(NetConstant.REGISTER)
                .cacheMode(CacheMode.NO_CACHE)
                .params(NetConstant.QUERY, jsonObject1.toString())
                .execute(new DialogStringCallBack(mActivity, "注册中...") {

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mEmptyView.hide();
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int status = jsonObject.getInt(Constant.STATUS);
                            if (status != 0) {
                                ToastUtils.showLong(jsonObject.getString(Constant.MSG));
                            } else {
                                ToastUtils.showLong("注册成功");
                                setResult(Constant.REGISTER, new Intent(mActivity, LoginActivity.class)
                                        .putExtra(Constant.PHONE_NUM, phoneNum));
                                finishActivity();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private boolean errorHint(EditText editText, @StringRes int resId) {
        String text = editText.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            editText.requestFocus();
            editText.setError(getString(resId));
            return false;
        }
        return true;
    }

    private void getSmsToken() {
        Map<String, String> map = new HashMap<>();
        map.put(Constant.SMS.MOBILE, phoneNum);
        map.put(Constant.SMS.OPERATE, Constant.SMS.REGISTER);
        JSONObject jsonObject = new JSONObject(map);
        String string = jsonObject.toString();
        OkGo.<BaseBean<String>>post(NetConstant.GET_VERIFICATION_CODE)
                .params(NetConstant.QUERY, string)
                .execute(new JsonCallback<BaseBean<String>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        smsToken = response.body().getBody();
                    }
                });
    }
}
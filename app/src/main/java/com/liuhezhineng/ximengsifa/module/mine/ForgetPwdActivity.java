package com.liuhezhineng.ximengsifa.module.mine;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.QueryUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 忘记密码：用来通过手机号验证码达到修改密码的目的
 */
public class ForgetPwdActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.et_phone)
    EditText mEtPhoneNum;
    @BindView(R.id.et_verification_code)
    EditText mEtVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
    @BindView(R.id.et_new_pwd)
    EditText mEtNewPwd;

    private String phoneNum;
    private String verificationCode;
    private String newPwd;

    private CountDownTimer timer = new CountDownTimer(
            Constant.COUNT_DOWN_TIME,
            Constant.COUNT_DOWN_INTERVAL) {

        @Override
        public void onTick(long millisUntilFinished) {
            mTvGetVerificationCode.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            mTvGetVerificationCode.setEnabled(true);
            mTvGetVerificationCode.setText("获取验证码");
        }
    };
    private String smsToken;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ForgetPwdActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(mTopBar, R.string.forget_pwd);
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
    @OnClick({R.id.tv_get_verification_code, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_verification_code:
                if (checkPhoneInput()) {
                    mTvGetVerificationCode.setEnabled(false);
                    timer.start();
                    getSmsToken();
                } else {
                    mEtPhoneNum.requestFocus();
                    mEtPhoneNum.setError(getString(R.string.phone_num_do_not_null));
                }
                break;
            case R.id.btn_ok:
                if (checkInput()) {
                    modifyPwd();
                }
            default:
                break;
        }
    }

    private boolean checkPhoneInput() {
        phoneNum = mEtPhoneNum.getText().toString().trim();
        return phoneNum.length() > 0;
    }

    private void getSmsToken() {
        String phoneNum = mEtPhoneNum.getText().toString().trim();
        if (!errorHint(mEtPhoneNum, R.string.phone_num_do_not_null)) {
            return;
        }
        OkGo.<BaseBean<String>>post(NetConstant.GET_VERIFICATION_CODE)
                .params(NetConstant.QUERY, new QueryUtils()
                        .params(Constant.SMS.MOBILE, phoneNum)
                        .params(Constant.SMS.OPERATE, Constant.SMS.FIND_PWD)
                        .getQueryStr())
                .execute(new JsonCallback<BaseBean<String>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        smsToken = response.body().getBody();
                        ToastUtils.showLong(R.string.send_vertification_code_successful);
                    }

                    @Override
                    public void onError(Response<BaseBean<String>> response) {
                        super.onError(response);
                    }
                });
    }

    private boolean checkInput() {
        phoneNum = mEtPhoneNum.getText().toString().trim();
        if (!errorHint(mEtPhoneNum, R.string.phone_num_do_not_null)) {
            return false;
        }
        if (!RegexUtils.isMobileExact(phoneNum)) {
            ToastUtils.showLong("请输入正确的手机号码");
            mEtPhoneNum.requestFocus();
            mEtPhoneNum.setError("请输入正确的手机号码");
            return false;
        }
        newPwd = mEtNewPwd.getText().toString().trim();
        if (!errorHint(mEtNewPwd, R.string.pwd_not_null)) {
            return false;
        }
        verificationCode = mEtVerificationCode.getText().toString();
        return errorHint(mEtVerificationCode, R.string.verification_code_not_null);
    }

    private void modifyPwd() {
        OkGo.<BaseBean<String>>post(NetConstant.FORGET_PWD)
                .params(NetConstant.QUERY, new QueryUtils()
                        .params(Constant.SMS.SMS_TOKEN, smsToken)
                        .params(Constant.SMS.PWD, newPwd)
                        .params(Constant.SMS.CODE, verificationCode)
                        .getQueryStr())
                .execute(new DialogCallBack<BaseBean<String>>(this,
                        getString(R.string.update_pws_loading)) {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        ToastUtils.showLong(R.string.update_pws_successful);
                        finishActivity();
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
}

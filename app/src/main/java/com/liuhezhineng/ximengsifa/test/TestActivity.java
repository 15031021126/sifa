package com.liuhezhineng.ximengsifa.test;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;

import java.io.File;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class TestActivity extends Activity {

    @BindView(R.id.btn_register)
    Button mBtnRegister;

    @BindString(R.string.account)
    String mStrAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    void test(View view) {
        new AsyncTask<String, Integer, File>() {

            @Override
            protected File doInBackground(String... strings) {
                return null;
            }
        };

        new Handler();
    }
    @OnTouch({R.id.btn_register, R.id.tv_get_verification_code})
    boolean onTouchTest(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.btn_register:
                ToastUtils.showLong("register" + event.getX() + ", " + event.getAction());
                break;
            default:
                ToastUtils.showLong("code" + event.getX() + ", " + event.getAction());
                break;
        }
        return true;
    }

    @OnClick({R.id.btn_register, R.id.tv_get_verification_code})
    void onClickTest(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                ToastUtils.showLong("register");
                break;
            default:
                ToastUtils.showLong("code");
                break;
        }
    }
}

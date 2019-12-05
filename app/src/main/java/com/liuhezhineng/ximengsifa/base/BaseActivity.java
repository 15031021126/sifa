package com.liuhezhineng.ximengsifa.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.blankj.utilcode.util.KeyboardUtils;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.module.mine.login.LoginActivity;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @date 2018/4/15
 * @description activity 基类
 * <p>
 * 里面写了太多的 picker view
 * 以后优化可以写个自定义 View
 */
public abstract class BaseActivity extends PickerActivity {

    protected static final String TAG = "qingfeng";
    /**
     * 容器，用来管理所有打开的页面实例
     */
    public static ArrayList<Activity> mActivityArrayList = new ArrayList<>();
    public QMUITopBar mTopBar;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(getLayoutId());

        ButterKnife.bind(this);

        HttpHeaders headers = new HttpHeaders();
        if (!TextUtils.isEmpty(UserHelper.getToken())) {
            headers.put("token", UserHelper.getToken());
        }
        OkGo.getInstance().init(getApplication())
                .addCommonHeaders(headers);

        mActivity = this;
        mActivityArrayList.add(this);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Translucent status bar
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.blue));
        }

        initView();
        initData();
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityArrayList.remove(this);
        dismissLoadingDialog();
    }

    protected static void gotoLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    protected abstract @LayoutRes int getLayoutId();

    protected void initView() {
        initDialog();
    }

    protected void initData() {
    }

    protected void setListener() {
    }

    private void initDialog() {
        dialog = new ProgressDialog(mActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(mActivity.getString(R.string.loading));
    }

    public void dismissLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
        }
    }

    public void showLoadingDialog() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void showLoadingDialog(String title) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.setMessage(title);
            dialog.show();
        }
    }

    @OnClick()
    public void onViewClicked(View view) {
        KeyboardUtils.hideSoftInput(mActivity);
    }

    protected void initTopBar(@StringRes int resId) {
        mTopBar = findViewById(R.id.top_bar);
        if (mTopBar == null) {
            return;
        }
        initTopBar(mTopBar, getString(resId));
    }

    protected void initTopBar(QMUITopBar topBar, String title) {
        topBar.setTitle(title);
        topBar.addLeftBackImageButton().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
    }

    /**
     * 统一 activity 跳转风格
     */
    public void finishActivity() {
        hintKbTwo();
        finish();
        overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
    }

    /**
     * 此方法只是关闭软键盘 可以在finish之前调用一下
     */
    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    protected void initTopBar(QMUITopBar topBar, @StringRes int resId) {
        initTopBar(topBar, getString(resId));
    }

    public Gson getIgnoreGson(final String key) {
        return new GsonBuilder().setExclusionStrategies(
                new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getName().equals(key);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                }
        ).create();
    }

}
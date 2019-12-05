package com.liuhezhineng.ximengsifa.module.service.webview;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;

/**
 * @author AIqinfeng
 * @description web view
 */
public class WebViewBaseActivity extends BaseActivity {

    public static final String TITLE = "title";
    public static final int PROGRESS_MAX = 100;

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.web_view)
	WebView mWebView;
	@BindView(R.id.pb)
    ProgressBar mPb;

	public static void actionStart(Context context, String url, String title) {
		Intent intent = new Intent(context, WebViewBaseActivity.class);
		intent.putExtra(Constant.STRING, url);
		intent.putExtra(TITLE, title);
		context.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_knowledge_base;
	}

	@Override
	protected void initData() {
		super.initData();
		String title = getIntent().getStringExtra(TITLE);
        String url = getIntent().getStringExtra(Constant.STRING);
        initTopBar(mTopBar, title);
		initWebSettings();
		mWebView.loadUrl(url);
	}

	private void initWebSettings() {
		WebSettings webSettings = mWebView.getSettings();
        //将图片调整到适合webview的大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        //支持缩放，默认为true。是下面那个的前提。
        webSettings.setSupportZoom(true);
        //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setBuiltInZoomControls(true);
        //隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        mPb.setProgress(PROGRESS_MAX);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mPb.setProgress(newProgress);
                if (newProgress >= PROGRESS_MAX) {
                    mPb.setVisibility(View.GONE);
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
	}

	@Override
	public void onBackPressed() {
		if (mWebView.canGoBack()) {
			mWebView.goBack();
		} else {
			super.onBackPressed();
		}
	}
}

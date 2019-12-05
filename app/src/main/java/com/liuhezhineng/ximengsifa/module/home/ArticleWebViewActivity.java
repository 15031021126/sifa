package com.liuhezhineng.ximengsifa.module.home;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.ArticleBean;
import com.liuhezhineng.ximengsifa.bean.ArticleDetailsBean;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ArticleWebViewActivity extends BaseActivity {

	@BindView(R.id.web_view)
	WebView mWebView;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_article_web_view;
	}

	public static void actionStart(Context context, ArticleBean bean) {
		Intent intent = new Intent(context, ArticleWebViewActivity.class);
		intent.putExtra(Constant.BEAN, bean);
		context.startActivity(intent);
	}

	ArticleBean mBean;
	@Override
	protected void initData() {
		super.initData();
		mBean = (ArticleBean) getIntent().getSerializableExtra(Constant.BEAN);
		getArticleDetails(mBean.getId());

		mWebView.setWebChromeClient(new WebChromeClient());
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		initWebSettings();
	}

	private void initWebSettings() {
		//声明WebSettings子类
		WebSettings webSettings = mWebView.getSettings();

		//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
		webSettings.setJavaScriptEnabled(true);
		// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
		// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

		//设置自适应屏幕，两者合用
		webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
		webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

		//缩放操作
		webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
		webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
		webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

		//其他细节操作
		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
		webSettings.setAllowFileAccess(true); //设置可以访问文件
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
		webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
		webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
	}

	@Override
	public void onBackPressed() {
		if (mWebView.canGoBack()) {
			mWebView.goBack();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		mWebView.getSettings().setJavaScriptEnabled(false);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mWebView.getSettings().setJavaScriptEnabled(true);
	}


	private void getArticleDetails(final String id) {
		Map<String, String> map = new HashMap<>(16);
		map.put(NetConstant.ARTICLE_ID, id);
		OkGo.<BaseBean<ArticleDetailsBean>>post(NetConstant.GET_ARTICLE_DETAILS)
			.params(NetConstant.QUERY, new JSONObject(map).toString())
			.execute(new DialogCallBack<BaseBean<ArticleDetailsBean>>(mActivity) {

				@Override
				public void onSuccess(Response<BaseBean<ArticleDetailsBean>> response) {
					ArticleDetailsBean body = response.body().getBody();
					String html = body.getArticleData().getContent();
					mWebView.loadDataWithBaseURL(null,html,"text/html", "utf-8",null);
					mWebView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
				}
			});
	}

}

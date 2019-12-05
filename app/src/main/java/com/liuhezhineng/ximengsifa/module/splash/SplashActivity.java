package com.liuhezhineng.ximengsifa.module.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.liuhezhineng.ximengsifa.base.MainActivity;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;

/**
 * @author AIqinfeng
 * @date 2018/4/23
 */

public class SplashActivity extends BaseActivity {

	private static final int SPLASH_TIME = 5000;
	@BindView(R.id.iv_splash)
	ImageView mIvSplash;
	@BindView(R.id.tv_num)
	TextView mTvNum;
	int i;
	boolean isBackPress, isIgnore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ButterKnife.bind(this);
		playAD();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_splash;
	}

	private void playAD() {
		new Thread() {
			@Override
			public void run() {
				for (i = SPLASH_TIME / 1000; i > 0; i--) {
					if (isBackPress || isIgnore) {
						return;
					}
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mTvNum.setText(String.valueOf(i));
						}
					});
					SystemClock.sleep(1000);
				}
				gotoMainActivity();
			}
		}.start();
	}

	private void gotoMainActivity() {
		startActivity(new Intent(SplashActivity.this, MainActivity.class));
		overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
		finish();
	}

	@OnClick(R.id.tv_click_ignore)
	public void onClick() {
		isIgnore = true;
		gotoMainActivity();
	}

	@Override
	public void onBackPressed() {
		isBackPress = true;
		finish();
	}
}

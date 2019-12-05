package com.liuhezhineng.ximengsifa.module.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * @author AIqinfeng
 * @date 2018/5/3
 */

public class FeedbackActivity extends BaseActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.et_feedback_content)
	EditText mEtFeedbackContent;
	@BindView(R.id.et_email_or_phone)
	EditText mEtEmailOrPhone;

	private String content;
	private String contactWay;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_feedback;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			content = savedInstanceState.getString(Constant.FEEDBACK_CONTENT);
			contactWay = savedInstanceState.getString(Constant.FEEDBACK_CONTACT_WAY);
			mEtFeedbackContent.setText(content);
			mEtEmailOrPhone.setText(contactWay);
		}
	}

	@Override
	protected void initData() {
		super.initData();
		initTopBar(mTopBar, R.string.feedback);
	}

	public static void actionStart(Context context) {
		Intent intent = new Intent(context, FeedbackActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState != null) {
			content = savedInstanceState.getString(Constant.FEEDBACK_CONTENT);
			contactWay = savedInstanceState.getString(Constant.FEEDBACK_CONTACT_WAY);
			mEtFeedbackContent.setText(content);
			mEtEmailOrPhone.setText(contactWay);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(Constant.FEEDBACK_CONTENT, content);
		outState.putString(Constant.FEEDBACK_CONTACT_WAY, contactWay);
	}

	@OnClick(R.id.btn_commit_feedback)
	public void onViewClicked() {
		content = mEtFeedbackContent.getText().toString().trim();
		contactWay = mEtEmailOrPhone.getText().toString().trim();
		if (content == null || content.length() <= 0) {
			mEtFeedbackContent.requestFocus();
			mEtFeedbackContent.setError(getString(R.string.feedback_content_not_null));
			return;
		}
		ToastUtils.showShort(R.string.commit_feedback_success);
		finishActivity();
	}
}

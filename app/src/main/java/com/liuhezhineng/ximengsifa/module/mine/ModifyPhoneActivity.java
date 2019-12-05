package com.liuhezhineng.ximengsifa.module.mine;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.ProfileBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogStringCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * @author AIqinfeng
 * @description 修改手机号码
 */
public class ModifyPhoneActivity extends BaseActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.et_modify_phone)
	EditText mEtModifyPhone;

	private ProfileBean mBean;

	public static void actionStart(Context context) {
		Intent intent = new Intent(context, ModifyPhoneActivity.class);
		((BaseActivity) context).startActivityForResult(intent, Constant.NORMAL_CODE);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_modify_phone;
	}

	@Override
	protected void initView() {
		super.initView();
		initTopBar(mTopBar, "修改手机号");

		mTopBar.addRightTextButton("保存", R.id.btn_ok).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String mobile = mEtModifyPhone.getText().toString().trim();
				if (TextUtils.isEmpty(mobile)) {
					ToastUtils.showLong("手机号不能为空");
					return;
				}
				if (mobile.length() != 11) {
					ToastUtils.showLong("手机号位数不对");
					return;
				}
				mBean.setMobile(mobile);
				modifyUserInfo();
			}
		});
	}

	@Override
	protected void initData() {
		super.initData();
		mBean = new ProfileBean();
	}

	private void modifyUserInfo() {
		String queryStr = new Gson().toJson(mBean);
		OkGo.<String>post(NetConstant.MODIFY_USER_INFO)
			.params(NetConstant.QUERY, queryStr)
			.execute(new DialogStringCallBack(mActivity, "修改中...") {
				@Override
				public void onSuccess(Response<String> response) {
					try {
						JSONObject jsonObject = new JSONObject(response.body());
						int status = jsonObject.getInt(Constant.STATUS);
						if (status != 0) {
							ToastUtils.showLong(jsonObject.getString(Constant.MSG));
						} else {
							setResult(RESULT_OK, new Intent().putExtra("phone", mBean.getMobile()));
							ToastUtils.showShort("修改成功");
							finishActivity();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
	}
}

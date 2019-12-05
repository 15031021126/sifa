package com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.SubTypeBean;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.advisory.ConsultingRequestBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.utils.PickerViewBuilder;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @description 留言咨询填写表单
 */
public class MyConsultationActivity extends BaseActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.tv_area)
	TextView mTvArea;
	@BindView(R.id.tv_problem_type)
	TextView mTvProblemType;
	@BindView(R.id.tv_type)
	TextView mTvType;
	@BindView(R.id.et_phone)
	TextView mEtPhone;
	@BindView(R.id.et_title)
	EditText mEtTitle;
	@BindView(R.id.et_content)
	EditText mEtContent;

	ConsultingRequestBean mBean;
	private ArrayList<TypeBean> problemTypeList;
	private OptionsPickerView<TypeBean> mProblemTypePicker;
	private ArrayList<SubTypeBean> typeList;
	private OptionsPickerView<SubTypeBean> mTypePicker;
	private String userId;

	public static void actionStart(Context context) {
		if (UserHelper.isIsLogin()) {
			Intent intent = new Intent(context, MyConsultationActivity.class);
			context.startActivity(intent);
		} else {
			gotoLogin(context);
		}
	}

	public static void actionStart(Context context, String userId) {
		if (UserHelper.isIsLogin()) {
			Intent intent = new Intent(context, MyConsultationActivity.class);
			intent.putExtra("user_id", userId);
			context.startActivity(intent);
		} else {
			gotoLogin(context);
		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_my_counsultations;
	}

	@Override
	protected void initView() {
		super.initView();
		initTopBar(mTopBar, "我要咨询");

		UserBean user = UserHelper.getUser();
		mEtPhone.setText(user.getMobile());
	}

	@Override
	protected void initData() {
		super.initData();
		mBean = new ConsultingRequestBean();

		userId = getIntent().getStringExtra("user_id");
		UserBean user = new UserBean();
		user.setId(userId);
		mBean.setUser(user);

		initPicker();
	}

	@Override
	@OnClick({R.id.tv_area, R.id.tv_problem_type, R.id.tv_type, R.id.btn_commit})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		switch (view.getId()) {
			case R.id.tv_area:
				showPickerView(countyPickerView);
				break;
			case R.id.tv_problem_type:
				showPickerView(mProblemTypePicker);
				break;
			case R.id.tv_type:
				if (TextUtils.isEmpty(mBean.getProblemType())) {
					ToastUtils.showLong("请先选择业务类型");
				} else {
					showPickerView(mTypePicker);
				}
				break;
			case R.id.btn_commit:
				if (checkInput()) {
					commitConsultation();
				}
				break;
			default:
				break;
		}
	}

	private boolean checkInput() {
		String area = mTvArea.getText().toString().trim();
		String problemType = mTvProblemType.getText().toString().trim();
		String type = mTvType.getText().toString().trim();
		String phone = mEtPhone.getText().toString().trim();
		String title = mEtTitle.getText().toString().trim();
		String content = mEtContent.getText().toString().trim();

		if (TextUtils.isEmpty(area)) {
			ToastUtils.showLong("所在地区不能为空");
			return false;
		} else if (TextUtils.isEmpty(problemType)) {
			ToastUtils.showLong("业务类型不能为空");
			return false;
		} else if (TextUtils.isEmpty(type)) {
			ToastUtils.showLong("问题类型不能为空");
			return false;
		} else if (TextUtils.isEmpty(phone)) {
			ToastUtils.showLong("联系电话不能为空");
			return false;
		} else if (TextUtils.isEmpty(title)) {
			ToastUtils.showLong("主题不能为空");
			return false;
		} else if (TextUtils.isEmpty(content)) {
			ToastUtils.showLong("内容不能为空");
			return false;
		} else {
			mBean.setTitle(title);
			mBean.setContent(content);
			mBean.setPhone(phone);
			return true;
		}
	}

	private void commitConsultation() {
		OkGo.<BaseBean<String>>post(NetConstant.ADD_ADVISORY_MESSAGES)
			.params(NetConstant.QUERY, new Gson().toJson(mBean))
			.execute(new DialogCallBack<BaseBean<String>>(mActivity, "提交中...") {
				@Override
				public void onSuccess(Response<BaseBean<String>> response) {
					int status = response.body().getStatus();
					if (status == 0) {
						ToastUtils.showLong("提交成功");
						ConsultingComplaintActivity.actionStart(mActivity, Constant.CONSULTING);
						finishActivity();
					} else {
						ToastUtils.showLong(response.body().getMsg());
					}
				}
			});
	}

	private void initPicker() {
		initProblemTypePicker();

		initCountyPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				Area bean = countyList.get(options1);
				mTvArea.setText(bean.getName());
				Area area = new Area();
				area.setId(bean.getId());
				mBean.setArea(area);
			}
		});
	}

	private void initProblemTypePicker() {
		problemTypeList = new ArrayList<>();
		typeList = new ArrayList<>();
		Map<String, String> map = new HashMap<>(16);
		map.put("key", "cms_guestbook_type");
		OkGo.<BaseBean<List<TypeBean>>>post(NetConstant.GET_TYPE)
			.cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
			.params(NetConstant.QUERY, new JSONObject(map).toString())
			.execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
				@Override
				public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
					problemTypeList.addAll(response.body().getBody());

					mProblemTypePicker = new PickerViewBuilder(mActivity,
						new OnOptionsSelectListener() {
							@Override
							public void onOptionsSelect(int options1, int options2, int options3,
								View v) {
								mTvProblemType.setText(problemTypeList.get(options1).getLabel());
								mBean.setProblemType(problemTypeList.get(options1).getValue());
								mTvType.setText("");
								mBean.setType("");
								typeList.clear();
								initTypePicker(options1);
							}
						})
						.build();

					mProblemTypePicker.setPicker(problemTypeList);
				}
			});
	}

	private void initTypePicker(final int index) {
		final Map<String, String> subMap = new HashMap<>(16);
		subMap.put("key", "cms_guestbook_type");
		subMap.put("parentId", problemTypeList.get(index).getValue());
		OkGo.<BaseBean<List<SubTypeBean>>>post(NetConstant.GET_SUB_TYPE)
			.cacheKey("cms_guestbook_type" + index)
			.cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
			.params(NetConstant.QUERY, new JSONObject(subMap).toString())
			.execute(new JsonCallback<BaseBean<List<SubTypeBean>>>() {
				@Override
				public void onSuccess(Response<BaseBean<List<SubTypeBean>>> response) {
					typeList.clear();
					List<SubTypeBean> body = response.body().getBody();
					typeList.addAll(body);

					mTypePicker = new PickerViewBuilder(mActivity, new OnOptionsSelectListener() {
						@Override
						public void onOptionsSelect(int options1, int options2, int options3,
							View v) {
							SubTypeBean subTypeBean = typeList.get(options1);
							mTvType.setText(subTypeBean.getLabel());
							mBean.setType(subTypeBean.getValue());
						}
					}).build();

					mTypePicker.setPicker(typeList);
				}
			});
	}
}

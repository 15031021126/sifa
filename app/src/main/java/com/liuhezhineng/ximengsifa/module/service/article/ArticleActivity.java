package com.liuhezhineng.ximengsifa.module.service.article;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.BindView;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.flyco.tablayout.SlidingTabLayout;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.dto.vo.ServerAppVo;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.module.service.ServiceFragment;
import com.liuhezhineng.ximengsifa.widget.AndroidSevenPopupWindow;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @date 2018/5/4
 * @description 文章类型: （首页）政策发布，服务动态，（服务）服务指南，文书表格，法律法规，谁执法谁普法
 */

public class ArticleActivity extends BaseActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.sliding_tab_layout)
	SlidingTabLayout mSlidingTabLayout;
	@BindView(R.id.view_pager)
	ViewPager mViewPager;
	List<ServerAppVo> mList;
	ServerAppVo mServerAppVo;
	PopupWindow popupWindow;
	private String[] mTitles;
	private ArrayList<Fragment> mFragments = new ArrayList<>();
	private String categoryType;
	private boolean flag;
	private String serviceId;

	public static void actionStart(Context context, ServerAppVo serverAppVo) {
		Intent intent = new Intent(context, ArticleActivity.class);
		intent.putExtra(Constant.BEAN, serverAppVo);
		context.startActivity(intent);
	}

	public static void actionStart(Context context, ServerAppVo services, String serviceId) {
		Intent intent = new Intent(context, ArticleActivity.class);
		intent.putExtra(Constant.BEAN, services);
		intent.putExtra(Constant.STRING, serviceId);
		context.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_article;
	}

	@Override
	protected void initView() {
		super.initView();
		mServerAppVo = (ServerAppVo) getIntent().getSerializableExtra(Constant.BEAN);
		initTopBar(mTopBar, mServerAppVo.getName());

		initFilterWindow();
		Button btnFilter = mTopBar.addRightTextButton("筛选", R.id.btn_ok);
		btnFilter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				} else {
					popupWindow.showAsDropDown(mTopBar, 0, 0, Gravity.CENTER_HORIZONTAL);
				}
			}
		});
	}

	private void initFilterWindow() {
		View contentView = LayoutInflater.from(mActivity)
			.inflate(R.layout.filter_date_and_title, null);
		popupWindow = new AndroidSevenPopupWindow(contentView,
			LayoutParams.MATCH_PARENT,
			LayoutParams.WRAP_CONTENT);
		ColorDrawable cd = new ColorDrawable();
		popupWindow.setBackgroundDrawable(cd);
		popupWindow.setFocusable(true);
		contentView.findViewById(R.id.view_mask).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		final EditText etTitle = contentView.findViewById(R.id.et_title);
		final TextView tvStartDate = contentView.findViewById(R.id.tv_start_date);
		final TextView tvEndDate = contentView.findViewById(R.id.tv_end_date);
		initBirthdayPicker(new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				if (flag) {
					tvStartDate.setText(getTime(date));
				} else {
					tvEndDate.setText(getTime(date));
				}
			}
		});
		tvStartDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				flag = true;
				birthdayPickerView.show();
			}
		});
		tvEndDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				flag = false;
				birthdayPickerView.show();
			}
		});
		contentView.findViewById(R.id.tv_ok).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
				String beginDate = tvStartDate.getText().toString().trim();
				String endDate = tvEndDate.getText().toString().trim();
				String title = etTitle.getText().toString().trim();
				((ArticleFragment) mFragments.get(mSlidingTabLayout.getCurrentTab()))
					.filterData(beginDate, endDate, title);
			}
		});
		contentView.findViewById(R.id.tv_reset).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tvStartDate.setText("");
				tvEndDate.setText("");
				etTitle.setText("");
			}
		});
	}

	@Override
	protected void initData() {
		super.initData();
		mList = new ArrayList<>();
		serviceId = getIntent().getStringExtra(Constant.STRING);
		if (!TextUtils.isEmpty(serviceId)) {
			initTopBar(mTopBar, "谁执法谁普法");
			getServicesList(serviceId);
			return;
		}
		if ("谁执法谁普法".equals(mServerAppVo.getLink())) {
			getServicesList(mServerAppVo.getId());
		} else {
			getArticleCategory();
		}
	}

	private void getServicesList(String serverId) {
		OkGo.<BaseBean<List<ServerAppVo>>>post(NetConstant.GET_SERVICES)
			.params(NetConstant.QUERY, "{\"serverId\":\"" + serverId + "\"}")
			.execute(new DialogCallBack<BaseBean<List<ServerAppVo>>>(mActivity) {
				@Override
				public void onSuccess(Response<BaseBean<List<ServerAppVo>>> response) {
					List<ServerAppVo> list = response.body().getBody();
					mList.addAll(list);
					mTitles = new String[list.size()];
					for (int i = 0; i < list.size(); i++) {
						mFragments
							.add(ArticleFragment.newInstance(list.get(i).getSourceId()));
						mTitles[i] = list.get(i).getName();
					}

					mViewPager.setAdapter(
						new ArticleActivity.MyPagerAdapter(getSupportFragmentManager()));
					mSlidingTabLayout.setViewPager(mViewPager, mTitles);
					String name = "";
					if (serviceId != null && !TextUtils.isEmpty(serviceId)) {
						name = mServerAppVo.getName();
					} else {
						name = ServiceFragment.mServerAppVo.getName();
					}
					for (int i = 0; i < mTitles.length; i++) {
						if (mTitles[i].equals(name)) {
							mSlidingTabLayout.setCurrentTab(i);
						}
					}
				}
			});
	}

	private void getArticleCategory() {
		switch (mServerAppVo.getLink()) {
			case "政策发布":
				categoryType = Constant.POLICY_RELEASE;
				break;
			case "服务动态":
				categoryType = Constant.SERVICE_DYNAMICS;
				break;
			case "法律法规":
				categoryType = Constant.LAWS_AND_REGULATIONS;
				break;
			case "服务指南":
				categoryType = Constant.SERVICE_GUIDE;
				break;
			case "文书表格":
				categoryType = Constant.PAPER_FORMS;
				break;
			default:
				break;
		}
		Map<String, String> map = new HashMap<>(16);
		map.put("categoryType", categoryType);
		String queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<List<ServerAppVo>>>post(NetConstant.GET_SERVICE_BY_CATEGORY_TYPE)
			.params(NetConstant.QUERY, queryStr)
			.execute(new DialogCallBack<BaseBean<List<ServerAppVo>>>(mActivity) {
				@Override
				public void onSuccess(Response<BaseBean<List<ServerAppVo>>> response) {
					List<ServerAppVo> list = response.body().getBody();
					mList.addAll(list);
					mTitles = new String[list.size()];
					for (int i = 0; i < list.size(); i++) {
						mFragments
							.add(ArticleFragment.newInstance(list.get(i).getSourceId()));
						mTitles[i] = list.get(i).getName();
					}

					mViewPager.setAdapter(
						new ArticleActivity.MyPagerAdapter(getSupportFragmentManager()));
					mSlidingTabLayout.setViewPager(mViewPager, mTitles);
					if (ServiceFragment.mServerAppVo != null) {
						String name = ServiceFragment.mServerAppVo.getName();
						for (int i = 0; i < mTitles.length; i++) {
							if (mTitles[i].equals(name)) {
								mSlidingTabLayout.setCurrentTab(i);
							}
						}
					}
				}
			});
	}

	private class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mTitles[position];
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}
	}
}

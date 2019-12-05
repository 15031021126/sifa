package com.liuhezhineng.ximengsifa.module;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.BindView;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.FilterAdapter;
import com.liuhezhineng.ximengsifa.adapter.IntegratedQueryAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.IntegrateQueryFilterBean;
import com.liuhezhineng.ximengsifa.bean.IntegratedQueryBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.DictKey;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.interfaces.OnFilterClickListener;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 */
public class IntegratedQueryActivity extends BaseActivity implements OnClickListener,
	OnFilterClickListener {

	private static final int HANDLE_CHANNEL = 0;
	private static final int CASE_CATEGORY = 1;
	private static final int CASE_PROCESS = 2;
	private static final int CASE_RANK = 3;
	private static final String INTEGRATE_QUERY = "integrate query";
	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.rv_list)
	RecyclerView mRvList;
	@BindView(R.id.iv_no_data)
	ImageView mIvNoData;
	@BindView(R.id.fl_no_data)
	FrameLayout mFlNoData;
	@BindView(R.id.refreshLayout)
	SmartRefreshLayout mRefreshLayout;

	ArrayList<IntegratedQueryBean> mList;
	IntegratedQueryAdapter mAdapter;
	int pageNo;
	int pageSize = NetConstant.DEFAULT_PAGE_SIZE;
	private PopupWindow mPopupWindow;
	private String pickerFlag;
	private TextView tvTitle;
	private TextView tvName;
	private TextView tvRequestUser;
	private View view;
	private IntegrateQueryFilterBean mBean = new IntegrateQueryFilterBean();
	private TextView tvRequestStartDate, tvRequestEndDate, tvDealStartDate, tvDealEndDate;
	private TextView tvArea;
	private FilterAdapter handleChannelsAdapter, caseProcessAdapter, caseRankAdapter, caseCategoryAdapter;

	public static void actionStart(BaseActivity activity) {
		if (UserHelper.isIsLogin()) {
			Intent intent = new Intent(activity, IntegratedQueryActivity.class);
			activity.startActivity(intent);
		} else {
			gotoLogin(activity);
		}
	}

	public static void actionStart(Context activity, IntegrateQueryFilterBean bean) {
		if (UserHelper.isIsLogin()) {
			Intent intent = new Intent(activity, IntegratedQueryActivity.class);
			intent.putExtra(INTEGRATE_QUERY, bean);
			activity.startActivity(intent);
		} else {
			gotoLogin(activity);
		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_integrated_query;
	}

	@Override
	protected void initView() {
		super.initView();
		initTopBar(mTopBar, "综合查询");

		mList = new ArrayList<>();
		mAdapter = new IntegratedQueryAdapter(mActivity, mList);
		mRvList.setAdapter(mAdapter);
	}

	@Override
	protected void initData() {
		super.initData();

		// 从统计分析页面带过来的筛选参数
		IntegrateQueryFilterBean bean = (IntegrateQueryFilterBean) getIntent().getSerializableExtra(INTEGRATE_QUERY);
		mBean = bean == null ? mBean : bean;

		pageNo = 1;
		PageBean page = new PageBean();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		mBean.setPage(page);

		getIntegratedQueryData(Constant.INIT_DATA);
	}

	private void getIntegratedQueryData(final int action) {
		mBean.getPage().setPageNo(pageNo);
		mBean.getPage().setPageSize(pageSize);
		String queryStr = new Gson().toJson(mBean);
		OkGo.<BaseBean<PageBean<IntegratedQueryBean>>>post(NetConstant.INTEGRATE_QUERY)
			.params(NetConstant.QUERY, queryStr)
			.execute(new DialogCallBack<BaseBean<PageBean<IntegratedQueryBean>>>(mActivity) {
				@Override
				public void onSuccess(Response<BaseBean<PageBean<IntegratedQueryBean>>> response) {
					PageBean<IntegratedQueryBean> pageBean = response.body().getBody();
					mRefreshLayout.setNoMoreData(pageBean.getCount() <= pageNo * pageSize);
					List<IntegratedQueryBean> list = pageBean.getList();
					if (pageNo == 1 && (list == null || list.size() <= 0)) {
						mFlNoData.setVisibility(View.VISIBLE);
					}
					switch (action) {
						case Constant.INIT_DATA:
							mAdapter.initData(list);
							break;
						case Constant.REFRESH:
							mRefreshLayout.finishRefresh();
							mAdapter.initData(list);
							break;
						case Constant.LOAD_MORE:
							mRefreshLayout.finishLoadMore();
							mAdapter.addData(list);
							break;
						default:
							break;
					}
				}
			});
	}

	@Override
	protected void setListener() {
		super.setListener();

		initFilterWindow();
		mTopBar.addRightTextButton(R.string.filter, R.id.selected_view).setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (!mPopupWindow.isShowing()) {
						mPopupWindow.showAtLocation(mTopBar, Gravity.END, 0, 0);
					} else {
						mPopupWindow.dismiss();
					}
				}
			});

		mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
			@Override
			public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
				pageNo++;
				getIntegratedQueryData(Constant.LOAD_MORE);
			}

			@Override
			public void onRefresh(@NonNull RefreshLayout refreshLayout) {
				pageNo = 1;
				mFlNoData.setVisibility(View.GONE);
				getIntegratedQueryData(Constant.REFRESH);
			}
		});
	}

	private void initFilterWindow() {
		view = LayoutInflater.from(mActivity)
			.inflate(R.layout.sliding_search_popop, null);
		mPopupWindow = new PopupWindow(view,
			LayoutParams.MATCH_PARENT,
			LayoutParams.WRAP_CONTENT,
			true);
		mPopupWindow.setBackgroundDrawable(new ColorDrawable());
		view.findViewById(R.id.view_mask).setOnClickListener(this);
		tvArea = view.findViewById(R.id.tv_area);
		tvArea.setOnClickListener(this);
		tvTitle = view.findViewById(R.id.et_title);
		tvRequestStartDate = view.findViewById(R.id.tv_request_start_date);
		tvRequestStartDate.setOnClickListener(this);
		tvRequestEndDate = view.findViewById(R.id.tv_request_end_date);
		tvRequestEndDate.setOnClickListener(this);
		tvDealStartDate = view.findViewById(R.id.tv_deal_start_date);
		tvDealStartDate.setOnClickListener(this);
		tvDealEndDate = view.findViewById(R.id.tv_deal_end_date);
		tvDealEndDate.setOnClickListener(this);
		tvName = view.findViewById(R.id.tv_undertaker);
		tvRequestUser = view.findViewById(R.id.tv_request_user);
		view.findViewById(R.id.tv_ok).setOnClickListener(this);
		view.findViewById(R.id.tv_reset).setOnClickListener(this);

		initHandleChannel();

		initCaseProcess();

		initCaseRank();

		initCountyPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				Area area = countyList.get(options1);
				tvArea.setText(area.getName());
				mBean.setCaseArea(area);
			}
		});

		initBirthdayPicker(new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				switch (pickerFlag) {
					case "1":
						tvRequestStartDate.setText(getTime(date));
						mBean.setApplyBeginDate(getTime(date));
						break;
					case "2":
						tvRequestEndDate.setText(getTime(date));
						mBean.setApplyEndDate(getTime(date));
						break;
					case "3":
						tvDealStartDate.setText(getTime(date));
						mBean.setAcceptBeginDate(getTime(date));
						break;
					case "4":
						tvDealEndDate.setText(getTime(date));
						mBean.setAcceptEndDate(getTime(date));
						break;
					default:
						break;
				}
			}
		});
	}

	private void initHandleChannel() {
		ArrayList<TypeBean> handleChannelsList = new ArrayList<>();
		handleChannelsAdapter = new FilterAdapter(mActivity,
			handleChannelsList,
			HANDLE_CHANNEL);
		RecyclerView rvHandleChannels = view.findViewById(R.id.rv_handle_channels);
		rvHandleChannels.setAdapter(handleChannelsAdapter);
		Map<String, String> handleChannelMap = new HashMap<>(16);
		handleChannelMap.put(Constant.KEY, DictKey.HANDLE_CHANNEL);
		OkGo.<BaseBean<List<TypeBean>>>post(NetConstant.GET_TYPE)
			.cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
			.params(NetConstant.QUERY, new JSONObject(handleChannelMap).toString())
			.execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
				@Override
				public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
					List<TypeBean> list = response.body().getBody();
					handleChannelsAdapter.initData(list);
				}
			});
	}

	private void initCaseProcess() {
		ArrayList<TypeBean> caseProcessList = new ArrayList<>();
		caseProcessAdapter = new FilterAdapter(mActivity, caseProcessList, CASE_PROCESS);
		RecyclerView rvCaseProcess = view.findViewById(R.id.rv_case_process);
		rvCaseProcess.setAdapter(caseProcessAdapter);
		Map<String, String> caseProcessMap = new HashMap<>(16);
		caseProcessMap.put(Constant.KEY, DictKey.CASE_STATE);
		OkGo.<BaseBean<List<TypeBean>>>post(NetConstant.GET_TYPE)
			.cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
			.params(NetConstant.QUERY, new JSONObject(caseProcessMap).toString())
			.execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
				@Override
				public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
					List<TypeBean> list = response.body().getBody();
					caseProcessAdapter.initData(list);
				}
			});
	}

	private void initCaseRank() {
		ArrayList<TypeBean> caseRankList = new ArrayList<>();
		caseRankAdapter = new FilterAdapter(mActivity, caseRankList, CASE_RANK);
		RecyclerView rvCaseRank = view.findViewById(R.id.rv_important_level);
		rvCaseRank.setAdapter(caseRankAdapter);
		Map<String, String> map = new HashMap<>(16);
		map.put(Constant.KEY, Constant.CASE_RANK);
		OkGo.<BaseBean<List<TypeBean>>>post(NetConstant.GET_TYPE)
			.cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
			.params(NetConstant.QUERY, new JSONObject(map).toString())
			.execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
				@Override
				public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
					List<TypeBean> list = response.body().getBody();
					caseRankAdapter.initData(list);
				}
			});
	}

	@Override
	public void onBackPressed() {
		if (mPopupWindow.isShowing()) {
			mPopupWindow.dismiss();
		}
		super.onBackPressed();
	}

	@Override
	public void filterClick(TypeBean bean, int flag) {
		switch (flag) {
			case HANDLE_CHANNEL:
				mBean.setType(bean.getValue());
				if (!TextUtils.isEmpty(bean.getValue())) {
					getCaseCategory(bean.getValue());
				} else {
					caseCategoryAdapter.initData(new ArrayList<TypeBean>());
					mBean.setCaseType("");
				}
				break;
			case CASE_PROCESS:
				mBean.setState(bean.getValue());
				break;
			case CASE_RANK:
				mBean.setSeverity(bean.getValue());
				break;
			case CASE_CATEGORY:
				mBean.setCaseType(bean.getValue());
				break;
			default:
				break;
		}
	}

	private void getCaseCategory(String parentId) {
		ArrayList<TypeBean> caseCategoryList = new ArrayList<>();
		caseCategoryAdapter = new FilterAdapter(mActivity,
			caseCategoryList, CASE_CATEGORY);
		RecyclerView rvCaseCategory = view.findViewById(R.id.rv_case_category);
		rvCaseCategory.setAdapter(caseCategoryAdapter);
		Map<String, String> caseProcessMap = new HashMap<>(16);
		caseProcessMap.put(Constant.KEY, DictKey.HANDLE_CHANNEL);
		caseProcessMap.put("parentId", parentId);
		OkGo.<BaseBean<List<TypeBean>>>post(NetConstant.GET_SUB_TYPE)
			.cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
			.params(NetConstant.QUERY, new JSONObject(caseProcessMap).toString())
			.execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
				@Override
				public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
					List<TypeBean> list = response.body().getBody();
					caseCategoryAdapter.initData(list);
				}
			});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_ok:
				mBean.setTitle(tvTitle.getText().toString().trim());
				mBean.setTransactor(tvName.getText().toString().trim());
				mBean.setApplyUser(tvRequestUser.getText().toString().trim());
				mPopupWindow.dismiss();
				getIntegratedQueryData(Constant.INIT_DATA);
				break;
			case R.id.tv_reset:
				resetFilter();
				break;
			case R.id.view_mask:
				mPopupWindow.dismiss();
				break;
			case R.id.tv_area:
				showPickerView(countyPickerView);
				break;
			case R.id.tv_request_start_date:
				pickerFlag = "1";
				birthdayPickerView.show();
				break;
			case R.id.tv_request_end_date:
				pickerFlag = "2";
				birthdayPickerView.show();
				break;
			case R.id.tv_deal_start_date:
				pickerFlag = "3";
				birthdayPickerView.show();
				break;
			case R.id.tv_deal_end_date:
				pickerFlag = "4";
				birthdayPickerView.show();
				break;
			default:
				break;
		}
	}

	private void resetFilter() {
		caseCategoryAdapter.initData(new ArrayList<TypeBean>());
		mBean = new IntegrateQueryFilterBean();
		handleChannelsAdapter.reset();
		caseCategoryAdapter.reset();
		caseProcessAdapter.reset();
		tvArea.setText("");
		tvTitle.setText("");
		tvRequestEndDate.setText("");
		tvRequestEndDate.setText("");
		tvDealStartDate.setText("");
		tvDealEndDate.setText("");
		caseRankAdapter.reset();
		tvName.setText("");
		tvRequestUser.setText("");
	}
}

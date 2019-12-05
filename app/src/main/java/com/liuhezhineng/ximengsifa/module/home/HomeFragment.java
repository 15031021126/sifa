package com.liuhezhineng.ximengsifa.module.home;

import static com.liuhezhineng.ximengsifa.constant.NetConstant.PAGE_NO;

import android.Manifest.permission;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import butterknife.BindView;
import com.gcssloop.widget.PagerGridLayoutManager;
import com.gcssloop.widget.PagerGridLayoutManager.PageListener;
import com.gcssloop.widget.PagerGridSnapHelper;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.ArticleAdapter;
import com.liuhezhineng.ximengsifa.adapter.ServicesAdapter;
import com.liuhezhineng.ximengsifa.base.BaseFragment;
import com.liuhezhineng.ximengsifa.bean.ArticleBean;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.WeatherBean;
import com.liuhezhineng.ximengsifa.bean.dto.vo.ServerAppVo;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.module.IntegratedQueryActivity;
import com.liuhezhineng.ximengsifa.module.StatisticalAnalysisActivity;
import com.liuhezhineng.ximengsifa.utils.LocationUtils;
import com.liuhezhineng.ximengsifa.widget.FlowIndicator;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @description 主页fragment
 */
public class HomeFragment extends BaseFragment implements PageListener, OnClickListener {

	@BindView(R.id.rv_services)
	RecyclerView mRvServices;
	@BindView(R.id.view_flipper)
	ViewFlipper mViewFlipper;
	@BindView(R.id.rv_info)
	RecyclerView mRvArticle;
	@BindView(R.id.btn_float_phone)
	FloatingActionButton mBtnFloatPhone;
	@BindView(R.id.flow_indicator)
	FlowIndicator mFlowIndicator;
	@BindView(R.id.tv_change)
	TextView mTvChange;
	@BindView(R.id.iv_weather)
	ImageView mIvWeather;
	@BindView(R.id.tv_city)
	TextView mTvCity;
	@BindView(R.id.tv_weather)
	TextView mTvWeather;
	@BindView(R.id.tv_weather_temp)
	TextView mTvWeatherTemp;
	@BindView(R.id.tv_weather_date)
	TextView mTvWeatherDate;
	@BindView(R.id.tv_no_permission)
	TextView mTvNoPermission;
	@BindView(R.id.view_dividing)
	View mViewDividing;
	@BindView(R.id.refreshLayout)
	SmartRefreshLayout mRefreshLayout;
	@BindView(R.id.iv_weather_no_net)
	ImageView mIvWeatherNoNet;
	@BindView(R.id.rl_weather)
	RelativeLayout mRlWeather;
	LoginSuccessBroadCastReceiver mReceiver;
	@BindView(R.id.fl_message)
	FrameLayout mFlMessage;
	@BindView(R.id.fl_search)
	FrameLayout mFlSearch;
	@BindView(R.id.tv_title)
	TextView mTvTitle;
	private ServicesAdapter mServicesAdapter;
	private List<ServerAppVo> mServicesBeanList;
	private ArticleAdapter mArticleAdapter;
	private List mArticleList;
	private int pageNo;

	public static HomeFragment newInstance(@StringRes int moduleTitle) {
		HomeFragment fragment = new HomeFragment();
		Bundle args = new Bundle();
		args.putInt(Constant.MODULE_TITLE, moduleTitle);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mReceiver = new LoginSuccessBroadCastReceiver();
		IntentFilter filter = new IntentFilter(Constant.LOGIN_SUCCESS);
		mActivity.registerReceiver(mReceiver, filter);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
		@Nullable Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_home;
	}

	@Override
	protected void initView() {
		super.initView();
		initTitleBar();
		initHomeServices();
		initArticleList();
	}

	private void initTitleBar() {
		if (getArguments() != null) {
			mTvTitle.setText(getArguments().getInt(Constant.MODULE_TITLE));
		}
		mFlSearch.setVisibility(View.GONE);
		mFlMessage.setVisibility(View.GONE);
		mFlSearch.setOnClickListener(this);
		mFlMessage.setOnClickListener(this);
	}

	private void initHomeServices() {
		mServicesBeanList = new ArrayList<>();
		mServicesAdapter = new ServicesAdapter(mActivity, mServicesBeanList);
		mRvServices.setAdapter(mServicesAdapter);
		// 1.水平分页布局管理器
		PagerGridLayoutManager layoutManager = new PagerGridLayoutManager(
			2, 4, PagerGridLayoutManager.HORIZONTAL);
		mRvServices.setLayoutManager(layoutManager);
		// 2.设置滚动辅助工具
		PagerGridSnapHelper pageSnapHelper = new PagerGridSnapHelper();
		pageSnapHelper.attachToRecyclerView(mRvServices);
		// 设置页面变化监听器
		layoutManager.setPageListener(this);
	}

	private void initArticleList() {
		mArticleList = new ArrayList();
		mArticleAdapter = new ArticleAdapter(mActivity, mArticleList);
		mRvArticle.setAdapter(mArticleAdapter);
		mRvArticle.addItemDecoration(new DividerItemDecoration(mActivity, RecyclerView.VERTICAL));
		mRvArticle.setLayoutManager(new LinearLayoutManager(mActivity));
		mRvArticle.setNestedScrollingEnabled(false);
	}

	@Override
	protected void initData() {
		super.initData();

//		checkLocationPermission();
		mIvWeatherNoNet.setVisibility(View.GONE);
		mRlWeather.setVisibility(View.GONE);

		setRefreshLoadMoreListener();
		getHomeService();
		pageNo = 1;
		getArticleList(pageNo, NetConstant.NO_PAGE_SIZE, NetConstant.HOT, Constant.INIT_DATA);
		getArticleList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, NetConstant.ARTICLE,
			Constant.INIT_DATA);
	}

	@Override
	protected void setListener() {
		super.setListener();
		mBtnFloatPhone.setOnClickListener(this);
		mTvChange.setOnClickListener(this);
	}

	@Override
	protected void callbackSuccess(Object body) {
		if (body instanceof WeatherBean) {
			setWeather((WeatherBean) body);
		}
	}

	private void setWeather(WeatherBean weather) {
		mTvNoPermission.setVisibility(View.GONE);
		mViewDividing.setVisibility(View.VISIBLE);
		Picasso.get().load(NetConstant.BASE_URL + weather.getCond_icon())
			.into(mIvWeather);
		mTvCity.setText(weather.getLocation());
		mTvWeather.setText(weather.getCond_txt());
		mTvWeatherTemp.setText(weather.getTmp() + "℃");
		mTvWeatherDate.setText(getCurrentDate());
	}

	private String getCurrentDate() {
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 EEEE");
		return format.format(date);
	}

	private void setRefreshLoadMoreListener() {
		mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
			@Override
			public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
				getArticleList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, NetConstant.ARTICLE,
					Constant.LOAD_MORE);
			}

			@Override
			public void onRefresh(@NonNull RefreshLayout refreshLayout) {
				initData();
			}
		});
	}

	private void checkLocationPermission() {
		if (ActivityCompat.checkSelfPermission(mActivity, permission.ACCESS_FINE_LOCATION)
			!= PackageManager.PERMISSION_GRANTED
			&& ActivityCompat.checkSelfPermission(mActivity, permission.ACCESS_COARSE_LOCATION)
			!= PackageManager.PERMISSION_GRANTED) {
			requestPermissions(
				new String[]{permission.ACCESS_COARSE_LOCATION, permission.ACCESS_FINE_LOCATION},
				Constant.LOCATION_PERMISSION);
		} else {
			getWeatherInfo();
		}
	}

	private void getWeatherInfo() {
		String location = LocationUtils.getLngAndLat(mActivity);
		Map<String, String> map = new HashMap<>(16);
		map.put(NetConstant.LOCATION, location);
		OkGo.<BaseBean<WeatherBean>>post(NetConstant.GET_WEATHER_INFO)
			.params(NetConstant.QUERY, new JSONObject(map).toString())
			.execute(new JsonCallback<BaseBean<WeatherBean>>() {
				@Override
				public void onError(Response<BaseBean<WeatherBean>> response) {
					super.onError(response);
					mIvWeatherNoNet.setVisibility(View.VISIBLE);
					mRlWeather.setVisibility(View.GONE);
				}

				@Override
				public void onSuccess(Response<BaseBean<WeatherBean>> response) {
					setWeather(response.body().getBody());
					mIvWeatherNoNet.setVisibility(View.GONE);
					mRlWeather.setVisibility(View.VISIBLE);
				}
			});
	}

	private void getHomeService() {
		OkGo.<BaseBean<List<ServerAppVo>>>post(NetConstant.GET_HOME_SERVICES)
			.cacheTime(NetConstant.CACHE_TIME)
			.cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
			.execute(new JsonCallback<BaseBean<List<ServerAppVo>>>() {

				@Override
				public void onSuccess(Response<BaseBean<List<ServerAppVo>>> response) {
					mServicesAdapter.initData(response.body().getBody());
				}
			});
	}

	@Override
	public void onPageSizeChanged(int pageSize) {
		if (pageSize <= 1) {
			mFlowIndicator.setVisibility(View.GONE);
		} else {
			mFlowIndicator.setVisibility(View.VISIBLE);
			mFlowIndicator.setCount(pageSize);
		}
	}

	@Override
	public void onPageSelect(int pageIndex) {
		mFlowIndicator.setFocus(pageIndex);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (mViewFlipper != null) {
			if (hidden) {
				mViewFlipper.stopFlipping();
			} else {
				mViewFlipper.startFlipping();
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
		@NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (Constant.LOCATION_PERMISSION == requestCode) {
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED
				&& grantResults[1] == PackageManager.PERMISSION_GRANTED) {
				getWeatherInfo();
			} else {
				mTvNoPermission.setVisibility(View.VISIBLE);
				mViewDividing.setVisibility(View.GONE);
			}
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		if (mReceiver != null) {
			mActivity.unregisterReceiver(mReceiver);
		}
	}

	class LoginSuccessBroadCastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			getHomeService();
		}
	}

	private void getArticleList(final int pageNo, final int pageSize, final String categoryType,
		final int action) {
		Map<String, String> map = new HashMap<>(16);
		map.put(PAGE_NO, String.valueOf(pageNo));
		map.put(NetConstant.PAGE_SIZE, String.valueOf(pageSize));
		map.put(NetConstant.CATEGORY_TYPE, categoryType);
		OkGo.<BaseBean<PageBean<ArticleBean>>>post(NetConstant.GET_ARTICLE)
			.params(NetConstant.QUERY, new JSONObject(map).toString())
			.execute(new JsonCallback<BaseBean<PageBean<ArticleBean>>>(mActivity) {
				@Override
				public void onError(Response<BaseBean<PageBean<ArticleBean>>> response) {
					super.onError(response);
					if (isUnbind) {
						return;
					}
					if (mRefreshLayout != null) {
						if (action == Constant.LOAD_MORE) {
							mRefreshLayout.finishLoadMore(false);
						} else {
							mRefreshLayout.finishRefresh(false);
						}
					}
				}

				@Override
				public void onSuccess(Response<BaseBean<PageBean<ArticleBean>>> response) {
					if (isUnbind) {
						return;
					}
					PageBean<ArticleBean> pageBean = response.body().getBody();
					List<ArticleBean> list = pageBean.getList();
					if (NetConstant.HOT.equals(categoryType)) {
						for (ArticleBean bean : list) {
							addFlipperView(bean.getTitle(), bean);
						}
					} else if (NetConstant.ARTICLE.equals(categoryType)) {
						boolean isMore = pageBean.getCount() > pageNo * pageSize;
						if (!isMore) {
							if (mRefreshLayout != null) {
								mRefreshLayout.setNoMoreData(true);
							}
						}
						if (Constant.LOAD_MORE == action) {
							if (mRefreshLayout != null) {
								mRefreshLayout.finishLoadMore();
							}
							mArticleAdapter.addData(list);
						} else {
							if (mRefreshLayout != null) {
								mRefreshLayout.finishRefresh();
							}
							mArticleAdapter.initData(list);
						}
						HomeFragment.this.pageNo++;
					}
				}
			});
	}

	private void addFlipperView(String title, final ArticleBean bean) {
		View view = View.inflate(mActivity, R.layout.view_flipper_item, null);
		TextView tvHot = view.findViewById(R.id.tv_hot);
		tvHot.setText(title);
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				ArticleDetailsActivity.actionStart(mActivity, bean);
			}
		});
		mViewFlipper.addView(view);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.fl_message:
				StatisticalAnalysisActivity.actionStart(mActivity);
				break;
			case R.id.fl_search:
				IntegratedQueryActivity.actionStart(mActivity);
				break;
			case R.id.btn_float_phone:
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:12348"));
				startActivity(intent);
				break;
			case R.id.tv_change:
				pageNo = 1;
				getArticleList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, NetConstant.ARTICLE,
					Constant.INIT_DATA);
				break;
			case R.id.tv_load_more:
				getArticleList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, NetConstant.ARTICLE,
					Constant.LOAD_MORE);
				break;
			default:
				break;
		}
	}
}
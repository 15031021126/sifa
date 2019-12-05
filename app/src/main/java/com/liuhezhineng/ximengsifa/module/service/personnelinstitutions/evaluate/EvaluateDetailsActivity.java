package com.liuhezhineng.ximengsifa.module.service.personnelinstitutions.evaluate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.EvaluateDetailsAdapter;
import com.liuhezhineng.ximengsifa.adapter.ServiceTypeAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.EvaluateBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.personal.PersonInsBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

interface TypeFilterListener {

	/**
	 * 案件类型筛选
	 *
	 * @param bean TypeBean
	 */
	void typeFilter(TypeBean bean);
}

/**
 * @author AIqinfeng
 * @description 评价详情
 */
public class EvaluateDetailsActivity extends BaseActivity implements TypeFilterListener {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.iv_avatar)
	ImageView mIvAvatar;
	@BindView(R.id.tv_name)
	TextView mTvName;
	@BindView(R.id.tv_work_year)
	TextView mTvWorkYear;
	@BindView(R.id.tv_person_type)
	TextView mTvPersonType;
	@BindView(R.id.tv_no)
	TextView mTvNo;
	@BindView(R.id.tv_phone)
	TextView mTvPhone;
	@BindView(R.id.rv_evaluate)
	RecyclerView mRvEvaluate;
	@BindView(R.id.refreshLayout)
	SmartRefreshLayout mRefreshLayout;

	@BindView(R.id.ll_case_type)
	LinearLayout mLlCaseType;
	@BindView(R.id.ll_rating)
	LinearLayout mLlRating;
	@BindView(R.id.ll_date)
	LinearLayout mLlDate;
	@BindView(R.id.tv_case_type)
	TextView mTvCaseType;
	@BindView(R.id.tv_rating)
	TextView mTvRating;
	@BindView(R.id.tv_date)
	TextView mTvDate;

	ArrayList<EvaluateBean> mList;
	EvaluateDetailsAdapter mAdapter;
	PersonInsBean mBean;
	@BindView(R.id.iv_case_type)
	ImageView mIvCaseType;
	@BindView(R.id.iv_rating)
	ImageView mIvRating;
	@BindView(R.id.iv_date)
	ImageView mIvDate;
	@BindView(R.id.view_mask)
	View mViewMask;
	@BindView(R.id.fl_no_data)
	FrameLayout mFlNoData;
	/**
	 * 用来获取评价列表
	 */
	private String id;
	/**
	 * 用来获取人员详情
	 */
	private String agencyId;
	private int pageNo;
	private boolean flag;
	private PopupWindow mPopupWindow;
	private PopupWindow mRatingWindow;
	private PopupWindow mDateWindow;
	private String type;
	private String prescription;
	private String beginDate;
	private String endDate;

	public static void actionStart(Context context, PersonInsBean bean) {
		Intent intent = new Intent(context, EvaluateDetailsActivity.class);
		intent.putExtra(Constant.BEAN, bean);
		context.startActivity(intent);
	}

	@Override
	@OnClick({R.id.ll_case_type, R.id.ll_rating, R.id.ll_date})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		mTvCaseType.setTextColor(Color.BLACK);
		mTvRating.setTextColor(Color.BLACK);
		mTvDate.setTextColor(Color.BLACK);
		mIvCaseType.setBackgroundResource(R.drawable.arrow_up);
		mIvRating.setBackgroundResource(R.drawable.arrow_up);
		mIvDate.setBackgroundResource(R.drawable.arrow_up);
		switch (view.getId()) {
			case R.id.ll_case_type:
				if (mPopupWindow != null && mPopupWindow.isShowing()) {
					mPopupWindow.dismiss();
				} else {
					mTvCaseType.setTextColor(getResources().getColor(R.color.blue));
					mIvCaseType.setBackgroundResource(R.drawable.arrow_down);
					showPopupWindow(mPopupWindow);
				}
				break;
			case R.id.ll_rating:
				if (mRatingWindow != null && mRatingWindow.isShowing()) {
					mRatingWindow.dismiss();
				} else {
					mTvRating.setTextColor(getResources().getColor(R.color.blue));
					mIvRating.setBackgroundResource(R.drawable.arrow_down);
					showPopupWindow(mRatingWindow);
				}
				break;
			case R.id.ll_date:
				if (mDateWindow != null && mDateWindow.isShowing()) {
					mDateWindow.dismiss();
				} else {
					mTvDate.setTextColor(getResources().getColor(R.color.blue));
					mIvDate.setBackgroundResource(R.drawable.arrow_down);
					showPopupWindow(mDateWindow);
				}
				break;
			default:
				break;
		}
	}

	private void showPopupWindow(PopupWindow window) {
		backgroundAlpha(0.5f);
		mRatingWindow.dismiss();
		mPopupWindow.dismiss();
		mDateWindow.dismiss();
		mViewMask.setVisibility(View.VISIBLE);
		window.showAsDropDown(mLlCaseType, 0, 0, Gravity.CENTER_HORIZONTAL);
	}

	/**
	 * 设置添加屏幕的背景透明度
	 */
	public void backgroundAlpha(float bgAlpha) {
//		WindowManager.LayoutParams lp = getWindow().getAttributes();
//		// 0.0-1.0
//		lp.alpha = bgAlpha;
//		getWindow().setAttributes(lp);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_evaluate_details;
	}

	@Override
	protected void initView() {
		super.initView();
		initTopBar(mTopBar, "满意度详情");
		mBean = (PersonInsBean) getIntent().getSerializableExtra(Constant.BEAN);
		id = mBean.getId();
		showPersonInfo();
		mList = new ArrayList<>();
		mAdapter = new EvaluateDetailsAdapter(mActivity, mList);
		mRvEvaluate.setAdapter(mAdapter);
		mRvEvaluate.addItemDecoration(
			new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
	}

	private void showPersonInfo() {
		Picasso.get().load(NetConstant.FILE_URL + mBean.getImageUrl())
			.placeholder(R.drawable.default_person)
			.into(mIvAvatar);
		mTvName.setText(mBean.getPersonName());
		try {
			int practisingYear = Integer.parseInt(mBean.getPractisingYear());
			mTvWorkYear.setText(mBean.getPractisingYear() + "年");
		} catch (Exception e) {
			mTvWorkYear.setText("暂无");
		}
		mTvPersonType.setText("人员类别  " + mBean.getRoleId());
		mTvNo.setText("执业证号  " + mBean.getNo());
		mTvPhone.setText("联系电话  " + mBean.getAgencyPhone());
	}

	@Override
	protected void initData() {
		super.initData();

		initCategoryPopupWindow(1);
		initRatingPopupWindow();
		initDateFilterPopupWindow();

		pageNo = 1;
		getEvaluateList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, id,
			Constant.INIT_DATA);
	}

	@Override
	protected void setListener() {
		super.setListener();

		setRefreshLoadMoreListener();
	}

	private void setRefreshLoadMoreListener() {
		mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
			@Override
			public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
				pageNo++;
				getEvaluateList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, id,
					Constant.LOAD_MORE);
			}

			@Override
			public void onRefresh(@NonNull RefreshLayout refreshLayout) {
				pageNo = 1;
				getEvaluateList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, id,
					Constant.REFRESH);
			}
		});
	}

	private void getEvaluateList(final int pageNo, final int pageSize, String agencyId,
		final int action) {
		getEvaluateList(pageNo, pageSize, agencyId, type, prescription, beginDate, endDate, action);
	}

	private void getEvaluateList(final int pageNo, final int pageSize, String agencyId,
		String type, String prescription, String beginDate, String endDate, final int action) {
		/*
		 "pageNo":"1",
		 "pageSize":"2",
		 "id":"8cbc7c5f48f511e882b36c92bf3591ae",
		 "type":"1",
		 "prescription":"3",
		 "beginDate":"2018-05-16",
		 "endDate":"2018-06-28"
		 */
		mFlNoData.setVisibility(View.GONE);
		Map<String, String> map = new HashMap<>();
		map.put("pageNo", String.valueOf(pageNo));
		map.put("pageSize", String.valueOf(pageSize));
		map.put("id", agencyId);
		map.put("type", type);
		map.put("prescription", prescription);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		String queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<PageBean<EvaluateBean>>>post(NetConstant.GET_EVALUATION)
			.params(NetConstant.QUERY, queryStr)
			.execute(new DialogCallBack<BaseBean<PageBean<EvaluateBean>>>(mActivity) {
				@Override
				public void onSuccess(Response<BaseBean<PageBean<EvaluateBean>>> response) {
					PageBean<EvaluateBean> pageBean = response.body().getBody();
					mRefreshLayout.setNoMoreData(pageBean.getCount() <= pageNo * pageSize);
					List<EvaluateBean> list = pageBean.getList();
					if (pageNo == 1 && list.size() <= 0) {
						mFlNoData.setVisibility(View.VISIBLE);
					} else {
						mFlNoData.setVisibility(View.GONE);
					}
					switch (action) {
						case Constant.INIT_DATA:
							mAdapter.initData(list);
							break;
						case Constant.LOAD_MORE:
							mRefreshLayout.finishLoadMore();
							mAdapter.addData(list);
							break;
						case Constant.REFRESH:
							mRefreshLayout.finishRefresh();
							mAdapter.initData(list);
							break;
						default:
							break;
					}
				}

				@Override
				public void onError(Response<BaseBean<PageBean<EvaluateBean>>> response) {
					super.onError(response);
					if (action == Constant.LOAD_MORE) {
						mRefreshLayout.finishLoadMore(false);
					} else {
						mRefreshLayout.finishRefresh(false);
					}
				}
			});
	}

	private void initCategoryPopupWindow(int flag) {
		mPopupWindow = new PopupWindow(mActivity);
		View view = LayoutInflater.from(this).inflate(R.layout.popup_window_category, null);
		mPopupWindow.setContentView(view);
		mPopupWindow.setHeight(LayoutParams.WRAP_CONTENT);
		mPopupWindow.setWidth(LayoutParams.MATCH_PARENT);
		mPopupWindow.setBackgroundDrawable(null);
		mPopupWindow.setOnDismissListener(new PopupDismissListener());
		RecyclerView rvLeft = view.findViewById(R.id.rv_left);
		RecyclerView rvRight = view.findViewById(R.id.rv_right);
		TextView tvCaseTypeAll = view.findViewById(R.id.tv_case_type_all);
		switch (flag) {
			case 1:
				rvRight.setVisibility(View.GONE);
				break;
			default:
				break;
		}
		tvCaseTypeAll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mPopupWindow.dismiss();
				type = "";
				mTvCaseType.setText("全部类别");
				filterData();
			}
		});

		ArrayList<TypeBean> list = new ArrayList<>();
		final ServiceTypeAdapter adapter = new ServiceTypeAdapter(mActivity, list);
		rvLeft.setAdapter(adapter);
		rvLeft.addItemDecoration(
			new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
		Map<String, String> map = new HashMap<>(16);
		map.put(Constant.KEY, "evaluate_type");
		OkGo.<BaseBean<List<TypeBean>>>post(NetConstant.GET_TYPE)
			.cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
			.params(NetConstant.QUERY, new JSONObject(map).toString())
			.execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
				@Override
				public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
					List<TypeBean> list = response.body().getBody();
					adapter.initData(list);
				}
			});
	}

	@Override
	public void typeFilter(TypeBean bean) {
		mPopupWindow.dismiss();
		type = bean.getValue();
		mTvCaseType.setText(bean.getLabel());
		filterData();
	}

	private void filterData() {
		pageNo = 1;
		getEvaluateList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, id, type,
			prescription, beginDate, endDate, Constant.INIT_DATA);
	}

	private void initRatingPopupWindow() {
		mRatingWindow = new PopupWindow(mActivity);
		View view = LayoutInflater.from(this).inflate(R.layout.filter_rating_window, null);
		mRatingWindow.setContentView(view);
		mRatingWindow.setHeight(LayoutParams.WRAP_CONTENT);
		mRatingWindow.setWidth(LayoutParams.MATCH_PARENT);
		mRatingWindow.setBackgroundDrawable(null);
		mRatingWindow.setOnDismissListener(new PopupDismissListener());

		setRatingFilterListener(view, R.id.tv_rating_all, "0");
		setRatingFilterListener(view, R.id.tv_rating_one, "1");
		setRatingFilterListener(view, R.id.tv_rating_two, "2");
		setRatingFilterListener(view, R.id.tv_rating_three, "3");
		setRatingFilterListener(view, R.id.tv_rating_four, "4");
		setRatingFilterListener(view, R.id.tv_rating_five, "5");
	}

	private void setRatingFilterListener(View view, @IdRes int id, final String rating) {
		view.findViewById(id).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("0".equals(rating)) {
					prescription = "";
					mTvRating.setText("全部星级");
				} else {
					prescription = rating;
					mTvRating.setText(rating + "星");
				}
				filterData();
				mRatingWindow.dismiss();
			}
		});
	}

	private void initDateFilterPopupWindow() {
		View contentView = LayoutInflater.from(mActivity)
			.inflate(R.layout.filter_date_and_title, null, false);
		mDateWindow = new PopupWindow(contentView,
			ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		mRatingWindow.setBackgroundDrawable(null);
		mDateWindow.setOnDismissListener(new PopupDismissListener());
		contentView.findViewById(R.id.ll_title).setVisibility(View.GONE);
		final TextView tvStartDate = contentView.findViewById(R.id.tv_start_date);
		final TextView tvEndDate = contentView.findViewById(R.id.tv_end_date);
		initBirthdayPicker(new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				if (flag) {
					tvStartDate.setText(getTime(date));
					beginDate = getTime(date);
				} else {
					tvEndDate.setText(getTime(date));
					endDate = getTime(date);
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
				mDateWindow.dismiss();
				filterData();
			}
		});
		contentView.findViewById(R.id.tv_reset).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tvStartDate.setText("");
				beginDate = "";
				tvEndDate.setText("");
				endDate = "";
			}
		});
	}

	/**
	 * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
	 *
	 * @author cg
	 */
	class PopupDismissListener implements PopupWindow.OnDismissListener {

		@Override
		public void onDismiss() {
			mViewMask.setVisibility(View.GONE);
			backgroundAlpha(1f);
		}
	}
}

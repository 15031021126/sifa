package com.liuhezhineng.ximengsifa.module.service.article;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.ArticleListAdapter;
import com.liuhezhineng.ximengsifa.base.BaseFragment;
import com.liuhezhineng.ximengsifa.bean.ArticleBean;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @description 文章列表页面
 */
public class ArticleFragment extends BaseFragment {

	private static final String ARG_PARAM1 = "param1";

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.rv)
	RecyclerView mRv;
	@BindView(R.id.refreshLayout)
	SmartRefreshLayout mRefreshLayout;
	@BindView(R.id.fl_no_data)
	FrameLayout mFlNoData;

	String sourceId;
	List<ArticleBean> mList;
	private ArticleListAdapter mAdapter;
	private int pageNo;

	// filter
	private boolean isFilter;
	private String beginDate = "";
	private String endDate = "";
	private String title = "";

	public ArticleFragment() {
		// Required empty public constructor
	}

	public static ArticleFragment newInstance(String param1) {
		ArticleFragment fragment = new ArticleFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		fragment.setArguments(args);
		return fragment;
	}

	public void filterData(String beginDate, String endDate, String title) {
		isFilter = true;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.title = title;
		pageNo = 1;
		getArticleList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, beginDate, endDate, title,
			Constant.INIT_DATA);
	}

	private void getArticleList(final int pageNo, final int pageSize, String beginDate,
		String endDate, String title, final int action) {
		/* "pageNo":1,
		"pageSize":"2",
        "categoryId":"658ace3630e94c5487664211280c183b",
        "isAll":false,
        "type":"1",
        "beginDate":"2018-05-16",
        "endDate":"2018-05-16",
        "title":"111"*/
		mFlNoData.setVisibility(View.GONE);
		final Map<String, String> map = new HashMap<>(16);
		map.put(NetConstant.PAGE_NO, String.valueOf(pageNo));
		map.put(NetConstant.PAGE_SIZE, String.valueOf(pageSize));
		map.put(NetConstant.CATEGORY_ID, sourceId);
		map.put(Constant.BEGIN_DATE, beginDate);
		map.put(Constant.END_DATE, endDate);
		map.put(Constant.TITLE, title);
		String queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<PageBean<ArticleBean>>>post(NetConstant.GET_LIST_BY_CATEGORY_ID)
			.params(NetConstant.QUERY, queryStr)
			.execute(new DialogCallBack<BaseBean<PageBean<ArticleBean>>>(mActivity) {

				@Override
				public boolean isShowLoadingDialog() {
					return isFilter;
				}

				@Override
				public void onFinish() {
					super.onFinish();
					isFilter = false;
				}

				@Override
				public void onSuccess(Response<BaseBean<PageBean<ArticleBean>>> response) {
					if (isUnbind) {
						return;
					}
					PageBean<ArticleBean> pageBean = response.body().getBody();
					if (mRefreshLayout != null) {
						mRefreshLayout.setNoMoreData(pageBean.getCount() <= pageNo * pageSize);
					}
					if (pageNo == 1 && pageBean.getCount() == 0) {
						mFlNoData.setVisibility(View.VISIBLE);
					} else {
						mFlNoData.setVisibility(View.GONE);
					}
					List<ArticleBean> list = pageBean.getList();
					switch (action) {
						case Constant.INIT_DATA:
							mAdapter.initData(list);
							break;
						case Constant.REFRESH:
							mAdapter.initData(list);
							mRefreshLayout.finishRefresh();
							break;
						case Constant.LOAD_MORE:
							mAdapter.addData(list);
							mRefreshLayout.finishLoadMore();
							break;
						default:
							break;
					}
				}
			});
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_article;
	}

	@Override
	protected void initView() {
		super.initView();

		mTopBar.setVisibility(View.GONE);
		if (getArguments() != null) {
			sourceId = getArguments().getString(ARG_PARAM1);
		}

		mList = new ArrayList<>();
		mAdapter = new ArticleListAdapter(mActivity, mList);
		mRv.setAdapter(mAdapter);
	}

	@Override
	protected void initData() {
		super.initData();
		pageNo = 1;
		getArticleList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
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
				getArticleList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.LOAD_MORE);
			}

			@Override
			public void onRefresh(@NonNull RefreshLayout refreshLayout) {
				pageNo = 1;
				beginDate = "";
				endDate = "";
				title = "";
				getArticleList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.REFRESH);
			}
		});
	}

	private void getArticleList(final int pageNo, final int pageSize, final int action) {
		getArticleList(pageNo, pageSize, beginDate, endDate, title, action);
	}
}

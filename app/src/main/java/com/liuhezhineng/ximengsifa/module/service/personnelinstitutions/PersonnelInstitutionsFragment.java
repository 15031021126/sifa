package com.liuhezhineng.ximengsifa.module.service.personnelinstitutions;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.PersonnelInsAdapter;
import com.liuhezhineng.ximengsifa.base.BaseFragment;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.personal.DictList;
import com.liuhezhineng.ximengsifa.bean.personal.PersonInsBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author AIqinfeng
 * @description 人员机构列表
 */
public class PersonnelInstitutionsFragment extends BaseFragment {

	private static final String ARG_PARAM1 = "param1";
	@BindView(R.id.rv_list)
	RecyclerView mRvList;
	@BindView(R.id.refreshLayout)
	SmartRefreshLayout mRefreshLayout;
	@BindView(R.id.fl_no_data)
	FrameLayout mFlNoData;
	@BindView(R.id.empty_view)
	QMUIEmptyView mEmptyView;

	DictList mDictList;
	String categoryId;
	private String serviceName;
	private PersonnelInsAdapter mAdapter;
	private ArrayList<PersonInsBean> mList;

	private int pageNo;
	private String isEvaluate = "false";

	private boolean isFilter;
	private String countyId = "";
	private String townId = "";
	private String name = "";
	private String isMongolian = "";
	private String isAidLawyerMongolian = "";
	/**
	 * "query":{
	 * "categoryId":"1",
	 * "pageNo":"1",
	 * "pageSize":"4",
	 * "serviceName":"事务所",
	 * "areaId":"1",
	 * "isEvaluate":"true",
	 * "townId":"23",
	 * "isMongolian":"1"
	 * "isAidLawyer":"0"
	 * }
	 */
	private String queryStr;

	public PersonnelInstitutionsFragment() {
	}

	public static PersonnelInstitutionsFragment newInstance(DictList param1) {
		PersonnelInstitutionsFragment fragment = new PersonnelInstitutionsFragment();
		Bundle args = new Bundle();
		args.putSerializable(ARG_PARAM1, param1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
		@Nullable Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onDestroyView() {
		if (mEmptyView != null && mEmptyView.isShowing()) {
			mEmptyView.hide();
		}
		super.onDestroyView();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_personnel_institutions;
	}

	@Override
	protected void initView() {
		super.initView();

		if ("满意度评价".equals(((PersonnelInstitutionsActivity) mActivity).mServerAppVo.getLink())) {
			isEvaluate = "true";
		} else {
			isEvaluate = "false";
		}

		if (getArguments() != null) {
			mDictList = (DictList) getArguments().getSerializable(ARG_PARAM1);
			if (mDictList != null) {
				categoryId = mDictList.getValue();
				serviceName = mDictList.getLabel();
			}
		}

		mList = new ArrayList<>();
		mAdapter = new PersonnelInsAdapter(mActivity, mList, serviceName, categoryId);
		mRvList.setAdapter(mAdapter);
		mRvList.addItemDecoration(
			new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
	}

	@Override
	protected void initData() {
		super.initData();

		pageNo = 1;
		isFilter = true;
		mEmptyView.show(true);

		getPersonList(categoryId, pageNo, NetConstant.DEFAULT_PAGE_SIZE,
			Constant.INIT_DATA);
	}

	@Override
	protected void onVisible() {
		super.onVisible();

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
				getPersonList(categoryId, ++pageNo, NetConstant.DEFAULT_PAGE_SIZE,
					Constant.LOAD_MORE);
			}

			@Override
			public void onRefresh(@NonNull RefreshLayout refreshLayout) {
				pageNo = 1;
				countyId = "";
				townId = "";
				isMongolian = "";
				isAidLawyerMongolian = "";
				name = "";
				getPersonList(categoryId, pageNo, NetConstant.DEFAULT_PAGE_SIZE,
					Constant.REFRESH);
			}
		});
	}

	private void getPersonList(String categoryId, final int pageNo, final int pageSize,
		final int action) {
		getPersonList(categoryId, pageNo, pageSize, name, countyId, townId, isMongolian,
			isAidLawyerMongolian, isEvaluate,
			action);
	}

	private void getPersonList(final String categoryId, final int pageNo, final int pageSize,
		String name, String areaId, String townId, String isMongolian, String isAidLawyerMongolian,
		String isEvaluate,
		final int action) {
		mFlNoData.setVisibility(View.GONE);
		Map<String, String> map = new HashMap<>(16);
		map.put(NetConstant.CATEGORY_ID, categoryId);
		map.put(NetConstant.NAME, name);
		map.put(NetConstant.AREA_ID, areaId);
		map.put("isEvaluate", isEvaluate);
		map.put("townId", townId);
		map.put("isAidLawyer", isAidLawyerMongolian);
		map.put("isMongolian", isMongolian);
		map.put(NetConstant.PAGE_NO, String.valueOf(pageNo));
		map.put(NetConstant.PAGE_SIZE, String.valueOf(pageSize));
		queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<PageBean<PersonInsBean>>>post(NetConstant.GET_INSTITUTIONS)
//			.cacheKey(queryStr)
//			.cacheTime(1000 * 60 * 5)
//			.cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
			.params(NetConstant.QUERY, queryStr)
			.execute(new JsonCallback<BaseBean<PageBean<PersonInsBean>>>(mActivity) {
//
//				@Override
//				public boolean isShowLoadingDialog() {
//					return isFilter;
//				}

				@Override
				public void onFinish() {
					super.onFinish();
					isFilter = false;
					if (isUnbind) {
						return;
					}
					if (mEmptyView != null && mEmptyView.isShowing()) {
						mEmptyView.hide();
					}
				}

				@Override
				public void onError(Response<BaseBean<PageBean<PersonInsBean>>> response) {
					super.onError(response);
					if (isUnbind) {
						return;
					}
					if (action == Constant.LOAD_MORE) {
						mRefreshLayout.finishLoadMore(false);
					} else {
						mRefreshLayout.finishRefresh(false);
					}
				}

				@Override
				public void onSuccess(Response<BaseBean<PageBean<PersonInsBean>>> response) {
//					if (isUnbind) {
//						return;
//					}
					PageBean<PersonInsBean> body = response.body().getBody();
					mRefreshLayout.setNoMoreData(body.getCount() <= pageNo * pageSize);
					List<PersonInsBean> list = body.getList();
					if (pageNo == 1 && list.size() == 0) {
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
			});
	}

	public void filterData(String name, String countyId, String townId, String isMongolian,
		String isAidLawyerMongolian) {
		pageNo = 1;
		isFilter = true;
		this.name = name;
		this.countyId = countyId;
		this.townId = townId;
		this.isMongolian = isMongolian;
		this.isAidLawyerMongolian = isAidLawyerMongolian;
		getPersonList(categoryId, pageNo, NetConstant.DEFAULT_PAGE_SIZE,
			name, countyId, townId, isMongolian, isAidLawyerMongolian, isEvaluate,
			Constant.INIT_DATA);
	}
}

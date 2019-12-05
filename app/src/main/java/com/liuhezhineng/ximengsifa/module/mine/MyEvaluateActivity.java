package com.liuhezhineng.ximengsifa.module.mine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.MyEvaluateAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.evaluate.MyEvaluateBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
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
 * @description 我的评价页面
 */
public class MyEvaluateActivity extends BaseActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.rv_list)
	RecyclerView mRvList;
	@BindView(R.id.refreshLayout)
	SmartRefreshLayout mRefreshLayout;
	@BindView(R.id.fl_no_data)
	FrameLayout mFlNoData;

	ArrayList<MyEvaluateBean> mList;
	MyEvaluateAdapter mAdapter;
	private int pageNo = 1;

	private String beginDate;
	private String endDate;
	private String title;

	public static void actionStart(Context context) {
		if (UserHelper.isIsLogin()) {
			Intent intent = new Intent(context, MyEvaluateActivity.class);
			context.startActivity(intent);
		} else {
			gotoLogin(context);
		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_my_evaluate;
	}

	@Override
	protected void initView() {
		super.initView();
		initTopBar(mTopBar, "我的评价");

		mList = new ArrayList<>();
		mAdapter = new MyEvaluateAdapter(mActivity, mList);
		mRvList.setAdapter(mAdapter);
	}

	@Override
	protected void initData() {
		super.initData();

		getArchiveBusinessList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
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
				getArchiveBusinessList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.LOAD_MORE);
			}

			@Override
			public void onRefresh(@NonNull RefreshLayout refreshLayout) {
				pageNo = 1;
				beginDate = "";
				endDate = "";
				title = "";
				getArchiveBusinessList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.REFRESH);
			}
		});
	}

	private void getArchiveBusinessList(int pageNo, int pageSize, final int action) {
		getArchiveBusinessList("", pageNo, pageSize, beginDate, endDate, title, "2", action);
	}

	/**
	 * "procDefKey": "legal_aid",
	 * "pageNo": "1",
	 * "pageSize": "20",
	 * "beginDate": "2018-05-24",
	 * "endDate": "2018-05-24",
	 * "status":"2" ,    //1代表还在受理中，2代表已经归档了
	 * "title":"张强测试"   //案件标题
	 */
	private void getArchiveBusinessList(String procDefKey, final int pageNo, final int pageSize,
		String beginDate, String endDate, String title, String status, final int action) {
		mFlNoData.setVisibility(View.GONE);
		Map<String, String> map = new HashMap<>(16);
		map.put(Constant.PROC_DEF_KEY, "");
		map.put(Constant.PAGE_NO, String.valueOf(pageNo));
		map.put(Constant.PAGE_SIZE, String.valueOf(pageSize));
		map.put(Constant.BEGIN_DATE, beginDate);
		map.put(Constant.END_DATE, endDate);
		map.put(Constant.TITLE, title);
		map.put("status", status);
		String queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<PageBean<MyEvaluateBean>>>post(NetConstant.GET_COMMIT_BUSINESS)
			.params(NetConstant.QUERY, queryStr)
			.execute(new DialogCallBack<BaseBean<PageBean<MyEvaluateBean>>>(mActivity) {
				@Override
				public void onSuccess(Response<BaseBean<PageBean<MyEvaluateBean>>> response) {
					PageBean<MyEvaluateBean> pageBean = response.body().getBody();
					if (pageNo <= 1 && pageBean.getCount() <= 0) {
						mFlNoData.setVisibility(View.VISIBLE);
					} else {
						mFlNoData.setVisibility(View.GONE);
					}
					mRefreshLayout.setNoMoreData(pageBean.getCount() <= pageNo * pageSize);
					List<MyEvaluateBean> list = pageBean.getList();
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
				public void onError(Response<BaseBean<PageBean<MyEvaluateBean>>> response) {
					super.onError(response);
					if (mRefreshLayout != null) {
						if (action == Constant.LOAD_MORE) {
							mRefreshLayout.finishLoadMore(false);
						} else {
							mRefreshLayout.finishRefresh(false);
						}
					}
				}
			});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == Constant.NORMAL_CODE) {
			pageNo = 1;
			getArchiveBusinessList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
		}
	}
}

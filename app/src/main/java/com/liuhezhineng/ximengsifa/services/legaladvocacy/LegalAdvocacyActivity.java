package com.liuhezhineng.ximengsifa.services.legaladvocacy;

import static com.liuhezhineng.ximengsifa.constant.NetConstant.PAGE_NO;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.ArticleListAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.ArticleBean;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.dto.vo.ServerAppVo;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
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
 * @description 法律宣传子服务下列表页面
 */
public class LegalAdvocacyActivity extends BaseActivity {

	@BindView(R.id.rv)
	RecyclerView mRv;
	@BindView(R.id.refreshLayout)
	SmartRefreshLayout mRefreshLayout;
	@BindView(R.id.iv_no_data)
	ImageView mIvNoData;

	ServerAppVo mServerAppVo;
	List<ArticleBean> mList;
	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	private ArticleListAdapter mAdapter;
	private int pageNo;

	public static void actionStart(Context context, ServerAppVo serverAppVo) {
		Intent intent = new Intent(context, LegalAdvocacyActivity.class);
		intent.putExtra(Constant.SERVICE_APP_VO, serverAppVo);
		context.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_article;
	}

	@Override
	protected void initView() {
		super.initView();
		mServerAppVo = (ServerAppVo) getIntent().getSerializableExtra(Constant.SERVICE_APP_VO);

		initTopBar(mTopBar, mServerAppVo.getLink());
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
				mIvNoData.setVisibility(View.GONE);
				getArticleList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.REFRESH);
			}
		});
	}

	private void getArticleList(final int pageNo, final int pageSize, final int action) {
		/* "pageNo":1,
	"pageSize":"2",
    "categoryId":"658ace3630e94c5487664211280c183b",
    "isAll":false,
    "type":"1",
    "beginDate":"2018-05-16",
    "endDate":"2018-05-16",
    "title":"111"*/
		final Map<String, String> map = new HashMap<>(16);
		map.put(PAGE_NO, String.valueOf(pageNo));
		map.put(NetConstant.PAGE_SIZE, String.valueOf(pageSize));
		map.put(NetConstant.CATEGORY_ID, mServerAppVo.getSourceId());
		String queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<PageBean<ArticleBean>>>post(NetConstant.GET_LIST_BY_CATEGORY_ID)
			.params(NetConstant.QUERY, queryStr)
			.execute(new JsonCallback<BaseBean<PageBean<ArticleBean>>>(mActivity) {
				@Override
				public void onSuccess(Response<BaseBean<PageBean<ArticleBean>>> response) {
					PageBean<ArticleBean> pageBean = response.body().getBody();
					mRefreshLayout.setNoMoreData(pageBean.getCount() <= pageNo * pageSize);

					List<ArticleBean> list = pageBean.getList();
					switch (action) {
						case Constant.INIT_DATA:
							if (list.size() == 0) {
								mRv.setVisibility(View.GONE);
								mIvNoData.setVisibility(View.VISIBLE);
							} else {
								mRv.setVisibility(View.VISIBLE);
								mIvNoData.setVisibility(View.GONE);
							}
							mAdapter.initData(list);
							break;
						case Constant.REFRESH:
							if (list.size() == 0) {
								mRv.setVisibility(View.GONE);
								mIvNoData.setVisibility(View.VISIBLE);
							} else {
								mRv.setVisibility(View.VISIBLE);
								mIvNoData.setVisibility(View.GONE);
							}
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
}

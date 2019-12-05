package com.liuhezhineng.ximengsifa.base;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import com.liuhezhineng.ximengsifa.R;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * @author AIqinfeng
 * @description 文章类活动
 */
public abstract class BaseArticleActivity extends BaseActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.refreshLayout)
	RefreshLayout mRefreshLayout;
	@BindView(R.id.iv_no_data)
	ImageView mIvNoData;

	private int pageNo;

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
				loadMore();
			}

			@Override
			public void onRefresh(@NonNull RefreshLayout refreshLayout) {
				pageNo = 1;
				mIvNoData.setVisibility(View.GONE);
				refreshData();
			}
		});
	}

	protected abstract void refreshData();

	protected abstract void loadMore();
}

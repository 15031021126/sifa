package com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.BindView;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.DraftAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.DraftBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.liuhezhineng.ximengsifa.widget.AndroidSevenPopupWindow;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @description 我的草稿
 */
public class MyDraftActivity extends BaseActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.rv_draft)
	RecyclerView mRvDraft;
	@BindView(R.id.refreshLayout)
	SmartRefreshLayout mRefreshLayout;
	@BindView(R.id.fl_no_data)
	FrameLayout mFlNoData;

	List<DraftBean> mList;
	private DraftAdapter mAdapter;
	private int pageNo;
	private String title = "";
	private String beginDate = "";
	private String endDate = "";
	private boolean flag;
	private PopupWindow mPopupWindow;

	public static void actionStart(Context context) {
		if (UserHelper.isIsLogin()) {
			Intent intent = new Intent(context, MyDraftActivity.class);
			context.startActivity(intent);
		} else {
			gotoLogin(context);
		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_my_draft;
	}

	@Override
	protected void initView() {
		super.initView();
		initTopBar(mTopBar, "我的草稿");
		mList = new ArrayList<>();
		mAdapter = new DraftAdapter(mActivity, mList);
		mRvDraft.setAdapter(mAdapter);
	}

	@Override
	protected void initData() {
		super.initData();
		pageNo = 1;
		getMyDraftList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
	}

	@Override
	protected void setListener() {
		super.setListener();
		setRefreshLoadMoreListener();

		initFilterWindow();
		mTopBar.addRightTextButton("筛选", R.id.btn_ok).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mPopupWindow != null && mPopupWindow.isShowing()) {
					mPopupWindow.dismiss();
				} else {
					mPopupWindow.showAsDropDown(mTopBar, 0, 0, Gravity.CENTER_HORIZONTAL);
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == DraftAdapter.DEL_DRAFT) {
			initData();
		}
	}

	private void setRefreshLoadMoreListener() {
		mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
			@Override
			public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
				getMyDraftList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.LOAD_MORE);
			}

			@Override
			public void onRefresh(@NonNull RefreshLayout refreshLayout) {
				pageNo = 1;
				title = "";
				beginDate = "";
				endDate = "";
				getMyDraftList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.REFRESH);
			}
		});
	}

	private void initFilterWindow() {
		View contentView = LayoutInflater.from(mActivity)
			.inflate(R.layout.filter_date_and_title, null);
		mPopupWindow = new AndroidSevenPopupWindow(contentView,
			LayoutParams.MATCH_PARENT,
			LayoutParams.WRAP_CONTENT);
		mPopupWindow.setFocusable(true);
		contentView.findViewById(R.id.view_mask).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mPopupWindow.dismiss();
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
				mPopupWindow.dismiss();
				beginDate = tvStartDate.getText().toString().trim();
				endDate = tvEndDate.getText().toString().trim();
				title = etTitle.getText().toString().trim();
				filterData(title, beginDate, endDate);
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

	private void getMyDraftList(int pageNo, int pageSize, int action) {
		getMyDraftList(pageNo, pageSize, title, beginDate, endDate, action);
	}

	private void filterData(String title, String beginDate, String endDate) {
		pageNo = 1;
		getMyDraftList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, title, beginDate, endDate,
			Constant.INIT_DATA);
	}

	private void getMyDraftList(final int pageNo, final int pageSize, String title,
		String beginDate,
		String endDate, final int action) {
		mFlNoData.setVisibility(View.GONE);

		//{"beginDate":"","endDate":"" 	"pageNo": "1", 	"pageSize": "2", 	"caseTitle": "" }
		java.util.Map<String, String> map = new HashMap<>(16);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("pageNo", String.valueOf(pageNo));
		map.put("pageSize", String.valueOf(pageSize));
		map.put("caseTitle", title);
		String queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<PageBean<DraftBean>>>post(NetConstant.GET_MY_DRAFT)
			.params(NetConstant.QUERY, queryStr)
			.execute(new DialogCallBack<BaseBean<PageBean<DraftBean>>>(mActivity) {
				@Override
				public void onSuccess(Response<BaseBean<PageBean<DraftBean>>> response) {
					PageBean<DraftBean> pageBean = response.body().getBody();
					if (pageNo <= 1 && pageBean.getCount() <= 0) {
						mFlNoData.setVisibility(View.VISIBLE);
					} else {
						mFlNoData.setVisibility(View.GONE);
					}
					mRefreshLayout.setNoMoreData(pageBean.getCount() <= pageNo * pageSize);
					List<DraftBean> list = pageBean.getList();
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
				public void onError(Response<BaseBean<PageBean<DraftBean>>> response) {
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
}

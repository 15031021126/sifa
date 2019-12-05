package com.liuhezhineng.ximengsifa.module.mine.order;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.OrderAdapter;
import com.liuhezhineng.ximengsifa.base.BaseFragment;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.order.OrderBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.liuhezhineng.ximengsifa.constant.NetConstant.PAGE_NO;

/**
 * @author AIqinfeng
 * @description 我的预约 代办/已办
 */
public class MyOrderFragment extends BaseFragment {

    private static final String BUSINESS_TYPE = "business_type";

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.fl_no_data)
    FrameLayout mFlNoData;

    List<OrderBean> mList;
    int pageNo;
    LocalBroadcastManager broadcastManager;
    IntentFilter intentFilter;
    BroadcastReceiver mReceiver;
    private OrderAdapter mAdapter;
    private String businessType;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MyOrderFragment() {
    }

    public static MyOrderFragment newInstance(String businessType) {
        MyOrderFragment fragment = new MyOrderFragment();
        Bundle args = new Bundle();
        args.putString(BUSINESS_TYPE, businessType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.DEAL_BUSINESS_CODE) {
            pageNo = 1;
            getOrderList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        broadcastManager = LocalBroadcastManager.getInstance(mActivity);
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.DEAL_BUSINESS);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(TAG, "onReceive: " + mFlNoData);
//				pageNo = 1;
//				getBusinessList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
            }
        };
        broadcastManager.registerReceiver(mReceiver, intentFilter);
    }

    private void getOrderList(final int pageNo, final int pageSize, final int action) {
        mFlNoData.setVisibility(View.GONE);
        String url = "";
        if ("我的预约".equals(businessType)) {
            url = NetConstant.Order.GET_MY_ORDER_LIST;
        } else {
            url = NetConstant.Order.GET_MY_TODO_ORDER_LIST;
        }
        final Map<String, String> map = new HashMap<>(16);
        map.put(PAGE_NO, String.valueOf(pageNo));
        map.put(NetConstant.PAGE_SIZE, String.valueOf(pageSize));
        OkGo.<BaseBean<PageBean<OrderBean>>>post(url)
                .params(NetConstant.QUERY, new JSONObject(map).toString())
                .execute(new DialogCallBack<BaseBean<PageBean<OrderBean>>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<OrderBean>>> response) {
                        if (isUnbind) {
                            return;
                        }
                        PageBean<OrderBean> pageBean = response.body().getBody();
                        if (pageNo <= 1 && pageBean.getCount() <= 0) {
                            mFlNoData.setVisibility(View.VISIBLE);
                        } else {
                            mFlNoData.setVisibility(View.GONE);
                        }
                        mRefreshLayout.setNoMoreData(pageBean.getCount() <= pageNo * pageSize);
                        List<OrderBean> list = pageBean.getList();
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
                    public void onError(Response<BaseBean<PageBean<OrderBean>>> response) {
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
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            businessType = getArguments().getString(BUSINESS_TYPE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business_list;
    }

    @Override
    protected void initView() {
        super.initView();

        mList = new ArrayList<>();
        mAdapter = new OrderAdapter(mActivity, mList, businessType);
        mRvList.setAdapter(mAdapter);
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    protected void initData() {
        super.initData();

        pageNo = 1;
        getOrderList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
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
                getOrderList(++pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.LOAD_MORE);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                getOrderList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.REFRESH);
            }
        });
    }
}

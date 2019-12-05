package com.liuhezhineng.ximengsifa;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.liuhezhineng.ximengsifa.adapter.LocalMsgAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.msg.LocalMsg;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
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

public class LocalMsgActivity extends BaseActivity {

    @BindView(R.id.rv_local_msg)
    RecyclerView mRvLocalMsg;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.fl_no_data)
    FrameLayout mFlNoData;

    int pageNo;
    List<LocalMsg> mList;
    LocalMsgAdapter mAdapter;

    public static void actionStart(Context context) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, LocalMsgActivity.class);
            context.startActivity(intent);
        } else {
            gotoLogin(context);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_local_msg;
    }

    @Override
    protected void initView() {
        super.initView();

        initTopBar(R.string.msg);

        mList = new ArrayList<>();
        mAdapter = new LocalMsgAdapter(mActivity, mList);
        mRvLocalMsg.setAdapter(mAdapter);
        mRvLocalMsg.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    protected void initData() {
        super.initData();

        pageNo = 1;
        getMsgList(pageNo, Constant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
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
                getMsgList(++pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.LOAD_MORE);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                getMsgList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.REFRESH);
            }
        });
    }

    private void getMsgList(final int pageNo, final int pageSize, final int action) {
        mFlNoData.setVisibility(View.GONE);
        final Map<String, String> map = new HashMap<>(16);
        map.put(Constant.PAGE_NO, String.valueOf(pageNo));
        map.put(Constant.PAGE_SIZE, String.valueOf(pageSize));
        map.put("title", "");
        map.put("type", "");
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<PageBean<LocalMsg>>>post(NetConstant.GET_LOCAL_MSG)
                .params(NetConstant.QUERY, queryStr)
                .execute(new DialogCallBack<BaseBean<PageBean<LocalMsg>>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<LocalMsg>>> response) {
                        PageBean<LocalMsg> pageBean = response.body().getBody();
                        if (pageNo <= 1 && pageBean.getCount() <= 0) {
                            mFlNoData.setVisibility(View.VISIBLE);
                        } else {
                            mFlNoData.setVisibility(View.GONE);
                        }
                        mRefreshLayout.setNoMoreData(pageBean.getCount() <= pageNo * pageSize);
                        List<LocalMsg> list = pageBean.getList();
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
                    public void onError(Response<BaseBean<PageBean<LocalMsg>>> response) {
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
        if (resultCode == RESULT_OK && requestCode == MsgDetailsActivity.READ_STATUS) {
            pageNo = 1;
            getMsgList(pageNo, Constant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
        }
    }
}

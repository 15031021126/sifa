package com.liuhezhineng.ximengsifa.form;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.liuhezhineng.ximengsifa.QueryUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.RemoteVisitListAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.form.VisitApply;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.liuhezhineng.ximengsifa.constant.NetConstant.PAGE_NO;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.PAGE_SIZE;

/**
 * @author iqing
 */
public class MyRemoteVisitApplyListActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.fl_no_data)
    FrameLayout mFlNoData;

    List<VisitApply> mList;
    int pageNo;
    private RemoteVisitListAdapter mAdapter;

    public static void actionStart(Context context, String todoType) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, MyRemoteVisitApplyListActivity.class);
            intent.putExtra(Constant.TODO_TYPE, todoType);
            context.startActivity(intent);
        } else {
            gotoLogin(context);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.STATE_REFRESH) {
            pageNo = 1;
            getRemoteVisitList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
        }
    }

    private void getRemoteVisitList(final int pageNo, final int pageSize, final int action) {
        mFlNoData.setVisibility(View.GONE);
        String url;
        if (Constant.UP_COMING.equals(todoType)) {
            url = NetConstant.RemoteVisit.GET_TO_DO_LIST;
        } else {
            url = NetConstant.RemoteVisit.GET_LIST;
        }
        String queryStr = new QueryUtils()
                .params(PAGE_NO, String.valueOf(pageNo))
                .params(PAGE_SIZE, String.valueOf(pageSize))
                .getQueryStr();
        OkGo.<BaseBean<PageBean<VisitApply>>>post(url)
                .params(NetConstant.QUERY, queryStr)
                .execute(new DialogCallBack<BaseBean<PageBean<VisitApply>>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<VisitApply>>> response) {
                        PageBean<VisitApply> pageBean = response.body().getBody();
                        if (pageNo <= 1 && pageBean.getCount() <= 0) {
                            mFlNoData.setVisibility(View.VISIBLE);
                        } else {
                            mFlNoData.setVisibility(View.GONE);
                        }
                        mRefreshLayout.setNoMoreData(pageBean.getCount() <= pageNo * pageSize);
                        List<VisitApply> list = pageBean.getList();
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_remote_visit_apply_list;
    }

    String todoType;

    @Override
    protected void initView() {
        super.initView();


        todoType = getIntent().getStringExtra(Constant.TODO_TYPE);
        if (todoType.equals(Constant.UP_COMING)) {
            initTopBar(R.string.remote_visit_to_do_list);
        } else {
            initTopBar(R.string.my_remote_visit_list);
        }

        mList = new ArrayList<>();
        mAdapter = new RemoteVisitListAdapter(mActivity, mList, todoType);
        mRvList.setAdapter(mAdapter);
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    protected void initData() {
        super.initData();

        pageNo = 1;
        getRemoteVisitList(pageNo,
                NetConstant.DEFAULT_PAGE_SIZE,
                Constant.INIT_DATA);
    }

    @Override
    protected void setListener() {
        super.setListener();
        setRefreshLoadMoreListener();
    }

    private void setRefreshLoadMoreListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(
                new OnRefreshLoadMoreListener() {
                    @Override
                    public void onLoadMore(
                            @NonNull RefreshLayout refreshLayout) {
                        getRemoteVisitList(++pageNo,
                                NetConstant.DEFAULT_PAGE_SIZE,
                                Constant.LOAD_MORE);
                    }

                    @Override
                    public void onRefresh(
                            @NonNull RefreshLayout refreshLayout) {
                        pageNo = 1;
                        getRemoteVisitList(pageNo,
                                NetConstant.DEFAULT_PAGE_SIZE,
                                Constant.REFRESH);
                    }
                });
    }
}


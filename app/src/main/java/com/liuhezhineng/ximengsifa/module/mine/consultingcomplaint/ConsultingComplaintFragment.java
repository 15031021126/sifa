package com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.ConsultingComplaintAdapter;
import com.liuhezhineng.ximengsifa.base.BaseFragment;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.advisory.AdvisoryBean;
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
public class ConsultingComplaintFragment extends BaseFragment {

    private static final String BUSINESS_TYPE = "business_type";

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.fl_no_data)
    FrameLayout mFlNoData;

    int pageNo;
    private ArrayList<AdvisoryBean> mList;
    private ConsultingComplaintAdapter mAdapter;

    // 留言、投诉
    private String businessType;
    // 代办 、 已办
    private String todoType;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ConsultingComplaintFragment() {
    }

    public static ConsultingComplaintFragment newInstance(String todoType, String businessType) {
        ConsultingComplaintFragment fragment = new ConsultingComplaintFragment();
        Bundle args = new Bundle();
        args.putString(Constant.TODO_TYPE, todoType);
        args.putString(BUSINESS_TYPE, businessType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.DEAL_BUSINESS_CODE) {
            initData();
        }
    }

    private void getConsultingComplaintList(final int pageNo, final int pageSize, final int action) {
        mFlNoData.setVisibility(View.GONE);
        String url = null;
        if (Constant.CONSULTING.equals(businessType)) {
            if (getString(R.string.my_advisory).equals(todoType)) {
                url = NetConstant.ConsultingComplaint.GET_ADVISORY_LIST;
            } else if (getString(R.string.my_consulting_to_do).equals(todoType)) {
                url = NetConstant.ConsultingComplaint.GET_MY_TODO_CONSULTING_LIST;
            }
        } else if (Constant.COMPLAINT.equals(businessType)) {
            if (getString(R.string.my_complaint).equals(todoType)) {
                url = NetConstant.ConsultingComplaint.GET_COMPLAINTS_LIST;
            }
            if (getString(R.string.my_complaint_to_do).equals(todoType)) {
                url = NetConstant.ConsultingComplaint.GET_MY_TODO_COMPLAINT_LIST;
            }
        }
        final Map<String, String> map = new HashMap<>(16);
        map.put(PAGE_NO, String.valueOf(pageNo));
        map.put(NetConstant.PAGE_SIZE, String.valueOf(pageSize));
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<PageBean<AdvisoryBean>>>post(url)
                .params(NetConstant.QUERY, queryStr)
                .execute(new DialogCallBack<BaseBean<PageBean<AdvisoryBean>>>(mActivity) {

                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<AdvisoryBean>>> response) {
                        PageBean<AdvisoryBean> pageBean = response.body().getBody();
                        if (pageNo == 1 && pageBean.getCount() <= 0) {
                            mFlNoData.setVisibility(View.VISIBLE);
                        } else {
                            mFlNoData.setVisibility(View.GONE);
                        }
                        mRefreshLayout.setNoMoreData(pageBean.getCount() <= pageNo * pageSize);
                        List<AdvisoryBean> list = pageBean.getList();
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

                    @Override
                    public void onError(Response<BaseBean<PageBean<AdvisoryBean>>> response) {
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
            todoType = getArguments().getString(Constant.TODO_TYPE);
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
        mAdapter = new ConsultingComplaintAdapter(mActivity, mList, businessType, todoType);
        mRvList.setAdapter(mAdapter);
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    protected void initData() {
        super.initData();

        pageNo = 1;
        getConsultingComplaintList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
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
                getConsultingComplaintList(++pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.LOAD_MORE);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                getConsultingComplaintList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.REFRESH);
            }
        });
    }

}

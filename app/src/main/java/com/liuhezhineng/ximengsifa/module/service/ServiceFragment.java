package com.liuhezhineng.ximengsifa.module.service;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.ServiceAdapter;
import com.liuhezhineng.ximengsifa.adapter.ServicesAdapter;
import com.liuhezhineng.ximengsifa.base.BaseFragment;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.dto.vo.ServerAppVo;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author AIqinfeng
 * @description 服务模块
 */
public class ServiceFragment extends BaseFragment {

    public static ServerAppVo mServerAppVo;

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_mongolian)
    ImageView mIvMongolian;
    @BindView(R.id.listview)
    ListView mListview;
    @BindView(R.id.tv_services_name)
    TextView mTvServicesName;
    @BindView(R.id.rv_services)
    RecyclerView mRvServices;
    @BindView(R.id.empty_view)
    QMUIEmptyView mEmptyView;
    @BindView(R.id.services_empty_view)
    QMUIEmptyView mServicesEmptyView;

    ServicesAdapter mServicesAdapter;
    ArrayList<ServerAppVo> mServiceList;
    private ServiceAdapter mServiceAdapter;

    public static ServiceFragment newInstance(@StringRes int moduleTitle) {
        ServiceFragment fragment = new ServiceFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.MODULE_TITLE, moduleTitle);
        fragment.setArguments(args);
        return fragment;
    }

    private void getServiceList() {
        OkGo.<BaseBean<List<ServerAppVo>>>post(NetConstant.GET_SERVICE)
                .cacheTime(NetConstant.CACHE_TIME)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<List<ServerAppVo>>>(mActivity) {
                    @Override
                    public void onError(Response<BaseBean<List<ServerAppVo>>> response) {
                        super.onError(response);
                        if (isUnbind) {
                            return;
                        }
                        mEmptyView.show(false, getString(R.string.load_fail), null,
                                getString(R.string.click_try_again)
                                , new OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mEmptyView.hide();
                                        getServiceList();
                                    }
                                });
                    }

                    @Override
                    public void onSuccess(Response<BaseBean<List<ServerAppVo>>> response) {
                        if (isUnbind) {
                            return;
                        }
                        List<ServerAppVo> list = response.body().getBody();
                        if (list != null && list.size() > 0) {
                            initServiceList(list);
                        } else {
                            mEmptyView.show("", "此账号暂无相关服务权限");
                        }
                    }
                });
    }

    private void initServiceList(List serviceList) {
        if (serviceList != null && serviceList.size() > 0) {
            mTvServicesName.setText(((ServerAppVo) serviceList.get(0)).getName());
            if (mServiceList != null) {
                mServiceList.clear();
                mServiceList.addAll(serviceList);
            }
            mServiceAdapter.updateData(serviceList);

            mEmptyView.hide();
            mServiceAdapter.setItemSelected(0);
            mServicesEmptyView.hide();
            if (mServiceList != null && mServiceList.get(0) != null) {
                getServicesList(mServiceList.get(0).getId());
                mServerAppVo = mServiceList.get(0);
            }
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mServerAppVo = (ServerAppVo) savedInstanceState.getSerializable(Constant.BEAN);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(Constant.BEAN, mServerAppVo);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initView() {
        super.initView();
        mEmptyView.hide();

        if (getArguments() != null) {
            mTvTitle.setText(getArguments().getInt(Constant.MODULE_TITLE));
        }
        mIvMongolian.setImageResource(R.drawable.home_page_service);

        mServiceList = new ArrayList<>();
        mServiceAdapter = new ServiceAdapter(mActivity, mServiceList);
        mListview.setAdapter(mServiceAdapter);

        mServicesAdapter = new ServicesAdapter(mActivity, mServiceList);
        mRvServices.setAdapter(mServicesAdapter);
    }

    @Override
    protected void initData() {
        getServiceList();
    }

    @Override
    protected void setListener() {
        super.setListener();

        mListview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mServiceAdapter.setItemSelected(position);
                mTvServicesName.setText(mServiceList.get(position).getName());
                getServicesList(mServiceList.get(position).getId());
                mServerAppVo = mServiceList.get(position);
            }
        });
    }

    private void getServicesList(final String serverId) {
        // 先显示空列表，避免上一次的列表暂时停留
        mServicesAdapter.initData(new ArrayList<ServerAppVo>());
        mServicesEmptyView.show(true);
        OkGo.<BaseBean<List<ServerAppVo>>>post(NetConstant.GET_SERVICES)
                .cacheKey("get_services_" + serverId + UserHelper.getToken())
                .cacheTime(NetConstant.CACHE_TIME)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(NetConstant.QUERY, "{\"serverId\":\"" + serverId + "\"}")
                .execute(new JsonCallback<BaseBean<List<ServerAppVo>>>(mActivity) {
                    @Override
                    public void onFinish() {
                        super.onFinish();
                        if (isUnbind) {
                            return;
                        }
                        mServicesEmptyView.hide();
                    }

                    @Override
                    public void onError(Response<BaseBean<List<ServerAppVo>>> response) {
                        super.onError(response);
                        if (isUnbind) {
                            return;
                        }

                        mServicesEmptyView.show(false, getString(R.string.load_fail), null,
                                getString(R.string.click_try_again)
                                , new OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mServicesEmptyView.hide();
                                        getServicesList(serverId);
                                    }
                                });
                    }

                    @Override
                    public void onSuccess(Response<BaseBean<List<ServerAppVo>>> response) {
                        if (isUnbind) {
                            return;
                        }
                        mServicesEmptyView.hide();
                        BaseBean<List<ServerAppVo>> baseBean = response.body();
                        List<ServerAppVo> list = baseBean.getBody();
                        if (list != null && list.size() > 0) {
                            mServicesAdapter.initData(list);
                        }
                    }
                });
    }
}
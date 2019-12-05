package com.liuhezhineng.ximengsifa.callback;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseFragment;
import com.liuhezhineng.ximengsifa.bean.MechanismsubjectBean;
import com.liuhezhineng.ximengsifa.callback.bean.MechPeopleBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author AIqinfeng
 * @description 人员机构列表
 */
@SuppressLint("ValidFragment")
public class MechPeopleFragment extends BaseFragment {


    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.fl_no_data)
    FrameLayout flNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.empty_view)
    QMUIEmptyView emptyView;
    Unbinder unbinder;
    public MechPeopleFragment( ) {
        // TODO: 2019/11/25

    }
    public static MechPeopleFragment newInstance(MechanismsubjectBean.BodyBean.ListBean param1) {
        MechPeopleFragment fragment = new MechPeopleFragment();
        Bundle args = new Bundle();
        args.putParcelable("offid", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personnel_institutions;
    }
   private MechanismsubjectBean.BodyBean.ListBean  listBean;
    private   MechismpeopleAdapter mechismAdapter;

    @Override
    protected void initView() {
        super.initView();
        emptyView.setVisibility(View.GONE);
        mechismAdapter=new MechismpeopleAdapter(getActivity());
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(mechismAdapter);

    }
    private void initPeopleData(int pageNo) {
        Map<String, String> map = new HashMap<>(16);
        map.put("categoryId", "18");
        map.put("officeId", listBean.getOfficeId());
        map.put("pageNo", String.valueOf(pageNo));
        map.put("pageSize", "10");
        map.put("name", "");
        map.put("areaId", "");
        map.put("townId", "");
        String queryStr = new JSONObject(map).toString();
        OkGo.<MechPeopleBean>post(NetConstant.GET_INSTITUTIONS).params("query",queryStr).execute(new JsonCallback2<MechPeopleBean>() {
            @Override
            public void onSuccess(Response<MechPeopleBean> response) {
                List<MechPeopleBean.BodyBean.ListBean> list = response.body().getBody().getList();
                if (list!=null){
                    mechismAdapter.addList(list);
                }
            }
        });
    }
    private  int pageNo=1;
    @Override
    protected void onVisible() {
        super.onVisible();
        listBean = (MechanismsubjectBean.BodyBean.ListBean) getArguments().getParcelable("offid");
        initPeopleData(pageNo);
    }

    @Override
    protected void setListener() {
        super.setListener();
        setRefreshLoadMoreListener();
    }



    private void setRefreshLoadMoreListener() {
        refreshLayout.setOnRefreshLoadMoreListener(
                new OnRefreshLoadMoreListener() {
                    @Override
                    public void onLoadMore(
                            @NonNull RefreshLayout refreshLayout) {
                        initPeopleData(pageNo++);
                        refreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onRefresh(
                            @NonNull RefreshLayout refreshLayout) {
                        initPeopleData(1);
                        refreshLayout.finishRefresh();
                    }
                });
    }


}

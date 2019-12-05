package com.liuhezhineng.ximengsifa.callback;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseFragment;
import com.liuhezhineng.ximengsifa.bean.MechanismsubjectBean;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author AIqinfeng
 * @description 人员机构列表
 */
@SuppressLint("ValidFragment")
public class MechanismFragment extends BaseFragment {


    MechanismsubjectBean.BodyBean.ListBean listBean;
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
    public MechanismFragment(MechanismsubjectBean.BodyBean.ListBean list ) {
        this.listBean = list;

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

    private  MechismAdapter mechismAdapter;
    @Override
    protected void initView() {
        super.initView();
            emptyView.setVisibility(View.GONE);
            mechismAdapter=new MechismAdapter(getActivity(),listBean);
            rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvList.setAdapter(mechismAdapter);
    }

    @Override
    protected void onVisible() {
        super.onVisible();
    }
    @Override
    protected void setListener() {
        super.setListener();
    }
}

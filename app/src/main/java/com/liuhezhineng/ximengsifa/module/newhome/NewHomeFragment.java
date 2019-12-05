package com.liuhezhineng.ximengsifa.module.newhome;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.ServicesAdapter;
import com.liuhezhineng.ximengsifa.base.BaseFragment;
import com.liuhezhineng.ximengsifa.bean.ArticleBean;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.dto.vo.ServerAppVo;
import com.liuhezhineng.ximengsifa.business.fastlegal.QuickLegalAidActivity;
import com.liuhezhineng.ximengsifa.business.fastlegal.WorkerActivity;
import com.liuhezhineng.ximengsifa.callback.LogUtils;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.module.home.ArticleDetailsActivity;
import com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint.ConsultingComplaintActivity;
import com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint.MyConsultationActivity;
import com.liuhezhineng.ximengsifa.module.mine.login.LoginActivity;
import com.liuhezhineng.ximengsifa.utils.BannerImageLoader;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.liuhezhineng.ximengsifa.vidyo.VideoRequestActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static com.liuhezhineng.ximengsifa.constant.NetConstant.PAGE_NO;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author AIqinfeng
 */
public class NewHomeFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.view_flipper)
    ViewFlipper mViewFlipper;
    @BindView(R.id.rv_services)
    RecyclerView mRvServices;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_mongolian)
    ImageView mIvMongolian;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    Integer[] iamges = {R.drawable.banner_1_legal_aid,
            R.drawable.banner_2_mediation,
            R.drawable.banner_3_lawyer,
            R.drawable.banner_4_justice,
            R.drawable.banner_5_authenticate,
            R.drawable.banner_6_exam,
            R.drawable.banner_7_publicity,
            R.drawable.banner_8_meng,
            R.drawable.banner_9_han,
            R.drawable.banner_10_wei,
    };
    ArrayList<ServerAppVo> mList;
    ServicesAdapter mAdapter;

    public NewHomeFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(int moduleTitle) {
        NewHomeFragment fragment = new NewHomeFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.MODULE_TITLE, moduleTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            mViewFlipper.stopFlipping();
            mBanner.stopAutoPlay();
        } else {
            mViewFlipper.startFlipping();
            mBanner.startAutoPlay();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_home;
    }

    @Override
    protected void initView() {
        super.initView();
        if (getArguments() != null) {
            mTvTitle.setText(getArguments().getInt(Constant.MODULE_TITLE));
        }
        mIvMongolian.setImageResource(R.drawable.home_page_home);
        initBanner();
        initRvServices();
    }

    private void initBanner() {
        mBanner.setImages(Arrays.asList(iamges))
                .setImageLoader(new BannerImageLoader())
                .setDelayTime(3000)
                .start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                    switch (position){
                        case 8:
                        case 7:
                            try {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
                                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setComponent(cmp);
                                startActivity(intent);
                            } catch (ActivityNotFoundException e) {
                                // TODO: handle exception
                                ToastUtils.showLong("检查到您手机没有安装微信，请安装后使用该功能");
                            }
                            break;
                        case  9 :
                            // TODO: 2019/11/20 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999
//                            Intent intent=new Intent();
//                            intent.setAction(Intent.ACTION_VIEW);
//                            intent.addCategory("android.intent.category.DEFAULT");
//                            intent.setData(Uri.parse("sinaweibo://splash"));
//                            startActivity(intent);
                            break;
                    }
            }
        });
    }

    private void initRvServices() {
        mList = new ArrayList<>();
        mAdapter = new ServicesAdapter(mActivity, mList, 1);
        mRvServices.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();

        setHomeServices();
        getArticleList();
    }

    private void setHomeServices() {
        if (UserHelper.isIsNormalUser()) {
            getHomeService();
        } else {
            ArrayList<ServerAppVo> serverAppVos = new ArrayList<>();
            serverAppVos.add(new ServerAppVo("待办",
                    "/userfiles/1/_thumbs/images/sys/versionManager/2018/up_coming.png",
                    "待办"));
            serverAppVos.add(new ServerAppVo(
                    "已办",
                    "/userfiles/1/_thumbs/images/sys/versionManager/2018/have_done.png",
                    "已办"));
            serverAppVos.add(new ServerAppVo("留言咨询",
                    "/userfiles/1/_thumbs/images/sys/versionManager/2018/consulting.png",
                    "我的咨询"));
            serverAppVos.add(new ServerAppVo("投诉建议",
                    "/userfiles/1/_thumbs/images/sys/versionManager/2018/complaint.png",
                    "我的投诉"));
            serverAppVos.add(new ServerAppVo("综合查询",
                    "/userfiles/1/_thumbs/images/sys/versionManager/2018/query.png",
                    "综合查询"));
            serverAppVos.add(new ServerAppVo("我的预约",
                    "/userfiles/1/_thumbs/images/sys/versionManager/2018/order.png",
                    "我的预约"));
            mAdapter.initData(serverAppVos);
            mRefreshLayout.finishRefresh();
        }
    }

    private void getArticleList() {
        Map<String, String> map = new HashMap<>(16);
        map.put(PAGE_NO, "1");
        map.put(NetConstant.PAGE_SIZE, "-1");
        map.put(NetConstant.CATEGORY_TYPE, NetConstant.HOT);
        OkGo.<BaseBean<PageBean<ArticleBean>>>post(NetConstant.GET_ARTICLE)
                .params(NetConstant.QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<PageBean<ArticleBean>>>(mActivity) {

                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<ArticleBean>>> response) {
                        PageBean<ArticleBean> pageBean = response.body().getBody();
                        List<ArticleBean> list = pageBean.getList();
                        for (ArticleBean bean : list) {
                            addFlipperView(bean.getTitle(), bean);
                        }
                    }
                });
    }

    private void getHomeService() {
        OkGo.<BaseBean<List<ServerAppVo>>>post(NetConstant.GET_HOME_SERVICES)
                .cacheTime(NetConstant.CACHE_TIME)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<List<ServerAppVo>>>() {
                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mRefreshLayout.finishRefresh();
                    }

                    @Override
                    public void onSuccess(Response<BaseBean<List<ServerAppVo>>> response) {
                        mAdapter.initData(response.body().getBody());
                    }
                });
    }

    private void addFlipperView(String title, final ArticleBean bean) {
        View view = View.inflate(mActivity, R.layout.view_flipper_item, null);
        TextView tvHot = view.findViewById(R.id.tv_hot);
        tvHot.setText(title);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ArticleDetailsActivity.actionStart(mActivity, bean);
            }
        });
        mViewFlipper.addView(view);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                setHomeServices();
                getArticleList();
            }
        });
    }

    @OnClick({R.id.fl_search, R.id.fl_message, R.id.iv_12348, R.id.iv_fast_legal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_search:
                break;
            case R.id.fl_message:
                break;
            case R.id.iv_fast_legal:
                // TODO: 2019/11/11 11.11   目前是超级管理员
                if (UserHelper.isIsLogin()) {
                    if (UserHelper.isIsNormalUser()) {
                        QuickLegalAidActivity.actionStart(mActivity);
                    } else {
                        WorkerActivity.actionStart(mActivity);
                    }
                } else {
                    Intent intent = new Intent(mActivity, LoginActivity.class);
                    mActivity.startActivity(intent);
                }
                break;
            case R.id.iv_12348:
                show12348Dialog();
                break;
            default:
                break;
        }
    }

    private void show12348Dialog() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_12348, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        final AlertDialog dialog = builder.setView(view).create();
        view.findViewById(R.id.ll_vidyo_consult).setOnClickListener(v -> {
            VideoRequestActivity.actionStart(mActivity, "12348服务人员", VideoRequestActivity.VIDYO_12348, "956aaf89a2b14e07acb2979bf0737b02");
            dialog.dismiss();
        });
        view.findViewById(R.id.ll_phone_consult).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:12348"));
            startActivity(intent);
            dialog.dismiss();
        });
        view.findViewById(R.id.ll_msg_consulting).setOnClickListener(v -> {
            dialog.dismiss();
            MyConsultationActivity.actionStart(mActivity);
        });
        view.findViewById(R.id.ll_knowledge_qa_consultation).setOnClickListener(v -> {
            dialog.dismiss();
            ConsultingComplaintActivity.actionStart(mActivity, Constant.CONSULTING);
        });
        dialog.show();
    }
}

package com.liuhezhineng.ximengsifa.module.mine.business;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.BusinessAdapter;
import com.liuhezhineng.ximengsifa.base.BaseFragment;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.business.fastlegal.AQuickAcceptLegalAidActivity;
import com.liuhezhineng.ximengsifa.business.fastlegal.BAssignUndertakerActivity;
import com.liuhezhineng.ximengsifa.business.fastlegal.QuickLegalAidActivity;
import com.liuhezhineng.ximengsifa.business.fastlegal.havedone.HaveDoneActivity;
import com.liuhezhineng.ximengsifa.business.fastlegal.havedone.HaveDoneStartActivity;
import com.liuhezhineng.ximengsifa.business.legalaid.AReviewActivity;
import com.liuhezhineng.ximengsifa.business.legalaid.BDesignatedUndertakerActivity;
import com.liuhezhineng.ximengsifa.business.legalaid.CDesignatedOrganizerActivity;
import com.liuhezhineng.ximengsifa.business.legalaid.DZhuRenActivity;
import com.liuhezhineng.ximengsifa.business.legalaid.EUndertakerActivity;
import com.liuhezhineng.ximengsifa.business.legalaid.FEvaluationActivity;
import com.liuhezhineng.ximengsifa.business.legalaid.GSubsidyActivity;
import com.liuhezhineng.ximengsifa.business.legalaid.ModifyRequestActivity;
import com.liuhezhineng.ximengsifa.business.legalaid.ZHaveDoneActivity;
import com.liuhezhineng.ximengsifa.business.noticedefense.BApprovalActivity;
import com.liuhezhineng.ximengsifa.business.noticedefense.CAssignLawyerActivity;
import com.liuhezhineng.ximengsifa.business.noticedefense.DLawyerDealActivity;
import com.liuhezhineng.ximengsifa.business.noticedefense.ModifyActivity;
import com.liuhezhineng.ximengsifa.business.noticedefense.NoticeDetailsActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.AAssignPeoplesMediatorActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.BReviewActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.CRegisterFromActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.DRecordActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.EMediateRecordActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.FAgreementActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.GReturnVisitActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.HDossierActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.HDMediateRecordeActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.HaveDoneAgreementActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.HaveDoneDossierActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.HaveDoneRecordActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.HaveDoneRegisterActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.HaveDoneReturnVisitActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.RequestActivity;
import com.liuhezhineng.ximengsifa.callback.HaveDoneUpdataActivity;
import com.liuhezhineng.ximengsifa.callback.MyLegalAgentActivity;
import com.liuhezhineng.ximengsifa.callback.UpdataReviewActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.Business;
import com.liuhezhineng.ximengsifa.constant.Constant.FastLegal;
import com.liuhezhineng.ximengsifa.constant.Constant.Notice;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.interfaces.OnListFragmentInteractionListener;
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
 * @description 业务列表
 */
public class MyBusinessFragment extends BaseFragment implements OnListFragmentInteractionListener {

	private static final String BUSINESS_TYPE = "business_type";

	@BindView(R.id.rv_list)
	RecyclerView mRvList;
	@BindView(R.id.refreshLayout)
	SmartRefreshLayout mRefreshLayout;
	@BindView(R.id.fl_no_data)
	FrameLayout mFlNoData;

	List<BusinessBean> mList;
	int pageNo;
	LocalBroadcastManager broadcastManager;
	IntentFilter intentFilter;
	BroadcastReceiver mReceiver;
	private BusinessAdapter mAdapter;
	private String businessType;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public MyBusinessFragment() {
	}

	public static MyBusinessFragment newInstance(String businessType) {
		MyBusinessFragment fragment = new MyBusinessFragment();
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
			getBusinessList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
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

	private void getBusinessList(final int pageNo, final int pageSize, final int action) {
		mFlNoData.setVisibility(View.GONE);
		String url = "";
		if ("待办".equals(businessType)) {
			url = NetConstant.GET_UPCOMING;
		} else {
			url = NetConstant.GET_HAVE_DONE;
		}
		final Map<String, String> map = new HashMap<>(16);
		map.put(PAGE_NO, String.valueOf(pageNo));
		map.put(NetConstant.PAGE_SIZE, String.valueOf(pageSize));
		OkGo.<BaseBean<PageBean<BusinessBean>>>post(url)
			.params(NetConstant.QUERY, new JSONObject(map).toString())
			.execute(new DialogCallBack<BaseBean<PageBean<BusinessBean>>>(mActivity) {
				@Override
				public void onSuccess(Response<BaseBean<PageBean<BusinessBean>>> response) {
					if (isUnbind) {
						return;
					}
					PageBean<BusinessBean> pageBean = response.body().getBody();
					if (pageNo <= 1 && pageBean.getCount() <= 0) {
						mFlNoData.setVisibility(View.VISIBLE);
					} else {
						mFlNoData.setVisibility(View.GONE);
					}
					mRefreshLayout.setNoMoreData(pageBean.getCount() <= pageNo * pageSize);
					List<BusinessBean> list = pageBean.getList();
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
				public void onError(Response<BaseBean<PageBean<BusinessBean>>> response) {
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
	public void onListFragmentInteraction(BusinessBean bean) {
		// TODO: 2019/11/11 lishangnan  等待联调
		Log.i("ssss","s1="+bean.getProcDefKey());
		switch (bean.getProcDefKey()) {
			case Business.LEGAL_AID:
				legalAid(bean);
				break;
			case Business.PEOPLE_MEDIATION:
				peopleMediation(bean);
				break;
			case Business.NOTIFICATION_DEFENSE:
				notificationDefense(bean);
				break;
			case Business.FAST_LEGAL:
				fastLegal(bean);
				break;
			case Business.LEGAL_DEFKEY:
				fastLegal(bean);
				break;
			default:
				break;
		}
	}




	private void legalAid(BusinessBean item) {
		if ("已办".equals(businessType)) {
			ZHaveDoneActivity.actionStart(mActivity, item);
		} else {
			Log.i("ssss","s1="+item.getTask().getTaskDefinitionKey());
			switch (item.getTask().getTaskDefinitionKey()) {
				case "adi_fuzeren_approve":
					UpdataReviewActivity.actionStart(mActivity, item);
					break;
				// adi_approve 科员审核
				case "adi_approve":
					AReviewActivity.actionStart(mActivity, item);
					break;
				// aid_apply_zhiding 申请人直接指定承办人
				case "aid_apply_zhiding":
					BDesignatedUndertakerActivity.actionStart(mActivity, item);
					break;
				// aid_ky_zhiding  科员指定承办机构
				case "aid_ky_zhiding":
					CDesignatedOrganizerActivity.actionStart(mActivity, item);
					break;
				// aid_zhuren 主任审核(同时指定承办人)
				case "aid_zhuren":
					DZhuRenActivity.actionStart(mActivity, item);
					break;
				//aid_chenbanren_shouli 承办人审核
				case "aid_chengbanren_shouli":
					EUndertakerActivity.actionStart(mActivity, item);
					break;
				//aid_chenbanren_banli 承办人办理
				case "aid_chengbanren_banli":
					EUndertakerActivity.actionStart(mActivity, item);
					break;
				//aid_pingjia 第三方评价(由法援科员办理)
				case "aid_pingjia":
					FEvaluationActivity.actionStart(mActivity, item);
					break;
				// aid_chenbanren_butie 承办人申请补贴
				case "aid_chengbanren_butie":
					GSubsidyActivity.actionStart(mActivity, item);
					break;
				// aid_update 重新申请
				default:
					ModifyRequestActivity.actionStart(mActivity, item);
					break;
			}
		}
	}

	private void peopleMediation(BusinessBean item) {
		if (Constant.HAVE_DONE.equals(businessType)) {
			switch (item.getTask().getTaskDefinitionKey()) {
				case "mediation_zhiding":
					RequestActivity.actionStart(mActivity, item);
					break;
				case "mediation_shenhe":
					RequestActivity.actionStart(mActivity, item);
					break;
				case "mediation_dengji":
					HaveDoneRegisterActivity.actionStart(mActivity, item);
					break;
				case "mediation_xiugai":
					RequestActivity.actionStart(mActivity, item);
					break;
				case "mediation_diaocha":
					HaveDoneRecordActivity.actionStart(mActivity, item);
					break;
				case "mediation_tiaojie":
					HDMediateRecordeActivity.actionStart(mActivity, item);
					break;
				case "mediation_xieyi":
					HaveDoneAgreementActivity.actionStart(mActivity, item);
					break;
				case "mediation_huifang":
					HaveDoneReturnVisitActivity.actionStart(mActivity, item);
					break;
				case "mediation_juanzong":
					HaveDoneDossierActivity.actionStart(mActivity, item);
					break;
				case "mediation_start":
					RequestActivity.actionStart(mActivity, item);
					break;
				default:
					RequestActivity.actionStart(mActivity, item);
					break;
			}
		} else {
			switch (item.getTask().getTaskDefinitionKey()) {
				case "mediation_zhiding":
					AAssignPeoplesMediatorActivity.actionStart(mActivity, item);
					break;
				case "mediation_shenhe":
					BReviewActivity
						.actionStart(mActivity, item);
					break;
				case "mediation_dengji":
					CRegisterFromActivity.actionStart(mActivity, item);
					break;
				case "mediation_xiugai":
					com.liuhezhineng.ximengsifa.business.peoplesmediation.ModifyRequestActivity
						.actionStart(mActivity, item);
					break;
				case "mediation_diaocha":
					DRecordActivity.actionStart(mActivity, item);
					break;
				case "mediation_tiaojie":
					EMediateRecordActivity.actionStart(mActivity, item);
					break;
				case "mediation_xieyi":
					FAgreementActivity.actionStart(mActivity, item);
					break;
				case "mediation_huifang":
					GReturnVisitActivity.actionStart(mActivity, item);
					break;
				case "mediation_juanzong":
					HDossierActivity.actionStart(mActivity, item);
					break;
				default:
					break;
			}
		}
	}

	private void notificationDefense(BusinessBean bean) {
		if (Constant.HAVE_DONE.equals(businessType)) {
			NoticeDetailsActivity.actionStart(mActivity, bean);
		} else {
			switch (bean.getTask().getTaskDefinitionKey()) {
				case Notice.START:
					break;
				case Notice.APPROVE:
					com.liuhezhineng.ximengsifa.business.noticedefense.AReviewActivity
						.actionStart(mActivity, bean);
					break;
				case Notice.UPDATE:
					ModifyActivity.actionStart(mActivity, bean);
					break;
				case Notice.AID_DIRECTOR:
					BApprovalActivity.actionStart(mActivity, bean);
					break;
				case Notice.ASSIGN_LAWYER:
					CAssignLawyerActivity.actionStart(mActivity, bean);
					break;
				case Notice.AID_CENTER_CONFIRM:
				case Notice.DEAL:
				case Notice.EVALUATE:
				case Notice.SUBSIDY:
					DLawyerDealActivity.actionStart(mActivity, bean);
					break;
				default:
					break;
			}
		}
	}

	private void fastLegal(BusinessBean bean) {
		Log.i("ssss","s2="+bean.getTask().getTaskDefinitionKey());
		if (Constant.HAVE_DONE.equals(businessType)) {
			switch (bean.getTask().getTaskDefinitionKey()) {
				case FastLegal.START:
					HaveDoneStartActivity.actionStart(mActivity, bean);
					break;
				case FastLegal.REVIEW:
				case FastLegal.DEAL:
				case FastLegal.ASSIGN_PEOPLE:
					HaveDoneActivity.actionStart(mActivity, bean);
					break;
				case "reconsider_start":
				case "reconsider_approve":
				case "reconsider_verify":
				case "reconsider_update":
					Intent intent = new Intent(mActivity, MyLegalAgentActivity.class);
					intent.putExtra(Constant.BUSINESS, bean);
					intent.putExtra(Constant.UP_COMING, "已办");
					mActivity.startActivity(intent);
					break;
				default:
					break;
			}
		} else {
			Log.i("ssss","ssssselse="+bean.getTask().getTaskDefinitionKey());
			switch (bean.getTask().getTaskDefinitionKey()) {
				case "fast_shouli":
					AQuickAcceptLegalAidActivity.actionStart(mActivity, bean);
					break;
				case "fast_zhiding":
				case "fast_banli":
					BAssignUndertakerActivity.actionStart(mActivity, bean);
					break;
				case "reconsider_approve":
					Intent intent = new Intent(mActivity, MyLegalAgentActivity.class);
					intent.putExtra(Constant.BUSINESS, bean);
					intent.putExtra(Constant.UP_COMING, "待办");
					mActivity.startActivity(intent);
					break;
				case "reconsider_verify":
					// 审核中
					Intent intent1 = new Intent(mActivity, MyLegalAgentActivity.class);
					intent1.putExtra(Constant.BUSINESS, bean);
					intent1.putExtra(Constant.UP_COMING, "审核中");
					mActivity.startActivity(intent1);
					break;
				case "apply_update":
					// 直通车  更新
					HaveDoneUpdataActivity.actionStart(mActivity, bean);
					break;
				case "reconsider_update":
					Intent intent2 = new Intent(mActivity, MyLegalAgentActivity.class);
					intent2.putExtra(Constant.BUSINESS, bean);
					intent2.putExtra(Constant.UP_COMING, "update");
					mActivity.startActivity(intent2);
				default:
					break;
			}
		}
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (getArguments() != null) {
			businessType = getArguments().getString(BUSINESS_TYPE);
		}
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
		@Nullable Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_business_list;
	}

	@Override
	protected void initView() {
		super.initView();
		mList = new ArrayList<>();
		mAdapter = new BusinessAdapter(mList, this);
		mRvList.setAdapter(mAdapter);
		mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
	}

	@Override
	protected void initData() {
		super.initData();
		pageNo = 1;
		getBusinessList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.INIT_DATA);
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
				pageNo++;
				getBusinessList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.LOAD_MORE);
			}

			@Override
			public void onRefresh(@NonNull RefreshLayout refreshLayout) {
				pageNo = 1;
				getBusinessList(pageNo, NetConstant.DEFAULT_PAGE_SIZE, Constant.REFRESH);
			}
		});
	}
}

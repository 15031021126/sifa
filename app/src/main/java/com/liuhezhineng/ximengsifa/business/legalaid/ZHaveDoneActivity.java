package com.liuhezhineng.ximengsifa.business.legalaid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AnnexAdapter;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessDetailsWrapper;
import com.liuhezhineng.ximengsifa.bean.bussiness.LegalAidData;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.LawOffice;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.Lawyer;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.LegalOffice;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.LegalPerson;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.business.base.BaseLegalAidActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import java.util.ArrayList;

/**
 * @author AIqinfeng
 * @description 已办法援
 */
public class ZHaveDoneActivity extends BaseLegalAidActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.et_name)
	TextView mEtName;
	@BindView(R.id.tv_sex)
	TextView mEtSex;
	@BindView(R.id.tv_ethnic)
	TextView mEtEthnic;
	@BindView(R.id.tv_birthday)
	TextView mEtBirthday;
	@BindView(R.id.et_id_card_num)
	TextView mEtIdCardNum;
	@BindView(R.id.et_area)
	TextView mEtArea;
	@BindView(R.id.et_work_address)
	TextView mEtWorkAddress;
	@BindView(R.id.et_address)
	TextView mEtAddress;
	@BindView(R.id.et_home_address)
	TextView mEtHomeAddress;
	@BindView(R.id.et_phone)
	TextView mEtPhoneNum;
	@BindView(R.id.et_agent_name)
	TextView mEtAgentName;
	@BindView(R.id.et_agent_id_card_num)
	TextView mEtAgentIdCardNum;
	@BindView(R.id.et_agent_person)
	TextView mEtAgentPerson;
	@BindView(R.id.et_title)
	TextView mEtTitle;
	@BindView(R.id.et_type)
	TextView mEtType;
	@BindView(R.id.et_line_mengyu)
	TextView mEtLineMengyu;
	@BindView(R.id.et_content)
	TextView mEtContent;
	@BindView(R.id.ll_zhiding_chengbanjigou)
	LinearLayout mLlZhidingChengbanjigou;
	@BindView(R.id.tv_lawyer_office)
	TextView mTvLawyerOffice;
	@BindView(R.id.tv_legal_office)
	TextView mTvLegalOffice;
	@BindView(R.id.ll_zhiding_chengbanren)
	LinearLayout mLlZhidingChengbenren;
	@BindView(R.id.tv_lawyer)
	TextView mTvLawyer;
	@BindView(R.id.tv_legal_person)
	TextView mTvLegalPerson;
	@BindView(R.id.ll__disanfangrenyuanpingfen)
	LinearLayout mLlDiSanFang;
	@BindView(R.id.ll_subsidy)
	LinearLayout mLlBuTie;
	@BindView(R.id.tv_score)
	TextView mTvScore;
	@BindView(R.id.tv_beizhu)
	TextView mTvBeiZhu;
	@BindView(R.id.et_subsidy)
	TextView mEtBuTie;
	@BindView(R.id.ll_lawyer)
	LinearLayout mLlLawyer;
	@BindView(R.id.ll_legal_person)
	LinearLayout mLlLegalPerson;
	@BindView(R.id.ll_law_office)
	LinearLayout mLlLawOffice;
	@BindView(R.id.ll_legal_office)
	LinearLayout mLlLegalOffice;
	@BindView(R.id.tv_aid_category)
	TextView mTvAidCategory;
	@BindView(R.id.tv_case_nature)
	TextView mTvCaseNature;
	@BindView(R.id.rv_annex1)
	RecyclerView mRvAnnex1;
	@BindView(R.id.ll_annex1)
	LinearLayout mLlAnnex1;
	@BindView(R.id.ll_aid_center)
	LinearLayout mLlAidCenter;
	@BindView(R.id.tv_aid_center)
	TextView mTvAidCenter;
	@BindView(R.id.ll_aid_lawyer)
	LinearLayout mLlAidLawyer;
	@BindView(R.id.tv_aid_lawyer)
	TextView mTvAidLawyer;

	@BindView(R.id.tv_town)
	TextView mTvtown;

	ArrayList<String> mAnnexList1;
	AnnexAdapter mAdapter1;

	LegalAidData bean;

	public static void actionStart(Context context, BusinessBean businessBean) {
		Intent intent = new Intent(context, ZHaveDoneActivity.class);
		intent.putExtra(Constant.BUSINESS, businessBean);
		context.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_business_details;
	}

	@Override
	protected void showDetails(
		Response<BaseBean<BusinessDetailsWrapper<LegalAidData>>> response) {
		super.showDetails(response);

		bean = response.body().getBody().getBusinessData();

		// 承办人附件
		setCaseFile1();

		mEtName.setText(bean.getName());
		mEtSex.setText(bean.getSexDesc());
		mEtEthnic.setText(bean.getEthnicDesc());
		mEtBirthday.setText(bean.getBirthday());
		mEtIdCardNum.setText(bean.getIdCard());
		Area area = bean.getArea();
		if (area.getName() != null) {
			mEtArea.setText(area.getName());
		}
		mEtWorkAddress.setText(bean.getEmployer());
		mEtHomeAddress.setText(bean.getDomicile());
		mEtAddress.setText(bean.getAddress());
		mEtPhoneNum.setText(bean.getPhone());
		mEtAgentName.setText(bean.getProxyName());
		mEtAgentIdCardNum.setText(bean.getProxyIdCard());
		mEtAgentPerson.setText(bean.getProxyTypeDesc());
		mEtTitle.setText(bean.getCaseTitle());
		mEtType.setText(bean.getCaseTypeDesc());
		mEtLineMengyu.setText(bean.getHasMongolDesc());
		mEtContent.setText(bean.getCaseReason());

		mTvtown.setText(bean.getTown().getName());

		Office aidCenter = bean.getLawAssistanceOffice();
		// 律所
		LawOffice lawOffice = bean.getLawOffice();
		// 基层法律服务所
		LegalOffice legalOffice = bean.getLegalOffice();

		if (aidCenter != null && !TextUtils.isEmpty(aidCenter.getName())) {
			mLlZhidingChengbanjigou.setVisibility(View.VISIBLE);
			mLlAidCenter.setVisibility(View.VISIBLE);
			mTvAidCenter.setText(aidCenter.getName());

			Office aidLawyer = bean.getLawAssistUser();
			if (aidLawyer != null && !TextUtils.isEmpty(aidLawyer.getName())) {
				mLlZhidingChengbenren.setVisibility(View.VISIBLE);
				mLlAidLawyer.setVisibility(View.VISIBLE);
				mTvAidLawyer.setText(aidLawyer.getName());
			}
		} else if (lawOffice != null && !TextUtils.isEmpty(lawOffice.getName())) {
			mLlZhidingChengbanjigou.setVisibility(View.VISIBLE);
			mLlLawOffice.setVisibility(View.VISIBLE);
			mTvLawyerOffice.setText(lawOffice.getName());

			// 承办人律师
			Lawyer lawyer = bean.getLawyer();
			if (lawyer != null && !TextUtils.isEmpty(lawyer.getName())) {
				mLlZhidingChengbenren.setVisibility(View.VISIBLE);
				mLlLawyer.setVisibility(View.VISIBLE);
				mTvLawyer.setText(lawyer.getName());
			}
		} else if (legalOffice != null && !TextUtils.isEmpty(legalOffice.getName())) {
			mLlZhidingChengbanjigou.setVisibility(View.VISIBLE);
			mLlLegalOffice.setVisibility(View.VISIBLE);
			mTvLegalOffice.setText(legalOffice.getName());

			// 基层法律服务人员
			LegalPerson legalPerson = bean.getLegalPerson();
			if (legalPerson != null && legalPerson.getName() != null) {
				mLlZhidingChengbenren.setVisibility(View.VISIBLE);
				mLlLegalPerson.setVisibility(View.VISIBLE);
				mTvLegalPerson.setText(legalPerson.getName());
			}
		}

		// 三方评价
		if (!TextUtils.isEmpty(bean.getThirdPartyScore())) {
			mLlDiSanFang.setVisibility(View.VISIBLE);
			mTvScore.setText(bean.getThirdPartyScore());
			mTvBeiZhu.setText(bean.getThirdPartyEvaluation());
		}

		// 补贴
		if (!TextUtils.isEmpty(bean.getReceiveSubsidy())) {
			mLlBuTie.setVisibility(View.VISIBLE);
			mEtBuTie.setText(bean.getReceiveSubsidy());
		}
	}

	private void setCaseFile1() {
		String caseFile1 = bean.getCaseFile2();
		// caseFile: "", "|xx", "null|xx"
		if (!"".equals(caseFile1)) {
			ArrayList<String> list = new ArrayList<>();
			String[] fileArray = caseFile1.split("\\|");
			for (String path : fileArray) {
				if (!TextUtils.isEmpty(path)) {
					list.add(path);
				}
			}
			if (list.size() <= 0) {
				mLlAnnex1.setVisibility(View.GONE);
			} else {
				mLlAnnex1.setVisibility(View.VISIBLE);
			}
			mAdapter1.initData(list);
		}
	}

	@Override
	protected void initView() {
		super.initView();
		initAnnex1();
	}

	private void initAnnex1() {
		mAnnexList1 = new ArrayList<>();
		mAdapter1 = new AnnexAdapter(mActivity, mAnnexList1);
		mRvAnnex1.setAdapter(mAdapter1);
	}
}

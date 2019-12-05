package com.liuhezhineng.ximengsifa.business.legalaid;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessDetailsWrapper;
import com.liuhezhineng.ximengsifa.bean.bussiness.LegalAidData;
import com.liuhezhineng.ximengsifa.business.base.BaseLegalAidActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import java.util.Date;

/**
 * @author AIqinfeng
 * @description 法援申请修改
 */
public class ModifyRequestActivity extends BaseLegalAidActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.et_agent_name)
	EditText mEtAgentName;
	@BindView(R.id.tv_sex)
	TextView mEtSex;
	@BindView(R.id.tv_ethnic)
	TextView mEtEthnic;
	@BindView(R.id.tv_birthday)
	TextView mEtBirthday;
	@BindView(R.id.et_id_card_num)
	EditText mEtIdCardNum;
	@BindView(R.id.et_area)
	TextView mEtArea;
	@BindView(R.id.et_work_address)
	EditText mEtWorkAddress;
	@BindView(R.id.et_address)
	EditText mEtAddress;
	@BindView(R.id.et_home_address)
	EditText mEtHomeAddress;
	@BindView(R.id.et_phone)
	EditText mEtPhoneNum;
	@BindView(R.id.et_name)
	EditText mEtName;
	@BindView(R.id.et_agent_id_card_num)
	EditText mEtAgentIdCardNum;
	@BindView(R.id.et_agent_person)
	TextView mEtAgentType;
	@BindView(R.id.et_title)
	EditText mEtTitle;
	@BindView(R.id.et_type)
	TextView mEtCaseType;
	@BindView(R.id.et_line_mengyu)
	TextView mEtLineMengyu;
	@BindView(R.id.et_content)
	EditText mEtContent;
	@BindView(R.id.et_comment)
	TextView mTvComment;
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

	@BindView(R.id.ll_agency)
	LinearLayout mLlAgency;

	private BusinessDetailsWrapper wrapper;
	private LegalAidData bean;
	private String name;
	private String sex;
	private String birthday;
	private Area area = new Area();
	private String ethnic;
	private String idCard;
	private String employer;
	private String address;
	private String domicile;
	private String phone;
	private String proxyName;
	private String proxyIdCard;
	private String proxyType;
	private String caseTitle;
	private String caseType;
	private String aidCategory;
	private String caseNature;
	private String hasMongol;
	private String caseReason;

	public static void actionStart(Context context, BusinessBean businessBean) {
		Intent intent = new Intent(context, ModifyRequestActivity.class);
		intent.putExtra(Constant.BUSINESS, businessBean);
		((BaseActivity) context).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_aid_modify;
	}

	@Override
	protected void initData() {
		super.initData();
		initPickerView();
	}

	private void initPickerView() {
		initSexPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mEtSex.setText(sexList.get(options1));
				sex = getSex(sexList.get(options1));
			}
		});
		initEthnicPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mEtEthnic.setText(ethnicList.get(options1).getLabel());
				ethnic = ethnicList.get(options1).getValue();
			}
		});
		initBirthdayPicker(new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				mEtBirthday.setText(getTime(date));
				birthday = getTime(date);
			}
		});
		mLegalAidWorkflow.setArea(new Area());
		initCountyPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				Area cityBean = countyList.get(options1);
				mEtArea.setText(cityBean.getName());
				area.setId(cityBean.getId());
				area.setName(cityBean.getName());
			}
		});
		initAgentTypePickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mEtAgentType.setText(agentTypeList.get(options1).getLabel());
				proxyType = agentTypeList.get(options1).getValue();
			}
		});
		initMongolPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mEtLineMengyu.setText(mongolList.get(options1));
				hasMongol = hasMongolian(mongolList.get(options1));
			}
		});
		initCaseTypePicker(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mEtCaseType.setText(caseTypeList.get(options1).getLabel());
				caseType = caseTypeList.get(options1).getValue();
			}
		});
		initAidCategoryPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvAidCategory.setText(aidCategoryList.get(options1).getLabel());
				aidCategory = aidCategoryList.get(options1).getValue();
			}
		});
		initCaseNatureView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvCaseNature.setText(caseNatureList.get(options1).getLabel());
				caseNature = caseNatureList.get(options1).getValue();
			}
		});
	}

	@Override
	@OnClick({R.id.tv_sex, R.id.tv_ethnic, R.id.et_area, R.id.et_agent_person, R.id.et_type,
		R.id.tv_aid_category, R.id.tv_case_nature,
		R.id.et_line_mengyu, R.id.tv_birthday, R.id.btn_yes})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		switch (view.getId()) {
			case R.id.tv_aid_category:
				showPickerView(aidCategoryPickerView);
				break;
			case R.id.tv_case_nature:
				showPickerView(caseNaturePickerView);
				break;
			case R.id.btn_yes:
				if (checkInput()) {
					flag = Constant.YES;
					commitRequest();
				}
				break;
			case R.id.tv_sex:
				showPickerView(sexPickerView);
				break;
			case R.id.tv_ethnic:
				showPickerView(ethnicPickerView);
				break;
			case R.id.et_area:
				showPickerView(countyPickerView);
				break;
			case R.id.et_agent_person:
				showPickerView(agentTypePickerView);
				break;
			case R.id.et_type:
				showPickerView(caseTypePicker);
				break;
			case R.id.et_line_mengyu:
				showPickerView(mongolPickerView);
				break;
			case R.id.tv_birthday:
				birthdayPickerView.show();
				break;
			default:
				break;
		}
	}

	private boolean checkInput() {
		name = getText(mEtName);
		birthday = getText(mEtBirthday);
		idCard = getText(mEtIdCardNum);
		employer = getText(mEtWorkAddress);
		address = getText(mEtAddress);
		domicile = getText(mEtHomeAddress);
		phone = getText(mEtPhoneNum);

		proxyName = getText(mEtAgentName);
		proxyIdCard = getText(mEtAgentIdCardNum);

		caseTitle = getText(mEtTitle);
		caseReason = getText(mEtContent);

		if (TextUtils.isEmpty(name)) {
			ToastUtils.showLong("用户名不能为空");
			return false;
		} else if (TextUtils.isEmpty(sex)) {
			ToastUtils.showLong("性别不能为空");
			return false;
		} else if (TextUtils.isEmpty(ethnic)) {
			ToastUtils.showLong("民族不能为空");
			return false;
		} else if (TextUtils.isEmpty(birthday)) {
			ToastUtils.showLong("出生日期不能为空");
			return false;
		} else if (TextUtils.isEmpty(idCard)) {
			ToastUtils.showLong("身份证不能为空");
			return false;
		} else if (area == null || TextUtils.isEmpty(area.getName())) {
			ToastUtils.showLong("所属地区不能为空");
			return false;
		} else if (TextUtils.isEmpty(phone)) {
			ToastUtils.showLong("手机号码不能为空");
			return false;
		} else if (TextUtils.isEmpty(caseType)) {
			ToastUtils.showLong("案件类型不能为空");
			return false;
		} else if (TextUtils.isEmpty(hasMongol)) {
			ToastUtils.showLong("涉及蒙语不能为空");
			return false;
		} else if (TextUtils.isEmpty(caseReason)) {
			ToastUtils.showLong("案情及申请理由不能为空");
			return false;
		} else if ((!name.equals(UserHelper.getUser().getRealName()))
			&& (!proxyName.equals(UserHelper.getUser().getRealName()))) {
			ToastUtils.showLong("申请人或代理人其中一个的姓名和身份证必须和登录人一致");
			return false;
		} else if (isEmpty(aidCategory)) {
			ToastUtils.showLong("案件所属分类不能为空");
			return false;
		} else if (isEmpty(caseNature)) {
			ToastUtils.showLong("案件性质不能为空");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 性别，民族等字典值传 value 而不是 label
	 */
	@Override
	protected void createFrom() {

		mLegalAidWorkflow.setName(name);
		mLegalAidWorkflow.setSex(sex);
		mLegalAidWorkflow.setEthnic(ethnic);
		mLegalAidWorkflow.setBirthday(birthday);
		mLegalAidWorkflow.setIdCard(idCard);
		mLegalAidWorkflow.setArea(area);
		mLegalAidWorkflow.setPhone(phone);
		mLegalAidWorkflow.setEmployer(employer);
		mLegalAidWorkflow.setAddress(address);
		mLegalAidWorkflow.setDomicile(domicile);

		mLegalAidWorkflow.setProxyName(proxyName);
		mLegalAidWorkflow.setProxyIdCard(proxyIdCard);
		mLegalAidWorkflow.setProxyType(proxyType);

		mLegalAidWorkflow.setCaseTitle(caseTitle);
		mLegalAidWorkflow.setCaseType(caseType);
		mLegalAidWorkflow.setAidCategory(aidCategory);
		mLegalAidWorkflow.setCaseNature(caseNature);
		mLegalAidWorkflow.setCaseFile(caseFile);
		mLegalAidWorkflow.setHasMongol(hasMongol);
		mLegalAidWorkflow.setCaseReason(caseReason);
	}

	@Override
	protected void showDetails(Response<BaseBean<BusinessDetailsWrapper<LegalAidData>>> response) {
		super.showDetails(response);
		wrapper = response.body().getBody();
		bean = (LegalAidData) wrapper.getBusinessData();

		sex = bean.getSex();
		ethnic = bean.getEthnic();
		area = bean.getArea();
		proxyType = bean.getProxyType();
		caseType = bean.getCaseType();
		aidCategory = bean.getAidCategory();
		caseNature = bean.getCaseNature();
		hasMongol = bean.getHasMongol();
		caseFile = bean.getCaseFile();

		if (!TextUtils.isEmpty(bean.getProxyName())) {
			mEtAgentName.setEnabled(false);
			mEtAgentIdCardNum.setEnabled(false);
		} else {
			mEtName.setEnabled(false);
			mEtIdCardNum.setEnabled(false);
			mLlAgency.setVisibility(View.GONE);
		}

		mEtName.setText(bean.getName());
		mEtSex.setText(bean.getSexDesc());
		mEtEthnic.setText(bean.getEthnicDesc());
		mEtBirthday.setText(bean.getBirthday());
		mEtIdCardNum.setText(bean.getIdCard());
		if (area != null) {
			mEtArea.setText(bean.getArea().getName());
		}
		mEtWorkAddress.setText(bean.getEmployer());
		mEtHomeAddress.setText(bean.getDomicile());
		mEtAddress.setText(bean.getAddress());
		mEtPhoneNum.setText(bean.getPhone());
		mEtAgentName.setText(bean.getProxyName());
		mEtAgentIdCardNum.setText(bean.getProxyIdCard());
		mEtAgentType.setText(bean.getProxyTypeDesc());
		mEtTitle.setText(bean.getCaseTitle());
		mEtCaseType.setText(bean.getCaseTypeDesc());
		mEtLineMengyu.setText(bean.getHasMongolDesc());
		mEtContent.setText(bean.getCaseReason());
	}

	@Override
	protected void initView() {
		super.initView();
		findViewById(R.id.tv_file).setVisibility(View.VISIBLE);
	}
}

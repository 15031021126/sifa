package com.liuhezhineng.ximengsifa.business.noticedefense;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.CrimeAdapter;
import com.liuhezhineng.ximengsifa.adapter.DraftAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.noticedefense.NoticeRequestBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.business.base.BaseBusinessActivity;
import com.liuhezhineng.ximengsifa.business.peoplesmediation.havedone.MyDraftActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.DictKey;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.constant.NetConstant.Notice;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.model.StringDialogCallback;
import com.liuhezhineng.ximengsifa.utils.IDCardInfoExtractor;
import com.liuhezhineng.ximengsifa.utils.IDCardValidator;
import com.liuhezhineng.ximengsifa.utils.PickerViewBuilder;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 法援 -> 通知辩护
 */
public class NoticeDefenseActivity extends BaseBusinessActivity {

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.ll_user_info_header)
	LinearLayout mLlUserInfoHeader;
	@BindView(R.id.tv_name)
	EditText mTvName;
	@BindView(R.id.tv_sex)
	TextView mTvSex;
	@BindView(R.id.tv_id_card)
	EditText mTvIdCard;
	@BindView(R.id.tv_birthday)
	TextView mTvBirthday;
	@BindView(R.id.tv_ethnic)
	TextView mTvEthnic;
	@BindView(R.id.tv_education)
	TextView mTvEducation;
	@BindView(R.id.tv_case_involved_count)
	TextView mTvCaseInvolvedCount;
	@BindView(R.id.tv_domicile)
	TextView mTvDomicile;
	@BindView(R.id.tv_domicile_details)
	EditText mTvDomicileDetails;
	@BindView(R.id.tv_custody)
	EditText mTvCustody;
	@BindView(R.id.tv_nationality)
	TextView mTvNationality;
	@BindView(R.id.tv_contract)
	EditText mTvContract;
	@BindView(R.id.tv_phone)
	EditText mTvPhone;
	@BindView(R.id.tv_aid_person_category)
	TextView mTvAidPersonCategory;
	@BindView(R.id.tv_accept_time)
	TextView mTvAcceptTime;
	@BindView(R.id.tv_case_name)
	TextView mTvCaseName;
	@BindView(R.id.tv_notice_office_category)
	TextView mTvNoticeOfficeCategory;
	@BindView(R.id.tv_notice_office_name)
	TextView mTvNoticeOfficeName;
	@BindView(R.id.tv_office_contract)
	TextView mTvOfficeContract;
	@BindView(R.id.tv_notice_phone)
	TextView mTvNoticePhone;
	@BindView(R.id.tv_case_involved)
	TextView mTvCaseInvolved;
	@BindView(R.id.tv_notice_number)
	EditText mTvNoticeNumber;
	@BindView(R.id.tv_involved_crime)
	TextView mTvInvolvedCrime;
	@BindView(R.id.tv_case_year_no)
	EditText mTvCaseYearNo;
	@BindView(R.id.tv_case_number)
	EditText mTvCaseNumber;
	@BindView(R.id.tv_notice_reason)
	TextView mTvNoticeReason;
	@BindView(R.id.tv_litigation_stage)
	TextView mTvLitigationStage;
	@BindView(R.id.tv_incidental_plaintiff)
	EditText mTvIncidentalPlaintiff;
	@BindView(R.id.tv_review_opinion)
	EditText mTvReviewOpinion;
	@BindView(R.id.tv_file)
	TextView mTvFile;
	@BindView(R.id.rv_annex)
	RecyclerView mRvAnnex;
	@BindView(R.id.ll_annex)
	LinearLayout mLlAnnex;
	@BindView(R.id.tv_aid_center)
	TextView mTvAidCenter;
	@BindView(R.id.tv_aid_way)
	TextView mTvAidWay;
	@BindView(R.id.tv_review_date)
	TextView mTvReviewDate;
	@BindView(R.id.tv_review_content)
	EditText mTvReviewContent;
	@BindView(R.id.tv_lawyer)
	TextView mTvLawyer;
	@BindView(R.id.tv_write_case_content)
	EditText mTvWriteCaseContent;
	@BindView(R.id.tv_evaluate_content)
	EditText mTvEvaluateContent;
	@BindView(R.id.tv_notice_subsidy)
	EditText mTvNoticeSubsidy;
	@BindView(R.id.btn_return)
	Button mBtnSave;
	@BindView(R.id.btn_commit)
	Button mBtnCommit;
	@BindView(R.id.ll_btn)
	LinearLayout mLlBtn;
	@BindView(R.id.ll_sex_and_birthday)
	LinearLayout mLlSexAndBirthday;

	NoticeRequestBean mBean;
	String pickerFlag;
	ArrayList<Office> aidOfficeList = new ArrayList<>();
	OptionsPickerView<Office> aidOfficePicker;
	@BindView(R.id.tv_crime_content)
	TextView mTvCrimeContent;
	private String isSubmit;
	private Map<Integer, Boolean> isCheckedMap = new HashMap<>(16);
	private String crimeKey = "";
	private String crimeValue = "";
	private AlertDialog mDialog;

	public static void actionStart(Context context) {
		if (UserHelper.isIsLogin()) {
			Intent intent = new Intent(context, NoticeDefenseActivity.class);
			context.startActivity(intent);
		} else {
			gotoLogin(context);
		}
	}

	/**
	 * draft -> apply
	 */
	public static void actionStart(Context context, String id, String proDefKey) {
		if (UserHelper.isIsLogin()) {
			Intent intent = new Intent(context, NoticeDefenseActivity.class);
			intent.putExtra("id", id);
			intent.putExtra("procDefKey", proDefKey);
			((BaseActivity) context).startActivityForResult(intent, DraftAdapter.DEL_DRAFT);
		} else {
			gotoLogin(context);
		}
	}

	@Override
	protected void initView() {
		super.initView();
		initTopBar(mTopBar, "通知辩护申请表");
	}

	@Override
	protected void initData() {
		super.initData();
		mBean = new NoticeRequestBean();
		initPicker();

	}

	private void initPicker() {
		initCrimeDialog();
		initSexPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvSex.setText(sexList.get(options1));
				mBean.setSex(getSex(sexList.get(options1)));
			}
		});

		initBirthdayPicker(new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				if (pickerFlag.equals(Constant.ACCUSER)) {
					mTvBirthday.setText(getTime(date));
					mBean.setBirthday(getTime(date));
				} else if (pickerFlag.equals(Constant.CASE)) {
					mTvAcceptTime.setText(getTime(date));
					mBean.setSldate(getTime(date));
				}
			}
		});

		initCountyPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				Area area = countyList.get(options1);
				mTvDomicile.setText(area.getName());
				mBean.setDom(area);
			}
		});

		initEthnicPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvEthnic.setText(ethnicList.get(options1).getLabel());
				mBean.setEthnic(ethnicList.get(options1).getValue());
			}
		});

		initEducationPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvEducation.setText(educationList.get(options1).getLabel());
				mBean.setEducation(educationList.get(options1).getValue());
			}
		});

		initInternationalPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvNationality.setText(internationList.get(options1).getLabel());
				mBean.setInternation(internationList.get(options1).getValue());
			}
		});

		initAidPersonTypePicker(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvAidPersonCategory.setText(aidPersonTypeList.get(options1).getLabel());
				mBean.setRenyuanType(aidPersonTypeList.get(options1).getValue());
			}
		});

		initCaseInvolvedPicker(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvCaseInvolved.setText(caseInvolvedList.get(options1).getLabel());
				mBean.setCaseTelevancy(caseInvolvedList.get(options1).getValue());
			}
		});

		initInformReasonPicker(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvNoticeReason.setText(informReasonList.get(options1).getLabel());
				mBean.setInformReson(informReasonList.get(options1).getValue());
			}
		});

		initCasesStagePickerPicker(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvLitigationStage.setText(casesStageList.get(options1).getLabel());
				mBean.setCasesStage(casesStageList.get(options1).getValue());
			}
		});

		Map<String, String> map = new HashMap<>(16);
		map.put("type", "5");
		map.put("isUser", "");
		map.put("areaId", "");
		map.put("officeId", "");
		String queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<List<Office>>>post(Notice.LOAD_OFFICE_OR_USER_BY_AREA_AND_TYPE)
			.params(NetConstant.QUERY, queryStr)
			.execute(new JsonCallback<BaseBean<List<Office>>>() {
				@Override
				public void onSuccess(Response<BaseBean<List<Office>>> response) {
					aidOfficeList.addAll(response.body().getBody());
					aidOfficePicker = new PickerViewBuilder(mActivity,
						new OnOptionsSelectListener() {
							@Override
							public void onOptionsSelect(int options1, int options2, int options3,
								View v) {
								Office office = aidOfficeList.get(options1);
								mTvAidCenter.setText(office.getName());
								mBean.setLegalOffice(office);
							}
						}).build();
					aidOfficePicker.setPicker(aidOfficeList);
				}
			});

		initCrimePicker(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				crimeKey = crimeKey + "," + crimeList.get(options1).getValue();
				crimeValue = crimeValue + "," + crimeList.get(options1).getLabel();

				mBean.setCaseGuilt(crimeKey.substring(1));
				mTvCrimeContent.setText(crimeValue.substring(1));
			}
		});

	}

	private void initCrimeDialog() {
		View view = LayoutInflater.from(mActivity).inflate(R.layout.crime_dialog, null);
		mDialog = new AlertDialog.Builder(mActivity)
			.setView(view).create();
		RecyclerView rvCrime = view.findViewById(R.id.rv_crime);
		final ArrayList<TypeBean> list = new ArrayList<>();

		final CrimeAdapter adapter = new CrimeAdapter(mActivity, list, isCheckedMap);
		rvCrime.setAdapter(adapter);
		rvCrime.addItemDecoration(
			new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));

		Map<String, String> map = new HashMap<>(16);
		map.put(Constant.KEY, DictKey.CASE_GUILT);
		OkGo.<BaseBean<List<TypeBean>>>post(NetConstant.GET_TYPE)
			.cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
			.params(NetConstant.QUERY, new JSONObject(map).toString())
			.execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
				@Override
				public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
					list.addAll(response.body().getBody());
					adapter.initData(response.body().getBody());
				}
			});

		view.findViewById(R.id.tv_cancel).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDialog.dismiss();
			}
		});
		view.findViewById(R.id.tv_confirm).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				crimeKey = "";
				crimeValue = "";
				for (int i = 0; i < list.size(); i++) {
					if (isCheckedMap.get(i)) {
						crimeKey += "," + list.get(i).getValue();
						crimeValue += "," + list.get(i).getLabel();
					}
				}
				mBean.setCaseGuilt(crimeKey.length() > 0 ? crimeKey.substring(1) : crimeKey);
				mTvCrimeContent
					.setText(crimeValue.length() > 0 ? crimeValue.substring(1) : crimeValue);
				mDialog.dismiss();
			}
		});
	}

	@Override
	protected void loadDraft(String id, String procDefKey) {
		Map<String, String> map = new HashMap<>(16);
		map.put("id", id);
		map.put("procDefKey", procDefKey);
		String queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<PageBean<NoticeRequestBean>>>post(NetConstant.GET_DRAFT_DATA)
			.params(NetConstant.QUERY, queryStr)
			.execute(new DialogCallBack<BaseBean<PageBean<NoticeRequestBean>>>(mActivity) {
				@Override
				public void onSuccess(Response<BaseBean<PageBean<NoticeRequestBean>>> response) {
					NoticeRequestBean bean = response.body().getBody().getList().get(0);
					showDraftData(bean);
				}
			});
	}

	@Override
	protected void loadUserInfo() {
		UserBean user = UserHelper.getUser();

		mTvOfficeContract.setText(user.getRealName());
		mTvNoticePhone.setText(user.getMobile());
	}

	@Override
	protected void setListener() {
		super.setListener();

		mTvIdCard.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.toString().trim().length() == 18 && new IDCardValidator()
					.isValidatedAllIdCard(s.toString().trim())) {

					String idCard = mTvIdCard.getText().toString().trim();
					mBean.setIdCard(idCard);

					IDCardInfoExtractor idCardInfo = new IDCardInfoExtractor(idCard);
					String month = idCardInfo.getMonth() + "";
					if (idCardInfo.getMonth() < 10) {
						month = "0" + month;
					}
					String day = idCardInfo.getDay() + "";
					if (idCardInfo.getDay() < 10) {
						day = "0" + day;
					}
					String birthday =
						idCardInfo.getYear() + "-" + month + "-" + day;
					mTvBirthday.setText(birthday);
					mBean.setBirthday(birthday);

					String gender = idCardInfo.getGender();
					mTvSex.setText(gender);
					mBean.setSex(getSex(gender));

					mLlSexAndBirthday.setVisibility(View.VISIBLE);
				} else {
					mLlSexAndBirthday.setVisibility(View.GONE);
				}
			}
		});
	}

	@Override
	@OnClick({R.id.tv_sex, R.id.tv_birthday, R.id.tv_ethnic, R.id.tv_education,
		R.id.tv_case_involved_count, R.id.tv_nationality, R.id.tv_aid_person_category,
		R.id.tv_accept_time, R.id.tv_notice_office_category, R.id.tv_notice_office_name,
		R.id.tv_case_involved, R.id.tv_involved_crime, R.id.tv_notice_reason,
		R.id.tv_litigation_stage, R.id.tv_aid_way, R.id.tv_review_date, R.id.tv_lawyer,
		R.id.btn_return, R.id.btn_commit, R.id.tv_domicile, R.id.tv_aid_center})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		switch (view.getId()) {
			case R.id.tv_aid_center:
				showPickerView(aidOfficePicker);
				break;
			case R.id.tv_domicile:
				showPickerView(countyPickerView);
				break;
//			case R.id.tv_sex:
//				showPickerView(sexPickerView);
//				break;
//			case R.id.tv_birthday:
//				pickerFlag = Constant.ACCUSER;
//				birthdayPickerView.show();
//				break;
			case R.id.tv_ethnic:
				showPickerView(ethnicPickerView);
				break;
			case R.id.tv_education:
				showPickerView(educationPickerView);
				break;
			case R.id.tv_case_involved_count:
				break;
			case R.id.tv_nationality:
				showPickerView(internationPickerView);
				break;
			case R.id.tv_aid_person_category:
				showPickerView(aidPersonTypePicker);
				break;
			case R.id.tv_accept_time:
				pickerFlag = Constant.CASE;
				birthdayPickerView.show();
				break;
			case R.id.tv_notice_office_category:
				break;
			case R.id.tv_notice_office_name:
				break;
			case R.id.tv_case_involved:
				showPickerView(caseInvolvedPicker);
				break;
			case R.id.tv_involved_crime:
//				showPickerView(crimePicker);
				mDialog.show();
				break;
			case R.id.tv_notice_reason:
				showPickerView(informReasonPicker);
				break;
			case R.id.tv_litigation_stage:
				showPickerView(casesStagePicker);
				break;
			case R.id.tv_aid_way:
				break;
			case R.id.tv_review_date:
				break;
			case R.id.tv_lawyer:
				break;
			case R.id.btn_return:
				isSubmit = "0";
				createForm();
				commitNotice();
				break;
			case R.id.btn_commit:
				isSubmit = "1";
				createForm();
				if (checkInput()) {
					commitNotice();
				}
				break;
			default:
				break;
		}
	}

	private void createForm() {

		mBean.setIsSubmit(isSubmit);

		mBean.setName(mTvName.getText().toString().trim());
		mBean.setIdCard(mTvIdCard.getText().toString().trim());
		mBean.setCasesum(mTvCaseInvolvedCount.getText().toString().trim());
		mBean.setDomicile(mTvDomicileDetails.getText().toString().trim());
		mBean.setAddress(mTvCustody.getText().toString().trim());
		mBean.setProxyName(mTvContract.getText().toString().trim());
		mBean.setPhone(mTvPhone.getText().toString().trim());

		mBean.setCaseTitle(mTvCaseName.getText().toString().trim());
		mBean.setOfficeType(mTvNoticeOfficeCategory.getText().toString().trim());
		mBean.setOfficeNamee(mTvNoticeOfficeName.getText().toString().trim());
		mBean.setJgPerson(mTvOfficeContract.getText().toString().trim());
		mBean.setJgphone(mTvNoticePhone.getText().toString().trim());
		mBean.setCaseInform(mTvNoticeNumber.getText().toString().trim());
		mBean.setYear(mTvCaseYearNo.getText().toString().trim());
		mBean.setYearNo(mTvCaseNumber.getText().toString().trim());
		mBean.setCumName(mTvIncidentalPlaintiff.getText().toString().trim());
		mBean.setCaseReason(mTvReviewOpinion.getText().toString().trim());
		mBean.setCaseFile(caseFile);
	}

	private void commitNotice() {
		String queryStr = new Gson().toJson(mBean);
		OkGo.<String>post(Notice.REQUEST)
			.params(NetConstant.QUERY, queryStr)
			.execute(new StringDialogCallback(mActivity, "提交中...") {
				@Override
				protected void responseSuccess(JSONObject object) {
					if ("0".equals(isSubmit)) {
						ToastUtils.showLong("保存成功");
						MyDraftActivity.actionStart(mActivity);
						finishActivity();
					} else {
						ToastUtils.showLong("提交成功");
						commitResult();
						finishActivity();
					}
				}
			});
	}

	private boolean checkInput() {
		if (TextUtils.isEmpty(mBean.getName())) {
			ToastUtils.showLong("申请人姓名不能为空");
			return false;
		} else if (TextUtils.isEmpty(mBean.getEthnic())) {
			ToastUtils.showLong("申请人民族不能为空");
			return false;
		} else if (TextUtils.isEmpty(mBean.getIdCard())) {
			ToastUtils.showLong("申请人身份证号不能为空");
			return false;
		} else if (mBean.getIdCard().length() != 18 || !new IDCardValidator().isValidatedAllIdCard(mBean.getIdCard())) {
			ToastUtils.showLong("请输入正确的身份证号");
			mTvIdCard.requestFocus();
			mTvIdCard.setError("请输入正确的身份证号");
			return false;
		} else if (TextUtils.isEmpty(mBean.getSex())) {
			ToastUtils.showLong("请输入正确的身份证号");
			mTvIdCard.requestFocus();
			mTvIdCard.setError("请输入正确的身份证号");
			return false;
		} else if (TextUtils.isEmpty(mBean.getBirthday())) {
			ToastUtils.showLong("请输入正确的身份证号");
			mTvIdCard.requestFocus();
			mTvIdCard.setError("请输入正确的身份证号");
			return false;
		} else if (TextUtils.isEmpty(mBean.getDom().getId())) {
			ToastUtils.showLong("申请人户籍地不能为空");
			return false;
		} else if (TextUtils.isEmpty(mBean.getAddress())) {
			ToastUtils.showLong("申请人羁押地不能为空");
			return false;
		} else if (TextUtils.isEmpty(mBean.getSldate())) {
			ToastUtils.showLong("受理时间不能为空");
			return false;
		} else if (TextUtils.isEmpty(mBean.getCaseTitle())) {
			ToastUtils.showLong("案件名称不能为空");
			return false;
		} else if (TextUtils.isEmpty(mBean.getOfficeType())) {
			ToastUtils.showLong("通知机关类型不能为空");
			return false;
		} else if (TextUtils.isEmpty(mBean.getOfficeNamee())) {
			ToastUtils.showLong("通知机关名称不能为空");
			return false;
		} else if (TextUtils.isEmpty(mBean.getCaseInform())) {
			ToastUtils.showLong("通知函号不能为空");
			return false;
		} else if (TextUtils.isEmpty(mBean.getInformReson())) {
			ToastUtils.showLong("通知原因不能为空");
			return false;
		} else if (TextUtils.isEmpty(mBean.getCasesStage())) {
			ToastUtils.showLong("诉讼阶段");
			return false;
		} else if (TextUtils.isEmpty(mBean.getLegalOffice().getId())) {
			ToastUtils.showLong("请选择法律援助中心");
			return false;
		} else {
			return true;
		}
	}

	private void showDraftData(NoticeRequestBean data) {
		mBean = data;

		mTvName.setText(data.getName());
		mTvEthnic.setText(data.getEthnicdesc());
		mTvIdCard.setText(data.getIdCard());
		mTvSex.setText(data.getSexdesc());
		mTvBirthday.setText(data.getBirthday());
		mTvEducation.setText(data.getEducationdesc());
		mTvCaseInvolvedCount.setText(data.getCasesum());
		mTvDomicile.setText(data.getDom().getName());
		mTvDomicileDetails.setText(data.getDomicile());
		mTvCustody.setText(data.getAddress());
		mTvNationality.setText(data.getInternationdesc());
		mTvContract.setText(data.getProxyName());
		mTvPhone.setText(data.getPhone());
		mTvAidPersonCategory.setText(data.getRenyuanTypedesc());

		mTvAcceptTime.setText(data.getSldate());
		mTvCaseName.setText(data.getCaseTitle());
		mTvNoticeOfficeCategory.setText(data.getOfficeType());
		mTvNoticeOfficeName.setText(data.getOfficeNamee());
		mTvOfficeContract.setText(data.getJgPerson());
		mTvNoticePhone.setText(data.getJgphone());
		mTvCaseInvolved.setText(data.getCaseTelevancydesc());
		mTvNoticeNumber.setText(data.getCaseInform());
		mTvCrimeContent.setText(data.getCaseGuiltdesc());
		mTvCaseYearNo.setText(data.getYear());
		mTvCaseNumber.setText(data.getYearNo());
		mTvNoticeReason.setText(data.getInformResondesc());
		mTvLitigationStage.setText(data.getCasesStagedesc());
		mTvIncidentalPlaintiff.setText(data.getCumName());
		mTvReviewOpinion.setText(data.getCaseReason());

		setCaseFile(data.getCaseFile());

		mTvAidCenter.setText(data.getLegalOffice().getName());
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_notice_defense;
	}
}
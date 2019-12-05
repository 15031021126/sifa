package com.liuhezhineng.ximengsifa.business.noticedefense;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
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
import butterknife.BindView;
import butterknife.OnClick;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.CrimeAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.Act;
import com.liuhezhineng.ximengsifa.bean.bussiness.BusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.business.base.BaseNoticeActivity;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.Constant.DictKey;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.constant.NetConstant.Notice;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.model.StringDialogCallback;
import com.liuhezhineng.ximengsifa.utils.IDCardInfoExtractor;
import com.liuhezhineng.ximengsifa.utils.IDCardValidator;
import com.liuhezhineng.ximengsifa.utils.PickerViewBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 */
public class ModifyActivity extends BaseNoticeActivity {

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

	String pickerFlag;
	ArrayList<Office> aidOfficeList = new ArrayList<>();
	OptionsPickerView<Office> aidOfficePicker;
	@BindView(R.id.tv_crime_content)
	TextView mTvCrimeContent;
	private AlertDialog mDialog;
	private Map<Integer, Boolean> isCheckedMap = new HashMap<>(16);
	private String crimeKey = "";
	private String crimeValue = "";

	public static void actionStart(BaseActivity activity, BusinessBean bean) {
		Intent intent = new Intent(activity, ModifyActivity.class);
		intent.putExtra(Constant.BUSINESS, bean);
		activity.startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_modify;
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
			case R.id.tv_sex:
				showPickerView(sexPickerView);
				break;
			case R.id.tv_birthday:
				pickerFlag = Constant.ACCUSER;
				birthdayPickerView.show();
				break;
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
				flag = Constant.NO;
				showNoDialog();
				break;
			case R.id.btn_commit:
				flag = Constant.YES;
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

		mNoticeRequestBean.setName(mTvName.getText().toString().trim());
		mNoticeRequestBean.setIdCard(mTvIdCard.getText().toString().trim());
		mNoticeRequestBean.setCasesum(mTvCaseInvolvedCount.getText().toString().trim());
		mNoticeRequestBean.setDomicile(mTvDomicileDetails.getText().toString().trim());
		mNoticeRequestBean.setAddress(mTvCustody.getText().toString().trim());
		mNoticeRequestBean.setProxyName(mTvContract.getText().toString().trim());
		mNoticeRequestBean.setPhone(mTvPhone.getText().toString().trim());

		mNoticeRequestBean.setCaseTitle(mTvCaseName.getText().toString().trim());
		mNoticeRequestBean.setOfficeType(mTvNoticeOfficeCategory.getText().toString().trim());
		mNoticeRequestBean.setOfficeNamee(mTvNoticeOfficeName.getText().toString().trim());
		mNoticeRequestBean.setJgPerson(mTvOfficeContract.getText().toString().trim());
		mNoticeRequestBean.setJgphone(mTvNoticePhone.getText().toString().trim());
		mNoticeRequestBean.setCaseInform(mTvNoticeNumber.getText().toString().trim());
		mNoticeRequestBean.setYear(mTvCaseYearNo.getText().toString().trim());
		mNoticeRequestBean.setYearNo(mTvCaseNumber.getText().toString().trim());
		mNoticeRequestBean.setCumName(mTvIncidentalPlaintiff.getText().toString().trim());
		mNoticeRequestBean.setCaseReason(mTvReviewOpinion.getText().toString().trim());
		mNoticeRequestBean.setCaseFile(caseFile);
	}

	private boolean checkInput() {
		if (TextUtils.isEmpty(mNoticeRequestBean.getName())) {
			ToastUtils.showLong("申请人姓名不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getEthnic())) {
			ToastUtils.showLong("申请人民族不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getIdCard())) {
			ToastUtils.showLong("申请人身份证号不能为空");
			return false;
		} else if (mNoticeRequestBean.getId().length() != 18 || !new IDCardValidator()
			.isValidatedAllIdCard(mNoticeRequestBean.getIdCard())) {
			ToastUtils.showLong("请输入正确的身份证号");
			mTvIdCard.requestFocus();
			mTvIdCard.setError("请输入正确的身份证号");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getSex())) {
			ToastUtils.showLong("请输入正确的身份证号");
			mTvIdCard.requestFocus();
			mTvIdCard.setError("请输入正确的身份证号");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getBirthday())) {
			ToastUtils.showLong("请输入正确的身份证号");
			mTvIdCard.requestFocus();
			mTvIdCard.setError("请输入正确的身份证号");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getDom().getId())) {
			ToastUtils.showLong("申请人户籍地不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getAddress())) {
			ToastUtils.showLong("申请人羁押地不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getSldate())) {
			ToastUtils.showLong("受理时间不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getCaseTitle())) {
			ToastUtils.showLong("案件名称不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getOfficeType())) {
			ToastUtils.showLong("通知机关类型不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getOfficeNamee())) {
			ToastUtils.showLong("通知机关名称不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getCaseInform())) {
			ToastUtils.showLong("通知函号不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getInformReson())) {
			ToastUtils.showLong("通知原因不能为空");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getCasesStage())) {
			ToastUtils.showLong("诉讼阶段");
			return false;
		} else if (TextUtils.isEmpty(mNoticeRequestBean.getLegalOffice().getId())) {
			ToastUtils.showLong("请选择法律援助中心");
			return false;
		} else {
			return true;
		}
	}

	@Override
	protected void initView() {
		super.initView();
		mTvFile.setVisibility(View.VISIBLE);
	}

	@Override
	protected void initData() {
		super.initData();

		initPicker();
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
					mNoticeRequestBean.setIdCard(idCard);

					IDCardInfoExtractor idCardInfo = new IDCardInfoExtractor(
						idCard);
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
					mNoticeRequestBean.setBirthday(birthday);

					String gender = idCardInfo.getGender();
					mTvSex.setText(gender);
					mNoticeRequestBean.setSex(getSex(gender));

					mLlSexAndBirthday.setVisibility(View.VISIBLE);
				} else {
					mLlSexAndBirthday.setVisibility(View.GONE);
				}
			}
		});
	}

	@Override
	protected void commitNotice() {

		Act act = new Act();
		act.setProcInsId(mBusinessBean.getProcInsId());
		act.setProcDefId(mBusinessBean.getProcDefId());
		act.setProcDefKey(mBusinessBean.getProcDefKey());
		act.setTaskId(mBusinessBean.getTask().getId());
		act.setTaskName(mBusinessBean.getTask().getName());
		act.setTaskDefKey(mBusinessBean.getTask().getTaskDefinitionKey());
		act.setFlag(flag);
		act.setComment(comment);
		mNoticeRequestBean.setAct(act);
		String queryStr = new Gson().toJson(mNoticeRequestBean);

		String loadingStr = "";
		if (Constant.YES.equals(flag)) {
			loadingStr = "重新提交...";
		} else if (Constant.NO.equals(flag)) {
			loadingStr = "放弃申请...";
		}

		OkGo.<String>post(Notice.FLOW)
			.params(NetConstant.QUERY, queryStr)
			.execute(new StringDialogCallback(mActivity, loadingStr) {
				@Override
				protected void responseSuccess(JSONObject object) {
					if (Constant.YES.equals(flag)) {
						ToastUtils.showLong("提交成功");
					} else if (Constant.NO.equals(flag)) {
						ToastUtils.showLong("案件已放弃");
					}
					LocalBroadcastManager.getInstance(mActivity)
						.sendBroadcast(new Intent(Constant.DEAL_BUSINESS));
					setResult(RESULT_OK);
					finishActivity();
				}
			});
	}

	private void initPicker() {
		initCrimeDialog();
		initSexPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvSex.setText(sexList.get(options1));
				mNoticeRequestBean.setSex(getSex(sexList.get(options1)));
			}
		});

		initBirthdayPicker(new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				if (pickerFlag.equals(Constant.ACCUSER)) {
					mTvBirthday.setText(getTime(date));
					mNoticeRequestBean.setBirthday(getTime(date));
				} else if (pickerFlag.equals(Constant.CASE)) {
					mTvAcceptTime.setText(getTime(date));
					mNoticeRequestBean.setSldate(getTime(date));
				}
			}
		});

		initCountyPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				Area area = countyList.get(options1);
				mTvDomicile.setText(area.getName());
				mNoticeRequestBean.setDom(area);
			}
		});

		initEthnicPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvEthnic.setText(ethnicList.get(options1).getLabel());
				mNoticeRequestBean.setEthnic(ethnicList.get(options1).getValue());
			}
		});

		initEducationPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvEducation.setText(educationList.get(options1).getLabel());
				mNoticeRequestBean.setEducation(educationList.get(options1).getValue());
			}
		});

		initInternationalPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvNationality.setText(internationList.get(options1).getLabel());
				mNoticeRequestBean.setInternation(internationList.get(options1).getValue());
			}
		});

		initAidPersonTypePicker(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvAidPersonCategory.setText(aidPersonTypeList.get(options1).getLabel());
				mNoticeRequestBean.setRenyuanType(aidPersonTypeList.get(options1).getValue());
			}
		});

		initCaseInvolvedPicker(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvCaseInvolved.setText(caseInvolvedList.get(options1).getLabel());
				mNoticeRequestBean.setCaseTelevancy(caseInvolvedList.get(options1).getValue());
			}
		});

		initInformReasonPicker(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvNoticeReason.setText(informReasonList.get(options1).getLabel());
				mNoticeRequestBean.setInformReson(informReasonList.get(options1).getValue());
			}
		});

		initCasesStagePickerPicker(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				mTvLitigationStage.setText(casesStageList.get(options1).getLabel());
				mNoticeRequestBean.setCasesStage(casesStageList.get(options1).getValue());
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
								mNoticeRequestBean.setLegalOffice(office);
							}
						}).build();
					aidOfficePicker.setPicker(aidOfficeList);
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

				mNoticeRequestBean
					.setCaseGuilt(crimeKey.length() > 0 ? crimeKey.substring(1) : crimeKey);
				mTvCrimeContent
					.setText(crimeValue.length() > 0 ? crimeValue.substring(1) : crimeValue);
				mDialog.dismiss();
			}
		});
	}
}

package com.liuhezhineng.ximengsifa.business.base;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.bean.bussiness.BaseBusinessBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.PeoplesMediationData;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form.MediationWorkflow;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogStringCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @description 人民调解基类
 */
public abstract class BasePeoplesMediationActivity extends BaseBusinessActivity {

	protected MediationWorkflow mBean;
	protected PeoplesMediationData mData;

	@Override
	protected void initData() {
		super.initData();

		mBaseBusinessBean = new PeoplesMediationData();
	}

	protected void loadBusinessDetails() {
		OkGo.<String>post(NetConstant.GET_PEOPLES_MEDIATION_WORKFLOW)
			.params(NetConstant.QUERY, getBusinessDataQueryJson())
			.execute(new DialogStringCallBack(mActivity, "加载中...") {
				@Override
				public void onSuccess(Response<String> response) {
					try {
						JSONObject jsonObject = new JSONObject(response.body());
						int status = jsonObject.getInt(Constant.STATUS);
						if (status == 0) {
							JSONObject object = jsonObject.getJSONObject(Constant.BODY);
							JSONObject data = object.getJSONObject(Constant.BUSINESS_DATA);
							procInsId = data.getString("id");
                            // TODO: 2019/1/9 把 PeoplesMediationBean 改成了 BaseBusinessBean
                            // 没有详尽测试
							mBaseBusinessBean = new Gson().fromJson(
							        data.toString(),
                                    BaseBusinessBean.class);
							caseFile = data.getString("caseFile");
							setCaseFile(caseFile);

							parseData(data);
						} else {
							ToastUtils.showLong(jsonObject.getString(Constant.MSG));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

	/**
	 * 人民调解返回数据
	 *
	 * @param data json 数据，很乱
	 * @throws Exception {@link Exception}
	 */
	protected abstract void parseData(JSONObject data) throws Exception;

	/**
	 * 设置请求参数
	 *
	 * @param data 加载得到的数据
	 */
	protected void setRequestInfo(PeoplesMediationData data) {
		mBean.setAccuserName(data.getAccuserName());
		mBean.setAccuserIdcard(data.getAccuserIdcard());
		mBean.setAccuserSex(data.getAccuserSex());
		mBean.setAccuserEthnic(data.getAccuserEthnic());
		mBean.setAccuserBirthday(data.getAccuserBirthday());
		mBean.setAccuserCounty(data.getAccuserCounty());
		mBean.setAccuserTown(data.getAccuserTown());
		mBean.setAccuserOccupation(data.getAccuserOccupation());
		mBean.setAccuserDomicile(data.getAccuserDomicile());
		mBean.setAccuserAddress(data.getAccuserAddress());
		mBean.setAccuserPostCode(data.getAccuserPostCode());
		mBean.setAccuserPhone(data.getAccuserPhone());
		mBean.setDefendantName(data.getDefendantName());
		mBean.setDefendantSex(data.getDefendantSex());
		mBean.setDefendantEthnic(data.getDefendantEthnic());
		mBean.setDefendantIdcard(data.getDefendantIdcard());
		mBean.setDefendantBirthday(data.getDefendantBirthday());
		mBean.setDefendantCounty(data.getDefendantCounty());
		mBean.setDefendantTown(data.getDefendantTown());
		mBean.setDefendantOccupation(data.getDefendantOccupation());
		mBean.setDefendantDomicile(data.getDefendantDomicile());
		mBean.setDefendantAddress(data.getDefendantAddress());
		mBean.setDefendantPostCode(data.getDefendantPostCode());
		mBean.setDefendantPhone(data.getDefendantPhone());
		mBean.setMediator(data.getMediator());
		mBean.setPeopleMediationCommittee(data.getPeopleMediationCommittee());
		mBean.setCaseTitle(data.getCaseTitle());
		mBean.setCaseSituation(data.getCaseSituation());
		mBean.setCaseFile(data.getCaseFile());
		mBean.setCaseType(data.getCaseType());
		mBean.setCaseCounty(data.getCaseCounty());
		mBean.setCaseTown(data.getCaseTown());
	}

	protected void saveForm(String url) {
		OkGo.<String>post(url)
			.params(NetConstant.QUERY, getQueryStr())
			.execute(new DialogStringCallBack(mActivity, "保存中...") {
				@Override
				public void onSuccess(Response<String> response) {
					ToastUtils.showLong("保存成功");
				}
			});
	}

	private String getQueryStr() {
		mBean.setId(procInsId);
		mBean.setAct(getAct());
		mBean.setCaseFile(caseFile);
		createFrom();
		return new Gson().toJson(mBean);
	}

	protected void createFrom() {
	}

	protected void commitRequest1(String url) {
		OkGo.<String>post(url)
			.params(NetConstant.QUERY, getQueryStr())
			.execute(new DialogStringCallBack(mActivity, "提交中...") {
				@Override
				public void onSuccess(Response<String> response) {
					try {
						JSONObject jsonObject = new JSONObject(response.body());
						int status = jsonObject.getInt(Constant.STATUS);
						if (status == 0) {
							if (!"no".equals(flag)) {
								ToastUtils.showShort("通过成功");
							} else {
								ToastUtils.showShort("退回成功");
							}
							LocalBroadcastManager.getInstance(mActivity)
								.sendBroadcast(new Intent(Constant.DEAL_BUSINESS));
							setResult(RESULT_OK);
							finishActivity();
						} else {
							ToastUtils.showLong(jsonObject.getString(Constant.MSG));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
	}
}

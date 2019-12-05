package com.liuhezhineng.ximengsifa.base;

import android.support.v7.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.QueryUtils;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.PageBean;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.InsUserBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Committee;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.bean.personal.PersonInsBean;
import com.liuhezhineng.ximengsifa.bean.picker.InsBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.liuhezhineng.ximengsifa.utils.PickerViewBuilder;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.liuhezhineng.ximengsifa.constant.Constant.AID_CATEGORY;
import static com.liuhezhineng.ximengsifa.constant.Constant.AID_CENTER;
import static com.liuhezhineng.ximengsifa.constant.Constant.AID_LAWYER;
import static com.liuhezhineng.ximengsifa.constant.Constant.Business;
import static com.liuhezhineng.ximengsifa.constant.Constant.CASE_CLASSIFY;
import static com.liuhezhineng.ximengsifa.constant.Constant.CASE_INVOLVING;
import static com.liuhezhineng.ximengsifa.constant.Constant.CASE_NATURE;
import static com.liuhezhineng.ximengsifa.constant.Constant.CASE_RANK;
import static com.liuhezhineng.ximengsifa.constant.Constant.CASE_SOURCE;
import static com.liuhezhineng.ximengsifa.constant.Constant.COMMITTEE;
import static com.liuhezhineng.ximengsifa.constant.Constant.DictKey;
import static com.liuhezhineng.ximengsifa.constant.Constant.EDUCATION;
import static com.liuhezhineng.ximengsifa.constant.Constant.ETHNIC;
import static com.liuhezhineng.ximengsifa.constant.Constant.KEY;
import static com.liuhezhineng.ximengsifa.constant.Constant.LAWYER;
import static com.liuhezhineng.ximengsifa.constant.Constant.LAWYER_OFFICE;
import static com.liuhezhineng.ximengsifa.constant.Constant.LEGAL_OFFICE;
import static com.liuhezhineng.ximengsifa.constant.Constant.LEGAL_PERSON;
import static com.liuhezhineng.ximengsifa.constant.Constant.PICKER_NO_DATA;
import static com.liuhezhineng.ximengsifa.constant.Constant.PROXY_TYPE;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.AREA_ID;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.CATEGORY_ID;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.FastLegal;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.GET_CITY;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.GET_INSTITUTIONS;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.GET_INSTITUTIONS_BY_AREA;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.GET_SUB_TYPE;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.GET_TYPE;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.PAGE_NO;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.PAGE_SIZE;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.QUERY;
import static com.liuhezhineng.ximengsifa.constant.NetConstant.TOWN_ID;

/**
 * @author iqing
 * @description 单纯的只是为了将那臃肿的代码找个地方放，不要污染了 BaseActivity
 */
public class PickerActivity extends AppCompatActivity {

    protected BaseActivity mActivity;


    /**
     * warning_time_type 超时办理时间：一小时、12小时、一天、三天、一周
     */
    protected ArrayList<TypeBean> warningTimeTypeList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> warningTimeTypePicker;

    protected void initWarningTimeTypePicker(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, "warning_time_type");
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (warningTimeTypeList != null) {
                            warningTimeTypeList.clear();
                            warningTimeTypeList.addAll(response.body().getBody());
                        }
                        warningTimeTypePicker = new PickerViewBuilder(mActivity, listener).build();
                        warningTimeTypePicker.setPicker(warningTimeTypeList);
                    }
                });
    }
    /**
     * 是否允许超时办理 warning_state_type: 是 否
     */
    protected ArrayList<TypeBean> warningStateTypeList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> warningStateTypePicker;

    protected void initWarningStateTypePicker(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, "warning_state_type");
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (warningStateTypeList != null) {
                            warningStateTypeList.clear();
                            warningStateTypeList.addAll(response.body().getBody());
                        }
                        warningStateTypePicker = new PickerViewBuilder(mActivity, listener).build();
                        warningStateTypePicker.setPicker(warningStateTypeList);
                    }
                });
    }

    protected ArrayList<String> sexList;
    protected OptionsPickerView<String> sexPickerView;
    protected ArrayList<TypeBean> ethnicList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> ethnicPickerView;
    protected ArrayList<Area> countyList = new ArrayList<>();
    protected OptionsPickerView<Area> countyPickerView;
    protected ArrayList<Area> townList = new ArrayList<>();
    protected OptionsPickerView<Area> townPickerView;
    protected ArrayList<TypeBean> agentTypeList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> agentTypePickerView;
    protected ArrayList<String> mongolList;
    protected OptionsPickerView<String> mongolPickerView;
    protected ArrayList<TypeBean> caseTypeList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> caseTypePicker;
    protected TimePickerView birthdayPickerView;
    protected ArrayList<TypeBean> caseResourceList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> caseResourcePickerView;
    protected ArrayList<TypeBean> caseRankList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> caseRankPickerView;
    protected ArrayList<TypeBean> disputeTypeList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> disputeTypePickerView;
    protected ArrayList<Committee> committeeList = new ArrayList<>();
    protected OptionsPickerView<Committee> committeePickerView;
    protected ArrayList<TypeBean> aidCategoryList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> aidCategoryPickerView;
    protected ArrayList<TypeBean> caseNatureList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> caseNaturePickerView;
    protected OptionsPickerView lawyerPersonPickerView;
    /**
     * 我要投诉的机构
     */
    protected ArrayList<Office> officeList = new ArrayList<>();
    protected OptionsPickerView<Office> officePickerView;
    protected ArrayList<TypeBean> suchComplaintsList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> suchComplaintsPickerView;
    protected ArrayList<TypeBean> classWorkerList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> classWorkerPickerView;
    protected ArrayList<TypeBean> educationList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> educationPickerView;
    protected ArrayList<TypeBean> occupationList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> occupationPickerView;
    protected ArrayList<PersonInsBean> aidLawyerList = new ArrayList<>();
    protected OptionsPickerView<PersonInsBean> aidLawyerPicker;
    protected ArrayList<PersonInsBean> lawyerList = new ArrayList<>();
    protected OptionsPickerView<PersonInsBean> lawyerPicker;
    protected ArrayList<PersonInsBean> legalPersonList = new ArrayList<>();
    protected OptionsPickerView<PersonInsBean> legalPersonPicker;
    /**
     * 律所
     */
    protected ArrayList<InsBean> lawyerOfficeList = new ArrayList<>();
    protected OptionsPickerView<InsBean> lawyerOfficePicker;
    /**
     * 法援中心
     */
    protected ArrayList<InsBean> aidCenterList = new ArrayList<>();
    protected OptionsPickerView<InsBean> aidCenterPicker;
    /**
     * 基层法律服务所
     */
    protected ArrayList<InsBean> legalOfficeList = new ArrayList<>();
    protected OptionsPickerView<InsBean> legalOfficePicker;
    protected ArrayList<TypeBean> fastCaseTypeList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> fastCaseTypePickerView;
    /**
     * 法律援助，人民调解
     */
    protected ArrayList<TypeBean> actTypeList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> actTypePickerView;
    protected ArrayList<TypeBean> internationList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> internationPickerView;
    protected ArrayList<TypeBean> aidWayList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> aidWayPickerView;
    protected ArrayList<TypeBean> aidPersonTypeList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> aidPersonTypePicker;
    protected ArrayList<TypeBean> caseMoneyList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> caseMoneyPickerView;
    protected ArrayList<TypeBean> caseInvolvedList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> caseInvolvedPicker;
    protected ArrayList<TypeBean> modalityList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> modalityPicker;
    protected ArrayList<TypeBean> crimeList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> crimePicker;
    protected ArrayList<InsUserBean> insList = new ArrayList<>();
    protected OptionsPickerView<InsUserBean> insPicker;
    protected ArrayList<InsUserBean> peopleList = new ArrayList<>();
    protected OptionsPickerView<InsUserBean> peoplePicker;
    protected ArrayList<TypeBean> informReasonList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> informReasonPicker;
    protected ArrayList<TypeBean> casesStageList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> casesStagePicker;
    protected ArrayList<String> videoParamList = new ArrayList<>();
    protected OptionsPickerView<String> videoParamPicker;
    protected ArrayList<TypeBean> orderMoldList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> orderMoldPickerView;
    /**
     * 投诉建议类型
     */
    protected ArrayList<TypeBean> consultingComplaintTypeList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> consultingComplaintTypeView;
    protected ArrayList<TypeBean> yesOrNoList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> yesOrNoPicker;




    protected ArrayList<TypeBean> examineList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> examinePickerView;
    //申请事项所处阶段诉讼括号中的
    protected void initExamineView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, "examine_type");
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (examineList != null) {
                            examineList.clear();
                            examineList.addAll(response.body().getBody());
                        }
                        examinePickerView = new PickerViewBuilder(mActivity, listener).build();
                        examinePickerView.setPicker(examineList);
                    }
                });
    }
    protected ArrayList<TypeBean> yesnoList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> yesnoPickerView;
    //是否符合经济来源
    protected void initYesNoView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, "yes_no");
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (yesnoList != null) {
                            yesnoList.clear();
                            yesnoList.addAll(response.body().getBody());
                        }
                        yesnoPickerView = new PickerViewBuilder(mActivity, listener).build();
                        yesnoPickerView.setPicker(yesnoList);
                    }
                });
    }


    protected ArrayList<TypeBean> cmpanyList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> companyPickerView;
    //案件来源转交申请括号中的
protected void initCompanyView(final OnOptionsSelectListener listener) {
    Map<String, String> map = new HashMap<>(16);
    map.put(KEY, "office_company_type");
    OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
            .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
            .params(QUERY, new JSONObject(map).toString())
            .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                @Override
                public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                    if (cmpanyList != null) {
                        cmpanyList.clear();
                        cmpanyList.addAll(response.body().getBody());
                    }
                    companyPickerView = new PickerViewBuilder(mActivity, listener).build();
                    companyPickerView.setPicker(cmpanyList);
                }
            });
}

    protected ArrayList<TypeBean> stageList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> casePickerView;
    //申请事项所处阶段
    protected void initstageView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, "case_stage_type");
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (stageList != null) {
                            stageList.clear();
                            stageList.addAll(response.body().getBody());
                        }
                        casePickerView = new PickerViewBuilder(mActivity, listener).build();
                        casePickerView.setPicker(stageList);
                    }
                });
    }
    protected ArrayList<TypeBean> matterList = new ArrayList<>();


    protected OptionsPickerView<TypeBean> matterPickerView;
    // //申请事项
    protected void initMatterView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, "apply_matter_type");
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (matterList != null) {
                            matterList.clear();
                            matterList.addAll(response.body().getBody());
                        }
                        matterPickerView = new PickerViewBuilder(mActivity, listener).build();
                        matterPickerView.setPicker(matterList);
                    }
                });
    }
    protected ArrayList<TypeBean> sourceList = new ArrayList<>();

    protected OptionsPickerView<TypeBean> sourcePickerView;
    //案件来源
    protected void initSourceView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, "case_source");
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (sourceList != null) {
                            sourceList.clear();
                            sourceList.addAll(response.body().getBody());
                        }
                        sourcePickerView = new PickerViewBuilder(mActivity, listener).build();
                        sourcePickerView.setPicker(sourceList);
                    }
                });
    }


    protected ArrayList<TypeBean> peopletypeList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> peoplePickerView;
    //人群類別
   protected void initPeopleView(final OnOptionsSelectListener listener) {
    Map<String, String> map = new HashMap<>(16);
    map.put(KEY, "crowd_type");
    OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
            .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
            .params(QUERY, new JSONObject(map).toString())
            .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                @Override
                public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                    if (peopletypeList != null) {
                        peopletypeList.clear();
                        peopletypeList.addAll(response.body().getBody());
                    }
                    peoplePickerView = new PickerViewBuilder(mActivity, listener).build();
                    peoplePickerView.setPicker(peopletypeList);
                }
            });
}


    protected ArrayList<TypeBean> educationrList = new ArrayList<>();
    protected OptionsPickerView<TypeBean> educPickerView;
    //文化
     protected void initeducationrView(final OnOptionsSelectListener listener) {
    Map<String, String> map = new HashMap<>(16);
    map.put(KEY, "education_degree_type");
    OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
            .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
            .params(QUERY, new JSONObject(map).toString())
            .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                @Override
                public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                    if (educationrList != null) {
                        educationrList.clear();
                        educationrList.addAll(response.body().getBody());
                    }
                    educPickerView = new PickerViewBuilder(mActivity, listener).build();
                    educPickerView.setPicker(educationrList);
                }
            });
}








    /**
     * 查询机构
     * *
     * * isUser 是否查询机构下人员
     * * id 不为空是查询某个机构下的人员
     *
     * @param listener
     * @param type     legal_aid 法援中心
     *                 people_mediation	人民调解
     *                 apply_lawyer	申请律师
     *                 legal_service_office基层法律服所
     *                 apply_appraise	申请鉴定
     *                 apply_notarization	申请公证
     * @param countyId
     * @param townId
     */
    protected void initInsPicker(final OnOptionsSelectListener listener,
                                 String type,
                                 String countyId,
                                 String townId) {
        insList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put("type", type);
        //0 jigou 1jigou and renyuan
        map.put("isUser", "0");
        map.put("areaId", countyId);
        map.put("townId", townId);
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<List<InsUserBean>>>post(FastLegal.GET_INS_OR_PERSON)
                .params(QUERY, queryStr)
                .cacheKey(UserHelper.getToken() + queryStr)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<List<InsUserBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<InsUserBean>>> response) {
                        List<InsUserBean> list = response.body().getBody();
                        if (list != null && list.size() > 0) {
                            insList.addAll(list);
                        } else {
                            InsUserBean insUserBean = new InsUserBean();
                            insUserBean.setId(PICKER_NO_DATA);
                            insUserBean.setName("该地区下暂无相关承办机构");
                            insList.add(insUserBean);
                        }
                        insPicker = new PickerViewBuilder(mActivity, listener).build();
                        insPicker.setPicker(insList);
                    }
                });
    }

    /**
     * 通过乡镇旗县 id 来获取人民调解员数据
     *
     * @param listener {@link OnOptionsSelectListener}
     * @param type     {@link Business}
     * @param insId    机构 id
     * @param countyId 旗县 id
     * @param townId   乡镇 id
     */
    protected void initPeoplePicker(final OnOptionsSelectListener listener,
                                    String type,
                                    String insId,
                                    String countyId,
                                    String townId) {
        peopleList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put("type", type);
        // 0 jigou 1jigou and renyuan，这里永远都是查人，所以固定为 1
        map.put("isUser", "1");
        map.put("id", insId);
        map.put("areaId", countyId);
        map.put("townId", townId);
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<List<InsUserBean>>>post(FastLegal.GET_INS_OR_PERSON)
                .params(QUERY, queryStr)
                .cacheKey(UserHelper.getToken() + queryStr)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<List<InsUserBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<InsUserBean>>> response) {
                        List<InsUserBean> list = response.body().getBody();
                        for (InsUserBean bean : list) {
                            peopleList.addAll(bean.getUsers());
                        }
                        if (list.size() <= 0) {
                            InsUserBean users = new InsUserBean();
                            users.setId(PICKER_NO_DATA);
                            users.setName("暂无相关承办人");
                            peopleList.add(users);
                        }
                        peoplePicker = new PickerViewBuilder(mActivity, listener).build();
                        peoplePicker.setPicker(peopleList);
                    }
                });
    }

    protected void initSuchComplaintsView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, "cms_complaint_shixiang");
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        List<TypeBean> list = response.body().getBody();
                        if (suchComplaintsList != null) {
                            suchComplaintsList.clear();
                            suchComplaintsList.addAll(response.body().getBody());
                        }
                        suchComplaintsPickerView = new PickerViewBuilder(mActivity, listener)
                                .build();

                        suchComplaintsPickerView.setPicker(suchComplaintsList);
                    }
                });
    }

    protected void initAidPersonTypePicker(final OnOptionsSelectListener listener) {
        aidPersonTypeList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, DictKey.PEOPLE_TYPE);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        aidPersonTypeList.addAll(response.body().getBody());
                        aidPersonTypePicker = new PickerViewBuilder(mActivity, listener).build();
                        aidPersonTypePicker.setPicker(aidPersonTypeList);
                    }
                });
    }

    protected void initCaseInvolvedPicker(final OnOptionsSelectListener listener) {
        caseInvolvedList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, CASE_INVOLVING);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        caseInvolvedList.addAll(response.body().getBody());
                        caseInvolvedPicker = new PickerViewBuilder(mActivity, listener).build();
                        caseInvolvedPicker.setPicker(caseInvolvedList);
                    }
                });
    }

    protected void initModalityPicker(final OnOptionsSelectListener listener) {
        modalityList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, DictKey.MODALITY);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        modalityList.addAll(response.body().getBody());
                        modalityPicker = new PickerViewBuilder(mActivity, listener).build();
                        modalityPicker.setPicker(modalityList);
                    }
                });
    }

    protected void initCrimePicker(final OnOptionsSelectListener listener) {
        crimeList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, DictKey.CASE_GUILT);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        crimeList.addAll(response.body().getBody());
                        crimePicker = new PickerViewBuilder(mActivity, listener).build();
                        crimePicker.setPicker(crimeList);
                    }
                });
    }

    protected void initCaseMoneyPicker(final OnOptionsSelectListener listener) {
        caseMoneyList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, DictKey.CASE_MONEY);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        caseMoneyList.addAll(response.body().getBody());
                        caseMoneyPickerView = new PickerViewBuilder(mActivity, listener)
                                .build();
                        caseMoneyPickerView.setPicker(caseMoneyList);
                    }
                });
    }

    protected void initInformReasonPicker(final OnOptionsSelectListener listener) {
        informReasonList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, DictKey.INFORM_REASON);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        informReasonList.addAll(response.body().getBody());
                        informReasonPicker = new PickerViewBuilder(mActivity, listener)
                                .build();
                        informReasonPicker.setPicker(informReasonList);
                    }
                });
    }

    protected void initCasesStagePickerPicker(final OnOptionsSelectListener listener) {
        casesStageList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, DictKey.CASES_STAGE);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        casesStageList.addAll(response.body().getBody());
                        casesStagePicker = new PickerViewBuilder(mActivity, listener).build();
                        casesStagePicker.setPicker(casesStageList);
                    }
                });
    }

    protected void initAidWayView(final OnOptionsSelectListener listener) {
        aidWayList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, DictKey.AID_WAY);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        List<TypeBean> list = response.body().getBody();
                        aidWayList.addAll(list);
                        aidWayPickerView = new PickerViewBuilder(mActivity, listener).build();
                        aidWayPickerView.setPicker(aidWayList);
                    }
                });
    }

    protected void initOccupationPickerViewView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, "sys_user_source_type");
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        List<TypeBean> list = response.body().getBody();
                        if (occupationList != null) {
                            occupationList.clear();
                            occupationList.addAll(list);
                        }
                        occupationPickerView = new PickerViewBuilder(mActivity, listener)
                                .build();

                        occupationPickerView.setPicker(occupationList);
                    }
                });
    }

    protected void initClassWorkerPickerViewView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, "cms_complaint_worker");
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        List<TypeBean> list = response.body().getBody();
                        if (classWorkerList != null) {
                            classWorkerList.clear();
                            classWorkerList.addAll(list);
                        }
                        classWorkerPickerView = new PickerViewBuilder(mActivity, listener)
                                .build();

                        classWorkerPickerView.setPicker(classWorkerList);
                    }
                });
    }

    protected void initOfficePickerView(final OnOptionsSelectListener listener, String areaId) {
        OkGo.<BaseBean<List<Office>>>post(GET_INSTITUTIONS_BY_AREA)
                .params(QUERY, "{\"area\":{\"id\":\"" + areaId + "\"}}")
                .cacheKey(GET_INSTITUTIONS_BY_AREA + areaId)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<List<Office>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<Office>>> response) {
                        officeList.addAll(response.body().getBody());

                        officePickerView = new PickerViewBuilder(mActivity, listener)
                                .build();
                        officePickerView.setPicker(officeList);
                    }
                });
    }

    protected void initAidLawyerPicker(final OnOptionsSelectListener listener,
                                       String countyId,
                                       String officeId) {
        aidLawyerList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(Constant.CATEGORY_ID, AID_LAWYER);
        map.put(Constant.AREA_ID, countyId);
        map.put("isAidLawyer", "1");
        map.put("officeId", officeId);
        map.put(PAGE_NO, String.valueOf(1));
        map.put(PAGE_SIZE, String.valueOf(Integer.MAX_VALUE));
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<PageBean<PersonInsBean>>>post(GET_INSTITUTIONS)
                .params(QUERY, queryStr)
                .cacheKey(queryStr)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<PageBean<PersonInsBean>>>(mActivity) {

                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<PersonInsBean>>> response) {
                        List<PersonInsBean> list = response.body().getBody().getList();
                        if (list == null || list.size() <= 0) {
                            PersonInsBean bean = new PersonInsBean();
                            bean.setId(PICKER_NO_DATA);
                            bean.setPersonName("该地区下暂无法援律师");
                            aidLawyerList.add(bean);
                        } else {
                            aidLawyerList.addAll(list);
                        }
                        aidLawyerPicker = new PickerViewBuilder(mActivity, listener).build();
                        aidLawyerPicker.setPicker(aidLawyerList);
                    }
                });
    }

    protected void initLawyerPicker(final OnOptionsSelectListener listener,
                                    String countyId,
                                    String officeId) {
        lawyerList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(CATEGORY_ID, LAWYER);
        map.put("officeId", officeId);
        map.put(AREA_ID, countyId);
        map.put(PAGE_NO, String.valueOf(1));
        map.put(PAGE_SIZE, String.valueOf(Integer.MAX_VALUE));
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<PageBean<PersonInsBean>>>post(GET_INSTITUTIONS)
                .params(QUERY, queryStr)
                .cacheKey(queryStr)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<PageBean<PersonInsBean>>>(mActivity) {

                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<PersonInsBean>>> response) {
                        List<PersonInsBean> list = response.body().getBody().getList();
                        if (list == null || list.size() <= 0) {
                            PersonInsBean bean = new PersonInsBean();
                            bean.setId(PICKER_NO_DATA);
                            bean.setPersonName("该地区下暂无律师");
                            lawyerList.add(bean);
                        } else {
                            lawyerList.addAll(list);
                        }
                        lawyerPicker = new PickerViewBuilder(mActivity, listener).build();
                        lawyerPicker.setPicker(lawyerList);
                    }
                });
    }

    protected void initLegalOfficePicker(final OnOptionsSelectListener listener, String countyId) {
        legalOfficeList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(CATEGORY_ID, LEGAL_OFFICE);
        map.put(AREA_ID, countyId);
        map.put(PAGE_NO, String.valueOf(1));
        map.put(PAGE_SIZE, String.valueOf(Integer.MAX_VALUE));
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<PageBean<InsBean>>>post(GET_INSTITUTIONS)
                .params(QUERY, queryStr)
                .cacheKey(queryStr)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<PageBean<InsBean>>>(mActivity) {

                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<InsBean>>> response) {
                        List<InsBean> list = response.body().getBody().getList();
                        if (list == null || list.size() <= 0) {
                            InsBean bean = new InsBean();
                            bean.setId(PICKER_NO_DATA);
                            bean.setAgencyName("该地区下暂无基层法律服务所");
                            legalOfficeList.add(bean);
                        } else {
                            legalOfficeList.addAll(list);
                        }
                        legalOfficePicker = new PickerViewBuilder(mActivity, listener).build();
                        legalOfficePicker.setPicker(legalOfficeList);
                    }
                });
    }

    protected void initAidCenterPicker(final OnOptionsSelectListener listener, String countyId) {
        aidCenterList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(CATEGORY_ID, AID_CENTER);
        map.put(AREA_ID, countyId);
        map.put(PAGE_NO, String.valueOf(1));
        map.put(PAGE_SIZE, String.valueOf(Integer.MAX_VALUE));
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<PageBean<InsBean>>>post(GET_INSTITUTIONS)
                .params(QUERY, queryStr)
                .cacheKey(queryStr)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<PageBean<InsBean>>>(mActivity) {

                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<InsBean>>> response) {
                        List<InsBean> list = response.body().getBody().getList();
                        if (list == null || list.size() <= 0) {
                            InsBean bean = new InsBean();
                            bean.setId(PICKER_NO_DATA);
                            bean.setAgencyName("该地区下暂无法援中心");
                            aidCenterList.add(bean);
                        } else {
                            aidCenterList.addAll(list);
                        }
                        aidCenterPicker = new PickerViewBuilder(mActivity, listener).build();
                        aidCenterPicker.setPicker(aidCenterList);
                    }
                });
    }

    protected void initLawyerOfficePicker(final OnOptionsSelectListener listener, String countyId) {
        lawyerOfficeList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(CATEGORY_ID, LAWYER_OFFICE);
        map.put(AREA_ID, countyId);
        map.put(PAGE_NO, String.valueOf(1));
        map.put(PAGE_SIZE, String.valueOf(Integer.MAX_VALUE));
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<PageBean<InsBean>>>post(GET_INSTITUTIONS)
                .params(QUERY, queryStr)
                .cacheKey(queryStr)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<PageBean<InsBean>>>(mActivity) {

                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<InsBean>>> response) {
                        List<InsBean> list = response.body().getBody().getList();
                        if (list == null || list.size() <= 0) {
                            InsBean bean = new InsBean();
                            bean.setId(PICKER_NO_DATA);
                            bean.setAgencyName("该地区下暂无律所");
                            lawyerOfficeList.add(bean);
                        } else {
                            lawyerOfficeList.addAll(list);
                        }
                        lawyerOfficePicker = new PickerViewBuilder(mActivity, listener).build();
                        lawyerOfficePicker.setPicker(lawyerOfficeList);
                    }
                });
    }

    protected void initLegalPersonPicker(final OnOptionsSelectListener listener, String countyId, String officeId) {
        legalPersonList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(CATEGORY_ID, LEGAL_PERSON);
        map.put(AREA_ID, countyId);
        map.put("officeId", officeId);
        map.put(PAGE_NO, String.valueOf(1));
        map.put(PAGE_SIZE, String.valueOf(Integer.MAX_VALUE));
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<PageBean<PersonInsBean>>>post(GET_INSTITUTIONS)
                .params(QUERY, queryStr)
                .cacheKey(queryStr)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<PageBean<PersonInsBean>>>(mActivity) {

                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<PersonInsBean>>> response) {
                        List<PersonInsBean> list = response.body().getBody().getList();
                        if (list == null || list.size() <= 0) {
                            PersonInsBean personInsBean = new PersonInsBean();
                            personInsBean.setId(PICKER_NO_DATA);
                            personInsBean.setPersonName("该地区无基层法律服务人员");
                            legalPersonList.add(personInsBean);
                        } else {
                            legalPersonList.addAll(list);
                        }
                        legalPersonPicker = new PickerViewBuilder(mActivity, listener).build();
                        legalPersonPicker.setPicker(legalPersonList);
                    }
                });
    }

    /**
     * 初始化法援分类
     */
    protected void initAidCategoryPickerView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, AID_CATEGORY);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (aidCategoryList != null) {
                            aidCategoryList.clear();
                            aidCategoryList.addAll(response.body().getBody());
                        }
                        aidCategoryPickerView = new PickerViewBuilder(mActivity, listener)
                                .build();
                        aidCategoryPickerView.setPicker(aidCategoryList);
                    }
                });
    }

    protected void initCaseNatureView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, CASE_NATURE);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (caseNatureList != null) {
                            caseNatureList.clear();
                            caseNatureList.addAll(response.body().getBody());
                        }
                        caseNaturePickerView = new PickerViewBuilder(mActivity, listener)
                                .build();

                        caseNaturePickerView.setPicker(caseNatureList);
                    }
                });
    }

    public String getSex(String sexDesc) {
        if ("男".equals(sexDesc)) {
            return "1";
        } else if ("女".equals(sexDesc)) {
            return "2";
        } else {
            return "";
        }
    }

    /**
     * "女".equals(sexList.get(options1)) ? "2" : "1"
     */
    protected void initSexPickerView(OnOptionsSelectListener listener) {
        sexList = new ArrayList<>();
        sexList.add("男");
        sexList.add("女");
        sexPickerView = new PickerViewBuilder(mActivity, listener).build();
        sexPickerView.setPicker(sexList);
    }

    protected void initVideoParamPickerView(OnOptionsSelectListener listener) {
        videoParamList = new ArrayList<>();
        videoParamList.add("攻击性");
        videoParamList.add("焦虑");
        videoParamList.add("平衡");
        videoParamList.add("活力");
        videoParamList.add("抑郁");
        videoParamList.add("压力");
        videoParamList.add("可疑");
        videoParamList.add("自信");
        videoParamList.add("自我调节");
        videoParamList.add("神经质");
        videoParamPicker = new PickerViewBuilder(mActivity, listener).build();
        videoParamPicker.setPicker(videoParamList);
    }

    protected void initEducationPickerView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, EDUCATION);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        List<TypeBean> list = response.body().getBody();
                        educationList.addAll(list);
                        educationPickerView = new PickerViewBuilder(mActivity, listener).build();
                        educationPickerView.setPicker(educationList);
                    }
                });
    }

    protected void initInternationalPickerView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, DictKey.NATIONALITY);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        List<TypeBean> list = response.body().getBody();
                        internationList.addAll(list);
                        internationPickerView = new PickerViewBuilder(mActivity, listener).build();
                        internationPickerView.setPicker(internationList);
                    }
                });
    }

    protected void initEthnicPickerView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, ETHNIC);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (ethnicList != null) {
                            ethnicList.clear();
                            ethnicList.addAll(response.body().getBody());
                        }
                        ethnicPickerView = new PickerViewBuilder(mActivity, listener).build();
                        ethnicPickerView.setPicker(ethnicList);
                    }
                });
    }

    protected void initBirthdayPicker(OnTimeSelectListener listener) {
        birthdayPickerView = new TimePickerBuilder(mActivity, listener)
                .setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true)
                .build();

//		Dialog mDialog = birthdayPickerView.getDialog();
//		if (mDialog != null) {
//
//			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
//				ViewGroup.LayoutParams.MATCH_PARENT,
//				ViewGroup.LayoutParams.WRAP_CONTENT,
//				Gravity.BOTTOM);
//
//			params.leftMargin = 0;
//			params.rightMargin = 0;
//			birthdayPickerView.getDialogContainerLayout().setLayoutParams(params);
//
//			Window dialogWindow = mDialog.getWindow();
//			if (dialogWindow != null) {
//				dialogWindow.setWindowAnimations(
//					com.bigkoo.pickerview.R.style.picker_view_slide_anim);
//				dialogWindow.setGravity(Gravity.BOTTOM);
//			}
//		}
    }

    protected void initCountyPickerView(final OnOptionsSelectListener listener) {
        countyList.clear();
        OkGo.<BaseBean<List<Area>>>post(GET_CITY)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<List<Area>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<Area>>> response) {
                        if (countyList != null) {
                            countyList.clear();
                            countyList.addAll(response.body().getBody());
                        }
                        countyPickerView = new PickerViewBuilder(mActivity, listener).build();
                        countyPickerView.setPicker(countyList);
                    }
                });
    }

    protected void initTownPickerView(final OnOptionsSelectListener listener, String countyId) {
        townList.clear();
        OkGo.<BaseBean<List<Area>>>post(GET_CITY)
                .cacheKey("town_" + countyId)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, "{\"parentId\":\"" + countyId + "\"}")
                .execute(new JsonCallback<BaseBean<List<Area>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<Area>>> response) {
                        if (townList != null) {
                            townList.clear();
                            townList.addAll(response.body().getBody());
                        }
                        townPickerView = new PickerViewBuilder(mActivity, listener)
                                .build();

                        townPickerView.setPicker(townList);
                    }
                });
    }

    protected void initAgentTypePickerView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, PROXY_TYPE);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (agentTypeList != null) {
                            agentTypeList.clear();
                            agentTypeList.addAll(response.body().getBody());
                        }
                        agentTypePickerView = new PickerViewBuilder(mActivity, listener)
                                .build();
                        agentTypePickerView.setPicker(agentTypeList);
                    }
                });
    }

    public String hasMongolian(String hasMongolian) {
        if ("是".equals(hasMongolian)) {
            return "1";
        } else if ("否".equals(hasMongolian)) {
            return "0";
        } else {
            return "-1";
        }
    }

    protected void initMongolPickerView(final OnOptionsSelectListener listener) {
        mongolList = new ArrayList<>();
        mongolList.add("是");
        mongolList.add("否");
        mongolPickerView = new PickerViewBuilder(mActivity, listener).build();
        mongolPickerView.setPicker(mongolList);
    }

    protected void initCaseTypePicker(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, CASE_CLASSIFY);
        map.put("parentId", "legal_aid");
        OkGo.<BaseBean<List<TypeBean>>>post(GET_SUB_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (caseTypeList != null) {
                            caseTypeList.clear();
                            caseTypeList.addAll(response.body().getBody());
                        }
                        caseTypePicker = new PickerViewBuilder(mActivity, listener).build();
                        caseTypePicker.setPicker(caseTypeList);
                    }
                });
    }

    protected void initFastCastTypePicker(final OnOptionsSelectListener listener, String parentId) {
        fastCaseTypeList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, CASE_CLASSIFY);
        map.put("parentId", parentId);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_SUB_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        List<TypeBean> list = response.body().getBody();
                        if (list != null && list.size() > 0) {
                            fastCaseTypeList.addAll(list);
                        } else {
                            TypeBean bean = new TypeBean();
                            bean.setValue(PICKER_NO_DATA);
                            bean.setLabel("该类别案件暂无案件类型");
                            fastCaseTypeList.add(bean);
                        }
                        fastCaseTypePickerView = new PickerViewBuilder(mActivity, listener).build();
                        fastCaseTypePickerView.setPicker(fastCaseTypeList);
                    }
                });
    }

    /**
     * 在线预约里的预约详细(二级字典)
     *
     * @param listener
     * @param parentId
     */
    protected void initMoldPicker(final OnOptionsSelectListener listener, String parentId) {
        orderMoldList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, "cms_appointment_type");
        map.put("parentId", parentId);
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<List<TypeBean>>>post(GET_SUB_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, queryStr)
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        List<TypeBean> list = response.body().getBody();
                        if (list != null && list.size() > 0) {
                            orderMoldList.addAll(list);
                        } else {
                            TypeBean bean = new TypeBean();
                            bean.setValue(PICKER_NO_DATA);
                            bean.setLabel("该预约类型暂无预约详细");
                            orderMoldList.add(bean);
                        }
                        orderMoldPickerView = new PickerViewBuilder(mActivity, listener).build();
                        orderMoldPickerView.setPicker(orderMoldList);
                    }
                });
    }

    /**
     * 投诉建议的类型(一级字典) 投诉还是建议
     *
     * @param listener {@link OnOptionsSelectListener}
     */
    protected void initConsultingComplaintTypePicker(final OnOptionsSelectListener listener) {
        consultingComplaintTypeList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, "cms_remarkType");
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, queryStr)
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        List<TypeBean> list = response.body().getBody();
                        if (list != null && list.size() > 0) {
                            consultingComplaintTypeList.addAll(list);
                        } else {
                            TypeBean bean = new TypeBean();
                            bean.setValue(PICKER_NO_DATA);
                            bean.setLabel("暂无相关类型");
                            consultingComplaintTypeList.add(bean);
                        }
                        consultingComplaintTypeView = new PickerViewBuilder(mActivity, listener).build();
                        consultingComplaintTypeView.setPicker(consultingComplaintTypeList);
                    }
                });
    }

    protected void initYesOrNoPicker(final OnOptionsSelectListener listener) {
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new QueryUtils().params(KEY, "yes_no").getQueryStr())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        yesOrNoList.clear();
                        yesOrNoList.addAll(response.body().getBody());
                        yesOrNoPicker = new PickerViewBuilder(mActivity, listener).build();
                        yesOrNoPicker.setPicker(yesOrNoList);
                    }
                });
    }

    protected void initActTypePicker(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, CASE_CLASSIFY);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (actTypeList != null) {
                            actTypeList.clear();
                            actTypeList.addAll(response.body().getBody());
                        }
                        actTypePickerView = new PickerViewBuilder(mActivity, listener).build();
                        actTypePickerView.setPicker(actTypeList);
                    }
                });
    }

    protected void initCaseResourcePickerView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, CASE_SOURCE);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (caseResourceList != null) {
                            caseResourceList.clear();
                            caseResourceList.addAll(response.body().getBody());
                        }
                        caseResourcePickerView = new PickerViewBuilder(mActivity, listener)
                                .build();

                        caseResourcePickerView.setPicker(caseResourceList);
                    }
                });
    }

    protected void initCaseRankView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, CASE_RANK);
        OkGo.<BaseBean<List<TypeBean>>>post(GET_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (caseRankList != null) {
                            caseRankList.clear();
                            caseRankList.addAll(response.body().getBody());
                        }
                        caseRankPickerView = new PickerViewBuilder(mActivity, listener)
                                .build();

                        caseRankPickerView.setPicker(caseRankList);
                    }
                });
    }

    protected void initDisputeTypePickerView(final OnOptionsSelectListener listener) {
        Map<String, String> map = new HashMap<>(16);
        map.put(KEY, CASE_CLASSIFY);
        map.put("parentId", "people_mediation");
        OkGo.<BaseBean<List<TypeBean>>>post(GET_SUB_TYPE)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<List<TypeBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<TypeBean>>> response) {
                        if (disputeTypeList != null) {
                            disputeTypeList.clear();
                            disputeTypeList.addAll(response.body().getBody());
                        }
                        disputeTypePickerView = new PickerViewBuilder(mActivity, listener)
                                .build();

                        disputeTypePickerView.setPicker(disputeTypeList);
                    }
                });
    }

    /**
     * 初始化人民调解委员会
     * 申请人调
     * 人调科员指定
     * 人调修改
     */
    protected void initCommitteePickerView(final OnOptionsSelectListener listener, String areaId,
                                           String townId) {
        committeeList.clear();
        Map<String, String> map = new HashMap<>(16);
        map.put(CATEGORY_ID, COMMITTEE);
        map.put(AREA_ID, areaId);
        map.put(TOWN_ID, townId);
        map.put(PAGE_NO, "1");
        map.put(PAGE_SIZE, String.valueOf(Integer.MAX_VALUE));
        OkGo.<BaseBean<PageBean<Committee>>>post(GET_INSTITUTIONS)
                .cacheKey("committee_" + areaId + townId)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params(QUERY, new JSONObject(map).toString())
                .execute(new JsonCallback<BaseBean<PageBean<Committee>>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseBean<PageBean<Committee>>> response) {
                        PageBean<Committee> pageBean = response.body().getBody();
                        if (pageBean.getCount() <= 0) {
                            Committee committee = new Committee();
                            committee.setAgencyName("该地区暂无调解委员会");
                            committee.setId(PICKER_NO_DATA);
                            committeeList.add(committee);
                        } else {
                            committeeList.addAll(pageBean.getList());
                        }
                        committeePickerView = new PickerViewBuilder(mActivity, listener).build();
                        committeePickerView.setPicker(committeeList);
                    }
                });
    }

    protected void showPickerView(OptionsPickerView pickerView) {
        if (pickerView == null) {
            ToastUtils.showLong("数据加载中，请稍等再试...");
        } else {
            pickerView.show();
        }
    }

    protected String getTime(Date date) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
        return s.format(date);
        // 此方法得出的结果是 xxxx 年 x 月 x 日，这里必须是 xxxx-xx-xx
        // return SimpleDateFormat.getDateInstance().format(date);
    }

}

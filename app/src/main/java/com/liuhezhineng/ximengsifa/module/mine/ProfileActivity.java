package com.liuhezhineng.ximengsifa.module.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.ProfileBean;
import com.liuhezhineng.ximengsifa.bean.login.RoleBean;
import com.liuhezhineng.ximengsifa.bean.login.UserBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.MyStringCallback;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

import static com.liuhezhineng.ximengsifa.constant.Constant.IMAGE_PICKER;

/**
 * @author AIqinfeng
 * @description 个人信息
 * 1、查看
 * 2、修改 （修改之后在进去显示修改之后的数据）
 * * 2.1、加载用户信息，从（内存、首选项（数据库））。
 * * 2.2、修改用户信息，更新服务器、首选项、内存、数据库。
 */
public class ProfileActivity extends BaseActivity implements OnClickListener {

    @BindView(R.id.qmui_list_view)
    QMUIGroupListView mQmuiListView;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;

    private QMUICommonListItemView accountView;
    private QMUICommonListItemView rolesView;
    private QMUICommonListItemView nameView;
    private QMUICommonListItemView sexView;
    private QMUICommonListItemView idCardView;
    private QMUICommonListItemView nationView;
    private QMUICommonListItemView countyView;
    private QMUICommonListItemView townView;
    private QMUICommonListItemView birthdayView;
    private QMUICommonListItemView phoneView;
    private QMUICommonListItemView educationView;
    private QMUICommonListItemView careerView;

    /**
     * 用户信息修改上传实体类
     */
    private ProfileBean mBean;

    public static void actionStart(Context context) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, ProfileActivity.class);
            context.startActivity(intent);
        } else {
            gotoLogin(context);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(R.string.profile);
        initQMUIGroupView();
        setData();
    }

    @Override
    protected void initData() {
        super.initData();
        mBean = new ProfileBean(UserHelper.getUser());
        initPicker();
    }

    @Override
    protected void setListener() {
        super.setListener();
        findViewById(R.id.iv_avatar).setOnClickListener(this);
    }

    private void initPicker() {
        initEthnicPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                nationView.setDetailText(ethnicList.get(options1).getLabel());
                mBean.setEthnic(ethnicList.get(options1).getValue());
                mBean.setEthnicDesc(ethnicList.get(options1).getLabel());
                modifyUserInfo();
            }
        });

        initSexPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                sexView.setDetailText(sexList.get(options1));
                mBean.getUserExpand().setSex("女".equals(sexList.get(options1)) ? "2" : "1");
                modifyUserInfo();
            }
        });

        initBirthdayPicker(new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                birthdayView.setDetailText(getTime(date));
                mBean.setBirthday(getTime(date));
                modifyUserInfo();
            }
        });

        initCountyPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                final Area county = countyList.get(options1);
                countyView.setDetailText(county.getName());
                mBean.setArea(county);
                modifyUserInfo();

                loadTownData(county.getId());
            }
        });

        initEducationPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                educationView.setDetailText(educationList.get(options1).getLabel());
                mBean.getUserExpand().setEducation(educationList.get(options1).getValue());
                mBean.getUserExpand().setEducationDesc(educationList.get(options1).getLabel());
                modifyUserInfo();
            }
        });

        initOccupationPickerViewView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                careerView.setDetailText(occupationList.get(options1).getLabel());
                mBean.setUserSourceType(occupationList.get(options1).getValue());
                mBean.setUserSourceTypeDesc(occupationList.get(options1).getLabel());
                modifyUserInfo();
            }
        });
    }

    private void initQMUIGroupView() {
        accountView = mQmuiListView.createItemView(getString(R.string.account));
        rolesView = mQmuiListView.createItemView(getString(R.string.roles));
        nameView = mQmuiListView.createItemView(getString(R.string.name));
        sexView = mQmuiListView.createItemView(getString(R.string.sex));
        idCardView = mQmuiListView.createItemView(getString(R.string.id_card_num));
        nationView = mQmuiListView.createItemView(getString(R.string.ethnic));
        countyView = mQmuiListView.createItemView(getString(R.string.county));
        townView = mQmuiListView.createItemView(getString(R.string.town));
        birthdayView = mQmuiListView.createItemView(getString(R.string.birthday));
        phoneView = mQmuiListView.createItemView(getString(R.string.phone_number));
        educationView = mQmuiListView.createItemView(getString(R.string.education));
        careerView = mQmuiListView.createItemView(getString(R.string.career));

        sexView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        nationView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        countyView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        townView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        birthdayView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        phoneView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        educationView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        careerView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        mQmuiListView.setSeparatorStyle(QMUIGroupListView.SEPARATOR_STYLE_NORMAL);
        QMUIGroupListView.newSection(this)
                .addItemView(accountView, this)
                .addItemView(rolesView, this)
                .addItemView(nameView, this)
                .addItemView(idCardView, this)
                .addItemView(sexView, this)
                .addItemView(nationView, this)
                .addItemView(countyView, this)
                .addItemView(townView, this)
                .addItemView(birthdayView, this)
                .addItemView(phoneView, this)
                .addItemView(educationView, this)
                .addItemView(careerView, this)
                .addTo(mQmuiListView);
    }

    private void setData() {
        // 加载用户信息
        UserBean user = UserHelper.getUser();

        Picasso.get().load(NetConstant.FILE_URL + user.getPhoto())
                .placeholder(R.drawable.default_person)
                .into(mIvAvatar);
        List<RoleBean> list = user.getRoleList();
        StringBuilder sb = new StringBuilder();
        for (RoleBean bean : list) {
            sb.append(bean.getName()).append(",");
        }
        rolesView.setDetailText(sb.toString().substring(0, sb.length() - 1));
        accountView.setDetailText(user.getLoginName());
        nameView.setDetailText(user.getRealName());
        String paperNum = user.getPaperNum();
        String idCardStr = paperNum.substring(0, 3) + "*********" + paperNum.substring(14);
        idCardView.setDetailText(idCardStr);

        sexView.setDetailText(user.getSexDesc());
        nationView.setDetailText(user.getEthnicDesc());
        countyView.setDetailText(user.getCounty().getName());
        townView.setDetailText(user.getTown().getName());
        birthdayView.setDetailText(user.getBirthday());
        phoneView.setDetailText(user.getMobile());
        educationView.setDetailText(user.getEducationDesc());
        careerView.setDetailText(user.getUserSourceTypeDesc());

        if (user.getCounty() != null && !TextUtils.isEmpty(user.getCounty().getId())) {
            loadTownData(user.getCounty().getId());
        }
    }

    private void loadTownData(String countyId) {
        initTownPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (options1 < 0) {
                    ToastUtils.showLong("请等待数据加载完成");
                    return;
                }
                Area town = townList.get(options1);
                townView.setDetailText(town.getName());
                mBean.setTownarea(town);
                modifyUserInfo();
            }
        }, countyId);
    }

    /**
     * 更新服务器用户信息
     */
    private void modifyUserInfo() {
        String queryStr = new Gson().toJson(mBean);
        OkGo.<String>post(NetConstant.MODIFY_USER_INFO)
                .params(Constant.QUERY, queryStr)
                .execute(new MyStringCallback(mActivity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int status = jsonObject.getInt(Constant.STATUS);
                            if (status != 0) {
                                ToastUtils.showLong(jsonObject.getString(Constant.MSG));
                            } else {
                                // 更新首选项、内存、本地数据库的用户信息
                                UserHelper.updateUser(mBean);
                                ToastUtils.showShort("修改成功");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constant.NORMAL_CODE) {
            String phone = data.getStringExtra("phone");
            phoneView.setDetailText(phone);
        }
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList images = (ArrayList) data
                        .getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                String path = ((ImageItem) images.get(0)).path;
                uploadAvatar(path);
            } else {
                ToastUtils.showLong(R.string.no_data);
            }
        }
    }

    private void uploadAvatar(final String path) {
        OkGo.<BaseBean<String>>post(NetConstant.UPLOAD_AVATAR)
                .params(Constant.FILE_DATA, new File(path))
                .execute(new DialogCallBack<BaseBean<String>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        String avatarServerPath = response.body().getBody();
                        UserHelper.updateUserAvatar(mActivity, avatarServerPath);
                        mIvAvatar.setImageURI(Uri.fromFile(new File(path)));
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_avatar) {
            Intent intent = new Intent(mActivity, ImageGridActivity.class);
            startActivityForResult(intent, IMAGE_PICKER);
        }
        if (v instanceof QMUICommonListItemView) {
            String string = String.valueOf(((QMUICommonListItemView) v).getText());
            actionClickList(string);
        }
    }

    private void actionClickList(String content) {
        switch (content) {
            case "性别":
                showPickerView(sexPickerView);
                break;
            case "民族":
                showPickerView(ethnicPickerView);
                break;
            case "所属旗县":
                showPickerView(countyPickerView);
                break;
            case "所属乡镇":
                if (TextUtils.isEmpty(countyView.getDetailText())) {
                    ToastUtils.showLong("请先选择旗县");
                    return;
                }
                showPickerView(townPickerView);
                break;
            case "出生日期":
                birthdayPickerView.show();
                break;
            case "手机号码":
                ModifyPhoneActivity.actionStart(mActivity);
                break;
            case "学历":
                showPickerView(educationPickerView);
                break;
            case "职业":
                showPickerView(occupationPickerView);
                break;
            default:
                break;
        }
    }
}

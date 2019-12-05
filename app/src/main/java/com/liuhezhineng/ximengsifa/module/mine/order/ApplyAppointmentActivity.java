package com.liuhezhineng.ximengsifa.module.mine.order;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.TypeBean;
import com.liuhezhineng.ximengsifa.bean.advisory.CreateUser;
import com.liuhezhineng.ximengsifa.bean.order.OrderRequestBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在线预约
 */
public class ApplyAppointmentActivity extends BaseActivity {

    public static final String BOOKED_USER_ID = "bookedUserId";
    public static final String TYPE = "type";

    @BindView(R.id.tv_order_name)
    TextView mTvOrderName;
    @BindView(R.id.et_order_phone)
    TextView mEtOrderPhone;
    @BindView(R.id.tv_order_date)
    TextView mTvOrderDate;
    @BindView(R.id.tv_order_details)
    TextView mTvOrderDetails;
    @BindView(R.id.et_order_content)
    EditText mEtOrderContent;

    private String bookedUserId;
    private String type;
    private String appointmentDate;
    private String mold;
    private String content;
    private String phone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_appointment;
    }

    /**
     * @param context
     * @param orderType    预约类型
     * @param bookedUserId 被预约人 id
     */
    public static void actionStart(Context context, String orderType, String bookedUserId) {
        if (UserHelper.isIsLogin()) {
            Intent intent = new Intent(context, ApplyAppointmentActivity.class);
            intent.putExtra(TYPE, orderType);
            intent.putExtra(BOOKED_USER_ID, bookedUserId);
            context.startActivity(intent);
        } else {
            gotoLogin(context);
        }
    }

    private void getIntentExtra() {
        Intent intent = getIntent();
        type = intent.getStringExtra(TYPE);
        bookedUserId = intent.getStringExtra(BOOKED_USER_ID);
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(R.string.online_order);
    }

    protected TimePickerView birthdayPickerView;

    @Override
    protected void initBirthdayPicker(OnTimeSelectListener listener) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTimeInMillis(System.currentTimeMillis());
        Calendar endDate = Calendar.getInstance();
        // 2222 year 2 month 2 day 2 hour 2 min 2 second
        // https://tool.chinaz.com/Tools/unixtime.aspx
        endDate.setTimeInMillis(7955085722000L);
        // 这里设置一下开始时间和结束时间 只能预约未来 TODO 还有时分秒没有控制好
        // Caused by: java.lang.IllegalArgumentException: startDate can't be later than endDate
        birthdayPickerView = new TimePickerBuilder(mActivity, listener)
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true)
                .setRangDate(startDate, endDate)
                .build();
    }

    @Override
    protected String getTime(Date date) {
        // 看代码意思是，就是中国包括中华人民共和国和台湾，如果 app 只面向大陆，就 china，仅面向台湾就 taiwan, 面向中国就 Chinese，个人理解的
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        return s.format(date);
        // 此方法得出的结果是 xxxx 年 x 月 x 日，这里必须是 xxxx-xx-xx
        // return SimpleDateFormat.getDateInstance().format(date);
    }

    @Override
    protected void initData() {
        super.initData();

        mTvOrderName.setText(UserHelper.getUser().getRealName());
        mEtOrderPhone.setText(UserHelper.getUser().getMobile());

        getIntentExtra();

        initBirthdayPicker(new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                appointmentDate = getTime(date);
                mTvOrderDate.setText(appointmentDate);
            }
        });

        initMoldPicker(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                TypeBean typeBean = orderMoldList.get(options1);
                mold = typeBean.getValue();
                mTvOrderDetails.setText(typeBean.getLabel());
            }
        }, type);
    }

    @OnClick({R.id.tv_online_order, R.id.tv_order_details, R.id.tv_order_date})
    void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_online_order:
                onlineOrder();
                break;
            case R.id.tv_order_date:
                birthdayPickerView.show();
                break;
            case R.id.tv_order_details:
                showPickerView(orderMoldPickerView);
                break;
            default:
                break;
        }
    }

    private void onlineOrder() {

        content = mEtOrderContent.getText().toString().trim();
        phone = mEtOrderPhone.getText().toString().trim();

        // 输入校验
        if (!checkInput()) {
            return;
        }

        //appointmentDate	|	[string]	|	默认值	|	预约时间    选择赋值
        //type	            |	[string]	|		|	预约分类cms_appointment_type        上个页面传递
        //mold	            |	[string]	|		|	预约详细cms_appointment_type        选择赋值
        //content	        |	[string]	|		|	预约内容        输入赋值
        //bookedUser.id	    |	[string]	|		|	被预约人        上个页面传递
        //phone	            |	[string]	|		|	预约时手机号    直接通过保存的用户信息获取
        // {"appointmentDate":"2018-12-16","type":"2","mold":"203","content":"啊飒飒是啊飒飒啊飒飒","phone":"15701234123","bookedUser":{"id":"sdasddfsdewewessdsdesasasas"}}
        CreateUser createUser = new CreateUser();
        createUser.setId(bookedUserId);
        OrderRequestBean bean = new OrderRequestBean();
        bean.setBookedUser(createUser);
        bean.setAppointmentDate(appointmentDate);
        bean.setType(type);
        bean.setMold(mold);
        bean.setContent(content);
        bean.setPhone(phone);
        String queryStr = new Gson().toJson(bean);
        Log.i(TAG, "onlineOrder: " + queryStr);
        OkGo.<BaseBean<String>>post(NetConstant.Order.REQUEST_ORDER)
                .params(Constant.QUERY, queryStr)
                .execute(new DialogCallBack<BaseBean<String>>(mActivity, "预约中...") {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        MyOrderActivity.actionStart(mActivity);
                        finishActivity();
                    }
                });
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showLong("请输入手机号");
            return false;
        } else if (TextUtils.isEmpty(mold)) {
            ToastUtils.showLong("请选择预约详细");
            return false;
        } else if (TextUtils.isEmpty(appointmentDate)) {
            ToastUtils.showLong("请选择预约时间");
            return false;
        } else {
            return true;
        }
    }
}

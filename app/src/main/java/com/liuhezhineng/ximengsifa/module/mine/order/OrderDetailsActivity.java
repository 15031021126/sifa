package com.liuhezhineng.ximengsifa.module.mine.order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.order.OrderBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的 -》 我的预约
 * 我的预约/待处理预约
 */
public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_order_no)
    TextView mTvOrderNo;
    @BindView(R.id.tv_worker)
    TextView mTvWorker;
    @BindView(R.id.tv_order_name)
    TextView mTvOrderName;
    @BindView(R.id.tv_order_phone)
    TextView mTvOrderPhone;
    @BindView(R.id.tv_order_date)
    TextView mTvOrderDate;
    @BindView(R.id.tv_order_details)
    TextView mTvOrderDetails;
    @BindView(R.id.tv_order_content)
    TextView mTvOrderContent;
    @BindView(R.id.ll_reject_reason)
    LinearLayout mLlRejectReason;
    @BindView(R.id.tv_reject_reason)
    TextView mTvRejectReason;

    @BindView(R.id.tv_reject)
    TextView mTvReject;
    @BindView(R.id.tv_agree)
    TextView mTvAgree;

    OrderBean mBean;
    // 预约状态 1 同意预约， 2 拒绝预约
    String state;
    String comment;

    public static void actionStart(Context context, OrderBean bean, String from) {
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra("bean", bean);
        intent.putExtra("from", from);
        ((BaseActivity) context).startActivityForResult(intent, Constant.DEAL_BUSINESS_CODE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(R.string.order_details);
    }

    @Override
    protected void initData() {
        super.initData();
        String from = getIntent().getStringExtra("from");
        mBean = (OrderBean) getIntent().getSerializableExtra("bean");
        if ("我的预约".equals(from)
                || !"待处理".equals(mBean.getStateDesc())) {
            mTvAgree.setVisibility(View.GONE);
            mTvReject.setVisibility(View.GONE);
        }
        mTvRejectReason.setText(mBean.getReturnContent());
        if (TextUtils.isEmpty(mBean.getReturnContent())) {
            mLlRejectReason.setVisibility(View.GONE);
        }
        mTvOrderNo.setText(mBean.getNum());
        mTvWorker.setText(mBean.getBookedUser().getName());
        mTvOrderName.setText(mBean.getCreateBy().getName());
        mTvOrderPhone.setText(mBean.getPhone());
        mTvOrderDate.setText(mBean.getAppointmentDate());
        mTvOrderDetails.setText(mBean.getMoldDesc());
        mTvOrderContent.setText(mBean.getContent());
    }

    @OnClick({R.id.tv_agree, R.id.tv_reject})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_agree:
                state = "1";
                dealOrder();
                break;
            default:
                state = "2";
                showNoDialog();
                break;
        }
    }

    protected void showNoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(mActivity, R.layout.dialog_no, null);

        TextView tvTitle = dialogView.findViewById(R.id.tv_title);
        final EditText etReason = dialogView.findViewById(R.id.et_no_reason);
        Button btnCancel = dialogView.findViewById(R.id.btn_no);
        Button btnConfirm = dialogView.findViewById(R.id.btn_yes);
        btnConfirm.setText("确认拒绝预约");

        String errorStr = "拒绝原因不能为空";

        btnCancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        final String finalErrorStr = errorStr;
        btnConfirm.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment = etReason.getText().toString().trim();
                if (TextUtils.isEmpty(comment)) {
                    ToastUtils.showLong(finalErrorStr);
                    return;
                }
                dealOrder();
                dialog.dismiss();
            }
        });
        dialog.setView(dialogView);
        dialog.show();
    }


    private void dealOrder() {
        //id	|	[string]	|	默认值	|	预约id
        //returnContent	|	[string]	|		|	预约处理结果
        //state	|	[string]	|		|	预约状态 1同意预约2拒绝预约
        Map<String, String> map = new HashMap<>(16);
        map.put(Constant.ID, mBean.getId());
        map.put("returnContent", comment);
        map.put("state", state);
        String query = new JSONObject(map).toString();
        OkGo.<BaseBean<String>>post(NetConstant.Order.RESPONSE_ORDER)
                .params(Constant.QUERY, query)
                .execute(new DialogCallBack<BaseBean<String>>(mActivity, "处理中...") {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        setResult(RESULT_OK);
                        finishActivity();
                    }
                });
    }
}

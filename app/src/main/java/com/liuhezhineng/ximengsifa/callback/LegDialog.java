package com.liuhezhineng.ximengsifa.callback;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.callback.bean.MyLegaBean;

import static com.liuhezhineng.ximengsifa.callback.bean.MyLegaBean.*;

/**
 * Created by lishangnan on 2019/11/15.
 */

public class LegDialog extends Dialog {
    BodyBean.BusinessDataBean data;
    TextView tv_apply_officeId;
    TextView tv_title;
    TextView tv_symbol;
    TextView tv_apply_conton;
    TextView tv_down_conton;
    TextView tv_data;

    public LegDialog(@NonNull Context context, BodyBean.BusinessDataBean businessData, int themeResId) {
        super(context,themeResId);
        this.data=businessData;
    }
    public LegDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_leg_updata);
        tv_apply_officeId=findViewById(R.id.tv_apply_officeId);
        tv_title=findViewById(R.id.tv_title);
        tv_symbol=findViewById(R.id.tv_symbol);
        tv_data=findViewById(R.id.tv_data);
        tv_apply_conton=findViewById(R.id.tv_apply_conton);
        tv_down_conton=findViewById(R.id.tv_down_conton);
        tv_data=findViewById(R.id.tv_data);
        tv_title.setText(data.getOaReconsiderationCorrection().getTitle());
        tv_symbol.setText(data.getOaReconsiderationCorrection().getSymbol());
        tv_apply_officeId.setText(data.getReconsiderationOfficeId().getName());
//        tv_data.setText(data.getOaReconsiderationCorrection().getCorrectionDate());
        tv_apply_conton.setText("      "+data.getApplyName()+"对"+data.getRespondentName()+data.getIncidentDate()+
        "做出的"+data.getCaseTitle()+",于"+data.getApplyDate()+"向"+data.getReconsiderationOfficeId().getName()
        +"申请行政复议"+"经依法审查，本机关认为：该行政复议申请"+data.getOaReconsiderationCorrection().getSymbol()
        +",需要补正一下材料："+data.getOaReconsiderationCorrection().getRequiredMaterials());
        tv_down_conton.setText("      "+"请你"+data.getAgentName()+"接到本通知书后，十日内提交补正材料。根据《中华人民共和国行政复议议法实施条例》" +
                "第二十九条的规定，无正当理由逾期不补正的，视为放弃行政复议申请。补正申请材料所用的时间不计入行政复议的审理期限");


        tv_data.setText(data.getCreateDate());

    }

}

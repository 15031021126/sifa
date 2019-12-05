package com.liuhezhineng.ximengsifa.callback;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;

import static com.liuhezhineng.ximengsifa.callback.bean.MyLegaBean.BodyBean;

/**
 * Created by lishangnan on 2019/11/15.
 */

public class NewLegDialog extends Dialog {
    BodyBean.BusinessDataBean data;
    TextView tv_apply_officeId;
    TextView tv_title;
    TextView tv_symbol;
    TextView tv_apply_conton;
    TextView tv_down_conton;
    TextView tv_data;

    public NewLegDialog(@NonNull Context context) {
        super(context);
    }

    public NewLegDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_leg_updata);


    }

}

package com.liuhezhineng.ximengsifa.video;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.util.TimeUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.video.AnalyzeHistoryBean;
import com.liuhezhineng.ximengsifa.bean.video.ResultBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class AnalyzeResultTableActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.linear_layout)
    LinearLayout mLinearLayout;
    @BindView(R.id.tv_start_date)
    TextView mTvStartDate;
    @BindView(R.id.tv_end_date)
    TextView mTvEndDate;
    @BindView(R.id.tv_param)
    TextView mTvParam;

    LineChart mLineChart;

    String beginDate;
    String endDate;
    String idCard = "376126199307221236";
    String videoParam = "攻击性";

    boolean isBeginDate = true;

    public static void actionStart(Context context, String idCard) {
        Intent intent = new Intent(context, AnalyzeResultTableActivity.class);
        intent.putExtra("id_card", idCard);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_analyze_result_table;
    }

    @Override
    protected void initView() {
        super.initView();

        initTopBar(mTopBar, "人物历史分析图表");

        mLineChart = new LineChart(this);
        mLineChart.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mLinearLayout.addView(mLineChart);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mLineChart.getAxisRight().setEnabled(false);
    }

    @Override
    protected void initData() {
        super.initData();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        endDate = simpleDateFormat.format(date);
        mTvEndDate.setText(endDate);
        beginDate = getMonthAgo(date);
        mTvStartDate.setText(beginDate);

        idCard = getIntent().getStringExtra("id_card");

        getChartData();
    }

    private void getChartData() {
        Map<String, String> map = new HashMap<>(16);
        map.put("idCard", idCard);
        map.put("beginDate", beginDate);
        map.put("endDate", endDate);
        OkGo.<BaseBean<AnalyzeHistoryBean>>post(NetConstant.Video.GET_ANALYZE_HISTORY)
                .params(Constant.QUERY, new Gson().toJson(map))
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new DialogCallBack<BaseBean<AnalyzeHistoryBean>>(AnalyzeResultTableActivity.this, "数据加载中...") {
                    @Override
                    public void onSuccess(Response<BaseBean<AnalyzeHistoryBean>> response) {
                        AnalyzeHistoryBean analyzeHistoryBean = response.body().getBody();

                        switch (videoParam) {
                            case "攻击性":
                                setData(analyzeHistoryBean.getAggressivity());
                                break;
                            case "焦虑":
                                setData(analyzeHistoryBean.getPresure());
                                break;
                            case "平衡":
                                setData(analyzeHistoryBean.getBalance());
                                break;
                            case "活力":
                                setData(analyzeHistoryBean.getEnergy());
                                break;
                            case "抑郁":
                                setData(analyzeHistoryBean.getDepressed());
                                break;
                            case "压力":
                                setData(analyzeHistoryBean.getPresure());
                                break;
                            case "可疑":
                                setData(analyzeHistoryBean.getSuspicious());
                                break;
                            case "自信":
                                setData(analyzeHistoryBean.getSelfConfidence());
                                break;
                            case "自我调节":
                                setData(analyzeHistoryBean.getSelfControl());
                                break;
                            case "神经质":
                                setData(analyzeHistoryBean.getNervousness());
                                break;
                        }

                    }
                });
    }

    private void setData(List<ResultBean> balanceList) {
        if (balanceList != null && balanceList.size() > 0) {
            List<Entry> balanceEntry = new ArrayList<Entry>();
            final List<String> lDate = new ArrayList<>();
            float x = 0f;
            for (ResultBean bean : balanceList) {
                lDate.add(bean.getAnalysisDate());
                balanceEntry.add(new Entry(x, Float.valueOf(bean.getAverageValue())));
                x++;
            }
            mLineChart.getDescription().setEnabled(false);
            XAxis xAxis = mLineChart.getXAxis();
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return lDate.get((int) value);
                }
            });
            LineDataSet balanceSet = new LineDataSet(balanceEntry, videoParam);
            mLineChart.setData(new LineData(balanceSet));
            mLineChart.invalidate();
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        initBirthdayPicker(new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (isBeginDate) {
                    beginDate = getTime(date);
                    mTvStartDate.setText(beginDate);
                } else {
                    endDate = getTime(date);
                    mTvEndDate.setText(endDate);
                }

                getChartData();
            }
        });

        initVideoParamPickerView(new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                videoParam = videoParamList.get(options1);
                mTvParam.setText(videoParam);
                getChartData();
            }
        });
    }

    @OnClick({R.id.tv_start_date, R.id.tv_end_date, R.id.tv_param})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start_date:
                isBeginDate = true;
                birthdayPickerView.show();
                break;
            case R.id.tv_end_date:
                isBeginDate = false;
                birthdayPickerView.show();
                break;
            case R.id.tv_param:
                showPickerView(videoParamPicker);
        }
    }

    public static String getMonthAgo(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        String monthAgo = simpleDateFormat.format(calendar.getTime());
        return monthAgo;
    }

}

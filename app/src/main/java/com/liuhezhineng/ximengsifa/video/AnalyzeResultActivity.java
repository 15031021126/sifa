package com.liuhezhineng.ximengsifa.video;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.bin.david.form.data.format.bg.ICellBackgroundFormat;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.google.gson.Gson;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.video.ResultBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class AnalyzeResultActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.tv_person_info)
    TextView mTvPersonInfo;
    @BindView(R.id.radar_chart)
    RadarChart mRadarChart;
    @BindView(R.id.smart_table)
    SmartTable mSmartTable;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    String analysisResult = "该分析人员";

    ArrayList<ResultBean> mList;
    String personInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_analyze_result;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(mTopBar, "分析详情");

        mSmartTable.getConfig()
                .setShowXSequence(false)
                .setShowYSequence(false);
        ICellBackgroundFormat<CellInfo> backgroundFormat = new BaseCellBackgroundFormat<CellInfo>() {
            @Override
            public int getBackGroundColor(CellInfo cellInfo) {
                if ("平均值".equals(cellInfo.column.getColumnName())) {
                    float value = Float.valueOf(cellInfo.value);
                    analysisResult(cellInfo.position, value);
                    mTvDesc.setText(analysisResult.substring(0, analysisResult.length() -1) + "。");
                    if ((0 == cellInfo.position && (value > 50 || value < 20))
                            || (1 == cellInfo.position && (value > 40 || value < 15))
                            || (2 == cellInfo.position && (value > 100 || value < 50))
                            || (3 == cellInfo.position && (value > 40 || value < 10))
                            || (4 == cellInfo.position && (value > 25 || value < 10))
                            || (5 == cellInfo.position && (value > 40 || value < 20))
                            || (6 == cellInfo.position && (value > 50 || value < 20))
                            || (7 == cellInfo.position && (value > 100 || value < 40))
                            || (8 == cellInfo.position && (value > 100 || value < 50))
                            || (9 == cellInfo.position && (value > 50 || value < 10))
                            ) {

                        return ContextCompat.getColor(AnalyzeResultActivity.this, android.R.color.holo_red_dark);
                    }
                }
                return TableConfig.INVALID_COLOR;
            }

        };
        mSmartTable.getConfig().setContentBackgroundFormat(backgroundFormat);
    }

    private void analysisResult(int pos, float value) {
        switch (pos) {
            case 0:
                if (value > 50) {
                    analysisResult += "极具攻击性，";
                }
                if (value < 20) {
                    analysisResult += "十分懦弱，";
                }
                break;
            case 1:
                if (value > 40) {
                    analysisResult += "十分焦虑，";
                }
                if (value < 15) {
                    analysisResult += "十分冷漠，";
                }
                break;
            case 2:
                if (value > 100) {
                    analysisResult += "嫉妒不平衡，";
                }
                if (value < 50) {
                    analysisResult += "十分漠然";
                }
                break;
            case 3:
                if (value > 50) {
                    analysisResult += "极具活力，";
                }
                if (value < 20) {
                    analysisResult += "十分萎靡，";
                }
                break;
            case 4:
                if (value > 50) {
                    analysisResult += "严重抑郁，";
                }
                if (value < 20) {
                    analysisResult += "过于自卑，";
                }
                break;
            case 5:
                if (value > 40) {
                    analysisResult += "压力很大，";
                }
                if (value < 10) {
                    analysisResult += "没有压力，";
                }
                break;
            case 6:
                if (value > 50) {
                    analysisResult += "非常可疑，";
                }
                if (value < 20) {
                    analysisResult += "没有存在感，";
                }
                break;
            case 7:
                if (value > 100) {
                    analysisResult += "过度自信，";
                }
                if (value < 40) {
                    analysisResult += "极不自信，";
                }
                break;
            case 8:
                if (value > 100) {
                    analysisResult += "自我调节能力强，";
                }
                if (value < 50) {
                    analysisResult += "自我调节能力弱，";
                }
                break;
            case 9:
                if (value > 50) {
                    analysisResult += "具有神经质，";
                }
                if (value < 10) {
                    analysisResult += "非常迟钝，";
                }
                break;
        }
    }

    @Override
    protected void initData() {
        super.initData();
        chartBanding();

        personInfo = getIntent().getStringExtra("person_info");
        mTvPersonInfo.setText(personInfo);

        Map<String, String> map = new HashMap<>(16);
        map.put("idCard", "376126199307221236");
        map.put("analysisDate", "2018-07-23");
        OkGo.<BaseBean<ArrayList<ResultBean>>>post(NetConstant.Video.GET_ANALYSIS_RESULT)
                .params(Constant.QUERY, new Gson().toJson(map))
                .execute(new JsonCallback<BaseBean<ArrayList<ResultBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<ArrayList<ResultBean>>> response) {
                        mList = response.body().getBody();
                        for (ResultBean bean : mList) {
                            switch (bean.getType()) {
                                case "1":
                                    bean.setName("(20~50)\n攻击性");
                                    break;
                                case "2":
                                    bean.setName("(15~40)\n焦虑");
                                    break;
                                case "3":
                                    bean.setName("(50~100)\n平衡");
                                    break;
                                case "4":
                                    bean.setName("(10~40)\n活力");
                                    break;
                                case "5":
                                    bean.setName("(10~25)\n抑郁");
                                    break;
                                case "6":
                                    bean.setName("(20~40)\n压力");
                                    break;
                                case "7":
                                    bean.setName("(20~50)\n可疑");
                                    break;
                                case "8":
                                    bean.setName("(40~100)\n自信");
                                    break;
                                case "9":
                                    bean.setName("(50~100)\n自我调节");
                                    break;
                                case "10":
                                    bean.setName("(10~50)\n神经质");
                                    break;
                            }
                        }
                        setData();
                        mSmartTable.setData(mList);
                    }
                });
    }

    private void chartBanding() {
        // 设置雷达图的描述，在底部
        mRadarChart.getDescription().setEnabled(false);
        // 绘制线条宽度，中心向外辐射
        mRadarChart.setWebLineWidth(1f);
        mRadarChart.setWebColor(Color.LTGRAY);
        // 换装线宽度
        mRadarChart.setWebLineWidthInner(1f);
        mRadarChart.setWebColorInner(Color.LTGRAY);
        mRadarChart.setWebAlpha(100);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        mRadarChart.setLayoutParams(layoutParams);
        mRadarChart.setRotationEnabled(false);

//        setData();

        mRadarChart.animateXY(//设置绘制动画
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = mRadarChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setAxisMinimum(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"攻击性", "焦虑", "平衡", "活力", "抑郁",
                    "压力", "可疑", "自信", "自我调解", "神经质"};//设置雷达图普

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(R.color.blue);

        YAxis yAxis = mRadarChart.getYAxis();
        yAxis.setLabelCount(10, false);
        yAxis.setTextSize(12f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setDrawLabels(false);

        Legend l = mRadarChart.getLegend();//得到图例
        l.setEnabled(false);//隐藏图例
    }

    private void setData() {//设置数据
        float mult = 100;
        float min = 20;
        //项目数
        int cnt = 10;

        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the mRadarChart.
        for (int i = 0; i < cnt; i++) {
//            float val1 = (float) (Math.random() * mult) + min;
//            entries1.add(new RadarEntry(val1));
            Float value = Float.valueOf(mList.get(i).getAverageValue());
            entries1.add(new RadarEntry(value));
        }
        //set1为一级图层
        RadarDataSet set1 = new RadarDataSet(entries1, "性情分析");
        set1.setColor(Color.BLUE);
        set1.setFillColor(Color.GREEN);//一级图层填充色
        set1.setDrawFilled(false);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);

        RadarData data = new RadarData(sets);
//        data.setValueTypeface(mTfLight);
        data.setValueTextSize(8f);
        data.setDrawValues(true);
        data.setValueTextColor(Color.BLACK);
        mRadarChart.setData(data);
        mRadarChart.invalidate();
    }
}

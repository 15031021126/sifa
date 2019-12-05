package com.liuhezhineng.ximengsifa.module;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.StatisticalAdapter;
import com.liuhezhineng.ximengsifa.base.BaseFragment;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.StatisticalBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticalAnalysisFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author AIqinfeng
 */
public class StatisticalAnalysisFragment extends BaseFragment implements
	OnChartValueSelectedListener {

	private static final String TYPE = "type";
	private static final String APPLY_BEGIN_DATE = "applyBeginDate";
	private static final String APPLY_END_DATE = "applyEndDate";

	@BindView(R.id.pic_chart)
	PieChart mChart;
	@BindView(R.id.rv_statistical)
	RecyclerView mRvStatistical;

	private String type;
	private String applyBeginDate;
	private String applyEndDate;
	private ArrayList<StatisticalBean> mList;
	private StatisticalAdapter mAdapter;

	public StatisticalAnalysisFragment() {
		// Required empty public constructor
	}

	public static StatisticalAnalysisFragment newInstance(String type, String applyBeginDate,
		String applyEndDate) {
		StatisticalAnalysisFragment fragment = new StatisticalAnalysisFragment();
		Bundle args = new Bundle();
		args.putString(TYPE, type);
		args.putString(APPLY_BEGIN_DATE, applyBeginDate);
		args.putString(APPLY_END_DATE, applyEndDate);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			type = getArguments().getString(TYPE);
			applyBeginDate = getArguments().getString(APPLY_BEGIN_DATE);
			applyEndDate = getArguments().getString(APPLY_END_DATE);
		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_statistical_analysis;
	}

	@Override
	protected void initView() {
		super.initView();

		mList = new ArrayList<>();
		mAdapter = new StatisticalAdapter(mActivity, mList);
		mRvStatistical.setAdapter(mAdapter);
		mRvStatistical.setLayoutManager(new LinearLayoutManager(mActivity));
		mRvStatistical.addItemDecoration(
			new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));

		initChart();
	}

	@Override
	protected void initData() {
		super.initData();
		getChartAndList();
	}

	private void getChartAndList() {
		Map<String, String> map = new HashMap<>(16);
		map.put(TYPE, type);
		map.put(APPLY_BEGIN_DATE, applyBeginDate);
		map.put(APPLY_END_DATE, applyEndDate);
		String queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<List<StatisticalBean>>>post(NetConstant.STATISTICAL_ANALYSIS)
			.params(NetConstant.QUERY, queryStr)
			.cacheTime(1000 * 60)
			.cacheKey(queryStr)
			.cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
			.execute(new DialogCallBack<BaseBean<List<StatisticalBean>>>(mActivity, "正在加载统计图...") {
				@Override
				public void onSuccess(Response<BaseBean<List<StatisticalBean>>> response) {
					mList = new ArrayList<>();
					mList.addAll(response.body().getBody());
					mAdapter.initData(response.body().getBody());
					setData(mList.size(), 100);
				}
			});
	}

	private void setData(int count, float range) {
		float mult = range;
		ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
		// add a lot of colors
		ArrayList<Integer> colors = new ArrayList<Integer>();
		// NOTE: The order of the entries when being added to the entries array determines their position around the center of
		// the chart.
		int caseCount = 0;
		for (int i = 0; i < count; i++) {
			colors.add(Color.parseColor(mList.get(i).getColor()));
			caseCount += Integer.parseInt(mList.get(i).getCount());
			entries.add(new PieEntry((float) Integer.parseInt(mList.get(i).getCount()),
				mList.get(i).getName()));
		}
		mChart.setCenterText("案件总数\n" + caseCount);
		PieDataSet dataSet = new PieDataSet(entries, "Statistical Analysis");
		dataSet.setSliceSpace(3f);
		dataSet.setSelectionShift(5f);
		dataSet.setColors(colors);
//		dataSet.setSelectionShift(0f);
		dataSet.setValueLinePart1OffsetPercentage(80.f);
		dataSet.setValueLinePart1Length(0.2f);
		dataSet.setValueLinePart2Length(0.4f);
		//dataSet.setUsingSliceColorAsValueLineColor(true);
		//dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
		dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
		PieData data = new PieData(dataSet);
		data.setValueFormatter(new IValueFormatter() {
			@Override
			public String getFormattedValue(float value, Entry entry, int dataSetIndex,
				ViewPortHandler viewPortHandler) {
				String percent = new DecimalFormat("###,###,##0.0").format(value);
				if (Double.parseDouble(percent) <= 2) {
					return "";
				}
				return ((PieEntry) entry).getLabel() +
					" " + percent + " %";
			}
		});
		data.setValueTextSize(11f);
		data.setValueTextColor(Color.BLACK);
//		data.setValueTypeface(tf);
		mChart.setData(data);
		// undo all highlights
		mChart.highlightValues(null);
		mChart.invalidate();
	}

	private void initChart() {
		mChart.setDrawEntryLabels(false);
		mChart.setUsePercentValues(true);
		mChart.getDescription().setEnabled(false);

		mChart.setDragDecelerationFrictionCoef(0.95f);

//		mChart.setCenterText(generateCenterSpannableText());

		mChart.setExtraOffsets(40.f, 0.f, 40.f, 0.f);

		mChart.setDrawHoleEnabled(true);
		mChart.setHoleColor(Color.WHITE);

		mChart.setTransparentCircleColor(Color.WHITE);
		mChart.setTransparentCircleAlpha(110);

		mChart.setHoleRadius(58f);
		mChart.setTransparentCircleRadius(61f);

		mChart.setDrawCenterText(true);

		mChart.setRotationAngle(0);
		// enable rotation of the chart by touch
		mChart.setRotationEnabled(true);
		mChart.setHighlightPerTapEnabled(true);

		// mChart.setUnit(" €");
		// mChart.setDrawUnitsInChart(true);

		// add a selection listener
		mChart.setOnChartValueSelectedListener(this);

//		setData(14, 100);

//		mChart.animateY(1400, Easing.EaseInOutQuad);
		// mChart.spin(2000, 0, 360);

		Legend l = mChart.getLegend();
		l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
		l.setOrientation(Legend.LegendOrientation.VERTICAL);
		l.setDrawInside(false);
		l.setEnabled(false);
	}

	@Override
	public void onValueSelected(Entry e, Highlight h) {

	}

	@Override
	public void onNothingSelected() {

	}

	public void filterData(String date, String endDate) {
		applyBeginDate = date;
		applyEndDate = endDate;
		getChartAndList();
	}
}

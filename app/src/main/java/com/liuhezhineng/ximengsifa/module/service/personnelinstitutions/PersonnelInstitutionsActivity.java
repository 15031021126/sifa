package com.liuhezhineng.ximengsifa.module.service.personnelinstitutions;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.dto.vo.ServerAppVo;
import com.liuhezhineng.ximengsifa.bean.personal.DictList;
import com.liuhezhineng.ximengsifa.bean.personal.PersonalInstitutionsBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.widget.AndroidSevenPopupWindow;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @description 人员机构查询页面
 */
public class PersonnelInstitutionsActivity extends BaseActivity {

	public ServerAppVo mServerAppVo;
	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.sliding_tab_layout)
	SlidingTabLayout mSlidingTabLayout;
	@BindView(R.id.view_pager)
	ViewPager mViewPager;

	List<ServerAppVo> mList;
	String isMongolian = "";
	String isAidLawyerMongolian = "";
	PopupWindow popupWindow;
	private String[] mTitles;
	private ArrayList<Fragment> mFragments = new ArrayList<>();
	private Area county = new Area();
	private Area town = new Area();

	public static void actionStart(Context context,
		ServerAppVo services) {
		Intent intent = new Intent(context, PersonnelInstitutionsActivity.class);
		intent.putExtra(Constant.SERVICE_APP_VO, services);
		context.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_institutions;
	}

	@Override
	protected void initView() {
		super.initView();
		mServerAppVo = (ServerAppVo) getIntent().getSerializableExtra(Constant.SERVICE_APP_VO);
		initTopBar(mTopBar, mServerAppVo.getName());
		mList = new ArrayList<>();
	}

	@Override
	protected void initData() {
		super.initData();
		getPersonalCategory();
	}

	@Override
	protected void setListener() {
		super.setListener();
		initFilterWindow();
		Button btnFilter = mTopBar.addRightTextButton("筛选", R.id.btn_ok);
		btnFilter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				} else {
					popupWindow.showAsDropDown(mTopBar, 0, 0, Gravity.CENTER_HORIZONTAL);
				}
			}
		});
	}

	private void getPersonalCategory() {
		Map<String, String> map = new HashMap<>(16);
		map.put("serverId", mServerAppVo.getId());
		String queryStr = new JSONObject(map).toString();
		OkGo.<BaseBean<PersonalInstitutionsBean>>post(NetConstant.GET_PERSON_INSTITUTIONS_CATEGORY)
			.params(NetConstant.QUERY, queryStr)
			.execute(new DialogCallBack<BaseBean<PersonalInstitutionsBean>>(mActivity) {
				@Override
				public void onSuccess(Response<BaseBean<PersonalInstitutionsBean>> response) {
					PersonalInstitutionsBean body = response.body().getBody();
					List<DictList> list = body.getDictList();

					if (list.size() == 1) {
						mSlidingTabLayout.setVisibility(View.GONE);
					}

					mTitles = new String[list.size()];
					for (int i = 0; i < list.size(); i++) {
						mFragments
							.add(PersonnelInstitutionsFragment.newInstance(list.get(i)));
						mTitles[i] = list.get(i).getLabel();
					}

					mViewPager.setAdapter(
						new PersonnelInstitutionsActivity.MyPagerAdapter(
							getSupportFragmentManager()));
					mSlidingTabLayout.setViewPager(mViewPager, mTitles);
				}
			});
	}

	private void initFilterWindow() {
		isMongolian = "";
		View contentView = LayoutInflater.from(mActivity)
			.inflate(R.layout.person_ins_filter_window, null);
		popupWindow = new AndroidSevenPopupWindow(contentView,
			LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ColorDrawable cd = new ColorDrawable();
		popupWindow.setBackgroundDrawable(cd);
		popupWindow.setFocusable(true);
		contentView.findViewById(R.id.view_mask).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		final EditText etName = contentView.findViewById(R.id.et_name);
		final TextView tvCounty = contentView.findViewById(R.id.tv_county);
		final TextView tvTown = contentView.findViewById(R.id.tv_town);
		tvCounty.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showPickerView(countyPickerView);
			}
		});
		tvTown.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(tvCounty.getText().toString().trim())) {
					ToastUtils.showLong("请先选择旗县");
				} else {
					showPickerView(townPickerView);
				}
			}
		});
		if ("人员查询".equals(mServerAppVo.getLink()) && "法律援助".equals(mServerAppVo.getPname())) {
			contentView.findViewById(R.id.ll_aid_layer_mongolian).setVisibility(View.VISIBLE);
		}
		if ("机构查询".equals(mServerAppVo.getLink())) {
			contentView.findViewById(R.id.ll_mongolian).setVisibility(View.GONE);
		}
		RadioButton rbNo = contentView.findViewById(R.id.rb_no);
		RadioButton rbYes = contentView.findViewById(R.id.rb_yes);
		final RadioButton rbAll = contentView.findViewById(R.id.rb_all);
		rbAll.setChecked(true);
		rbYes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isMongolian = "1";
			}
		});
		rbNo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isMongolian = "0";
			}
		});
		rbAll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isMongolian = "";
			}
		});
		RadioButton rbAidLawyerNo = contentView.findViewById(R.id.rb_aid_layer_no);
		RadioButton rbAidLawyerYes = contentView.findViewById(R.id.rb_aid_layer_yes);
		final RadioButton rbAidLawyerAll = contentView.findViewById(R.id.rb_aid_layer_all);
		rbAidLawyerAll.setChecked(true);
		rbAidLawyerNo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isAidLawyerMongolian = "0";
			}
		});
		rbAidLawyerYes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isAidLawyerMongolian = "1";
			}
		});
		rbAidLawyerAll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isAidLawyerMongolian = "";
			}
		});
		initCountyPickerView(new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				Area bean = countyList.get(options1);
				county.setId(bean.getId());
				county.setName(bean.getName());
				town = new Area();
				tvCounty.setText(countyList.get(options1).getName());
				tvTown.setText("");
				initTownPickerView(new OnOptionsSelectListener() {
					@Override
					public void onOptionsSelect(int options1, int options2, int options3, View v) {
						if (options1 < 0) {
							ToastUtils.showLong("请等待数据加载完成");
							return;
						}
						Area townBean = townList.get(options1);
						town.setId(townBean.getId());
						town.setName(townBean.getName());
						tvTown.setText(town.getName());
					}
				}, countyList.get(options1).getId());
			}
		});
		contentView.findViewById(R.id.tv_ok).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
				String name = etName.getText().toString().trim();
				((PersonnelInstitutionsFragment) mFragments.get(mSlidingTabLayout.getCurrentTab()))
					.filterData(name, county.getId(), town.getId(), isMongolian,
						isAidLawyerMongolian);
			}
		});
		contentView.findViewById(R.id.tv_reset).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				etName.setText("");
				tvCounty.setText("");
				tvTown.setText("");
				isMongolian = "";
				county = new Area();
				town = new Area();
				rbAll.setChecked(true);
				rbAidLawyerAll.setChecked(true);
			}
		});
	}

	private class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mTitles[position];
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}
	}

}

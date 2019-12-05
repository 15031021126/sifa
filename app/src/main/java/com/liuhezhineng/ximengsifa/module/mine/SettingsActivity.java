package com.liuhezhineng.ximengsifa.module.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.BuildConfig;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.UserHelp2Activity;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.module.mine.login.LoginActivity;
import com.liuhezhineng.ximengsifa.utils.UpdateUtils;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.db.CacheManager;
import com.lzy.okgo.model.HttpHeaders;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView.Section;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * @author AIqinfeng
 * @description 我 -> 设置
 */

public class SettingsActivity extends BaseActivity implements OnClickListener {

	private final String clearCacheStr = "清除缓存";
	private final String checkUpdateStr = "检查更新";
	private final String feedbackStr = "问题反馈";
	private final String userHelpStr = "用户手册";

	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.qmui_settings_list)
	QMUIGroupListView mQmuiSettingsList;
	@BindView(R.id.tv_logout)
	TextView mTvLogout;
	@BindView(R.id.ll_logout)
	LinearLayout mLLLayout;

	public static void actionStart(Context context) {
		Intent intent = new Intent(context, SettingsActivity.class);
		context.startActivity(intent);
	}

	@Override
	public void onClick(View view) {
		if (view instanceof QMUICommonListItemView) {
			String string = String.valueOf(((QMUICommonListItemView) view).getText());
			actionClickList(string);
		}
	}

	private void actionClickList(String content) {
		switch (content) {
            case userHelpStr:
                UserHelp2Activity.actionStart(mActivity);
                break;
			case feedbackStr:
				FeedbackActivity.actionStart(mActivity);
				break;
			case clearCacheStr:
				// TODO clear article image
				// clear article image
				// clear api cache
				boolean clearSuccess = CacheManager.getInstance().clear();
				if (clearSuccess) {
					ToastUtils.showShort(getString(R.string.clear_cache_success));
				} else {
					ToastUtils.showShort(getString(R.string.clear_cache_failure));
				}
				break;
			case checkUpdateStr:
				UpdateUtils.update(mActivity, true);
				break;
			default:
				break;
		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_settings;
	}

	@Override
	protected void initView() {
		super.initView();
		initTopBar(mTopBar, R.string.settings);

		createListItemView(clearCacheStr, feedbackStr, userHelpStr, checkUpdateStr);

		if (UserHelper.isIsLogin()) {
			mLLLayout.setVisibility(View.VISIBLE);
		} else {
			mLLLayout.setVisibility(View.GONE);
		}
	}

	@Override
	@OnClick({R.id.tv_logout})
	public void onViewClicked(View view) {
		super.onViewClicked(view);
		if (view.getId() == R.id.tv_logout) {
			logout();
		}
	}

    /**
     * 退出登录
     */
    protected void logout() {
        // 1、关闭所有的页面
        for (Activity activity : mActivityArrayList) {
            if (activity != null) {
                activity.finish();
            }
        }
        // 2、清除内存中数据
        UserHelper.clear();
        // 3、清除推送别名
        JPushInterface.deleteAlias(mActivity, 0);
        // 4、清除 token
        OkGo.getInstance().addCommonHeaders(new HttpHeaders(NetConstant.TOKEN, ""));
        // 5、清除缓存
        CacheManager.getInstance().clear();
        // 6、跳转到登录页
        LoginActivity.actionStart(this);
        // 7、清除所有的推送
        JPushInterface.clearAllNotifications(this);
    }

	private void createListItemView(String... strings) {
		Section section = QMUIGroupListView.newSection(mActivity);
		for (String str : strings) {
			QMUICommonListItemView itemView = mQmuiSettingsList.createItemView(str);
			if (str.equals(checkUpdateStr)) {
				itemView.setDetailText(BuildConfig.VERSION_NAME);
			}
			itemView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
			section.addItemView(itemView, this);
		}
		section.addTo(mQmuiSettingsList);
	}
}

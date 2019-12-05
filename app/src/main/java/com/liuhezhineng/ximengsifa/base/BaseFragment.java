package com.liuhezhineng.ximengsifa.base;

import  android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author AIqinfeng
 * @date 2018/4/15
 * @description fragment 基类
 */

public abstract class BaseFragment<T> extends Fragment {

	public static final String TAG = "okgo";
	protected BaseActivity mActivity;
	protected DialogCallBack<BaseBean<T>> mDialogCallBack;
	protected QMUITopBar mTopBar;
	/**
	 * 用来判断 fragment 中 butterknife  是否解绑，
	 * 但是对于 ViewPager + 多个 fragemnt  会有问题，暂时没用到
	 */
	protected boolean isUnbind;
	/**
	 * Fragment当前状态是否可见
	 */
	protected boolean isVisible;
	private Unbinder mUnbinder;

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if (getUserVisibleHint()) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;
			onInvisible();
		}
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mActivity = (BaseActivity) context;
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
		@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutId(), container, false);
		mUnbinder = ButterKnife.bind(this, view);

		initDialogCallback();
		initView();
		initData();
		setListener();
		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
//		if (mUnbinder != null) {
//			mUnbinder.unbind();
//			isUnbind = true;
//		}
	}

	/**
	 * get current fragment layout id
	 *
	 * @return layout id
	 */
	protected abstract @LayoutRes
	int getLayoutId();

	private void initDialogCallback() {
		mDialogCallBack = new DialogCallBack<BaseBean<T>>(
			mActivity) {
			@Override
			public void onSuccess(Response<BaseBean<T>> response) {
				T body = response.body().getBody();
				callbackSuccess(body);
			}
		};
	}

	protected void initView() {
	}

	protected void initData() {
	}

	protected void setListener() {
	}

	protected void callbackSuccess(T body) {
	}

	/**
	 * 可见时的回调方法
	 */
	protected void onVisible() {
	}

	/**
	 * 不可见时的回调方法
	 */
	protected void onInvisible() {
	}

	public void request(Type type, String url) {
//		OkGo.<type>post(url)
//			.execute(new JsonCallback<>(type) {
//				@Override
//				public void onSuccess(Response<T> response) {
//					refreshUI();
//				}
//			});
	}

	protected void refreshUI() {

	}
}

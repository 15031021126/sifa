package com.liuhezhineng.ximengsifa.interfaces;

import com.liuhezhineng.ximengsifa.bean.TypeBean;

/**
 * @author AIqinfeng
 * @description 综合查询筛选点击事件监听：办理渠道，案件类别，案件进度，严重等级。
 */
public interface OnFilterClickListener {

	/**
	 * 相关点击事件 办理渠道 -> 常规办理
	 * @param bean 点击的控件对应的对象
	 * @param flag 点击的哪一个模块
	 */
	void filterClick(TypeBean bean, int flag);
}
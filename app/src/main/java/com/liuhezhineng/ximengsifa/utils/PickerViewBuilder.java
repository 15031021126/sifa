package com.liuhezhineng.ximengsifa.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Window;
import android.widget.FrameLayout;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

/**
 * @author AIqinfeng
 * @date 2018/5/16
 */

public class PickerViewBuilder extends OptionsPickerBuilder {

	public PickerViewBuilder(Context context,
		OnOptionsSelectListener listener) {
		super(context, listener);
		this.setContentTextSize(20)
			.setDividerColor(Color.LTGRAY)
			.setSelectOptions(0)
			.isDialog(true)
			.setBgColor(Color.WHITE)
			.setTitleBgColor(0xfff5f5f5)
			.setCancelColor(Color.parseColor("#77b6fa"))
			.setSubmitColor(Color.parseColor("#77b6fa"))
			.setTextColorCenter(Color.BLACK)
			.isRestoreItem(true)
			.isCenterLabel(false);

		setPickerDialogStyle(this.build());
	}

	private void setPickerDialogStyle(OptionsPickerView pickerView) {
		Dialog mDialog = pickerView.getDialog();
		if (mDialog != null) {

			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				100,
				Gravity.BOTTOM);

			params.leftMargin = 0;
			params.rightMargin = 0;
			pickerView.getDialogContainerLayout().setLayoutParams(params);

			Window dialogWindow = mDialog.getWindow();
			if (dialogWindow != null) {
				dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
				dialogWindow.setWindowAnimations(
					com.bigkoo.pickerview.R.style.picker_view_slide_anim);
				dialogWindow.setGravity(Gravity.BOTTOM);
			}
		}
	}

}

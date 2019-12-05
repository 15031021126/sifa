package com.liuhezhineng.ximengsifa.interfaces;

import com.liuhezhineng.ximengsifa.bean.evaluate.BeEvaluateCommitInfoBean;
import java.util.List;

/**
 * @author AIqinfeng
 */
public interface OnPrescriptionListener {
	void setPrescription(List<BeEvaluateCommitInfoBean> list);
}
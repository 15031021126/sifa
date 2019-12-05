package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.form;

import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * @author AIqinfeng
 * @date 2018/6/8
 */

public class DossierFormBean extends MediationWorkflow implements Base {

	/**
	 "dossierTitle":"",   //卷宗标题
	 "dossierContent":"",   //卷宗内容
	 */

	private String dossierTitle;
	private String dossierContent;

	public String getDossierTitle() {
		return dossierTitle == null ? "" : dossierTitle;
	}

	public void setDossierTitle(String dossierTitle) {
		this.dossierTitle = dossierTitle;
	}

	public String getDossierContent() {
		return dossierContent == null ? "" : dossierContent;
	}

	public void setDossierContent(String dossierContent) {
		this.dossierContent = dossierContent;
	}
}

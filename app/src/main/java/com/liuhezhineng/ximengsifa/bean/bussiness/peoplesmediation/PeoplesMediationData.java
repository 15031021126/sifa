package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation;

import com.liuhezhineng.ximengsifa.bean.advisory.CreateUser;

/**
 * @author AIqinfeng
 * @date 2018/6/6
 */

public class PeoplesMediationData extends PeoplesMediationBean {

	private String accuserSexDesc;
	private String accuserEthnicDesc;
	private String defendantSexDesc;
	private String defendantEthnicDesc;
    // TODO: 2019/1/9 createBy json
	private CreateUser createBy;

    public CreateUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(CreateUser createBy) {
        this.createBy = createBy;
    }

    public String getAccuserSexDesc() {
		return accuserSexDesc == null ? "" : accuserSexDesc;
	}

	public void setAccuserSexDesc(String accuserSexDesc) {
		this.accuserSexDesc = accuserSexDesc;
	}

	public String getAccuserEthnicDesc() {
		return accuserEthnicDesc == null ? "" : accuserEthnicDesc;
	}

	public void setAccuserEthnicDesc(String accuserEthnicDesc) {
		this.accuserEthnicDesc = accuserEthnicDesc;
	}

	public String getDefendantSexDesc() {
		return defendantSexDesc == null ? "" : defendantSexDesc;
	}

	public void setDefendantSexDesc(String defendantSexDesc) {
		this.defendantSexDesc = defendantSexDesc;
	}

	public String getDefendantEthnicDesc() {
		return defendantEthnicDesc == null ? "" : defendantEthnicDesc;
	}

	public void setDefendantEthnicDesc(String defendantEthnicDesc) {
		this.defendantEthnicDesc = defendantEthnicDesc;
	}
}

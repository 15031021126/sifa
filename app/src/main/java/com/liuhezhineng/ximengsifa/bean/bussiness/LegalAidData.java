/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean.bussiness;

import com.liuhezhineng.ximengsifa.bean.advisory.CreateUser;
import com.liuhezhineng.ximengsifa.bean.bussiness.legalaid.RequestLawHelpWorkFlow;

/**
 * Auto-generated: 2018-05-25 9:23:12
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class LegalAidData extends RequestLawHelpWorkFlow {

	private String createDate;
	private String updateDate;
	private String procInsId;
	private String year;
	private String yearNo;
	private String legalAidType;
	private String archiving;
	private String sexDesc;
	private String ethnicDesc;
	private String hasMongolDesc;
	private String proxyTypeDesc;
	private String caseTypeDesc;
	private String aidCategoryDesc;
	private String caseNatureDesc;
	private CreateUser createBy;

    public CreateUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(CreateUser createBy) {
        this.createBy = createBy;
    }

    public String getCaseNatureDesc() {
		return caseNatureDesc == null ? "" : caseNatureDesc;
	}

	public void setCaseNatureDesc(String caseNatureDesc) {
		this.caseNatureDesc = caseNatureDesc;
	}

	public String getAidCategoryDesc() {
		return aidCategoryDesc == null ? "" : aidCategoryDesc;
	}

	public void setAidCategoryDesc(String aidCategoryDesc) {
		this.aidCategoryDesc = aidCategoryDesc;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYearNo() {
		return yearNo;
	}

	public void setYearNo(String yearNo) {
		this.yearNo = yearNo;
	}

	public String getLegalAidType() {
		return legalAidType;
	}

	public void setLegalAidType(String legalAidType) {
		this.legalAidType = legalAidType;
	}

	public String getArchiving() {
		return archiving;
	}

	public void setArchiving(String archiving) {
		this.archiving = archiving;
	}

	public String getSexDesc() {
		return sexDesc;
	}

	public void setSexDesc(String sexDesc) {
		this.sexDesc = sexDesc;
	}

	public String getEthnicDesc() {
		return ethnicDesc;
	}

	public void setEthnicDesc(String ethnicDesc) {
		this.ethnicDesc = ethnicDesc;
	}

	public String getHasMongolDesc() {
		return hasMongolDesc;
	}

	public void setHasMongolDesc(String hasMongolDesc) {
		this.hasMongolDesc = hasMongolDesc;
	}

	public String getProxyTypeDesc() {
		return proxyTypeDesc;
	}

	public void setProxyTypeDesc(String proxyTypeDesc) {
		this.proxyTypeDesc = proxyTypeDesc;
	}

	public String getCaseTypeDesc() {
		return caseTypeDesc;
	}

	public void setCaseTypeDesc(String caseTypeDesc) {
		this.caseTypeDesc = caseTypeDesc;
	}

}
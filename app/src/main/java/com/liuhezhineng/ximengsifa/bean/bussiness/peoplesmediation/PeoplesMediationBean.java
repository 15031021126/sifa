package com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation;

import com.liuhezhineng.ximengsifa.bean.Area;
import com.liuhezhineng.ximengsifa.bean.bussiness.BaseBusinessBean;

/**
 * @author AIqinfeng
 * @date 2018/5/29
 * @description 人民调解申请表单对应实体类
 */

public class PeoplesMediationBean extends BaseBusinessBean {

    private String proxyName;
    private String proxyType;
    private String proxyIdCard;

	private String accuserName;
	private String accuserIdcard;
	private String accuserSex;
	private String accuserBirthday;
	private String accuserEthnic;
	private Area accuserCounty;
	private Area accuserTown;
	private String accuserOccupation;
	private String accuserDomicile;
	private String accuserAddress;
	private String accuserPostCode;
	private String accuserPhone;

	private String defendantName;
	private String defendantIdcard;
	private String defendantSex;
	private String defendantBirthday;
	private String defendantEthnic;
	private Area defendantCounty;
	private Area defendantTown;
	private String defendantOccupation;
	private String defendantDomicile;
	private String defendantAddress;
	private String defendantPostCode;
	private String defendantPhone;

	private String caseTitle;
	private String caseType;

	private String caseTypeDesc;
	private Area caseCounty;
	private String caseFile;
	private String caseSituation;
	private Area caseTown;

	private Office mediator;
	private Office peopleMediationCommittee;
    /**
     * 督办：是否允许超时办理
     */
    private String warningState;
    /**
     * 督办：超时办理时间
     */
    private String warningTime;
    private String warningStateDesc;
    private String warningTimeDesc;

    public String getWarningStateDesc() {
        return warningStateDesc == null ? "" : warningStateDesc;
    }

    public void setWarningStateDesc(String warningStateDesc) {
        this.warningStateDesc = warningStateDesc;
    }

    public String getWarningTimeDesc() {
        return warningTimeDesc == null ? "" : warningTimeDesc;
    }

    public void setWarningTimeDesc(String warningTimeDesc) {
        this.warningTimeDesc = warningTimeDesc;
    }

    public String getWarningState() {
        return warningState == null ? "" : warningState;
    }

    public void setWarningState(String warningState) {
        this.warningState = warningState;
    }

    public String getWarningTime() {
        return warningTime == null ? "" : warningTime;
    }

    public void setWarningTime(String warningTime) {
        this.warningTime = warningTime;
    }

    public String getProxyName() {
        return proxyName == null ? "" : proxyName;
    }

    public void setProxyName(String proxyName) {
        this.proxyName = proxyName;
    }

    public String getProxyType() {
        return proxyType == null ? "" : proxyType;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

    public String getProxyIdCard() {
        return proxyIdCard == null ? "" : proxyIdCard;
    }

    public void setProxyIdCard(String proxyIdCard) {
        this.proxyIdCard = proxyIdCard;
    }

    public String getCaseType() {
		return caseType == null ? "" : caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getCaseTypeDesc() {
		return caseTypeDesc == null ? "" : caseTypeDesc;
	}

	public void setCaseTypeDesc(String caseTypeDesc) {
		this.caseTypeDesc = caseTypeDesc;
	}

	public Area getCaseCounty() {
		if (caseCounty == null) {
			caseCounty = new Area();
		}
		return caseCounty;
	}

	public void setCaseCounty(Area caseCounty) {
		this.caseCounty = caseCounty;
	}

	public Area getCaseTown() {
		if (caseTown == null) {
			caseTown = new Area();
		}
		return caseTown;
	}

	public void setCaseTown(Area caseTown) {
		this.caseTown = caseTown;
	}

	public Office getMediator() {
		return mediator;
	}

	public void setMediator(Office mediator) {
		this.mediator = mediator;
	}

	public Office getPeopleMediationCommittee() {
		return peopleMediationCommittee;
	}

	public void setPeopleMediationCommittee(
		Office peopleMediationCommittee) {
		this.peopleMediationCommittee = peopleMediationCommittee;
	}

	public String getCaseFile() {
		return caseFile == null ? "" : caseFile;
	}

	public void setCaseFile(String caseFile) {
		this.caseFile = caseFile;
	}

	public String getAccuserName() {
		return accuserName == null ? "" : accuserName;
	}

	public void setAccuserName(String accuserName) {
		this.accuserName = accuserName;
	}

	public String getAccuserIdcard() {
		return accuserIdcard == null ? "" : accuserIdcard;
	}

	public void setAccuserIdcard(String accuserIdcard) {
		this.accuserIdcard = accuserIdcard;
	}

	public String getAccuserSex() {
		return accuserSex == null ? "" : accuserSex;
	}

	public void setAccuserSex(String accuserSex) {
		this.accuserSex = accuserSex;
	}

	public String getAccuserBirthday() {
		return accuserBirthday == null ? "" : accuserBirthday;
	}

	public void setAccuserBirthday(String accuserBirthday) {
		this.accuserBirthday = accuserBirthday;
	}

	public String getAccuserEthnic() {
		return accuserEthnic == null ? "" : accuserEthnic;
	}

	public void setAccuserEthnic(String accuserEthnic) {
		this.accuserEthnic = accuserEthnic;
	}

	public Area getAccuserCounty() {
		return accuserCounty;
	}

	public void setAccuserCounty(
		Area accuserCounty) {
		this.accuserCounty = accuserCounty;
	}

	public Area getAccuserTown() {
		return accuserTown;
	}

	public void setAccuserTown(Area accuserTown) {
		this.accuserTown = accuserTown;
	}

	public String getAccuserOccupation() {
		return accuserOccupation == null ? "" : accuserOccupation;
	}

	public void setAccuserOccupation(String accuserOccupation) {
		this.accuserOccupation = accuserOccupation;
	}

	public String getAccuserDomicile() {
		return accuserDomicile == null ? "" : accuserDomicile;
	}

	public void setAccuserDomicile(String accuserDomicile) {
		this.accuserDomicile = accuserDomicile;
	}

	public String getAccuserAddress() {
		return accuserAddress == null ? "" : accuserAddress;
	}

	public void setAccuserAddress(String accuserAddress) {
		this.accuserAddress = accuserAddress;
	}

	public String getAccuserPostCode() {
		return accuserPostCode == null ? "" : accuserPostCode;
	}

	public void setAccuserPostCode(String accuserPostCode) {
		this.accuserPostCode = accuserPostCode;
	}

	public String getAccuserPhone() {
		return accuserPhone == null ? "" : accuserPhone;
	}

	public void setAccuserPhone(String accuserPhone) {
		this.accuserPhone = accuserPhone;
	}

	public String getDefendantName() {
		return defendantName == null ? "" : defendantName;
	}

	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}

	public String getDefendantIdcard() {
		return defendantIdcard == null ? "" : defendantIdcard;
	}

	public void setDefendantIdcard(String defendantIdcard) {
		this.defendantIdcard = defendantIdcard;
	}

	public String getDefendantSex() {
		return defendantSex == null ? "" : defendantSex;
	}

	public void setDefendantSex(String defendantSex) {
		this.defendantSex = defendantSex;
	}

	public String getDefendantBirthday() {
		return defendantBirthday == null ? "" : defendantBirthday;
	}

	public void setDefendantBirthday(String defendantBirthday) {
		this.defendantBirthday = defendantBirthday;
	}

	public String getDefendantEthnic() {
		return defendantEthnic == null ? "" : defendantEthnic;
	}

	public void setDefendantEthnic(String defendantEthnic) {
		this.defendantEthnic = defendantEthnic;
	}

	public Area getDefendantCounty() {
		return defendantCounty;
	}

	public void setDefendantCounty(
		Area defendantCounty) {
		this.defendantCounty = defendantCounty;
	}

	public Area getDefendantTown() {
		return defendantTown;
	}

	public void setDefendantTown(
		Area defendantTown) {
		this.defendantTown = defendantTown;
	}

	public String getDefendantOccupation() {
		return defendantOccupation == null ? "" : defendantOccupation;
	}

	public void setDefendantOccupation(String defendantOccupation) {
		this.defendantOccupation = defendantOccupation;
	}

	public String getDefendantDomicile() {
		return defendantDomicile == null ? "" : defendantDomicile;
	}

	public void setDefendantDomicile(String defendantDomicile) {
		this.defendantDomicile = defendantDomicile;
	}

	public String getDefendantAddress() {
		return defendantAddress == null ? "" : defendantAddress;
	}

	public void setDefendantAddress(String defendantAddress) {
		this.defendantAddress = defendantAddress;
	}

	public String getDefendantPostCode() {
		return defendantPostCode == null ? "" : defendantPostCode;
	}

	public void setDefendantPostCode(String defendantPostCode) {
		this.defendantPostCode = defendantPostCode;
	}

	public String getDefendantPhone() {
		return defendantPhone == null ? "" : defendantPhone;
	}

	public void setDefendantPhone(String defendantPhone) {
		this.defendantPhone = defendantPhone;
	}

	public String getCaseTitle() {
		return caseTitle == null ? "" : caseTitle;
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	public String getCaseSituation() {
		return caseSituation == null ? "" : caseSituation;
	}

	public void setCaseSituation(String caseSituation) {
		this.caseSituation = caseSituation;
	}
}

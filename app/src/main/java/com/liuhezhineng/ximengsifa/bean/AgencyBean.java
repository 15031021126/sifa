/**
 * Copyright 2018 bejson.com
 */
package com.liuhezhineng.ximengsifa.bean;

import com.contrarywind.interfaces.IPickerViewData;
import java.io.Serializable;

/**
 * Auto-generated: 2018-05-02 16:52:53
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class AgencyBean implements IPickerViewData, Serializable {

	private boolean isNewRecord;
	private AreaBean area;
	private String agencyId;
	private String agencyName;
	private String agencyAddress;
	private String agencyPhone;
	private String imageUrl;
	private String coordinate;
	private int hasPerson;

	public boolean getIsNewRecord() {
		return isNewRecord;
	}

	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	public AreaBean getArea() {
		return area;
	}

	public void setArea(AreaBean area) {
		this.area = area;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgencyAddress() {
		return agencyAddress;
	}

	public void setAgencyAddress(String agencyAddress) {
		this.agencyAddress = agencyAddress;
	}

	public String getAgencyPhone() {
		return agencyPhone;
	}

	public void setAgencyPhone(String agencyPhone) {
		this.agencyPhone = agencyPhone;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public int getHasPerson() {
		return hasPerson;
	}

	public void setHasPerson(int hasPerson) {
		this.hasPerson = hasPerson;
	}

	@Override
	public String getPickerViewText() {
		return agencyName;
	}
}
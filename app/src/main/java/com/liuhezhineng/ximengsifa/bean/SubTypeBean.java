package com.liuhezhineng.ximengsifa.bean;

import com.contrarywind.interfaces.IPickerViewData;
import java.io.Serializable;

/**
 * @author AIqinfeng
 * @date 2018/5/15
 */

public class SubTypeBean implements Serializable, IPickerViewData {

	/**
	 *  value	数据值	是	[string]	1
	 label	名称	是	[string]	律师咨询
	 type	类型	是	[string]	cms_guestbook_type
	 description	描述	是	[string]	留言类型
	 sort	排序	是	[string]	10
	 parentId	父级id	是	[string]	0
	 remarks	备注	是	[string]
	 languageType	语言类型	是	[string]	CN
	 */
	private String value;
	private String label;
	private String type;
	private String description;
	private String sort;
	private String parentId;
	private String remarks;
	private String languageType;

	public String getValue() {
		return value == null ? "" : value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label == null ? "" : label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type == null ? "" : type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description == null ? "" : description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSort() {
		return sort == null ? "" : sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getParentId() {
		return parentId == null ? "" : parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRemarks() {
		return remarks == null ? "" : remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLanguageType() {
		return languageType == null ? "" : languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	@Override
	public String getPickerViewText() {
		return label;
	}
}

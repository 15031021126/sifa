package com.liuhezhineng.ximengsifa.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * @author tp_hcg
 * @date 2016/11/10 app 更新类
 */
public class AppUpdateBean implements Parcelable {

	public static final Creator<AppUpdateBean> CREATOR = new Creator<AppUpdateBean>() {
		@Override
		public AppUpdateBean createFromParcel(Parcel in) {
			return new AppUpdateBean(in);
		}

		@Override
		public AppUpdateBean[] newArray(int size) {
			return new AppUpdateBean[size];
		}
	};
	//版本号
	@SerializedName("version")
	private String versionCode;
	//描述
	@SerializedName("represent")
	private String description;
	//下载地址
	private String url;

	public AppUpdateBean() {
	}

	protected AppUpdateBean(Parcel in) {
		versionCode = in.readString();
		description = in.readString();
		url = in.readString();
	}

	public String getVersion() {
		return versionCode;
	}

	public void setVersion(String version) {
		this.versionCode = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "AppUpdateBean{" +
			"versionCode='" + versionCode + '\'' +
			", description='" + description + '\'' +
			", url='" + url + '\'' +
			'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(versionCode);
		dest.writeString(description);
		dest.writeString(url);
	}
}

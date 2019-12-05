package com.liuhezhineng.ximengsifa.bean.bussiness;

import java.io.Serializable;

public class Map implements Serializable {

	private String apply;
	private String title;
//	private List<String> fyky;
//	private String zhuren;
	private String pass;
	private String chenbanren;

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
//
//	public List<String> getFyky() {
//		return fyky;
//	}
//
//	public void setFyky(List<String> fyky) {
//		this.fyky = fyky;
//	}
//
//	public String getZhuren() {
//		return zhuren;
//	}
//
//	public void setZhuren(String zhuren) {
//		this.zhuren = zhuren;
//	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getChenbanren() {
		return chenbanren;
	}

	public void setChenbanren(String chenbanren) {
		this.chenbanren = chenbanren;
	}

}
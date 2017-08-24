package com.qs.model;

/**
 * 加工费用
 *
 */
public class JiagongfeeInfo {
	
	private int id;	 
	private String fee_type;//1为特殊加工费用，2为印花费用，3为外加工费用
	private String jiagong_type;//特殊加工方式，印花方式、外加工类型
	private String danjia;
	private String sunhao;
	private String jiagong_fee;
	private String sub_time; 
	private String edit_time; 	 
	private String sub_people; 
	private String edit_people; 
	private String state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getJiagong_type() {
		return jiagong_type;
	}
	public void setJiagong_type(String jiagong_type) {
		this.jiagong_type = jiagong_type;
	}
	public String getDanjia() {
		return danjia;
	}
	public void setDanjia(String danjia) {
		this.danjia = danjia;
	}
	public String getSunhao() {
		return sunhao;
	}
	public void setSunhao(String sunhao) {
		this.sunhao = sunhao;
	}
	public String getJiagong_fee() {
		return jiagong_fee;
	}
	public void setJiagong_fee(String jiagong_fee) {
		this.jiagong_fee = jiagong_fee;
	}
	public String getSub_time() {
		return sub_time;
	}
	public void setSub_time(String sub_time) {
		this.sub_time = sub_time;
	}
	public String getEdit_time() {
		return edit_time;
	}
	public void setEdit_time(String edit_time) {
		this.edit_time = edit_time;
	}
	public String getSub_people() {
		return sub_people;
	}
	public void setSub_people(String sub_people) {
		this.sub_people = sub_people;
	}
	public String getEdit_people() {
		return edit_people;
	}
	public void setEdit_people(String edit_people) {
		this.edit_people = edit_people;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "JiagongfeeInfo [id=" + id + ", fee_type=" + fee_type
				+ ", jiagong_type=" + jiagong_type + ", danjia=" + danjia
				+ ", sunhao=" + sunhao + ", jiagong_fee=" + jiagong_fee
				+ ", sub_time=" + sub_time + ", edit_time=" + edit_time
				+ ", sub_people=" + sub_people + ", edit_people=" + edit_people
				+ ", state=" + state + "]";
	}
	 
}

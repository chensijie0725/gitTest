package com.qs.model;

/**
 * 用户
 *
 */
public class RanzhengInfo {
	
	private int id;	 
	private String cloth_id;
	private String danjia;
	private String sunhao;
	private String ranzheng_fee;
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
	public String getCloth_id() {
		return cloth_id;
	}
	public void setCloth_id(String cloth_id) {
		this.cloth_id = cloth_id;
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
 
	public String getRanzheng_fee() {
		return ranzheng_fee;
	}
	public void setRanzheng_fee(String ranzheng_fee) {
		this.ranzheng_fee = ranzheng_fee;
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
		return "RanzhengInfo [id=" + id + ", cloth_id=" + cloth_id
				+ ", danjia=" + danjia + ", sunhao=" + sunhao
				+ ", ranzheng_price=" +", ranzheng_fee="
				+ ranzheng_fee + ", sub_time=" + sub_time + ", edit_time="
				+ edit_time + ", sub_people=" + sub_people + ", edit_people="
				+ edit_people + ", state=" + state + "]";
	}	 
}

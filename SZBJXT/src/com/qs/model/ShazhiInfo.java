package com.qs.model;

/**
 * 用户
 *
 */
public class ShazhiInfo {
	
	private int id;	 
	private String yuanliao_id;
	private String danjia;
	private String shazhi;
	private String sunhao;
	private String shijibili; 
	private String shazhi_price; 
	private String sub_time; 
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
	public String getYuanliao_id() {
		return yuanliao_id;
	}
	public void setYuanliao_id(String yuanliao_id) {
		this.yuanliao_id = yuanliao_id;
	}
	public String getShazhi() {
		return shazhi;
	}
	public void setShazhi(String shazhi) {
		this.shazhi = shazhi;
	}
	public String getSunhao() {
		return sunhao;
	}
	public void setSunhao(String sunhao) {
		this.sunhao = sunhao;
	}
	public String getShijibili() {
		return shijibili;
	}
	public void setShijibili(String shijibili) {
		this.shijibili = shijibili;
	}
	public String getShazhi_price() {
		return shazhi_price;
	}
	public void setShazhi_price(String shazhi_price) {
		this.shazhi_price = shazhi_price;
	}
 
	public String getSub_people() {
		return sub_people;
	}
	public void setSub_people(String sub_people) {
		this.sub_people = sub_people;
	}
	 
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
 
	public String getDanjia() {
		return danjia;
	}
	public void setDanjia(String danjia) {
		this.danjia = danjia;
	}
 
	public String getEdit_people() {
		return edit_people;
	}
	public void setEdit_people(String edit_people) {
		this.edit_people = edit_people;
	}
	@Override
	public String toString() {
		return "ShazhiInfo [id=" + id + ", yuanliao_id=" + yuanliao_id
				+ ", danjia=" + danjia + ", shazhi=" + shazhi + ", sunhao="
				+ sunhao + ", shijibili=" + shijibili + ", shazhi_price="
				+ shazhi_price + ", sub_time=" + sub_time + ", edit_time="
				+ edit_time + ", sub_people=" + sub_people + ", edit_people="
				+ edit_people + ", state=" + state + "]";
	} 	
	
}

package com.qs.model;


/**
 * 用户
 *
 */
public class ZhizaoInfo {
	
	private int id;	 
	private String cloth_id;
	private String model;
	private String output;
	private String zhizhao_price;
	private String zhizao_fee;
	private String sunhao; 
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getZhizhao_price() {
		return zhizhao_price;
	}
	public void setZhizhao_price(String zhizhao_price) {
		this.zhizhao_price = zhizhao_price;
	}
	public String getSunhao() {
		return sunhao;
	}
	public void setSunhao(String sunhao) {
		this.sunhao = sunhao;
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
 
	 
	public String getZhizao_fee() {
		return zhizao_fee;
	}
	public void setZhizao_fee(String zhizao_fee) {
		this.zhizao_fee = zhizao_fee;
	}
	@Override
	public String toString() {
		return "ZhizaoInfo [id=" + id + ", cloth_id=" + cloth_id + ", model="
				+ model + ", output=" + output + ", zhizhao_price="
				+ zhizhao_price + ", zhizao_fee=" + zhizao_fee + ", sunhao="
				+ sunhao + ", sub_time=" + sub_time + ", edit_time="
				+ edit_time + ", sub_people=" + sub_people + ", edit_people="
				+ edit_people + ", state=" + state + "]";
	}
 
 	  		
}

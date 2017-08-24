package com.qs.model;

/**
 * 总查询费用
 *
 */
public class TotalsearchInfo {
	
	private int id;	 
	private String cloth_id;
	private String pingming;
	private String ganghao;
	private String shazhi_fee;
	private String zhizao_fee;
	private String rangzheng_fee;
	private String specialjg_fee;
	private String yinhua_fee;
	private String waijiagong_fee;
	private String total_fee;
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
	public String getPingming() {
		return pingming;
	}
	public void setPingming(String pingming) {
		this.pingming = pingming;
	}
	public String getGanghao() {
		return ganghao;
	}
	public void setGanghao(String ganghao) {
		this.ganghao = ganghao;
	}
	public String getShazhi_fee() {
		return shazhi_fee;
	}
	public void setShazhi_fee(String shazhi_fee) {
		this.shazhi_fee = shazhi_fee;
	}
	public String getZhizao_fee() {
		return zhizao_fee;
	}
	public void setZhizao_fee(String zhizao_fee) {
		this.zhizao_fee = zhizao_fee;
	}
	public String getRangzheng_fee() {
		return rangzheng_fee;
	}
	public void setRangzheng_fee(String rangzheng_fee) {
		this.rangzheng_fee = rangzheng_fee;
	}
	public String getSpecialjg_fee() {
		return specialjg_fee;
	}
	public void setSpecialjg_fee(String specialjg_fee) {
		this.specialjg_fee = specialjg_fee;
	}
	public String getYinhua_fee() {
		return yinhua_fee;
	}
	public void setYinhua_fee(String yinhua_fee) {
		this.yinhua_fee = yinhua_fee;
	}
	public String getWaijiagong_fee() {
		return waijiagong_fee;
	}
	public void setWaijiagong_fee(String waijiagong_fee) {
		this.waijiagong_fee = waijiagong_fee;
	}
	@Override
	public String toString() {
		return "TotalsearchInfo [id=" + id + ", cloth_id=" + cloth_id
				+ ", pingming=" + pingming + ", ganghao=" + ganghao
				+ ", shazhi_fee=" + shazhi_fee + ", zhizao_fee=" + zhizao_fee
				+ ", rangzheng_fee=" + rangzheng_fee + ", specialjg_fee="
				+ specialjg_fee + ", yinhua_fee=" + yinhua_fee
				+ ", waijiagong_fee=" + waijiagong_fee + "]";
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}	 
}

package com.qs.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.qs.model.ZhizaoInfo;
 
 


public interface ZhizaoService {
 
	ZhizaoInfo findByclothid(String cloth_id);
	int totalZhizaoInfo();
	List<ZhizaoInfo> findzhizaoInfoAll(int startNumber,int endNumber);
	boolean updateeditInfo(ZhizaoInfo  zhizaoInfo);
	boolean updatesubInfo(ZhizaoInfo zhizaoInfo);
	List<ZhizaoInfo> findByCondition(JSONObject obj);
	int countConditionzhizao(JSONObject obj);
	int addsubInfo(ZhizaoInfo zhizaoInfo);
	int addeditInfo(ZhizaoInfo zhizaoInfo);
	boolean delete(int id);
	 
	 
	 	 
}

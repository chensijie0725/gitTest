package com.qs.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.qs.model.ShazhiInfo;
 
 


public interface ShazhiService {
 
	int totalShazhiInfo();
	List<ShazhiInfo> findshazhiInfoAll(int startNumber,int endNumber);
	boolean updateeditInfo(ShazhiInfo shazhiInfo);
	boolean updatesubInfo(ShazhiInfo shazhiInfo);
	List<ShazhiInfo> findByCondition(JSONObject obj);
	int countConditionShazhi(JSONObject obj);
	List<ShazhiInfo> findshazhifee(List<String> yuanliaolist);
	int addeditInfo(ShazhiInfo shazhiInfo); 
	int addsubInfo(ShazhiInfo shazhiInfo);
	boolean delete(int id);
	int addshazhifromexcel(ShazhiInfo shazhiInfo);
	 	 
}

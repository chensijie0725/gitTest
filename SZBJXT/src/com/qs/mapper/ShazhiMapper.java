package com.qs.mapper;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;

import com.qs.model.ShazhiInfo;



public interface ShazhiMapper {
 
	int totalShazhiInfo();
	List<ShazhiInfo> findAll(@Param("startNumber") int startNumber, @Param("endNumber")int endNumber);
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

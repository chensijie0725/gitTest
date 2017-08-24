package com.qs.mapper;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;

import com.qs.model.RanzhengInfo;


public interface RanzhengMapper {
 
	int totalRanzhengInfo();
	List<RanzhengInfo> findAll(@Param("startNumber") int startNumber, @Param("endNumber")int endNumber);
	boolean updateeditInfo(RanzhengInfo ranzhengInfo);
	boolean updatesubInfo(RanzhengInfo ranzhengInfo);	 
	List<RanzhengInfo> findByCondition(JSONObject obj);
	int countConditionRanzheng(JSONObject obj);
	int addsubInfo(RanzhengInfo ranzhengInfo);
	int addeditInfo(RanzhengInfo ranzhengInfo);
	boolean delete(int id);
	RanzhengInfo findByclothid(String cloth_id);
}

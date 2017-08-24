package com.qs.mapper;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;

import com.qs.model.ZhizaoInfo;


public interface ZhizaoMapper {
 
	int totalZhizaoInfo();
	List<ZhizaoInfo> findAll(@Param("startNumber") int startNumber, @Param("endNumber")int endNumber);
	boolean updateeditInfo(ZhizaoInfo zhizaoInfo);
	boolean updatesubInfo(ZhizaoInfo zhizaoInfo);	 
	List<ZhizaoInfo> findByCondition(JSONObject obj);
	int countConditionZhizao(JSONObject obj);
	int addsubInfo(ZhizaoInfo zhizaoInfo);
	int addeditInfo(ZhizaoInfo zhizaoInfo);
	boolean delete(int id);
	ZhizaoInfo findByclothid(String cloth_id);
	int addzhizaoInfo(ZhizaoInfo zhizaoInfo);
	int addzhizaofromexcel(ZhizaoInfo zhizaoInfo);
}

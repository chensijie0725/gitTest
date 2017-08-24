package com.qs.service;

import java.util.List;
import net.sf.json.JSONObject;
import com.qs.model.RanzhengInfo;

 

public interface RanzhengService {
	RanzhengInfo findByclothid(String cloth_id);
	int totalRanzhengInfo();
	List<RanzhengInfo> findranzhengInfoAll(int startNumber,int endNumber);
	boolean updateeditInfo(RanzhengInfo  ranzhengInfo);
	boolean updatesubInfo(RanzhengInfo ranzhengInfo);
	List<RanzhengInfo> findByCondition(JSONObject obj);
	int countConditionranzheng(JSONObject obj);
	int addsubInfo(RanzhengInfo ranzhengInfo);
	int addeditInfo(RanzhengInfo ranzhengInfo);
	boolean delete(int id);
	 
	 
	 	 
}

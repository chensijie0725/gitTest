package com.qs.service;

import java.util.List;

import net.sf.json.JSONObject;
import com.qs.model.JiagongfeeInfo;
 
 


public interface JiagongfeeService {
 
	int totalJiagongfeeInfo();
	List<JiagongfeeInfo> findjiagongfeeInfoAll(int startNumber,int endNumber);
	boolean updateeditInfo(JiagongfeeInfo  jiagongfeeInfo);
	boolean updatesubInfo(JiagongfeeInfo jiagongfeeInfo);
	List<JiagongfeeInfo> findByCondition(JSONObject obj);
	int countConditionjiagongfee(JSONObject obj);
	 
	 
	 	 
}

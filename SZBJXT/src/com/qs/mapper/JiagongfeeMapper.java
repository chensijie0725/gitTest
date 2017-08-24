package com.qs.mapper;

import java.util.List;

import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import com.qs.model.JiagongfeeInfo;


public interface JiagongfeeMapper {
 
	int totalJiagongfee();
	List<JiagongfeeInfo> findAll(@Param("startNumber") int startNumber, @Param("endNumber")int endNumber);
	boolean updateeditInfo(JiagongfeeInfo jiagongfeeInfo);
	boolean updatesubInfo(JiagongfeeInfo jiagongfeeInfo);	 
	List<JiagongfeeInfo> findByCondition(JSONObject obj);
	int countConditionJiagongfee(JSONObject obj);
}

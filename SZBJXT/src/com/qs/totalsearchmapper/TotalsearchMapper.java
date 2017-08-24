package com.qs.totalsearchmapper;

import java.util.List;
import java.util.Map;

import com.qs.model.ShazhiInfo;

 

public interface TotalsearchMapper {

	 
	List<Map<String, Object>> findgongyidanInfoAll(String cloth_id);
	
	List<Map<String, Object>> findyuanliaoInfoAll(Map map);

	List<ShazhiInfo> findshazhifee(List<String> yuanliaolist);
}

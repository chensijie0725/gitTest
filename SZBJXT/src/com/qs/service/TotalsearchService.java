package com.qs.service;

import java.util.List;
import java.util.Map;

import com.qs.model.ShazhiInfo;

 
 
 


public interface TotalsearchService {
 
	 
	List<Map<String, Object>> findgongyidanInfoAll(String cloth_id);
	List<Map<String, Object>> findyuanliaoInfoAll(Map map);
 	 	 	 	 
}

package com.qs.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.model.ShazhiInfo;
import com.qs.service.TotalsearchService;
import com.qs.totalsearchmapper.TotalsearchMapper;




@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class TotalsearchServiceImpl implements TotalsearchService {
	private static final Logger LOG = Logger.getLogger("TotalsearchServiceImpl");
	@Resource
	private TotalsearchMapper mapper;

	@Override
	public List<Map<String, Object>> findgongyidanInfoAll(String cloth_id) {
		 
		List<Map<String, Object>> maplist = mapper.findgongyidanInfoAll(cloth_id);
		LOG.info("通过布号查出的工艺单编号集合："+maplist);		
		return maplist;
	}

	@Override
	public List<Map<String, Object>> findyuanliaoInfoAll(Map map) {
		List<Map<String, Object>> maplist = mapper.findyuanliaoInfoAll(map);
		LOG.info("通过工艺单号查出的原料信息及比例集合："+maplist);		
		return maplist;
	}

 
  
	 
}

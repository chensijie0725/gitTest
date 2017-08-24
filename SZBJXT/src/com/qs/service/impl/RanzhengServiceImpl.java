package com.qs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.mapper.RanzhengMapper;
import com.qs.model.RanzhengInfo;
import com.qs.service.RanzhengService;
 




@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class RanzhengServiceImpl implements RanzhengService {	
	@Resource
	private RanzhengMapper mapper;
 
	public List<RanzhengInfo> findranzhengInfoAll(int startNumber,int endNumber) {
		List<RanzhengInfo> findAllList = mapper.findAll(startNumber,endNumber);
		for(int i =0;i<findAllList.size();i++){
			String state = "0".equals(findAllList.get(i).getState()) ?"已提交":"已保存";
			findAllList.get(i).setState(state);
		}
		return findAllList;
	}
	@Override
	public int totalRanzhengInfo() {
		int i = mapper.totalRanzhengInfo();
		return i;
	}
	@Override
	public boolean updateeditInfo(RanzhengInfo ranzhengInfo) {
		
		return mapper.updateeditInfo(ranzhengInfo);
	}
	@Override
	public boolean updatesubInfo(RanzhengInfo ranzhengInfo) {
		
		return mapper.updatesubInfo(ranzhengInfo);
	}
	@Override
	public List<RanzhengInfo> findByCondition(JSONObject obj) {
		List<RanzhengInfo> findAllList = mapper.findByCondition(obj);
		for(int i =0;i<findAllList.size();i++){
			String state = "0".equals(findAllList.get(i).getState()) ?"已提交":"已保存";
			findAllList.get(i).setState(state);
		}
		return findAllList;	 
	}
	@Override
	public int countConditionranzheng(JSONObject obj) {
		int i = mapper.countConditionRanzheng(obj);
		return i;
	}
	@Override
	public int addsubInfo(RanzhengInfo ranzhengInfo) {
		return mapper.addsubInfo(ranzhengInfo);
	}
	@Override
	public int addeditInfo(RanzhengInfo ranzhengInfo) { 
		return mapper.addeditInfo(ranzhengInfo);
	}
	@Override
	public boolean delete(int id) {
		return mapper.delete(id);
	}
	@Override
	public RanzhengInfo findByclothid(String cloth_id) {
		return mapper.findByclothid(cloth_id);
	}
 

	 

	 
}

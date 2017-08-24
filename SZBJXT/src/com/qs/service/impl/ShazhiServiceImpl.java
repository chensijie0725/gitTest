package com.qs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qs.mapper.ShazhiMapper;
import com.qs.model.ShazhiInfo;
import com.qs.service.ShazhiService;




@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class ShazhiServiceImpl implements ShazhiService {	
	private static final Logger LOG = Logger.getLogger("ShazhiServiceImpl");
	@Resource
	private ShazhiMapper mapper;
 
	public List<ShazhiInfo> findshazhiInfoAll(int startNumber,int endNumber) {
		List<ShazhiInfo> findAllList = mapper.findAll(startNumber,endNumber);
		for(int i =0;i<findAllList.size();i++){
			String state = "0".equals(findAllList.get(i).getState()) ?"已提交":"已保存";
			findAllList.get(i).setState(state);
		}
		return findAllList;
	}
	@Override
	public int totalShazhiInfo() {
		int i = mapper.totalShazhiInfo();
		return i;
	}
	@Override
	public boolean updateeditInfo(ShazhiInfo shazhiInfo) {
		
		return mapper.updateeditInfo(shazhiInfo);
	}
	@Override
	public boolean updatesubInfo(ShazhiInfo shazhiInfo) {
		
		return mapper.updatesubInfo(shazhiInfo);
	}
	@Override
	public List<ShazhiInfo> findByCondition(JSONObject obj) {
		List<ShazhiInfo> findAllList = mapper.findByCondition(obj);
		for(int i =0;i<findAllList.size();i++){
			String state = "0".equals(findAllList.get(i).getState()) ?"已提交":"已保存";
			findAllList.get(i).setState(state);
		}
		return findAllList;	 
	}
	@Override
	public int countConditionShazhi(JSONObject obj) {
		int i = mapper.countConditionShazhi(obj);
		return i;
	}
	@Override
	public List<ShazhiInfo> findshazhifee(List<String> yuanliaolist) {
		List<ShazhiInfo> shazhilist = mapper.findshazhifee(yuanliaolist);
		LOG.info("根据原料信息查询出的纱织价格信息："+shazhilist);		
		return shazhilist;
	}
	@Override
	public int addeditInfo(ShazhiInfo shazhiInfo) {		 
		return mapper.addeditInfo(shazhiInfo);
	}
	@Override
	public int addsubInfo(ShazhiInfo shazhiInfo) {
		return mapper.addsubInfo(shazhiInfo);
	}
	@Override
	public boolean delete(int id) {		 
		return mapper.delete(id);
	}
	@Override
	public int addshazhifromexcel(ShazhiInfo shazhiInfo) {
		return mapper.addshazhifromexcel(shazhiInfo);
	}		 
}

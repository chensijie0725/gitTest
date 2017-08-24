package com.qs.service.impl;

import java.util.List;
import javax.annotation.Resource;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qs.mapper.ZhizaoMapper;
import com.qs.model.ZhizaoInfo;
import com.qs.service.ZhizaoService;




@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class ZhizaoServiceImpl implements ZhizaoService {	
	@Resource
	private ZhizaoMapper mapper;
 
	public List<ZhizaoInfo> findzhizaoInfoAll(int startNumber,int endNumber) {
		List<ZhizaoInfo> findAllList = mapper.findAll(startNumber,endNumber);
		for(int i =0;i<findAllList.size();i++){
			String state = "0".equals(findAllList.get(i).getState()) ?"已提交":"已保存";
			findAllList.get(i).setState(state);
		}
		return findAllList;
	}
	@Override
	public int totalZhizaoInfo() {
		int i = mapper.totalZhizaoInfo();
		return i;
	}
	@Override
	public boolean updateeditInfo(ZhizaoInfo zhizaoInfo) {	
		return mapper.updateeditInfo(zhizaoInfo);
	}
	@Override
	public boolean updatesubInfo(ZhizaoInfo zhizaoInfo) {	
		return mapper.updatesubInfo(zhizaoInfo);
	}
	@Override
	public List<ZhizaoInfo> findByCondition(JSONObject obj) {
		List<ZhizaoInfo> findAllList = mapper.findByCondition(obj);
		for(int i =0;i<findAllList.size();i++){
			String state = "0".equals(findAllList.get(i).getState()) ?"已提交":"已保存";
			findAllList.get(i).setState(state);
		}
		return findAllList;	 
	}
	@Override
	public int countConditionzhizao(JSONObject obj) {
		int i = mapper.countConditionZhizao(obj);
		return i;
	}
	@Override
	public int addsubInfo(ZhizaoInfo zhizaoInfo) {
		return mapper.addsubInfo(zhizaoInfo);
	}
	@Override
	public int addeditInfo(ZhizaoInfo zhizaoInfo) {
		return mapper.addeditInfo(zhizaoInfo);
	}
	@Override
	public boolean delete(int id) {
		return mapper.delete(id);
	}
	@Override
	public ZhizaoInfo findByclothid(String cloth_id) {
		ZhizaoInfo zhizaoInfo = mapper.findByclothid(cloth_id);
		return zhizaoInfo;
	}

	 

	 
}

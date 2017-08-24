package com.qs.service.impl;

import java.util.List;
import javax.annotation.Resource;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qs.mapper.JiagongfeeMapper;
import com.qs.model.JiagongfeeInfo;
import com.qs.service.JiagongfeeService; 




@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class JiagongfeeServiceImpl implements JiagongfeeService {	
	@Resource
	private JiagongfeeMapper mapper;
 
	public List<JiagongfeeInfo> findjiagongfeeInfoAll(int startNumber,int endNumber) {
		List<JiagongfeeInfo> findAllList = mapper.findAll(startNumber,endNumber);
		for(int i =0;i<findAllList.size();i++){
			String state = "0".equals(findAllList.get(i).getState()) ?"已提交":"已保存";
			findAllList.get(i).setState(state);
			String fee_type="";
			if("1".equals(findAllList.get(i).getFee_type())){
				fee_type="特殊加工费用";
			}else if("2".equals(findAllList.get(i).getFee_type())){
				fee_type="印花费用";
			}else{
				fee_type="外加工费用";
			}
			findAllList.get(i).setFee_type(fee_type);		 
		}
		return findAllList;
	}
	@Override
	public int totalJiagongfeeInfo() {
		int i = mapper.totalJiagongfee();
		return i;
	}
	@Override
	public boolean updateeditInfo(JiagongfeeInfo jiagongfeeInfo) {
		
		return mapper.updateeditInfo(jiagongfeeInfo);
	}
	@Override
	public boolean updatesubInfo(JiagongfeeInfo jiagongfeeInfo) {
		
		return mapper.updatesubInfo(jiagongfeeInfo);
	}
	@Override
	public List<JiagongfeeInfo> findByCondition(JSONObject obj) {
		List<JiagongfeeInfo> findAllList = mapper.findByCondition(obj);
		for(int i =0;i<findAllList.size();i++){
			String state = "0".equals(findAllList.get(i).getState()) ?"已提交":"已保存";
			findAllList.get(i).setState(state);
			String fee_type="";
			if("1".equals(findAllList.get(i).getFee_type())){
				fee_type="特殊加工费用";
			}else if("2".equals(findAllList.get(i).getFee_type())){
				fee_type="印花费用";
			}else{
				fee_type="外加工费用";
			}
			findAllList.get(i).setFee_type(fee_type);
		}
		return findAllList;	 
	}
	@Override
	public int countConditionjiagongfee(JSONObject obj) {
		int i = mapper.countConditionJiagongfee(obj);
		return i;
	}
 

	 

	 
}

package com.qs.service.impl;

import java.util.List;
import javax.annotation.Resource;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qs.mapper.UserMapper;
import com.qs.model.User;
import com.qs.service.UserService;



@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper mapper;
 

	public boolean delete(int id) {
		
		return mapper.delete(id);
	}

	public List<User> findAll(int startNumber,int endNumber) {
		List<User> findAllList = mapper.findAll(startNumber,endNumber);
		return findAllList;
	}

	public User findById(int id) {

		User user = mapper.findById(id);
		
		return user;
	}

	public int save(User user) {

		return mapper.save(user);
	}

	public boolean update(User user) {
		return mapper.update(user);
	}

 

	@Override
	public int totalUser() {
		int i = mapper.totalUser();
		return i;
	}

	@Override
	public List<User> findByCondition(JSONObject obj) {
		List<User> findAllList = mapper.findByCondition(obj);
		return findAllList;
	}
	@Override
	public int countConditionUser(JSONObject obj) {
		int i = mapper.countConditionUser(obj);
		return i;
	}

	@Override
	public User findByuserName(String user_name) {
		User pwd = mapper.findByuserName(user_name);
		return pwd;
	}

}

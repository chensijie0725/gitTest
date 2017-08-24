package com.qs.mapper;

import java.util.List;

 



import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;

import com.qs.model.User;

public interface UserMapper {

	int save(User user);
	boolean update(User user);
	boolean delete(int id);
	User findById(int id);
	List<User> findAll();
	int totalUser();
	List<User> findAll(@Param("startNumber") int startNumber, @Param("endNumber")int endNumber);
	List<User> findByCondition(JSONObject obj);
	int countConditionUser(JSONObject obj);
	User findByuserName(String user_name);
}

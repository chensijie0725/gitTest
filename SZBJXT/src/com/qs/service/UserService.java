package com.qs.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.qs.model.User;


public interface UserService {
	int save(User user);
	boolean update(User user);
	boolean delete(int id);
	User findById(int id);
	List<User> findAll(int startNumber, int endNumber);
	int totalUser();
	List<User> findByCondition(JSONObject obj);
	int countConditionUser(JSONObject obj);
	User findByuserName(String user_name);
	 
}

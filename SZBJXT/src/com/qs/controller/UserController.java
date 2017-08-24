package com.qs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qs.model.PageJSON;
import com.qs.model.User;
import com.qs.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 获取所有用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllUser.htm")
	public String getAllUser(HttpServletRequest request){
		
		List<User> findAll = userService.findAll(0,100);			 		
		request.setAttribute("userList", findAll);		 
		return "/allUser";
	}
	
	/**
	 * 跳转到添加用户界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddUser.htm")
	public String toAddUser(HttpServletRequest request){		
		return "/addUser";
	}
	/**
	 * 添加用户并重定向
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/addUser.htm")
	public String addUser(User user,HttpServletRequest request){
		userService.save(user);
		return "redirect:/user/getAllUser";
	}
	
	/**
	 *编辑用户
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateUser.htm")
	public String updateUser(User user,HttpServletRequest request){				
		if(userService.update(user)){
			user = userService.findById(user.getId());
			request.setAttribute("user", user);
			return "redirect:/user/getAllUser";
		}else{
			return "/error";
		}
	}
	/**
	 * 根据id查询单个用户
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUser.htm")
	public String getUser(int id,HttpServletRequest request){
		
		request.setAttribute("user", userService.findById(id));
		return "/editUser";
	}
	/**
	 * 删除用户
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delUser.htm")
	public void delUser(int id,HttpServletRequest request,HttpServletResponse response){
		String result = "{\"result\":\"error\"}";		
		if(userService.delete(id)){
			result = "{\"result\":\"success\"}";
		}		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	
	@RequestMapping("/index.htm")
	public String getindex(HttpServletRequest request){	
	 	User user = (User) request.getSession().getAttribute("userInfo");		
	 	/*	if("".equals(user)||null==user){
			request.setAttribute("errorInfo", "缺少用户信息");
			return "/error";
		}*/	 
		request.setAttribute("userName", user.getUser_name());
		return "/index";
	}
	
	@RequestMapping("/usertable.htm")
	public String getusertable(HttpServletRequest request){	
		User user = (User) request.getSession().getAttribute("userInfo");
		String power = "是".equals(user.getPower())?"1":"0";
		if("0".equals(power)){
			request.setAttribute("errorInfo", "不是超管用户，暂无权限");
			return "/error";
		}		 
		return "/usertable";
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/getAllUserlist.htm" )
	public void getAllUserList(HttpServletRequest request,HttpServletResponse response){
		String search_name = request.getParameter("search_name");
		List<User> findzhizaouserAll = new ArrayList<User>();
		int totalcount =0;//总条数
		String result = "{\"total\":0,\"rows\":[ ]}";//定义变量并初始化JSON格式的结果集
		PageJSON<User> pjson = new PageJSON<User>() ;//封装Rate类	 	
		if(!"".equals(search_name)&&search_name!=null){
			 JSONObject obj = new JSONObject();
			 obj.put("user_name", search_name);
			   findzhizaouserAll= userService.findByCondition(obj);
			//   totalcount=1000;
			   totalcount = userService.countConditionUser(obj);
		}else{
			String a = request.getParameter("rows");
			String b = request.getParameter("page");		
			int pageSize = Integer.parseInt(a);
			int pageNumber = Integer.parseInt(b);
			int startNumber = (pageNumber-1)*pageSize;
			int endNumber = pageNumber*pageSize;		 	 
			findzhizaouserAll = userService.findAll(startNumber,endNumber);	
			totalcount = userService.totalUser();		 
		}
		pjson.setRows(findzhizaouserAll); 
		pjson.setTotal(totalcount);        
		result = JSONObject.fromObject(pjson).toString();
		System.out.println(result);		 
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();			  
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}			 
	}
	
	@RequestMapping("/AddUser.htm")
	public void AddUser(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String a = request.getParameter("power");
		String power = ("0".equals(a))?"是":"否";
		User user = new User();
		user.setUser_name(user_name);
		user.setPassword(password);
		user.setPower(power);		
		int i = userService.save(user);	 
		if(i==0){
			result =  "{\"result\":\"error\"}";
		}
		response.setContentType("application/json");		 
			PrintWriter out = response.getWriter();			  
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		 
	}
	
	@RequestMapping("/UpdUser.htm")
	public void UpdUser(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		int id = Integer.parseInt(request.getParameter("id"));
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String a = request.getParameter("power");
		String power = ("0".equals(a))?"是":"否";
		User user = new User();
		user.setId(id);
		user.setUser_name(user_name);
		user.setPassword(password);
		user.setPower(power);		
		boolean i = userService.update(user);	 
		if(!i){
			result =  "{\"result\":\"error\"}";
		}
		response.setContentType("application/json");		 
			PrintWriter out = response.getWriter();			  
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		 
	}
	
	@RequestMapping("/DelUser.htm")
	public void DelUser(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		int id = Integer.parseInt(request.getParameter("id"));		 	
		boolean i = userService.delete(id);	 
		if(!i){
			result =  "{\"result\":\"error\"}";
		}
		response.setContentType("application/json");		 
			PrintWriter out = response.getWriter();			  
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		 
	}
	
	@RequestMapping("/Login.htm")
	public void Login(HttpServletRequest request,HttpServletResponse response){
	try {
		String result =  "{\"result\":\"账号或密码错误\"}";
				 	 
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");		 	
		User user = userService.findByuserName(user_name);	 
		if(password.equals(user.getPassword())){
			result =  "{\"result\":\"success\"}";
		//	String power = "是".equals(user.getPower())?"1":"0";
			request.getSession().setAttribute("userInfo", user);
		}
		response.setContentType("application/json");		 
		PrintWriter out = response.getWriter();			  
		out.write(result);
		} catch (IOException e) {
		e.printStackTrace();
		}			 					 
	}
	
	@RequestMapping("/loginout.htm")
	public void loginout(HttpServletRequest request,HttpServletResponse response){	 
		request.getSession().removeAttribute("userInfo");
		try {
			response.sendRedirect("/login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return "/login";	 			 					 
	}
	
	
	
	
}

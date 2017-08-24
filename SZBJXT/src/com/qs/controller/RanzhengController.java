package com.qs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qs.model.PageJSON;
import com.qs.model.RanzhengInfo;
import com.qs.model.User;
import com.qs.model.ZhizaoInfo;
import com.qs.service.RanzhengService;

@Controller
@RequestMapping("/ranzheng")
public class RanzhengController {

	@Autowired
	private  RanzhengService ranzhengService;
	
 
	
	@RequestMapping("/ranzhengtable.htm")
	public String getranzhengtable(HttpServletRequest request){			 	 
		return "/ranzhengtable";
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/getAllranzhenglist.htm" )
	public void getAllUserList(HttpServletRequest request,HttpServletResponse response){
		String cloth_id = request.getParameter("cloth_id");
		List<RanzhengInfo> RanzhengInfoAll = new ArrayList<RanzhengInfo>();
		int totalcount =0;//总条数
		String result = "{\"total\":0,\"rows\":[ ]}";//定义变量并初始化JSON格式的结果集
		PageJSON<RanzhengInfo> pjson = new PageJSON<RanzhengInfo>() ;//封装Rate类	 	
		if((!"".equals(cloth_id)&&cloth_id!=null)){
			 JSONObject obj = new JSONObject();
			 obj.put("cloth_id", cloth_id);
			 RanzhengInfoAll= ranzhengService.findByCondition(obj);
			 totalcount = ranzhengService.countConditionranzheng(obj);
		}else{
			String a = request.getParameter("rows");
			String b = request.getParameter("page");		
			int pageSize = Integer.parseInt(a);
			int pageNumber = Integer.parseInt(b);
			int startNumber = (pageNumber-1)*pageSize;
			int endNumber = pageNumber*pageSize;		 	 
			RanzhengInfoAll = ranzhengService.findranzhengInfoAll(startNumber,endNumber);	
			totalcount = ranzhengService.totalRanzhengInfo();		 
		}
		pjson.setRows(RanzhengInfoAll); 
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
	
	@RequestMapping("/Updranzheng.htm")
	public void UpdUser(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		User user = (User) request.getSession().getAttribute("userInfo");	
		int id = Integer.parseInt(request.getParameter("id"));
		String user_name = user.getUser_name();
		String cloth_id = request.getParameter("cloth_id");
		String danjia = request.getParameter("danjia");
		String sunhao = request.getParameter("sunhao");		
		String state = request.getParameter("state");//0为提交 1为保存
		RanzhengInfo ranzhengInfo = new RanzhengInfo();
		ranzhengInfo.setId(id);
		ranzhengInfo.setCloth_id(cloth_id);
		ranzhengInfo.setDanjia(danjia);
		ranzhengInfo.setSunhao(sunhao);
		ranzhengInfo.setState(state);
		DecimalFormat df = new DecimalFormat("#.000");
		double price = countranzheng_fee(danjia,sunhao);
		String ranzheng_fee= df.format(price);
		ranzhengInfo.setRanzheng_fee(ranzheng_fee);
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = dateFormat.format(d);
		ranzhengInfo.setEdit_time(nowTime);
		ranzhengInfo.setEdit_people(user_name);
		boolean i = true;
		if("0".equals(state)){
			ranzhengInfo.setSub_people(user_name);
			ranzhengInfo.setSub_time(nowTime);
		 	 i = ranzhengService.updatesubInfo(ranzhengInfo);//修改提交操作信息
		}else{
		 	 i = ranzhengService.updateeditInfo(ranzhengInfo);//修改保存操作信息
		}
	 	 	 
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
	@RequestMapping("/Addranzheng.htm")
	public void AddUser(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		User user = (User) request.getSession().getAttribute("userInfo");	
		String user_name = user.getUser_name();
		String cloth_id = request.getParameter("cloth_id");
		String danjia = request.getParameter("danjia");
		String sunhao = request.getParameter("sunhao");
		String state = request.getParameter("state");//0为提交 1为保存
		RanzhengInfo ranzhengInfo = new RanzhengInfo();
		ranzhengInfo.setCloth_id(cloth_id);
		ranzhengInfo.setDanjia(danjia);
		ranzhengInfo.setSunhao(sunhao);
		ranzhengInfo.setState(state);	 
		DecimalFormat df = new DecimalFormat("#.000");
		double price = countranzheng_fee(danjia,sunhao);
		String ranzheng_fee= df.format(price);
		ranzhengInfo.setRanzheng_fee(ranzheng_fee);
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = dateFormat.format(d);
		ranzhengInfo.setEdit_time(nowTime);
		ranzhengInfo.setEdit_people(user_name);
		int i = 0;
		if("0".equals(state)){
			ranzhengInfo.setSub_people(user_name);
			ranzhengInfo.setSub_time(nowTime);
		 	 i = ranzhengService.addsubInfo(ranzhengInfo);//添加提交操作信息
		}else{
			 i = ranzhengService.addeditInfo(ranzhengInfo);//添加保存操作信息
		}
	 	 	 
		if(i<0){
			result =  "{\"result\":\"error\"}";
		}
		response.setContentType("application/json");		 
			PrintWriter out = response.getWriter();			  
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		 
	}
	@RequestMapping("/Delranzheng.htm")
	public void DelZhizao(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		int id = Integer.parseInt(request.getParameter("id"));		 	
		boolean i = ranzhengService.delete(id);	 
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
	

	private double countranzheng_fee(String danjia, String sunhao) {
		double a = Double.parseDouble(danjia);
		double b = Double.parseDouble(sunhao)/100;
		double d = a*(1+b);
		return d;	
	}	
}

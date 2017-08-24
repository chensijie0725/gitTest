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
import com.qs.model.ShazhiInfo;
import com.qs.model.User;
import com.qs.service.ShazhiService;

@Controller
@RequestMapping("/shazhi")
public class ShazhiController {

	@Autowired
	private  ShazhiService shazhiservice;
	
 
	
	@RequestMapping("/shazhitable.htm")
	public String getushazhitable(HttpServletRequest request){			 	 
		return "/shazhitable";
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/getAllshazhilist.htm" )
	public void getAllUserList(HttpServletRequest request,HttpServletResponse response){
		String yuanliao_id = request.getParameter("yuanliao_id");
		String shazhi = request.getParameter("shazhi");
		List<ShazhiInfo> ShazhiInfoAll = new ArrayList<ShazhiInfo>();
		int totalcount =0;//总条数
		String result = "{\"total\":0,\"rows\":[ ]}";//定义变量并初始化JSON格式的结果集
		PageJSON<ShazhiInfo> pjson = new PageJSON<ShazhiInfo>() ;//封装Rate类	 	
		if((!"".equals(yuanliao_id)&&yuanliao_id!=null)||(!"".equals(shazhi)&&shazhi!=null)){
			 JSONObject obj = new JSONObject();
			 obj.put("yuanliao_id", yuanliao_id);
			 obj.put("shazhi", shazhi);
			 ShazhiInfoAll= shazhiservice.findByCondition(obj);
			 totalcount = shazhiservice.countConditionShazhi(obj);
		}else{
			String a = request.getParameter("rows");
			String b = request.getParameter("page");		
			int pageSize = Integer.parseInt(a);
			int pageNumber = Integer.parseInt(b);
			int startNumber = (pageNumber-1)*pageSize;
			int endNumber = pageNumber*pageSize;		 	 
			ShazhiInfoAll = shazhiservice.findshazhiInfoAll(startNumber,endNumber);	
			totalcount = shazhiservice.totalShazhiInfo();		 
		}
		pjson.setRows(ShazhiInfoAll); 
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
	
	@RequestMapping("/Updshazhi.htm")
	public void Updshazhi(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		User user = (User) request.getSession().getAttribute("userInfo");	
		int id = Integer.parseInt(request.getParameter("id"));
		String user_name = user.getUser_name();
		String yuanliao_id = request.getParameter("yuanliao_id");
		String shazhi = request.getParameter("shazhi");
		String danjia = request.getParameter("danjia");
		String sunhao = request.getParameter("sunhao");
	//	String shijibili = request.getParameter("shijibili");
		String state = request.getParameter("state");//0为提交 1为保存
		ShazhiInfo shazhiInfo = new ShazhiInfo();
		shazhiInfo.setId(id);
		shazhiInfo.setYuanliao_id(yuanliao_id);
		shazhiInfo.setShazhi(shazhi);
		shazhiInfo.setDanjia(danjia);
		shazhiInfo.setSunhao(sunhao);
//		shazhiInfo.setShijibili(shijibili);
		shazhiInfo.setState(state);
		shazhiInfo.setEdit_people(user_name);
		DecimalFormat df = new DecimalFormat("#.000");
		double price = countshazhi_price(danjia,sunhao);
		String shazhi_price= df.format(price);
		shazhiInfo.setShazhi_price(shazhi_price);
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = dateFormat.format(d);
		shazhiInfo.setEdit_time(nowTime);
		boolean i = true;
		if("0".equals(state)){
			shazhiInfo.setSub_people(user_name);
			shazhiInfo.setSub_time(nowTime);
			i = shazhiservice.updatesubInfo(shazhiInfo);//修改提交操作信息
		}else{
			 i = shazhiservice.updateeditInfo(shazhiInfo);//修改保存操作信息
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

	
	@RequestMapping("/Addshazhi.htm")
	public void Addshazhi(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		User user = (User) request.getSession().getAttribute("userInfo");	
	//	int id = Integer.parseInt(request.getParameter("id"));
		String user_name = user.getUser_name();
		String yuanliao_id = request.getParameter("yuanliao_id");
		String shazhi = request.getParameter("shazhi");
		String danjia = request.getParameter("danjia");
		String sunhao = request.getParameter("sunhao");
		String state = request.getParameter("state");//0为提交 1为保存
		ShazhiInfo shazhiInfo = new ShazhiInfo();
		shazhiInfo.setYuanliao_id(yuanliao_id);
		shazhiInfo.setShazhi(shazhi);
		shazhiInfo.setDanjia(danjia);
		shazhiInfo.setSunhao(sunhao);
		shazhiInfo.setState(state);
		shazhiInfo.setEdit_people(user_name);
		DecimalFormat df = new DecimalFormat("#.000");
		double price = countshazhi_price(danjia,sunhao);
		String shazhi_price= df.format(price);
		shazhiInfo.setShazhi_price(shazhi_price);
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = dateFormat.format(d);
		shazhiInfo.setEdit_time(nowTime);
		int i = 0;
		if("0".equals(state)){
			shazhiInfo.setSub_people(user_name);
			shazhiInfo.setSub_time(nowTime);
			i = shazhiservice.addsubInfo(shazhiInfo);//添加提交操作信息
		}else{
			 i = shazhiservice.addeditInfo(shazhiInfo);//添加保存操作信息
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
	
	@RequestMapping("/Delshazhi.htm")
	public void DelShazhi(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		int id = Integer.parseInt(request.getParameter("id"));		 	
		boolean i = shazhiservice.delete(id);	 
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
	
	private double countshazhi_price(String danjia, String sunhao) {
		double a = Double.parseDouble(danjia);
		double b = Double.parseDouble(sunhao)/100;
	//	double c = Double.parseDouble(shijibili)/100;
		double d = a*(1+b);
		return d;	
	}	
}

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
import com.qs.model.User;
import com.qs.model.ZhizaoInfo;
import com.qs.service.ZhizaoService;

@Controller
@RequestMapping("/zhizao")
public class ZhizaoController {
	@Autowired
	private  ZhizaoService zhizaoservice;	
	
	@RequestMapping("/zhizaotable.htm")
	public String getzhizaotable(HttpServletRequest request){			 	 
		return "/zhizaotable";
	}
	@RequestMapping("/toolbar.htm")
	public String gettoolbartable(HttpServletRequest request){			 	 
		return "/Testtoolbar";
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/getAllzhizaolist.htm" )
	public void getAllUserList(HttpServletRequest request,HttpServletResponse response){
		String cloth_id = request.getParameter("cloth_id");
		String model = request.getParameter("model");
		List<ZhizaoInfo> ZhizaoInfoAll = new ArrayList<ZhizaoInfo>();
		int totalcount =0;//总条数
		String result = "{\"total\":0,\"rows\":[ ]}";//定义变量并初始化JSON格式的结果集
		PageJSON<ZhizaoInfo> pjson = new PageJSON<ZhizaoInfo>() ;//封装Rate类	 	
		if((!"".equals(cloth_id)&&cloth_id!=null)||(!"".equals(model)&&model!=null)){
			 JSONObject obj = new JSONObject();
			 obj.put("cloth_id", cloth_id);
			 obj.put("model", model);
			 ZhizaoInfoAll= zhizaoservice.findByCondition(obj);
			 totalcount = zhizaoservice.countConditionzhizao(obj);
		}else{
			String a = request.getParameter("rows");
			String b = request.getParameter("page");		
			int pageSize = Integer.parseInt(a);
			int pageNumber = Integer.parseInt(b);
			int startNumber = (pageNumber-1)*pageSize;
			int endNumber = pageNumber*pageSize;		 	 
			ZhizaoInfoAll = zhizaoservice.findzhizaoInfoAll(startNumber,endNumber);	
			totalcount = zhizaoservice.totalZhizaoInfo();		 
		}
		pjson.setRows(ZhizaoInfoAll); 
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
	
	@RequestMapping("/Updzhizao.htm")
	public void UpdUser(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		User user = (User) request.getSession().getAttribute("userInfo");	
		int id = Integer.parseInt(request.getParameter("id"));
		String user_name = user.getUser_name();
		String cloth_id = request.getParameter("cloth_id");
		String model = request.getParameter("model");
		String output = request.getParameter("output");
		String zhizhao_price = request.getParameter("zhizhao_price");
		String sunhao = request.getParameter("sunhao");
		String state = request.getParameter("state");//0为提交 1为保存
		ZhizaoInfo zhizaoInfo = new ZhizaoInfo();
		zhizaoInfo.setId(id);
		zhizaoInfo.setCloth_id(cloth_id);
		zhizaoInfo.setModel(model);
		zhizaoInfo.setOutput(output);
		zhizaoInfo.setZhizhao_price(zhizhao_price);
		zhizaoInfo.setSunhao(sunhao);
		zhizaoInfo.setState(state);	 
		DecimalFormat df = new DecimalFormat("#.000");
		double price = countzhizao_fee(sunhao,zhizhao_price);
		String zhizao_fee= df.format(price);
		zhizaoInfo.setZhizao_fee(zhizao_fee);
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = dateFormat.format(d);
		zhizaoInfo.setEdit_time(nowTime);
		zhizaoInfo.setEdit_people(user_name);
		boolean i = true;
		if("0".equals(state)){
			zhizaoInfo.setSub_people(user_name);
			zhizaoInfo.setSub_time(nowTime);
		 	 i = zhizaoservice.updatesubInfo(zhizaoInfo);//修改提交操作信息
		}else{
			 i = zhizaoservice.updateeditInfo(zhizaoInfo);//修改保存操作信息
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

	@RequestMapping("/Addzhizao.htm")
	public void Addzhizao(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		User user = (User) request.getSession().getAttribute("userInfo");	
		String user_name = user.getUser_name();
		String cloth_id = request.getParameter("cloth_id");
		String model = request.getParameter("model");
		String output = request.getParameter("output");
		String zhizhao_price = request.getParameter("zhizhao_price");
		String sunhao = request.getParameter("sunhao");
		String state = request.getParameter("state");//0为提交 1为保存
		ZhizaoInfo zhizaoInfo = new ZhizaoInfo();
		zhizaoInfo.setCloth_id(cloth_id);
		zhizaoInfo.setModel(model);
		zhizaoInfo.setOutput(output);
		zhizaoInfo.setZhizhao_price(zhizhao_price);
		zhizaoInfo.setSunhao(sunhao);
		zhizaoInfo.setState(state);	 
		DecimalFormat df = new DecimalFormat("#.000");
		double price = countzhizao_fee(sunhao,zhizhao_price);
		String zhizao_fee= df.format(price);
		zhizaoInfo.setZhizao_fee(zhizao_fee);
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = dateFormat.format(d);
		zhizaoInfo.setEdit_time(nowTime);
		zhizaoInfo.setEdit_people(user_name);
		int i = 0;
		if("0".equals(state)){
			zhizaoInfo.setSub_people(user_name);
			zhizaoInfo.setSub_time(nowTime);
		 	 i = zhizaoservice.addsubInfo(zhizaoInfo);//添加提交操作信息
		}else{
			 i = zhizaoservice.addeditInfo(zhizaoInfo);//添加保存操作信息
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
	@RequestMapping("/Delzhizao.htm")
	public void DelZhizao(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		int id = Integer.parseInt(request.getParameter("id"));		 	
		boolean i = zhizaoservice.delete(id);	 
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
	
	private double countzhizao_fee(String sunhao, String zhizhao_price) {
		double b = Double.parseDouble(sunhao)/100;
		double c = Double.parseDouble(zhizhao_price);
		double d = (1+b)*c;
		return d;	
	}	
}

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
import com.qs.model.JiagongfeeInfo;
import com.qs.model.PageJSON;
import com.qs.model.RanzhengInfo;
import com.qs.model.User;
import com.qs.service.JiagongfeeService;


@Controller
@RequestMapping("/jiagongfee")
public class JiagongfeeController {

	@Autowired
	private  JiagongfeeService jiagongfeeService;
	
 
	
	@RequestMapping("/jiagongfeetable.htm")
	public String getranzhengtable(HttpServletRequest request){			 	 
		return "/jiagongfeetable";
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/getAlljiagongfeelist.htm" )
	public void getAllUserList(HttpServletRequest request,HttpServletResponse response){
		String fee_type = request.getParameter("fee_type");
		String jiagong_type = request.getParameter("jiagong_type");
		List<JiagongfeeInfo> JiagongfeeInfoAll = new ArrayList<JiagongfeeInfo>();
		int totalcount =0;//总条数
		String result = "{\"total\":0,\"rows\":[ ]}";//定义变量并初始化JSON格式的结果集
		PageJSON<JiagongfeeInfo> pjson = new PageJSON<JiagongfeeInfo>() ;//封装Rate类	 	
		if((!"".equals(fee_type)&&fee_type!=null)||(!"".equals(jiagong_type)&&jiagong_type!=null)){
			 JSONObject obj = new JSONObject();
			 obj.put("fee_type", fee_type);
			 obj.put("jiagong_type", jiagong_type);
			 JiagongfeeInfoAll= jiagongfeeService.findByCondition(obj);
			 totalcount = jiagongfeeService.countConditionjiagongfee(obj);
		}else{
			String a = request.getParameter("rows");
			String b = request.getParameter("page");		
			int pageSize = Integer.parseInt(a);
			int pageNumber = Integer.parseInt(b);
			int startNumber = (pageNumber-1)*pageSize;
			int endNumber = pageNumber*pageSize;		 	 
			JiagongfeeInfoAll = jiagongfeeService.findjiagongfeeInfoAll(startNumber,endNumber);	
			totalcount = jiagongfeeService.totalJiagongfeeInfo();		 
		}
		pjson.setRows(JiagongfeeInfoAll); 
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
	
	@RequestMapping("/Updjiagongfee.htm")
	public void UpdUser(HttpServletRequest request,HttpServletResponse response){
	try {
		String result = "{\"result\":\"success\"}";
		User user = (User) request.getSession().getAttribute("userInfo");	
		int id = Integer.parseInt(request.getParameter("id"));
		String user_name = user.getUser_name();
		String danjia = request.getParameter("danjia");
		String sunhao = request.getParameter("sunhao");		
		String state = request.getParameter("state");//0为提交 1为保存
		JiagongfeeInfo jiagongfee = new JiagongfeeInfo();
		jiagongfee.setId(id);
		jiagongfee.setDanjia(danjia);
		jiagongfee.setSunhao(sunhao);
		jiagongfee.setState(state);
		DecimalFormat df = new DecimalFormat("#.000");
		double price = countjiagong_fee(danjia,sunhao);
		String jiagong_fee= df.format(price);
		jiagongfee.setJiagong_fee(jiagong_fee);
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = dateFormat.format(d);
		jiagongfee.setEdit_time(nowTime);
		jiagongfee.setEdit_people(user_name);
		boolean i = true;
		if("0".equals(state)){
			jiagongfee.setSub_people(user_name);
			jiagongfee.setSub_time(nowTime);
		 	  i = jiagongfeeService.updatesubInfo(jiagongfee);//修改提交操作信息
		}else{
		 	  i = jiagongfeeService.updateeditInfo(jiagongfee);//修改保存操作信息
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

	private double countjiagong_fee(String danjia, String sunhao) {
		double a = Double.parseDouble(danjia);
		double b = Double.parseDouble(sunhao)/100;
		double d = a*(1+b);
		return d;	
	}	
}

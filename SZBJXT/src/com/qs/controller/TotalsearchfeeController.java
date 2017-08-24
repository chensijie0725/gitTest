package com.qs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qs.model.PageJSON;
import com.qs.model.RanzhengInfo;
import com.qs.model.ShazhiInfo;
import com.qs.model.TotalsearchInfo;
import com.qs.model.ZhizaoInfo;
import com.qs.service.RanzhengService;
import com.qs.service.ShazhiService;
import com.qs.service.TotalsearchService;
import com.qs.service.ZhizaoService;
import com.qs.util.ReturnInfo;
 


@Controller
@RequestMapping("/totalsearch")
public class TotalsearchfeeController {
	private static final Logger LOG = Logger.getLogger("TotalsearchfeeController");
	@Autowired
	private TotalsearchService totalsearchService;
	@Autowired
	private  ShazhiService shazhiservice;
	@Autowired
	private  ZhizaoService zhizaoservice;
	@Autowired
	private  RanzhengService ranzhengService;
	
	@RequestMapping("/totalsearchfeetable.htm")
	public String getranzhengtable(HttpServletRequest request){			 	 
		return "/totalSearchtable";
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/getAlltotalsearchfeelist.htm" )
	public void getAllUserList(HttpServletRequest request,HttpServletResponse response){
		String result = "{\"result\":\"success\"}";
		String cloth_id = request.getParameter("cloth_id");
		if(null==cloth_id){
			result = "{\"total\":0,\"rows\":[ ]}";//定义变量并初始化JSON格式的结果集
			ReturnInfo.send(response, result); 
			return;
		}
		List<TotalsearchInfo> totalsearchInfolist = new ArrayList<TotalsearchInfo>();
		 //初始化纱织价格
		List<Map<String, Object>> gongyidan_map = new ArrayList<Map<String, Object>>();		 
		List<Map<String, Object>> yuanliao_map = new ArrayList<Map<String, Object>>();		
		gongyidan_map= initializegongyidanInfo(cloth_id);//查询工艺单编号	
		if(0==gongyidan_map.size()){
			result= "{'msgInfo':'数据库内无该布号对应的工艺单号','msg':'error'}";
			ReturnInfo.send(response, result); 
			return;
		}
		yuanliao_map = initializeyuanliaoInfo(gongyidan_map);
		if(0==yuanliao_map.size()){
			result= "{'msgInfo':'数据库内无该布号对应的原料信息','msg':'error'}";
			ReturnInfo.send(response, result); 
			return;
		}
		JSONObject shazhires = initializeshazifee(yuanliao_map);	
		if(!shazhires.optString("msg").equals("200")){
			result= "{'msgInfo':'原料价格信息状态错误','msg':'error'}";
			ReturnInfo.send(response, result); 
			return;
		}
		//初始化织造价格
		ZhizaoInfo zhizaoInfo =  zhizaoservice.findByclothid(cloth_id);
		 LOG.info("织造价格信息："+zhizaoInfo.toString());
		 if(null==zhizaoInfo||"1".equals(zhizaoInfo.getState())){
			 	result= "{'msgInfo':'织造价格信息状态错误','msg':'error'}";
				ReturnInfo.send(response, result); 
				return;
		 }
		//初始化染整价格
		RanzhengInfo ranzhengInfo =  ranzhengService.findByclothid(cloth_id);
		 LOG.info("染整价格信息："+ranzhengInfo.toString());
		 if(null==ranzhengInfo||"1".equals(ranzhengInfo.getState())){
				result= "{'msgInfo':'染整价格信息状态错误','msg':'error'}";
				ReturnInfo.send(response, result); 
				return;
		 }
	 
		TotalsearchInfo totalsearchInfo = new TotalsearchInfo();
		totalsearchInfo.setCloth_id(cloth_id);
		totalsearchInfo.setShazhi_fee(shazhires.optString("totalzhazhifee"));
		totalsearchInfo.setZhizao_fee(zhizaoInfo.getZhizao_fee());
		totalsearchInfo.setPingming(gongyidan_map.get(0).get("PBMC").toString());
		totalsearchInfo.setRangzheng_fee(ranzhengInfo.getRanzheng_fee());
		Double tmp = Double.parseDouble(shazhires.optString("totalzhazhifee"))+
				Double.parseDouble(zhizaoInfo.getZhizao_fee())+
				Double.parseDouble(ranzhengInfo.getRanzheng_fee());
		DecimalFormat df = new DecimalFormat("#.0");
		String totalfee= df.format(tmp);
		totalsearchInfo.setTotal_fee(totalfee);
		totalsearchInfolist.add(totalsearchInfo);
		PageJSON<TotalsearchInfo> pjson = new PageJSON<TotalsearchInfo>() ;//封装Rate类	 
		pjson.setRows(totalsearchInfolist); 
		pjson.setTotal(1);		 
		result = JSONObject.fromObject(pjson).toString();	 
		ReturnInfo.send(response, result);
	}

	private JSONObject initializeshazifee(List<Map<String, Object>> yuanliao_map) {
		HashMap<String, String> yuanliaomap = new HashMap<String, String>();
		String totalzhazhifee = "";
		double tmp = 0;
		JSONObject obj = new JSONObject();
			obj.put("msg", "200") ;
		List<String> yuanliaolist = new  ArrayList<String>();			 
		   for (Map<String, Object> map : yuanliao_map) {
			   yuanliaolist.add((String) map.get("YLBH"));
			   yuanliaomap.put((String) map.get("YLBH"), (String) map.get("SJBL"));
		   }
		   LOG.info("原料及实际比例："+yuanliaomap.toString());
		   List<ShazhiInfo> shazhilist = shazhiservice.findshazhifee(yuanliaolist);
		   for(int i =0;i<shazhilist.size();i++){
			   if("1".equals(shazhilist.get(i).getState())){
				   obj.put("msg", "500") ;
				   break;
			   }
			String yuanliao_id = shazhilist.get(i).getYuanliao_id();
			double shijibili = Double.parseDouble(yuanliaomap.get(yuanliao_id))/100;
			double shazhi_price =Double.parseDouble(shazhilist.get(i).getShazhi_price());
			tmp= shazhi_price*shijibili + tmp;			 
		   }
		   DecimalFormat df = new DecimalFormat("#.000");
		   totalzhazhifee= df.format(tmp);
		   obj.put("totalzhazhifee", totalzhazhifee);
		   LOG.info("纱织总价："+obj.toString());
		   return obj;		  		 
	}

	private List<Map<String, Object>> initializeyuanliaoInfo(List<Map<String, Object>> gongyidan_map) {	
		Map map =new  HashMap<String, String>();
		map = gongyidan_map.get(0);
		List<Map<String, Object>> maplist =totalsearchService.findyuanliaoInfoAll(map);	
		return maplist;
	}

	private List<Map<String, Object>> initializegongyidanInfo(String cloth_id) {
		List<Map<String, Object>> maplist =totalsearchService.findgongyidanInfoAll(cloth_id);	
		return maplist;
	}
	
	 
}

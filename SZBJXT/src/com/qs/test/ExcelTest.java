package com.qs.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qs.mapper.ShazhiMapper;
import com.qs.mapper.ZhizaoMapper;
import com.qs.model.ShazhiInfo;
import com.qs.model.ZhizaoInfo;
import com.qs.service.ShazhiService;
import com.qs.util.ReadShazhiExcel;
import com.qs.util.ReadZhizaoExcel;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/spring-common.xml")
public class ExcelTest {
	@Resource
	private ShazhiMapper shazhimapper;
	@Resource
	private ZhizaoMapper zhizaomapper;
	
	public static void main(String[] args) {				
		  ReadShazhiExcel xlsMain = new ReadShazhiExcel();
		  ShazhiInfo shazhiInfo = null;
	       List<ShazhiInfo> list = new ArrayList<ShazhiInfo>();
		try {
			list = xlsMain.readXls("D:/excel/shazhidata.xls");
			for (int i = 0; i < list.size(); i++) {
	        	shazhiInfo = list.get(i);
	         
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	         
	}	
	/*@Test
 	public void testAddshazhi(){
		 ReadShazhiExcel xlsMain = new ReadShazhiExcel();
		  ShazhiInfo shazhiInfo = null;
	       List<ShazhiInfo> list = new ArrayList<ShazhiInfo>();
		try {
			list = xlsMain.readXls("D:/excel/shazhidata.xls");
			for (int i = 0; i < list.size(); i++) {
	        	shazhiInfo = list.get(i);
	        	shazhimapper.addshazhifromexcel(shazhiInfo);
	        }
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}*/
	@Test
 	public void testAddzhizao(){
		ReadZhizaoExcel xlsMain = new ReadZhizaoExcel();
		  ZhizaoInfo zhizaoInfo = null;
	       List<ZhizaoInfo> zhizaolist = new ArrayList<ZhizaoInfo>();
		try {
			zhizaolist = xlsMain.readXls("D:/excel/zhizaodata.xls");
			for (int i = 0; i < zhizaolist.size(); i++) {
				zhizaoInfo = zhizaolist.get(i);
				zhizaomapper.addzhizaofromexcel(zhizaoInfo);
	        }
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
}

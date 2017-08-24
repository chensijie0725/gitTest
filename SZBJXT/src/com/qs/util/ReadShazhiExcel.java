package com.qs.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.qs.model.ShazhiInfo;

@SuppressWarnings("resource")
public class ReadShazhiExcel {
	  private static final Logger logger = Logger.getLogger(ReadShazhiExcel.class);
	 public List<ShazhiInfo> readXls(String filepath) throws IOException {
	        InputStream is = new FileInputStream(filepath); 
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	        ShazhiInfo shazhiInfo = null;
	        List<ShazhiInfo> list = new ArrayList<ShazhiInfo>();
	        // 循环工作表Sheet
	        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
	            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	            if (hssfSheet == null) {
	                continue;
	            }
	            // 循环行Row
	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                if (hssfRow != null) {
	                	shazhiInfo = new ShazhiInfo();
	                 //   HSSFCell no = hssfRow.getCell(0);
	                    HSSFCell yuanliao_id = hssfRow.getCell(1);
	                    HSSFCell shazhi = hssfRow.getCell(2);	                  
	                    shazhiInfo.setYuanliao_id(getValue(yuanliao_id));;
	                    shazhiInfo.setShazhi(getValue(shazhi));
	                    list.add(shazhiInfo);
	                    logger.info(shazhiInfo.toString());
	                }
	            }
	        }
	        return list;
	    }
	    
	     @SuppressWarnings("static-access")
	    private String getValue(HSSFCell hssfCell) {
	    	 	if(hssfCell==null){
	    		 return "";
	    	 	}
	            if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	                // 返回布尔类型的值
	                return String.valueOf(hssfCell.getBooleanCellValue());
	            } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	                // 返回数值类型的值
	                return String.valueOf(hssfCell.getNumericCellValue());
	            } else {
	                // 返回字符串类型的值
	                return String.valueOf(hssfCell.getStringCellValue());
	            }
	        }
}

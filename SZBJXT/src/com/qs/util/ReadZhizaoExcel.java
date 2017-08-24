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
import com.qs.model.ZhizaoInfo;

@SuppressWarnings("resource")
public class ReadZhizaoExcel {
	  private static final Logger logger = Logger.getLogger(ReadZhizaoExcel.class);
	 public List<ZhizaoInfo> readXls(String filepath) throws IOException {
	        InputStream is = new FileInputStream(filepath); 
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			ZhizaoInfo zhizaoInfo = null;
	        List<ZhizaoInfo> list = new ArrayList<ZhizaoInfo>();
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
	                	zhizaoInfo = new ZhizaoInfo();
	                 //   HSSFCell no = hssfRow.getCell(0);
	                    HSSFCell cloth_id = hssfRow.getCell(1);
	                    HSSFCell model = hssfRow.getCell(2);
	                    zhizaoInfo.setCloth_id(getValue(cloth_id));;
	                    zhizaoInfo.setModel(getValue(model));
	                    list.add(zhizaoInfo);
	                    logger.info(zhizaoInfo.toString());
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

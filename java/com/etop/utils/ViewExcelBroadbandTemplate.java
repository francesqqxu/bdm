package com.etop.utils;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.etop.pojo.FeeConfirm;

public class ViewExcelBroadbandTemplate  extends AbstractExcelView {
	
	private Properties columnNameMappings;
	
	public void setColumnNameMappings(Properties columnNameMappings) {
		this.columnNameMappings = columnNameMappings;
	}

	public void buildExcelDocument(Map model, HSSFWorkbook workbook,     
            HttpServletRequest request, HttpServletResponse response)     
            throws Exception {    
          
        String excelName = "宽带信息导入模板.xls";  
        // 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开  
        //response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition", "inline; filename="+ new String(excelName.getBytes("GB2312"), "ISO-8859-1"));    
          
        String[]  columnNames  =  (String[]) model.get("columnNames");     
        // 产生Excel表头  
        HSSFSheet sheet = workbook.createSheet("broadbandTemplate");  
        HSSFRow header = sheet.createRow(0); // 第0行  
        
        for(int i=0; i<columnNames.length; i++){
        	
        	header.createCell(i).setCellValue(findMatchingColumnName(columnNameMappings,columnNames[i]));
        	header.getCell(i).setCellType(HSSFCell.CELL_TYPE_STRING);
        }
	} 
	
	protected String findMatchingColumnName(Properties columnNameMappings, String columnName) {
		 String columnName_ch = null;
		 
		for (Enumeration<?> names = columnNameMappings.propertyNames(); names.hasMoreElements();) {
			    String columnMapping  =  (String)names.nextElement();
			    if(columnMapping.equals(columnName)){
				columnName_ch = columnNameMappings.getProperty(columnName);
			    }
		}
		
		return columnName_ch;
	}

	
}

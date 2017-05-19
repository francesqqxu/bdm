package com.etop.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.etop.pojo.FeeConfirmReport;  
  

/** 
* 生成excel视图，可用excel工具打开或者保存 
* 由ViewController的return new ModelAndView(viewExcel, model)生成 
*/  
public class ViewExcel extends AbstractExcelView {     
     
	//表头
	public static   final String[] tableHeader = {"宽带标识", "停用年月" , "城市", "线路类型", "运营商" , "接入方式"
    		, "接入宽带", "应缴费用", "营改增", "经办", "结算负责人", "付费方式", "结算周期(月)", "支付月", "费用归集"
    		, "本月费用", "本月扣税后费用" };
	 //表头单元格的数目
	public static final short cellNumber = (short)tableHeader.length;
	
    public void buildExcelDocument(Map model, HSSFWorkbook workbook,     
            HttpServletRequest request, HttpServletResponse response)     
            throws Exception {    
          
        String excelName = "宽带费用报表.xls";  
        // 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开  
        //response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition", "inline; filename="+ new String(excelName.getBytes("GB2312"), "ISO-8859-1"));    
          
        List feeConfirmList = (List) model.get("list");     
        
        // 产生Excel表 
        HSSFSheet sheet = workbook.createSheet("feeConfirmList");  
        
       //创建表头
        HSSFRow header = sheet.createRow(0); // 第0行  
        header.setHeight((short)1000);
        
        HSSFCellStyle headerStyle = (HSSFCellStyle) workbook.createCellStyle();
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont headerFont = (HSSFFont)workbook.createFont();
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setFontName("Times New Roman");
        headerStyle.setFont(headerFont);
       
        // 产生标题列 
       for(int i=0; i<cellNumber; i++){
    	   HSSFCell headerCell =  header.createCell(i);
    	   headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);	   
    	   headerCell.setCellValue(tableHeader[i]);
    	   headerCell.setCellStyle(headerStyle);
    	   
    	}
        
        
        HSSFCellStyle cellStyle = workbook.createCellStyle();  
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("mm/dd/yyyy"));  
  
        HSSFCellStyle  cellstyle_nofee = workbook.createCellStyle();
        HSSFFont cellFont = (HSSFFont)workbook.createFont();
        cellFont.setColor(HSSFColor.GREY_25_PERCENT.index);
        cellFont.setFontName("Times New Roman");
        cellstyle_nofee.setFont(cellFont);
        
        HSSFCellStyle cellstyle_double = workbook.createCellStyle();
        HSSFDataFormat df = workbook.createDataFormat();
        cellstyle_double.setDataFormat(df.getFormat("0.00"));
        
        
        double afterTaxFee = 0.00;
        
        
        // 填充数据  
        int rowNum = 1;  
        for (Iterator iter = feeConfirmList.iterator(); iter.hasNext();) {  
            FeeConfirmReport feeConfirmReport = (FeeConfirmReport) iter.next();  
            HSSFRow row = sheet.createRow(rowNum++);  
            
            if(null != feeConfirmReport.getBroadbandId() && !"".equals(feeConfirmReport.getBroadbandId()) ){
            	if(	"aaa".equals(feeConfirmReport.getBroadbandId())  ) {
            		row.createCell(0).setCellValue("");
            	}
            	else if (	"bbb".equals(feeConfirmReport.getBroadbandId())){
	            			row.createCell(0).setCellValue("");	
	            }
	            else {
	            			row.createCell(0) .setCellValue( feeConfirmReport.getBroadbandId().toString());  
	         	}
            }
            else {
            	row.createCell(0).setCellValue("");
            }
            
            if(null != feeConfirmReport.getStopDate() && !"".equals(feeConfirmReport.getStopDate())){
            	 row.createCell( 1).setCellValue(feeConfirmReport.getStopDate().toString());  
            }
            else{
            	row.createCell(1).setCellValue("");
            }
            row.getCell(1).setCellStyle(cellStyle);
            sheet.setColumnHidden(1, true);
            if(null != feeConfirmReport.getCity() && !"".equals(feeConfirmReport.getCity())){
            	row.createCell(2).setCellValue(feeConfirmReport.getCity().toString());
            }
            else{
            	row.createCell(2).setCellValue("");
            }
            if(null != feeConfirmReport.getLineType() && !"".equals(feeConfirmReport.getLineType())){
            	row.createCell(3).setCellValue(feeConfirmReport.getLineType().toString());
            	
            }else{
            	row.createCell(3).setCellValue("");
            }
            if(null != feeConfirmReport.getOperator() && !"".equals(feeConfirmReport.getOperator())){
            	row.createCell(4).setCellValue(feeConfirmReport.getOperator().toString());
            	
            }else{
            	row.createCell(4).setCellValue("");
            }
            if(null != feeConfirmReport.getAccessWay() && !"".equals(feeConfirmReport.getAccessWay())){
            	row.createCell(5).setCellValue(feeConfirmReport.getAccessWay().toString());
            	
            }else{
            	row.createCell(5).setCellValue("");
            }
            sheet.setColumnHidden(5, true);
            if(null != feeConfirmReport.getBandwidth()  && !"".equals(feeConfirmReport.getBandwidth())){
            	row.createCell(6).setCellValue(feeConfirmReport.getBandwidth().toString());
            	
            }else{
            	row.createCell(6).setCellValue("");
            }
            if(null != feeConfirmReport.getFee()  && !"".equals(feeConfirmReport.getFee()) && ! "0.00".equals(feeConfirmReport.getFee().toString())  ){
            	row.createCell(7).setCellValue(feeConfirmReport.getFee().doubleValue());
            	row.getCell(7).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            	
            }else{
            	row.createCell(7).setCellValue("");
            	//row.getCell(7).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            }
            if(null != feeConfirmReport.getValueAddTax()  && !"".equals(feeConfirmReport.getValueAddTax())
            		&& !"0.00".equals(feeConfirmReport.getValueAddTax().toString())){
            	row.createCell(8).setCellValue(feeConfirmReport.getValueAddTax().doubleValue());
            	row.getCell(8).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            }else{
            	row.createCell(8).setCellValue("");
            	//row.getCell(8).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            }
            if(null != feeConfirmReport.getAgent() && !"".equals(feeConfirmReport.getAgent())){
            	row.createCell(9).setCellValue(feeConfirmReport.getAgent().toString());
            }
            else{
            	row.createCell(9).setCellValue("");
            }
            sheet.setColumnHidden(9, true);
            if(null != feeConfirmReport.getDirector() && !"".equals(feeConfirmReport.getDirector())){
            	if(	"zzz".equals(feeConfirmReport.getDirector())  ) {
            		row.createCell(0).setCellValue("");
            	}else {
            		row.createCell(10).setCellValue(feeConfirmReport.getDirector().toString());
            	}
            }
            else{
            	row.createCell(10).setCellValue("");
            }
            
            if(null != feeConfirmReport.getPaymentMethod() && !"".equals(feeConfirmReport.getPaymentMethod())){
            	row.createCell(11).setCellValue( feeConfirmReport.getPaymentMethod() );
            }
            else{
            	row.createCell(11).setCellValue("");
            }
            
            if(null != feeConfirmReport.getSettlementCycle() && !"".equals(feeConfirmReport.getSettlementCycle().trim())){
            	row.createCell(12).setCellValue(Integer.parseInt(feeConfirmReport.getSettlementCycle()));
            	row.getCell(12).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            }
            else{
            	row.createCell(12).setCellValue("");
            	//row.getCell(12).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            }
            
            if(null != feeConfirmReport.getPaymentMonth() && !"".equals(feeConfirmReport.getPaymentMonth().trim())){
            	row.createCell(13).setCellValue(Integer.parseInt(feeConfirmReport.getPaymentMonth()));
            	row.getCell(13).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            }
            else{
            	row.createCell(13).setCellValue("");
            	//row.getCell(13).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            }
            
            if(null != feeConfirmReport.getFeeCollection() && !"".equals(feeConfirmReport.getFeeCollection())){
            	row.createCell(14).setCellValue(feeConfirmReport.getFeeCollection().toString());
            }
            else{
            	row.createCell(14).setCellValue("");
            }
            if(null != feeConfirmReport.getChangedFee()  && !"".equals(feeConfirmReport.getChangedFee())
            		  && !"0.00".equals(feeConfirmReport.getChangedFee().toString())){
            	row.createCell(15).setCellValue(feeConfirmReport.getChangedFee().doubleValue());
            	row.getCell(15).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            	
            }else{
            	row.createCell(15).setCellValue("");
            	//row.getCell(15).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            }
            
            if(null != feeConfirmReport.getAfterTaxFee()  && !"".equals(feeConfirmReport.getAfterTaxFee())
            		&& !"0.00".equals((feeConfirmReport.getAfterTaxFee().toString()))){
            	//row.createCell(16).setCellValue(feeConfirmReport.getAfterTaxFee().doubleValue());
            	//afterTaxFee = Double.parseDouble( df.format(  feeConfirmReport.getAfterTaxFee().doubleValue()));
            	//afterTaxFee = feeConfirmReport.getAfterTaxFee().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            	//afterTaxFee = Double.parseDouble(df.format(afterTaxFee));
            	afterTaxFee = Double.parseDouble(feeConfirmReport.getAfterTaxFee().toString());
            	 
            	row.createCell(16).setCellValue(afterTaxFee);
            	row.getCell(16).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            	row.getCell(16).setCellStyle(cellstyle_double);
            }else{
            	row.createCell(16).setCellValue("");
            	//row.getCell(16).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            }
//            if( !"aaa".equals(feeConfirmReport.getBroadbandId()) && !"bbb".equals(feeConfirmReport.getBroadbandId())){
//	            if(null == feeConfirmReport.getChangedFee()   || "".equals(feeConfirmReport.getChangedFee())){
//	                for(int i=0; i<cellNumber; i++){
//	                	row.getCell(i).setCellStyle(cellstyle_nofee);
//	                }
//	            }
//            }
//            if(null != feeConfirmReport.getChangedFee() && !"".equals(feeConfirmReport.getChangedFee())){
//	            if(feeConfirmReport.getChangedFee().compareTo(BigDecimal.valueOf(0.00)) == 0  ){
//	            	 row.createCell(2)  
//	                 .setCellValue(feeConfirmReport.getFee().toString());  
//	            }
//	            else{
//	            	row.createCell(2).setCellValue(feeConfirmReport.getChangedFee().toString());
//	            }
//            }
           
        }  
  
        // 列总和计算  
//        HSSFRow row = sheet.createRow(rowNum);  
//        row.createCell((short) 0).setCellValue("TOTAL:");  
//        String formual = "SUM(D2:D" + rowNum + ")"; // D2到D[rowNum]单元格起(count数据)  
//        row.createCell((short) 3).setCellFormula(formual); 
        
//           OutputStream outputStream = response.getOutputStream();
//           workbook.write(outputStream);
//           outputStream.flush();
//           outputStream.close();
    }     
    
    
}  
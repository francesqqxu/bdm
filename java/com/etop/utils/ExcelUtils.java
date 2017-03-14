package com.etop.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
  
/** 
 * 读取Excel 
 *  
 * @author zengwendong 
 */  
public class ExcelUtils {  
    private Logger logger = LoggerFactory.getLogger(ExcelUtils.class);  
    private Workbook wb;  
    private Sheet sheet;  
    private Row row;  
  
    public ExcelUtils(String filepath) {  
        if(filepath==null){  
            return;  
        }  
        String ext = filepath.substring(filepath.lastIndexOf("."));  
        try {  
            InputStream is = new FileInputStream(filepath);  
            wb = WorkbookFactory.create(is);
            System.out.println(wb instanceof XSSFWorkbook); 
          
        } catch (FileNotFoundException e) {  
            logger.error("FileNotFoundException", e);  
        } catch (IOException e) {  
            logger.error("IOException", e);  
        } catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }  
    
    public ExcelUtils(InputStream in){
    	if(null != in){
    		try {
				wb = WorkbookFactory.create(in);
			} catch (InvalidFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    @SuppressWarnings("unchecked")
//    public static <T> List<T> transToObject(String fullFilePath,Class<T> clz) throws Exception{
//        InputStream is = null;
//        try {
//            is = new FileInputStream(fullFilePath);
//            Workbook wb = ReaderFileUtil.loadWorkBookByPath(fullFilePath,is);
//            Sheet sheet = wb.getSheetAt(0);
//            Map<String,Object> map = readExcelTitle(sheet);
//            int start = Integer.parseInt(map.get(START_INDEX).toString());
//            List<String> titles = (List<String>)map.get(TITLES);
//            int last = sheet.getLastRowNum();
//            List<T> lst = new ArrayList<T>();
//            for(int i = (start+1);i<=last;i++){
//                List<String> values = getRowValues(sheet, i);
//                T t = transToObject(titles, values, clz);
//                lst.add(t);
//            }
//            return lst;
//        } catch (Exception e) {
//            throw e;
//        }finally{
//            if(is!=null){
//                is.close();
//            }
//        }
//    }
     
    public static <T> T transToObject(List<String> titles,List<Object> values,Class<T> clz) throws Exception{
        T t = clz.newInstance();
        int size = titles.size();
        for(int i = 0;i<size;i++){
            if(values.size()<=i){
                break;
            }
            String title = titles.get(i).trim();
            Object value = values.get(i);
            Double temp_double = 0.00;
            Integer temp_int =0;
            if(title.equals("settlementCycle") || title.equals("paymentMonth")){
            	if(null != value && !"".equals(value.toString())){
	            	temp_double = Double.parseDouble(value.toString());
	            	temp_int = ( (Double) (temp_double * 100/100)).intValue();
	            	value =  temp_int.toString();
            	}
            }
            setValue(t,clz,title,value);
        }
        return t;
    }
     
    private static void setValue(Object o,Class<?>clz,String title,Object value) throws Exception{
        Method m = null;
//        if(title.indexOf(".")!=-1){
//            String[] titleSplit = title.split("\\.");
//            m = getSetMethod(titleSplit<a href="http://www.oschina.net/js/ke/plugins/emoticons/images/0.gif" target="_blank"><img src="http://www.oschina.net/js/ke/plugins/emoticons/images/0.gif" alt="" style="cursor: pointer;"></a>, clz);
//        }else{
            m = getSetMethod(title, clz);
//        }
//        if(m == null){
//            logger.info(title+"在"+clz.getName()+"中不存在");
//            return;
//        }
        setValue(o, m, title,value);
    }
     
    private static void setValue(Object o,Method method,String title,Object value) throws Exception{
        Class<?>[] clazz = method.getParameterTypes();  
       // String type = clazz[0].getName();
        if(null == value || "".equals(value)){
            return;
        }
        method.invoke(o, ConvertUtils.convert(value,clazz[0] ));
       
//        if("java.lang.String".equals(type)){
//            method.invoke(o, value);
//        }else if("java.util.Date".equals(type)){
//            Date d = null;
//            if(value.length()>10){
//                d = ConvertUtils.convertObj2Time(value);
//               
//            }else{
//                d = ConvertUtil.convertObj2Date(value);
//            }
//            method.invoke(o, d);
//        }else if("java.lang.Integer".equals(type)||"int".equals(type)){
//            Integer i = ConvertUtil.convertObj2Int(value);
//            method.invoke(o, i);
//        }else if("java.lang.Long".equals(type)||"long".equals(type)){
//            Long l = ConvertUtil.convertObj2Long(value);
//            method.invoke(o, l);
//        }else if("java.lang.Short".equals(type)||"short".equals(type)){
//            Short s = ConvertUtil.convertObj2Short(value);
//            method.invoke(o, s);
//        }else if("java.lang.Boolean".equals(type)||"boolean".equals(type)){
//            Boolean b = ConvertUtil.convertObj2Boolean(value);
//            method.invoke(o, b);
//        }else if("java.math.BigDecimal".equals(type)){
//            BigDecimal b = ConvertUtil.convertObj2BigDecimal(value);
//            method.invoke(o, b);
//        }else{
//            Method getMethodName = o.getClass().getMethod(method.getName().replace("set", "get"));
//            Object returnValue = getMethodName.invoke(o);
//            Class<?> returnClass = Class.forName(type);
//            if(returnValue == null){
//                returnValue = returnClass.newInstance();
//                method.invoke(o, returnValue);
//            }
//            title = title.substring(title.indexOf(".")+1);
//            setValue(returnValue, returnClass, title, value);
//        }
    }
     
    private static Method getSetMethod(String propName,Class<?> clz){
        Method[]methods = clz.getMethods();
        for(Method method : methods){
            if(method.getName().toLowerCase().equals("set"+propName.toLowerCase())){
                Class<?>[] clazz = method.getParameterTypes();
                if(clazz.length == 1){
                    return method;
                }
            }
        }
        return null;
    }
     
    
      
    /** 
     * 读取Excel表格表头的内容 
     *  
     * @param InputStream 
     * @return String 表头内容的数组 
     * @author zengwendong 
     */  
    public String[] readExcelTitle() throws Exception{  
        if(wb==null){  
            throw new Exception("Workbook对象为空！");  
        }  
        sheet = wb.getSheetAt(0);  
        row = sheet.getRow(0);  
        // 标题总列数  
        int colNum = row.getPhysicalNumberOfCells();  
        System.out.println("colNum:" + colNum);  
        String[] title = new String[colNum];  
        for (int i = 0; i < colNum; i++) {  
             title[i] =(String)getCellFormatValue(row.getCell((short) i));  
            //title[i] = row.getCell(i).getCellFormula();  
        }  
        return title;  
    }  
  
    public List<String> transferToColumnTitle(List<String> titles) {
    	Map  titlesMap = new HashMap();
    	titlesMap.put("标识","broadbandId");
    	titlesMap.put("停用年月","stopDate");
    	titlesMap.put("城市","city");
    	titlesMap.put("状态","status");
    	titlesMap.put("缴费单位","payOrganization");
    	titlesMap.put("场地地址","address");
    	titlesMap.put("线路类型","lineType");
    	titlesMap.put("链接地址","linkAddress");
    	titlesMap.put("运营商","operator");
    	titlesMap.put("接入方式","accessWay");
    	titlesMap.put("接入宽带","bandwidth");
    	titlesMap.put("应缴费用","fee");
    	titlesMap.put("营改增","valueAddTax");
    	titlesMap.put("经办","agent");
    	titlesMap.put("结算负责","director");
    	titlesMap.put("付费方式","paymentMethod");
    	titlesMap.put("结算周期(月)","settlementCycle");
    	titlesMap.put("支付月","paymentMonth");
    	titlesMap.put("费用归集","feeCollection");
    	titlesMap.put("网络利用率","useRatio");
    	titlesMap.put("网络使用","networkUsage");
    	    	
    	List<String>titlesColumn = new ArrayList<String>();
    	
    	for(int i=0; i <titles.size(); i++){
    		
    		Iterator entries = titlesMap.entrySet().iterator();
    		while(entries.hasNext()){
    			Map.Entry   entry = (Map.Entry) entries.next();
    			if(entry.getKey().equals(titles.get(i))){
    			      titlesColumn.add(i, (String)entry.getValue());
    			}
    					
    		}
    	}
    	return titlesColumn;
    }
     
    
    /** 
     * 读取Excel数据内容 
     *  
     * @param InputStream 
     * @return Map 包含单元格数据内容的Map对象 
     * @author zengwendong 
     */  
    public Map<Integer, Map<Integer,Object>> readExcelContent() throws Exception{  
        if(wb==null){  
            throw new Exception("Workbook对象为空！");  
        }  
        Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();  
          
        sheet = wb.getSheetAt(0);  
        // 得到总行数  
        int rowNum = sheet.getLastRowNum();  
        row = sheet.getRow(0);  
        int colNum = row.getPhysicalNumberOfCells();  
        // 正文内容应该从第二行开始,第一行为表头的标题  
        for (int i = 1; i <= rowNum; i++) {  
            row = sheet.getRow(i);  
            int j = 0;  
            Map<Integer,Object> cellValue = new HashMap<Integer, Object>();  
            while (j < colNum) {  
                Object obj = getCellFormatValue(row.getCell(j));  
                cellValue.put(j, obj);  
                j++;  
            }  
            content.put(i, cellValue);  
        }  
        return content;  
    }  
  
    /** 
     *  
     * 根据Cell类型设置数据 
     *  
     * @param cell 
     * @return 
     * @author zengwendong 
     */  
    private Object getCellFormatValue(Cell cell) {  
        Object cellvalue = "";  
        if (cell != null) {  
            // 判断当前Cell的Type  
            switch (cell.getCellType()) {  
            case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC  
            	 // 判断当前的cell是否为Date  
                if (DateUtil.isCellDateFormatted(cell)) {  
                    // 如果是Date类型则，转化为Data格式  
                    // data格式是带时分秒的：2013-7-10 0:00:00  
                    // cellvalue = cell.getDateCellValue().toLocaleString();  
                    // data格式是不带带时分秒的：2013-7-10  
                    Date date = cell.getDateCellValue();  
                    cellvalue = date;  
                } else {// 如果是纯数字  
  
                    // 取得当前Cell的数值  
                	DecimalFormat df = new DecimalFormat("#.00");
                	cellvalue = df.format(cell.getNumericCellValue());
                  //  cellvalue = String.valueOf(cell.getNumericCellValue());  
                }  
                break;  
            case Cell.CELL_TYPE_FORMULA: 
                 cellvalue = cell.getCellFormula();
                 break;
            case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING  
                // 取得当前的Cell字符串  
                cellvalue = cell.getRichStringCellValue().getString();  
                break;  
            case Cell.CELL_TYPE_BLANK:     
            	cellvalue = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN: 
            	cellvalue = cell.getBooleanCellValue();
            	break;
            case Cell.CELL_TYPE_ERROR: 
            	cellvalue = "";
            	break;
            default:// 默认的Cell值  
                cellvalue = "";  
            }  
        } else {  
            cellvalue = "";  
        }  
        return cellvalue;  
    }  
  
    public static void main(String[] args) {  
        try {  
            String filepath = "d:\\宽带信息06.xlsx";  
            ExcelUtils excelReader = new ExcelUtils(filepath);  
            // 对读取Excel表格标题测试  
//          String[] title = excelReader.readExcelTitle();  
//          System.out.println("获得Excel表格的标题:");  
//          for (String s : title) {  
//              System.out.print(s + " ");  
//          }  
              
            // 对读取Excel表格内容测试  
            Map<Integer, Map<Integer,Object>> map = excelReader.readExcelContent();  
            System.out.println("获得Excel表格的内容:");  
            for (int i = 1; i <= map.size(); i++) {  
                System.out.println(map.get(i));  
            }  
        } catch (FileNotFoundException e) {  
            System.out.println("未找到指定路径的文件!");  
            e.printStackTrace();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  
package com.etop.service;

import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.etop.dao.BroadbandDAO;
import com.etop.dto.BroadbandDto;
import com.etop.pojo.Broadband;
import com.etop.utils.ExcelUtils;
import com.etop.utils.PageUtil;

/**
 * @类名： BroadbandService
 * @description: broadband管理服务
 * @date： 2016-03-29
 * @author frances.xu
 *
 */

@SuppressWarnings("serial")
@Service("BroadbandService")
public class BroadbandService implements Serializable {
	
	private final  static Logger log = Logger.getLogger(BroadbandService.class);
	Map<String , Object>  map = new HashMap<String, Object>();
	
	@Autowired
	private BroadbandDAO broadbandDAO;
	
	public PageUtil<BroadbandDto> findBroadbands(String order, int  page, int rows, String sort, String city, String feeCollection, String director) {
		String sql = "";
		int  first = rows * ( page -1 );
		
		String  sWhere = "";
		if(null != city && !"".equals(city)){
			sWhere =  " and  city like '%" +city + "%'";
		}
		if(null != feeCollection && !"".equals(feeCollection)){
			sWhere +=  " and feeCollection  like '%" +feeCollection + "%'";
		}
		
		if(null != director && !"".equals(director)){
			sWhere +=  " and director  like '%" +director + "%'";
		}
		sql = "select  * from t_broadband where 1=1 " + sWhere +  " order by " + " " + sort + " " + order + " limit " + first + "," + rows;
        log.info(sql);
        return broadbandDAO.findBySql(sql, Broadband.class, false);
        
	}
	
	public PageUtil<BroadbandDto> findBroadbandsByName(String order, int  page, int rows, String sort, String city, String feeCollection, String chineseName) {
		String sql = "";
		int  first = rows * ( page -1 );
		
		String  sWhere = "";
		if(null != city && !"".equals(city)){
			sWhere =  " and  city like '%" +city + "%'";
		}
		if(null != feeCollection && !"".equals(feeCollection)){
			sWhere +=  " and feeCollection like '%" + feeCollection + "%'";
		}
		sql = "select * from t_broadband where 1=1 and director = '" + chineseName + "'  " + sWhere +  " order by " + " " + sort + " " + order + " limit " + first + "," + rows;
        log.info(sql);
		return broadbandDAO.findBySql(sql, Broadband.class, false);
		
	}
	
	public void addBroadband(BroadbandDto broadbandDto) throws ParseException{
		Broadband  broadband = new Broadband();
		BeanUtils.copyProperties(broadbandDto, broadband);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String stopDate = broadbandDto.getStopDate();
		if(null != stopDate && !"".equals(stopDate)){
			broadband.setStopDate(sdf.parse(stopDate));
			broadband.setStatus("停");
		}
		else{
			broadband.setStatus("");
		}
		if(broadband.getSettlementCycle().equals("1")){
			broadband.setPaymentMonth("1");
		}
		broadbandDAO.save(broadband);
		// new RuntimeException("msg");
	}
	
	public Object updateBroadband(BroadbandDto broadbandDto) throws ParseException{
		map.clear();
		Broadband broadband = findBroadbandById(broadbandDto.getId());
	    if(null == broadband || "".equals(broadband)){
	    	map.put("success", false);
	    	return map;
	    }
	    BeanUtils.copyProperties(broadbandDto, broadband);
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String stopDate = broadbandDto.getStopDate();
		if(null != stopDate && !"".equals(stopDate)){
			broadband.setStopDate(sdf.parse(stopDate));
			broadband.setStatus("停");
		}
		else{
			broadband.setStatus("");
			broadband.setStopDate(null);
		}
		if(broadband.getSettlementCycle().equals("1")){
			broadband.setPaymentMonth("1");
		}
		
	    broadbandDAO.update(broadband);
	    map.put("success",true);
	    return map;
	}
	
	public Broadband findBroadbandById(int id){
		Map<String , Object> params = new HashMap<String, Object>();
		params.put("id",id);
		String sql = " from Broadband b where b.id = :id";
		return broadbandDAO.findUniqueResult(sql, params);
	}
	
	public Broadband findBroadbandByBroadbandId(String broadbandId){
		Map<String , Object> params = new HashMap<String, Object>();
		params.put("broadbandId",broadbandId);
		String sql = " from Broadband b where b.broadbandId = :broadbandId";

		return broadbandDAO.findUniqueResult(sql, params);
		
	}
	
	
	public Object deleteBroadbands(String ids){
		log.info(ids);
		String[]  idArr = ids.split(",");
		Integer[] idIArr = new Integer[idArr.length];
		for(int i=0; i <idArr.length; i++){
			idIArr[i] = Integer.parseInt(idArr[i]);
		}
		Map<String, Object >  params = new HashMap<String, Object>();
		params.put("ids", idIArr);
		String sql  = "delete from Broadband  where id in (:ids)";
		//String sql = "delete from t_broadband where id in (:ids)"; 
		int result = broadbandDAO.excute(sql, params);
		map.clear();
		map.put("success",true);
		return map;
	}
	
	public Object deleteAllBroadbands(){
		map.clear();
		String sql = "delete from Broadband";
		broadbandDAO.excute(sql);
		map.put("success", true);
		return map;
	}
	
	/**
	 * ajaxfileupload.js 在上传文件时， 调整为强制返回json数据， 这样前台可以处理返回数据
	 * @param file
	 * @param request
	 * @return
	 */
	public Object addBroadbands(MultipartFile  file, HttpServletRequest request)  {
		
		String uploadFilename  = "";
		uploadFilename = file.getOriginalFilename();
		InputStream in =null;
		map.clear();
		
	try{
			in = file.getInputStream();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			MultipartFile multipartFile = multipartRequest.getFile("file"); 
			ExcelUtils excelUtils = new ExcelUtils(in);
		    List<String>  titles = java.util.Arrays.asList( excelUtils.readExcelTitle());
			List<String> titlesColumn= excelUtils.transferToColumnTitle(titles);
			Map<Integer, Map<Integer, Object >> content = excelUtils.readExcelContent();
			List<Broadband>  broadbands = new ArrayList<Broadband>();
			
			for(int i=1; i<=content.size(); i++){
			   Map<Integer,Object> cellValue = content.get(i);
			   Broadband broadband = new Broadband();
			   List<Object> cellValues = new ArrayList<Object>();
			   for(int j=0;j<cellValue.size(); j++){
				   cellValues.add(cellValue.get(j));
			   }
			   broadband = ExcelUtils.transToObject(titlesColumn, cellValues, Broadband.class);
			   if (broadband.getSettlementCycle().equals("1" )) {
				   broadband.setPaymentMonth("1");
			   }
					   
			   
	           broadbands.add(broadband);
			}
			broadbandDAO.addBraodbands(broadbands);
			map.put("success", true);
		}catch(Exception ex){
			map.put("success", false);
			map.put("msg","导入宽带信息出错，请联系管理人员！Detail Message: "+ ex.getMessage() );
			return map;
		}
		 return map;
	}

}

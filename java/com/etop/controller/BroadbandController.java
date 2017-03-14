package com.etop.controller;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.etop.basic.controller.BaseController;
import com.etop.dto.BroadbandDto;
import com.etop.pojo.Broadband;
import com.etop.pojo.User;
import com.etop.service.BroadbandService;
import com.etop.utils.EntityUtil;
import com.etop.utils.ExcelUtils;
import com.etop.utils.PageUtil;



/**
 * @类名： BandWidthController
 * @description:  宽带管理控制器
 * @date：2016-03-29
 * @author frances.xu
 *
 */


@Controller
@RequestMapping("/broadband")
public class BroadbandController extends BaseController {
	
    Map<String , Object>  map = new HashMap<String, Object>();
	private final static Logger log = Logger.getLogger(BroadbandController.class);
	
	@Autowired
	private BroadbandService broadbandService;
	
	@RequestMapping("/broadbandList.html")
	public String getBroadbands() {
		log.info("/broadbandList.html");
		return "broadbandList";
	}
	
	@RequestMapping("/broadbands_import.html")
	public Object broadbandsImport() {
		log.info("/broadbands_import.html");
	   	return "broadbandFileUpload";
	}
	
	@RequestMapping("/get_broadbands.html")
	@ResponseBody
	public Object getBroadbandList(@RequestParam("order") String order, @RequestParam("page") int page, @RequestParam("rows")  int rows
			, @RequestParam("sort") String sort , @RequestParam("city") String city, @RequestParam("feeCollection") String feeCollection
		    , @RequestParam("director") String director) {
		log.info("/get_broadbands.html");
		PageUtil<BroadbandDto> broadbandList = broadbandService.findBroadbands(order, page, rows, sort, city, feeCollection, director);
		//throw new RuntimeException("msg");
		return broadbandList;
	}
	
	@RequestMapping("/get_broadbands_byUser.html")
	@ResponseBody
	public Object getBroadbandListByUser(@RequestParam("order") String order, @RequestParam("page") int page, @RequestParam("rows")  int rows
			, @RequestParam("sort") String sort , @RequestParam("city") String city, @RequestParam("feeCollection") String feeCollection, HttpServletRequest request ) {
		log.info("/get_broadbands_byUser.html");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		PageUtil<BroadbandDto> broadbandList = broadbandService.findBroadbandsByName(order, page, rows, sort, city, feeCollection, user.getChineseName());
		return broadbandList;
	}
	
	@RequestMapping("/add.html")
	@ResponseBody
    public Object addBroadband(BroadbandDto broadbandDto  ) throws ParseException{
		log.info("/add.html");
		broadbandService.addBroadband(broadbandDto);
    	map.put("success", true);
    	return map;
    }
	
	@RequestMapping("/edit.html")
	@ResponseBody
	public Object updateBroadband(BroadbandDto broadbandDto) throws ParseException{
		log.info("/edit.html");
		
		return broadbandService.updateBroadband(broadbandDto);
	    
	}
	@RequestMapping("/get_broadband.html")
	@ResponseBody
	public Object getBroadband(int id ){
	     log.info("/get_broadband.html");
	     
	     return broadbandService.findBroadbandById(id);
	}
	
	@RequestMapping("/del.html")
	@ResponseBody
	public Object delBroadband(String ids){
		log.info("del.html");
		return broadbandService.deleteBroadbands(ids);
	}
	
	@RequestMapping("/delAll.html")
	@ResponseBody
	public Object delBroadband(){
		log.info("del.html");
		return broadbandService.deleteAllBroadbands();
	}
	
	@RequestMapping("/fileUpload.html")
	public String fileUpload(){
		log.info("/fileUpload.html");
		return "fileUpload";
	}
	
	
	@RequestMapping("/fileUploading.html")
	@ResponseBody
	public Object fileUploading(@RequestParam("fileToUpload") MultipartFile file, HttpServletRequest request) throws Exception{
    	return broadbandService.addBroadbands(file, request);
	}
	
	@RequestMapping("/exportBroadbandTemplate.html")
	public  String exportFeeConfirm(ModelMap mm,
			HttpServletRequest request, HttpServletResponse response){
		  log.info("exportBroadbandTemplate.html");
		  EntityUtil  entityUtil = new EntityUtil();
		  mm.addAttribute("columnNames",entityUtil.getColumnNames(Broadband.class));
		  //model.put("list", feeConfirmService.getFeeConfirmReportList(dateFrom, dateTo));
		  return "viewExcelBroadbandTemplate";
		  //return new ModelAndView(new ViewExcel(), model);
	}
	
	
}

package com.etop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.basic.controller.BaseController;
import com.etop.dto.FeeConfirmDto;
import com.etop.pojo.FeeConfirm;
import com.etop.pojo.User;
import com.etop.service.FeeConfirmService;
import com.etop.utils.PageUtil;

/**
 * 
 * @author frances.xu
 *
 */
@Controller
@RequestMapping("/feeConfirm")
public class FeeConfirmController extends BaseController{
	
	Map<String , Object>  map = new HashMap<String, Object>();
	private final static Logger log = Logger.getLogger(FeeConfirmController.class);
	 private final static String rptTypeStaticInfo = "rptInfo";
	 private final static String rptTypeMonthFee = "rptTotal";
	
	
	@Autowired
	private FeeConfirmService feeConfirmService;
	
	@RequestMapping("/feeConfirmSponsor.html")
	public String  feeConfirmSponsor(){
		log.info("/feeConfirmSponsor.html");
		return "feeConfirmSponsor";
	}
	
	@RequestMapping("/get_feeConfirmSponsors.html")
	@ResponseBody
	public Object getFeeConfirmSponsors(@RequestParam("order") String order, @RequestParam("page") int page, @RequestParam("rows") int rows,
			@RequestParam("sort") String sort,	@RequestParam("feeDate") String feeDate){
		log.info("/get_feeConfirmSponsors.html");
		
		PageUtil<FeeConfirmDto>   feeConfirmList = feeConfirmService.getFeeConfirmSponsor(order,page,rows,sort, feeDate);
		return feeConfirmList;
	}
	
	@RequestMapping("/submit_feeConfirmSponsors.html")
	@ResponseBody
	public Object submitFeeConfirmSponsors(@RequestParam("feeDate") String feeDate){
		log.info("/submit_feeConfirmSponsors.html");
		
		return feeConfirmService.addFeeConfirmSponsor(feeDate);
 
	}
	
	
	@RequestMapping("/feeConfirming.html")
	public String  feeConfirming(){
		log.info("/feeConfirming.html");
		return "feeConfirming";
	}
	
	@RequestMapping("/get_feeConfirms.html")
	@ResponseBody
	public Object getFeeConfirms(@RequestParam("order") String order, @RequestParam("page") int page, @RequestParam("rows") int rows,
			@RequestParam("sort") String sort,	@RequestParam("feeDate") String feeDate, HttpServletRequest request ){
		log.info("/get_feeConfirms.html");
		User user = (User)request.getSession().getAttribute("user");
		PageUtil<FeeConfirmDto>   feeConfirmList = feeConfirmService.findFeeConfirms(order,page,rows,sort, feeDate,user.getChineseName());
		return feeConfirmList;
	}
	
	@RequestMapping("/addFeeConfirms.html")
	@ResponseBody
	public Object addFeeConfirms(){
		log.info("/addFeeConfirms.html");
		return feeConfirmService.addFeeConfirms();
	}
	
	@RequestMapping("/delFeeConfirms.html")
	@ResponseBody
	public Object delFeeConfirms(String feeDate) {
		log.info("/del.html");
		return feeConfirmService.deleteFeeConfirms(feeDate);
	}
	
	@RequestMapping("/edit.html")
	@ResponseBody
	public Object editFeeConfirm(FeeConfirmDto feeConfirmDto) {
		log.info("edit.html");
		
		return feeConfirmService.updateFeeConfirm( feeConfirmDto);
		
	}
	@RequestMapping("/get_feeConfirm.html")
	@ResponseBody
	public Object getFeeConfirm(int id){
		log.info("/get_feeConfirm.html");
		return feeConfirmService.findFeeConfirmById(id);
	}
	
	@RequestMapping("/confirm.html")
	@ResponseBody
	public Object confirm(String ids, String feeDate){
		log.info("confirm.html");
		return feeConfirmService.updateConfirm(ids, feeDate);
	}
	
	@RequestMapping("/feeConfirmReport.html")
	public String feeConfirmReport(){
		log.info("feeConfirmReport.html");
		return "feeConfirmReport";
	}
	
	@RequestMapping("/get_feeConfirmReport.html")
	@ResponseBody
	public Object get_feeConfirmReport(@RequestParam("order") String order, @RequestParam("page") int page, @RequestParam("rows") int rows,
			@RequestParam("sort") String sort,	@RequestParam("dateFrom") String dateFrom , @RequestParam("dateTo") String dateTo, @RequestParam("rptType") String rptType) {
		log.info("/get_feeConfirmReport.html");
		log.info("dateFrom" + dateFrom);
		log.info("dateTo" + dateTo);
		return  feeConfirmService.getFeeConfirmReport( order, page, rows, sort, dateFrom, dateTo, rptType);
	}
	
	@RequestMapping("/checkFeeConfirmReport.html")
	@ResponseBody
	public Object checkFeeConfirmReport(@RequestParam("dateFrom") String dateFrom , @RequestParam("dateTo") String dateTo) {
		log.info("/checkFeeConfirmReport.html");
		log.info("dateFrom" + dateFrom);
		log.info("dateTo" + dateTo);
		return  feeConfirmService.checkFeeConfirmReport( dateFrom, dateTo);
	}
	
	@RequestMapping("/exportFeeConfirm.html")
	public  String exportFeeConfirm(ModelMap mm,@RequestParam("dateFrom") String dateFrom,
			@RequestParam("dateTo") String dateTo, @RequestParam("rptType") String rptType,
			HttpServletRequest request, HttpServletResponse response){
		  log.info("exportFeeConfirm.html");
		  mm.addAttribute("list",feeConfirmService.getFeeConfirmReportList(dateFrom, dateTo, rptType));
		  //model.put("list", feeConfirmService.getFeeConfirmReportList(dateFrom, dateTo));
		  if(rptType.equals(rptTypeMonthFee)){
			  return "viewExcel";
		  }
		  else if(rptType.equals(rptTypeStaticInfo)){
			  return "viewExcelStaticInfo";
		  }
		  return  "viewExcel";
		  //return new ModelAndView(new ViewExcel(), model);
	}
	
	@RequestMapping("/test.html")
	public String test(){
		return "test.jsp";
	}
	
	@RequestMapping("/testReport.html")
	public  String testReport(ModelMap mm,@RequestParam("dateFrom") String dateFrom,
			@RequestParam("dateTo") String dateTo,@RequestParam("rptCheck") String rptCheck,
			HttpServletRequest request, HttpServletResponse response){
		  mm.addAttribute("list",feeConfirmService.getFeeConfirmReportList(dateFrom, dateTo, rptCheck ));
		  //model.put("list", feeConfirmService.getFeeConfirmReportList(dateFrom, dateTo));
		  return "viewExcel";
		  //return new ModelAndView(new ViewExcel(), model);
	}
	
}

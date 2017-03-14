package com.etop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.service.DeptService;

/**
 * 
 * @author frances.xu
 *
 */
@Controller
@RequestMapping("/dept")
public class DeptController {
	
	public final static Logger log = Logger.getLogger(DeptController.class);
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("/get_dept.html")
	@ResponseBody
	public Object get_dept() {
	    log.info("get_dept.html");
		return  deptService.getDept();
		
	}
	
	

}

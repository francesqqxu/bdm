package com.etop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.service.OrganizationService;

/**
 * 
 * 
 * @author frances.xu
 *
 */

@Controller
@RequestMapping("/org")
public class OrganizationController {
	
	public static final  Logger log = Logger.getLogger(OrganizationController.class);
	
	@Autowired
	private OrganizationService organizationService;
	
	@RequestMapping("/get_org.html")
	@ResponseBody
	public Object get_org() {
	    log.info("get_org.html");
		return  organizationService.getOrg();
		
	}
	
	
}

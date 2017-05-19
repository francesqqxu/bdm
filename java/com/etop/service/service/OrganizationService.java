package com.etop.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.dao.OrganizationDAO;
import com.etop.pojo.Organization;

/**
 * 
 * 
 * @author frances.xu
 *
 */

@SuppressWarnings("serial")
@Service("OrganizationService")
public class OrganizationService implements Serializable {
	
	public static final Logger log =  Logger.getLogger(OrganizationService.class);
	@Autowired
    private OrganizationDAO organizationDAO;
	
	public  List<Organization>  getOrg() {
		
		List<Organization> orgs = new ArrayList();
		
		return organizationDAO.find("from Organization");
	}
	
}

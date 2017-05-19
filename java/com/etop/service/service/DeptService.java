package com.etop.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.dao.DeptDAO;
import com.etop.pojo.Dept;

/**
 * 
 * @author frances.xu
 *
 */
@SuppressWarnings("serial")
@Service("DeptService")
public class DeptService implements Serializable  {

	public final static Logger log =   Logger.getLogger(DeptService.class);
	
	@Autowired
	private  DeptDAO deptDAO;
	
	public  List<Dept>  getDept() {
	   List <Dept>  depts = new ArrayList();
	
	   depts = deptDAO.find("from Dept");
	      
	   return depts;
	}
	
	

	
	
}

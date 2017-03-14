package com.etop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.etop.basic.dao.BaseDAO;
import com.etop.pojo.FeeConfirmReport;

@SuppressWarnings("serial")
@Repository("FeeConfirmReportDAO")
public class FeeConfirmReportDAO extends BaseDAO<FeeConfirmReport> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public  List<FeeConfirmReport>  findFeeConfirmReport(String sql){
		
		return jdbcTemplate.query(sql,  new BeanPropertyRowMapper(FeeConfirmReport.class));
	}
	

}

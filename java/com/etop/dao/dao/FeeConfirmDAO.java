package com.etop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.etop.basic.dao.BaseDAO;
import com.etop.dto.FeeConfirmReportDto;
import com.etop.pojo.FeeConfirm;

/**
 * 
 * @author frances.xu
 *
 */
@Repository("FeeConfirmDAO")
public class FeeConfirmDAO extends BaseDAO<FeeConfirm> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void executeSql(String sql, Object[] params, int[] types){
		jdbcTemplate.update(sql, params, types);
	}
	public void executeSql(String sql, Object[] params){
		jdbcTemplate.update(sql,params);
	}
	
	public void executeSql(String sql){
		jdbcTemplate.update(sql);
	}
	
	public Integer uniqueResult(String sql, Object[] params){
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
	
	public List<FeeConfirmReportDto> getFeeConfirmReport(String sql){
		return jdbcTemplate.query(sql , new RowMapper<FeeConfirmReportDto>(){
			public FeeConfirmReportDto mapRow(ResultSet rs, int index)  throws SQLException {
				FeeConfirmReportDto  feeConfirmReport = new FeeConfirmReportDto();
				feeConfirmReport.setBroadbandId(rs.getString("broadbandId"));
				feeConfirmReport.setStopDate(rs.getString("stopDate"));
				feeConfirmReport.setCity(rs.getString("city"));
				feeConfirmReport.setStatus(rs.getString("status"));
				feeConfirmReport.setPayOrganization(rs.getString("payOrganizaiton"));
				feeConfirmReport.setAccessWay(rs.getString("address"));
				
				return feeConfirmReport;
				
			}
			
		});
		
		
	}
	
	
	
}

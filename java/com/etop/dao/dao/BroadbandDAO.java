package com.etop.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.etop.basic.dao.BaseDAO;
import com.etop.pojo.Broadband;

/**
 * @类名： BroadbandDAO
 * @description: 
 * @date: 2016-03-29
 * @author frances.xu
 *
 */

@SuppressWarnings("serial")
@Repository("BroadbandDAO")
public class BroadbandDAO extends BaseDAO<Broadband> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void addBraodbands(List<Broadband> broadbands)  {
		
		final String sql = "INSERT INTO t_broadband(broadbandId,stopDate,city,status, payOrganization, address,lineType,"
				+ "linkAddress,operator, accessWay, bandwidth, fee, valueAddTax, agent,director, paymentMethod, settlementCycle,paymentMonth,"
				+ "feeCollection,useRatio, networkUsage )"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public int getBatchSize() {
				return broadbands.size();
			}

			public void setValues(PreparedStatement ps, int index)
					throws SQLException {
				Broadband broadband = broadbands.get(index);
				ps.setString(1, broadband.getBroadbandId());
				java.util.Date  tmpDate = broadband.getStopDate();
				if(null!=tmpDate && ! "".equals(tmpDate)){
					ps.setDate(2, (new java.sql.Date(tmpDate.getTime())));
				}
				else {
					ps.setDate(2, null);
				}
				ps.setString(3, broadband.getCity());
				ps.setString(4, broadband.getStatus());
				ps.setString(5,broadband.getPayOrganization());
				ps.setString(6, broadband.getAddress());
				ps.setString(7, broadband.getLineType());
				ps.setString(8, broadband.getLinkAddress());
				ps.setString(9, broadband.getOperator());
				ps.setString(10, broadband.getAccessWay());
				ps.setString(11, broadband.getBandwidth());
				ps.setBigDecimal(12, broadband.getFee());
				ps.setBigDecimal(13, broadband.getValueAddTax());
				ps.setString(14, broadband.getAgent());
				ps.setString(15, broadband.getDirector());
				ps.setString(16, broadband.getPaymentMethod());
				ps.setString(17, broadband.getSettlementCycle());
				ps.setString(18, broadband.getPaymentMonth());
				ps.setString(19, broadband.getFeeCollection());
				ps.setFloat(20, broadband.getUseRatio());
				ps.setString(21, broadband.getNetworkUsage());
				
			}
		});
		
	}
}

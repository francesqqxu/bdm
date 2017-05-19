package com.etop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.etop.basic.dao.BaseDAO;
import com.etop.dto.ResourceDto;
import com.etop.pojo.Resource;

/**
 * @类名: ResourceDAO
 * @描述: TODO(这里用一句话描述这个类的作用)
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 下午3:01:38
 */
@SuppressWarnings("serial")
@Repository("ResourceDAO")
public class ResourceDAO extends BaseDAO<Resource> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<ResourceDto>  getResourcesByRole(int roleId){
		
		String sql  = "select  r.* , IFNULL( p.permission_id ,0)as rPermission_id,  IFNULL(p.role_id,0) as rRole_id "
				+ " from  t_resource r ,  t_role_permission p where  r.permission_id = p.permission_id  "
				+ " and p.role_id = ?   ";    
		return jdbcTemplate.query(sql,  new Object[]{roleId}, new RowMapper<ResourceDto>(){
				public ResourceDto mapRow(ResultSet rs, int index) throws SQLException{
					ResourceDto resource = new ResourceDto();
					resource.setId(rs.getInt("id"));
					resource.setValue(rs.getString("value"));
					resource.setPermission_id(rs.getInt("permission_id"));
					resource.setRole_id(rs.getInt("role_id"));
					resource.setIconCls(rs.getString("iconCls"));
					resource.setPid(rs.getInt("pid"));
					resource.setState(rs.getString("state"));
					resource.setText(rs.getString("text"));
					resource.setType(rs.getString("type"));
					resource.setrPermission_id(rs.getInt("rPermission_id"));
					resource.setrRole_id(rs.getInt("rRole_id"));
					return resource;
				}
		});
		
		
		
	}
	
}

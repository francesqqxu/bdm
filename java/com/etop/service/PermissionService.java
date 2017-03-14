package com.etop.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etop.dao.PermissionDAO;
import com.etop.dto.PermissionDto;
import com.etop.pojo.Permission;
import com.etop.utils.PageUtil;

/**
 * @类名: PermissionService
 * @描述: 权限服务和DAO对接
 * @作者 liuren-mail@163.com
 * @日期 2015年5月27日 下午3:58:27
 */
@SuppressWarnings("serial")
@Service
public class PermissionService implements Serializable {

	@Autowired
	private PermissionDAO permissionDAO;

	public List<Permission> findAll(){
		return permissionDAO.find("from Permission p");
	}
	@Transactional
	public PageUtil<PermissionDto> findAllPermisson() {
		return permissionDAO.findBySql("select * from t_permission",
				PermissionDto.class, false);
	}
	
	public void savePermission(Permission permission) {
		permissionDAO.save(permission);
	}

	public Permission findPermissionById(int id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		return permissionDAO.findUniqueResult("from Permission p where p.id= :id", params);
	}

	public void updatePermission(Permission permission) {
		permissionDAO.saveOrUpdate(permission);
	}

	public void deletePermission(int id) {
		permissionDAO.deleteById(id);
	}
	public String getNameById(int permission_id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", permission_id);
		Permission permission = permissionDAO.findUniqueResult("from Permission p where p.id= :id", params);
		String name =permission.getPermissionname();
		return name;
	}

}

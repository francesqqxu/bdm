package com.etop.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etop.dao.RoleDAO;
import com.etop.dto.RoleDto;
import com.etop.pojo.Permission;
import com.etop.pojo.Resource;
import com.etop.pojo.Role;
import com.etop.utils.PageUtil;
/**
 * @类名: RoleService 
 * @描述: 角色服务和DAO对接
 * @作者 liuren-mail@163.com
 * @日期 2015年5月26日 下午5:20:03
 */
@SuppressWarnings("serial")
@Service
public class RoleService implements Serializable{
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Transactional
	public List<Role> getAllRole() {
		return roleDAO.find("from Role r");
	}
	
	@Transactional
	public PageUtil<RoleDto> findAllRole() {
		return roleDAO.findBySql("select * from t_role", RoleDto.class, false);
	}

	public  List<Role> getRoleByPid(int pid){
        String sql = "";		
	
       Map<String , Object >   params =  new HashMap<> ();
       params.put("pid", pid);
		sql  = "from Role where pid = :pid ";
    	List<Role> roles = roleDAO.find(sql, params);
    	return roles;
	}
	public Role findById(int id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		return roleDAO.findUniqueResult("from Role r where r.id= :id", params);
	}
 
	

	public void updateRole(int id,String rolename,String description, String authList) {
	   
		Role role = findById(id);
		Resource resource;
		Permission permission;
		Set<Permission> permissions = new HashSet<Permission>();
		String[] authIds = null;
		if(null !=authList && !"".equals(authList)){
			authIds = authList.split(",");
		}
		
		if (role !=null && !"".equals(role)) {
			
			role.setRolename(rolename);
			role.setDescription(description);
			role.setText(rolename);
			role.setIconCls("icon-user");
			role.setState("open");
			role.setPid(0);
			
			
		    for(int i=0; i<authIds.length; i++){
		    	 resource = resourceService.findResourceById(Integer.parseInt(authIds[i]));
		    	 permission = permissionService.findPermissionById(resource.getPermission_id());
		    	 permissions.add(permission);
		    }
		    role.setPermissionList(permissions);
		}
		//throw new RuntimeException("msg");
		roleDAO.saveOrUpdate(role);
	}

	public void saveRole(String rolename,String description, String authList) {
		Role role = new Role();
		Resource resource;
		Permission permission; 
		Set<Permission> permissions = new HashSet<Permission>();
		String[] authIds = null;
		if(null !=authList && !"".equals(authList)){
			authIds = authList.split(",");
		}
		
		if (rolename !=null && description !=null && !"".equals(rolename) && !"".equals(description)) {
			role.setRolename(rolename);
			role.setDescription(description);
			role.setText(rolename);
			role.setIconCls("icon-user");
			role.setState("open");
			role.setPid(0);
			
			for(int i=0; i<authIds.length; i++){
		    	 resource = resourceService.findResourceById(Integer.parseInt(authIds[i]));
		    	 permission = permissionService.findPermissionById(resource.getPermission_id());
		    	 permissions.add(permission);
	    }
	    role.setPermissionList(permissions);
		}
		roleDAO.save(role);
	}

	public void deleteRole(String  ids) {
		Map<String, Object > params = new HashMap<String, Object>();
		String[] idArr = ids.split(",");
	    Integer[] i_idArr = new Integer[idArr.length];
	    
	    for(int i=0; i<idArr.length; i++){
	    	i_idArr[i] = Integer.parseInt(idArr[i]);
	    }
		params.put("ids", i_idArr);
		String sql = "delete from Role where id in :ids";
		roleDAO.excute(sql, params);
 
	}

	public String getNameById(int role_id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", role_id);
		Role role =roleDAO.findUniqueResult("from Role r where r.id= :id", params);
		String rolename = role.getRolename();
		return rolename;
	}
}

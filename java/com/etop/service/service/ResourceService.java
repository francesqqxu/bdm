package com.etop.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etop.dao.ResourceDAO;
import com.etop.dao.RoleDAO;
import com.etop.dto.ResourceDto;
import com.etop.pojo.Resource;
import com.etop.pojo.Role;
import com.etop.utils.PageUtil;

/**
 * @类名: FunctionService
 * @描述: 网页过滤服务，与dao进行对接
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 上午11:46:22
 */
@SuppressWarnings("serial")
@Service("ResourceService")
public class ResourceService implements Serializable {

	@Autowired
	private ResourceDAO resourceDAO;
	
	private RoleDAO  roleDAO;

	/**
	 * 查找所有权限过滤信息
	 * 
	 * @return
	 */
	
	public  List<Resource> getResourceByPid(int pid){
        String sql = "";		
	
       Map<String , Object >   params =  new HashMap<> ();
       params.put("pid", pid);
		sql  = "from Resource where pid = :pid and type='perms'";
    	List<Resource> resources = resourceDAO.find(sql, params);
    	return resources;
	}

	public  List<ResourceDto> getResourceByRole(int roleId ){
       return resourceDAO.getResourcesByRole(roleId);
		
	}
	public List<Resource> findAll() {
		return resourceDAO.find("from Resource f");
	}

	@Transactional
	public PageUtil<Resource> findAllResource() {
		return resourceDAO.findBySql("select * from t_resource",
				ResourceDto.class, false);
	}

	public void saveResource(Resource resource) {
		resourceDAO.save(resource);
	}

	public Resource findResourceById(int id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		return resourceDAO.findUniqueResult("from Resource f where f.id= :id", params);
	}

	public void updateResource(Resource resource) {
		resourceDAO.saveOrUpdate(resource);
	}

	public void deleteResource(int id) {
		resourceDAO.deleteById(id);
	}
}

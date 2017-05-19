package com.etop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.basic.controller.BaseController;
import com.etop.dto.RoleDto;
import com.etop.pojo.Resource;
import com.etop.pojo.Role;
import com.etop.service.RoleService;
import com.etop.utils.PageUtil;

/**
 * @类名: RoleController
 * @描述: 处理角色操作的控制器
 * @作者 liuren-mail@163.com
 * @日期 2015年5月26日 下午6:09:03
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	Map<String,Object> map = new HashMap<String,Object>();
	private final static Logger log = Logger.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;

	@RequestMapping("/roleList.html")
	public String getRoles() {
		log.info("/roleList.html");
		return "/roleList";
	}

	/**
	 * @标题: getRoleList
	 * @描述: 返回json数据
	 * @参数 @return 设定文件
	 * @返回 Object 返回类型
	 * @throws
	 * @作者 liuren-mail@163.com
	 * @日期 2015年5月27日 下午3:56:39
	 */
	@RequestMapping("/get_roles.html")
	@ResponseBody
	public Object getRoleList() {
		log.info("/get_roles.html");
		PageUtil<RoleDto> roleList = roleService.findAllRole();
		return roleList;
	}
	
	
	@RequestMapping("/get_roleById.html")
	@ResponseBody
	public Object getAllRoleById(Integer id) {
		log.info("/get_roleById.html");
		if ( null == id  ) {
			id = 0;
		}
		List<Role> roles = new ArrayList<Role>();
		roles = roleService.getRoleByPid(id);
		return roles;
	}

	@RequestMapping("/get_roles_nopage.html")
	@ResponseBody
	public Object getRolesNOPage() {
	    log.info("get_roles_nopage.html");
		return  roleService.getAllRole();
		
	}
	
	
	
	@RequestMapping("/add.html")
	@ResponseBody
	public Object addRole(String rolename,String description, String authList) {
		log.info("/add.html");
		
		roleService.saveRole( rolename, description,  authList);
		map.put("success", true);
		return map;
	}

	@RequestMapping("/edit.html")
	@ResponseBody
	public Object updateRole(int id,String rolename,String description, String authList) {
		log.info("/edit.html");
		roleService.updateRole(id, rolename, description,  authList);
		map.put("success", true);
		return map;
	}

	@RequestMapping("/del.html")
	@ResponseBody
	public Object deleteRole(String ids) {
		log.info("/del.html");
		roleService.deleteRole(ids);
		map.put("success", true);
		return map;
	}

	@RequestMapping("/roleTest.html")
	public String testRole() {
		log.info("roleTest.html");
		return "/success.jsp";
	}
}

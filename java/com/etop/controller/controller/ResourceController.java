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
import com.etop.dto.ResourceDto;
import com.etop.pojo.Menu;
import com.etop.pojo.Resource;
import com.etop.service.PermissionService;
import com.etop.service.ResourceService;
import com.etop.service.RoleService;
import com.etop.utils.PageUtil;

/**
 * @类名: FunctionController
 * @描述: 处理过滤网址出的控制器
 * @作者 liuren-mail@163.com
 * @日期 2015年5月27日 下午3:31:54
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {
	Map<String,Object> map = new HashMap<String,Object>();
	private final static Logger log = Logger
			.getLogger(ResourceController.class);
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping("/resouceList.html")
	public String getResources() {
		log.info("/resouceList.html");
		return "/resouceList";
	}

	/**
	 * @标题: getFunctionList
	 * @描述: 返回分页的json数据
	 * @参数 @return 设定文件
	 * @返回 Object 返回类型
	 * @throws
	 * @作者 liuren-mail@163.com
	 * @日期 2015年5月27日 下午3:55:12
	 */
	@RequestMapping("/get_resourcesById.html")
	@ResponseBody
	public Object getAllResourceById(Integer id) {
		log.info("/get_resourcesById.html");
		if ( null == id  ) {
			id = 0;
		}
		List<Resource> resources = new ArrayList<Resource>();
		resources = resourceService.getResourceByPid(id);
		return resources;
	}

	@RequestMapping("/get_resourcesByRole.html")
	@ResponseBody
	public Object getAllResourceByRole(Integer roleId) {
		log.info("/get_resourcesByRole.html");
		
		List<ResourceDto> resources = new ArrayList<ResourceDto>();
		resources = resourceService.getResourceByRole(roleId);
		return resources;
	}

	
	@RequestMapping("/get_resources.html")
	@ResponseBody
	public Object getResourceList() {
		log.info("/get_resources.html");
		PageUtil<Resource> resourceList = resourceService.findAllResource();
		return resourceList;
	}

	@RequestMapping("/add.html")
	@ResponseBody
	public Object addResource(String value,int permission_id,int role_id,String type) {
		log.info("/add.html");
		Resource resource = new Resource();
		resource.setPermission_id(permission_id);
		resource.setRole_id(role_id);
		resource.setValue(value);
		resource.setType(type);
		resourceService.saveResource(resource);
		map.put(SUCCESS, true);
		return map;
	}

	@RequestMapping("/edit.html")
	@ResponseBody
	public Object updateResource(int id,String value,int permission_id, int role_id, String type) {
		log.info("/edit.html");
		Resource resource = resourceService.findResourceById(id);
		if (resource==null||"".equals(resource)) {
			map.put(SUCCESS, false);
			return map;
		}
		resource.setPermission_id(permission_id);
		resource.setRole_id(role_id);
		resource.setValue(value);
		resource.setType(type);
		resourceService.updateResource(resource);
		map.put(SUCCESS, true);
		return map;
	}

	@RequestMapping("/del.html")
	@ResponseBody
	public Object deleteResource(int id) {
		log.info("/del.html");
		resourceService.deleteResource(id);
		map.put(SUCCESS, true);
		return map;
	}
}

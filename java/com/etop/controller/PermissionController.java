package com.etop.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.etop.basic.controller.BaseController;
import com.etop.dto.PermissionDto;
import com.etop.pojo.Permission;
import com.etop.service.PermissionService;
import com.etop.utils.PageUtil;

/**
 * @类名: PermissionController
 * @描述: 处理用户权限的控制器
 * @作者 liuren-mail@163.com
 * @日期 2015年5月27日 下午3:28:09
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {
	Map<String,Object> map = new HashMap<String,Object>();
	private final static Logger log = Logger
			.getLogger(PermissionController.class);
	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/permissionList.html")
	public String getPermissons() {
		log.info("/permissionList.html");
		return "/permissonList";
	}

	/**
	 * @标题: getPermissionList
	 * @描述: 返回分页的json数据
	 * @参数 @return 设定文件
	 * @返回 Object 返回类型
	 * @throws
	 * @作者 liuren-mail@163.com
	 * @日期 2015年5月27日 下午3:33:27
	 */
	@RequestMapping("/get_permissions.html")
	@ResponseBody
	public Object getPermissionList() {
		log.info("/get_permission.html");
		PageUtil<PermissionDto> permissionList = permissionService
				.findAllPermisson();
		return permissionList;
	}

	@RequestMapping("/add.html")
	@ResponseBody
	public Object addPermission(String permissionname) {
		log.info("/add.html");
		if (permissionname==null||"".equals(permissionname)) {
			map.put("success", false);
			return map;
		}
		Permission permission = new Permission();
		permission.setPermissionname(permissionname);
		permissionService.savePermission(permission);
		map.put("success", true);
		return map;
	}

	@RequestMapping("/edit.html")
	@ResponseBody
	public Object updatePermission(int id,String permissionname) {
		log.info("/edit.html");
		Permission permission =permissionService.findPermissionById(id);
		if (permission==null||"".equals(permission)) {
			map.put("success", false);
		}
		permission.setPermissionname(permissionname);
		permissionService.updatePermission(permission);
		map.put("success", true);
		return map;
	}

	@RequestMapping("/del.html")
	@ResponseBody
	public Object deletePermission(int id) {
		log.info("/del.html");
		Permission permission = permissionService.findPermissionById(id);
		if (permission==null||"".equals(permission)) {
			map.put("success", false);
			return map;
		}
		permissionService.deletePermission(id);
		map.put("success", true);
		return map;
	}
}

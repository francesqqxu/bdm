package com.etop.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.basic.controller.BaseController;
import com.etop.dto.UserDto;
import com.etop.pojo.Role;
import com.etop.pojo.User;
import com.etop.service.RoleService;
import com.etop.service.UserService;
import com.etop.utils.MD5Utils;
import com.etop.utils.PageUtil;

/**
 * @类名: UserController
 * @描述: 处理用户操作的控制器
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 下午3:10:19
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	Map<String, Object> map = new HashMap<String, Object>();
	private final static Logger log = Logger.getLogger(UserController.class);
	private UserService userService;
    private RoleService roleService;
    
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setRoleService(RoleService roleService){
		this.roleService = roleService;
	}

	// add,edit,del页面并没有写具体逻辑，要验证是否成功，需要观察控制台输出。
	@RequestMapping(value = "/get_users.html")
	@ResponseBody
	public Object getUsers() {
		log.info("/get_users.html");
		PageUtil<UserDto> pageList = userService.findAllUser();
		return pageList;
	}

	@RequestMapping("/userList.html")
	public String userList() {
		log.info("/userList.html");
		return "/userList";
	}

	@RequestMapping("get_rolesByUser.html")
	@ResponseBody
	public Object getRolesByUser(int userId){
		log.info("/get_rolesByUser.html");
		return userService.getRoleByUser( userId);
	}
	@RequestMapping("/add.html")
	@ResponseBody
	public Object addUser(String username, String password, String chineseName , String[] roleList) {
		log.info("/add.html");
		
		userService.saveUser(username,  password,  chineseName ,   roleList);
		map.put("success", true);
		return map;
	}

	@RequestMapping("/edit.html")
	@ResponseBody
	public Object updateUser(int id, String username, String password, String chineseName, String  roleList) {
		log.info("/edit.html");
		String[] roleIds = null;
		if(null != roleList && !"".equals(roleList)){
			 roleIds = roleList.split(",");
		}
		User user = userService.findById(id);
		if (user == null || "".equals(user)) {
			map.put("success", false);
			return map;
		}
		MD5Utils MD5 = new MD5Utils();
		Set<Role> roles = new HashSet<Role>();
		
		if (username != null  &&  password != null && ! "".equals(username)
				&& !"".equals(password)) {
			if(!user.getPassword().equals(password)){
				String md5_password = MD5.GetMD5Code(password);
				user.setPassword(md5_password);
			}
			user.setUsername(username);
			user.setChineseName(chineseName);
			for(int i=0; i<roleIds.length; i++){
				roles.add(roleService.findById(Integer.parseInt(roleIds[i])));
			}
			user.setRoleList(roles);
		}
		
	
		userService.updateUser(user);
		
		map.put("success", true);
		System.out.println("=========================================>要修改的id为:"
				+ id);
		return map;
	}

	@RequestMapping("/editPwd.html")
	@ResponseBody
	public Object updatePwd(int  id, String oldPwd, String newPwd){
		log.info("/editPwd.html");
		MD5Utils MD5 = new MD5Utils();
		User user = userService.findById(id);
	   if(null == user|| "".equals(user)){
		   map.put("success", false);
		   return map;
	   }
	   
	   if(!userService.checkPwd(id, oldPwd)){
		   map.put("success", false);
		   map.put("msg", "原密码不正确！");
		   return map;
	   }
		
	   String md5_pwd = MD5.GetMD5Code(newPwd);
	   user.setPassword(md5_pwd);
       userService.updateUser(user);
       map.put("success", true);
       map.put("msg", "密码修改成功！") ;
       return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/del.html")
	public Object deleteUser(String ids) {
		log.info("/del.html");
		log.info(ids);
		userService.deleteUser(ids);
		map.put("success", true);
		System.out.println("=========================================>要删除的id为:"
				+ ids);
		return map;
	}
}

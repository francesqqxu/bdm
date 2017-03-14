package com.etop.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.dao.UserDAO;
import com.etop.dto.UserDto;
import com.etop.pojo.Role;
import com.etop.pojo.User;
import com.etop.utils.MD5Utils;
import com.etop.utils.PageUtil;

/**
 * @类名: UserService
 * @描述: 用户服务，与dao进行对接
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 下午3:03:20
 */
@SuppressWarnings("serial")
@Service("UserService")
public class UserService implements Serializable {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleService roleService;
	/**
	 * 通过用户名查找用户信息
	 * 
	 * @param username
	 * @return
	 */
	public User findByName(String username) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", username);
		return userDAO.findUniqueResult("from User u where u.username = :name",
				params);
	}

	public List<User> getAllUser() {
		return userDAO.find("from User u");
	}

	public PageUtil<UserDto> findAllUser() {
		return userDAO.findBySql("select * from t_user", UserDto.class, false);
	}

	public void saveUser(String username, String password, String chineseName, String[] roleList) {
		MD5Utils MD5 = new MD5Utils();
		User user = new User();
		Set<Role> roles = new HashSet<Role>();
		if (username != null  &&  password != null && ! "".equals(username)
				&& !"".equals(password)) {
			
			String md5_password = MD5.GetMD5Code(password);
			user.setUsername(username);
			user.setPassword(md5_password);
			user.setChineseName(chineseName);
			for(int i=0; i<roleList.length; i++){
				roles.add(roleService.findById(Integer.parseInt(roleList[i])));
			}
			user.setRoleList(roles);
			//throw new RuntimeException("msg");
			userDAO.save(user);
		}
	}

	public User findById(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return userDAO.findUniqueResult("from User u where u.id = :id", params);
	}
	
	public Set<Role> getRoleByUser(int id){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		User user =  userDAO.findUniqueResult("from User u where u.id = :id", params);
	    return user.getRoleList();
	}

	public void updateUser(User user) {
		     
		   // throw new RuntimeException("msg");
		   userDAO.saveOrUpdate(user);
	}

	public void deleteUser(String ids) {
		Map<String, Object > params = new HashMap<String, Object>();
		String[] idArr = ids.split(",");
	    Integer[] i_idArr = new Integer[idArr.length];
	    
	    for(int i=0; i<idArr.length; i++){
	    	i_idArr[i] = Integer.parseInt(idArr[i]);
	    }
		params.put("ids", i_idArr);
		String sql = "delete from User where id in :ids";
		userDAO.excute(sql, params);
	}
	
	public boolean checkPwd(int id, String pwd){
		Map<String , Object> params = new HashMap<String, Object>();
		MD5Utils MD5 = new MD5Utils();
		
		String md5_password = MD5.GetMD5Code(pwd);
		params.put("id", id);
		params.put("pwd",md5_password);
		User user = userDAO.findUniqueResult("from User u where u.id = :id and password = :pwd", params);
		if ( null != user ) {
			return true;
		}else {
			return false;
		}
			
		
	}
}

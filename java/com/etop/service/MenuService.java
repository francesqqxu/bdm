package com.etop.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.dao.MenuDAO;
import com.etop.pojo.Menu;




/**
 * @类名：MenuService
 * @description: 导航菜单服务， 与MenuDao对接
 * @date: 2016-03-28
 * @author frances.xu
 *
 */

@SuppressWarnings("serial")
@Service("MenuService")
public class MenuService implements Serializable {
	
	@Autowired
	private MenuDAO  menuDAO;
	
	public  List<Menu> getMenuByPid(int pid){
        String sql = "";		
	
       Map<String , Object >   params =  new HashMap<> ();
       params.put("pid", pid);
		sql  = "from Menu where pid = :pid";
    	List<Menu> menus = menuDAO.find(sql, params);
    	return menus;
	}

}

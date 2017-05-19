package com.etop.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.basic.controller.BaseController;
import com.etop.pojo.Menu;
import com.etop.service.MenuService;


@Controller
@RequestMapping("/menu")
public class menuController extends BaseController {
	
	private final static  Logger log = Logger.getLogger(menuController.class);
	private MenuService  menuService;

	
	@Autowired
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}


   @RequestMapping("/get_menu.html")
   @ResponseBody
	public  Object getMenus(Integer  id) {
		log.info("/get_menu.html");
		
		if ( null == id  ) {
			id = 0;
		}
		List<Menu> menus = new ArrayList<Menu>();
		menus = menuService.getMenuByPid(id);
		return menus;
		
	}

}

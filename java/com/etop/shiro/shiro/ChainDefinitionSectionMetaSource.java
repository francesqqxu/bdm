package com.etop.shiro;

import com.etop.pojo.Resource;
import com.etop.service.ResourceService;
import com.etop.service.PermissionService;
import com.etop.service.RoleService;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

/**
 * @类名: ChainDefinitionSectionMetaSource
 * @描述: 产生责任链，确定每个url的访问权限
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 下午3:12:53
 */
public class ChainDefinitionSectionMetaSource implements
		FactoryBean<Ini.Section> {

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	// 静态资源访问权限
	private String filterChainDefinitions = "/static/**=anon";

	@Override
	public Ini.Section getObject() throws Exception {
		List<Resource> list = resourceService.findAll();
		Ini ini = new Ini();
		// 加载默认的url
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		// 循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,
		// 里面的键就是链接URL,值就是存在什么条件才能访问该链接
		for (Iterator<Resource> it = list.iterator(); it.hasNext();) {
			Resource resource = it.next();
			int permission_id = resource.getPermission_id();
			int role_id = resource.getRole_id();
			// 构成permission字符串
			if (StringUtils.isNotEmpty(resource.getValue())
					&& StringUtils.isNotEmpty(resource.getType())) {
				String permission = "";
				switch (resource.getType()) {
				case "anon":
					permission = "anon";
					break;
				case "perms":
					permission = "perms["
							+ permissionService.getNameById(permission_id)//function.getPermission().getPermissionname()
							+ "]";
					break;
				case "roles":
					permission = "roles[" 
							+ roleService.getNameById(role_id)//function.getRole().getRolename()
							+ "]";
					break;
				default:
					break;
				}
				section.put(resource.getValue(), permission);
			}

		}
		// 所有资源的访问权限，必须放在最后
       section.put("/**", "authc");
		return section;
	}

	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}

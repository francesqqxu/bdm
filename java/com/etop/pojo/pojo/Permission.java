package com.etop.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @类名: Permission
 * @描述: 权限类，保存权限信息与对应的角色（多对一）
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 上午11:26:31
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_permission")
public class Permission implements Serializable {

	private Integer id;
	private String permissionname;
	private Set<Role> roles;
	public Permission() {
		super();
	}

	public Permission(Integer id, String permissionname, Set<Role> roles) {
		super();
		this.id = id;
		this.permissionname = permissionname;
		this.roles = roles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermissionname() {
		return permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "permissionList", fetch = FetchType.LAZY)
	public Set<Role> getRole() {
		return roles;
	}

	public void setRole(Set<Role> roles) {
		this.roles= roles;
	}
}
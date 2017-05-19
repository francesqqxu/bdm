package com.etop.pojo;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @类名: Role
 * @描述: 角色类，用于保存角色信息、用户列表（多对多）与角色（多对多）对应的权限
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 下午3:07:42
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_role")
public class Role implements Serializable {

	private Integer id;
	private String text;
	private String rolename;
	private String description;
	private String state;
	private String iconCls;
	private Integer pid;
	private Set<Permission> permissionList;
	private Set<User> userList;

	public Role() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Role(Integer id, String rolename, Set<Permission> permissionList,
			Set<User> userList) {
		super();
		this.id = id;
		this.rolename = rolename;
		this.permissionList = permissionList;
		this.userList = userList;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(targetEntity = Permission.class, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE },fetch = FetchType.LAZY)
	@JsonIgnore
	// 防止无限循环
	@JoinTable(name = "t_role_permission", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "permission_id") })
	public Set<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(Set<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	@ManyToMany(targetEntity = com.etop.pojo.User.class, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE }, mappedBy="roleList", fetch=FetchType.LAZY)
	public Set<User> getUserList() {
		return userList;
	}

	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}

	@Transient
	public Set<String> getPermissionsName() {
		Set<String> list = new HashSet<>();
		Set<Permission> perlist = getPermissionList();
		for (Permission per : perlist) {
			list.add(per.getPermissionname());
		}
		return list;
	}

	private class User {
	}
	
}
package com.etop.pojo;

import javax.persistence.*;

import java.io.Serializable;

/**
 * @类名: Function
 * @描述: 网页过滤信息类，保存网页过滤信息，以及对应的权限(一对一)或角色(一对一)
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 下午3:07:03
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_resource")
public class Resource implements Serializable {
	/**
	 * 这个是id
	 */
	private Integer id;
	
	private String text;
	
	private String state;
	
	private String iconCls;
	
	private Integer pid;
	/**
	 * 这个是过滤的url
	 */
	private String value;
//	private Permission permission;
//	private Role role;
	private int permission_id;
	private int role_id;
	private String type;

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

	
	public Resource() {
		super();
	}

//	public Function(Integer id, String value, Permission permission, Role role,
//			String type) {
//		super();
//		this.id = id;
//		this.value = value;
//		this.permission = permission;
//		this.role = role;
//		this.type = type;
//	}
	public Resource(Integer id, String value, int permission_id, int role_id,
			String type) {
		super();
		this.id = id;
		this.value = value;
		this.permission_id = permission_id;
		this.role_id = role_id;
		this.type = type;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

//	@OneToOne 
//	@JoinColumn(name = "permission_id")
//	public Permission getPermission() {
//		return permission;
//	}
//
//	public void setPermission(Permission permission) {
//		this.permission = permission;
//	}
//
//	@OneToOne
//	@JoinColumn(name = "role_id")
//	public Role getRole() {
//		return role;
//	}
//
//	public void setRole(Role role) {
//		this.role = role;
//	}
	public int getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(int permission_id) {
		this.permission_id = permission_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

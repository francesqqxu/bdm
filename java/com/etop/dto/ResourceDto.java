package com.etop.dto;

public class ResourceDto {

	private Integer id;
    private String text;
	
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

	private String state;
	
	private String iconCls;
	
	private Integer pid;
	
	private String value;
	private Integer permission_id;
	private Integer role_id;
	private String type;
	
	private Integer rPermission_id;
	
	private Integer rRole_id;
	

	public Integer getrPermission_id() {
		return rPermission_id;
	}

	public void setrPermission_id(Integer rPermission_id) {
		this.rPermission_id = rPermission_id;
	}

	public Integer getrRole_id() {
		return rRole_id;
	}

	public void setrRole_id(Integer rRole_id) {
		this.rRole_id = rRole_id;
	}

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

	public Integer getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(Integer permission_id) {
		this.permission_id = permission_id;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

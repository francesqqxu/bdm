package com.etop.pojo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @类名: User
 * @描述: 用户类，保存用户信息与角色（多对多）
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 下午3:08:11
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_user")
public class User implements Serializable {

	private Integer id;
	@NotEmpty(message = "用户名不能为空")
	private String username;
	@NotEmpty(message = "密码不能为空")
	private String password;
	 
	private String chineseName;
	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	private Set<Role> roleList;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToMany(targetEntity=Role.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE  }, fetch = FetchType.LAZY)
	@JsonIgnore
	// 防止无限循环
	@JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	public Set<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<Role> roleList) {
		this.roleList = roleList;
	}

	@Transient
	public Set<String> getRolesName() {
		Set<Role> roles = getRoleList();
		Set<String> set = new HashSet<String>();
		for (Role role : roles) {
			set.add(role.getRolename());
		}
		return set;
	}

	public User() {
		super();
	}

	public User(Integer id, String username, String password, Set<Role> roleList) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roleList = roleList;
	}
}
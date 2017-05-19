package com.etop.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @类名: Dept
 * @description: 
 * @date: 2016-04-01
 * @author frances.xu
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name="t_dept")
public class Dept implements Serializable {
	
	private Integer id;
	
	private String dno;
	
	private String dname;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDno() {
		return dno;
	}
	public void setDno(String dno) {
		this.dno = dno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	

}

package com.lw.jk.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Dept implements Serializable{

	private String id; 
	
	private String deptName ;
	
	private Dept parent ; // 父部门 子管理
	
	private Integer state ; // 1 :启用 0 :停用
	
	private Set<Dept> users = new HashSet<>(0) ;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Dept getParent() {
		return parent;
	}

	public void setParent(Dept parent) {
		this.parent = parent;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set<Dept> getUsers() {
		return users;
	}

	public void setUsers(Set<Dept> users) {
		this.users = users;
	}


	
	
	
}
